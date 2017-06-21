package com.feigong.baseball.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.feigong.baseball.R;
import com.feigong.baseball.base.util.DateUtil;
import com.feigong.baseball.base.util.DensityUtils;
import com.feigong.baseball.base.util.ScreenUtils;
import com.feigong.baseball.beans.ReturnMSG_Recommend;
import com.feigong.baseball.common.ImageUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by ruler on 17/6/7.
 */

public class InformationRecommendAdaper extends RecyclerView.Adapter {


    private static final String TAG = "InformationRecommendAdaper";

    //
    private static final int TYPE_VIDEO = 2;
    //
    private static final int TYPE_ONE = 1;
    //
    private static final int TYPE_THREE = 3;


    private List<ReturnMSG_Recommend.DataBean.RecommandListBean> dataList;
    private Context context;
    private LayoutInflater layoutInflater;


    public InformationRecommendAdaper(List<ReturnMSG_Recommend.DataBean.RecommandListBean> datalist, Context context) {
        this.dataList = datalist;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        int itemType=0;

        if(position>-1){
            //
            int type =dataList.get(position).getMtype();
            int count = dataList.get(position).getCover_mode();
            //
            if(type==2){//视频
                itemType = TYPE_VIDEO;
            }else {
                if(count==3){//多图
                    itemType = TYPE_THREE;
                }else {//单图
                    itemType = TYPE_ONE;
                }
            }
        }
        return itemType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (TYPE_VIDEO == viewType){
            view = layoutInflater.inflate(R.layout.item_type_video, parent, false);
            return new VideoViewHolder(view);
        } else if (TYPE_THREE == viewType){

            view = layoutInflater.inflate(R.layout.item_type_three, parent, false);
            return new ThreeViewHolder(view);
        } else {
            view = layoutInflater.inflate(R.layout.item_type_one, parent, false);
            return new OneViewHolder(view);
        }
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //绑定数据

        ReturnMSG_Recommend.DataBean.RecommandListBean bean = dataList.get(position);

        if (bean != null) {
            if (holder instanceof VideoViewHolder) {
                VideoViewHolder videoViewHolder = (VideoViewHolder) holder;
                videoViewHolder.tv_title.setText(bean.getTitle());
                videoViewHolder.tv_name.setText(bean.getPub_name());
                videoViewHolder.tv_format_duration.setText(bean.getFormat_duration());

                long time =new Long((long)bean.getPub_time());
                videoViewHolder.tv_time.setText(DateUtil.longToString(time,DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));
                //
                if(bean.getCover()!=null && bean.getCover().size()>0){
                    String cover =bean.getCover().get(0);
                    ImageLoader.getInstance().displayImage(cover, videoViewHolder.iv_cover, ImageUtil.getImageOptions());
                }

            } else if (holder instanceof ThreeViewHolder) {
                ThreeViewHolder threeViewHolder = (ThreeViewHolder) holder;
                threeViewHolder.tv_title.setText(bean.getTitle());
                threeViewHolder.tv_name.setText(bean.getPub_name());
                long time =new Long((long)bean.getPub_time());
                threeViewHolder.tv_time.setText(DateUtil.longToString(time,DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));
                if(bean.getCover()!=null && bean.getCover().size()>0){

                    ImageLoader.getInstance().displayImage(bean.getCover().get(0), threeViewHolder.iv_cover1, ImageUtil.getImageOptions());
                    ImageLoader.getInstance().displayImage(bean.getCover().get(1), threeViewHolder.iv_cover2, ImageUtil.getImageOptions());
                    ImageLoader.getInstance().displayImage(bean.getCover().get(2), threeViewHolder.iv_cover3, ImageUtil.getImageOptions());
                }

            } else {
                OneViewHolder oneViewHolder = (OneViewHolder) holder;
                oneViewHolder.tv_title.setText(bean.getTitle());
                oneViewHolder.tv_name.setText(bean.getPub_name());
                long time =new Long((long)bean.getPub_time());
                oneViewHolder.tv_time.setText(DateUtil.longToString(time,DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));
                //
                if(bean.getCover()!=null && bean.getCover().size()>0){
                    String cover =bean.getCover().get(0);
                    ImageLoader.getInstance().displayImage(cover, oneViewHolder.iv_cover, ImageUtil.getImageOptions());
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    class VideoViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        TextView tv_name;
        TextView tv_time;
        TextView tv_format_duration;
        ImageView iv_cover;


        public VideoViewHolder(View itemView) {
            super(itemView);

            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_format_duration = (TextView) itemView.findViewById(R.id.tv_format_duration);
            iv_cover = (ImageView) itemView.findViewById(R.id.iv_cover);

            //设置图片宽高
            ViewGroup.LayoutParams layoutParams = iv_cover.getLayoutParams();
            int width = ScreenUtils.getScreenWidth(context);
            int margin = DensityUtils.dp2px(context,5)*2;

            layoutParams.height = width/16*9-margin;
            layoutParams.width = width-margin;
            iv_cover.setLayoutParams(layoutParams);

        }
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
            int margin = DensityUtils.dp2px(context,5)*2;

            layoutParams.height = width/16*9-margin;
            layoutParams.width = width-margin;
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
            int margin = DensityUtils.dp2px(context,5)*2;
            layoutParams.height = (width-margin)/3/16*9;
            layoutParams.width=(width-margin)/3-30;

            iv_cover1.setLayoutParams(layoutParams);
            iv_cover2.setLayoutParams(layoutParams);
            iv_cover2.setPadding(margin/2,0,margin/2,0);
            iv_cover3.setLayoutParams(layoutParams);

        }
    }


}
