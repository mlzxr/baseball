package com.feigong.baseball.application;/**
 * Created by ruler on 16/7/19.
 */

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import cn.jpush.android.api.JPushInterface;
import com.feigong.baseball.base.util.ScreenUtils;

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

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        //获取屏幕相关属性
        ScreenUtils.getScreenWidth(context);
        ScreenUtils.getScreenHeight(context);
        ScreenUtils.getScreenDensity(context);
        //网络图片加载框架
        initImageLoader(context);
        //
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);

        //Debug
        //LeakCanary.install(this);
    }

    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = ImageLoaderConfiguration.createDefault(context);
        ImageLoader.getInstance().init(config);
    }


    public static Context getContext(){
        return context;
    }

}
