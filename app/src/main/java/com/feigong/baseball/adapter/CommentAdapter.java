package com.feigong.baseball.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.feigong.baseball.R;
import com.feigong.baseball.base.util.T;
import com.feigong.baseball.beans.ReturnMSGComment;
import com.feigong.baseball.common.ImageUtil;
import com.feigong.baseball.viewholder.RecyclerItemNormalHolder;
import com.mrw.wzmrecyclerview.SimpleAdapter.SimpleAdapter;
import com.mrw.wzmrecyclerview.SimpleAdapter.ViewHolder;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by ruler on 17/8/2.
 * <p>
 * 评论适配器
 */

public class CommentAdapter extends RecyclerView.Adapter{

    private Context context;

    private ArrayList<ReturnMSGComment.DataBean> datalist;



    public CommentAdapter(Context context, ArrayList<ReturnMSGComment.DataBean> datalist, int item_type_comment) {
        this.context = context;
        this.datalist = datalist;
    }


    @Override
    public int getItemCount() {
        return datalist.size();
    }


    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_type_comment, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ReturnMSGComment.DataBean dataBean  = datalist.get(position);
        if(dataBean!=null){
            CommentViewHolder commentViewHolder =  (CommentViewHolder) holder;
            String avator = dataBean.getReviewer_avator();
            if(!TextUtils.isEmpty(avator)){
                ImageLoader.getInstance().displayImage(avator, commentViewHolder.iv_avatar, ImageUtil.getImageOptionsCircle());
            }
            commentViewHolder.tv_title.setText(dataBean.getReviewer_name());
            commentViewHolder.iv_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            commentViewHolder.tv_count.setText("");
            commentViewHolder.iv_comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            commentViewHolder.tv_comment_text.setText(dataBean.getMessage());
            commentViewHolder.ll_reply_list.removeAllViews();
            //评论回复
            if(dataBean.getReplys()!=null && dataBean.getReplys().size()>0){


            }
        }
    }

    class CommentViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_avatar;
        private TextView tv_title;
        private ImageView iv_like;
        private TextView tv_count;
        private ImageView iv_comment;

        private TextView tv_comment_text;
        private LinearLayout ll_reply_list;


        public CommentViewHolder(View view) {
            super(view);

            iv_avatar = (ImageView) view.findViewById(R.id.iv_avatar);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            iv_like = (ImageView) view.findViewById(R.id.iv_like);
            tv_count = (TextView) view.findViewById(R.id.tv_count);
            iv_comment = (ImageView) view.findViewById(R.id.iv_comment);

            tv_comment_text = (TextView) view.findViewById(R.id.tv_comment_text);
            ll_reply_list =(LinearLayout)view.findViewById(R.id.ll_reply_list);

        }



    }
}