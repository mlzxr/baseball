package com.feigong.baseball.myinfo;/**
 * Created by ruler on 17/2/15.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;

import com.feigong.baseball.R;
import com.feigong.baseball.application.App;
import com.feigong.baseball.base.common.JSONUtil;
import com.feigong.baseball.base.fragment.BaseFragment;
import com.feigong.baseball.base.util.DateUtil;
import com.feigong.baseball.base.util.L;
import com.feigong.baseball.base.util.SPUtils;
import com.feigong.baseball.base.util.T;
import com.feigong.baseball.base.view.util.ViewUtil;
import com.feigong.baseball.beans.ReturnMSG;
import com.feigong.baseball.beans.ReturnMSG_UserInfo;
import com.feigong.baseball.common.Constant;
import com.feigong.baseball.common.GetUrl;
import com.feigong.baseball.fgview.ViewTopBar;
import com.feigong.baseball.fgview.View_ITTI_Horizontal;
import com.feigong.baseball.weibo.openapi.UsersAPI;
import com.feigong.baseball.weibo.openapi.WBUser;
import com.google.gson.Gson;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendAuth;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;

/**
 * 项目名称：baseball
 * 类名称： SocialFragment
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：2017.02.15 12:13
 * 备注：社交帐号绑定
 *
 * @version 1.0
 */
public class SocialFragment extends BaseFragment {

    private static final String TAG="SocialFragment";

    private View_ITTI_Horizontal view_itti_qq;
    private View_ITTI_Horizontal view_itti_wx;
    private View_ITTI_Horizontal view_itti_xl;
    //
    private IWXAPI iwxapi;
    private Tencent mTencent;
    private UserInfo mInfo;

    private AuthInfo mAuthInfo;
    private Oauth2AccessToken mAccessToken;
    private SsoHandler mSsoHandler;

    private UsersAPI mUsersAPI;


    public static SocialFragment newInstance() {
        SocialFragment socialFragment = new SocialFragment();
        return socialFragment;
    }

    public class MyStringCallback extends StringCallback
    {
        @Override
        public void onBefore(Request request, int id)
        {
            L.e(TAG,"onBefore");
        }

        @Override
        public void onAfter(int id)
        {
            L.e(TAG,"onAfter");
        }

        @Override
        public void onError(Call call, Exception e, int id)
        {
            L.e(TAG,e.getMessage());
        }

        @Override
        public void onResponse(String response, int id)
        {
            L.e(TAG,response);
            switch (id)
            {

                case 101:
                    ReturnMSG_UserInfo returnMSG_userInfo =  new Gson().fromJson(response,ReturnMSG_UserInfo.class);
                    if(returnMSG_userInfo!=null && returnMSG_userInfo.getCode()==Constant.FGCode.OpOk_code){
                        ReturnMSG_UserInfo.DataBean dataBean= returnMSG_userInfo.getData();
                        if(dataBean!=null && dataBean.getLoginInfo()!=null){

                            if(dataBean.getLoginInfo()!=null){
                                if(dataBean.getLoginInfo().getIsBindQQ()==1){
                                    setUnbundleText(view_itti_qq,1);
                                }else {
                                    setBindingText(view_itti_qq,0);
                                }
                                //
                                if(dataBean.getLoginInfo().getIsBindWechat()==1){
                                    setUnbundleText(view_itti_wx,1);
                                }else {
                                    setBindingText(view_itti_wx,0);
                                }
                                //
                                if(dataBean.getLoginInfo().getIsBindWeibo()==1){
                                    setUnbundleText(view_itti_xl,1);
                                }else {
                                    setBindingText(view_itti_xl,0);
                                }
                            }
                        }
                    }
                    break;


                case 102:
                    ReturnMSG returnMSG = new Gson().fromJson(response,ReturnMSG.class);
                    if(returnMSG!=null&& returnMSG.getMsg()!=null){
                        T.showShort(App.getContext(),returnMSG.getMsg());
                    }else {
                        T.showShort(App.getContext(),R.string.operation_failed_please_try_again_later);
                    }
                    break;

                case 103:


                    break;
            }
        }

        @Override
        public void inProgress(float progress, long total, int id)
        {
        }
    }

    @Override
    public void onStart() {
        super.onStart();
            L.e(TAG,"显示界面");


    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_social;
    }

    @Override
    protected void initVariables() {
        T.showShort(App.getContext(),"wx");
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        ViewTopBar viewTopBar = (ViewTopBar)view.findViewById(R.id.viewTopBar);
        viewTopBar.getTv_title().setText(getString(R.string.social_account));
        viewTopBar.getIv_back().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        //
        view_itti_qq =(View_ITTI_Horizontal)view.findViewById(R.id.view_itti_qq);
        view_itti_wx =(View_ITTI_Horizontal)view.findViewById(R.id.view_itti_wx);
        view_itti_xl =(View_ITTI_Horizontal)view.findViewById(R.id.view_itti_sl);
        //
        view_itti_qq.getLeftImageView().setImageResource(R.mipmap.icon_qq);
        view_itti_wx.getLeftImageView().setImageResource(R.mipmap.icon_wx);
        view_itti_xl.getLeftImageView().setImageResource(R.mipmap.icon_xl);
        //
        view_itti_qq.getCentreTextView().setText(R.string.qq);
        view_itti_wx.getCentreTextView().setText(R.string.wx);
        view_itti_xl.getCentreTextView().setText(R.string.xl);
        //
        setBindingText(view_itti_qq,0);
        setBindingText(view_itti_wx,0);
        setBindingText(view_itti_xl,0);
        //QQ
        view_itti_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(v,Constant.Other.QQ);
            }
        });
        //微信
        view_itti_wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(v,Constant.Other.WX);
            }
        });
        //微博
        view_itti_xl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(v,Constant.Other.WB);
            }
        });

    }

    @Override
    protected void loadData() {
        String token = String.valueOf(SPUtils.get(App.getContext(),Constant.TOKEN,""));
        String url = GetUrl.getUserInfoByToken();
        OkHttpUtils
                .get()
                .url(url)
                .addHeader(Constant.TOKEN,token)
                .id(101)
                .build()
                .execute(new MyStringCallback());
    }

    private void update(View view, final String type){
        int flag = ViewUtil.getTagInt(view);
        if(flag==1){
            String message ="";
            switch (type){
                case Constant.Other.QQ:
                    message=getString(R.string.sure_unbundle_qq);
                    break;
                case Constant.Other.WX:
                    message=getString(R.string.sure_unbundle_wx);
                    break;

                case Constant.Other.WB:
                    message=getString(R.string.sure_unbundle_wb);
                    break;
            }
            /**
             * 解绑第三方登陆
             */
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.hint);
            builder.setMessage(message);
            builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    String token = String.valueOf(SPUtils.get(App.getContext(),Constant.TOKEN,""));
                    String url = GetUrl.unbundleOther(type);
                    L.e(TAG,url);
                    OkHttpUtils
                            .get()
                            .url(url)
                            .addHeader(Constant.TOKEN,token)
                            .id(102)
                            .build()
                            .execute(new MyStringCallback());
                    dialog.dismiss();
                }
            });
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();
        }else {
            /**
             * 打开第三方登陆
             */
            switch (type){

                case Constant.Other.QQ:
                    loginQQ();
                    break;
                case Constant.Other.WX:
                    loginWX();
                    break;

                case Constant.Other.WB:
                    loginWB();
                    break;
            }
        }
    }

    /**
     *  QQ登陆
     */
    private void loginQQ() {
        L.e(TAG, "loginQQ");
        mTencent = Tencent.createInstance(Constant.QQ.APP_ID, getActivity());
        if(!mTencent.isSessionValid()){
            mTencent.login(getActivity(),"all",loginListener);
        }
    }

    /**
     * 微信登陆
     */
    private void loginWX() {
        L.e(TAG, "loginWX");
        iwxapi = WXAPIFactory.createWXAPI(getActivity(), Constant.WX.APP_ID, true);
        if (!iwxapi.isWXAppInstalled()) {
            T.showShort(App.getContext(), R.string.check_wx);
            return;
        }
        iwxapi.registerApp(Constant.WX.APP_ID);
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "feigong_wx_login";
        iwxapi.sendReq(req);
    }

    /**
     * 微博登陆
     */
    private void loginWB() {
        L.e(TAG, "loginWB");
        mAuthInfo = new AuthInfo(getActivity(), Constant.WB.APP_KEY, Constant.WB.REDIRECT_URL, Constant.WB.SCOPE);
        mSsoHandler = new SsoHandler(getActivity(), mAuthInfo);
        mSsoHandler.authorizeClientSso(new AuthListener());
    }

    /**
     * 第三方绑定
     * @param openid
     * @param type
     */
    private void socialBind(String openid,String type){
        String token = String.valueOf(SPUtils.get(App.getContext(),Constant.TOKEN,""));
        String url = GetUrl.goSocialBind();
        L.e(TAG,url);
        Map<String, String> params = new HashMap<String, String>();
        params.put("openid", openid);
        params.put("identity_type", type);
        String json = new Gson().toJson(params);
        OkHttpUtils
                .postString()
                .url(url)
                .addHeader(Constant.TOKEN,token)
                .id(103)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(json)
                .build()
                .execute(new MyStringCallback());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        L.e(TAG,"onActivityResult");
        //微博回调
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
        //QQ回调
        if (requestCode == Constants.REQUEST_LOGIN ||
                requestCode == Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode,resultCode,data,loginListener);
        }
    }
    /**
     * 微博认证授权回调类。
     */
    class AuthListener implements WeiboAuthListener {

        @Override
        public void onComplete(Bundle values) {
            // 从 Bundle 中解析 Token
            mAccessToken = Oauth2AccessToken.parseAccessToken(values);
            //从这里获取用户输入的 电话号码信息
            String phoneNum = mAccessToken.getPhoneNum();
            if (mAccessToken.isSessionValid()) {
                // 显示 Token
                T.showLong(App.getContext(), R.string.auth_success);
                mUsersAPI = new UsersAPI(getActivity(), Constant.WB.APP_KEY, mAccessToken);
                long uid = Long.parseLong(mAccessToken.getUid());
                mUsersAPI.show(uid, mListener);


            } else {
                // 以下几种情况，您会收到 Code：
                // 1. 当您未在平台上注册的应用程序的包名与签名时；
                // 2. 当您注册的应用程序包名与签名不正确时；
                // 3. 当您在平台上注册的包名和签名与您当前测试的应用的包名和签名不匹配时。
                String code = values.getString("code");
                String message = getString(R.string.auth_failed);
                if (!TextUtils.isEmpty(code)) {
                    message = message + "\nObtained the code: " + code;
                }
                T.showLong(App.getContext(), message);
            }
        }

        @Override
        public void onCancel() {
            T.showShort(App.getContext(), R.string.auth_canceled);
        }

        @Override
        public void onWeiboException(WeiboException e) {
            T.showShort(App.getContext(), e.getMessage());
        }
    }


    /**
     * 微博 OpenAPI 回调接口。
     */
    private RequestListener mListener = new RequestListener() {
        @Override
        public void onComplete(String response) {
            if (!TextUtils.isEmpty(response)) {
                L.e(TAG, response);
                // 调用 User#parse 将JSON串解析成User对象
                WBUser user = WBUser.parse(response);
                if (user != null) {
                    socialBind(user.id,Constant.Other.WB);
                } else {
                    L.e(TAG, response);
                }
            }
        }

        @Override
        public void onWeiboException(WeiboException e) {
            L.e(TAG, e.getMessage());

        }
    };


    /**
     * QQ
     */
    IUiListener loginListener = new BaseUiListener() {
        @Override
        protected void doComplete(JSONObject values) {
            initOpenidAndToken(values);
            updateUserInfo();
        }
    };

    private void initOpenidAndToken(JSONObject jsonObject) {
        try {
            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                mTencent.setAccessToken(token, expires);
                mTencent.setOpenId(openId);
            }
        } catch(Exception e) {

        }
    }

    private void updateUserInfo() {
        if (mTencent != null && mTencent.isSessionValid()) {
            IUiListener listener = new IUiListener() {
                @Override
                public void onError(UiError e) {

                }

                @Override
                public void onComplete(final Object response) {
                    JSONObject json = (JSONObject)response;
                    socialBind(mTencent.getOpenId(),Constant.Other.QQ);
                }
                @Override
                public void onCancel() {

                }
            };
            mInfo = new UserInfo(getActivity(), mTencent.getQQToken());
            mInfo.getUserInfo(listener);
        }
    }


    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            if (null == response) {
                T.showLong(App.getContext(),R.string.auth_failed);
                return;
            }
            JSONObject jsonResponse = (JSONObject) response;
            if (null != jsonResponse && jsonResponse.length() == 0) {
                T.showLong(App.getContext(),R.string.auth_failed);
                return;
            }
            T.showLong(App.getContext(),R.string.auth_success);
            doComplete((JSONObject)response);
        }

        protected void doComplete(JSONObject values) {

        }

        @Override
        public void onError(UiError e) {
            T.showLong(App.getContext(),"onError: " + e.errorDetail);
        }

        @Override
        public void onCancel() {
            T.showLong(App.getContext(),"onCancel: ");
        }
    }


    private void setUnbundleText(View_ITTI_Horizontal view_itti_horizontal, int type){
        view_itti_horizontal.getRightTextView().setText(R.string.unbundle);
        view_itti_horizontal.getRightTextView().setTextColor(Color.parseColor("#c0c0c0"));
        view_itti_horizontal.setTag(type);
    }

    private void setBindingText(View_ITTI_Horizontal view_itti_horizontal,int type){
        view_itti_horizontal.getRightTextView().setText(R.string.binding);
        view_itti_horizontal.getRightTextView().setTextColor(Color.BLACK);
        view_itti_horizontal.setTag(type);
    }

}
