package com.ysq.musicplayer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ysq.musicplayer.entity.Song;
import com.ysq.musicplayer.utils.LocalMusicUtil;
import com.ysq.musicplayer.utils.MyTask;
import com.ysq.musicplayer.utils.PlayListUtil;

public class MusicPic extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.musicpic,container,false);
        ImageView pic = view.findViewById(R.id.mp_music_pic);
        Song song = PlayListUtil.getSingSong();
        if(song.isOnlineMusic){
            MyTask myTask = new MyTask(pic);
            myTask.execute(song.getRoot().getSonginfo().getPic_premium());
        }else {
            pic.setImageBitmap(LocalMusicUtil.getArtwork(this.getContext(), song.getId(), song.getAlbumId(), false, false));
        }
        return view;
    }
}
