package com.feigong.baseball.myinfo;/**
 * Created by ruler on 16/9/7.
 */

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.feigong.baseball.R;
import com.feigong.baseball.activity.home.HomeActivity;
import com.feigong.baseball.base.BaseFragment;
import com.feigong.baseball.beans.ReturnMSG_UserInfo;
import com.feigong.baseball.common.Constant;
import com.feigong.baseball.common.GetUrl;
import com.feigong.baseball.common.MethodsUtil;
import com.feigong.baseball.fgview.View_ITI_Horizontal;
import com.google.gson.Gson;
import com.ml.core.imageloader.ImageLoaderUtil;
import com.ml.core.util.L;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Request;

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

    private static final String TAG = "MeFragment";


    private ImageView iv_avator;//头像

    //
    public static MeFragment newInstance() {
        MeFragment newFragment = new MeFragment();
        return newFragment;
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
            L.e(TAG, e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {

            L.e(TAG, response);

            switch (id) {
                case 100:
                    ReturnMSG_UserInfo returnMSG_userInfo = new Gson().fromJson(response, ReturnMSG_UserInfo.class);
                    if (returnMSG_userInfo != null && returnMSG_userInfo.getCode() == Constant.FGCode.OpOk_code) {
                        ReturnMSG_UserInfo.DataBean dataBean = returnMSG_userInfo.getData();
                        if (dataBean != null) {
                            String nickname = dataBean.getLoginInfo().getNickname();
                            String avator = dataBean.getLoginInfo().getAvator();
                            if (!TextUtils.isEmpty(avator)) {
                                ImageLoaderUtil.imageLoadingCircle(avator, iv_avator);
                            }
                        }
                    }
                    break;

            }
        }

        @Override
        public void inProgress(float progress, long total, int id) {
        }
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

        iv_avator = (ImageView) view.findViewById(R.id.iv_avator);
        //
        View_ITI_Horizontal myMessage_iti = (View_ITI_Horizontal) view.findViewById(R.id.my_message_iti);
        View_ITI_Horizontal myCollect_iti = (View_ITI_Horizontal) view.findViewById(R.id.my_collect_iti);
        //
        View_ITI_Horizontal myAccount_iti = (View_ITI_Horizontal) view.findViewById(R.id.my_account_iti);
        View_ITI_Horizontal mySetting_iti = (View_ITI_Horizontal) view.findViewById(R.id.my_setting_iti);

        //
        myMessage_iti.getLeftImageView().setImageResource(R.mipmap.iconmonst_email);
        myCollect_iti.getLeftImageView().setImageResource(R.mipmap.iconmonstr_star);
        myAccount_iti.getLeftImageView().setImageResource(R.mipmap.iconmonstr_link);
        mySetting_iti.getLeftImageView().setImageResource(R.mipmap.iconmonstr_gear);
        //
        myMessage_iti.getCentreTextView().setText(getString(R.string.my_message));
        myCollect_iti.getCentreTextView().setText(getString(R.string.my_collect));
        myAccount_iti.getCentreTextView().setText(getString(R.string.my_account));
        mySetting_iti.getCentreTextView().setText(getString(R.string.my_setting));

        //设置头像
        iv_avator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> map = new HashMap<String, Object>();
                //
                map.put(Constant.FLAG, Constant.FragmentTAG.get_picture_fragment);
                map.put(Constant.TAG, Constant.FragmentTAG.get_picture_fragmentTAG);
                map.put(Constant.TAKE_PHONE_TYPE.TAKE_TYPE, Constant.TAKE_PHONE_TYPE.AVATOR);
                //
                openFragment(map);
            }
        });

        mySetting_iti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> map = new HashMap<String, Object>();
                //
                map.put(Constant.FLAG, Constant.FragmentTAG.setting_fragment);
                map.put(Constant.TAG, Constant.FragmentTAG.setting_fragmentTAG);
                //
                openFragment(map);
            }
        });

    }

    @Override
    protected void loadData() {


        String url = GetUrl.getUserInfoByToken();
        OkHttpUtils
                .get()
                .url(url)
                .addHeader(Constant.TOKEN, MethodsUtil.getToken())
                .id(100)
                .build()
                .execute(new MyStringCallback());


    }


    public void loadAvator() {

        ImageLoaderUtil.imageLoadingCircle("file://" + Constant.UPLOADFILEPATH, iv_avator);
    }

    private void openFragment(Map map) {
        HomeActivity homeActivity = (HomeActivity) getActivity();
        homeActivity.setLayout(map);
    }


}
