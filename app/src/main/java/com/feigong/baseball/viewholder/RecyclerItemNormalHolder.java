package com.feigong.baseball.viewholder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.feigong.baseball.R;
import com.feigong.baseball.beans.ReturnMSG_VideoList;
import com.feigong.baseball.common.ImageUtil;
import com.feigong.baseball.listener.SampleListener;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.Debuger;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;


/**
 * Created by guoshuyu on 2017/1/9.
 */

public class RecyclerItemNormalHolder extends RecyclerItemBaseHolder {

    public final static String TAG = "RecyclerView2List";

    protected Context context = null;
    
    private StandardGSYVideoPlayer gsyVideoPlayer;

    private ImageView imageView,iv_avatar;

    private TextView tv_title,tv_date;



    public RecyclerItemNormalHolder(Context context, View v) {
        super(v);
        this.context = context;
        imageView = new ImageView(context);
        gsyVideoPlayer = (StandardGSYVideoPlayer)v.findViewById(R.id.video_item_player);
        //
        tv_title = (TextView)v.findViewById(R.id.tv_title);
        tv_date =(TextView)v.findViewById(R.id.tv_date);
        iv_avatar =(ImageView)v.findViewById(R.id.iv_avatar);

    }

    public void onBind(final int position, ReturnMSG_VideoList.DataBean.VodListBean videoModel) {

        //增加封面
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ImageLoader.getInstance().displayImage(videoModel.getV_poster(), imageView, ImageUtil.getImageOptions());
        if (imageView.getParent() != null) {
            ViewGroup viewGroup = (ViewGroup)imageView.getParent();
            viewGroup.removeView(imageView);
        }


        //
        gsyVideoPlayer.setThumbImageView(imageView);

        final String url = videoModel.getV_url();

        //默认缓存路径
        gsyVideoPlayer.setUp(url, true , null, videoModel.getTitle());

        //增加title
        gsyVideoPlayer.getTitleTextView().setVisibility(View.VISIBLE);

        //设置返回键
        gsyVideoPlayer.getBackButton().setVisibility(View.GONE);

        //设置全屏按键功能
        gsyVideoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolveFullBtn(gsyVideoPlayer);
            }
        });
        gsyVideoPlayer.setRotateViewAuto(true);
        gsyVideoPlayer.setLockLand(true);
        gsyVideoPlayer.setPlayTag(TAG);
        gsyVideoPlayer.setShowFullAnimation(true);
        //循环
        //gsyVideoPlayer.setLooping(true);
        gsyVideoPlayer.setNeedLockFull(true);

        //gsyVideoPlayer.setSpeed(2);

        gsyVideoPlayer.setPlayPosition(position);

        gsyVideoPlayer.setStandardVideoAllCallBack(new SampleListener(){
            @Override
            public void onPrepared(String url, Object... objects) {
                super.onPrepared(url, objects);
                Debuger.printfLog("onPrepared");
                if (!gsyVideoPlayer.isIfCurrentIsFullscreen()) {
                    //静音
                    GSYVideoManager.instance().setNeedMute(false);
                }

            }

            @Override
            public void onQuitFullscreen(String url, Object... objects) {
                super.onQuitFullscreen(url, objects);
                //全屏不静音
                GSYVideoManager.instance().setNeedMute(true);
            }

            @Override
            public void onEnterFullscreen(String url, Object... objects) {
                super.onEnterFullscreen(url, objects);
                GSYVideoManager.instance().setNeedMute(false);
            }
        });


        //发布人头像
        ImageLoader.getInstance().displayImage(videoModel.getPublisher_avator(), iv_avatar, ImageUtil.getImageOptionsCircle());
        //
        tv_title.setText(videoModel.getPublisher_name());
        tv_date.setText(videoModel.getPublish_time());


    }

    /**
     * 全屏幕按键处理
     */
    private void resolveFullBtn(final StandardGSYVideoPlayer standardGSYVideoPlayer) {
        standardGSYVideoPlayer.startWindowFullscreen(context, true, true);
    }

}