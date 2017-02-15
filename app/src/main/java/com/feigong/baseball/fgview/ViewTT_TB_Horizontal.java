package com.feigong.baseball.fgview;/**
 * Created by ruler on 16/12/5.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

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
public class ViewTT_TB_Horizontal extends LinearLayout {

    private TextView left_textView;
    private TextView centreTextView;
    private ToggleButton tb_push;

    public ViewTT_TB_Horizontal(Context context) {
        super(context);
        init(context);
    }

    public ViewTT_TB_Horizontal(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    private void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.view_tti_tb_horizontal,this,true);

        left_textView =(TextView)findViewById(R.id.left_textView);

        centreTextView =(TextView)findViewById(R.id.centre_textView);

        tb_push =(ToggleButton)findViewById(R.id.tb_push);


    }

    public TextView getLeft_textView() {
        return left_textView;
    }

    public ViewTT_TB_Horizontal setLeft_textView(TextView left_textView) {
        this.left_textView = left_textView;
        return this;
    }

    public TextView getCentreTextView() {
        return centreTextView;
    }

    public ViewTT_TB_Horizontal setCentreTextView(TextView centreTextView) {
        this.centreTextView = centreTextView;
        return this;
    }

    public ToggleButton getTb_push() {
        return tb_push;
    }

    public ViewTT_TB_Horizontal setTb_push(ToggleButton tb_push) {
        this.tb_push = tb_push;
        return this;
    }
}
