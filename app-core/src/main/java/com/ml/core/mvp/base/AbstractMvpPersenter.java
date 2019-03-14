package com.ml.core.mvp.base;

/**
 * Created by zhangyajun on 2019/3/12.
 * 指定绑定的View必须继承自IMvpBaseView
 */

public abstract class AbstractMvpPersenter<V> {

    private V mvpView;

    /**
     * 绑定V层
     *
     * @param view
     */
    public void attachMvpView(V view) {
        this.mvpView = view;
    }

    /**
     * 解除绑定V层
     */
    public void detachMvpView() {
        mvpView = null;
    }

    /**
     * 获取V层
     *
     * @return
     */
    public V getMvpView() {
        return mvpView;
    }

}
