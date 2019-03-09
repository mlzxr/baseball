package com.feigong.baseball.fragment;/**
 * Created by ruler on 17/2/3.
 */

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.feigong.baseball.R;
import com.feigong.baseball.activity.HomeActivity;
import com.feigong.baseball.application.App;
import com.feigong.baseball.base.BaseFragment;
import com.feigong.baseball.beans.ReturnMSG;
import com.feigong.baseball.common.BitmapUtil;
import com.feigong.baseball.common.Constant;
import com.feigong.baseball.common.GetUrl;
import com.feigong.baseball.common.MethodsUtil;
import com.feigong.baseball.fgview.ViewTopBar;
import com.google.gson.Gson;
import com.ml.core.util.L;
import com.ml.core.util.SPUtils;
import com.ml.core.util.T;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;

/**
 * 项目名称：baseball
 * 类名称： GetPictureFragment
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：2017.02.03 15:48
 * 备注：
 *
 * @version 1.0
 */
public class GetPictureFragment extends BaseFragment {

    private static final String TAG = "GetPictureFragment";

    private ImageView iv_avator;

    private static final int TAKE_PHOTO = 8001;
    private static final int LOAD_PHOTO = 8002;
    private static final int CROP_PHOTO = 8003;

    private Uri photoUri;

    private int take_type=0;

    public static GetPictureFragment newInstance(int take_type) {
        L.e(TAG,"当前拍照类型："+take_type);
        GetPictureFragment getPictureFragment = new GetPictureFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.TAKE_PHONE_TYPE.TAKE_TYPE,Constant.TAKE_PHONE_TYPE.AVATOR);
        getPictureFragment.setArguments(bundle);
        return getPictureFragment;
    }

    public class MyStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request, int id) {
            L.e(TAG, "onBefore...");
        }

        @Override
        public void onAfter(int id) {
            L.e(TAG, "onAfter...");
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            T.showShort(App.getContext(),R.string.update_picture_error);
        }

        @Override
        public void onResponse(String response, int id) {
            L.e(TAG,response);
            //
            switch (id) {
                case 100:
                    ReturnMSG returnMSG = new Gson().fromJson(response,ReturnMSG.class);
                    if(returnMSG!=null && returnMSG.getCode()==Constant.FGCode.OpOk_code){

                        T.showShort(App.getContext(),returnMSG.getMsg());
                        //更新头像
                       // EventBus.getDefault().post(new EventData(EventCode.USERINFO,null));


                        Activity activity = getActivity();
                        if(activity instanceof HomeActivity){
                            HomeActivity homeActivity = (HomeActivity)activity;
                            homeActivity.loadAvatar(2);
                        }
                    }
                    break;

                case 101:


                    break;
            }
        }

        @Override
        public void inProgress(float progress, long total, int id) {
            L.e(TAG, "inProgress:" + progress);
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_get_picture;
    }

    @Override
    protected void initVariables() {
        context = getActivity();
        Bundle bundle = getArguments();
        if(bundle!=null){
            take_type = bundle.getInt(Constant.TAKE_PHONE_TYPE.TAKE_TYPE);
        }
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        ViewTopBar viewTopBar =(ViewTopBar)view.findViewById(R.id.viewTopBar);
        viewTopBar.getTv_title().setText(getString(R.string.my_setting));
        viewTopBar.getIv_back().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        viewTopBar.getTv_title().setText(R.string.setting_picture);
        //
        iv_avator = (ImageView) view.findViewById(R.id.iv_avator);
       // ViewGroup.LayoutParams layoutParams = iv_avator.getLayoutParams();
       // layoutParams.height = ScreenUtils.getScreenWidth(App.getContext());
       // iv_avator.setLayoutParams(layoutParams);

        //拍照
        view.findViewById(R.id.tv_take_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoUri = Uri.fromFile(new File(Constant.UPLOADFILEPATH));

                Intent intent_take = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent_take.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                intent_take.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intent_take, TAKE_PHOTO);

            }
        });

        //图库
        view.findViewById(R.id.tv_take_form).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, LOAD_PHOTO);

            }
        });


    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //L.e(TAG,"onActivityResult----"+",requestCode:"+requestCode+"-----"+",resultCode:"+resultCode);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == TAKE_PHOTO) {
                //获取拍照照片，进行裁剪
                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(photoUri, "image/**");
                intent.putExtra("scale", true);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intent, CROP_PHOTO);

            } else if (data != null && requestCode == LOAD_PHOTO) {
                String picturePath = "";
                //获取相片返回
                //4.4以上
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    picturePath = geUriOnKitKat(data);
                } else {
                    picturePath = getImagePath(data.getData(), null);
                }
                //
                photoUri = Uri.fromFile(new File(Constant.UPLOADFILEPATH));

                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(Uri.fromFile(new File(picturePath)), "image/**");
                intent.putExtra("scale", true);
                intent.putExtra("noFaceDetection", true);// 取消人脸识别
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intent, CROP_PHOTO);

            } else if (requestCode == CROP_PHOTO) {
                //照片裁剪
                Bitmap bitmap = BitmapUtil.getSmallBitmap(Constant.UPLOADFILEPATH, BitmapUtil.WIDTH, BitmapUtil.HEIGHT);
                //

                updateAvator(bitmap);


            }
        }
    }

    /**
     * 获取返回的图片
     * @param bitmap
     */
    private void updateAvator(Bitmap bitmap){
        if(bitmap!=null){
            String token = String.valueOf(SPUtils.get(App.getContext(),Constant.TOKEN,""));
            String base64 = BitmapUtil.BitmapToBase64(bitmap);
            //
            if(!TextUtils.isEmpty(base64)){
                base64 ="data:image/jpg;base64,"+base64;
            }
            Map<String, String> params = new HashMap<String, String>();
            params.put("image_base64", base64);
            params.put("image_filename", MethodsUtil.getFileName()+".jpg");
            String json = new Gson().toJson(params);
           // L.e(TAG,token);
           // L.e(TAG,json);
            String url = GetUrl.AvatorModify();
            OkHttpUtils
                    .postString()
                    .addHeader(Constant.TOKEN,token)
                    .url(url)
                    .id(100)
                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                    .content(json)
                    .build()
                    .execute(new MyStringCallback());

            //上传图片成功刷新本地图片
            iv_avator.setImageBitmap(bitmap);

        }else {

            T.showShort(App.getContext(),R.string.get_picture_error);
        }
    }



    @TargetApi(Build.VERSION_CODES.KITKAT)
    private String geUriOnKitKat(Intent data) {
        String imagePath = null;

        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(getActivity(), uri)) {
            //如果啥document类型的uri，则通过document id处理
            final String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                final String type = docId.split(":")[0];//类型
                final String id = docId.split(":")[1];//解析数字格式的ID
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{id};

                //final String selection = MediaStore.Images.Media._ID + "=" + id;

                //imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
                imagePath = getDataColumn(App.getContext(),contentUri, selection, selectionArgs);
                L.e(TAG,imagePath);

            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                getImagePath(contentUri, null);

            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            //如果不是document 类型的uri ，则使用普通路径显示图片
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            imagePath = uri.getPath();
        }
        L.e(TAG, imagePath);
        return imagePath;
    }


    private String getImagePath(Uri uri, String selection) {
        String picturePath = null;
        try {
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getActivity().getContentResolver().query(uri,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();
        } catch (Exception e) {
            L.e(TAG,e.getCause().getMessage());
            picturePath = "";
        }
        return picturePath;
    }

    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

}
