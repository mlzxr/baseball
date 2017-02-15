package com.feigong.baseball.beans;/**
 * Created by ruler on 17/1/15.
 */

/**
 * 项目名称：baseball
 * 类名称： ReturnMSG_UserInfo
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：2017.01.15 09:37
 * 备注：
 *
 * @version 1.0
 */
public class ReturnMSG_UserInfo extends ReturnMSG {


    /**
     * data : {"loginInfo":{"uid":"QTlERTYxMEZBRTNGNEUzQUEzMDBCMEQzRjI3QzFDNjk=","nickname":"漫步zhe","avator":"http://q.qlogo.cn/qqapp/1105849119/3EDF1E56101DE83E72B7EDD028CDC81B/100","regtime":"Feb 14, 2017","isBindWeibo":0,"isBindWechat":0,"isBindQQ":1,"isBindMobile":0,"mobileBind":"未绑定手机号"}}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * loginInfo : {"uid":"QTlERTYxMEZBRTNGNEUzQUEzMDBCMEQzRjI3QzFDNjk=","nickname":"漫步zhe","avator":"http://q.qlogo.cn/qqapp/1105849119/3EDF1E56101DE83E72B7EDD028CDC81B/100","regtime":"Feb 14, 2017","isBindWeibo":0,"isBindWechat":0,"isBindQQ":1,"isBindMobile":0,"mobileBind":"未绑定手机号"}
         */
        private String token;
        private LoginInfoBean loginInfo;

        public LoginInfoBean getLoginInfo() {
            return loginInfo;
        }

        public void setLoginInfo(LoginInfoBean loginInfo) {
            this.loginInfo = loginInfo;
        }

        public static class LoginInfoBean {
            /**
             * uid : QTlERTYxMEZBRTNGNEUzQUEzMDBCMEQzRjI3QzFDNjk=
             * nickname : 漫步zhe
             * avator : http://q.qlogo.cn/qqapp/1105849119/3EDF1E56101DE83E72B7EDD028CDC81B/100
             * regtime : Feb 14, 2017
             * isBindWeibo : 0
             * isBindWechat : 0
             * isBindQQ : 1
             * isBindMobile : 0
             * mobileBind : 未绑定手机号
             */

            private String uid;
            private String nickname;
            private String avator;
            private String regtime;
            private int isBindWeibo;
            private int isBindWechat;
            private int isBindQQ;
            private int isBindMobile;
            private String mobileBind;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getAvator() {
                return avator;
            }

            public void setAvator(String avator) {
                this.avator = avator;
            }

            public String getRegtime() {
                return regtime;
            }

            public void setRegtime(String regtime) {
                this.regtime = regtime;
            }

            public int getIsBindWeibo() {
                return isBindWeibo;
            }

            public void setIsBindWeibo(int isBindWeibo) {
                this.isBindWeibo = isBindWeibo;
            }

            public int getIsBindWechat() {
                return isBindWechat;
            }

            public void setIsBindWechat(int isBindWechat) {
                this.isBindWechat = isBindWechat;
            }

            public int getIsBindQQ() {
                return isBindQQ;
            }

            public void setIsBindQQ(int isBindQQ) {
                this.isBindQQ = isBindQQ;
            }

            public int getIsBindMobile() {
                return isBindMobile;
            }

            public void setIsBindMobile(int isBindMobile) {
                this.isBindMobile = isBindMobile;
            }

            public String getMobileBind() {
                return mobileBind;
            }

            public void setMobileBind(String mobileBind) {
                this.mobileBind = mobileBind;
            }

        }

        public String getToken() {
            return token;
        }

        public DataBean setToken(String token) {
            this.token = token;
            return this;
        }
    }


}
