package com.ysq.musicplayer.entity;

public class Bitrate {

    private String show_link;

    private int free;

    private int song_file_id;

    private int file_size;

    private String file_extension;

    private int file_duration;

    private int file_bitrate;

    private String file_link;

    private String hash;

    public String getShow_link() {
        return show_link;
    }

    public void setShow_link(String show_link) {
        this.show_link = show_link;
    }

    public int getFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }

    public int getSong_file_id() {
        return song_file_id;
    }

    public void setSong_file_id(int song_file_id) {
        this.song_file_id = song_file_id;
    }

    public int getFile_size() {
        return file_size;
    }

    public void setFile_size(int file_size) {
        this.file_size = file_size;
    }

    public String getFile_extension() {
        return file_extension;
    }

    public void setFile_extension(String file_extension) {
        this.file_extension = file_extension;
    }

    public int getFile_duration() {
        return file_duration;
    }

    public void setFile_duration(int file_duration) {
        this.file_duration = file_duration;
    }

    public int getFile_bitrate() {
        return file_bitrate;
    }

    public void setFile_bitrate(int file_bitrate) {
        this.file_bitrate = file_bitrate;
    }

    public String getFile_link() {
        return file_link;
    }

    public void setFile_link(String file_link) {
        this.file_link = file_link;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        return "Bitrate{" +
                "show_link='" + show_link + '\'' +
                ", free=" + free +
                ", song_file_id=" + song_file_id +
                ", file_size=" + file_size +
                ", file_extension='" + file_extension + '\'' +
                ", file_duration=" + file_duration +
                ", file_bitrate=" + file_bitrate +
                ", file_link='" + file_link + '\'' +
                ", hash='" + hash + '\'' +
                '}';
    }
}
