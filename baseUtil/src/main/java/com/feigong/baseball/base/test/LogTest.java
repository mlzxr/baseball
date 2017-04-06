package com.feigong.baseball.base.test;/**
 * Created by ruler on 17/4/6.
 */

import com.feigong.baseball.base.util.L;

/**
 * 项目名称：baseball
 * 类名称： LogTest
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：2017.04.06 10:26
 * 备注：
 *
 * @version 1.0
 */
public class  LogTest {
    private static final String TAG="baseball";
    public  static void print(String string){
        L.e(TAG,string);
    }
}
