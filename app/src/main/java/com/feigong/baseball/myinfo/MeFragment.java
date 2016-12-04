package com.feigong.baseball.myinfo;/**
 * Created by ruler on 16/9/7.
 */

import android.os.Bundle;
import android.view.View;

import com.feigong.baseball.R;
import com.feigong.baseball.base.fragment.BaseFragment;

/**
 * 项目名称：baseball
 * 类名称： InformationFragment
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：2016.09.07 10:51
 * 备注：我的
 *
 * @version 1.0
 */
public class MeFragment extends BaseFragment {

    private static final String TAG="MeFragment";

    //
    public static MeFragment newInstance() {
        MeFragment newFragment = new MeFragment();
        return newFragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void loadData() {

    }
}
