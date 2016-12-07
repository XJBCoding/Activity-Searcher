package com.example.proj.zhaohuo;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yelluw on 2016/12/8.
 */
public class ConnectHelper {
    public ConnectHelper(){}
    public String readIt(InputStream stream, int len) throws IOException,UnsupportedEncodingException
    {  Reader reader = null;
        reader = new InputStreamReader(stream, "GB18030");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }
    //连接网络函数，在异步线程中调用
    public List<String> downloadUrl(String myurl) throws IOException{
        InputStream is = null;
        int len = 500;
        List<String> list = new LinkedList<>();
        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.connect();
            int response = conn.getResponseCode(); //状态码为200表示连接成功
            Log.d("The response is",""+response);
            is = conn.getInputStream(); //获取输入流
            String contentAsString = readIt(is, len); //转换为string
            List<String> reList = new LinkedList<>();
            reList.add(contentAsString);
            return reList;
        }
        finally {
            if (is != null) {
                is.close();
            }
        }
    }
}
