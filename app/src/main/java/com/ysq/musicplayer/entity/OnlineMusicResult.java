package com.ysq.musicplayer.entity;

import java.util.Arrays;

public class OnlineMusicResult {

    private BaiduSong[] song_list;
    private BillBoard billboard;
    private String error_code;

    public BaiduSong[] getSong_list() {
        return song_list;
    }

    public void setSong_list(BaiduSong[] song_list) {
        this.song_list = song_list;
    }

    public BillBoard getBillboard() {
        return billboard;
    }

    public void setBillboard(BillBoard billboard) {
        this.billboard = billboard;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    @Override
    public String toString() {
        return "OnlineMusicResult{" +
                "song_list=" + Arrays.toString(song_list) +
                ", billboard=" + billboard +
                ", error_code='" + error_code + '\'' +
                '}';
    }
}
