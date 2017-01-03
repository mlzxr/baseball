package com.feigong.baseball.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.feigong.baseball.application.App;
import com.feigong.baseball.base.util.L;
import com.feigong.baseball.base.util.T;
import com.feigong.baseball.common.Constant;
import com.feigong.baseball.common.GetUrl;
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

import okhttp3.Call;
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

//    //第三方统一登陆
//    Handler otherHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            String token = (String) msg.obj;
//            switch (msg.what) {
//                case 1:
//                    //****
//                    SharedPreferencesManager.getInstance().putString(Constants.TOKEN, token);
//                    SharedPreferencesManager.getInstance().putBoolean(Constants.IS_LOGIN, true);
//                    SharedPreferencesManager.getInstance().commit();
//
//                    Meg.Show(R.string.login_succeed);
//                    //
//                    Bundle bundle = new Bundle();
//                    bundle.putString("login", "login");
//                    Intent intent = new Intent();
//                    intent.putExtras(bundle);
//                    intent.setClass(WXEntryActivity.this, MenuActivity.class);
//                    startActivity(intent);
//                    //
//                    WXEntryActivity.this.finish();
//                    break;
//
//                case 0:
//                    WXEntryActivity.this.finish();
//                    break;
//            }
//        }
//    };
//
//    Handler netHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//
//            switch (msg.what) {
//                case 1:
//                    ResultTokenWX resultTokenWX = (ResultTokenWX) msg.obj;
//                    getUserInfo(resultTokenWX);
//
//                    break;
//                case 0:
//                    Meg.Show("获取验证失败");
//                    WXEntryActivity.this.finish();
//                    break;
//            }
//        }
//    };
//
//    Handler userInfoHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//
//            switch (msg.what) {
//                case 1:
//                    UserInfoWX userInfoWX = (UserInfoWX) msg.obj;
//                    String sex = FunctionUtil.getSex(3, userInfoWX.getSex() + "");
//                    otherLogin(userInfoWX.getOpenid(), userInfoWX.getNickname(), userInfoWX.getHeadimgurl(), sex, 3);
//
//                    break;
//                case 0:
//                    Meg.Show("获取用户信息失败");
//                    WXEntryActivity.this.finish();
//                    break;
//            }
//        }
//    };
//
//
//    private void getUserInfo(final ResultTokenWX resultTokenWX) {
//        new Thread() {
//            @Override
//            public void run() {
//                //
//                LogUtil.I(TAG,"resultTokenWX:"+resultTokenWX.toString());
//                String val = NetworkTool.getUserInfoWithWX(resultTokenWX);
//                LogUtil.I(TAG,"val:"+val);
//                //
//                try {
//                    UserInfoWX userInfoWX = new Gson().fromJson(val, UserInfoWX.class);
//                    if (userInfoWX != null) {
//                        userInfoHandler.obtainMessage(1, userInfoWX).sendToTarget();
//                    } else {
//                        userInfoHandler.obtainMessage(0).sendToTarget();
//                    }
//                } catch (Exception e) {
//                    userInfoHandler.obtainMessage(0).sendToTarget();
//                }
//            }
//        }.start();
//    }


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
            switch (id) {
                case 100:
                    L.e(TAG, "response:" + response);
                    break;
            }
        }

        @Override
        public void inProgress(float progress, long total, int id) {
            L.e(TAG, "inProgress:" + progress);
        }
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
                T.showLong(App.getContext(), "ERR_USER_CANCEL");

                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                T.showLong(App.getContext(), "ERR_AUTH_DENIED");

                break;
            default:

                break;
        }
    }


    //注册第三方登陆信息
    private void otherLogin(final String openId, final String nickname, final String avatar, final String sex, final int type) {
        //
        new Thread() {
            @Override
            public void run() {
//                String val = NetworkTool.SocialLogin(openId, nickname, avatar, sex, type);
//                try {
//                    ReturnLoginInfo returnLoginInfo = new Gson().fromJson(val, ReturnLoginInfo.class);
//                    if (returnLoginInfo != null && returnLoginInfo.getResult() != null) {
//                        if (returnLoginInfo.getResult().getResultCode() == 200) {
//                            otherHandler.obtainMessage(1, returnLoginInfo.getToken()).sendToTarget();
//                        } else {
//                            otherHandler.obtainMessage(0).sendToTarget();
//                        }
//                    } else {
//                        otherHandler.obtainMessage(0).sendToTarget();
//                    }
//                } catch (Exception e) {
//                    otherHandler.obtainMessage(0).sendToTarget();
//                }
            }
        }.start();
    }
}
