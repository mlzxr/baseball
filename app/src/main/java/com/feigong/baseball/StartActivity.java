package com.feigong.baseball;

import com.ml.core.mvp.base.AbstractMvpActivity;

/**
 * Created by zhangyajun on 2019/3/13.
 */

public class StartActivity extends AbstractMvpActivity<StartView,StartPresenter> implements StartView{



    @Override
    protected StartPresenter createPresenter() {
        return new StartPresenter();
    }

}
