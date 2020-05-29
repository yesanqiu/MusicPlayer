package com.ysq.musicplayer.entity;

public class Root {

    private Songinfo songinfo;

    private int error_code;

    private Bitrate bitrate;

    public Songinfo getSonginfo() {
        return songinfo;
    }

    public void setSonginfo(Songinfo songinfo) {
        this.songinfo = songinfo;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public Bitrate getBitrate() {
        return bitrate;
    }

    public void setBitrate(Bitrate bitrate) {
        this.bitrate = bitrate;
    }

    @Override
    public String toString() {
        return "Root{" +
                "songinfo=" + songinfo +
                ", error_code=" + error_code +
                ", bitrate=" + bitrate +
                '}';
    }
}
