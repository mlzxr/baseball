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
         * _id : 5a8d0539f07cee2f9a13fdbb
         * reviewer_uid : MUM5QjI4M0E5QUZFNDY2NDhFN0QxOUM1REM5QTYwMEM=
         * reviewer_name : 孤星天天
         * reviewer_avator : http://q.qlogo.cn/qqapp/1105920188/8D288BF323823EBDC0AECF168C2F2468/100
         * message : nihao
         * reviewer_timestamp : 1519191353
         * replys : [{"_rid":"5a8e26110cf2f0a79330e3d1","message":"api02","praise_count":0,"replyer_uid":"MUM5QjI4M0E5QUZFNDY2NDhFN0QxOUM1REM5QTYwMEM=","replyer_name":"孤星天天","replyer_avator":"http://q.qlogo.cn/qqapp/1105920188/8D288BF323823EBDC0AECF168C2F2468/100","replyer_time":{"$date":"2018-02-22T02:08:17.027Z"},"replyer_timestamp":1519265297,"reviewer_time_format":"33分钟前"},{"_rid":"5a8e2dcd0cf2f0a79330e3d2","message":"api03","praise_count":0,"replyer_uid":"MUM5QjI4M0E5QUZFNDY2NDhFN0QxOUM1REM5QTYwMEM=","replyer_name":"孤星天天","replyer_avator":"http://q.qlogo.cn/qqapp/1105920188/8D288BF323823EBDC0AECF168C2F2468/100","replyer_time":{"$date":"2018-02-22T02:41:17.411Z"},"replyer_timestamp":1519267277,"reviewer_time_format":"1分钟前"}]
         * del_flag : 0
         * reviewer_time : 21小时前
         */

        private String _id;
        private String reviewer_uid;
        private String reviewer_name;
        private String reviewer_avator;
        private String message;
        private int reviewer_timestamp;
        private int del_flag;
        private String reviewer_time;
        private List<ReplysBean> replys;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getReviewer_uid() {
            return reviewer_uid;
        }

        public void setReviewer_uid(String reviewer_uid) {
            this.reviewer_uid = reviewer_uid;
        }

        public String getReviewer_name() {
            return reviewer_name;
        }

        public void setReviewer_name(String reviewer_name) {
            this.reviewer_name = reviewer_name;
        }

        public String getReviewer_avator() {
            return reviewer_avator;
        }

        public void setReviewer_avator(String reviewer_avator) {
            this.reviewer_avator = reviewer_avator;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getReviewer_timestamp() {
            return reviewer_timestamp;
        }

        public void setReviewer_timestamp(int reviewer_timestamp) {
            this.reviewer_timestamp = reviewer_timestamp;
        }

        public int getDel_flag() {
            return del_flag;
        }

        public void setDel_flag(int del_flag) {
            this.del_flag = del_flag;
        }

        public String getReviewer_time() {
            return reviewer_time;
        }

        public void setReviewer_time(String reviewer_time) {
            this.reviewer_time = reviewer_time;
        }

        public List<ReplysBean> getReplys() {
            return replys;
        }

        public void setReplys(List<ReplysBean> replys) {
            this.replys = replys;
        }

        public static class ReplysBean {
            /**
             * _rid : 5a8e26110cf2f0a79330e3d1
             * message : api02
             * praise_count : 0
             * replyer_uid : MUM5QjI4M0E5QUZFNDY2NDhFN0QxOUM1REM5QTYwMEM=
             * replyer_name : 孤星天天
             * replyer_avator : http://q.qlogo.cn/qqapp/1105920188/8D288BF323823EBDC0AECF168C2F2468/100
             * replyer_time : {"$date":"2018-02-22T02:08:17.027Z"}
             * replyer_timestamp : 1519265297
             * reviewer_time_format : 33分钟前
             */

            private String _rid;
            private String message;
            private int praise_count;
            private String replyer_uid;
            private String replyer_name;
            private String replyer_avator;
            private ReplyerTimeBean replyer_time;
            private int replyer_timestamp;
            private String reviewer_time_format;

            public String get_rid() {
                return _rid;
            }

            public void set_rid(String _rid) {
                this._rid = _rid;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public int getPraise_count() {
                return praise_count;
            }

            public void setPraise_count(int praise_count) {
                this.praise_count = praise_count;
            }

            public String getReplyer_uid() {
                return replyer_uid;
            }

            public void setReplyer_uid(String replyer_uid) {
                this.replyer_uid = replyer_uid;
            }

            public String getReplyer_name() {
                return replyer_name;
            }

            public void setReplyer_name(String replyer_name) {
                this.replyer_name = replyer_name;
            }

            public String getReplyer_avator() {
                return replyer_avator;
            }

            public void setReplyer_avator(String replyer_avator) {
                this.replyer_avator = replyer_avator;
            }

            public ReplyerTimeBean getReplyer_time() {
                return replyer_time;
            }

            public void setReplyer_time(ReplyerTimeBean replyer_time) {
                this.replyer_time = replyer_time;
            }

            public int getReplyer_timestamp() {
                return replyer_timestamp;
            }

            public void setReplyer_timestamp(int replyer_timestamp) {
                this.replyer_timestamp = replyer_timestamp;
            }

            public String getReviewer_time_format() {
                return reviewer_time_format;
            }

            public void setReviewer_time_format(String reviewer_time_format) {
                this.reviewer_time_format = reviewer_time_format;
            }

            public static class ReplyerTimeBean {
                /**
                 * $date : 2018-02-22T02:08:17.027Z
                 */

                private String $date;

                public String get$date() {
                    return $date;
                }

                public void set$date(String $date) {
                    this.$date = $date;
                }
            }
        }
    }
}
