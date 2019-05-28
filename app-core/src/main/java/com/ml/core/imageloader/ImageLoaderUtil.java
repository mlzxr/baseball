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

    /**
     * 加载图片
     *
     * @param url
     * @param imageView
     */
    public static void imageLoadingDefault(String url, final ImageView imageView) {

        ImageLoader.getInstance().displayImage(url, imageView, options);

    }

    /**
     * 加载图片，监听过程
     *
     * @param url
     * @param imageView
     * @param customerImageLoadingListener
     */
    public static void imageLoadingDefaultListener(String url, final ImageView imageView, final CustomerImageLoadingListener customerImageLoadingListener) {

        //
        ImageLoader.getInstance().displayImage(url, imageView, options, new ImageLoadingListener() {

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
     * 加载圆角图片
     *
     * @param url
     * @param imageView
     */
    public static void imageLoadingCircle(String url, final ImageView imageView) {

        ImageLoader.getInstance().displayImage(url, imageView, options);

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
