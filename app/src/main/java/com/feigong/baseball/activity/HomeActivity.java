package com.feigong.baseball.activity;/**
 * Created by ruler on 16/9/6.
 */

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.feigong.baseball.MainActivity;
import com.feigong.baseball.R;
import com.feigong.baseball.application.App;
import com.feigong.baseball.base.BaseActivity;
import com.feigong.baseball.base.common.MapUtil;
import com.feigong.baseball.base.util.L;
import com.feigong.baseball.base.util.SPUtils;
import com.feigong.baseball.base.util.T;
import com.feigong.baseball.beans.ReturnMSG_Channel;
import com.feigong.baseball.beans.ReturnMSG_UserInfo;
import com.feigong.baseball.common.CodeConstant;
import com.feigong.baseball.common.Constant;
import com.feigong.baseball.common.DBConstant;
import com.feigong.baseball.common.GetUrl;
import com.feigong.baseball.dao.TabTitleNameService;
import com.feigong.baseball.dto.TabTitleName;
import com.feigong.baseball.fragment.GetPictureFragment;
import com.feigong.baseball.fragment.ShowWebVIewImagesFragment;
import com.feigong.baseball.information.InformationDetailFragment;
import com.feigong.baseball.information.InformationFragment;
import com.feigong.baseball.myinfo.LoginFragment;
import com.feigong.baseball.myinfo.MeFragment;
import com.feigong.baseball.myinfo.SecurityAccountFragment;
import com.feigong.baseball.myinfo.SettingFragment;
import com.feigong.baseball.myinfo.SocialFragment;
import com.feigong.baseball.video.VideoFragment;
import com.google.gson.Gson;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.GSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;

/**
 * 项目名称：baseball
 * 类名称： HomeActivity
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：2016.09.06 09:43
 * 备注：
 *
 * @version 1.0
 */
public class HomeActivity extends BaseActivity {

    @Bind(R.id.home_viewPager)
    ViewPager homeViewPager;

    @Bind(R.id.ll_information)
    LinearLayout llInformation;

    @Bind(R.id.ll_video)
    LinearLayout llVideo;

    @Bind(R.id.ll_me)
    LinearLayout llMe;

    @Bind(R.id.ll_bottom)
    LinearLayout llBottom;

    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;

    private FragmentPagerAdapter fragmentPagerAdapter;
    private List<Fragment> fragmentArrayList = new ArrayList<>();

    private int index = 1;

    @Override

    protected int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initVariables() {


        fragmentArrayList.add(InformationFragment.newInstance());
        fragmentArrayList.add(VideoFragment.newInstance());
        fragmentArrayList.add(MeFragment.newInstance());
    }


    @Override
    protected void initViews(Bundle savedInstanceState) {


        //
        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentArrayList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentArrayList.size();
            }
        };
        //
        homeViewPager.setAdapter(fragmentPagerAdapter);
        homeViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                setTag(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //
        setTag(index);
    }

    @Override
    protected void initData() {

        if (!checkLogin()) {
            toLogin();
        } else {
            String url = GetUrl.getUserInfoByToken();
            OkHttpUtils
                    .get()
                    .url(url)
                    .addHeader(Constant.TOKEN, token)
                    .id(100)
                    .build()
                    .execute(new MyStringCallback());
        }

    }

    public void setLayout(Map<String, Object> map) {

        Fragment fragment = null;
        int flag = MapUtil.getInt(map, Constant.FLAG);
        String tag = MapUtil.getValue(map, Constant.TAG);
        switch (flag) {
            case Constant.FragmentTAG.setting_fragment:
                fragment = getSupportFragmentManager().findFragmentByTag(tag);
                if (fragment == null) {
                    fragment = SettingFragment.newInstance();
                }
                break;
            case Constant.FragmentTAG.security_account_fragment:
                fragment = getSupportFragmentManager().findFragmentByTag(tag);
                if (fragment == null) {
                    fragment = SecurityAccountFragment.newInstance();
                }
                break;
            case Constant.FragmentTAG.login_fragment:
                fragment = getSupportFragmentManager().findFragmentByTag(tag);
                if (fragment == null) {
                    fragment = LoginFragment.newInstance();
                }
                break;
            case Constant.FragmentTAG.get_picture_fragment:
                fragment = getSupportFragmentManager().findFragmentByTag(tag);
                if (fragment == null) {
                    int take_type = (int) map.get(Constant.TAKE_PHONE_TYPE.TAKE_TYPE);
                    fragment = GetPictureFragment.newInstance(take_type);
                }
                break;

            case Constant.FragmentTAG.social_fragment:
                fragment = getSupportFragmentManager().findFragmentByTag(tag);
                if (fragment == null) {
                    fragment = SocialFragment.newInstance();
                }
                break;
            case Constant.FragmentTAG.informationDetail_fragment:

                fragment = getSupportFragmentManager().findFragmentByTag(tag);
                if (fragment == null) {
                    fragment = InformationDetailFragment.newInstance((String) map.get("objid_ref"));
                }
                break;
            case Constant.FragmentTAG.showWebVIewImages_fragment:

                fragment = getSupportFragmentManager().findFragmentByTag(tag);
                if (fragment == null) {
                    fragment = ShowWebVIewImagesFragment.newInstance((String) map.get(DATA));
                }

                break;

        }
        //
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.frameLayout, fragment, tag);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commitAllowingStateLoss();
        }
    }


    @OnClick({R.id.ll_information, R.id.ll_video, R.id.ll_me})
    public void CheckIndex(View view) {
        switch (view.getId()) {
            case R.id.ll_information:
                setTag(0);
                break;

            case R.id.ll_video:
                setTag(1);
                break;

            case R.id.ll_me:
                setTag(2);
                break;
        }
    }


    /**
     * 设置底部导航tab
     * @param index
     */
    private void setTag(int index) {
        homeViewPager.setCurrentItem(index);
        resetTab();
        switch (index) {
            case 0:
                ((ImageView) llInformation.getChildAt(0)).setImageResource(R.mipmap.tab_information_s);
                ((TextView) llInformation.getChildAt(1)).setTextColor(Color.parseColor("#E13437"));
                break;

            case 1:

                ((ImageView) llVideo.getChildAt(0)).setImageResource(R.mipmap.tab_video_s);
                ((TextView) llVideo.getChildAt(1)).setTextColor(Color.parseColor("#E13437"));

                break;

            case 2:

                ((ImageView) llMe.getChildAt(0)).setImageResource(R.mipmap.tab_my_s);
                ((TextView) llMe.getChildAt(1)).setTextColor(Color.parseColor("#E13437"));

                break;
        }
    }

    //重置底部状态栏
    private void resetTab() {
        ((ImageView) llInformation.getChildAt(0)).setImageResource(R.mipmap.tab_information_n);
        ((ImageView) llVideo.getChildAt(0)).setImageResource(R.mipmap.tab_video_n);
        ((ImageView) llMe.getChildAt(0)).setImageResource(R.mipmap.tab_my_n);
        //

        ((TextView) llInformation.getChildAt(1)).setTextColor(ContextCompat.getColor(App.getContext(), R.color.tab_select_n));
        ((TextView) llVideo.getChildAt(1)).setTextColor(ContextCompat.getColor(App.getContext(), R.color.tab_select_n));
        ((TextView) llMe.getChildAt(1)).setTextColor(ContextCompat.getColor(App.getContext(), R.color.tab_select_n));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment f = fragmentArrayList.get(homeViewPager.getCurrentItem());
        f.onActivityResult(requestCode, resultCode, data);


        Fragment fragment = getSupportFragmentManager().findFragmentByTag(Constant.FragmentTAG.login_fragmentTAG);
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
        fragment = getSupportFragmentManager().findFragmentByTag(Constant.FragmentTAG.social_fragmentTAG);
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void toLogin() {
        Map<String, Object> map = new HashMap<String, Object>();
        //
        map.put(Constant.FLAG, Constant.FragmentTAG.login_fragment);
        map.put(Constant.TAG, Constant.FragmentTAG.login_fragmentTAG);
        //
        this.setLayout(map);
    }

    public void loadAvatar(int index) {
        Fragment fragment = fragmentArrayList.get(index);
        if (fragment instanceof MeFragment) {
            MeFragment meFragment = (MeFragment) fragment;
            meFragment.loadAvator();
        }
    }

    public class MyStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request, int id) {
        }

        @Override
        public void onAfter(int id) {

        }

        @Override
        public void onError(Call call, Exception e, int id) {
            L.e(TAG, e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {
            switch (id) {
                case 100:
                    L.e(TAG, response);
                    ReturnMSG_UserInfo returnMSG_userInfo = new Gson().fromJson(response, ReturnMSG_UserInfo.class);
                    if (returnMSG_userInfo != null && returnMSG_userInfo.getCode() == Constant.FGCode.OpOk_code) {
                        ReturnMSG_UserInfo.DataBean dataBean = returnMSG_userInfo.getData();
                        if (dataBean != null) {

                            SPUtils.put(App.getContext(), Constant.USERINFO.NICKNAME, dataBean.getLoginInfo().getNickname());
                            SPUtils.put(App.getContext(), Constant.USERINFO.AVATOR, dataBean.getLoginInfo().getAvator());
                        }
                    } else {
                        toLogin();
                    }
                    break;

            }
        }

        @Override
        public void inProgress(float progress, long total, int id) {
        }
    }


    //
    @Override
    protected void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
    }


    @Override
    protected void onResume() {
        super.onResume();
        GSYVideoManager.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoPlayer.releaseAllVideos();
    }


    @Override
    public void onBackPressed() {
        if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }


}
