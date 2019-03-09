package com.feigong.baseball.activity;/**
 * Created by ruler on 16/9/6.
 */

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.feigong.baseball.R;
import com.feigong.baseball.application.App;
import com.feigong.baseball.base.BaseActivity;
import com.feigong.baseball.beans.EventData;
import com.feigong.baseball.common.Constant;
import com.feigong.baseball.common.EventCode;
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
import com.ml.core.common.MapUtil;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.GSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

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

    private static final String TAG ="HomeActivity";


    @Bind(R.id.ll_information)
    LinearLayout llInformation;

    @Bind(R.id.ll_video)
    LinearLayout llVideo;

    @Bind(R.id.ll_me)
    LinearLayout llMe;

    @Bind(R.id.ll_bottom)
    LinearLayout llBottom;

    @Bind(R.id.body_layout)
    FrameLayout bodyLayout;

    @Bind(R.id.frame_layout)
    FrameLayout frameLayout;

    //长期驻存的碎片
    private List<Fragment> fragmentArrayList = new ArrayList<>();

    @Override

    protected int getLayout() {
        return R.layout.activity_home;
    }

    //当前tab索引位置
    private int indexTab = 0;

    @Override
    protected void initVariables() {
        //
        EventBus.getDefault().register(this);
        //
        fragmentArrayList.add(InformationFragment.newInstance());
        fragmentArrayList.add(VideoFragment.newInstance());
        fragmentArrayList.add(MeFragment.newInstance());
        //添加常驻碎片
        for (Fragment fragment : fragmentArrayList) {
            bodyFragment(fragment);
        }
    }

    /**
     * 添加主体碎片
     * @param fragment
     */
    private void bodyFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.body_layout, fragment, fragment.getClass().getSimpleName());
        fragmentTransaction.commitNow();
    }


    /**
     * 清除所有碎片
     */
    private void clearFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //
        List<Fragment> lists = getSupportFragmentManager().getFragments();
        if(lists!=null){
            for(int k=0;k<lists.size();k++){
                Fragment fragment =  lists.get(k);
                if (fragment != null && fragment.getTag() != null) {
                    String fragmentTag = fragment.getTag();
                    //
                    boolean flag = false;
                    for (Fragment residentFragment : fragmentArrayList) {
                        String residentTag = residentFragment.getTag();
                        if (residentTag.equalsIgnoreCase(fragmentTag)) {
                            flag = true;
                            break;
                        }
                    }
                    //
                    if (flag) {
                        fragmentTransaction.hide(fragment);
                    } else {
                        fragmentTransaction.remove(fragment);
                    }
                }
            }
        }
        if (indexTab < fragmentArrayList.size()) {
            Fragment fragment = fragmentArrayList.get(indexTab);
            fragmentTransaction.show(fragment);
        }
        //
        fragmentTransaction.commitNow();
        //隐藏长期贮存的碎片

    }


    @Override
    protected void initViews(Bundle savedInstanceState) {
        //设置界面初使位置
        setTag(0);
    }

    @Override
    protected void initData() {


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
            fragmentTransaction.add(R.id.frame_layout, fragment, tag);
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
                if(checkLogin()){
                    setTag(2);
                }else {
                    toLogin();
                }
                break;
        }
    }




    /**
     * 设置底部导航tab
     *
     * @param index
     */
    private void setTag(int index) {
        resetTab();
        indexTab = index;
        clearFragment();
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
    }

    private void toLogin() {
        Map<String, Object> map = new HashMap<String, Object>();
        //
        map.put(Constant.FLAG, Constant.FragmentTAG.login_fragment);
        map.put(Constant.TAG, Constant.FragmentTAG.login_fragmentTAG);
        //
        this.setLayout(map);
    }


    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onEventData(EventData eventData) {
        switch (eventData.getCode()) {
            case EventCode.USERINFO:
                loadAvatar(2);
                break;
        }

    }

    public void loadAvatar(int index) {
        Fragment fragment = fragmentArrayList.get(index);
        if (fragment instanceof MeFragment) {
            MeFragment meFragment = (MeFragment) fragment;
            meFragment.loadAvator();
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
        //
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void onBackPressed() {
        if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }



}
