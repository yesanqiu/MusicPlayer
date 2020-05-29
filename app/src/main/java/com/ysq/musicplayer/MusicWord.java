package com.ysq.musicplayer;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ysq.musicplayer.entity.Song;
import com.ysq.musicplayer.utils.MusicWordTask;
import com.ysq.musicplayer.utils.PlayListUtil;

public class MusicWord extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.music_word,container,false);
        TextView word = view.findViewById(R.id.mw_music_word);
        word.setMovementMethod(new ScrollingMovementMethod());
        Song song  = PlayListUtil.getSingSong();
        if(song.isOnlineMusic){
            MusicWordTask musicWordTask = new MusicWordTask(word);
            musicWordTask.execute(song.getRoot().getSonginfo().getLrclink());
        }else {
            word.setText("歌词");
        }
        return view;
    }
}
