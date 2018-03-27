package com.feigong.baseball.beans;

/**
 * Created by ruler on 18/3/15.
 */

public class EventData {

    private int code;

    private Object object;

    public EventData(int code, Object object) {
        this.code = code;
        this.object = object;
    }

    public int getCode() {
        return code;
    }

    public EventData setCode(int code) {
        this.code = code;
        return this;
    }

    public Object getObject() {
        return object;
    }

    public EventData setObject(Object object) {
        this.object = object;
        return this;
    }
}
