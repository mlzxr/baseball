package com.feigong.baseball.information;/**
 * Created by ruler on 16/9/7.
 */

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.feigong.baseball.R;
import com.feigong.baseball.base.BaseFragment;
import com.feigong.baseball.beans.ReturnMSG_Channel;
import com.feigong.baseball.common.CodeConstant;
import com.feigong.baseball.common.Constant;
import com.feigong.baseball.common.DBConstant;
import com.feigong.baseball.common.GetUrl;
import com.feigong.baseball.common.MethodsUtil;
import com.feigong.baseball.dao.TabTitleNameService;
import com.feigong.baseball.dto.TabTitleName;
import com.feigong.baseball.fgview.ViewTopBar;
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

    private static final String TAG="InformationFragment";
    //
    public static InformationFragment newInstance() {
        InformationFragment newFragment = new InformationFragment();
        return newFragment;
    }

    private ViewTopBar viewTopBar;
    private ViewPager mViewPager;
    private MagicIndicator mMagicIndicator;
    private CommonNavigator mCommonNavigator;

    //获取本地数据
    List<TabTitleName>  tabTitleNames;

    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.fragment_information;
    }

    @Override
    protected void initVariables() {
        context = getActivity();
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
        String url = GetUrl.infoChannel();
        OkHttpUtils
                .get()
                .url(url)
                .id(CodeConstant.Request.info_channel)
                .build()
                .execute(new MyStringCallback());

    }


    private void setTitleTab(){

        //获取本地数据
        tabTitleNames = TabTitleNameService.query(DBConstant.INFO_CHANNEL);
        //判断本地是否有离线数据
        if(tabTitleNames!=null && tabTitleNames.size()>0){
            fragmentList.clear();
            //
            mCommonNavigator = new CommonNavigator(context);
            mCommonNavigator.setSkimOver(true);
            mCommonNavigator.setAdapter(new CommonNavigatorAdapter() {
                @Override
                public int getCount() {
                    return tabTitleNames.size();
                }

                @Override
                public IPagerTitleView getTitleView(Context context, final int index) {
                    ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);
                    clipPagerTitleView.setText(tabTitleNames.get(index).getTitleName());
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

            //
            for (int k =0;k<tabTitleNames.size();k++){
                if(k==0){
                    fragmentList.add(InformationRecommendFragment.newInstance());
                }else {
                    fragmentList.add(InformationTypeFragment.newInstance(tabTitleNames.get(k).getTitleCode()));
                }
            }
            mViewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
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


    public class MyStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request, int id) {
        }

        @Override
        public void onAfter(int id) {

        }

        @Override
        public void onError(Call call, Exception e, int id) {
            switch (id){
                case CodeConstant.Request.info_channel:
                     setTitleTab();
                    break;
            }

        }

        @Override
        public void onResponse(String response, int id) {
            switch (id) {

                case CodeConstant.Request.info_channel:

                    ReturnMSG_Channel returnMSG_channel = new Gson().fromJson(response,ReturnMSG_Channel.class);
                    if(returnMSG_channel!=null &&returnMSG_channel.getCode()== Constant.FGCode.OpOk_code){
                        List<ReturnMSG_Channel.DataBean.ChannelsBean> tablist = returnMSG_channel.getData().getChannels();
                        if(tablist!=null && tablist.size()>0){
                            int version = returnMSG_channel.getData().getVersion();
                            //获取本地数据
                            tabTitleNames = TabTitleNameService.query(DBConstant.INFO_CHANNEL);
                            if(tabTitleNames!=null && tabTitleNames.size()>0){
                                for (TabTitleName tabTitleName: tabTitleNames){
//                                    if(tabTitleName.getTitleVersion()<version){
//                                        TabTitleNameService.delete(tabTitleName);
//                                    }
                                    TabTitleNameService.delete(tabTitleName);
                                }
                            }
                            //插入刚刚获取的数据
                            TabTitleName tabTitleName = new TabTitleName();
                            //
                            tabTitleName.setId(MethodsUtil.GUID());
                            tabTitleName.setTitleVersion(version);
                            tabTitleName.setTitleType(DBConstant.INFO_CHANNEL);
                            tabTitleName.setTitleCode("C00");
                            tabTitleName.setTitleName("推荐");
                            TabTitleNameService.insert(tabTitleName);
                            //
                            for(ReturnMSG_Channel.DataBean.ChannelsBean bean :tablist){
                                tabTitleName = new TabTitleName();
                                tabTitleName.setId(MethodsUtil.GUID());
                                tabTitleName.setTitleVersion(version);
                                tabTitleName.setTitleType(DBConstant.INFO_CHANNEL);
                                tabTitleName.setTitleCode(bean.getD_code());
                                tabTitleName.setTitleName(bean.getD_name());
                                TabTitleNameService.insert(tabTitleName);
                            }
                        }
                    }
                    setTitleTab();
                    break;
            }
        }

        @Override
        public void inProgress(float progress, long total, int id) {
        }
    }


}
