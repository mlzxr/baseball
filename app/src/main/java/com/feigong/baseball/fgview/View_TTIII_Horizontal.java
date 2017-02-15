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
public class View_TTIII_Horizontal extends LinearLayout {

    private TextView left_textView;
    private TextView centreTextView;
    private ImageView rightImageView;

    public View_TTIII_Horizontal(Context context) {
        super(context);
        init(context);
    }

    public View_TTIII_Horizontal(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    private void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.view_ttiii_horizontal,this,true);

        left_textView =(TextView)findViewById(R.id.left_textView);

        centreTextView =(TextView)findViewById(R.id.centre_textView);

        rightImageView =(ImageView)findViewById(R.id.right_imageView);


    }

    public TextView getLeft_textView() {
        return left_textView;
    }

    public View_TTIII_Horizontal setLeft_textView(TextView left_textView) {
        this.left_textView = left_textView;
        return this;
    }

    public TextView getCentreTextView() {
        return centreTextView;
    }

    public View_TTIII_Horizontal setCentreTextView(TextView centreTextView) {
        this.centreTextView = centreTextView;
        return this;
    }

    public ImageView getRightImageView() {
        return rightImageView;
    }

    public View_TTIII_Horizontal setRightImageView(ImageView rightImageView) {
        this.rightImageView = rightImageView;
        return this;
    }
}
