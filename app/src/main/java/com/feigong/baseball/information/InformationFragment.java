package com.feigong.baseball.information;/**
 * Created by ruler on 16/9/7.
 */

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.feigong.baseball.R;
import com.feigong.baseball.application.App;
import com.feigong.baseball.base.fragment.BaseFragment;
import com.feigong.baseball.base.util.L;
import com.feigong.baseball.base.util.SPUtils;
import com.feigong.baseball.base.util.T;
import com.feigong.baseball.beans.ReturnMSG;
import com.feigong.baseball.beans.ReturnMSG_Channel;
import com.feigong.baseball.beans.ReturnMSG_Push;
import com.feigong.baseball.common.Constant;
import com.feigong.baseball.common.GetUrl;
import com.feigong.baseball.common.TAGUitl;
import com.feigong.baseball.fgview.ViewTopBar;
import com.feigong.baseball.myinfo.SettingFragment;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;

/**
 * 项目名称：baseball
 * 类名称： InformationFragment
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：2016.09.07 10:51
 * 备注：资讯
 *
 * @version 1.0
 */
public class InformationFragment extends BaseFragment {

    private ViewTopBar viewTopBar;
    private ViewPager mViewPager;
    private MagicIndicator mMagicIndicator;
    private CommonNavigator mCommonNavigator;

    private List<ReturnMSG_Channel.DataBean> tablist = new ArrayList<>();

    private List<Fragment> fragmentList = new ArrayList<>();

    //
    public static InformationFragment newInstance() {
        InformationFragment newFragment = new InformationFragment();
        return newFragment;
    }


    public class MyStringCallback extends StringCallback
    {
        @Override
        public void onBefore(Request request, int id)
        {
        }

        @Override
        public void onAfter(int id)
        {

        }

        @Override
        public void onError(Call call, Exception e, int id)
        {
            L.e(TAGUitl.INFORMATIONFRAGMENT,e.getMessage());
        }

        @Override
        public void onResponse(String response, int id)
        {
            switch (id)
            {
                case 600:

                    ReturnMSG_Channel returnMSG_channel = new Gson().fromJson(response,ReturnMSG_Channel.class);
                    if(returnMSG_channel!=null &&returnMSG_channel.getCode()==Constant.FGCode.OpOk_code){
                        tablist = returnMSG_channel.getData();
                        if(tablist!=null && tablist.size()>0){
                            asyncloadingfragment();
                            asyncLoadingTab();
                        }
                    }
                    break;
            }
        }

        @Override
        public void inProgress(float progress, long total, int id)
        {
        }
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_information;
    }

    @Override
    protected void initVariables() {
        context = getActivity();
        String url = GetUrl.infoChannel();
        OkHttpUtils
                .get()
                .url(url)
                .id(600)
                .build()
                .execute(new MyStringCallback());
        //

    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        viewTopBar =(ViewTopBar)view.findViewById(R.id.viewTopBar);
        viewTopBar.getIv_back().setVisibility(View.INVISIBLE);
        viewTopBar.getTv_title().setText(R.string.app_name);
        //
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        mMagicIndicator = (MagicIndicator) view.findViewById(R.id.magic_indicator);
        mMagicIndicator.setBackgroundColor(Color.WHITE);
    }

    @Override
    protected void loadData() {

    }

    /**
     * 获取频道
     */
    private void asyncLoadingTab(){
        mCommonNavigator = new CommonNavigator(context);
        mCommonNavigator.setSkimOver(true);
        mCommonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return tablist.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);
                clipPagerTitleView.setText(tablist.get(index).toString());
                clipPagerTitleView.setTextColor(Color.BLACK);
                clipPagerTitleView.setClipColor(Color.RED);
                clipPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return clipPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return null;
            }
        });
        mMagicIndicator.setNavigator(mCommonNavigator);
        ViewPagerHelper.bind(mMagicIndicator, mViewPager);

    }
    private  void asyncloadingfragment(){
        fragmentList.clear();
        ReturnMSG_Channel.DataBean newwData = new ReturnMSG_Channel.DataBean();
        newwData.setD_name("推荐");
        newwData.setD_code("C00");
        tablist.add(0,newwData);
        //

        fragmentList.add(InformationRecommendFragment.newInstance());
        //
        for (int k =1;k<tablist.size();k++){
            ReturnMSG_Channel.DataBean data = tablist.get(k);
            fragmentList.add(InformationTypeFragment.newInstance(data.getD_code()));
        }
        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

        });
    }


}
