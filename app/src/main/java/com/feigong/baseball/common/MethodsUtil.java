package com.feigong.baseball.common;/**
 * Created by ruler on 17/2/17.
 */

import com.feigong.baseball.application.App;
import com.ml.core.util.SPUtils;

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


    //获取有效token
    public static String getToken() {
        String token = (String) SPUtils.get(App.getContext(), Constant.TOKEN, "");
        return token;
    }
    //保存token
    public static void putToken(String token){
        SPUtils.put(App.getContext(),Constant.TOKEN,token);
    }


    /**
     * 生成文件名称
     *
     * @return
     */
    public static String getFileName() {
        int n = 0;
        while (n < 100000) {
            n = (int) (Math.random() * 1000000);
        }
        return new Date().getTime() + "" + n;
    }

    public static final String GUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }

    /**
     * 隐藏手机号
     *
     * @param phone
     * @return
     */
    public static final String hidePhone(String phone) {
        // 括号表示组，被替换的部分$n表示第n组的内容
        phone = phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        return phone;
    }


}
