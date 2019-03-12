package com.ml.core.imageloader;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import com.ml.core.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Created by zhangyajun on 2019/3/12.
 */

public class ImageLoaderUtil {
    //默认
    public static final int DEFAULT = 0;
    //圆角
    public static final int CIRCLE = 1;

    /**
     * 监听网络图片加载进程
     *
     * @param url
     * @param imageView
     * @param customerImageLoadingListener
     * @param type                         1:圆角 0:原始的
     */
    public static void imageLoadingListener(String url, final ImageView imageView, int type, final CustomerImageLoadingListener customerImageLoadingListener) {

        DisplayImageOptions displayImageOptions = null;
        if (type == CIRCLE) {
            displayImageOptions = optionsCircle;
        } else {
            displayImageOptions = options;
        }
        //
        ImageLoader.getInstance().displayImage(url, imageView, displayImageOptions, new ImageLoadingListener() {

            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                customerImageLoadingListener.onLoadingComplete();
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });

    }

    /**
     * 加载网络图片
     *
     * @param url
     * @param imageView
     * @param type      1:圆角  0:原始的
     */
    public static void imageLoadingListener(String url, final ImageView imageView, int type) {
        DisplayImageOptions displayImageOptions = null;
        if (type == CIRCLE) {
            displayImageOptions = optionsCircle;
        } else {
            displayImageOptions = options;
        }
        ImageLoader.getInstance().displayImage(url, imageView, displayImageOptions);

    }


    //返回原始图片样式
    private final static DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.color.image_background)
            .showImageForEmptyUri(R.color.image_background)
            .showImageOnFail(R.color.image_background)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .considerExifParams(true)
            .build();


    //返回圆形图片样式
    private final static DisplayImageOptions optionsCircle = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.color.image_background)
            .showImageForEmptyUri(R.color.image_background)
            .showImageOnFail(R.color.image_background)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .considerExifParams(true)
            .displayer(new CircleBitmapDisplayer(Color.WHITE, 5))
            .build();


}
