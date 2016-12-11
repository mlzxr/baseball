package com.feigong.baseball.myinfo;/**
 * Created by ruler on 16/9/7.
 */

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.feigong.baseball.R;
import com.feigong.baseball.activity.HomeActivity;
import com.feigong.baseball.base.fragment.BaseFragment;
import com.feigong.baseball.common.Constant;
import com.feigong.baseball.common.ImageUtil;
import com.feigong.baseball.fgview.View_ITI_Horizontal;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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


    private ImageView iv_avatar;//头像
    private ImageView iv_setting;//设置


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

        iv_avatar =(ImageView)view.findViewById(R.id.iv_avatar);
        iv_setting =(ImageView)view.findViewById(R.id.iv_setting);
        //
        View_ITI_Horizontal myMessage_iti = (View_ITI_Horizontal)view.findViewById(R.id.my_message_iti);
        View_ITI_Horizontal myCollect_iti = (View_ITI_Horizontal)view.findViewById(R.id.my_collect_iti);
        //
        View_ITI_Horizontal myAccount_iti = (View_ITI_Horizontal)view.findViewById(R.id.my_account_iti);
        View_ITI_Horizontal mySetting_iti = (View_ITI_Horizontal)view.findViewById(R.id.my_setting_iti);

        //
        myMessage_iti.getLeftImageView().setImageResource(R.mipmap.ic_launcher);
        myCollect_iti.getLeftImageView().setImageResource(R.mipmap.ic_launcher);
        myAccount_iti.getLeftImageView().setImageResource(R.mipmap.ic_launcher);
        mySetting_iti.getLeftImageView().setImageResource(R.mipmap.ic_launcher);
        //
        myMessage_iti.getCentreTextView().setText(getString(R.string.my_message));
        myCollect_iti.getCentreTextView().setText(getString(R.string.my_collect));
        myAccount_iti.getCentreTextView().setText(getString(R.string.my_account));
        mySetting_iti.getCentreTextView().setText(getString(R.string.my_setting));
        //
        iv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> map = new HashMap<String, Object>();
                //
                map.put(Constant.FLAG,Constant.FragmentTAG.login_fragment);
                map.put(Constant.TAG,Constant.FragmentTAG.login_fragmentTAG);
                //
                HomeActivity homeActivity = (HomeActivity)getActivity();
                homeActivity.setLayout(map);

            }
        });
        //
        mySetting_iti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> map = new HashMap<String, Object>();
                //
                map.put(Constant.FLAG,Constant.FragmentTAG.setting_fragment);
                map.put(Constant.TAG,Constant.FragmentTAG.setting_fragmentTAG);
                //
                HomeActivity homeActivity = (HomeActivity)getActivity();
                homeActivity.setLayout(map);
            }
        });


    }

    @Override
    protected void loadData() {

        ImageLoader.getInstance().displayImage("http://g.hiphotos.baidu.com/image/pic/item/c2cec3fdfc03924578c6cfe18394a4c27c1e25e8.jpg", iv_avatar,ImageUtil.getImageOptionsCircle());




    }
}
