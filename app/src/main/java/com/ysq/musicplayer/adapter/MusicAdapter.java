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

import java.util.List;

public class MusicAdapter extends BaseAdapter {

    private List<Song> songs;
    private Context context;
    private int resource;

    public MusicAdapter(Context context,List<Song> songs,int resource){
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

        ImageView image = convertView.findViewById(R.id.music_p);
        TextView musicName = convertView.findViewById(R.id.music_name);
        TextView singer = convertView.findViewById(R.id.singer);
//        image.setImageAlpha((int) song.getAlbumId());
        image.setImageBitmap(LocalMusicUtil.getArtwork(context,song.getId(),song.getAlbumId(),false,false));
//        image.setImageResource((int) songs.get(position).getAlbumId());

        musicName.setText(song.getName());
        singer.setText(song.getSinger());
        return convertView;
    }


}
