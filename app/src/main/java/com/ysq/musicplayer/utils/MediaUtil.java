package com.ysq.musicplayer.utils;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;


import androidx.annotation.Nullable;

import com.ysq.musicplayer.Index;
import com.ysq.musicplayer.Player;
import com.ysq.musicplayer.entity.Song;

import java.io.IOException;
import java.util.List;

public class MediaUtil {

    private static MediaPlayer mediaPlayer = new MediaPlayer();

    //播放模式
    // 0 为 列表循环
    // 1 为 单曲循环
    // 2 为 随机播放
    private static int playMode = 0;

    //播放音乐
    public static void playMusic(){
        try {
            List<Song> playList = PlayListUtil.getPlayList();
            initPlayer();
            if(playList.size()!=0) {
                String path = playList.get(0).getPath();
                if (playList.get(0).isOnlineMusic){
                    path = playList.get(0).getRoot().getBitrate().getFile_link();
                }
                mediaPlayer.setDataSource(path);
                mediaPlayer.prepare();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        System.out.println("---------------complet-----------");
                        System.out.println(PlayListUtil.getIndex());
                        nextMusic();
                        Index.instance.initPlayer();
                        Player.instance.initMusicMsg();
                    }
                });
                mediaPlayer.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //下一首
    public static void nextMusic(){
        try{
            initPlayer();
            PlayListUtil.next(playMode);
            if(!PlayListUtil.isEmpty()) {
                Song s = PlayListUtil.getSingSong();
                String path = s.getPath();
                if (s.isOnlineMusic){
                    path = s.getRoot().getBitrate().getFile_link();
                }
                mediaPlayer.setDataSource(path);
                mediaPlayer.prepare();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        System.out.println("---------------complet-----------");
                        System.out.println(PlayListUtil.getIndex());
                        nextMusic();
                        Index.instance.initPlayer();
                        Player.instance.initMusicMsg();
                    }
                });
                mediaPlayer.start();

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //上一首
    public static void prevMusic(){
        try{
            initPlayer();
            PlayListUtil.prev(playMode);
            if(!PlayListUtil.isEmpty()) {
                Song s = PlayListUtil.getSingSong();
                String path = s.getPath();
                if (s.isOnlineMusic){
                    path = s.getRoot().getBitrate().getFile_link();
                }
                mediaPlayer.setDataSource(path);
                mediaPlayer.prepare();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        System.out.println("---------------complet-----------");
                        System.out.println(PlayListUtil.getIndex());
                        nextMusic();
                        Index.instance.initPlayer();
                        Player.instance.initMusicMsg();
                    }
                });
                mediaPlayer.start();

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //获取播放状态
    public static boolean getPlayStatic(){
        return mediaPlayer.isPlaying();
    }


    //暂停或播放
    public static void pause(){
        if(getPlayStatic()){
            mediaPlayer.pause();
        }else{
            mediaPlayer.start();
        }
    }

    public static void initPlayer(){

        mediaPlayer.release();
        mediaPlayer = new MediaPlayer();
    }

    //获取播放进度
    public static int getPosition(){
        if(getPlayStatic()){
            return mediaPlayer.getCurrentPosition();
        }else {
            return 0;
        }
    }

    //调整播放进度
    public static void setPosition(int position){
        Song s = PlayListUtil.getSingSong();
        if(s.isOnlineMusic){
            mediaPlayer.seekTo(position * s.getRoot().getBitrate().getFile_duration() * 10);
        }else {
            mediaPlayer.seekTo(position * s.getDuration() / 100);
        }
    }

    //改变播放模式
    public static void changePlayMode(){
        playMode++;

        playMode = playMode%3;
    }

    //获得当前播放模式
    public static int getPlayMode(){
        return playMode;
    }


}
