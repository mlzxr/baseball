package com.feigong.baseball.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.feigong.baseball.R;
import com.feigong.baseball.base.fragment.BaseFragment;
import com.feigong.baseball.video.VideoDetailActivity;
import com.mrw.wzmrecyclerview.Divider.BaseItemDecoration;
import com.mrw.wzmrecyclerview.LayoutManager.WZMLinearLayoutManager;
import com.mrw.wzmrecyclerview.PullToLoad.OnLoadListener;
import com.mrw.wzmrecyclerview.PullToLoad.PullToLoadRecyclerView;
import com.mrw.wzmrecyclerview.PullToRefresh.OnRefreshListener;
import com.mrw.wzmrecyclerview.SimpleAdapter.SimpleAdapter;
import com.mrw.wzmrecyclerview.SimpleAdapter.ViewHolder;

import java.util.ArrayList;

/**
 * Created by ruler on 17/9/4.
 */

public class CommentFragment extends BaseFragment {

    private static final String TAG="CommentFragment";



    private PullToLoadRecyclerView pullToLoadRecyclerView;

    private MyAdapter myAdapter;
    private ArrayList datalist;



    public static CommentFragment newInstance() {
        CommentFragment newFragment = new CommentFragment();
        return newFragment;
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_comment;
    }

    @Override
    protected void initVariables() {
        context =getActivity();
        datalist = new ArrayList();

        myAdapter = new MyAdapter(context,datalist,R.layout.item_type_comment);
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        pullToLoadRecyclerView =(PullToLoadRecyclerView)view.findViewById(R.id.list_item_recycler);
    }

    @Override
    protected void loadData() {

        pullToLoadRecyclerView.setLayoutManager(new WZMLinearLayoutManager(WZMLinearLayoutManager.VERTICAL));
        //        设置分割线
        pullToLoadRecyclerView.addItemDecoration(new BaseItemDecoration(context,R.color.tab_select_n));
        pullToLoadRecyclerView.setAdapter(myAdapter);
        //        设置刷新监听
        pullToLoadRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onStartRefreshing() {

            }
        });
//        设置加载监听
        pullToLoadRecyclerView.setOnLoadListener(new OnLoadListener() {
            @Override
            public void onStartLoading(int skip) {

            }
        });
    }



    class MyAdapter extends SimpleAdapter {

        public MyAdapter(Context context, ArrayList datas, int layoutId) {
            super(context, datas, layoutId);
        }

        /**
         * 子类需实现以下方法
         *
         * @param holder
         * @param data
         */
        @Override
        protected void onBindViewHolder(ViewHolder holder, Object data) {

        }
    }

}
