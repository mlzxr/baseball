package ml.com.http;/**
 * Created by ruler on 16/7/23.
 */

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目名称：baseball
 * 类名称： NetWorkTool
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：2016.07.23 13:51
 * 备注：
 *
 * @version 1.0
 */
public class NetWorkTool {

    public static final String URL = "http://api.baseballsay.com/";

    public static String test(String url,JSONObject jsonObject) {
        String method = "/app_workorder/patch_order/";
        return new WebClient().POST(URL + method, jsonObject.toString());
    }


}
