package com.feigong.baseball.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.feigong.baseball.R;

/**
 * Created by ruler on 18/3/27.
 *
 * Activity 基础
 *
 */

public class BasicsActivity extends AppCompatActivity {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_network_anomaly);
    }

    /** 日志输出标志 **/
    protected final String TAG = this.getClass().getSimpleName();

    /**
     * [简化Toast]
     * @param msg
     */
    protected void showToast(String msg){
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }
}
