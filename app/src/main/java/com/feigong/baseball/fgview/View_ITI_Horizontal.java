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
 * 类名称： View_ITI_Horizontal
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：2016.12.05 12:08
 * 备注：
 *
 * @version 1.0
 */
public class View_ITI_Horizontal extends LinearLayout {

    private ImageView leftImageView;
    private TextView centreTextView;
    private ImageView rightImageView;

    public View_ITI_Horizontal(Context context) {
        super(context);
        init(context);
    }

    public View_ITI_Horizontal(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    private void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.view_iti_horizontal,this,true);

        leftImageView =(ImageView)findViewById(R.id.left_imageView);

        centreTextView =(TextView)findViewById(R.id.centre_textView);

        rightImageView =(ImageView)findViewById(R.id.right_imageView);

    }

    public ImageView getLeftImageView() {
        return leftImageView;
    }

    public View_ITI_Horizontal setLeftImageView(ImageView leftImageView) {
        this.leftImageView = leftImageView;
        return this;
    }

    public TextView getCentreTextView() {
        return centreTextView;
    }

    public View_ITI_Horizontal setCentreTextView(TextView centreTextView) {
        this.centreTextView = centreTextView;
        return this;
    }

    public ImageView getRightImageView() {
        return rightImageView;
    }

    public View_ITI_Horizontal setRightImageView(ImageView rightImageView) {
        this.rightImageView = rightImageView;
        return this;
    }
}
