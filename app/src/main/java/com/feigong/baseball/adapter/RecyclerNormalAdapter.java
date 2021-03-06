package com.feigong.baseball.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feigong.baseball.R;
import com.feigong.baseball.beans.ReturnMSG_VideoList;
import com.feigong.baseball.beans.VideoModel;
import com.feigong.baseball.viewholder.RecyclerItemNormalHolder;

import java.util.List;


/**
 * Created by guoshuyu on 2017/1/9.
 */

public class RecyclerNormalAdapter extends RecyclerView.Adapter {

    private final static String TAG = "RecyclerBaseAdapter";

    private List<ReturnMSG_VideoList.DataBean.VodListBean> itemDataList = null;
    private Context context = null;


    public RecyclerNormalAdapter(Context context, List<ReturnMSG_VideoList.DataBean.VodListBean> itemDataList) {
        this.itemDataList = itemDataList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_video_item_normal, parent, false);
        final RecyclerView.ViewHolder holder = new RecyclerItemNormalHolder(context, v);
        return holder;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        RecyclerItemNormalHolder recyclerItemViewHolder = (RecyclerItemNormalHolder) holder;
        recyclerItemViewHolder.setRecyclerBaseAdapter(this);
        recyclerItemViewHolder.onBind(position, itemDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemDataList.size();
    }


    @Override
    public int getItemViewType(int position) {
        return 1;
    }

}
