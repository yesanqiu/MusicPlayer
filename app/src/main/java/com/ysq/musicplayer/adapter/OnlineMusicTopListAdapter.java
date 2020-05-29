package com.ysq.musicplayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ysq.musicplayer.R;
import com.ysq.musicplayer.entity.BaiduSong;
import com.ysq.musicplayer.entity.OnlineMusicResult;
import com.ysq.musicplayer.utils.MyTask;

import java.util.List;

public class OnlineMusicTopListAdapter extends BaseAdapter {

    private Context context;
    private BaiduSong[] song_list;
    private int resource;

    public OnlineMusicTopListAdapter(Context context, BaiduSong[] song_list, int resource){
        this.context = context;
        this.song_list = song_list;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return song_list.length;
    }

    @Override
    public Object getItem(int position) {
        return song_list[position];
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(song_list[position].getArtist_id());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource,parent,false);
        ImageView pic = convertView.findViewById(R.id.online_music_pic);
        TextView name = convertView.findViewById(R.id.online_music_name);
        TextView singer = convertView.findViewById(R.id.online_music_singer);
        BaiduSong song = song_list[position];
        name.setText(song.getTitle());
        MyTask myTask = new MyTask(pic);
        myTask.execute(song.getPic_s500());
        singer.setText(song.getAuthor());
        return convertView;
    }
}
