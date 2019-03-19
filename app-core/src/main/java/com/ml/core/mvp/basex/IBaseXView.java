package com.ml.core.mvp.basex;

import android.app.Activity;

/**
 * Created by zhangyajun on 2019/3/12.
 * 所有mvpView的父接口
 */

public interface IBaseXView {

    <T extends Activity> T getSelfActivity();

}
