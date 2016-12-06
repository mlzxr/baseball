package com.feigong.baseball.myinfo;/**
 * Created by ruler on 16/12/6.
 */

import android.os.Bundle;
import android.view.View;

import com.feigong.baseball.R;
import com.feigong.baseball.base.fragment.BaseFragment;
import com.feigong.baseball.fgview.ViewTopBar;

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
    }

    @Override
    protected void loadData() {

    }


}
