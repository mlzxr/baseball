package com.feigong.baseball.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.feigong.baseball.Interface.BaseInterFaceListenerText;
import com.feigong.baseball.R;
import com.feigong.baseball.application.App;
import com.feigong.baseball.base.util.T;
import com.feigong.baseball.base.view.util.ViewUtil;

/**
 * Created by ruler on 17/11/2.
 *
 * 自定义底部输入控件
 */

public class BottomDialogFragment extends DialogFragment {

    private Button bt_commit;

    private EditText edit_text;

    private BaseInterFaceListenerText baseInterFaceListenerText;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = new Dialog(getActivity(),R.style.BottomDialog);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(R.layout.view_dialog);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消

        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        window.setAttributes(lp);



        bt_commit = (Button) dialog.findViewById(R.id.bt_comment);
        bt_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = ViewUtil.getEditText(edit_text);
                if(TextUtils.isEmpty(text)){
                    T.showShort(App.getContext(),R.string.input_not_null);
                }else {
                    baseInterFaceListenerText.onClickListener(edit_text.getText().toString());
                    dismiss();
                }
            }
        });
        edit_text =(EditText)dialog.findViewById(R.id.edit_text);
        return dialog;

    }

    public void setBaseInterFaceListener(BaseInterFaceListenerText baseInterFaceListenerText){

        this.baseInterFaceListenerText = baseInterFaceListenerText;

    };

}
