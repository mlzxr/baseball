package com.feigong.baseball.myinfo;/**
 * Created by ruler on 16/12/5.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.feigong.baseball.MainActivity;
import com.feigong.baseball.R;
import com.feigong.baseball.activity.HomeActivity;
import com.feigong.baseball.application.App;
import com.feigong.baseball.base.BaseFragment;
import com.feigong.baseball.beans.ReturnMSG_Push;
import com.feigong.baseball.common.Constant;
import com.feigong.baseball.common.GetUrl;
import com.feigong.baseball.common.MethodsUtil;
import com.feigong.baseball.fgview.ViewTT_TB_Horizontal;
import com.feigong.baseball.fgview.ViewTopBar;
import com.feigong.baseball.fgview.View_TTI_Horizontal;
import com.google.gson.Gson;
import com.ml.core.util.L;
import com.ml.core.util.SPUtils;
import com.ml.core.util.T;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Request;

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
            switch (id)
            {
                case 100:

                    ReturnMSG_Push returnMSG_push =  new Gson().fromJson(response,ReturnMSG_Push.class);
                    if(returnMSG_push!=null && returnMSG_push.getCode()==Constant.FGCode.OpOk_code){
                        SPUtils.put(App.getContext(),Constant.PUSH_FLAG,returnMSG_push.getData().getPush_flag());
                        T.showShort(App.getContext(),R.string.setting_success);
                    }else {
                        T.showShort(App.getContext(),R.string.setting_failed);
                    }

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
        ViewTT_TB_Horizontal view_tti_tb_push =(ViewTT_TB_Horizontal)view.findViewById(R.id.view_tti_tb_push);
        View_TTI_Horizontal view_tti_cache =(View_TTI_Horizontal)view.findViewById(R.id.view_tti_cache);
        //
        View_TTI_Horizontal view_tti_share =(View_TTI_Horizontal)view.findViewById(R.id.view_tti_share);
        View_TTI_Horizontal view_tti_about =(View_TTI_Horizontal)view.findViewById(R.id.view_tti_about);

        //
        view_tti_account.getLeft_textView().setText("  "+getString(R.string.security_account));
        view_tti_tb_push.getLeft_textView().setText("  "+getString(R.string.push_notification));
        view_tti_cache.getLeft_textView().setText("  "+getString(R.string.clear_cache));
        view_tti_share.getLeft_textView().setText("  "+getString(R.string.share_friend));
        view_tti_about.getLeft_textView().setText("  "+getString(R.string.about_us));
        int push_flag = (int) SPUtils.get(App.getContext(),Constant.PUSH_FLAG,0);
        if(push_flag==1){
            view_tti_tb_push.getTb_push().setChecked(true);
        }else {
            view_tti_tb_push.getTb_push().setChecked(false);
        }


        /**
         * 帐号
         */
        view_tti_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> map = new HashMap<String, Object>();
                //
                map.put(Constant.FLAG,Constant.FragmentTAG.security_account_fragment);
                map.put(Constant.TAG,Constant.FragmentTAG.security_account_fragmentTAG);
                 /*
                打开主界面
                 */
                HomeActivity homeActivity = (HomeActivity)getActivity();
                homeActivity.setLayout(map);
            }
        });
        /**
         * 推送
         */
        view_tti_tb_push.getTb_push().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token = String.valueOf(SPUtils.get(App.getContext(),Constant.TOKEN,""));
                int flag = (int) SPUtils.get(App.getContext(),Constant.PUSH_FLAG,0);
                flag = Math.abs((flag-1));
                String url = GetUrl.UpdatePushSwitch(flag);
                OkHttpUtils
                        .get()
                        .url(url)
                        .addHeader(Constant.TOKEN,token)
                        .id(100)
                        .build()
                        .execute(new MyStringCallback());
            }
        });
        /**
         * 清理缓存
         */
        view_tti_cache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.if_clear_cache);
                builder.setMessage(R.string.the_cache_is_convenient_you_browse_content);
                builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        ImageLoader.getInstance().clearDiskCache();
                        ImageLoader.getInstance().clearMemoryCache();
                        T.showLong(App.getContext(),R.string.clear_cache_over);
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });

        /*
           退出登陆
         */
        view.findViewById(R.id.bt_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodsUtil.putToken(Constant.EMPTY);

                getActivity().finish();

                Intent intent =new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void loadData() {

    }
}
