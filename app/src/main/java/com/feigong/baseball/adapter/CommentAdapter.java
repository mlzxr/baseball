package com.feigong.baseball.adapter;

import android.content.Context;

import com.mrw.wzmrecyclerview.SimpleAdapter.SimpleAdapter;
import com.mrw.wzmrecyclerview.SimpleAdapter.ViewHolder;

import java.util.ArrayList;

/**
 * Created by ruler on 17/8/2.
 */

public class CommentAdapter extends SimpleAdapter {

    public CommentAdapter(Context context, ArrayList datas, int layoutId) {
        super(context, datas, layoutId);
    }

    /**
     * 子类需实现以下方法
     *
     * @param holder
     * @param data
     */
    @Override
    protected void onBindViewHolder(ViewHolder holder, Object data) {

    }
}
