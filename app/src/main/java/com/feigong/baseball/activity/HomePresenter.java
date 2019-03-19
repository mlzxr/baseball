package com.feigong.baseball.activity;

import com.ml.core.mvp.basex.IBaseXPresenter;

/**
 * Created by zhangyajun on 2019/3/12.
 */

public class HomePresenter extends IBaseXPresenter<HomeView> {

//    private final RequestMode4 mRequestMode;
//
//    public HomePresenter() {
//        this.mRequestMode = new RequestMode4();
//    }
//
//    public void clickRequest(final String cityId){
//        //获取View
//        if(getmMvpView() != null){
//            getmMvpView().requestLoading();
//        }
//        //模拟网络延迟，可以显示出加载中
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mRequestMode.request(cityId, new Callback<WeatherBean>() {
//                    @Override
//                    public void onResponse(Call<WeatherBean> call, Response<WeatherBean> response) {
//                        //判断View是否为空
//                        if(getmMvpView() != null){
//                            getmMvpView().resultSuccess(response.body());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<WeatherBean> call, Throwable t) {
//                        if(getmMvpView() != null){
//                            getmMvpView().resultFailure(Log.getStackTraceString(t));
//                        }
//                    }
//                });
//            }
//        },1000);
//    }
//
//    public void interruptHttp(){
//        mRequestMode.interruptHttp();
//    }

}
