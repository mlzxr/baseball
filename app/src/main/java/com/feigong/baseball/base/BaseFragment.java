package com.feigong.baseball.base;/**
 * Created by ruler on 16/7/15.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.feigong.baseball.R;
import com.feigong.baseball.application.App;
import com.ml.core.util.L;
import com.ml.core.util.NetUtils;


/**
 * 项目名称：cqt_app
 * 类名称： BaseFragment
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：2016.07.15 17:30
 * 备注：
 *
 * @version 1.0
 */
public abstract class BaseFragment extends Fragment {

    private static final String TAG = "BaseFragment";

    protected static final String CODE = "code";
    protected static final String MSG = "msg";
    protected static final String DATA = "data";

    protected static final int OK = 1;
    protected static final int EER = 0;

    protected Context context;


    //************************************************
    //*********** fragment 生命周期
    //************************************************

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //L.e(TAG,"onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //L.e(TAG,"onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        //
        if (checkNetWork()) {
            view = inflater.inflate(getLayout(), null);
            initVariables();
            initViews(view, savedInstanceState);
            loadData();
        } else {
            view = inflater.inflate(R.layout.activity_network_anomaly, null);
        }
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        //L.e(TAG,"onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        //L.e(TAG,"onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        //L.e(TAG,"onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        //L.e(TAG,"onStop");
        //  ImageLoader.getInstance().clearMemoryCache();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ///L.e(TAG,"onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //L.e(TAG,"onDestroy");
        System.gc();
    }


    @Override
    public void onDetach() {
        super.onDetach();
        L.e(TAG, "onDetach");

    }


    //************************************************
    //*********** 基本方法
    //************************************************
    //
    protected abstract int getLayout();

    //初始化变量
    protected abstract void initVariables();

    //初始化布局
    protected abstract void initViews(View view, Bundle savedInstanceState);

    //加载数据
    protected abstract void loadData();

    //钩子
    protected boolean checkNetWork() {
        boolean flag =  NetUtils.isConnected(App.getContext());
        return true;
    }

    /**
     * [简化Toast]
     *
     * @param msg
     */
    protected void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }


}
