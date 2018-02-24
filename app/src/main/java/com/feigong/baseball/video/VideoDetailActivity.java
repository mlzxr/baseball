package com.feigong.baseball.video;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;

import com.feigong.baseball.R;
import com.feigong.baseball.application.App;
import com.feigong.baseball.base.BaseActivity;
import com.feigong.baseball.base.util.L;
import com.feigong.baseball.base.util.T;
import com.feigong.baseball.beans.ReturnMSG_VideoDetail;
import com.feigong.baseball.common.Constant;
import com.feigong.baseball.common.GetUrl;
import com.feigong.baseball.common.ImageUtil;
import com.feigong.baseball.fgview.LandLayoutVideo;
import com.feigong.baseball.fgview.ViewTopBar;
import com.feigong.baseball.fragment.CommentFragment;
import com.feigong.baseball.listener.SampleListener;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.GSYVideoPlayer;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by ruler on 17/7/26.
 */

public class VideoDetailActivity extends BaseActivity {

    private static final String TAG="VideoDetailActivity";

    private ViewTopBar viewTopBar;


    //推荐使用StandardGSYVideoPlayer，功能一致
    //CustomGSYVideoPlayer部分功能处于试验阶段
    LandLayoutVideo detailPlayer;

    private boolean isPlay;
    private boolean isPause;

    private OrientationUtils orientationUtils;


    private String objid_ref;

    public class MyStringCallback extends StringCallback{
        @Override
        public void onError(Call call, Exception e, int id) {

        }

        @Override
        public void onResponse(String response, int id) {
            L.e(TAG,response);
            switch (id)
            {
                case 10001:
                    ReturnMSG_VideoDetail returnMSGVideoDetail = new Gson().fromJson(response,ReturnMSG_VideoDetail.class);
                    if(returnMSGVideoDetail!=null &&  returnMSGVideoDetail.getCode()== Constant.FGCode.OpOk_code){
                        ReturnMSG_VideoDetail.DataBean bean =  returnMSGVideoDetail.getData();
                        if(bean!=null){
                            detailPlayer.setUp(bean.getV_url(), false, null, bean.getTitle());
                            detailPlayer.getTitleTextView().setText(bean.getTitle());
                            //增加封面
                            ImageView imageView = new ImageView(VideoDetailActivity.this);
                            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            ImageLoader.getInstance().displayImage(bean.getV_poster(), imageView, ImageUtil.getImageOptions());
                            detailPlayer.setThumbImageView(imageView);
                            //增加title
                            detailPlayer.getTitleTextView().setVisibility(View.GONE);
                            detailPlayer.getBackButton().setVisibility(View.GONE);
                            //
                            viewTopBar.getTv_title().setText(bean.getTitle());

                        }
                    }

                    break;
            }
        }
    }



    @Override
    protected int getLayout() {
        return R.layout.fragment_video_detail;
    }

    @Override
    protected void initVariables() {
        Bundle bundle = getIntent().getExtras();
        objid_ref = bundle.getString("objid_ref");

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        viewTopBar =(ViewTopBar)findViewById(R.id.viewTopBar);

        viewTopBar.getIv_back().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        detailPlayer =(LandLayoutVideo)findViewById(R.id.detail_player);

        //
        String url = "http://baobab.wdjcdn.com/14564977406580.mp4";
        detailPlayer.setUp(url, false, null, "");
        detailPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orientationUtils.setEnable(false);
            }
        });

        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils(this, detailPlayer);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);
        detailPlayer.setIsTouchWiget(true);
        //关闭自动旋转
        detailPlayer.setRotateViewAuto(false);
        detailPlayer.setLockLand(false);
        detailPlayer.setShowFullAnimation(false);
        detailPlayer.setNeedLockFull(true);
        detailPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接横屏
                orientationUtils.resolveByClick();

                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                detailPlayer.startWindowFullscreen(VideoDetailActivity.this, true, true);
            }
        });

        detailPlayer.setStandardVideoAllCallBack(new SampleListener() {
            @Override
            public void onPrepared(String url, Object... objects) {
                super.onPrepared(url, objects);
                //开始播放了才能旋转和全屏
                orientationUtils.setEnable(true);
                isPlay = true;
            }

            @Override
            public void onAutoComplete(String url, Object... objects) {
                super.onAutoComplete(url, objects);
            }

            @Override
            public void onClickStartError(String url, Object... objects) {
                super.onClickStartError(url, objects);
            }

            @Override
            public void onQuitFullscreen(String url, Object... objects) {
                super.onQuitFullscreen(url, objects);
                if (orientationUtils != null) {
                    orientationUtils.backToProtVideo();
                }
            }
        });

        detailPlayer.setLockClickListener(new LockClickListener() {
            @Override
            public void onClick(View view, boolean lock) {
                if (orientationUtils != null) {
                    //配合下方的onConfigurationChanged
                    orientationUtils.setEnable(!lock);
                }
            }
        });

        //评论区域
        CommentFragment commentFragment = CommentFragment.newInstance(objid_ref);
        //
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frameLayout, commentFragment,"CommentFragment");
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();


    }

    @Override
    protected void initData() {
        String url = GetUrl.getVideoDetailById(objid_ref);
    }






    //
    @Override
    protected void onPause() {
        super.onPause();
        isPause = true;
        GSYVideoManager.onPause();
    }



    @Override
    protected void onResume() {
        super.onResume();
        isPause = false;
        GSYVideoManager.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoPlayer.releaseAllVideos();
        //GSYPreViewManager.instance().releaseMediaPlayer();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }


    @Override
    public void onBackPressed() {
        if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
            return;
        }

        super.onBackPressed();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        if (isPlay && !isPause) {
            if (newConfig.orientation == ActivityInfo.SCREEN_ORIENTATION_USER) {
                if (!detailPlayer.isIfCurrentIsFullscreen()) {
                    detailPlayer.startWindowFullscreen(VideoDetailActivity.this, true, true);
                }
            } else {
                //新版本isIfCurrentIsFullscreen的标志位内部提前设置了，所以不会和手动点击冲突
                if (detailPlayer.isIfCurrentIsFullscreen()) {
                    StandardGSYVideoPlayer.backFromWindowFull(VideoDetailActivity.this);
                }
                if (orientationUtils != null) {
                    orientationUtils.setEnable(true);
                }
            }
        }
    }




}
