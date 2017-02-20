package com.feigong.baseball.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.feigong.baseball.MainActivity;
import com.feigong.baseball.R;
import com.feigong.baseball.application.App;
import com.feigong.baseball.base.common.JSONUtil;
import com.feigong.baseball.base.util.L;
import com.feigong.baseball.base.util.SPUtils;
import com.feigong.baseball.base.util.T;
import com.feigong.baseball.beans.ReturnMSG_UserInfo;
import com.feigong.baseball.common.Constant;
import com.feigong.baseball.common.GetUrl;
import com.feigong.baseball.myinfo.LoginFragment;
import com.feigong.baseball.myinfo.SocialFragment;
import com.google.gson.Gson;
import com.tencent.mm.sdk.openapi.BaseReq;
import com.tencent.mm.sdk.openapi.BaseResp;
import com.tencent.mm.sdk.openapi.ConstantsAPI;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.SendAuth;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 项目名称：feigong
 * 类名称：
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：
 * 备注：
 *
 * @version 1.0
 */


public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "WXEntryActivity";

    private IWXAPI iwxapi = null;

    public class MyStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request, int id) {

            L.e(TAG, "loading...");
        }

        @Override
        public void onAfter(int id) {
            L.e(TAG, "Sample-okHttp");
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            e.printStackTrace();
        }

        @Override
        public void onResponse(String response, int id) {
            L.e(TAG,id+":"+response);
            //
            switch (id) {
                case 100://通过code获取access_token
                    ResultTokenWX resultTokenWX = new Gson().fromJson(response, ResultTokenWX.class);
                    if(resultTokenWX!=null){
                        getWXUserInfo(resultTokenWX);
                    }else {
                        T.showLong(App.getContext(), R.string.auth_failed);
                    }
                    break;

                case 101://通过access_token调用接口,返回用户数据
                    UserInfoWX userInfoWX = new Gson().fromJson(response, UserInfoWX.class);
                    if(userInfoWX!=null){
                        String openid = userInfoWX.getOpenid();
                        String nickname = userInfoWX.getNickname();
                        String avator = userInfoWX.getHeadimgurl();
                        String token = String.valueOf(SPUtils.get(App.getContext(),Constant.TOKEN,""));
                        if(TextUtils.isEmpty(token)){//新用户使用第三方登陆
                            //获取新用户实例
                            getFGUserInfo(openid,nickname,avator,Constant.Other.WX);
                        }else {//绑定社会化第三方登陆
                            WXEntryActivity.this.finish();
                            socialBind(openid,Constant.Other.WX);
                        }
                    }else {
                        T.showLong(App.getContext(), R.string.get_user_info_error);
                    }

                    break;

                case 102:
                    //
                    ReturnMSG_UserInfo returnMSG_userInfo =  new Gson().fromJson(response,ReturnMSG_UserInfo.class);
                    if(returnMSG_userInfo!=null && returnMSG_userInfo.getCode()==Constant.FGCode.OpOk_code){
                        ReturnMSG_UserInfo.DataBean dataBean= returnMSG_userInfo.getData();
                        if(dataBean!=null){

                            SPUtils.put(App.getContext(),Constant.TOKEN,dataBean.getToken());
                            SPUtils.put(App.getContext(),Constant.USERINFO.NICKNAME,dataBean.getLoginInfo().getNickname());
                            SPUtils.put(App.getContext(),Constant.USERINFO.AVATOR,dataBean.getLoginInfo().getAvator());

                            WXEntryActivity.this.finish();
                            /*
                            打开主界面
                             */
                            Intent intent =new Intent(WXEntryActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }

                    break;

                case 103:


                     break;

            }
        }

        @Override
        public void inProgress(float progress, long total, int id) {
            L.e(TAG, "inProgress:" + progress);
        }
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

    private void getFGUserInfo(String openid, String nickname, String avator, String type) {

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
                .id(102)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(json)
                .build()
                .execute(new MyStringCallback());
    }

    private void getWXUserInfo(ResultTokenWX resultTokenWX){
        String url = GetUrl.getWXUserInfo(resultTokenWX.getAccess_token(),resultTokenWX.getOpenid());
        OkHttpUtils.get().url(url).id(101).build().execute(new MyStringCallback());

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        iwxapi = WXAPIFactory.createWXAPI(App.getContext(), Constant.WX.APP_ID, false);
        iwxapi.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setIntent(intent);
        iwxapi.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        switch (baseReq.getType()) {
            case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:

                break;
            case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:

                break;
            default:
                break;
        }
    }

    @Override
    public void onResp(BaseResp baseResp) {
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:

                SendAuth.Resp resp = (SendAuth.Resp) baseResp;

                String url = GetUrl.getAccessToken(resp.token);
                OkHttpUtils.get().url(url).id(100).build().execute(new MyStringCallback());

                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                T.showLong(App.getContext(), R.string.auth_canceled);

                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                T.showLong(App.getContext(), "ERR_AUTH_DENIED");

                break;
            default:

                break;
        }
    }


}
