package com.feigong.baseball.fragment;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.feigong.baseball.R;
import com.feigong.baseball.base.BaseFragment;
import com.feigong.baseball.beans.ListImage;
import com.google.gson.Gson;
import com.ml.core.imageloader.ImageLoaderUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by ruler on 17/7/12.
 */

public class ShowWebVIewImagesFragment extends BaseFragment {

    public static final String TAG = "ShowWebVIewImagesFragment";

    private ViewPager viewPager;
    private TextView tv_index;
    private ListImage listImage;

    public static ShowWebVIewImagesFragment newInstance(String data) {
        ShowWebVIewImagesFragment showWebVIewImagesFragment = new ShowWebVIewImagesFragment();
        Bundle bundle = new Bundle();
        bundle.putString(DATA, data);
        showWebVIewImagesFragment.setArguments(bundle);
        return showWebVIewImagesFragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_webview_images;
    }

    @Override
    protected void initVariables() {
        String data = getArguments().getString(DATA);
        if (!TextUtils.isEmpty(data)) {
            listImage = new Gson().fromJson(data, ListImage.class);
        }
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        tv_index = (TextView) view.findViewById(R.id.tv_index);

    }

    @Override
    protected void loadData() {
        if (listImage != null && listImage.getImg_urls().size() > 0) {
            ImageAdapter imageAdapter = new ImageAdapter();
            viewPager.setAdapter(imageAdapter);
            viewPager.setCurrentItem(listImage.getSelect_index());
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
//
                    tv_index.setText((position + 1) + "/" + listImage.getImg_urls().size());
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            //
            tv_index.setText((listImage.getSelect_index() + 1) + "/" + listImage.getImg_urls().size());
        }
    }


    class ImageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return listImage.getImg_urls().size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(getContext());
            String imagUrl = listImage.getImg_urls().get(position);
            ImageLoaderUtil.imageLoadingListener(imagUrl, imageView, 0);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
        }
    }


}
