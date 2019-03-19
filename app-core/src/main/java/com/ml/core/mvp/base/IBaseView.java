package com.ml.core.mvp.base;

import com.ml.core.mvp.basex.IBaseXView;

/**
 * Created by zhangyajun on 2019/3/19.
 */

public interface IBaseView extends IBaseXView {

    /**
     * 显示正在加载 view
     */
    void showLoading();

    /**
     * 关闭正在加载 view
     */
    void hideLoading();

    /**
     * 显示提示
     *
     * @param msg
     */
    void showToast(String msg);
}
