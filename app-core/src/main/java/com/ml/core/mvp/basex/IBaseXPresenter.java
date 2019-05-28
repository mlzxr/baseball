package com.ml.core.mvp.basex;

/**
 * Created by zhangyajun on 2019/3/12.
 * 指定绑定的View必须继承自IMvpBaseView
 */

public interface IBaseXPresenter {

    /**
     * presenter 是否与 view 建立联系，防止出现内存泄露状况
     *
     * @return
     */
    boolean isViewAttach();

    /**
     * 断开 presenter 与 view 直接的联系
     */
    void detachView();
}
