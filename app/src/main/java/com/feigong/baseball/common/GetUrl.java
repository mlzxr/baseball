package com.feigong.baseball.common;/**
 * Created by ruler on 16/12/28.
 */

/**
 * 项目名称：baseball
 * 类名称： GetUrl
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：2016.12.28 16:16
 * 备注：
 *
 * @version 1.0
 */
public class GetUrl {


    /**
     *  微信登陆，获取验证
     * @param code
     * @return
     */
    public static String getAccessToken(String code) {
        StringBuffer sb = new StringBuffer();
        sb.append("https://api.weixin.qq.com/sns/oauth2/access_token");
        sb.append("?appid=" + Constant.WX.APP_ID);
        sb.append("&secret=" + Constant.WX.APP_SECRET);
        sb.append("&code=" + code);
        sb.append("&grant_type=authorization_code");
        return sb.toString();
    }









}
