package com.feigong.baseball.information;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.widget.Button;

import com.feigong.baseball.R;
import com.feigong.baseball.base.fragment.BaseFragment;
import com.feigong.baseball.base.util.L;
import com.feigong.baseball.myinfo.SocialFragment;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.github.lzyzsd.jsbridge.DefaultHandler;
import com.google.gson.Gson;

/**
 * Created by ruler on 17/7/5.
 */

public class InformationDetailFragment extends BaseFragment {

    private static final String TAG="InformationDetailFragment";


    BridgeWebView webView;

    Button button;

    int result_code=0;

    ValueCallback<Uri> mUploadMessage;





    public static InformationDetailFragment newInstance() {
        InformationDetailFragment informationDetailFragment = new InformationDetailFragment();
        return informationDetailFragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_information_detail;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        webView=(BridgeWebView) view.findViewById(R.id.webView);

        button=(Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        webView.setDefaultHandler(new DefaultHandler());

        webView.setWebChromeClient(new WebChromeClient(){
            @SuppressWarnings("unused")
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String AcceptType, String capture) {
                this.openFileChooser(uploadMsg);
            }

            @SuppressWarnings("unused")
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String AcceptType) {
                this.openFileChooser(uploadMsg);
            }

            public void openFileChooser(ValueCallback<Uri> uploadMsg) {
                mUploadMessage = uploadMsg;

            }
        });

        webView.loadUrl("http://m.baseballsay.com/article?from=native");

        webView.registerHandler("img_swipe", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                L.e(TAG,"handler = img_swipe, data from web = " + data);
                function.onCallBack("img_swipe exe, response data 中文 from Java");
            }
        });

        User user = new User();
        Location location = new Location();
        location.address = "SDU";
        user.location = location;
        user.name = "大头鬼";

        webView.callHandler("functionInJs", new Gson().toJson(user), new CallBackFunction() {
            @Override
            public void onCallBack(String data) {

            }
        });

        webView.send("hello");


    }

    @Override
    protected void loadData() {

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == result_code) {
            if (null == mUploadMessage){
                return;
            }
            Uri result = data == null || resultCode != Activity.RESULT_OK ? null : data.getData();
            mUploadMessage.onReceiveValue(result);
            mUploadMessage = null;
        }
    }


    static class Location{
        String address;
    }

    static class User{
        String name;
        Location location;
        String testStr;
    }

}
