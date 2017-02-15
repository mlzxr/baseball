package com.feigong.baseball.myinfo;/**
 * Created by ruler on 17/2/15.
 */

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.feigong.baseball.R;
import com.feigong.baseball.application.App;
import com.feigong.baseball.base.fragment.BaseFragment;
import com.feigong.baseball.base.util.DateUtil;
import com.feigong.baseball.base.util.L;
import com.feigong.baseball.base.util.SPUtils;
import com.feigong.baseball.base.util.T;
import com.feigong.baseball.base.view.util.ViewUtil;
import com.feigong.baseball.beans.ReturnMSG_Push;
import com.feigong.baseball.beans.ReturnMSG_UserInfo;
import com.feigong.baseball.common.Constant;
import com.feigong.baseball.common.GetUrl;
import com.feigong.baseball.fgview.ViewTopBar;
import com.feigong.baseball.fgview.View_ITTI_Horizontal;
import com.feigong.baseball.fragment.GetPictureFragment;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Request;

/**
 * 项目名称：baseball
 * 类名称： SocialFragment
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：2017.02.15 12:13
 * 备注：社交帐号绑定
 *
 * @version 1.0
 */
public class SocialFragment extends BaseFragment {

    private static final String TAG="SocialFragment";

    private View_ITTI_Horizontal view_itti_qq;
    private View_ITTI_Horizontal view_itti_wx;
    private View_ITTI_Horizontal view_itti_xl;


    public static SocialFragment newInstance() {
        SocialFragment socialFragment = new SocialFragment();
        return socialFragment;
    }

    public class MyStringCallback extends StringCallback
    {
        @Override
        public void onBefore(Request request, int id)
        {
        }

        @Override
        public void onAfter(int id)
        {

        }

        @Override
        public void onError(Call call, Exception e, int id)
        {
            L.e(TAG,e.getMessage());
        }

        @Override
        public void onResponse(String response, int id)
        {
            L.e(TAG,response);
            switch (id)
            {
                case 100:

                    break;
            }
        }

        @Override
        public void inProgress(float progress, long total, int id)
        {
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_social;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        ViewTopBar viewTopBar = (ViewTopBar)view.findViewById(R.id.viewTopBar);
        viewTopBar.getTv_title().setText(getString(R.string.social_account));
        viewTopBar.getIv_back().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        //
        view_itti_qq =(View_ITTI_Horizontal)view.findViewById(R.id.view_itti_qq);
        view_itti_wx =(View_ITTI_Horizontal)view.findViewById(R.id.view_itti_wx);
        view_itti_xl =(View_ITTI_Horizontal)view.findViewById(R.id.view_itti_sl);
        //
        view_itti_qq.getLeftImageView().setImageResource(R.mipmap.icon_qq);
        view_itti_wx.getLeftImageView().setImageResource(R.mipmap.icon_wx);
        view_itti_xl.getLeftImageView().setImageResource(R.mipmap.icon_xl);
        //
        view_itti_qq.getCentreTextView().setText(R.string.qq);
        view_itti_wx.getCentreTextView().setText(R.string.wx);
        view_itti_xl.getCentreTextView().setText(R.string.xl);
        //
        setBindingText(view_itti_qq,0);
        setBindingText(view_itti_wx,0);
        setBindingText(view_itti_xl,0);
        //QQ
        view_itti_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(v,Constant.Other.QQ);
            }
        });
        //微信
        view_itti_wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(v,Constant.Other.WX);
            }
        });
        //微博
        view_itti_xl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(v,Constant.Other.WB);
            }
        });

    }

    @Override
    protected void loadData() {
        String userinfo = String.valueOf(SPUtils.get(App.getContext(), Constant.USERINFO.All,""));
        ReturnMSG_UserInfo returnMSG_userInfo =  new Gson().fromJson(userinfo,ReturnMSG_UserInfo.class);
        if(returnMSG_userInfo!=null &&  returnMSG_userInfo.getData()!=null){
            ReturnMSG_UserInfo.DataBean.LoginInfoBean loginInfoBean = returnMSG_userInfo.getData().getLoginInfo();
            if(loginInfoBean!=null){
                if(loginInfoBean.getIsBindQQ()==1){
                    setUnbundleText(view_itti_qq,1);
                }else {
                    setBindingText(view_itti_qq,0);
                }
                //
                if(loginInfoBean.getIsBindWechat()==1){
                    setUnbundleText(view_itti_wx,1);
                }else {
                    setBindingText(view_itti_wx,0);
                }
                //
                if(loginInfoBean.getIsBindWeibo()==1){
                    setUnbundleText(view_itti_xl,1);
                }else {
                    setBindingText(view_itti_xl,0);
                }
            }
        }
    }

    private void update(View view, final String type){
        int flag = ViewUtil.getTagInt(view);
        if(flag==1){
            String message ="";
            switch (type){
                case Constant.Other.QQ:
                    message=getString(R.string.sure_unbundle_qq);
                    break;
                case Constant.Other.WX:
                    message=getString(R.string.sure_unbundle_wx);
                    break;

                case Constant.Other.WB:
                    message=getString(R.string.sure_unbundle_wb);
                    break;
            }
            /**
             * 解绑第三方登陆
             */
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.hint);
            builder.setMessage(message);
            builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    String token = String.valueOf(SPUtils.get(App.getContext(),Constant.TOKEN,""));
                    String url = GetUrl.unbundleOther(type);
                    L.e(TAG,url);
                    OkHttpUtils
                            .get()
                            .url(url)
                            .addHeader(Constant.TOKEN,token)
                            .id(100)
                            .build()
                            .execute(new MyStringCallback());
                    dialog.dismiss();
                }
            });
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();
        }else {
            /**
             * 打开第三方登陆
             */


        }
    }

    private void setUnbundleText(View_ITTI_Horizontal view_itti_horizontal,int type){
        view_itti_horizontal.getRightTextView().setText(R.string.unbundle);
        view_itti_horizontal.getRightTextView().setTextColor(Color.parseColor("#c0c0c0"));
        view_itti_horizontal.setTag(type);
    }

    private void setBindingText(View_ITTI_Horizontal view_itti_horizontal,int type){
        view_itti_horizontal.getRightTextView().setText(R.string.binding);
        view_itti_horizontal.getRightTextView().setTextColor(Color.BLACK);
        view_itti_horizontal.setTag(type);
    }

}
