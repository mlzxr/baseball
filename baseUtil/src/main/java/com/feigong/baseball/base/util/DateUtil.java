package com.feigong.baseball.base.util;/**
 * Created by ruler on 16/8/28.
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 项目名称：cqt_app
 * 类名称： DateUtil
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：2016.08.28 11:05
 * 备注：
 *
 * @version 1.0
 */
public class DateUtil {

    private static final String FORMAT_YYYY_MM_DD="yyyy-MM-dd";

    private static final String FORMAT_YYYY_MM_DD_HH_MM_SS="yyyy-MM-dd HH:mm:ss";

    /**
     * 日期字符串转时间
     * @param strDate
     * @return
     */
    public  static Date getDate(String strDate){
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
        Date date= null;
        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {

        }
        return date;
    }

    /**
     * 返回格式化的日期字符串
     * @return
     */
    public static String getDate(){
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
        return sdf.format(new Date());
    }

    /**
     * 返回格式化的日期字符串
     * @return
     */
    public static String getDateLong(){
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYY_MM_DD_HH_MM_SS);
        return sdf.format(new Date());
    }



    /**
     * 格林威治时间字符串转日期
     * @param gmt
     * @return
     */
    public static String GmtToDateStrByGmtStr(String gmt){
        Date date = new Date(gmt);
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
        return sdf.format(date);
    }

}
