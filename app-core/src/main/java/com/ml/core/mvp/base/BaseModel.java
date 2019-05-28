package com.ml.core.mvp.base;

import android.database.Observable;
import android.support.annotation.NonNull;

import com.ml.core.mvp.listener.HttpResponseListener;

/**
 * Created by zhangyajun on 2019/3/19.
 */

public class BaseModel {

    /**
     * 发送网络请求
     *
     * @param observable
     * @param callback
     * @param <T>
     */
    protected <T> void sendRequest(@NonNull Observable<T> observable, HttpResponseListener<T> callback) {

    }

    /**
     * 发送网络请求
     *
     * @param tag
     * @param observable
     * @param callback
     * @param <T>
     */
    private <T> void sendRequest(@NonNull Object tag, @NonNull Observable<T> observable, HttpResponseListener callback) {

    }


}
