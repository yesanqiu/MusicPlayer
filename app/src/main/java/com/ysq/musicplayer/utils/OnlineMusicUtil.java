package com.ysq.musicplayer.utils;

//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ysq.musicplayer.entity.OnlineMusicResult;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class OnlineMusicUtil {

    private final static String GET_TOP_MUSIC_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?format=json&calback=&method=baidu.ting.billboard.billList&";
    private final static int[] TYPES = {1,2,11,21,22,23,24,25};

    public static Gson gson = new Gson();
    private static List<OnlineMusicResult> musicTop = new ArrayList<>();
    public static int successNum;
    private static boolean flag = false;

    private static Bitmap bitmap;
    private static String result = "";

    public static void initTopMusicList(){
        for(int type: TYPES) {
            getTopMusic(type, 10, 0);
        }
    }

    public static String formateTime(int time){
        int min = time / 60;
        int sec = time % 60;
        String second = "";
        if(sec >= 10){
            second = Integer.toString(sec);
        }else {
            second = "0" + Integer.toString(sec);
        }
        return min + ":" + second;
    }

    public static void getTopMusic(int type,int size,int offset){
        System.out.println("type:" + type);
        HttpUtil.request(GET_TOP_MUSIC_URL +"type=" + type + "&size=" + size + "&offset=" + offset, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("成功了");
                musicTop.add((OnlineMusicResult) fromToJson(response.body().string(), OnlineMusicResult.class));
                successNum ++;
            }
        });
    }

    public static <T> T fromToJson(String json, Type listType) {
        T t = null;
        t = gson.fromJson(json, listType);
        return t;
    }

    public static List<OnlineMusicResult> getResult(){
        while (true) {
            if (successNum == TYPES.length) {
                return musicTop;
            }
        }
    }

    public static Bitmap getPic(String url){
        URL myurl = null;
        try {
            myurl = new URL(url);

        // 获得连接
        HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
        conn.setConnectTimeout(6000);//设置超时
        conn.setDoInput(true);
        conn.setUseCaches(false);//不缓存
        conn.connect();
        InputStream is = conn.getInputStream();
        bitmap = BitmapFactory.decodeStream(is);
            System.out.println(bitmap == null);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        HttpUtil.request(url, new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                System.out.println("获取图片失败");
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                System.out.println("获取图片成功");
//                InputStream inputStream = response.body().byteStream();
//                BitmapFactory.Options options=new BitmapFactory.Options();
//                System.out.println(1);
//                bitmap = BitmapFactory.decodeStream(inputStream,null,options);
//                System.out.println(2);
//            }
//        });
//        while (bitmap == null){
//            System.out.println(1);
//        }
        return bitmap;
    }

    public static void getMusicWord(String url, final View context, final int view_id){

        HttpUtil.request(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String words = response.body().string();
                String[] s = words.split("\n");
                for(String str:s){
                    if(!str.substring(str.length()-1).equals("]")) {
                        result += str.split("]")[1];
                        result += "\n";
                    }
                }
                TextView textView = context.findViewById(view_id);
                textView.setText(result);
                flag =true;

            }
        });

    }
    //加载图片
    public static Bitmap getURLimage(String url) {
//            Bitmap bitmap=null;
            URLConnection connection;
            InputStream is;


            try {

                URL myurl = new URL(url);
                connection=myurl.openConnection();
                connection.connect();
                is=connection.getInputStream();
                //包装一下
                BufferedInputStream bis=new BufferedInputStream(is);
                //将一个输入流解析为一个BitMap对象
                bitmap= BitmapFactory.decodeStream(is);
                //关闭输入流
                is.close();
                bis.close();
            } catch (MalformedURLException e) {
                Log.i("info", "MalformedURLException");
                e.printStackTrace();
            } catch (IOException e) {
                Log.i("info", "IOException:"+e.toString());
                e.printStackTrace();
            }
            return bitmap;
    }

    public static void main(String[] args) {
//       initTopMusicList();
//       OnlineMusicResult o = getResult().get(1);
//        String str = getMusicWord("http://qukufile2.qianqian.com/data2/lrc/bed1fcb36f51259eefab8ba6d95f524f/672457403/672457403.lrc");
//        System.out.println(str);
//        if(str.equals("")){
//            System.out.println("null");
//        }else{
//            System.out.println("yes");
//        }
        Scanner s = new Scanner(System.in);
        int a = s.nextInt();

        while(true){
            if(a == 1){
                System.out.println("a == 1");
                break;
            }
        }
    }
}
