package com.ysq.musicplayer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ysq.musicplayer.adapter.OnlineMusicTopListAdapter;
import com.ysq.musicplayer.entity.OnlineMusicResult;
import com.ysq.musicplayer.utils.HttpUtil;
import com.ysq.musicplayer.utils.MyTask;
import com.ysq.musicplayer.utils.OnlineMusicTask;
import com.ysq.musicplayer.utils.OnlineMusicUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class OnlineMusicTopList extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.online_music_top_list);
        Intent intent=getIntent();
        int i = intent.getIntExtra("position",-1);
        System.out.println(i);
        List<OnlineMusicResult> onlineMusicResult = OnlineMusicUtil.getResult();
        final OnlineMusicResult result = onlineMusicResult.get(i);
        ImageButton imageButton = findViewById(R.id.top_list_to_index);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OnlineMusicTopList.this,Index.class));
//                Index.setViewPagerCurrentItem(1);
            }
        });

        TextView title = findViewById(R.id.top_list_title);
        title.setText(result.getBillboard().getName());

        ImageView topPic = findViewById(R.id.top_list_pic);
        MyTask myTask = new MyTask(topPic);
        myTask.execute(result.getBillboard().getPic_s192());

        LinearLayout linearLayout = findViewById(R.id.top_list_top);
//        linearLayout.setBackgroundColor(Color.parseColor(changColor(result.getBillboard().getBg_color())));
        TextView  name = findViewById(R.id.top_list_name);
        name.setText(result.getBillboard().getName());
        TextView  time = findViewById(R.id.top_list_time);
        time.setText("最近更新时间：" + result.getBillboard().getUpdate_date());
        TextView introduce = findViewById(R.id.top_list_introduce);
        introduce.setText(result.getBillboard().getComment());

        ListView listView = findViewById(R.id.top_list_content);
        OnlineMusicTopListAdapter onlineMusicTopListAdapter = new OnlineMusicTopListAdapter(this,result.getSong_list(),R.layout.online_music_top_list_line);
        listView.setAdapter(onlineMusicTopListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("position:" + position);
                System.out.println("id:" + id);
                String songId = result.getSong_list()[position].getSong_id();
                String url = "http://tingapi.ting.baidu.com/v1/restserver/ting?format=json&calback=&method=baidu.ting.song.play&songid="+songId;
                System.out.println(url);
                OnlineMusicTask onlineMusicTask = new OnlineMusicTask();
                onlineMusicTask.execute(url);
            }
        });
    }

//    public String changColor(String colorStr){
//        String color = "";
//        if()
//        if (colorStr.substring(0,2).equals("0x")){
//            color = "#" + colorStr.split("x")[1];
//        }else{
//            color = colorStr;
//        }
//        return color;
//    }

}
