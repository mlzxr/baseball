package com.feigong.baseball.myinfo;/**
 * Created by ruler on 16/12/7.
 */

import android.os.Bundle;
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
import com.feigong.baseball.fgview.AutoZoomInImageView;

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
    private TextView tv_title;
    private LinearLayout ll_login;


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
                        .setScaleDelta(0.3f)//放大的系数是原来的（1 + 0.3）倍
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

}
