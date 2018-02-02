package com.feigong.baseball.common;/**
 * Created by ruler on 17/2/17.
 */

import java.util.Date;
import java.util.UUID;

/**
 * 项目名称：baseball
 * 类名称： MethodsUtil
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：2017.02.17 10:24
 * 备注：
 *
 * @version 1.0
 */
public class MethodsUtil {

    /**
     * 生成文件名称
     * @return
     */
    public static String getFileName() {
        int n = 0;
        while (n < 100000) {
            n = (int) (Math.random() * 1000000);
        }
        return new Date().getTime() + "" + n;
    }

    public static final String GUID()
    {
        UUID uuid= UUID.randomUUID();
        return uuid.toString().replaceAll("-","");
    }


}
