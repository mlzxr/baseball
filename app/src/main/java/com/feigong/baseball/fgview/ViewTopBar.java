package com.feigong.baseball.fgview;/**
 * Created by ruler on 16/12/5.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.feigong.baseball.R;

/**
 * 项目名称：baseball
 * 类名称： ViewTopBar
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：2016.12.05 15:18
 * 备注：
 *
 * @version 1.0
 */
public class ViewTopBar extends LinearLayout {


    private ImageView iv_back,iv_commit;
    private TextView tv_title;

    public ViewTopBar(Context context) {
        super(context);
        init(context);
    }

    public ViewTopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.view_top_bar,this,true);

        iv_back =(ImageView)findViewById(R.id.iv_back);
        iv_commit =(ImageView)findViewById(R.id.iv_commit);
        tv_title =(TextView)findViewById(R.id.tv_title);

    }

    public ImageView getIv_back() {
        return iv_back;
    }

    public ViewTopBar setIv_back(ImageView iv_back) {
        this.iv_back = iv_back;
        return this;
    }

    public ImageView getIv_commit() {
        return iv_commit;
    }

    public ViewTopBar setIv_commit(ImageView iv_commit) {
        this.iv_commit = iv_commit;
        return this;
    }

    public TextView getTv_title() {
        return tv_title;
    }

    public ViewTopBar setTv_title(TextView tv_title) {
        this.tv_title = tv_title;
        return this;
    }
}
