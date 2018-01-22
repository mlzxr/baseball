package com.feigong.baseball.common;

import android.graphics.Color;

import com.feigong.baseball.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;

/**
 * 项目名称：movie
 * 类名称：
 * 类描述：
 * 创建人：zhangyajun
 * 创建时间：2015/12/1 16:28
 * 备注：
 *
 * @version 1.0
 */
public class ImageUtil {

    //返回原始图片样式
    private final static DisplayImageOptions  options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.drawable.no_photo_bak)
            .showImageForEmptyUri(R.drawable.no_photo_bak)
            .showImageOnFail(R.drawable.no_photo_bak)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .considerExifParams(true)
            .build();
    //
    public static DisplayImageOptions getImageOptions() {
        return options;
    }


    //返回圆形图片样式
    private final static DisplayImageOptions  optionsCircle = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.drawable.no_photo_bak)
            .showImageForEmptyUri(R.drawable.no_photo_bak)
            .showImageOnFail(R.drawable.no_photo_bak)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .considerExifParams(true)
            .displayer(new CircleBitmapDisplayer(Color.WHITE, 5))
            .build();


    public static DisplayImageOptions getImageOptionsCircle(){

        return optionsCircle;
    }


}
