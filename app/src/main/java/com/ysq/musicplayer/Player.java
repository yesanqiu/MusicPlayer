package com.ysq.musicplayer;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ysq.musicplayer.adapter.SelectionPagerAdapter;
import com.ysq.musicplayer.entity.Song;
import com.ysq.musicplayer.utils.LocalMusicUtil;
import com.ysq.musicplayer.utils.MediaUtil;
import com.ysq.musicplayer.utils.OnlineMusicUtil;
import com.ysq.musicplayer.utils.PlayListUtil;

import java.util.ArrayList;
import java.util.List;

public class Player extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    public static Player instance;
    private ViewPager viewPager;
    private List<Fragment> fragments;
    private SeekBar seekBar;
    private TextView textView;
    private Song song;

    private Handler handler = new Handler();
    private SeekBarLister seekBarLister = new SeekBarLister();


    class SeekBarLister implements Runnable {

        SeekBarLister(){
            song = PlayListUtil.getSingSong();
        }


        @Override
        public void run() {

            double position = MediaUtil.getPosition();
            double duration = 0.00;
            if(song.isOnlineMusic){
                duration = song.getRoot().getBitrate().getFile_duration() * 1000;
            }else{
                duration = song.getDuration();
            }

            System.out.println("position:" + position);
            System.out.println(duration);
            int r = (int) ((position / duration) * 100);
            System.out.println("播放进度：" + r);
            seekBar.setProgress(r);
            textView.setText(LocalMusicUtil.formatTime(MediaUtil.getPosition()));

            if (MediaUtil.getPlayStatic()) {
                handler.postDelayed(seekBarLister, 1000);
            }
        }
    }




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player);

        instance = this;

        initMusicMsg();
        viewPager = findViewById(R.id.musicpic);
        initViewPager();

        seekBar = findViewById(R.id.listen_jindutiao_sb);
        textView = findViewById(R.id.listen_current_tv);


        //绑定按键
        ImageView playMode = findViewById(R.id.play_mode);
        playMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaUtil.changePlayMode();
                initMusicMsg();
            }
        });
        ImageView playPrev = findViewById(R.id.play_prev);
        playPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaUtil.prevMusic();
                initMusicMsg();
            }
        });
        ImageView playPauseOrStart = findViewById(R.id.play_pause_or_start);
        playPauseOrStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaUtil.pause();
                initMusicMsg();
            }
        });
        ImageView playNext = findViewById(R.id.play_next);
        playNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaUtil.nextMusic();
                initMusicMsg();
            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if(fromUser){

                    System.out.println("seekbar process:" + progress);
                    if(song.isOnlineMusic){

                        textView.setText(LocalMusicUtil.formatTime(progress * song.getRoot().getBitrate().getFile_duration() * 10));
                    }else{
                        System.out.println(LocalMusicUtil.formatTime(progress * song.getDuration() /100));
                        textView.setText(LocalMusicUtil.formatTime(progress * song.getDuration() /100));
                    }
                    MediaUtil.setPosition(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });
        handler.postDelayed(seekBarLister,1000);
    }


    public void initMusicMsg(){
        Song song = PlayListUtil.getSingSong();
        if(song != null) {
            TextView playerMusicName = findViewById(R.id.player_music_name);
            TextView playerSinger = findViewById(R.id.player_singer);
            TextView longtime = findViewById(R.id.listen_length_tv);
            ImageView playMode = findViewById(R.id.play_mode);
            ImageView playPauseOrStart = findViewById(R.id.play_pause_or_start);
            if(song.isOnlineMusic){
                playerMusicName.setText(song.getRoot().getSonginfo().getTitle());
                playerSinger.setText(song.getRoot().getSonginfo().getAuthor());
                longtime.setText(LocalMusicUtil.formatTime(song.getRoot().getBitrate().getFile_duration() *1000));
            }else {
                playerMusicName.setText(song.getName());
                playerSinger.setText(song.getSinger());
                longtime.setText(LocalMusicUtil.formatTime(song.getDuration()));
            }
            int playModeNum = MediaUtil.getPlayMode();
            switch (playModeNum){
                case 0:
                    playMode.setImageResource(R.drawable.ic_play_btn_loop);
                    break;
                case 1:
                    playMode.setImageResource(R.drawable.ic_play_btn_one);
                    break;
                case 2:
                    playMode.setImageResource(R.drawable.ic_play_btn_shuffle);
                    break;

            }
            if(MediaUtil.getPlayStatic()){
                playPauseOrStart.setImageResource(R.drawable.ic_play_btn_pause);
            }else {
                playPauseOrStart.setImageResource(R.drawable.ic_play_btn_play);
            }

        }
    }

    //初始化ViewPager
    private void initViewPager(){
        fragments = new ArrayList<>();
        fragments.add(new MusicPic());
        fragments.add(new MusicWord());
        viewPager.setAdapter(new SelectionPagerAdapter(getSupportFragmentManager(),fragments));
        viewPager.addOnPageChangeListener(this);
        viewPager.setCurrentItem(0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        System.out.println(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


}
