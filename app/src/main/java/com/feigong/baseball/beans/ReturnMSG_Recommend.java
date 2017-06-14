package com.feigong.baseball.beans;/**
 * Created by ruler on 17/1/15.
 */

import java.util.List;

/**
 * 项目名称：baseball
 * 类名称： ReturnMSG
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：2017.01.15 09:34
 * 备注：
 *
 * @version 1.0
 */
public class ReturnMSG_Recommend extends ReturnMSG{


    /**
     * data : {"recommand_list":[{"_id":"5917f2bef07cee4b0d82bc99","objid_ref":"59041649f07cee4b0d82bc92","title":"龙珠超比鲁斯象帕棒球大战","cover":["http://g1.baseballsay.com/MTQ5MzQ0MDAyOTI5Njc0Mw/c1"],"duration":196,"mtype":2,"pub_avator":"http://q.qlogo.cn/qqapp/1105849119/1382B457A9E1AE0A360A9F0D6C7CD61C/100","pub_name":"杜亦天","pub_time":1493440072,"cover_mode":1},{"_id":"5917f2ccf07cee4b0d82bc9a","objid_ref":"59041dc9f07cee4b0d82bc96","title":"耐克超感人棒球励志广","cover":["http://g1.baseballsay.com/MTQ5MzQ0MTc0ODI3NzEyOQ/c1"],"duration":16,"mtype":2,"pub_avator":"http://q.qlogo.cn/qqapp/1105849119/1382B457A9E1AE0A360A9F0D6C7CD61C/100","pub_name":"杜亦天","pub_time":1493441986},{"_id":"5917f432f07cee4b0d82bc9c","objid_ref":"5917f42bf07cee4b0d82bc9b","title":"17岁女孩圆92岁老奶奶棒球梦女子职业棒球的传承","cover":["http://g1.baseballsay.com/MTQ5NDU2NzE3MDE4ODM4NQ=/c1"],"mtype":0,"pub_avator":"http://q.qlogo.cn/qqapp/1105849119/1382B457A9E1AE0A360A9F0D6C7CD61C/100","pub_name":"杜亦天","pub_time":1494742059,"cover_mode":1}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<RecommandListBean> recommand_list;

        public List<RecommandListBean> getRecommand_list() {
            return recommand_list;
        }

        public void setRecommand_list(List<RecommandListBean> recommand_list) {
            this.recommand_list = recommand_list;
        }

        public static class RecommandListBean {
            /**
             * _id : 5917f2bef07cee4b0d82bc99
             * objid_ref : 59041649f07cee4b0d82bc92
             * title : 龙珠超比鲁斯象帕棒球大战
             * cover : ["http://g1.baseballsay.com/MTQ5MzQ0MDAyOTI5Njc0Mw/c1"]
             * duration : 196
             * mtype : 2
             * pub_avator : http://q.qlogo.cn/qqapp/1105849119/1382B457A9E1AE0A360A9F0D6C7CD61C/100
             * pub_name : 杜亦天
             * pub_time : 1493440072
             * cover_mode : 1
             */

            private String _id;
            private String objid_ref;
            private String title;
            private int duration;
            private int mtype;
            private String pub_avator;
            private String pub_name;
            private int pub_time;
            private int cover_mode;
            private List<String> cover;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getObjid_ref() {
                return objid_ref;
            }

            public void setObjid_ref(String objid_ref) {
                this.objid_ref = objid_ref;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public int getMtype() {
                return mtype;
            }

            public void setMtype(int mtype) {
                this.mtype = mtype;
            }

            public String getPub_avator() {
                return pub_avator;
            }

            public void setPub_avator(String pub_avator) {
                this.pub_avator = pub_avator;
            }

            public String getPub_name() {
                return pub_name;
            }

            public void setPub_name(String pub_name) {
                this.pub_name = pub_name;
            }

            public int getPub_time() {
                return pub_time;
            }

            public void setPub_time(int pub_time) {
                this.pub_time = pub_time;
            }

            public int getCover_mode() {
                return cover_mode;
            }

            public void setCover_mode(int cover_mode) {
                this.cover_mode = cover_mode;
            }

            public List<String> getCover() {
                return cover;
            }

            public void setCover(List<String> cover) {
                this.cover = cover;
            }
        }
    }
}
