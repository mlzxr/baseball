package com.feigong.baseball.common;/**
 * Created by ruler on 16/12/6.
 */

/**
 * 项目名称：baseball
 * 类名称： Constant
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：2016.12.06 10:52
 * 备注：
 *
 * @version 1.0
 */
public class Constant {


    public static final String FLAG="flag";
    public static final String TAG="tag";


    public interface FragmentTAG{
        int setting_fragment=1;
        String setting_fragmentTAG="setting_fragment";
        //
        int security_account_fragment=2;
        String security_account_fragmentTAG="security_account_fragment";
        //
        int login_fragment=3;
        String login_fragmentTAG="login_fragment";

    }


    /***第三方应用参数***/

    // 微信
    public interface WX{
        public static final String APP_ID = "wx03acd4dc4205b6f2";
        public static final String APP_SECRET = "4a70b851767dcb9b896468aaf43f086e";
    }
    //微博
    public interface WB{
        public static final String APP_KEY      = "3273926641";
        public static final String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";
        public static final String SCOPE =
                "email,direct_messages_read,direct_messages_write,"
                        + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                        + "follow_app_official_microblog," + "invitation_write";
    }
    public interface QQ{
        public static final String APP_ID = "1105849119";
    }

    //QQ






}
