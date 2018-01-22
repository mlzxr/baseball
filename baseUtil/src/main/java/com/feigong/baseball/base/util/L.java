package com.feigong.baseball.base.util;/**
 * Created by ruler on 16/7/11.
 */

import android.util.Log;

/**
 * 项目名称：cqt_app
 * 类名称： L
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：2016.07.11 20:39
 * 备注：自定义 日志类
 *
 * @version 1.0
 */
public class L {

    private static final String TAG="L";

    private L(){

        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static boolean isDebug = true;

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg)
    {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void d(String tag, String msg)
    {
        if (isDebug)
            Log.d(tag, msg);
    }

    public static void e(String tag, String msg)
    {
        if (isDebug)
            Log.e(tag, msg);
    }

    public static void e(String msg)
    {
        if (isDebug)
            Log.e(TAG, msg);
    }

    public static void v(String tag, String msg)
    {
        if (isDebug)
            Log.v(tag, msg);
    }

}
