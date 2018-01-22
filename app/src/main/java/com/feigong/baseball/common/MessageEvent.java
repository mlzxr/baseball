package com.feigong.baseball.common;

/**
 * Created by ruler on 18/1/19.
 */

public class MessageEvent {

    private int code;
    private String message;

    public MessageEvent(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public MessageEvent setCode(int code) {
        this.code = code;
        return this;
    }

    public MessageEvent setMessage(String message) {
        this.message = message;
        return this;
    }
}
