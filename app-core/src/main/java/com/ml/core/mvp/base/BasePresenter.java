package com.ml.core.mvp.base;

import com.ml.core.mvp.basex.BaseXPresenter;
import com.ml.core.mvp.listener.HttpResponseListener;

/**
 * Created by zhangyajun on 2019/3/19.
 */

public abstract class BasePresenter<V extends IBaseView, T> extends BaseXPresenter<V> implements IBasePresenter, HttpResponseListener<T> {


    public BasePresenter(V view) {
        super(view);
    }


    @Override
    public void onSuccess(Object tag, T t) {

    }

    @Override
    public void onFailure(Object tag, T failure) {

    }

    @Override
    public void cancel(Object tag) {

    }

    @Override
    public void cancelAll() {

    }
}
