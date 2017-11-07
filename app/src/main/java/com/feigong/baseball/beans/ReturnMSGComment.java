package com.feigong.baseball.beans;

import java.util.List;

/**
 * Created by ruler on 17/11/4.
 *
 *
 * 评论列表
 */

public class ReturnMSGComment extends ReturnMSG {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * _id : 59fd5234f07cee3d6a653d99
         * del_flag : 0
         * message : 卧槽
         * replys : []
         * reviewer_avator : http://q.qlogo.cn/qqapp/1105849119/3EDF1E56101DE83E72B7EDD028CDC81B/100
         * reviewer_name : 漫步zhe
         * reviewer_time : 8小时前
         * reviewer_timestamp : 1509773876
         * reviewer_uid : RUE0NzQxMzEyMjFDNDgwMDg4RDU3Q0U5MzIzRTJFMDM=
         */

        private String _id;
        private int del_flag;
        private String message;
        private String reviewer_avator;
        private String reviewer_name;
        private String reviewer_time;
        private int reviewer_timestamp;
        private String reviewer_uid;
        private List<?> replys;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public int getDel_flag() {
            return del_flag;
        }

        public void setDel_flag(int del_flag) {
            this.del_flag = del_flag;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getReviewer_avator() {
            return reviewer_avator;
        }

        public void setReviewer_avator(String reviewer_avator) {
            this.reviewer_avator = reviewer_avator;
        }

        public String getReviewer_name() {
            return reviewer_name;
        }

        public void setReviewer_name(String reviewer_name) {
            this.reviewer_name = reviewer_name;
        }

        public String getReviewer_time() {
            return reviewer_time;
        }

        public void setReviewer_time(String reviewer_time) {
            this.reviewer_time = reviewer_time;
        }

        public int getReviewer_timestamp() {
            return reviewer_timestamp;
        }

        public void setReviewer_timestamp(int reviewer_timestamp) {
            this.reviewer_timestamp = reviewer_timestamp;
        }

        public String getReviewer_uid() {
            return reviewer_uid;
        }

        public void setReviewer_uid(String reviewer_uid) {
            this.reviewer_uid = reviewer_uid;
        }

        public List<?> getReplys() {
            return replys;
        }

        public void setReplys(List<?> replys) {
            this.replys = replys;
        }
    }
}
