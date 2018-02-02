package com.feigong.baseball;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;

import com.feigong.baseball.base.BaseActivity;
import com.feigong.baseball.activity.HomeActivity;

/**
 * 应用主入口
 *
 *
 */
public class MainActivity extends BaseActivity {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initVariables() {
        //检查sd卡读取权限
        int permission = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
    }

    @Override
    protected void initData() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                openHomeActivity();
            }
        },2000);
    }

    //打开主界面
    private void openHomeActivity(){
        Intent intent =new Intent();
        intent.setClass(MainActivity.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }

}
