package com.feigong.baseball.wxapi;

/**
 * 项目名称：movie
 * 类名称：
 * 类描述：
 * 创建人：zhangyajun
 * 创建时间：2015/12/3 21:47
 * 备注：
 *
 * @version 1.0
 */
public class ResultTokenWX {

    private String access_token;//接口调用凭证
    private int expires_in;//	access_token接口调用凭证超时时间，单位（秒）
    private String refresh_token;//	用户刷新access_token
    private String openid;//	授权用户唯一标识
    private String scope;//

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
