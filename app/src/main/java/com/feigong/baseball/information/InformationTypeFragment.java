package com.feigong.baseball.information;/**
 * Created by ruler on 17/3/6.
 */

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.feigong.baseball.R;
import com.feigong.baseball.activity.HomeActivity;
import com.feigong.baseball.adapter.InformationTypeAdpter;
import com.feigong.baseball.base.fragment.BaseFragment;
import com.feigong.baseball.base.util.L;
import com.feigong.baseball.beans.ReturnMSG_Information;
import com.feigong.baseball.common.Constant;
import com.feigong.baseball.common.GetUrl;
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
 * 类名称： InformmationTypeFragment
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：2017.03.06 16:34
 * 备注：资讯
 *
 * @version 1.0
 */
public class InformationTypeFragment extends BaseFragment {

    private static final String TAG="InformationTypeFragment";

    private String code;

    private PullToLoadRecyclerView list_item_recycler;
    private ArrayList<ReturnMSG_Information.DataBean.ArticleListBean> datalist;

    private InformationTypeAdpter informationTypeAdpter;

    public static InformationTypeFragment newInstance(String code) {
        InformationTypeFragment newFragment = new InformationTypeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("code",code);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    public class MyStringCallback extends StringCallback {


        @Override
        public void onError(Call call, Exception e, int id) {

        }

        @Override
        public void onResponse(String response, int id) {
            L.e(TAG,response);
            switch (id)
            {
                case 601:
                    ReturnMSG_Information returnMSG_information = new Gson().fromJson(response,ReturnMSG_Information.class);
                    if(returnMSG_information!=null && returnMSG_information.getCode()== Constant.FGCode.OpOk_code){
                        List list = returnMSG_information.getData().getArticle_list();
                        datalist.addAll(list);
                    }
                    informationTypeAdpter.notifyDataSetChanged();
                    break;
            }
        }
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_information_type;
    }

    @Override
    protected void initVariables() {
        code = getArguments().getString("code");
        datalist = new ArrayList();
        context = getActivity();

        informationTypeAdpter =new InformationTypeAdpter(datalist,context);
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        list_item_recycler =(PullToLoadRecyclerView)view.findViewById(R.id.list_item_recycler);
        list_item_recycler.setLayoutManager(new LinearLayoutManager(context));
        //        设置分割线
        list_item_recycler.addItemDecoration(new BaseItemDecoration(context,R.color.silver));
        list_item_recycler.setAdapter(informationTypeAdpter);



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
        //
        list_item_recycler.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {

                ReturnMSG_Information.DataBean.ArticleListBean articleListBean = datalist.get(position);
                L.e(TAG,"getCover_mode:"+articleListBean.getCover_mode());


                Map<String,Object> map = new HashMap<String, Object>();
                //
                map.put(Constant.FLAG,Constant.FragmentTAG.informationDetail_fragment);
                map.put(Constant.TAG,Constant.FragmentTAG.InformationDetailFragmentTAG);
                map.put("objid_ref",articleListBean.get_id());
                //
                HomeActivity homeActivity = (HomeActivity)getActivity();
                homeActivity.setLayout(map);

            }
        });


    }

    @Override
    protected void loadData() {
        L.e(TAG,code);
        String url = GetUrl.ArticleRefreshByCode(code);
        OkHttpUtils
                .get()
                .url(url)
                .id(601)
                .build()
                .execute(new MyStringCallback());
    }


}
