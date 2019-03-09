package com.ml.core.common;/**
 * Created by ruler on 17/1/11.
 */

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 项目名称：baseball
 * 类名称： JSONUtil
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：2017.01.11 10:36
 * 备注：
 *
 * @version 1.0
 */
public class JSONUtil {

    /**
     * 获取数组转换
     * @param jsonArray
     * @return
     */
    public static ArrayList<JSONObject> jsonToList(JSONArray jsonArray) {
        ArrayList<JSONObject> arrayList = new ArrayList<JSONObject>();
        if (jsonArray != null && jsonArray.length() > 0) {
            int length = jsonArray.length();
            for (int k = 0; k < length; k++) {
                try {
                    arrayList.add(jsonArray.getJSONObject(k));
                } catch (Exception e) {
                }
            }
        }
        return arrayList;
    }

    public static String objToStr(Class<?> clazz){
        if(clazz!=null){
            return new Gson().toJson(clazz);
        }
        return "";
    }


    /**
     * 根据名称获取jsonArray数组
     * @param jsonObject
     * @param key
     * @return
     */
    public static JSONArray getJSONArrayByKey(JSONObject jsonObject, String key) {
        try {
            return jsonObject.getJSONArray(key) == null ? null : jsonObject.getJSONArray(key);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据索引获取jsonArray数组
     * @param jsonArray
     * @param index
     * @return
     */
    public static JSONObject getJSONObjectByIndex(JSONArray jsonArray, int index) {
        try {
            JSONObject jsonObject = jsonArray.getJSONObject(index);
            return jsonObject;
        } catch (Exception e) {
            return null;
        }
    }

    public static JSONObject getJSONObjectByKey(JSONObject jsonObject, String key) {
        try {
            JSONObject json = jsonObject.getJSONObject(key);
            return json;
        } catch (Exception e) {
            return null;
        }
    }

    public static String getValue(JSONObject jsonObject, String key) {
        try {
            return jsonObject.getString(key) == null ? "" : jsonObject.getString(key).replace("null", "");
        } catch (Exception e) {
            return "";
        }
    }

    public static int getValueInt(JSONObject jsonObject, String key) {
        try {
            return jsonObject.getInt(key) == 0 ? 0 : jsonObject.getInt(key);
        } catch (Exception e) {
            return 0;
        }
    }

    public static double getValueDouble(JSONObject jsonObject, String key) {
        try {
            return jsonObject.getDouble(key) == 0 ? 0 : jsonObject.getDouble(key);
        } catch (Exception e) {
            return 0;
        }
    }

    public static long getValueLong(JSONObject jsonObject, String key) {
        try {
            return jsonObject.getLong(key) == 0 ? 0 : jsonObject.getLong(key);
        } catch (Exception e) {
            return 0;
        }
    }

    public static boolean getBoolean(JSONObject jsonObject, String key) {
        try {
            return jsonObject.getString(key) == null ? false : jsonObject.getBoolean(key);
        } catch (Exception e) {
            return false;
        }
    }



}
