package com.ysq.musicplayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ysq.musicplayer.adapter.OnlineMusicAdapter;
import com.ysq.musicplayer.entity.OnlineMusicResult;
import com.ysq.musicplayer.utils.OnlineMusicUtil;

import java.util.List;

import static com.ysq.musicplayer.utils.OnlineMusicUtil.getURLimage;

public class OnlineMusic extends Fragment {

    private OnlineMusicAdapter onlineMusicAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.online_music,container,false);
        OnlineMusicUtil.initTopMusicList();
        final List<OnlineMusicResult> onlineMusicResult = OnlineMusicUtil.getResult();
        ListView listView = view.findViewById(R.id.online_music_view);


        onlineMusicAdapter = new OnlineMusicAdapter(onlineMusicResult,this.getContext(),R.layout.online_music_line);
        listView.setAdapter(onlineMusicAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("position:" + position);
                System.out.println("id:" + id);
                String name = onlineMusicResult.get(position).getBillboard().getName();
                System.out.println(name);
                Intent intent = new Intent(getContext(),OnlineMusicTopList.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
        return view;
    }



}
