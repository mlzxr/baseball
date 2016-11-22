package ml.com.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import ml.com.baseutillib.L;

/**
 * 项目名称：cqt_app
 * 类名称：
 * 类描述：
 * 创建人：Z.Y.J
 * 创建时间：2015/8/11 14:15
 * 备注：
 *
 * @version 1.0
 */
public class WebClient {

    public static final String TAG = "WebClient";

    public String POST(String urlString,String values) {
        //   username = URLEncoder.encode(username);
            // 中文数据需要经过URL编码
      //  password = URLEncoder.encode(password);
        try {
            String params = "username=11&password=222";
            byte[] data = params.getBytes();

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(3000);
            //这是请求方式为POST
            conn.setRequestMethod("POST");
            //设置post请求必要的请求头
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 请求头, 必须设置
            conn.setRequestProperty("Content-Length", data.length + "");
            // 注意是字节长度, 不是字符长度
            conn.setDoOutput(true);
            // 准备写出
            conn.getOutputStream().write(data);
            // 写出数据

        }catch (Exception e){

        }
        return "";
    }

    public String GET(String urlString){
        HttpURLConnection conn = null; //连接对象
        InputStream is = null;
        String resultData = "";
        try {
            URL url = new URL(urlString); //URL对象
            conn = (HttpURLConnection)url.openConnection(); //使用URL打开一个链接
            conn.setDoInput(true); //允许输入流，即允许下载
            conn.setDoOutput(true); //允许输出流，即允许上传
            conn.setUseCaches(false); //不使用缓冲
            conn.setRequestMethod("GET"); //使用post请求
            is = conn.getInputStream();   //获取输入流，此时才真正建立链接
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bufferReader = new BufferedReader(isr);
            String inputLine  = "";
            while((inputLine = bufferReader.readLine()) != null){
                resultData += inputLine + "\n";
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null){
                conn.disconnect();
            }
        }
        return resultData;
    }



}
