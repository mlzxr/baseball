package com.feigong.baseball.base;/**
 * Created by ruler on 16/7/11.
 */

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.feigong.baseball.R;
import com.feigong.baseball.application.App;
import com.feigong.baseball.base.util.NetUtils;
import com.feigong.baseball.base.util.SPUtils;
import com.feigong.baseball.common.Constant;


import org.greenrobot.eventbus.EventBus;

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
public abstract class BaseActivity extends BasicsActivity {

    /**
     * 是否沉浸状态栏
     **/
    private boolean isSetStatusBar = true;


    protected static final String CODE = "code";
    protected static final String MSG = "msg";
    protected static final String DATA = "data";

    protected static final int OK = 1;
    protected static final int EER = 0;


    protected String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        if (isSetStatusBar) {
            steepStatusBar();
        }

        //测试
        SPUtils.put(App.getContext(), Constant.TOKEN, "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiJNVU01UWpJNE0wRTVRVVpGTkRZMk5EaEZOMFF4T1VNMVJFTTVRVFl3TUVNPSIsImlhdCI6MTUxOTE5MTI2OTk0N30.3cpabXASYdepk96B2_9uZ7B1Pa45Ky57DJAGwNnuzLA");

        token = String.valueOf(SPUtils.get(App.getContext(), Constant.TOKEN, ""));

        if (checkNetWork()) {
            setContentView(getLayout());
            ButterKnife.bind(this);
            //
            initVariables();
            initViews(savedInstanceState);
            initData();
        } else {
            setContentView(R.layout.activity_network_anomaly);
        }

    }

    /**
     * [沉浸状态栏]
     */
    private void steepStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            } else {
                Window window = getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.flags |= flagTranslucentStatus | flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
        }
    }


    protected abstract int getLayout();

    protected abstract void initVariables();

    protected abstract void initViews(Bundle savedInstanceState);

    protected abstract void initData();

    //判断网络是否正常
    protected boolean checkNetWork() {

        return NetUtils.isConnected(App.getContext());
    }

    /**
     * 判断是否登陆
     *
     * @return
     */
    protected boolean checkLogin() {

        if (!TextUtils.isEmpty(token)) {
            return true;
        }
        return false;
    }


    /**
     * [简化Toast]
     *
     * @param msg
     */
    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }

        System.gc();
    }


}
