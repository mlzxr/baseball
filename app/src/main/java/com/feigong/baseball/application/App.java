package com.feigong.baseball.application;/**
 * Created by ruler on 16/7/19.
 */

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.multidex.MultiDexApplication;

import com.feigong.baseball.base.crash.CrashHandler;
import com.feigong.baseball.dto.DaoMaster;
import com.feigong.baseball.dto.DaoSession;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import cn.jpush.android.api.JPushInterface;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import org.greenrobot.greendao.database.Database;

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
public class App extends MultiDexApplication {


    private static Context context;

    private CrashHandler crashHandler;

    public static final boolean ENCRYPTED = true;

    private RefWatcher refWatcher;

    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        //定义单列Context
        context = getApplicationContext();


        //监听内存泄漏
        refWatcher = LeakCanary.install(this);



        //异常监听
        //crashHandler = CrashHandler.getInstance();
        //crashHandler.init(context);




        //
        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);

        //网络图片加载框架
        initImageLoader();
        //加载orm框架
        initGreenDao();



    }

    private void initGreenDao(){

        DaoMaster.DevOpenHelper helper = new  DaoMaster.DevOpenHelper(this, ENCRYPTED ? "users-db-encrypted" : "users-db");
        Database db =  helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();


    }


    public static DaoSession getDaoInstant() {
        return daoSession;
    }

    private void initImageLoader() {
        ImageLoaderConfiguration config = ImageLoaderConfiguration.createDefault(getApplicationContext());
        ImageLoader.getInstance().init(config);
    }




    public static Context getContext() {
        return context;
    }


    public static RefWatcher getRefWatcher(Context context) {
        App application = (App) context.getApplicationContext();
        return application.refWatcher;
    }


}
