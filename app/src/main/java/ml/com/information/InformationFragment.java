package ml.com.information;/**
 * Created by ruler on 16/9/7.
 */

import android.os.Bundle;
import android.view.View;

import ml.com.baseball.R;
import ml.com.basefragment.BaseFragment;

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

    @Override
    protected int getLayout() {
        return R.layout.fragment_information;
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
