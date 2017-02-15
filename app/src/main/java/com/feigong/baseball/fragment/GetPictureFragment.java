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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.feigong.baseball.R;
import com.feigong.baseball.activity.HomeActivity;
import com.feigong.baseball.application.App;
import com.feigong.baseball.base.fragment.BaseFragment;
import com.feigong.baseball.base.util.L;
import com.feigong.baseball.base.util.ScreenUtils;
import com.feigong.baseball.common.BitmapUtil;
import com.feigong.baseball.common.Constant;
import com.feigong.baseball.fgview.ViewTopBar;

import java.io.File;

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

    public static GetPictureFragment newInstance() {
        GetPictureFragment getPictureFragment = new GetPictureFragment();
        return getPictureFragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_get_picture;
    }

    @Override
    protected void initVariables() {
        context = getActivity();
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
        ViewGroup.LayoutParams layoutParams = iv_avator.getLayoutParams();
        layoutParams.height = ScreenUtils.getScreenWidth(App.getContext());
        iv_avator.setLayoutParams(layoutParams);

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

        L.e(TAG,"onActivityResult----"+",requestCode:"+requestCode+"-----"+",resultCode:"+resultCode);

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
                iv_avator.setImageBitmap(bitmap);
                //
                Activity activity = getActivity();
                if(activity instanceof HomeActivity){
                    HomeActivity homeActivity = (HomeActivity)activity;
                    homeActivity.loadAvatar(2);
                }

            }
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
