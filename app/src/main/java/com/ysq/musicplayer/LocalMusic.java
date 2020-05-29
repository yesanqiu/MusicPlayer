package com.ysq.musicplayer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ysq.musicplayer.adapter.MusicAdapter;
import com.ysq.musicplayer.entity.Song;
import com.ysq.musicplayer.utils.LocalMusicUtil;
import com.ysq.musicplayer.utils.MediaUtil;
import com.ysq.musicplayer.utils.PlayListUtil;

import java.util.List;

public class LocalMusic extends Fragment {

    private List<Song> songs;
    private MusicAdapter musicAdapter;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.local_music,container,false);
        context = this.getContext();
        songs = LocalMusicUtil.getmusic(context);
        ListView listView = view.findViewById(R.id.musicView);
        musicAdapter = new MusicAdapter(this.getContext(),songs,R.layout.music_line);
        listView.setAdapter(musicAdapter);
//        listView.findViewById(R.id.)
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("id:"+ musicAdapter.getItemId(position));
                System.out.println("position:"+ position);

                PlayListUtil.add((Song) musicAdapter.getItem(position));
                if(PlayListUtil.getPlayList().size()==1){
                    Song song = PlayListUtil.getPlayList().get(0);
                    MediaUtil.playMusic();

                    //获取首页歌曲控件
                    ImageView imageView = getActivity().findViewById(R.id.to_player);
                    imageView.setImageBitmap(LocalMusicUtil.getArtwork(getContext(),song.getId(),song.getAlbumId(),false,false));
                    TextView textView = getActivity().findViewById(R.id.index_music_name);
                    textView.setText(PlayListUtil.getPlayList().get(0).getName());
                    ImageButton imageButton = getActivity().findViewById(R.id.index_start);
                    imageButton.setImageResource(R.drawable.ic_play_bar_btn_pause);
                }

            }
        });
//        System.out.println("song:" + songs.get(0).toString());
        return view;
    }
}
