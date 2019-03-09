package com.feigong.baseball.fragment;

import android.os.Bundle;
import android.view.View;

import com.feigong.baseball.Interface.BaseInterFaceListenerText;
import com.feigong.baseball.R;
import com.feigong.baseball.adapter.CommentAdapter;
import com.feigong.baseball.application.App;
import com.feigong.baseball.base.BaseFragment;
import com.feigong.baseball.beans.ReturnMSG;
import com.feigong.baseball.beans.ReturnMSGComment;
import com.feigong.baseball.common.Constant;
import com.feigong.baseball.common.GetUrl;
import com.feigong.baseball.dialog.BottomDialogFragment;
import com.google.gson.Gson;
import com.ml.core.util.L;
import com.ml.core.util.SPUtils;
import com.ml.core.util.T;
import com.ml.core.util.ViewUtil;
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

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * 评论列表
 */

public class CommentFragment extends BaseFragment {

    private static final String TAG = "CommentFragment";

    private BottomDialogFragment bottomDialogFragment;

    private PullToLoadRecyclerView pullToLoadRecyclerView;

    private CommentAdapter commentAdapter;
    private ArrayList<ReturnMSGComment.DataBean>  datalist;

    private View tv_comment;

    private ReturnMSGComment returnMSGComment;

    private String objid_ref;


    public static CommentFragment newInstance(String objid_ref) {
        CommentFragment newFragment = new CommentFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.OBJID_REF, objid_ref);
        newFragment.setArguments(bundle);

        return newFragment;
    }


    //获取评论返回状态码
    class MyStringCallback extends StringCallback {

        @Override
        public void onError(Call call, Exception e, int id) {
            T.showShort(App.getContext(), R.string.comments_to_failure_please_try_again_later);
        }

        @Override
        public void onResponse(String response, int id) {
            L.e(TAG,response);
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
                    returnMSGComment = new Gson().fromJson(response,ReturnMSGComment.class);
                    if(returnMSGComment!=null && returnMSGComment.getCode()==Constant.FGCode.OpOk_code){
                        if(returnMSGComment.getData()!=null && returnMSGComment.getData().size()>0){
                            datalist.clear();
                            datalist.addAll(returnMSGComment.getData());
                        }
                        commentAdapter.notifyDataSetChanged();
                        pullToLoadRecyclerView.completeRefresh();
                    }

                    break;

                case 712:
                    returnMSGComment =  new Gson().fromJson(response,ReturnMSGComment.class);
                    if(returnMSGComment!=null && returnMSGComment.getCode()==Constant.FGCode.OpOk_code){
                        if(returnMSGComment.getData()!=null && returnMSGComment.getData().size()>0){
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
        return R.layout.fragment_comment;
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
        commentAdapter.setBaseInterFaceListenerText(new BaseInterFaceListenerText() {
            @Override
            public void onClickListener(String id) {

                openDialogFragment(id,false);
            }
        });

    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        pullToLoadRecyclerView = (PullToLoadRecyclerView) view.findViewById(R.id.list_item_recycler);
        tv_comment =  (View)view.findViewById(R.id.tv_comment);
        tv_comment.setTag(objid_ref);
        tv_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = ViewUtil.getTag(v);
                openDialogFragment(id,true);
            }
        });

    }

    /**
     * 打开评论弹出框
     * @param id
     */
    private void openDialogFragment(final String id,final boolean apiFlag){
        L.e(TAG,id);
        //
        bottomDialogFragment = new BottomDialogFragment();
        bottomDialogFragment.setBaseInterFaceListener(new BaseInterFaceListenerText() {
            @Override
            public void onClickListener(String value) {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("refid", id);
                hashMap.put("message", value);
                String token = String.valueOf(SPUtils.get(App.getContext(), Constant.TOKEN, ""));
                String json = new Gson().toJson(hashMap);
                //
                String url;
                if(apiFlag){
                    url =GetUrl.postComment();
                }else {
                    url =GetUrl.postReply();
                }
                //
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
        bottomDialogFragment.show(getChildFragmentManager(), TAG);

    }


    @Override
    protected void loadData() {

        pullToLoadRecyclerView.setLayoutManager(new WZMLinearLayoutManager(WZMLinearLayoutManager.VERTICAL));
        //        设置分割线
        pullToLoadRecyclerView.addItemDecoration(new BaseItemDecoration(context, R.color.tab_select_n));
        pullToLoadRecyclerView.setAdapter(commentAdapter);
        //        设置刷新监听
        pullToLoadRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onStartRefreshing() {
                loading();
            }
        });
//        设置加载监听
        pullToLoadRecyclerView.setOnLoadListener(new OnLoadListener() {
            @Override
            public void onStartLoading(int skip) {
                loadingMore();
            }
        });
        //
        loading();
    }

    private void loading(){
        String url = GetUrl.getCommentList(objid_ref, new Date().getTime());
        OkHttpUtils.get().url(url).id(711).build().execute(new MyStringCallback());

    }

    private void loadingMore(){

        if(datalist!=null && datalist.size()>0){
            ReturnMSGComment.DataBean  dataBean  =  datalist.get(datalist.size()-1);
            if(dataBean!=null){
                String token = String.valueOf(SPUtils.get(App.getContext(), Constant.TOKEN, ""));
                String url = GetUrl.getCommentListMore(objid_ref, dataBean.getReviewer_timestamp());
                OkHttpUtils.get().url(url).id(712)
                        .addParams("unix_stamp",String.valueOf(dataBean.getReviewer_timestamp()))
                        .addHeader(Constant.TOKEN,token)
                        .build().execute(new MyStringCallback());
            }
        }
    }





}
