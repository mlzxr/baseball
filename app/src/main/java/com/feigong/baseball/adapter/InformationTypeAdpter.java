package com.feigong.baseball.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.feigong.baseball.R;
import com.feigong.baseball.beans.ReturnMSG_Information;
import com.ml.core.imageloader.ImageLoaderUtil;
import com.ml.core.util.DateUtil;
import com.ml.core.util.DensityUtils;
import com.ml.core.util.ScreenUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by ruler on 17/6/25.
 */

public class InformationTypeAdpter extends RecyclerView.Adapter {

    private static final String TAG = "InformationTypeAdpter";

    //
    private static final int TYPE_VIDEO = 2;
    //
    private static final int TYPE_ONE = 1;
    //
    private static final int TYPE_THREE = 3;


    private ArrayList<ReturnMSG_Information.DataBean.ArticleListBean> datalist;
    private Context context;
    private LayoutInflater layoutInflater;


    public InformationTypeAdpter(ArrayList<ReturnMSG_Information.DataBean.ArticleListBean> datalist, Context context) {
        this.datalist = datalist;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getItemViewType(int position) {
        int itemType = 0;

        if (position > -1) {
            //
            int count = datalist.get(position).getCover_mode();
            if (count == 3) {//多图
                itemType = TYPE_THREE;
            } else {//单图
                itemType = TYPE_ONE;
            }
        }
        return itemType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (TYPE_THREE == viewType) {

            view = layoutInflater.inflate(R.layout.item_type_three, parent, false);
            return new ThreeViewHolder(view);
        } else {
            view = layoutInflater.inflate(R.layout.item_type_one, parent, false);
            return new OneViewHolder(view);
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ReturnMSG_Information.DataBean.ArticleListBean bean = datalist.get(position);
        if (bean != null) {
            if (holder instanceof ThreeViewHolder) {
                ThreeViewHolder threeViewHolder = (ThreeViewHolder) holder;
                threeViewHolder.tv_title.setText(bean.getTitle());
                threeViewHolder.tv_name.setText(bean.getPublish_time());
                long time = new Long((long) bean.getPublish_timestamp());
                threeViewHolder.tv_time.setText(DateUtil.longToString(time, DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));
                if (bean.getCover_url() != null && bean.getCover_url().size() > 0) {


                    ImageLoaderUtil.imageLoadingDefault(bean.getCover_url().get(0), threeViewHolder.iv_cover1);
                    ImageLoaderUtil.imageLoadingDefault(bean.getCover_url().get(1), threeViewHolder.iv_cover2);
                    ImageLoaderUtil.imageLoadingDefault(bean.getCover_url().get(2), threeViewHolder.iv_cover3);
                }

            } else {
                OneViewHolder oneViewHolder = (OneViewHolder) holder;
                oneViewHolder.tv_title.setText(bean.getTitle());
                oneViewHolder.tv_name.setText(bean.getPublish_time());
                long time = new Long((long) bean.getPublish_timestamp());
                oneViewHolder.tv_time.setText(DateUtil.longToString(time, DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));
                //
                if (bean.getCover_url() != null && bean.getCover_url().size() > 0) {
                    String cover = bean.getCover_url().get(0);
                    ImageLoaderUtil.imageLoadingDefault(cover, oneViewHolder.iv_cover);
                }
            }
        }


    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getItemCount() {
        return datalist.size();
    }


    class OneViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        TextView tv_name;
        TextView tv_time;
        ImageView iv_cover;

        public OneViewHolder(View itemView) {
            super(itemView);

            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            iv_cover = (ImageView) itemView.findViewById(R.id.iv_cover);

            //设置图片宽高
            ViewGroup.LayoutParams layoutParams = iv_cover.getLayoutParams();
            int width = ScreenUtils.getScreenWidth(context);
            int margin = DensityUtils.dp2px(context, 5) * 2;

            layoutParams.height = width / 16 * 9 - margin;
            layoutParams.width = width - margin;
            iv_cover.setLayoutParams(layoutParams);
        }
    }

    class ThreeViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        TextView tv_name;
        TextView tv_time;
        ImageView iv_cover1;
        ImageView iv_cover2;
        ImageView iv_cover3;

        public ThreeViewHolder(View itemView) {
            super(itemView);

            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            iv_cover1 = (ImageView) itemView.findViewById(R.id.iv_cover1);
            iv_cover2 = (ImageView) itemView.findViewById(R.id.iv_cover2);
            iv_cover3 = (ImageView) itemView.findViewById(R.id.iv_cover3);
            //
            //设置图片宽高
            ViewGroup.LayoutParams layoutParams = iv_cover1.getLayoutParams();
            int width = ScreenUtils.getScreenWidth(context);
            int margin = DensityUtils.dp2px(context, 5) * 2;
            layoutParams.height = (width - margin) / 3 / 16 * 9;
            layoutParams.width = (width - margin) / 3 - 30;

            iv_cover1.setLayoutParams(layoutParams);
            iv_cover2.setLayoutParams(layoutParams);
            iv_cover2.setPadding(margin / 2, 0, margin / 2, 0);
            iv_cover3.setLayoutParams(layoutParams);

        }
    }


}
