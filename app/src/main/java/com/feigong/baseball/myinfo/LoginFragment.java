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
import com.feigong.baseball.base.fragment.BaseFragment;
import com.feigong.baseball.base.util.L;
import com.feigong.baseball.base.util.T;
import com.feigong.baseball.common.Constant;
import com.feigong.baseball.fgview.AutoZoomInImageView;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendAuth;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

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
    private TextView tv_title,tv_look;
    private LinearLayout ll_login;

    private IWXAPI iwxapi;

    private AuthInfo mAuthInfo;
    private Oauth2AccessToken mAccessToken;
    private SsoHandler mSsoHandler;


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
    private void loginWX(){//微信登陆
        L.e(TAG,"loginWX");
        iwxapi = WXAPIFactory.createWXAPI(getActivity(), Constant.WX.APP_ID,true);
        if (!iwxapi.isWXAppInstalled()) {
            T.showLong(App.getContext(),R.string.check_wx);
            return;
        }
        iwxapi.registerApp(Constant.WX.APP_ID);
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "feigong_wx_login";
        iwxapi.sendReq(req);


    }
    private void loginWB(){//微博登陆
        L.e(TAG,"loginWB");
        mAuthInfo = new AuthInfo(getActivity(), Constant.WB.APP_KEY, Constant.WB.REDIRECT_URL, Constant.WB.SCOPE);
        mSsoHandler = new SsoHandler(getActivity(), mAuthInfo);
        mSsoHandler.authorizeClientSso(new AuthListener());

    }

    private void loginQQ(){//QQ登陆
        L.e(TAG,"loginQQ");

    }



    /**
     * 微博认证授权回调类。
     * 1. SSO 授权时，需要在 {@link #onActivityResult} 中调用 {@link SsoHandler#authorizeCallBack} 后，
     *    该回调才会被执行。
     * 2. 非 SSO 授权时，当授权结束后，该回调就会被执行。
     * 当授权成功后，请保存该 access_token、expires_in、uid 等信息到 SharedPreferences 中。
     */
    class AuthListener implements WeiboAuthListener {

        @Override
        public void onComplete(Bundle values) {
            // 从 Bundle 中解析 Token
            mAccessToken = Oauth2AccessToken.parseAccessToken(values);
            //从这里获取用户输入的 电话号码信息
            String  phoneNum =  mAccessToken.getPhoneNum();
            if (mAccessToken.isSessionValid()) {
                // 显示 Token
                //updateTokenView(false);
                T.showLong(App.getContext(),R.string.auth_success);
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
                T.showLong(App.getContext(),message);
            }
        }

        @Override
        public void onCancel() {
            T.showShort(App.getContext(),R.string.auth_canceled);
        }

        @Override
        public void onWeiboException(WeiboException e) {
            T.showShort(App.getContext(),e.getMessage());
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //微博回调
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }

}
