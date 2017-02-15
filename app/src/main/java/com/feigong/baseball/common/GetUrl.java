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

    public static final String DOMAIN="http://api.baseballsay.com";


    public static String getUserInfoByToken(){
        String url =DOMAIN+"/userApi/get_uinfo/";
        return url;
    }

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

    /**
     * 获取微信用户信息
     * @param access_token
     * @param openid
     * @return
     */
    public static String getWXUserInfo(String access_token,String openid) {
        StringBuffer sb = new StringBuffer();
        sb.append("https://api.weixin.qq.com/sns/userinfo");
        sb.append("?access_token=" + access_token + "&openid=" + openid);
        return sb.toString();
    }

    /**
     * 社会化登陆（第三方登录）
     * @param type
     * @return
     */
    public static String getSocialLogin(String type) {
        String url =DOMAIN+"/userApi/social_login/"+type;
        return url;
    }

    /**
     * 推送消息开关
     * @param flag
     * @return
     */
    public static String UpdatePushSwitch(int flag) {
        String url =DOMAIN+"/userApi/push_switch/{push_flag}";
        url = url.replace("{push_flag}",String.valueOf(flag));
        return url;
    }

    /**
     * 解绑社会化登陆
     * @param type
     * @return
     */
    public static String unbundleOther(String type) {
        String url =DOMAIN+"/userApi/social_unbind/"+type;
        return url;
    }
}