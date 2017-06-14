package com.feigong.baseball.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.feigong.baseball.beans.ReturnMSG_Recommend;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruler on 17/6/7.
 */

public class InformationRecommendAdaper extends RecyclerView.Adapter{

    private List<ReturnMSG_Recommend.DataBean.RecommandListBean> dataList;
    private Context context;

    public InformationRecommendAdaper(List<ReturnMSG_Recommend.DataBean.RecommandListBean> datalist, Context context) {
        this.dataList = datalist;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return dataList.get(position).getMtype();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }


}
