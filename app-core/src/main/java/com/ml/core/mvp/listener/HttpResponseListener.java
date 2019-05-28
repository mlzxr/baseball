package com.ml.core.mvp.listener;

/**
 * Created by zhangyajun on 2019/3/19.
 * 网络返回监听
 *
 * @param <T>
 */
public interface HttpResponseListener<T> {

    /**
     * 网络请求成功
     *
     * @param tag 请求的标记
     * @param t   返回的数据
     */
    void onSuccess(Object tag, T t);

    /**
     * 网络请求失败
     *
     * @param tag     请求的标记
     * @param failure 请求失败时，返回的信息类
     */
    void onFailure(Object tag, T failure);
}
