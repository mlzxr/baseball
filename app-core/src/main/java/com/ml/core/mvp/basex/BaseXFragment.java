package com.ml.core.mvp.basex;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhangyajun on 2019/3/19.
 */

public abstract class BaseXFragment<P extends IBaseXPresenter> extends Fragment implements IBaseXView {

    private P presenter;

    private View parentView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        parentView = inflater.inflate(getLayoutById(), container, false);

        return parentView;
    }

    public P getPresenter() {
        if (presenter == null) {
            presenter = onBindPresenter();
        }
        if (presenter == null) {
            throw new NullPointerException("presenter 不能为空");
        }
        return presenter;
    }

    protected abstract int getLayoutById();

    public abstract P onBindPresenter();


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null)
            presenter.detachView();
    }

}
