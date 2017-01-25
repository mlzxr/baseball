package com.feigong.baseball.beans;/**
 * Created by ruler on 17/1/15.
 */

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
public class ReturnMSG {

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public ReturnMSG setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ReturnMSG setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    @Override
    public String toString() {
        return "ReturnMSG{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
