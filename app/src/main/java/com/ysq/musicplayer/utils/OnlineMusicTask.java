package com.ysq.musicplayer.utils;

import android.os.AsyncTask;
import android.util.Log;

import com.ysq.musicplayer.Index;
import com.ysq.musicplayer.entity.Root;
import com.ysq.musicplayer.entity.Song;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class OnlineMusicTask extends AsyncTask<String, Integer, Root> {



//    public OnlineMusicTask(ImageView imageView){
//        this.imageView = imageView;
//    }

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
    protected Root doInBackground(String... params) {

        String url=params[0];
        HttpURLConnection connection = null;
        InputStream is;
        BufferedReader br;
        String result;
        Root root = null;


        try {

            URL myurl = new URL(url);
            connection = (HttpURLConnection) myurl.openConnection();
            /** 设置连接方式：GET */
            connection.setRequestMethod("GET");
            /** 设置连接主机服务器超时时间：15000毫秒 */
            connection.setConnectTimeout(15000);
            /** 设置读取远程返回的数据时间：60000毫秒 */
            connection.setReadTimeout(60000);

            /** 设置通用的请求属性 */
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Referer", "never");

            connection.connect();
            /** 请求成功：返回码为200 */
            if(connection.getResponseCode() == 200){
                is = connection.getInputStream();
                br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
                System.out.println(connection.getResponseMessage());
                StringBuffer sbf = new StringBuffer();
                String line = null;
                while((line = br.readLine()) != null){
                    sbf.append(line);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
                root = OnlineMusicUtil.fromToJson(result, Root.class);
                Song song = new Song();
                song.setOnlineMusic(true);
                song.setRoot(root);
                PlayListUtil.add(song);
                if(PlayListUtil.getPlayList().size()==1) {
                    MediaUtil.playMusic();
                }
                System.out.println(result);
                System.out.println(root);
                br.close();
                is.close();
            }
        } catch (MalformedURLException e) {
            Log.i("info", "MalformedURLException");
            e.printStackTrace();
        } catch (IOException e) {
            Log.i("info", "IOException:"+e.toString());
            e.printStackTrace();
        }

        return root;


    }

    // 方法3：onProgressUpdate（）
    // 作用：在主线程 显示线程任务执行的进度
    @Override
    protected void onProgressUpdate(Integer... progresses) {


    }

    // 方法4：onPostExecute（）
    // 作用：接收线程任务执行结果、将执行结果显示到UI组件
    @Override
    protected void onPostExecute(Root result) {
        // 执行完毕后，则更新UI
        Index.instance.initPlayer();
    }

    // 方法5：onCancelled()
    // 作用：将异步任务设置为：取消状态
    @Override
    protected void onCancelled() {


    }
}