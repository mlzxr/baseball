package com.feigong.baseball.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.feigong.baseball.beans.ReturnMSG_Information;

import java.util.ArrayList;

/**
 * Created by ruler on 17/6/25.
 */

public class InformationTypeAdpter extends RecyclerView.Adapter {

    private static final String TAG="InformationTypeAdpter";

    private ArrayList<ReturnMSG_Information.DataBean.ArticleListBean> datalist;
    private Context context;

    public InformationTypeAdpter(ArrayList<ReturnMSG_Information.DataBean.ArticleListBean> datalist, Context context) {
        this.datalist = datalist;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return datalist.size();
    }

}
