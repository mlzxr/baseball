package com.feigong.baseball.beans;

import java.util.List;

/**
 * Created by ruler on 17/6/27.
 */

public class ReturnMSG_Information extends ReturnMSG {


    /**
     * data : {"article_list":[{"_id":"593bfcd0f07cee6158abee39","refid":"NjE3NzY2Qzk4OTM1NDM3Q0EyMTE1NjEwQjM4QzJGNzM=","title":"MLB | 旧金山巨人开季惨淡","cover_url":["http://g1.baseballsay.com/MTQ5NzEwMjMxMDUxMjM0MA=/c3","http://g1.baseballsay.com/MTQ5NzEwMjM2NDc0OTE5Mw=/c3","http://g1.baseballsay.com/MTQ5NzEwMjQxNjMwNzI0Nw=/c3"],"cover_mode":2,"publisher_uid":"RUUxRDIxNUE4RUZDNDlBN0I2MzhDQTU2QkIzNjJEQ0E=","publisher_name":"杜亦天","publisher_avator":"http://q.qlogo.cn/qqapp/1105849119/1382B457A9E1AE0A360A9F0D6C7CD61C/100","publish_timestamp":1497103568,"brower_count":0,"publish_time":"2017-06-10"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ArticleListBean> article_list;

        public List<ArticleListBean> getArticle_list() {
            return article_list;
        }

        public void setArticle_list(List<ArticleListBean> article_list) {
            this.article_list = article_list;
        }

        public static class ArticleListBean {
            /**
             * _id : 593bfcd0f07cee6158abee39
             * refid : NjE3NzY2Qzk4OTM1NDM3Q0EyMTE1NjEwQjM4QzJGNzM=
             * title : MLB | 旧金山巨人开季惨淡
             * cover_url : ["http://g1.baseballsay.com/MTQ5NzEwMjMxMDUxMjM0MA=/c3","http://g1.baseballsay.com/MTQ5NzEwMjM2NDc0OTE5Mw=/c3","http://g1.baseballsay.com/MTQ5NzEwMjQxNjMwNzI0Nw=/c3"]
             * cover_mode : 2
             * publisher_uid : RUUxRDIxNUE4RUZDNDlBN0I2MzhDQTU2QkIzNjJEQ0E=
             * publisher_name : 杜亦天
             * publisher_avator : http://q.qlogo.cn/qqapp/1105849119/1382B457A9E1AE0A360A9F0D6C7CD61C/100
             * publish_timestamp : 1497103568
             * brower_count : 0
             * publish_time : 2017-06-10
             */

            private String _id;
            private String refid;
            private String title;
            private int cover_mode;
            private String publisher_uid;
            private String publisher_name;
            private String publisher_avator;
            private int publish_timestamp;
            private int brower_count;
            private String publish_time;
            private List<String> cover_url;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getRefid() {
                return refid;
            }

            public void setRefid(String refid) {
                this.refid = refid;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getCover_mode() {
                return cover_mode;
            }

            public void setCover_mode(int cover_mode) {
                this.cover_mode = cover_mode;
            }

            public String getPublisher_uid() {
                return publisher_uid;
            }

            public void setPublisher_uid(String publisher_uid) {
                this.publisher_uid = publisher_uid;
            }

            public String getPublisher_name() {
                return publisher_name;
            }

            public void setPublisher_name(String publisher_name) {
                this.publisher_name = publisher_name;
            }

            public String getPublisher_avator() {
                return publisher_avator;
            }

            public void setPublisher_avator(String publisher_avator) {
                this.publisher_avator = publisher_avator;
            }

            public int getPublish_timestamp() {
                return publish_timestamp;
            }

            public void setPublish_timestamp(int publish_timestamp) {
                this.publish_timestamp = publish_timestamp;
            }

            public int getBrower_count() {
                return brower_count;
            }

            public void setBrower_count(int brower_count) {
                this.brower_count = brower_count;
            }

            public String getPublish_time() {
                return publish_time;
            }

            public void setPublish_time(String publish_time) {
                this.publish_time = publish_time;
            }

            public List<String> getCover_url() {
                return cover_url;
            }

            public void setCover_url(List<String> cover_url) {
                this.cover_url = cover_url;
            }
        }
    }
}
