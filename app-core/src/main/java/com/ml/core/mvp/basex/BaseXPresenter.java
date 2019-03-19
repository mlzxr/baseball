package com.ml.core.mvp.basex;

import java.lang.ref.WeakReference;

/**
 * Created by zhangyajun on 2019/3/19.
 */

public class BaseXPresenter<V extends IBaseXView> implements IBaseXPresenter {

    private WeakReference<V> viewRef;


    public BaseXPresenter(V view) {
        attchView(view);
    }

    private void attchView(V view) {
        viewRef = new WeakReference<V>(view);
    }


    /**
     * 返回当前View
     *
     * @return
     */
    public V getView() {
        return viewRef.get();
    }


    @Override
    public boolean isViewAttach() {
        if (viewRef != null && viewRef.get() != null) {
            return true;
        }
        return false;
    }

    /**
     * 解除依赖
     */
    @Override
    public void detachView() {
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }
}
