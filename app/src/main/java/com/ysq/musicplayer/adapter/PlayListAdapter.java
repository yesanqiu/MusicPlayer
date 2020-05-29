package com.ysq.musicplayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ysq.musicplayer.R;
import com.ysq.musicplayer.entity.Song;
import com.ysq.musicplayer.utils.LocalMusicUtil;
import com.ysq.musicplayer.utils.MyTask;
import com.ysq.musicplayer.utils.PlayListUtil;

import java.util.List;

public class PlayListAdapter extends BaseAdapter {

    private List<Song> songs;
    private Context context;
    private int resource;

    public PlayListAdapter(Context context, List<Song> songs, int resource){
        this.context = context;
        this.songs = songs;
        this.resource = resource;
    }

    //返回个数
    @Override
    public int getCount() {
        return songs.size();
    }

    //返回某个索引的数据
    @Override
    public Object getItem(int position) {
        return songs.get(position);
    }


    //返回某个索引下的歌曲id
    @Override
    public long getItemId(int position) {
        return songs.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource,parent,false);
        Song song = songs.get(position);
        View view = convertView.findViewById(R.id.red_left);
        ImageView image = convertView.findViewById(R.id.music_p);
        TextView musicName = convertView.findViewById(R.id.music_name);
        TextView singer = convertView.findViewById(R.id.singer);
        if(position!= PlayListUtil.getIndex()){
            view.setVisibility(View.INVISIBLE);
        }

        if(song.isOnlineMusic){
            MyTask myTask = new MyTask(image);
            myTask.execute(song.getRoot().getSonginfo().getPic_premium());
            musicName.setText(song.getRoot().getSonginfo().getTitle());
            singer.setText(song.getRoot().getSonginfo().getAuthor());
        }else {
            image.setImageBitmap(LocalMusicUtil.getArtwork(context, song.getId(), song.getAlbumId(), false, false));
            musicName.setText(song.getName());
            singer.setText(song.getSinger());
        }
        return convertView;
    }


}
