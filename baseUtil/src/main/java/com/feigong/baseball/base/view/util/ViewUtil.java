package com.feigong.baseball.base.view.util;/**
 * Created by ruler on 16/7/23.
 */

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 项目名称：cqt_app
 * 类名称： ViewUtil
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：2016.07.23 13:13
 * 备注：
 *
 * @version 1.0
 */
public class ViewUtil {

    public static String getText(TextView textView){
        if(textView!=null && textView.getText()!=null){
            return textView.getText().toString().trim();
        }
        return "";
    }

    public static String getTag(View view){
        if(view!=null && view.getTag()!=null){
            return view.getTag().toString().trim();
        }
        return "";
    }

    public static int getTagInt(View view){
        if(view!=null && view.getTag()!=null){
            return Integer.parseInt(view.getTag().toString());
        }
        return 0;
    }

    public static boolean getTagBool(View view){
        if(view!=null && view.getTag()!=null){
            return (boolean)view.getTag();
        }
        return false;
    }

    public static long getTagLong(View view){
        if(view!=null && view.getTag()!=null){
            return Long.parseLong(view.getTag().toString());
        }
        return 0;
    }

    public static String getEditText(EditText editText){
        if(editText!=null && editText.getText()!=null){
            return editText.getText().toString().trim();
        }
        return "";
    }



}
