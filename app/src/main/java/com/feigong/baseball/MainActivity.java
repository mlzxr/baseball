package com.feigong.baseball;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.feigong.baseball.activity.HomeActivity;
import com.feigong.baseball.base.BaseActivity;
import com.feigong.baseball.common.Constant;
import com.feigong.baseball.common.ImageUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 应用主入口
 */
public class MainActivity extends BaseActivity {


    private static final String TAG = "MainActivity";


    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Bind(R.id.rl_layout)
    RelativeLayout rlLayout;
    @Bind(R.id.tv_skip)
    TextView tvSkip;
    @Bind(R.id.iv_advertising)
    ImageView ivAdvertising;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initVariables() {
        //检查sd卡读取权限
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
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
        ivAdvertising.setVisibility(View.GONE);
        String url ="https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=120514187,1061921539&fm=27&gp=0.jpg";
        ImageLoader.getInstance().displayImage(url, ivAdvertising, ImageUtil.getImageOptions(),new ImageLoadingListener(){

            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                 ivAdvertising.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });

        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomeActivity();
            }
        });



    }

    @Override
    protected void initData() {

        CountDownTimer timer = new CountDownTimer(5200,1000) {
            int num = 4;
            @Override
            public void onTick(long millisUntilFinished) {
                tvSkip.setText(getString(R.string.skip)+"("+String.valueOf(num)+")");
                num--;
            }
            @Override
            public void onFinish() {
                openHomeActivity();
            }
        };
        timer.start();

    }



    //打开主界面
    private void openHomeActivity() {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

}
