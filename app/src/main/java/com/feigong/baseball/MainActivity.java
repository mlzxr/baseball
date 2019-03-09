package com.feigong.baseball;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.feigong.baseball.activity.HomeActivity;
import com.feigong.baseball.base.BaseActivity;
import com.feigong.baseball.common.ImageUtil;
import com.feigong.baseball.common.StatusBarUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import butterknife.Bind;

/**
 * 应用主入口
 */
public class MainActivity extends BaseActivity {


    private static final String TAG = "MainActivity";

    @Bind(R.id.rl_layout)
    RelativeLayout rlLayout;
    @Bind(R.id.tv_skip)
    TextView tvSkip;
    @Bind(R.id.iv_advertising)
    ImageView ivAdvertising;


    private CountDownTimer timer;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initVariables() {
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.transparent));

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ivAdvertising.setVisibility(View.GONE);
        String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1522737230&di=7873305994bc28da3644e4baae8025a2&imgtype=jpg&er=1&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F4610b912c8fcc3ce510ed22e9845d688d53f20e4.jpg";

        ImageLoader.getInstance().displayImage(url, ivAdvertising, ImageUtil.getImageOptions(), new ImageLoadingListener() {

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
                timer.cancel();
                openHomeActivity();
            }
        });


    }

    @Override
    protected void initData() {

        timer = new CountDownTimer(3200, 1000) {
            int num = 3;

            @Override
            public void onTick(long millisUntilFinished) {
                tvSkip.setText(getString(R.string.skip) + "(" + String.valueOf(num) + ")");
                num--;
                if (num == 1) {
                    ivAdvertising.setVisibility(View.GONE);
                }
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
