package com.feigong.baseball.information;/**
 * Created by ruler on 17/3/6.
 */

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.feigong.baseball.R;
import com.feigong.baseball.base.fragment.BaseFragment;

/**
 * 项目名称：baseball
 * 类名称： InformmationTypeFragment
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：2017.03.06 16:34
 * 备注：
 *
 * @version 1.0
 */
public class InformationTypeFragment extends BaseFragment {

    private static final String TAG="InformationTypeFragment";

    private String code;


    public static InformationTypeFragment newInstance(String code) {
        InformationTypeFragment newFragment = new InformationTypeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("code",code);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_information_type;
    }

    @Override
    protected void initVariables() {
        code = getArguments().getString("code");


    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(code);

    }

    @Override
    protected void loadData() {

    }
}
