package com.feigong.baseball.video;/**
 * Created by ruler on 17/3/6.
 */

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.feigong.baseball.R;
import com.feigong.baseball.adapter.RecyclerNormalAdapter;
import com.feigong.baseball.application.App;
import com.feigong.baseball.base.fragment.BaseFragment;
import com.feigong.baseball.base.util.DensityUtils;
import com.feigong.baseball.base.util.L;
import com.feigong.baseball.beans.ReturnMSG_Channel;
import com.feigong.baseball.beans.ReturnMSG_VideoList;
import com.feigong.baseball.beans.VideoModel;
import com.feigong.baseball.common.Constant;
import com.feigong.baseball.common.GetUrl;
import com.feigong.baseball.viewholder.RecyclerItemNormalHolder;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.GSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;

/**
 * 项目名称：baseball
 * 类名称： InformmationTypeFragment
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：2017.03.06 16:34
 * 备注：
 *
 * @version 1.0
 */
public class VideoTypeFragment extends BaseFragment {

    private static final String TAG = "VideoTypeFragment";

    private String code;

    private XRecyclerView list_item_recycler;
    private LinearLayoutManager linearLayoutManager;


    private List<ReturnMSG_VideoList.DataBean.VodListBean> dataList=null;
    boolean mFull = false;
    private RecyclerNormalAdapter recyclerNormalAdapter;

    public static VideoTypeFragment newInstance(String code) {
        VideoTypeFragment newFragment = new VideoTypeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("code", code);
        newFragment.setArguments(bundle);
        return newFragment;
    }




    public class MyStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request, int id) {
        }

        @Override
        public void onAfter(int id) {

        }

        @Override
        public void onError(Call call, Exception e, int id) {
            L.e(TAG, e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {
            switch (id) {
                case 799://刷新列表
                    L.e(TAG,"799:"+response);
                    ReturnMSG_VideoList returnMSG_videoList = new Gson().fromJson(response, ReturnMSG_VideoList.class);
                    if (returnMSG_videoList != null && returnMSG_videoList.getCode() == Constant.FGCode.OpOk_code) {
                        ReturnMSG_VideoList.DataBean dataBean = returnMSG_videoList.getData();
                        if (dataBean != null && dataBean.getVod_list() != null && dataBean.getVod_list().size() > 0) {
                            //
                            dataList.addAll(dataBean.getVod_list());
                        }
                    }
                    recyclerNormalAdapter.notifyDataSetChanged();;
                    break;
            }
        }

        @Override
        public void inProgress(float progress, long total, int id) {
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_video_type;
    }

    @Override
    protected void initVariables() {
        context = getActivity();
        code = getArguments().getString("code");
        dataList = new ArrayList<>();
        L.e(TAG, "dp2px:"+DensityUtils.dp2px(App.getContext(), (float) 200.0));
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        list_item_recycler = (XRecyclerView) view.findViewById(R.id.list_item_recycler);
        recyclerNormalAdapter = new RecyclerNormalAdapter(context, dataList);
        linearLayoutManager = new LinearLayoutManager(context);
        list_item_recycler.setLayoutManager(linearLayoutManager);
        list_item_recycler.setAdapter(recyclerNormalAdapter);
        list_item_recycler.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        list_item_recycler.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        list_item_recycler.setArrowImageView(R.mipmap.iconfont_downgrey);
        //
        list_item_recycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable(){
                    public void run() {

                        recyclerNormalAdapter.notifyDataSetChanged();
                        list_item_recycler.refreshComplete();
                    }

                }, 1000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable(){
                    public void run() {

                        list_item_recycler.loadMoreComplete();
                        recyclerNormalAdapter.notifyDataSetChanged();
                    }
                }, 1000);
            }
        });


        //
        list_item_recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {

            int firstVisibleItem, lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                //
                L.e(TAG,"firstVisibleItem:"+firstVisibleItem);
                L.e(TAG,"lastVisibleItem:"+lastVisibleItem);


                //大于0说明有播放
                if (GSYVideoManager.instance().getPlayPosition() >= 0) {
                    //当前播放的位置
                    int position = GSYVideoManager.instance().getPlayPosition();
                    L.e(TAG,"position:"+position);
                    //对应的播放列表TAG
                    if (GSYVideoManager.instance().getPlayTag().equals(RecyclerItemNormalHolder.TAG)
                            && (position < firstVisibleItem || position > lastVisibleItem)) {

                        //如果滑出去了上面和下面就是否，和今日头条一样
                        //是否全屏
                        if (!mFull) {
                            GSYVideoPlayer.releaseAllVideos();
                            recyclerNormalAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }



    @Override
    protected void loadData() {
        String url = GetUrl.vodRefreshByCode(code);
        L.e(TAG,url);
        OkHttpUtils
                .get()
                .url(url)
                .id(799)
                .build()
                .execute(new MyStringCallback());
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        if (newConfig.orientation != ActivityInfo.SCREEN_ORIENTATION_USER) {
            mFull = false;
        } else {
            mFull = true;
        }

    }




}
