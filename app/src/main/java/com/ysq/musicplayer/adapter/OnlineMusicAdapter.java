package com.ysq.musicplayer.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
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

public class OnlineMusicAdapter extends BaseAdapter {

    private List<OnlineMusicResult> musicTop;
    private Context context;
    private int resource;
    private ImageView imageView;
    private Bitmap bitmap;

    public OnlineMusicAdapter(List<OnlineMusicResult> musicTop,Context context, int resource) {
        this.musicTop = musicTop;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return musicTop.size();
    }

    @Override
    public Object getItem(int position) {
        return musicTop.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(musicTop.get(position).getSong_list()[position].getArtist_id());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource,parent,false);
        BaiduSong[] baiduSongs = musicTop.get(position).getSong_list();
        imageView = convertView.findViewById(R.id.top_pic);
        TextView first = convertView.findViewById(R.id.first_music);
        TextView second = convertView.findViewById(R.id.second_music);
        TextView third = convertView.findViewById(R.id.third_music);
        System.out.println(musicTop.get(position).getBillboard().getBg_pic());
//        OnlineMusicUtil.getPic(musicTop.get(position).getBillboard().getBg_pic(),convertView,R.id.top_pic);
        MyTask task = new MyTask(imageView);
        task.execute(musicTop.get(position).getBillboard().getPic_s192());
//        image.setImageBitmap(bitmap);
        first.setText("1." + baiduSongs[0].getTitle());
        second.setText("2. " + baiduSongs[1].getTitle());
        third.setText("3. " + baiduSongs[2].getTitle());
        return convertView;
    }


}
