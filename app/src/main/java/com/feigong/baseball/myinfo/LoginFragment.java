package com.feigong.baseball.myinfo;/**
 * Created by ruler on 16/12/7.
 */

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.feigong.baseball.R;
import com.feigong.baseball.application.App;
import com.feigong.baseball.base.common.JSONUtil;
import com.feigong.baseball.base.fragment.BaseFragment;
import com.feigong.baseball.base.util.L;
import com.feigong.baseball.base.util.SPUtils;
import com.feigong.baseball.base.util.T;
import com.feigong.baseball.beans.ReturnMSG_UserInfo;
import com.feigong.baseball.common.Constant;
import com.feigong.baseball.common.GetUrl;
import com.feigong.baseball.fgview.AutoZoomInImageView;
import com.feigong.baseball.weibo.openapi.UsersAPI;
import com.feigong.baseball.weibo.openapi.WBUser;
import com.feigong.baseball.wxapi.ResultTokenWX;
import com.feigong.baseball.wxapi.UserInfoWX;
import com.feigong.baseball.wxapi.WXEntryActivity;
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
import com.tencent.open.utils.Util;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.GenericsCallback;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;

/**
 * 项目名称：baseball
 * 类名称： LoginFragment
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：2016.12.07 22:51
 * 备注：登陆
 *
 * @version 1.0
 */
public class LoginFragment extends BaseFragment {

    private static final String TAG = "LoginFragment";

    private AutoZoomInImageView autoZoomInImageView;
    private ImageView iv_logo;
    private TextView tv_title, tv_look;
    private LinearLayout ll_login;

    private IWXAPI iwxapi;
    private Tencent mTencent;
    private UserInfo mInfo;

    private AuthInfo mAuthInfo;
    private Oauth2AccessToken mAccessToken;
    private SsoHandler mSsoHandler;

    private UsersAPI mUsersAPI;

    public static LoginFragment newInstance() {
        LoginFragment loginFragment = new LoginFragment();
        return loginFragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        iv_logo = (ImageView) view.findViewById(R.id.iv_logo);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        ll_login = (LinearLayout) view.findViewById(R.id.ll_login);

        //
        autoZoomInImageView = (AutoZoomInImageView) view.findViewById(R.id.zoomInImageView);
        autoZoomInImageView.post(new Runnable() {

            @Override
            public void run() {
                //使用较为具体的方式启动放大动画
                autoZoomInImageView.init()
                        .setScaleDelta(0.1f)//放大的系数是原来的（1 + 0.3）倍
                        .setDurationMillis(1500)//动画的执行时间为1000毫秒
                        .setOnZoomListener(new AutoZoomInImageView.OnZoomListener() {
                            @Override
                            public void onStart(View view) {//放大动画开始时的回调

                                tv_title.setVisibility(View.VISIBLE);
                                AnimationSet fadeOutAnim = (AnimationSet) AnimationUtils.loadAnimation(App.getContext(), R.anim.fade_out_anim);
                                fadeOutAnim.setDuration(1000);
                                fadeOutAnim.setAnimationListener(new Animation.AnimationListener() {
                                    @Override
                                    public void onAnimationStart(Animation animation) {

                                    }

                                    @Override
                                    public void onAnimationEnd(Animation animation) {

                                    }

                                    @Override
                                    public void onAnimationRepeat(Animation animation) {

                                    }
                                });
                                tv_title.startAnimation(fadeOutAnim);
                            }

                            @Override
                            public void onUpdate(View view, float progress) { //放大动画进行过程中的回调 progress取值范围是[0,1]

                            }

                            @Override
                            public void onEnd(View view) {//放大动画结束时的回调

                                loadingView();
                            }
                        })
                        .start(200);//延迟200毫秒启动

            }
        });
        //
        view.findViewById(R.id.tv_look).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        //

        view.findViewById(R.id.iv_icon_wx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginWX();
            }
        });
        view.findViewById(R.id.iv_icon_wb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginWB();
            }
        });
        view.findViewById(R.id.iv_icon_qq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginQQ();
            }
        });


    }

    @Override
    protected void loadData() {

    }

    private void loadingView() {
        AnimationSet animationSetLogin = (AnimationSet) AnimationUtils.loadAnimation(App.getContext(), R.anim.login_anim);
        animationSetLogin.setDuration(2000);
        animationSetLogin.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                tv_title.setVisibility(View.GONE);
                iv_logo.setVisibility(View.VISIBLE);
                ll_login.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        iv_logo.startAnimation(animationSetLogin);
        ll_login.startAnimation(animationSetLogin);
    }


    //第三方登陆
    private void loginWX() {//微信登陆
        L.e(TAG, "loginWX");
        iwxapi = WXAPIFactory.createWXAPI(getActivity(), Constant.WX.APP_ID, true);
        if (!iwxapi.isWXAppInstalled()) {
            T.showLong(App.getContext(), R.string.check_wx);
            return;
        }
        iwxapi.registerApp(Constant.WX.APP_ID);
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "feigong_wx_login";
        iwxapi.sendReq(req);


    }

    private void loginWB() {//微博登陆
        L.e(TAG, "loginWB");
        mAuthInfo = new AuthInfo(getActivity(), Constant.WB.APP_KEY, Constant.WB.REDIRECT_URL, Constant.WB.SCOPE);
        mSsoHandler = new SsoHandler(getActivity(), mAuthInfo);
        mSsoHandler.authorizeClientSso(new AuthListener());

    }

    private void loginQQ() {//QQ登陆
        L.e(TAG, "loginQQ");
        mTencent = Tencent.createInstance(Constant.QQ.APP_ID, getActivity());
        if(!mTencent.isSessionValid()){
            mTencent.login(getActivity(),"all",loginListener);
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
                    L.e(TAG, "获取User信息成功，用户昵称：" + user.screen_name);
                    String openid = user.id;
                    String nickname = user.screen_name;
                    String avator = user.avatar_large;
                    //获取新用户实例
                    getFGUserInfo(openid,nickname,avator,Constant.Other.WB);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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

    IUiListener loginListener = new BaseUiListener() {
        @Override
        protected void doComplete(JSONObject values) {
            L.e(TAG,"values:"+values.toString());
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
                    L.e(TAG,"json:"+json.toString());
                    String openid = mTencent.getOpenId();
                    String nickname = JSONUtil.getValue(json,"nickname");
                    String avator = JSONUtil.getValue(json,"figureurl_qq_2");
                    //获取新用户实例
                    getFGUserInfo(openid,nickname,avator,Constant.Other.QQ);
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

    /**
     * 获取第三方信息保存数据，返回新的非攻用户
     * @param openid
     * @param nickname
     * @param avator
     */
    private void getFGUserInfo(String openid,String nickname,String avator,String type){

        String url = GetUrl.getSocialLogin(type);
        //
        Map<String, String> params = new HashMap<String, String>();
        params.put("openid", openid);
        params.put("nickname", nickname);
        params.put("avator", avator);

        String json = new Gson().toJson(params);
        L.e(TAG,json);
        OkHttpUtils
                .postString()
                .url(url)
                .id(100)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(json)
                .build()
                .execute(new MyStringCallback());
    }

    public class MyStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request, int id) {
            L.e(TAG, "onBefore...");
        }

        @Override
        public void onAfter(int id) {
            L.e(TAG, "onAfter...");
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            e.printStackTrace();
        }

        @Override
        public void onResponse(String response, int id) {
            L.e(TAG,"非攻新用户实例："+response);
            //
            switch (id) {
                case 100:
                    ReturnMSG_UserInfo returnMSG_userInfo =  new Gson().fromJson(response,ReturnMSG_UserInfo.class);
                    if(returnMSG_userInfo!=null && returnMSG_userInfo.getCode()==Constant.FGCode.OpOk_code){
                        ReturnMSG_UserInfo.DataBean dataBean= returnMSG_userInfo.getData();
                        if(dataBean!=null){
                            ReturnMSG_UserInfo.DataBean.LoginInfoBean loginInfoBean = dataBean.getLoginInfo();
                            SPUtils.put(App.getContext(),Constant.TOKEN,dataBean.getToken());
                            SPUtils.put(App.getContext(),Constant.USERINFO.NICKNAME,loginInfoBean.getNickname());
                            SPUtils.put(App.getContext(),Constant.USERINFO.AVATOR,loginInfoBean.getAvator());
                        }
                    }
                    break;

                case 101:


                    break;
            }
        }

        @Override
        public void inProgress(float progress, long total, int id) {
            L.e(TAG, "inProgress:" + progress);
        }
    }

    //





}
