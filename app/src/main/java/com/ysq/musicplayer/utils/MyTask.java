package com.ysq.musicplayer.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MyTask extends AsyncTask<String, Integer, Bitmap> {


    private ImageView imageView;

    public MyTask(ImageView imageView){
        this.imageView = imageView;
    }

    // 方法1：onPreExecute（）
    // 作用：执行 线程任务前的操作
    @Override
    protected void onPreExecute() {
        // 执行前显示提示
    }


    // 方法2：doInBackground（）
    // 作用：接收输入参数、执行任务中的耗时操作、返回 线程任务执行的结果
    // 此处通过计算从而模拟“加载进度”的情况
    @Override
    protected Bitmap doInBackground(String... params) {

        String url=params[0];
        Bitmap bitmap=null;
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

    // 方法3：onProgressUpdate（）
    // 作用：在主线程 显示线程任务执行的进度
    @Override
    protected void onProgressUpdate(Integer... progresses) {


    }

    // 方法4：onPostExecute（）
    // 作用：接收线程任务执行结果、将执行结果显示到UI组件
    @Override
    protected void onPostExecute(Bitmap result) {
        System.out.println(result == null);
//            System.out.println(result.getHeight());
        // 执行完毕后，则更新UI
        imageView.setImageBitmap(result);
    }

    // 方法5：onCancelled()
    // 作用：将异步任务设置为：取消状态
    @Override
    protected void onCancelled() {


    }
}