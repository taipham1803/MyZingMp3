package com.example.taipham.myzingmp3.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.taipham.myzingmp3.Adapter.ListPlaylistAdapter;
import com.example.taipham.myzingmp3.Model.Playlist;
import com.example.taipham.myzingmp3.R;
import com.example.taipham.myzingmp3.Service.APIService;
import com.example.taipham.myzingmp3.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListplaylistActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerViewListPlaylist;
    private ListPlaylistAdapter listPlaylistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listplaylist);
        initWidget();
        init();
        GetData();
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Playlist>> callback = dataservice.GetDanhsachcacPlayList();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> playlistArrayList = (ArrayList<Playlist>) response.body();
//                Log.d("BBB",playlistArrayList.get(0).getTen());


                listPlaylistAdapter = new ListPlaylistAdapter(ListplaylistActivity.this,playlistArrayList);
                recyclerViewListPlaylist.setLayoutManager(new GridLayoutManager(ListplaylistActivity.this,2));
                recyclerViewListPlaylist.setAdapter(listPlaylistAdapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Play Lists");
        toolbar.setTitleTextColor(getResources().getColor(R.color.Purple));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initWidget() {
        toolbar = findViewById(R.id.toolbar_listplaylist);
        recyclerViewListPlaylist = findViewById(R.id.rv_listplaylist);
    }
}
