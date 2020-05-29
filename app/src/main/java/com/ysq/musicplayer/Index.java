package com.ysq.musicplayer;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ysq.musicplayer.adapter.SelectionPagerAdapter;
import com.ysq.musicplayer.entity.Song;
import com.ysq.musicplayer.utils.LocalMusicUtil;
import com.ysq.musicplayer.utils.MediaUtil;
import com.ysq.musicplayer.utils.MyTask;
import com.ysq.musicplayer.utils.OnlineMusicUtil;
import com.ysq.musicplayer.utils.PlayListUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Index extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private static final String TAG = "Index";

    private static ViewPager viewPager;
    private List<Fragment> fragments;
    public static Index instance = null;
//    public static Activity index = this;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);
        instance = this;
        Log.d(TAG, "onCreate: 1" );
        Log.d(TAG, "onCreate: 2" );

        viewPager = findViewById(R.id.music);
        initViewPager();

        //跳转播放列表绑定
        ImageButton list = findViewById(R.id.list);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Index.this,PlayList.class));
            }
        });


        //跳转
        ImageView toPlayer = findViewById(R.id.to_player);
        toPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Index.this,Player.class));
            }
        });

        //在线音乐和本地音乐列表切换
        TextView online = findViewById(R.id.online);
        online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("点击按扭 在线音乐");
                viewPager.setCurrentItem(1);
            }
        });

        TextView local = findViewById(R.id.local);
        local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("点击按扭 本地音乐");
                viewPager.setCurrentItem(0);
            }
        });

        //播放或暂停按钮
        ImageButton startOrPause = findViewById(R.id.index_start);
        startOrPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaUtil.pause();
                initPlayer();
            }
        });

        //下一首歌曲绑定
        ImageButton nextSong = findViewById(R.id.next_song);
        nextSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaUtil.nextMusic();
                initPlayer();
            }
        });

        //初始化播放界面
        initPlayer();


    }

    public static void setViewPagerCurrentItem(int index){
        if(viewPager!= null){
            viewPager.setCurrentItem(index);
        }
    }

    //初始化ViewPager
    private void initViewPager(){
        fragments = new ArrayList<>();
        fragments.add(new LocalMusic());
        fragments.add(new OnlineMusic());
        viewPager.setAdapter(new SelectionPagerAdapter(getSupportFragmentManager(),fragments));
        viewPager.addOnPageChangeListener(this);
        viewPager.setCurrentItem(0);
    }

    //初始化播放界面
    public void initPlayer(){
        ImageView imageView = findViewById(R.id.to_player);
        TextView textView = findViewById(R.id.index_music_name);
        ImageButton imageButton = findViewById(R.id.index_start);
        if(PlayListUtil.getPlayList().size()>0) {
            Song song = PlayListUtil.getSingSong();
            if(song.isOnlineMusic){
                MyTask myTask = new MyTask(imageView);
                myTask.execute(song.getRoot().getSonginfo().getPic_premium());
                textView.setText(song.getRoot().getSonginfo().getTitle());
                if (MediaUtil.getPlayStatic()) {
                    imageButton.setImageResource(R.drawable.ic_play_bar_btn_pause);
                } else {
                    imageButton.setImageResource(R.drawable.ic_play_bar_btn_play);
                }
            }else {
                imageView.setImageBitmap(LocalMusicUtil.getArtwork(this, song.getId(), song.getAlbumId(), false, false));
                textView.setText(song.getName());
                if (MediaUtil.getPlayStatic()) {
                    imageButton.setImageResource(R.drawable.ic_play_bar_btn_pause);
                } else {
                    imageButton.setImageResource(R.drawable.ic_play_bar_btn_play);
                }
            }
        }else{
            imageView.setImageResource(R.drawable.ic_seek_bar_progress_btn);
            textView.setText(R.string.musicName);
            imageButton.setImageResource(R.drawable.ic_play_bar_btn_play);
        }
    }




    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        System.out.println(position);
        TextView local = findViewById(R.id.local);
        TextView online = findViewById(R.id.online);
        if(position==0){

            local.setTextColor(getResources().getColor(R.color.titleLight));
            online.setTextColor(getResources().getColor(R.color.titleNoLight));
        }else{
            online.setTextColor(getResources().getColor(R.color.titleLight));
            local.setTextColor(getResources().getColor(R.color.titleNoLight));
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }


}
