package ml.com.baseutillib;/**
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

    private static final String FORMAT_yyyy_MM_dd="yyyy-MM-dd";

    public  static Date getDate(String strDate){
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_yyyy_MM_dd);
        Date date= null;
        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {

        }
        return date;
    }

    public static String getDate(){
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_yyyy_MM_dd);
        return sdf.format(new Date());
    }
}
