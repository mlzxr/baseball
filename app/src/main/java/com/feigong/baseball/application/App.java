package com.feigong.baseball.application;/**
 * Created by ruler on 16/7/19.
 */

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import cn.jpush.android.api.JPushInterface;

import com.feigong.baseball.base.util.ScreenUtils;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * 项目名称：cqt_app
 * 类名称： App
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：2016.07.19 16:56
 * 备注：
 *
 * @version 1.0
 */
public class App extends Application {

    public static RefWatcher getRefWatcher(Context context) {
        App application = (App) context.getApplicationContext();
        return application.refWatcher;
    }


    private static Context context;

    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        //监听内存泄漏
        refWatcher = LeakCanary.install(this);


        //网络图片加载框架
        initImageLoader(this);
        //
        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);
        //定义单列Context
        context = getApplicationContext();
        //获取屏幕相关属性
        ScreenUtils.getScreenWidth(context);
        ScreenUtils.getScreenHeight(context);
        ScreenUtils.getScreenDensity(context);

    }

    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = ImageLoaderConfiguration.createDefault(context);
        ImageLoader.getInstance().init(config);
    }

    public static Context getContext() {
        return context;
    }

}
