package com.feigong.baseball.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.feigong.baseball.Interface.BaseInterFaceListenerView;
import com.feigong.baseball.R;

/**
 * Created by ruler on 18/2/26.
 * 输入弹出框
 *
 *
 */

public class InputDialogFragment extends DialogFragment {

    private static final String TAG="VerificationDialogFragment";

    private TextView tvTitle;
    private EditText etAccount;

    private BaseInterFaceListenerView callBackView;

    public InputDialogFragment setCallBackView(BaseInterFaceListenerView callBackView) {
        this.callBackView = callBackView;
        return this;
    }

    //
    public static InputDialogFragment newInstance(String title) {
        InputDialogFragment fragment = new InputDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        setStyle(DialogFragment.STYLE_NO_TITLE,android.R.style.Theme_Holo_Light_Dialog_MinWidth);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        String title = bundle.getString("title");
        // getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        //
        View view=inflater.inflate(R.layout.fragment_dialog_input,container,false);
        //
        tvTitle = (TextView)view.findViewById(R.id.tv_title);
        tvTitle.setText(title);
        etAccount = (EditText)view.findViewById(R.id.et_account);
        //
        view.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        view.findViewById(R.id.btn_commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBackView.onClickListener(etAccount);
                getDialog().dismiss();
            }
        });

        return view;
    }


}
