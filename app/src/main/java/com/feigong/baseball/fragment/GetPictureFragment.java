package com.feigong.baseball.fragment;/**
 * Created by ruler on 17/2/3.
 */

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.feigong.baseball.R;
import com.feigong.baseball.application.App;
import com.feigong.baseball.base.fragment.BaseFragment;
import com.feigong.baseball.base.util.ScreenUtils;
import com.feigong.baseball.fgview.ViewTopBar;
import com.feigong.baseball.myinfo.LoginFragment;

/**
 * 项目名称：baseball
 * 类名称： GetPictureFragment
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：2017.02.03 15:48
 * 备注：
 *
 * @version 1.0
 */
public class GetPictureFragment extends BaseFragment {

    private static final String TAG = "GetPictureFragment";

    public static GetPictureFragment newInstance() {
        GetPictureFragment getPictureFragment = new GetPictureFragment();
        return getPictureFragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_get_picture;
    }

    @Override
    protected void initVariables() {
        context = getActivity();
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
        ImageView iv_avator = (ImageView) view.findViewById(R.id.iv_avator);
        ViewGroup.LayoutParams layoutParams = iv_avator.getLayoutParams();
        layoutParams.height = ScreenUtils.getScreenWidth(App.getContext());
        iv_avator.setLayoutParams(layoutParams);
        //
    }

    @Override
    protected void loadData() {

    }


}
