package com.feigong.baseball.base;/**
 * Created by ruler on 16/7/11.
 */

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.feigong.baseball.application.App;
import com.feigong.baseball.base.util.SPUtils;
import com.feigong.baseball.common.Constant;


import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * 项目名称：cqt_app
 * 类名称： BaseActivity
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：2016.07.11 20:14
 * 备注：
 *
 * @version 1.0
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected static final String CODE="code";
    protected static final String MSG="msg";
    protected static final String DATA="data";

    protected static final int OK=1;
    protected static final int EER=0;

    protected String TAG;

    protected String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getComponentName().getShortClassName();
        //设置状态栏透明化
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        }


        //测试
        SPUtils.put(App.getContext(), Constant.TOKEN,"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiJNVU01UWpJNE0wRTVRVVpGTkRZMk5EaEZOMFF4T1VNMVJFTTVRVFl3TUVNPSIsImlhdCI6MTUxOTE5MTI2OTk0N30.3cpabXASYdepk96B2_9uZ7B1Pa45Ky57DJAGwNnuzLA");

        token = String.valueOf(SPUtils.get(App.getContext(),Constant.TOKEN,""));

        if(checkNetWork()){
            setContentView(getLayout());
            ButterKnife.bind(this);
            //


            initVariables();
            initViews(savedInstanceState);
            initData();
        }


    }

    protected abstract int getLayout();

    protected abstract void initVariables();

    protected abstract void initViews(Bundle savedInstanceState);

    protected abstract void initData();
    //
    protected boolean checkNetWork() {

        return true;
    }

    /**
     * 判断是否登陆
     * @return
     */
    protected boolean checkLogin(){

        if(!TextUtils.isEmpty(token)){
            return true;
        }
        return false;
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }


}
