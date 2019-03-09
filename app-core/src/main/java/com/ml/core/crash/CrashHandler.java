package com.ml.core.crash;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;
import android.os.Process;

import com.ml.core.util.AppUtils;
import com.ml.core.util.DateUtil;
import com.ml.core.util.SDCardUtils;
import com.ml.core.util.T;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ruler on 17/10/15.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static CrashHandler crashHandler;

    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;

    private Context context;

    //存储异常信息
    private HashMap<String,String> info = new HashMap<>();



    private CrashHandler() {

    }


    public static CrashHandler getInstance() {
        if (crashHandler == null) {
            synchronized (CrashHandler.class) {
                if (crashHandler == null)
                    crashHandler = new CrashHandler();
            }
        }
        return crashHandler;
    }


    public void init(Context context) {
        this.context = context;
        uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);


    }


    /**
     * The thread is being terminated by an uncaught exception. Further
     * exceptions thrown in this method are prevent the remainder of the
     * method from executing, but are otherwise ignored.
     *
     * @param thread the thread that has an uncaught exception
     * @param ex     the exception that was thrown
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        //收集错误信息
        //保存信息
        //上传服务器


        if (handlerException(ex)) {//未处理
            if (uncaughtExceptionHandler!=null){
                uncaughtExceptionHandler.uncaughtException(thread,ex);

            }else {//已经人为处理
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Process.killProcess(Process.myPid());
                System.exit(1);
            }

        }
    }

    /**
     * 人为处理异常
     * @param ex
     * @return
     */
    private boolean handlerException(Throwable ex) {
        if(ex==null){
            return  false;
        }else {
            new Thread(){
                @Override
                public void run() {
                    Looper.prepare();
                    T.showShort(context,"UncaughtException");
                    Looper.loop();
                }
            }.start();
            //收集错误信息
            collectErrorInfo();
            saveErrorInfo(ex);



        }

        return false;
    }

    /**
     * 保存错误信息
     * @param ex
     */
    private void saveErrorInfo(Throwable ex) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String,String> entry :info.entrySet()){
            String keyName = entry.getKey();
            String keyValue = entry.getValue();
            stringBuffer.append(keyName+"="+keyValue+"\n");
        }
        Writer writer = new StringWriter();
        PrintWriter printWritr =  new PrintWriter(writer);
        ex.printStackTrace(printWritr);
        Throwable throwable = ex.getCause();
        if(throwable!=null){
            throwable.printStackTrace(printWritr);
            throwable= ex.getCause();
        }
        //
        printWritr.close();

        String result = writer.toString();
        stringBuffer.append(result);
        //
        long curTime = System.currentTimeMillis();
        String time = DateUtil.getDateLong();
        String fileName = "crash-"+time+"-"+curTime+".log";
        if(SDCardUtils.isSDCardEnable()){
            String path = "/sdcard/crash/";
            File file = new File(path);
            if(!file.exists()){
                file.mkdir();
            }
            FileOutputStream fileOutputStream=null;

            try {
                fileOutputStream = new FileOutputStream(path+fileName);
                fileOutputStream.write(stringBuffer.toString().getBytes());

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if(fileOutputStream!=null){
                        fileOutputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    /**
     * 收集错误信息
     */
    private void collectErrorInfo() {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo  packageInfo = AppUtils.getPackageInfo(context);
        if(packageInfo!=null){
            String versionName = packageInfo.versionName;
            int versionCode = packageInfo.versionCode;
            //
            info.put("versionName",versionName);
            info.put("versionCode",String.valueOf(versionCode));
        }
        //
        Field[] fields = Build.class.getFields();
        if(fields!=null && fields.length>0){
            for (Field field :fields){
                field.setAccessible(true);
                try {
                    info.put(field.getName(),field.get(null).toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
