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

    public static final String DOMAIN = "http://api.baseballsay.com";


    public static String getUserInfoByToken() {
        String url = DOMAIN + "/userApi/get_uinfo/";
        return url;
    }

    /**
     * 微信登陆，获取验证
     *
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
     *
     * @param access_token
     * @param openid
     * @return
     */
    public static String getWXUserInfo(String access_token, String openid) {
        StringBuffer sb = new StringBuffer();
        sb.append("https://api.weixin.qq.com/sns/userinfo");
        sb.append("?access_token=" + access_token + "&openid=" + openid);
        return sb.toString();
    }

    /**
     * 社会化登陆（第三方登录）
     *
     * @param type
     * @return
     */
    public static String getSocialLogin(String type) {
        String url = DOMAIN + "/userApi/social_login/" + type;
        return url;
    }

    /**
     * 推送消息开关
     *
     * @param flag
     * @return
     */
    public static String UpdatePushSwitch(int flag) {
        String url = DOMAIN + "/userApi/push_switch/{push_flag}";
        url = url.replace("{push_flag}", String.valueOf(flag));
        return url;
    }

    /**
     * 解绑社会化登陆
     *
     * @param type
     * @return
     */
    public static String unbundleOther(String type) {
        String url = DOMAIN + "/userApi/social_unbind/" + type;
        return url;
    }

    /**
     * 修改用户头像
     *
     * @return
     */
    public static String AvatorModify() {
        String url = DOMAIN + "/userApi/avator_modify";
        return url;
    }

    /**
     * 第三方绑定
     *
     * @return
     */
    public static String goSocialBind() {
        String url = DOMAIN + "/userApi/social_bind";
        return url;
    }

    /**
     * 获取频道数据
     *
     * @return
     */
    public static String infoChannel() {
        String url = DOMAIN + "/channelApi/info_channel";
        return url;
    }

    /**
     * 获取视频频道数据
     *
     * @return
     */
    public static String vodChannel() {
        String url = DOMAIN + "/channelApi/vod_channel";
        return url;
    }

    /**
     * 根据code获取视频列表
     *
     * @param code
     * @return
     */
    public static String vodRefreshByCode(String code) {
        String url = DOMAIN + "/vod/refresh/{channel_code}";
        url = url.replace("{channel_code}", code);
        return url;
    }
    public static String vodPullByCode(String code,int stamp) {
        String url = DOMAIN + "/vod/pull/{channel_code}/{unix_stamp}";
        url = url.replace("{channel_code}", code);
        url = url.replace("{unix_stamp}", String.valueOf(stamp));
        return url;
    }


    /**
     * 资讯－推荐列表
     *
     * @return
     */
    public static String ArticleRefresh() {
        String url = DOMAIN + "/article/refresh";
        return url;
    }
    public static String ArticlePull(int stamp) {
        String url = DOMAIN + "/article/pull/{unix_stamp}";
        url = url.replace("{unix_stamp}", String.valueOf(stamp));
        return url;
    }





    /**
     * 资讯列表刷新
     *
     * @param code
     * @return
     */
    public static String ArticleRefreshByCode(String code) {
        String url = DOMAIN + "/article/refresh/{channel_code}";
        url = url.replace("{channel_code}", code);
        return url;
    }
    public static String ArticlePullByCode(String code,int stamp) {
        String url = DOMAIN + "/article/pull/{channel_code}/{unix_stamp}";
        url = url.replace("{channel_code}", code);
        url = url.replace("{unix_stamp}", String.valueOf(stamp));
        return url;
    }



    /**
     * 获取视频详细信息
     * @param oid
     * @return
     */
    public static String getVideoDetailById(String oid) {
        String url = DOMAIN + "/vod/{oid}";
        url = url.replace("{oid}", oid);
        return url;
    }


    /**
     * 发表评论
     * @return
     */
    public static String postComment() {
        String url = DOMAIN + "/userApi/u_comment";
        return url;

    }

    /**
     * 回复评论
     * @return
     */
    public static String postReply(){
        String url = DOMAIN + "/userApi/u_reply";
        return url;

    }



    /**
     * 加载评论列表
     * @param objid_ref
     * @param time
     * @return
     */
    public static String getCommentList(String objid_ref, long time) {
        String url = DOMAIN+"/comment/load_logout/{refid}";
        url = url.replace("{refid}", objid_ref);
        return url;
    }

    /**
     * 加载更多评论
     * @param objid_ref
     * @param time
     * @return
     */
    public static String getCommentListMore(String objid_ref, long time) {
        String url = DOMAIN+"/comment/load_login/{refid}";
        url = url.replace("{refid}", objid_ref);
        return url;
    }

    /**
     * 手机发送验证码
     * @param phonenumber
     * @return
     */
    public static String sendCheckCode(String phonenumber) {
        String url = DOMAIN+"/userApi/sendCheckCode/{mobile}";
        url = url.replace("{mobile}", phonenumber);
        return url;
    }

    /**
     * 绑定手机
     * @return
     */
    public static String mobileBind() {
        String url = DOMAIN+"/userApi/mobile_bind";
        return url;
    }
}
