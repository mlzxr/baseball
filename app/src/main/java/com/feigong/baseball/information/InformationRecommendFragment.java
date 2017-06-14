package com.feigong.baseball.information;/**
 * Created by ruler on 17/3/6.
 */

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.feigong.baseball.R;
import com.feigong.baseball.adapter.InformationRecommendAdaper;
import com.feigong.baseball.base.fragment.BaseFragment;
import com.feigong.baseball.base.util.L;
import com.feigong.baseball.beans.ReturnMSG_Recommend;
import com.feigong.baseball.common.Constant;
import com.feigong.baseball.common.GetUrl;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 项目名称：baseball
 * 类名称： InformationRecommendFragment
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：2017.03.06 16:34
 * 备注：
 *
 * @version 1.0
 */
public class InformationRecommendFragment extends BaseFragment {

    private static final String TAG = "InformationRecommendFragment";

    private XRecyclerView list_item_recycler;
    private LinearLayoutManager linearLayoutManager;
    private InformationRecommendAdaper informationRecommendAdaper;

    private List<ReturnMSG_Recommend.DataBean.RecommandListBean> dataList;


    public static InformationRecommendFragment newInstance() {
        InformationRecommendFragment newFragment = new InformationRecommendFragment();
        return newFragment;
    }

    public class MyStringCallback extends StringCallback{


        @Override
        public void onError(Call call, Exception e, int id) {

        }

        @Override
        public void onResponse(String response, int id) {
            switch (id)
            {
                case 610:
                    ReturnMSG_Recommend returnMSG_recommend = new Gson().fromJson(response,ReturnMSG_Recommend.class);
                    if(returnMSG_recommend!=null && returnMSG_recommend.getCode()== Constant.FGCode.OpOk_code){
                        dataList = returnMSG_recommend.getData().getRecommand_list();


                    }


                    break;
            }
        }
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_information_recommend;
    }

    @Override
    protected void initVariables() {
        dataList = new ArrayList();
        context = getActivity();

    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        list_item_recycler = (XRecyclerView) view.findViewById(R.id.list_item_recycler);
        informationRecommendAdaper = new InformationRecommendAdaper(dataList, context);
        linearLayoutManager = new LinearLayoutManager(context);
        list_item_recycler.setLayoutManager(linearLayoutManager);
        list_item_recycler.setAdapter(informationRecommendAdaper);
        list_item_recycler.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        list_item_recycler.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        list_item_recycler.setArrowImageView(R.mipmap.iconfont_downgrey);
        //
        list_item_recycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable(){
                    public void run() {

                        informationRecommendAdaper.notifyDataSetChanged();
                        list_item_recycler.refreshComplete();
                    }

                }, 1000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable(){
                    public void run() {

                        list_item_recycler.loadMoreComplete();
                        informationRecommendAdaper.notifyDataSetChanged();
                    }
                }, 1000);
            }
        });



    }

    @Override
    protected void loadData() {
        String url = GetUrl.informationRecommend();
        OkHttpUtils
                .get()
                .url(url)
                .id(610)
                .build()
                .execute(new MyStringCallback());
    }
}
