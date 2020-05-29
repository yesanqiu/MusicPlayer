package com.ysq.musicplayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ysq.musicplayer.adapter.MusicAdapter;
import com.ysq.musicplayer.adapter.PlayListAdapter;
import com.ysq.musicplayer.entity.Song;
import com.ysq.musicplayer.utils.PlayListUtil;

import java.util.List;

public class PlayList extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playlist);
        ImageView imageButton = findViewById(R.id.play_list_to_index);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlayList.this,Index.class));
            }
        });
        ListView listView = findViewById(R.id.mm);
        if(listView == null){
            System.out.println("listVIew");
        }else {
            if (PlayListUtil.getPlayList() != null) {
                PlayListAdapter playListAdapter = new PlayListAdapter(this, PlayListUtil.getPlayList(), R.layout.play_list_music_line);
                listView.setAdapter(playListAdapter);
            } else {
                System.out.println("null");
            }
        }
    }


}
