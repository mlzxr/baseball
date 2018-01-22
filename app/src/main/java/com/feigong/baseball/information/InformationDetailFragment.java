package com.feigong.baseball.information;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.feigong.baseball.Interface.BaseInterFaceListenerText;
import com.feigong.baseball.R;
import com.feigong.baseball.activity.HomeActivity;
import com.feigong.baseball.adapter.CommentAdapter;
import com.feigong.baseball.application.App;
import com.feigong.baseball.base.BaseFragment;
import com.feigong.baseball.base.util.L;
import com.feigong.baseball.base.util.SPUtils;
import com.feigong.baseball.base.util.T;
import com.feigong.baseball.beans.ListImage;
import com.feigong.baseball.beans.ReturnMSG;
import com.feigong.baseball.beans.ReturnMSGComment;
import com.feigong.baseball.common.Constant;
import com.feigong.baseball.common.GetUrl;
import com.feigong.baseball.common.TAGUitl;
import com.feigong.baseball.dialog.BottomDialogFragment;
import com.feigong.baseball.fgview.ViewTopBar;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.google.gson.Gson;
import com.mrw.wzmrecyclerview.Divider.BaseItemDecoration;
import com.mrw.wzmrecyclerview.LayoutManager.WZMLinearLayoutManager;
import com.mrw.wzmrecyclerview.PullToLoad.OnLoadListener;
import com.mrw.wzmrecyclerview.PullToLoad.PullToLoadRecyclerView;
import com.mrw.wzmrecyclerview.PullToRefresh.OnRefreshListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by ruler on 17/7/5.
 * 资讯详情页h5
 */

public class InformationDetailFragment extends BaseFragment {

    private ViewTopBar viewTopBar;

    private BridgeWebView webView;

    private PullToLoadRecyclerView pullToLoadRecyclerView;
    private CommentAdapter commentAdapter;

    private ValueCallback<Uri> mUploadMessage;

    private BottomDialogFragment bottomDialogFragment;

    private ArrayList<ReturnMSGComment.DataBean> datalist;
    private ReturnMSGComment returnMSGComment;

    private String objid_ref;

    public static InformationDetailFragment newInstance(String objid_ref) {
        InformationDetailFragment informationDetailFragment = new InformationDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.OBJID_REF, objid_ref);
        informationDetailFragment.setArguments(bundle);
        return informationDetailFragment;
    }


    //获取评论返回状态码
    class MyStringCallback extends StringCallback {

        @Override
        public void onError(Call call, Exception e, int id) {
            T.showShort(App.getContext(), R.string.comments_to_failure_please_try_again_later);
        }

        @Override
        public void onResponse(String response, int id) {
            L.e(TAGUitl.INFORMATIONDETAILFRAGMENT, response);
            switch (id) {
                case 710:
                    ReturnMSG returnMSG = new Gson().fromJson(response, ReturnMSG.class);
                    if (returnMSG != null) {
                        if (returnMSG.getCode() == Constant.FGCode.OpOk_code) {
                            T.showShort(App.getContext(), R.string.comment_succeed);
                            loading();
                        } else {
                            T.showShort(App.getContext(), returnMSG.getMsg());
                        }
                    } else {
                        T.showShort(App.getContext(), R.string.comment_defeated);
                    }

                    break;

                case 711://刷新评论
                    returnMSGComment = new Gson().fromJson(response, ReturnMSGComment.class);
                    if (returnMSGComment != null && returnMSGComment.getCode() == Constant.FGCode.OpOk_code) {
                        if (returnMSGComment.getData() != null && returnMSGComment.getData().size() > 0) {
                            datalist.clear();
                            datalist.addAll(returnMSGComment.getData());
                        }
                        commentAdapter.notifyDataSetChanged();
                        pullToLoadRecyclerView.completeRefresh();
                    }

                    break;

                case 712:
                    returnMSGComment = new Gson().fromJson(response, ReturnMSGComment.class);
                    if (returnMSGComment != null && returnMSGComment.getCode() == Constant.FGCode.OpOk_code) {
                        if (returnMSGComment.getData() != null && returnMSGComment.getData().size() > 0) {
                            datalist.addAll(returnMSGComment.getData());
                        }
                        commentAdapter.notifyDataSetChanged();
                        pullToLoadRecyclerView.completeLoad();
                    }

                    break;

            }
        }
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_information_detail;
    }

    @Override
    protected void initVariables() {

        context = getActivity();
        datalist = new ArrayList<>();


        Bundle bundle = getArguments();
        if (bundle != null) {
            objid_ref = bundle.getString(Constant.OBJID_REF);
        }
        commentAdapter = new CommentAdapter(context, datalist, R.layout.item_type_comment);
        //
        //View view = LayoutInflater.from(context).inflate(R.layout.web_view,null);
        webView = new BridgeWebView(getActivity().getApplicationContext());
        L.e(TAGUitl.INFORMATIONFRAGMENT, "objid_ref:" + objid_ref);

    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {

        viewTopBar = (ViewTopBar) view.findViewById(R.id.viewTopBar);
        viewTopBar.getIv_back().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
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
        webView.loadUrl(Constant.H5.ARTICLE + objid_ref + "/?from=native");


        webView.registerHandler("img_swipe", new BridgeHandler() {

            @Override
            public void handler(String data, CallBackFunction function) {
                if (!TextUtils.isEmpty(data)) {
                    ListImage listImage = new Gson().fromJson(data, ListImage.class);
                    if (listImage != null && listImage.getImg_urls() != null && listImage.getImg_urls().size() > 0) {

                        Map<String, Object> map = new HashMap<String, Object>();
                        //
                        map.put(Constant.FLAG, Constant.FragmentTAG.showWebVIewImages_fragment);
                        map.put(Constant.TAG, Constant.FragmentTAG.ShowWebVIewImagesFragmentTAG);
                        map.put(DATA, data);
                        //
                        HomeActivity homeActivity = (HomeActivity) getActivity();
                        homeActivity.setLayout(map);

                    }
                }
            }
        });


        pullToLoadRecyclerView = (PullToLoadRecyclerView) view.findViewById(R.id.list_item_recycler);


        view.findViewById(R.id.tv_comment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomDialogFragment = new BottomDialogFragment();
                bottomDialogFragment.setBaseInterFaceListener(new BaseInterFaceListenerText() {
                    @Override
                    public void onClickListener(String value) {
                        HashMap<String, String> hashMap = new HashMap<String, String>();

                        hashMap.put("refid", objid_ref);
                        hashMap.put("message", value);
                        String token = String.valueOf(SPUtils.get(App.getContext(), Constant.TOKEN, ""));
                        String json = new Gson().toJson(hashMap);

                        //
                        String url = GetUrl.postComment();
                        OkHttpUtils
                                .postString()
                                .addHeader(Constant.TOKEN, token)
                                .content(json)
                                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                                .url(url)
                                .id(710)
                                .build()
                                .execute(new MyStringCallback());

                    }
                });
                bottomDialogFragment.show(getChildFragmentManager(), TAGUitl.INFORMATIONDETAILFRAGMENT);
            }
        });


    }

    @Override
    protected void loadData() {

        pullToLoadRecyclerView.setLayoutManager(new WZMLinearLayoutManager(WZMLinearLayoutManager.VERTICAL));
        pullToLoadRecyclerView.addItemDecoration(new BaseItemDecoration(context, R.color.tab_select_n));
        pullToLoadRecyclerView.setAdapter(commentAdapter);
        pullToLoadRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onStartRefreshing() {
                loading();
            }

        });
        pullToLoadRecyclerView.setOnLoadListener(new OnLoadListener() {
            @Override
            public void onStartLoading(int skip) {
                loadingMore();
            }
        });


        WebView web_view = new WebView(getActivity());
        web_view.loadUrl("http://m.baseballsay.com/article?from=native");
        pullToLoadRecyclerView.addHeaderView(web_view);
        loading();

    }


    private void loading() {
        String url = GetUrl.getCommentList(objid_ref, new Date().getTime());
        OkHttpUtils.get().url(url).id(711).build().execute(new MyStringCallback());

    }

    private void loadingMore() {

        if (datalist != null && datalist.size() > 0) {
            ReturnMSGComment.DataBean dataBean = datalist.get(datalist.size() - 1);
            if (dataBean != null) {
                String token = String.valueOf(SPUtils.get(App.getContext(), Constant.TOKEN, ""));
                String url = GetUrl.getCommentListMore(objid_ref, dataBean.getReviewer_timestamp());
                OkHttpUtils.get().url(url).id(712)
                        .addParams("unix_stamp", String.valueOf(dataBean.getReviewer_timestamp()))
                        .addHeader(Constant.TOKEN, token)
                        .build().execute(new MyStringCallback());
            }
        }
    }


}
