package com.example.taipham.myzingmp3.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taipham.myzingmp3.Activity.PlayMusicActivity;
import com.example.taipham.myzingmp3.Adapter.PlayMusicAdapter;
import com.example.taipham.myzingmp3.R;

public class Fragment_Play_Listsong extends Fragment {
    private View view;
    private RecyclerView recyclerViewListSongToPlay;
    private PlayMusicAdapter playMusicAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_listsong,container,false);
        recyclerViewListSongToPlay = view.findViewById(R.id.rv_listsongtoplay);
        if (PlayMusicActivity.listSongToPlay.size() > 0){
            playMusicAdapter = new PlayMusicAdapter(getActivity(),PlayMusicActivity.listSongToPlay);
            recyclerViewListSongToPlay.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewListSongToPlay.setAdapter(playMusicAdapter);
        }
        return view;
    }

    private void GetData(){

    }


}
