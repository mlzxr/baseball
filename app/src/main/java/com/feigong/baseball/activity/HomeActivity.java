package com.feigong.baseball.activity;/**
 * Created by ruler on 16/9/6.
 */

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import com.feigong.baseball.R;
import com.feigong.baseball.application.App;
import com.feigong.baseball.base.activity.BaseActivity;
import com.feigong.baseball.base.common.MapUtil;
import com.feigong.baseball.base.util.L;
import com.feigong.baseball.base.util.SPUtils;
import com.feigong.baseball.common.Constant;
import com.feigong.baseball.information.InformationFragment;
import com.feigong.baseball.myinfo.LoginFragment;
import com.feigong.baseball.myinfo.MeFragment;
import com.feigong.baseball.myinfo.SecurityAccountFragment;
import com.feigong.baseball.myinfo.SettingFragment;
import com.feigong.baseball.video.VideoFragment;

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

    private static final String TAG="HomeActivity";

    private ViewPager home_viewPager;
    private LinearLayout ll_information, ll_video, ll_me;

    private FragmentPagerAdapter fragmentPagerAdapter;
    private List<Fragment> fragmentArrayList = new ArrayList<>();

    private int index = 1;

    @Override

    protected int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initVariables() {
        activity = HomeActivity.this;
        fragmentArrayList.add(InformationFragment.newInstance());
        fragmentArrayList.add(VideoFragment.newInstance());
        fragmentArrayList.add(MeFragment.newInstance());
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

        home_viewPager = (ViewPager) findViewById(R.id.home_viewPager);
        //
        ll_information = (LinearLayout) findViewById(R.id.ll_information);
        ll_video = (LinearLayout) findViewById(R.id.ll_video);
        ll_me = (LinearLayout) findViewById(R.id.ll_me);

        //
        ll_information.setOnClickListener(new CheckTag(0));
        ll_video.setOnClickListener(new CheckTag(1));
        ll_me.setOnClickListener(new CheckTag(2));

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
        home_viewPager.setAdapter(fragmentPagerAdapter);
        home_viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
        home_viewPager.setCurrentItem(index);
        setTag(index);

    }

    @Override
    protected void initData() {
        String token = String.valueOf(SPUtils.get(App.getContext(),Constant.TOKEN,""));
        L.e(TAG,token);
        if(TextUtils.isEmpty(token)){
            Map<String,Object> map = new HashMap<String, Object>();
            //
            map.put(Constant.FLAG,Constant.FragmentTAG.login_fragment);
            map.put(Constant.TAG,Constant.FragmentTAG.login_fragmentTAG);
            //
            this.setLayout(map);
        }

    }

    public void setLayout(Map<String,Object> map){
        Fragment fragment = null;
        int flag = MapUtil.getInt(map, Constant.FLAG);
        String tag = MapUtil.getValue(map, Constant.TAG);
        switch (flag){
            case Constant.FragmentTAG.setting_fragment:
                fragment = getSupportFragmentManager().findFragmentByTag(tag);
                if(fragment==null){
                    fragment = SettingFragment.newInstance();
                }
                break;
            case Constant.FragmentTAG.security_account_fragment:
                fragment = getSupportFragmentManager().findFragmentByTag(tag);
                if(fragment==null){
                    fragment = SecurityAccountFragment.newInstance();
                }
                break;
            case Constant.FragmentTAG.login_fragment:
                fragment = getSupportFragmentManager().findFragmentByTag(tag);
                if(fragment==null){
                    fragment = LoginFragment.newInstance();
                }
                break;
        }
        //
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.frameLayout, fragment,tag);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    class CheckTag implements View.OnClickListener {
        private int index;

        public CheckTag(int index) {
            this.index = index;
        }


        @Override
        public void onClick(View v) {
            home_viewPager.setCurrentItem(index);
            setTag(index);
        }
    }

    private void setTag(int index) {
        resetTab();

        switch (index) {
            case 0:
                ((ImageView) ll_information.getChildAt(0)).setImageResource(R.mipmap.tab_information_s);
                ((TextView) ll_information.getChildAt(1)).setTextColor(Color.parseColor("#E13437"));
                break;

            case 1:

                ((ImageView) ll_video.getChildAt(0)).setImageResource(R.mipmap.tab_video_s);
                ((TextView) ll_video.getChildAt(1)).setTextColor(Color.parseColor("#E13437"));

                break;

            case 2:

                ((ImageView) ll_me.getChildAt(0)).setImageResource(R.mipmap.tab_my_s);
                ((TextView) ll_me.getChildAt(1)).setTextColor(Color.parseColor("#E13437"));

                break;
        }
    }

    //重置底部状态栏
    private void resetTab() {
        ((ImageView) ll_information.getChildAt(0)).setImageResource(R.mipmap.tab_information_n);
        ((ImageView) ll_video.getChildAt(0)).setImageResource(R.mipmap.tab_video_n);
        ((ImageView) ll_me.getChildAt(0)).setImageResource(R.mipmap.tab_my_n);
        //
        ((TextView) ll_information.getChildAt(1)).setTextColor(Color.parseColor("#989898"));
        ((TextView) ll_video.getChildAt(1)).setTextColor(Color.parseColor("#989898"));
        ((TextView) ll_me.getChildAt(1)).setTextColor(Color.parseColor("#989898"));
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        Fragment f = fragmentArrayList.get(home_viewPager.getCurrentItem());
//        f.onActivityResult(requestCode, resultCode, data);

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(Constant.FragmentTAG.login_fragmentTAG);
        if(fragment!=null){
            fragment.onActivityResult(requestCode,resultCode,data);
        }
    }
}
