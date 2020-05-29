package com.ysq.musicplayer.utils;

import android.media.MediaPlayer;

import com.ysq.musicplayer.entity.Song;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class PlayListUtil {

    private static List<Song> playList = new ArrayList<>();
    private static int index = 0;

    static {
    }

    public static void add(Song song){
        if(!isExist(song)) {
            playList.add(song);
        }
    }


    public static boolean isExist(Song song){
        for(Song s: playList){
            if(s.getId() == song.getId()){
                return true;
            }
        }
        return false;
    }

    public static void next(int playMode){
        switch (playMode) {
            case 0:
                //循环播放
                if (index < playList.size() - 1) {
                    index++;
                } else {
                    index = 0;
                }
                break;
            case 1:
                //顺序播放
                break;
            case 2:
                Random r = new Random();
                index = r.nextInt(playList.size());
                break;

        }
    }

    public static void prev(int playMode){
        if(playMode==2){
            Random r = new Random();
            index = r.nextInt(playList.size());
        }else if(playMode == 0){
            if(index == 0 ){
                index = playList.size()-1;
            }else {
                index--;
            }
        }
    }

    public static int getIndex(){
        return index;
    }


    public static boolean isEmpty(){
        return playList.isEmpty();
    }

    public static Song getSingSong(){
        if(playList.size()==0){
            return new Song();
        }
        return playList.get(index);
    }


    public static boolean delete(int id){
        boolean result = false;
        for(Song s: playList){
            if(s.getId() == id){
                playList.remove(s);
                result = true;
            }
        }
        return result;
    }

    public static List<Song> getPlayList(){
        return playList;
    }



}
