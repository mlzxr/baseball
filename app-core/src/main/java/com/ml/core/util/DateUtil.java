package com.ml.core.util;/**
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

    public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

    public static final String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";


    /**
     * 日期字符串转时间
     *
     * @param strDate
     * @return
     */
    public static Date getDate(String strDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
        Date date = null;
        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {

        }
        return date;
    }

    /**
     * 返回格式化的日期字符串
     *
     * @return
     */
    public static String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
        return sdf.format(new Date());
    }

    /**
     * 返回格式化的日期字符串
     *
     * @return
     */
    public static String getDateLong() {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYY_MM_DD_HH_MM_SS);
        return sdf.format(new Date());
    }


    /**
     * 格林威治时间字符串转日期
     *
     * @param gmt
     * @return
     */
    public static String GmtToDateStrByGmtStr(String gmt) {
        Date date = new Date(gmt);
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
        return sdf.format(date);
    }


    /**
     * long类型转换为String类型
     *
     * @param currentTime
     * @param formatType
     * @return
     * @throws ParseException
     */
    public static String longToString(long currentTime, String formatType){
        try {
            Date date = longToDate(currentTime, formatType); // long类型转成Date类型
            String strTime = dateToString(date, formatType); // date类型转成String
            return strTime;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * long转换为Date类型
     *
     * @param currentTime
     * @param formatType
     * @return
     * @throws ParseException
     */
    public static Date longToDate(long currentTime, String formatType)
            throws ParseException {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }


    /**
     * date类型转换为String类型
     *
     * @param data
     * @param formatType
     * @return
     */
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }

    /**
     * string类型转换为date类型
     *
     * @param strTime
     * @param formatType
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }


}
