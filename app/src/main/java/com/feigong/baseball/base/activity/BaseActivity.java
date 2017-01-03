package com.feigong.baseball.base.activity;/**
 * Created by ruler on 16/7/11.
 */

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.WindowManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

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
public abstract class BaseActivity extends FragmentActivity {

    private static final String TAG = "BaseActivity";

    protected static final String CODE="code";
    protected static final String MSG="msg";
    protected static final String DATA="data";

    protected static final int OK=1;
    protected static final int EER=0;



    protected ExecutorService baseExecutorService;

    protected Runnable baseRunnable;

    protected Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏透明化
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        if(checkPass()){
            setContentView(getLayout());

            initVariables();
            initViews(savedInstanceState);
            initData();
        }
    }

    protected abstract int getLayout();

    protected abstract void initVariables();

    protected abstract void initViews(Bundle savedInstanceState);

    protected abstract void initData();
    //钩子
    protected boolean checkPass() {

        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        shutdownAndAwaitTermination(baseExecutorService);
        System.gc();
    }

    //释放线程
    protected void shutdownAndAwaitTermination(ExecutorService pool) {
        if(pool!=null){
            pool.shutdownNow();
            try {
                if (!pool.awaitTermination(1, TimeUnit.SECONDS)) {
                    pool.shutdownNow();

                    if (!pool.awaitTermination(1, TimeUnit.SECONDS))
                        System.err.println("Pool did not terminate");
                }
            } catch (InterruptedException ie) {
                pool.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }



}
