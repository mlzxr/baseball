package com.feigong.baseball.beans;

import java.util.List;

/**
 * Created by ruler on 17/7/31.
 */

public class ReturnMSG_VideoDetail extends ReturnMSG {


    /**
     * data : {"_id":"59041649f07cee4b0d82bc92","refid":"N0U3ODRBMTQ1RTFBNDZFOUJCOTFCOTJBNTc4RUI0RUI=","title":"龙珠超比鲁斯象帕棒球大战","v_url":"http://v.baseballsay.com/RjVDMjdDRTE4MkU0NEZCNDhGQUQwRTNGREIxRDJBMUE","v_poster":"http://g1.baseballsay.com/MTQ5MzQ0MDAyOTI5Njc0Mw/c1","tags":["棒球","龙珠超"],"v_brief":"龙珠超比鲁斯象帕棒球大战","brower_count":13,"praise_count":0,"v_duration":"03:16","publisher_name":"杜亦天","publisher_avator":"http://q.qlogo.cn/qqapp/1105849119/1382B457A9E1AE0A360A9F0D6C7CD61C/100","publish_time":"2017-04-29"}
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
         * _id : 59041649f07cee4b0d82bc92
         * refid : N0U3ODRBMTQ1RTFBNDZFOUJCOTFCOTJBNTc4RUI0RUI=
         * title : 龙珠超比鲁斯象帕棒球大战
         * v_url : http://v.baseballsay.com/RjVDMjdDRTE4MkU0NEZCNDhGQUQwRTNGREIxRDJBMUE
         * v_poster : http://g1.baseballsay.com/MTQ5MzQ0MDAyOTI5Njc0Mw/c1
         * tags : ["棒球","龙珠超"]
         * v_brief : 龙珠超比鲁斯象帕棒球大战
         * brower_count : 13
         * praise_count : 0
         * v_duration : 03:16
         * publisher_name : 杜亦天
         * publisher_avator : http://q.qlogo.cn/qqapp/1105849119/1382B457A9E1AE0A360A9F0D6C7CD61C/100
         * publish_time : 2017-04-29
         */

        private String _id;
        private String refid;
        private String title;
        private String v_url;
        private String v_poster;
        private String v_brief;
        private int brower_count;
        private int praise_count;
        private String v_duration;
        private String publisher_name;
        private String publisher_avator;
        private String publish_time;
        private List<String> tags;

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

        public String getV_url() {
            return v_url;
        }

        public void setV_url(String v_url) {
            this.v_url = v_url;
        }

        public String getV_poster() {
            return v_poster;
        }

        public void setV_poster(String v_poster) {
            this.v_poster = v_poster;
        }

        public String getV_brief() {
            return v_brief;
        }

        public void setV_brief(String v_brief) {
            this.v_brief = v_brief;
        }

        public int getBrower_count() {
            return brower_count;
        }

        public void setBrower_count(int brower_count) {
            this.brower_count = brower_count;
        }

        public int getPraise_count() {
            return praise_count;
        }

        public void setPraise_count(int praise_count) {
            this.praise_count = praise_count;
        }

        public String getV_duration() {
            return v_duration;
        }

        public void setV_duration(String v_duration) {
            this.v_duration = v_duration;
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

        public String getPublish_time() {
            return publish_time;
        }

        public void setPublish_time(String publish_time) {
            this.publish_time = publish_time;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }
    }
}
