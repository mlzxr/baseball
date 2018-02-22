package com.feigong.baseball.fgview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.feigong.baseball.R;

/**
 * Created by ruler on 18/2/22.
 *
 * 评论回复
 *
 */

public class View_TT_Horizontal extends LinearLayout {



    private TextView tv_replyer_name,tv_message;

    public View_TT_Horizontal(Context context) {
        super(context);
        init(context);
    }

    public View_TT_Horizontal(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private  void  init(Context context){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        layoutInflater.inflate(R.layout.myview_comment_reply, this, true);
        tv_replyer_name =(TextView)findViewById(R.id.tv_replyer_name);
        tv_message =(TextView)findViewById(R.id.tv_message);
    }



    public TextView getTv_replyer_name() {
        return tv_replyer_name;
    }

    public TextView getTv_message() {
        return tv_message;
    }
}
