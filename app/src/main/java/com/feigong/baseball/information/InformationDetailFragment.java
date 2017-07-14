package com.feigong.baseball.information;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.widget.Button;

import com.feigong.baseball.R;
import com.feigong.baseball.activity.HomeActivity;
import com.feigong.baseball.application.App;
import com.feigong.baseball.base.fragment.BaseFragment;
import com.feigong.baseball.base.util.L;
import com.feigong.baseball.base.util.T;
import com.feigong.baseball.beans.ListImage;
import com.feigong.baseball.common.Constant;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.github.lzyzsd.jsbridge.DefaultHandler;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ruler on 17/7/5.
 */

public class InformationDetailFragment extends BaseFragment {

    private static final String TAG="InformationDetailFragment";


    BridgeWebView webView;

    ValueCallback<Uri> mUploadMessage;

    private String objid_ref;

    public static InformationDetailFragment newInstance(String objid_ref) {
        InformationDetailFragment informationDetailFragment = new InformationDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("objid_ref",objid_ref);
        informationDetailFragment.setArguments(bundle);
        return informationDetailFragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_information_detail;
    }

    @Override
    protected void initVariables() {
        Bundle bundle = getArguments();
        objid_ref =  bundle.getString("objid_ref");


    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        webView=(BridgeWebView) view.findViewById(R.id.webView);

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

      //  webView.loadUrl("http://m.baseballsay.com/article?from=native");
        webView.loadUrl(Constant.H5.ARTICLE+objid_ref+"/?from=native");


        webView.registerHandler("img_swipe", new BridgeHandler() {

            @Override
            public void handler(String data, CallBackFunction function) {
                if(!TextUtils.isEmpty(data)){
                    ListImage listImage = new Gson().fromJson(data,ListImage.class);
                    if(listImage!=null && listImage.getImg_urls()!=null && listImage.getImg_urls().size()>0){

                        Map<String,Object> map = new HashMap<String, Object>();
                        //
                        map.put(Constant.FLAG,Constant.FragmentTAG.showWebVIewImages_fragment);
                        map.put(Constant.TAG,Constant.FragmentTAG.ShowWebVIewImagesFragmentTAG);
                        map.put(DATA,data);
                        //
                        HomeActivity homeActivity = (HomeActivity) getActivity();
                        homeActivity.setLayout(map);

                    }
                }
            }
        });
    }

    @Override
    protected void loadData() {

    }

}
