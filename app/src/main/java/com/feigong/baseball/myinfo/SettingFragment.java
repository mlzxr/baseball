package com.feigong.baseball.myinfo;/**
 * Created by ruler on 16/12/5.
 */

import android.os.Bundle;
import android.view.View;

import com.feigong.baseball.R;
import com.feigong.baseball.activity.HomeActivity;
import com.feigong.baseball.base.fragment.BaseFragment;
import com.feigong.baseball.common.Constant;
import com.feigong.baseball.fgview.ViewTopBar;
import com.feigong.baseball.fgview.View_ITI_Horizontal;
import com.feigong.baseball.fgview.View_TTI_Horizontal;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目名称：baseball
 * 类名称： SettingFragment
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：2016.12.05 15:07
 * 备注：
 *
 * @version 1.0
 */
public class SettingFragment extends BaseFragment {

    private static final String TAG="SettingFragment";


    public static SettingFragment newInstance(){
        SettingFragment settingFragment = new SettingFragment();
        return settingFragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {

        ViewTopBar viewTopBar =(ViewTopBar)view.findViewById(R.id.viewTopBar);
        viewTopBar.getTv_title().setText(getString(R.string.my_setting));
        viewTopBar.getIv_back().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        //
        View_TTI_Horizontal view_tti_account =(View_TTI_Horizontal)view.findViewById(R.id.view_tti_account);
        View_TTI_Horizontal view_tti_push =(View_TTI_Horizontal)view.findViewById(R.id.view_tti_push);
        View_TTI_Horizontal view_tti_cache =(View_TTI_Horizontal)view.findViewById(R.id.view_tti_cache);
        //
        View_TTI_Horizontal view_tti_share =(View_TTI_Horizontal)view.findViewById(R.id.view_tti_share);
        View_TTI_Horizontal view_tti_about =(View_TTI_Horizontal)view.findViewById(R.id.view_tti_about);

        //
        view_tti_account.getLeft_textView().setText("  "+getString(R.string.security_account));
        view_tti_push.getLeft_textView().setText("  "+getString(R.string.push_notification));
        view_tti_cache.getLeft_textView().setText("  "+getString(R.string.clear_cache));
        view_tti_share.getLeft_textView().setText("  "+getString(R.string.share_friend));
        view_tti_about.getLeft_textView().setText("  "+getString(R.string.about_us));
        //
        view_tti_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> map = new HashMap<String, Object>();
                //
                map.put(Constant.FLAG,Constant.FragmentTAG.security_account_fragment);
                map.put(Constant.TAG,Constant.FragmentTAG.security_account_fragmentTAG);
                //
                HomeActivity homeActivity = (HomeActivity)getActivity();
                homeActivity.setLayout(map);
            }
        });


    }

    @Override
    protected void loadData() {

    }
}
