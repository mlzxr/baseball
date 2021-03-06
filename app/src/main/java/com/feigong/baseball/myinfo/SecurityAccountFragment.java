package com.feigong.baseball.myinfo;/**
 * Created by ruler on 16/12/6.
 */

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.feigong.baseball.Interface.BaseInterFaceListenerView;
import com.feigong.baseball.R;
import com.feigong.baseball.activity.home.HomeActivity;
import com.feigong.baseball.application.App;
import com.feigong.baseball.base.BaseFragment;
import com.feigong.baseball.beans.ReturnMSG;
import com.feigong.baseball.beans.ReturnMSG_UserInfo;
import com.feigong.baseball.common.AccountValidatorUtil;
import com.feigong.baseball.common.Constant;
import com.feigong.baseball.common.GetUrl;
import com.feigong.baseball.common.MethodsUtil;
import com.feigong.baseball.fgview.ViewTopBar;
import com.feigong.baseball.fgview.View_TTIII_Horizontal;
import com.feigong.baseball.fgview.View_TTI_Horizontal;
import com.feigong.baseball.fragment.InputDialogFragment;
import com.google.gson.Gson;
import com.ml.core.util.DateUtil;
import com.ml.core.util.L;
import com.ml.core.util.SPUtils;
import com.ml.core.util.T;
import com.ml.core.util.ViewUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;

/**
 * 项目名称：baseball
 * 类名称： SecurityAccountFragment
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：2016.12.06 21:48
 * 备注：帐号与安全
 *
 * @version 1.0
 */
public class SecurityAccountFragment extends BaseFragment {

    private static final String TAG="SecurityAccountFragment";

    private View_TTI_Horizontal view_tti_phone_binding;
    private View_TTIII_Horizontal view_ttiii_other_account;

    private View_TTI_Horizontal view_tti_register_time;


    private InputDialogFragment inputDialogFragment;

    private String phone,code;


    public static SecurityAccountFragment newInstance(){
        SecurityAccountFragment securityAccountFragment = new SecurityAccountFragment();
        return securityAccountFragment;
    }

    public class MyStringCallback extends StringCallback
    {
        @Override
        public void onBefore(Request request, int id)
        {
        }

        @Override
        public void onAfter(int id)
        {

        }

        @Override
        public void onError(Call call, Exception e, int id)
        {
            L.e(TAG,e.getMessage());
        }

        @Override
        public void onResponse(String response, int id)
        {
            switch (id)
            {
                case 100:
                    L.e(TAG,response);
                    ReturnMSG_UserInfo returnMSG_userInfo =  new Gson().fromJson(response,ReturnMSG_UserInfo.class);
                    if(returnMSG_userInfo!=null && returnMSG_userInfo.getCode()==Constant.FGCode.OpOk_code){
                        ReturnMSG_UserInfo.DataBean dataBean= returnMSG_userInfo.getData();
                        if(dataBean!=null){
                            view_tti_phone_binding.getCentreTextView().setText(dataBean.getLoginInfo().getMobileBind());
                            String dateStr = DateUtil.GmtToDateStrByGmtStr(dataBean.getLoginInfo().getRegtime());
                            view_tti_register_time.getCentreTextView().setText(dateStr);
                        }
                    }
                    break;

                case 101:
                    ReturnMSG returnMSG = new Gson().fromJson(response,ReturnMSG.class);
                    if(returnMSG!=null){
                        T.showShort(App.getContext(),returnMSG.getMsg());
                        //
                        inputDialogFragment = InputDialogFragment.newInstance(getString(R.string.input_phone_code));
                        inputDialogFragment.show(getChildFragmentManager(),"inputDialogFragment");
                        inputDialogFragment.setCallBackView(new BaseInterFaceListenerView() {
                            @Override
                            public void onClickListener(View view) {
                                code = ViewUtil.getEditText((EditText) view);
                                if(!TextUtils.isEmpty(code)){
                                    mobileBind(code);
                                }else {
                                    T.showShort(App.getContext(),R.string.input_phone_code);
                                }
                            }
                        });


                    }else {//
                        T.showShort(App.getContext(),R.string.send_phone_code_error);
                    }
                    break;

                case 102:
                    returnMSG = new Gson().fromJson(response,ReturnMSG.class);
                    if(returnMSG!=null && returnMSG.getCode()== Constant.FGCode.OpOk_code){
                        //绑定成功，刷新UI
                        T.showShort(App.getContext(),returnMSG.getMsg());
                        view_tti_phone_binding.getCentreTextView().setText(MethodsUtil.hidePhone(phone));

                    }else {//
                        T.showShort(App.getContext(),R.string.phone_bind_error);
                    }
                    break;


            }
        }

        @Override
        public void inProgress(float progress, long total, int id)
        {
        }
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_security_account;
    }

    @Override
    protected void initVariables() {


    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        ViewTopBar viewTopBar = (ViewTopBar)view.findViewById(R.id.viewTopBar);
        viewTopBar.getTv_title().setText(getString(R.string.security_account));
        viewTopBar.getIv_back().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        //
        View_TTI_Horizontal view_tti_avatar = (View_TTI_Horizontal)view.findViewById(R.id.view_tti_avatar);
        View_TTI_Horizontal view_tti_nickname = (View_TTI_Horizontal)view.findViewById(R.id.view_tti_nickname);
        View_TTI_Horizontal view_tti_sex = (View_TTI_Horizontal)view.findViewById(R.id.view_tti_sex);
        view_tti_phone_binding = (View_TTI_Horizontal)view.findViewById(R.id.view_tti_phone_binding);
        view_ttiii_other_account = (View_TTIII_Horizontal)view.findViewById(R.id.view_ttiii_other_account);
        view_tti_register_time = (View_TTI_Horizontal)view.findViewById(R.id.view_tti_register_time);
        //
        view_tti_avatar.getLeft_textView().setText(getString(R.string.avatar));
        view_tti_nickname.getLeft_textView().setText(getString(R.string.nickname));
        view_tti_sex.getLeft_textView().setText(getString(R.string.sex));
        view_tti_phone_binding.getLeft_textView().setText(getString(R.string.phone_binding));

        view_ttiii_other_account.getLeft_textView().setText(getString(R.string.account_binding));
        view_tti_register_time.getLeft_textView().setText(getString(R.string.register_time));
        view_tti_register_time.getRightImageView().setVisibility(View.INVISIBLE);
        //
        view_ttiii_other_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> map = new HashMap<String, Object>();
                //
                map.put(Constant.FLAG,Constant.FragmentTAG.social_fragment);
                map.put(Constant.TAG,Constant.FragmentTAG.social_fragmentTAG);
                 /*
                打开主界面
                 */
                HomeActivity homeActivity = (HomeActivity)getActivity();
                homeActivity.setLayout(map);
            }
        });
        //
        view_tti_phone_binding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                inputDialogFragment = InputDialogFragment.newInstance(getString(R.string.phone));
                inputDialogFragment.show(getChildFragmentManager(),"inputDialogFragment");
                inputDialogFragment.setCallBackView(new BaseInterFaceListenerView() {
                    @Override
                    public void onClickListener(View view) {
                        phone = ViewUtil.getEditText((EditText) view);
                        if(AccountValidatorUtil.isMobile(phone)){
                            sendPhoneCode(phone);
                        }else {
                            T.showShort(App.getContext(),R.string.input_phone_error);
                        }
                    }
                });


            }
        });
    }

    /**
     * 发送验证码
     * @param phonenumber
     */
    private void sendPhoneCode(String phonenumber) {
        String url = GetUrl.sendCheckCode(phonenumber);
        OkHttpUtils
                .get()
                .url(url)
                .id(101)
                .build()
                .execute(new MyStringCallback());

    }


    private void mobileBind(String code){
        HashMap<String,String> hashMap =new HashMap<>();
        hashMap.put("mobile",phone);
        hashMap.put("checkcode",code);

        String json = new Gson().toJson(hashMap);
        //
        String url = GetUrl.mobileBind();
        OkHttpUtils
                .postString()
                .content(json)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .url(url)
                .id(102)
                .build()
                .execute(new MyStringCallback());

    }


    @Override
    protected void loadData() {
        String token = String.valueOf(SPUtils.get(App.getContext(),Constant.TOKEN,""));
        String url = GetUrl.getUserInfoByToken();
        OkHttpUtils
                .get()
                .url(url)
                .addHeader(Constant.TOKEN,token)
                .id(100)
                .build()
                .execute(new MyStringCallback());
    }


}
