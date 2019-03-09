package com.ml.core.common;/**
 * Created by ruler on 16/12/6.
 */

import java.util.Map;

/**
 * 项目名称：baseball
 * 类名称： MapUtil
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：2016.12.06 10:32
 * 备注：
 *
 * @version 1.0
 */
public class MapUtil {

    /**
     * 返回Int 类型的结果
     * @param map
     * @param key
     * @return
     */
    public static int getInt(Map<String,Object> map,String key){
        if(map!=null && map.get(key)!=null){
            return (int) map.get(key);
        }
        return 0;
    }


    public static String getValue(Map<String,Object> map,String key){
        if(map!=null && map.get(key)!=null){
            return String.valueOf(map.get(key));
        }
        return null;
    }

}
