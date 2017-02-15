package com.feigong.baseball.beans;/**
 * Created by ruler on 17/2/14.
 */

/**
 * 项目名称：baseball
 * 类名称： ReturnMSG_Push
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：2017.02.14 21:27
 * 备注：
 *
 * @version 1.0
 */
public class ReturnMSG_Push extends ReturnMSG{


    /**
     * code : 10000
     * msg : 操作成功
     * data : {"push_flag":0}
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
         * push_flag : 0
         */

        private int push_flag;

        public int getPush_flag() {
            return push_flag;
        }

        public void setPush_flag(int push_flag) {
            this.push_flag = push_flag;
        }
    }
}
