package com.ml.core.mvp.basex;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by zhangyajun on 2019/3/12.
 */

public abstract class BaseXActivity<P extends IBaseXPresenter> extends AppCompatActivity implements IBaseXView {

    private P presenter;

    public abstract P onBindPresenter();


    public P getPresenter() {
        if (presenter == null) {
            presenter = onBindPresenter();
        }
        if (presenter == null) {
            throw new NullPointerException("presenter 不能为空");
        }
        return presenter;
    }

    @Override
    public Activity getSelfActivity() {
        return this;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null)
            presenter.detachView();
    }
}
