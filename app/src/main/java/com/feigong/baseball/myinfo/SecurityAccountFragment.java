package com.feigong.baseball.myinfo;/**
 * Created by ruler on 16/12/6.
 */

import android.os.Bundle;
import android.view.View;

import com.feigong.baseball.R;
import com.feigong.baseball.base.fragment.BaseFragment;
import com.feigong.baseball.fgview.ViewTopBar;
import com.feigong.baseball.fgview.View_TTI_Horizontal;

/**
 * 项目名称：baseball
 * 类名称： SecurityAccountFragment
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：2016.12.06 21:48
 * 备注：
 *
 * @version 1.0
 */
public class SecurityAccountFragment extends BaseFragment {

    private static final String TAG="SecurityAccountFragment";

    public static SecurityAccountFragment newInstance(){
        SecurityAccountFragment securityAccountFragment = new SecurityAccountFragment();
        return securityAccountFragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_security_account;
    }

    @Override
    protected void initVariables() {


    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        ViewTopBar viewTopBar = (ViewTopBar)view.findViewById(R.id.viewTopBar);
        viewTopBar.getTv_title().setText(getString(R.string.security_account));
        viewTopBar.getIv_back().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        //
        View_TTI_Horizontal view_tti_avatar = (View_TTI_Horizontal)view.findViewById(R.id.view_tti_avatar);
        View_TTI_Horizontal view_tti_nickname = (View_TTI_Horizontal)view.findViewById(R.id.view_tti_nickname);
        View_TTI_Horizontal view_tti_sex = (View_TTI_Horizontal)view.findViewById(R.id.view_tti_sex);
        View_TTI_Horizontal view_tti_phone_binding = (View_TTI_Horizontal)view.findViewById(R.id.view_tti_phone_binding);
        View_TTI_Horizontal view_tti_account_binding = (View_TTI_Horizontal)view.findViewById(R.id.view_tti_account_binding);
        View_TTI_Horizontal view_tti_register_time = (View_TTI_Horizontal)view.findViewById(R.id.view_tti_register_time);
        //
        view_tti_avatar.getLeft_textView().setText(getString(R.string.avatar));
        view_tti_nickname.getLeft_textView().setText(getString(R.string.nickname));
        view_tti_sex.getLeft_textView().setText(getString(R.string.sex));
        view_tti_phone_binding.getLeft_textView().setText(getString(R.string.phone_binding));
        view_tti_account_binding.getLeft_textView().setText(getString(R.string.account_binding));
        view_tti_register_time.getLeft_textView().setText(getString(R.string.register_time));
        //
    }

    @Override
    protected void loadData() {



    }


}
