package com.feigong.baseball;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.feigong.baseball.base.BaseActivity;
import com.feigong.baseball.activity.HomeActivity;

/**
 * 应用主入口
 *
 *
 */
public class MainActivity extends BaseActivity {


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //
                Intent intent =new Intent();
                intent.setClass(MainActivity.this,HomeActivity.class);
                startActivity(intent);
                //
                finish();
            }
        },1000);

    }
}
