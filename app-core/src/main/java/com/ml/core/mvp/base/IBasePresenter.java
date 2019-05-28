package com.ml.core.mvp.base;

import com.ml.core.mvp.basex.IBaseXPresenter;

/**
 * Created by zhangyajun on 2019/3/19.
 */

public interface IBasePresenter extends IBaseXPresenter {

    /**
     * 取消网络请求
     *
     * @param tag 网络请求标记
     */
    void cancel(Object tag);

    /**
     * 取消所有的网络请求
     */
    void cancelAll();
}
