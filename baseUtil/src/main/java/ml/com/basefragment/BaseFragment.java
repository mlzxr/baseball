package ml.com.basefragment;/**
 * Created by ruler on 16/7/15.
 */

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import ml.com.baseutillib.L;

/**
 * 项目名称：cqt_app
 * 类名称： BaseFragment
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：2016.07.15 17:30
 * 备注：
 *
 * @version 1.0
 */
public abstract class BaseFragment extends Fragment {

    private static final String TAG="BaseFragment";

    protected static final String CODE="code";
    protected static final String MSG="msg";
    protected static final String DATA="data";

    protected static final int OK=1;
    protected static final int EER=0;

    protected Context context;

    protected ExecutorService baseExecutorService;
    /********************************************************************************************/

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(checkPass()){
            initVariables();
            initViews(view, savedInstanceState);
            loadData();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        L.e(TAG,"onCreateView");
        return inflater.inflate(getLayout(), null);
    }
    //
    protected abstract int getLayout();

    //初始化变量
    protected abstract void initVariables();
    //初始化布局
    protected abstract void initViews(View view,Bundle savedInstanceState);
    //加载数据
    protected abstract void loadData();
    //钩子
    protected boolean checkPass() {
        return true;
    }




    /********************************************************************************************/



    @Override
    public void onStart() {
        super.onStart();
        L.e(TAG,"onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
      //  ImageLoader.getInstance().clearMemoryCache();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        shutdownAndAwaitTermination(baseExecutorService);
        System.gc();
    }

    //释放线程
    void shutdownAndAwaitTermination(ExecutorService pool) {
        if(pool!=null){
            pool.shutdownNow();
            try {
                if (!pool.awaitTermination(1, TimeUnit.SECONDS)) {
                    pool.shutdownNow();
                    if (!pool.awaitTermination(1, TimeUnit.SECONDS))
                        System.err.println("Pool did not terminate");
                }
            } catch (InterruptedException ie) {
                pool.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }


}
