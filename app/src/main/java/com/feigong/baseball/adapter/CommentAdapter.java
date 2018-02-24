package com.feigong.baseball.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.feigong.baseball.Interface.BaseInterFaceListenerText;
import com.feigong.baseball.R;
import com.feigong.baseball.application.App;
import com.feigong.baseball.base.view.util.ViewUtil;
import com.feigong.baseball.beans.ReturnMSGComment;
import com.feigong.baseball.common.ImageUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;

/**
 * Created by ruler on 17/8/2.
 * <p>
 * 评论适配器
 */

public class CommentAdapter extends RecyclerView.Adapter {

    private Context context;

    private ArrayList<ReturnMSGComment.DataBean> datalist;

    private BaseInterFaceListenerText baseInterFaceListenerText;

    public CommentAdapter setBaseInterFaceListenerText(BaseInterFaceListenerText baseInterFaceListenerText) {
        this.baseInterFaceListenerText = baseInterFaceListenerText;
        return this;
    }

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
        ReturnMSGComment.DataBean dataBean = datalist.get(position);
        if (dataBean != null) {
            CommentViewHolder commentViewHolder = (CommentViewHolder) holder;
            String avator = dataBean.getReviewer_avator();
            if (!TextUtils.isEmpty(avator)) {
                ImageLoader.getInstance().displayImage(avator, commentViewHolder.iv_avatar, ImageUtil.getImageOptionsCircle());
            }
            commentViewHolder.tv_title.setText(dataBean.getReviewer_name());
            commentViewHolder.iv_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            commentViewHolder.tv_count.setText("");
            commentViewHolder.iv_comment.setTag(dataBean.get_id());
            commentViewHolder.iv_comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = ViewUtil.getTag(v);
                    baseInterFaceListenerText.onClickListener(id);
                }
            });
            commentViewHolder.tv_comment_text.setText(dataBean.getMessage());
            commentViewHolder.ll_reply_list.removeAllViews();
            //评论回复
            for (ReturnMSGComment.DataBean.ReplysBean replysBean : dataBean.getReplys()) {

                //
                String text = replysBean.getReplyer_name() + ": " + replysBean.getMessage();


                TextView textView =new TextView(context);
                textView.setText(addClickablePart(text, ""), TextView.BufferType.SPANNABLE);
                commentViewHolder.ll_reply_list.addView(textView);


            }
        }
    }

    //
    private SpannableStringBuilder addClickablePart(String text, String reply) {
        SpannableString spanStr = new SpannableString("");
        SpannableStringBuilder ssb = new SpannableStringBuilder(spanStr);
        ssb.append(text);
        String[] likeUsers = text.split(" ");
        if (likeUsers.length > 0) {
            // 最后一个
            for (int i = 0; i < likeUsers.length; i++) {
                final String name = likeUsers[i];
                final int start = text.indexOf(name) + spanStr.length();
                ssb.setSpan(new MySpan(i), start, start + name.length(), 0);
            }
        }
        return ssb.append(reply);
    }

    //回复点击
    class MySpan extends ClickableSpan {
        private int index;

        public MySpan(int index) {
            this.index = index;
        }

        @Override
        public void onClick(View view) {

            return;
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            if (index == 0) {
                ds.setColor(ContextCompat.getColor(App.getContext(), R.color.skyblue)); // 设置文本颜色
                ds.setUnderlineText(false);
            }else {
                ds.setColor(ContextCompat.getColor(App.getContext(), R.color.tab_select_n)); // 设置文本颜色
                ds.setUnderlineText(false);
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
            ll_reply_list = (LinearLayout) view.findViewById(R.id.ll_reply_list);

        }


    }
}
