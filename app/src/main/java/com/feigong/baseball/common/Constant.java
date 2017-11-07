package com.feigong.baseball.common;/**
 * Created by ruler on 16/12/6.
 */

import android.app.Activity;
import android.os.Environment;

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

    public static final String TOKEN="token";
    public static final String PUSH_FLAG="push_flag";

    public static final String EMPTY="";

    public static final String ID="id";


    public static final String UPLOADFILEPATH = Environment.getExternalStorageDirectory().getPath() + "/temp.jpg";//指定默认照片存储路径


    //http://m.baseballsay.com/article/show/5917f42bf07cee4b0d82bc9b


    public interface USERINFO{
        public static final String IS_BINDING_WX="is_binding_wx";
        public static final String NICKNAME="nickname";
        public static final String AVATOR="avator";

    }

    public static final String FLAG = "flag";
    public static final String TAG = "tag";

    public interface FragmentTAG {
        //系统设置
        int setting_fragment = 1;
        String setting_fragmentTAG = "setting_fragment";
        //帐号安全
        int security_account_fragment = 2;
        String security_account_fragmentTAG = "security_account_fragment";
        //登陆
        int login_fragment = 3;
        String login_fragmentTAG = "login_fragment";
        //拍照
        int get_picture_fragment=4;
        String get_picture_fragmentTAG="get_picture_fragment";
        //社会化登陆绑定
        int social_fragment=5;
        String social_fragmentTAG="social_fragment";
        //资讯
        int information_fragment = 6;
        String InformationFragmentTAG="information_fragment";





        //视频
        int video_fragment = 7;
        String VideoFragmentTAG="video_fragment";


        //资讯详细页面
        int informationDetail_fragment =8;
        String InformationDetailFragmentTAG="informationDetail_fragment";



        //H5详情页中的图片集
        int showWebVIewImages_fragment =9;
        String ShowWebVIewImagesFragmentTAG="ShowWebVIewImagesFragment";

    }


    public interface H5{
        public static final String ARTICLE="http://m.baseballsay.com/article/show/";
    }


    public interface ActivityTAG{

        int home_activity=1;

        int wx_entry_activity=2;

    }


    /**
     * 拍照类型
     */
    public interface TAKE_PHONE_TYPE{
        public static final String TAKE_TYPE="take_type";
        //头像
        public static final int AVATOR=1;


    }


    // 第三方应用参数
    public interface Other {
        //登录类型(1:微博,2:微信,3:扣扣)
        public static final String WB = "1";
        public static final String WX = "2";
        public static final String QQ = "3";

    }

    // 微信

    public interface WX {
        public static final String APP_ID = "wx03acd4dc4205b6f2";
        public static final String APP_SECRET = "4a70b851767dcb9b896468aaf43f086e";
    }

    //微博
    public interface WB {
        public static final String APP_KEY = "3273926641";
        public static final String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";
        public static final String SCOPE =
                "email,direct_messages_read,direct_messages_write,"
                        + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                        + "follow_app_official_microblog," + "invitation_write";
    }

    //QQ
    public interface QQ {
        public static final String APP_ID = "1105849119";
    }


    //返回状态码
    public interface FGCode {
        public static final int OpOk_code = 10000;
        public static final String OpOk_msg = "操作成功";
        public static final int OpFail_code = 10004;
        public static final String OpFail_msg = "操作失败";
        public static final int ParamError_code = 40001;
        public static final String ParamError_msg = "参数问题";
        public static final int TokenExpire_code = 40002;
        public static final String TokenExpire_msg = "Token过期";
        public static final int TokenInValid_code = 40003;
        public static final String TokenInValid_msg = "无效的Token";
    }


}
