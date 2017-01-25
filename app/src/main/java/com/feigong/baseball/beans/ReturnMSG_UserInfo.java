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
     * data : {"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiJOMEUzUlRreU1rRTJNVEE1TkRGRE5VSkNOalV4TVRneFJURkNOVFF4UVRBPSIsImlhdCI6MTQ4NDEwNzU3MzI0Nn0.7NjJQvC2BD2pScD6x976aReUgw8S9gAzzItyH3lkvc8","loginInfo":{"uid":"N0E3RTkyMkE2MTA5NDFDNUJCNjUxMTgxRTFCNTQxQTA=","nickname":"张晓二","avator":"http://q.qlogo.cn/qqapp/1105849119/9FEFE532706F3A7A70644BFFBF828CF3/100","regtime":"2017-01-11"}}
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
         * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiJOMEUzUlRreU1rRTJNVEE1TkRGRE5VSkNOalV4TVRneFJURkNOVFF4UVRBPSIsImlhdCI6MTQ4NDEwNzU3MzI0Nn0.7NjJQvC2BD2pScD6x976aReUgw8S9gAzzItyH3lkvc8
         * loginInfo : {"uid":"N0E3RTkyMkE2MTA5NDFDNUJCNjUxMTgxRTFCNTQxQTA=","nickname":"张晓二","avator":"http://q.qlogo.cn/qqapp/1105849119/9FEFE532706F3A7A70644BFFBF828CF3/100","regtime":"2017-01-11"}
         */

        private String token;
        private LoginInfoBean loginInfo;
        //
        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public LoginInfoBean getLoginInfo() {
            return loginInfo;
        }

        public void setLoginInfo(LoginInfoBean loginInfo) {
            this.loginInfo = loginInfo;
        }

        //
        public static class LoginInfoBean {
            /**
             * uid : N0E3RTkyMkE2MTA5NDFDNUJCNjUxMTgxRTFCNTQxQTA=
             * nickname : 张晓二
             * avator : http://q.qlogo.cn/qqapp/1105849119/9FEFE532706F3A7A70644BFFBF828CF3/100
             * regtime : 2017-01-11
             */

            private String uid;
            private String nickname;
            private String avator;
            private String regtime;

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
        }
    }
}
