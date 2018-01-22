package com.feigong.baseball.beans;/**
 * Created by ruler on 17/3/7.
 */

import java.util.List;

/**
 * 项目名称：baseball
 * 类名称： ReturnMSG_Channel
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：2017.03.07 15:46
 * 备注：
 *
 * @version 1.0
 */
public class ReturnMSG_Channel extends ReturnMSG{


    /**
     * data : {"channels":[{"d_code":"c01","d_name":"推荐"},{"d_code":"c02","d_name":"娱乐"},{"d_code":"c03","d_name":"MLB"},{"d_code":"c04","d_name":"教学"},{"d_code":"c06","d_name":"动漫"},{"d_code":"c07","d_name":"器材"}],"version":1}
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
         * channels : [{"d_code":"c01","d_name":"推荐"},{"d_code":"c02","d_name":"娱乐"},{"d_code":"c03","d_name":"MLB"},{"d_code":"c04","d_name":"教学"},{"d_code":"c06","d_name":"动漫"},{"d_code":"c07","d_name":"器材"}]
         * version : 1
         */

        private int version;
        private List<ChannelsBean> channels;

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public List<ChannelsBean> getChannels() {
            return channels;
        }

        public void setChannels(List<ChannelsBean> channels) {
            this.channels = channels;
        }

        public static class ChannelsBean {
            /**
             * d_code : c01
             * d_name : 推荐
             */

            private String d_code;
            private String d_name;

            public String getD_code() {
                return d_code;
            }

            public void setD_code(String d_code) {
                this.d_code = d_code;
            }

            public String getD_name() {
                return d_name;
            }

            public void setD_name(String d_name) {
                this.d_name = d_name;
            }


            @Override
            public String toString() {
                return d_name;
            }
        }
    }
}
