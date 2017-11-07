package com.feigong.baseball.information;/**
 * Created by ruler on 17/3/6.
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.feigong.baseball.R;
import com.feigong.baseball.activity.HomeActivity;
import com.feigong.baseball.adapter.InformationRecommendAdaper;
import com.feigong.baseball.base.fragment.BaseFragment;
import com.feigong.baseball.base.util.L;
import com.feigong.baseball.beans.ReturnMSG_Recommend;
import com.feigong.baseball.common.Constant;
import com.feigong.baseball.common.GetUrl;
import com.feigong.baseball.video.VideoDetailActivity;
import com.google.gson.Gson;
import com.mrw.wzmrecyclerview.Divider.BaseItemDecoration;
import com.mrw.wzmrecyclerview.HeaderAndFooter.OnItemClickListener;
import com.mrw.wzmrecyclerview.PullToLoad.OnLoadListener;
import com.mrw.wzmrecyclerview.PullToLoad.PullToLoadRecyclerView;
import com.mrw.wzmrecyclerview.PullToRefresh.OnRefreshListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * 项目名称：baseball
 * 类名称： InformationRecommendFragment
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：2017.03.06 16:34
 * 备注：推荐
 *
 * @version 1.0
 */
public class InformationRecommendFragment extends BaseFragment {

    private static final String TAG = "InformationRecommendFragment";

    private PullToLoadRecyclerView list_item_recycler;
    private LinearLayoutManager linearLayoutManager;
    private InformationRecommendAdaper informationRecommendAdaper;

    private List<ReturnMSG_Recommend.DataBean.RecommandListBean> dataList;


    public static InformationRecommendFragment newInstance() {
        InformationRecommendFragment newFragment = new InformationRecommendFragment();
        return newFragment;
    }

    public class MyStringCallback extends StringCallback{

        @Override
        public void onError(Call call, Exception e, int id) {

        }

        @Override
        public void onResponse(String response, int id) {
            switch (id)
            {
                case 610:
                    ReturnMSG_Recommend returnMSG_recommend = new Gson().fromJson(response,ReturnMSG_Recommend.class);
                    if(returnMSG_recommend!=null && returnMSG_recommend.getCode()== Constant.FGCode.OpOk_code){
                        List list = returnMSG_recommend.getData().getRecommand_list();
                        dataList.addAll(list);
                    }
                    informationRecommendAdaper.notifyDataSetChanged();
                    break;
            }
        }
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_information_recommend;
    }

    @Override
    protected void initVariables() {
        dataList = new ArrayList();
        context = getActivity();
        //
        informationRecommendAdaper = new InformationRecommendAdaper(dataList, context);

    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        list_item_recycler = (PullToLoadRecyclerView) view.findViewById(R.id.list_item_recycler);
        list_item_recycler.setLayoutManager(new LinearLayoutManager(context));
        //        设置分割线
        list_item_recycler.addItemDecoration(new BaseItemDecoration(context,R.color.silver));
        list_item_recycler.setAdapter(informationRecommendAdaper);


        list_item_recycler.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onStartRefreshing() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list_item_recycler.completeRefresh();
                    }
                }, 1000);
            }
        });
        list_item_recycler.setOnLoadListener(new OnLoadListener() {
            @Override
            public void onStartLoading(int skip) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list_item_recycler.completeLoad();
                    }
                }, 1000);
            }
        });
        list_item_recycler.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                ReturnMSG_Recommend.DataBean.RecommandListBean bean = dataList.get(position);

                L.e(TAG,"getCover_mode:"+bean.getCover_mode());
                L.e(TAG,"getMtype:"+bean.getMtype());

                Map<String,Object> map = new HashMap<String, Object>();
                if(bean.getMtype()==2){//视频
                    //
                    Bundle bundle =new Bundle();
                    bundle.putString("objid_ref",bean.getObjid_ref());
                    //
                    Intent intent =new Intent();
                    intent.putExtras(bundle);
                    intent.setClass(context,VideoDetailActivity.class);
                    startActivity(intent);

                }else{

                    //
                    map.put(Constant.FLAG,Constant.FragmentTAG.informationDetail_fragment);
                    map.put(Constant.TAG,Constant.FragmentTAG.InformationDetailFragmentTAG);
                    map.put("objid_ref",bean.getObjid_ref());

                }
                //
                HomeActivity homeActivity = (HomeActivity)getActivity();
                homeActivity.setLayout(map);
            }
        });



    }

    @Override
    protected void loadData() {
        String url = GetUrl.informationRecommend();
        OkHttpUtils
                .get()
                .url(url)
                .id(610)
                .build()
                .execute(new MyStringCallback());
    }
}
