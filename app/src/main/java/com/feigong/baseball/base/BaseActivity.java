package com.feigong.baseball.base;/**
 * Created by ruler on 16/7/11.
 */

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.widget.Toast;

import com.feigong.baseball.R;
import com.feigong.baseball.application.App;
import com.feigong.baseball.common.Constant;
import com.feigong.baseball.common.MethodsUtil;
import com.feigong.baseball.common.StatusBarUtil;
import com.ml.core.util.NetUtils;
import com.ml.core.util.SPUtils;


import org.greenrobot.eventbus.EventBus;


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


    protected static final String CODE = "code";
    protected static final String MSG = "msg";
    protected static final String DATA = "data";

    protected static final int OK = 1;
    protected static final int EER = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        steepStatusBar();

        //mod
        SPUtils.put(App.getContext(), Constant.TOKEN, "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiJSVGM1TVRNNU5UVkZRekk1TkVVeU5UZzBNamhETkRRd05EWTJOemMzTmtRPSIsImlhdCI6MTU1NTExNzM3NDc1M30.mr9QSKZsgfGxBBNLKOvb8yugONxGLvOAC9Rs_g1XWt4");


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
        StatusBarUtil.setColor(this, ContextCompat.getColor(this,R.color.tab_select_s));
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
        if (!TextUtils.isEmpty(MethodsUtil.getToken())) {
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
