package com.ysq.musicplayer.utils;



import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtil {


    /**
     * 发送 URL 请求
     *
     * @param url
     * @return
     */
    public static void request(final String url, final Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).addHeader("User-Agent","Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)").build();
        client.newCall(request).enqueue(callback);
    }




}