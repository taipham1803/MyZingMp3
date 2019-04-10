package com.example.taipham.myzingmp3.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.taipham.myzingmp3.Adapter.AllAlbumAdapter;
import com.example.taipham.myzingmp3.Model.Album;
import com.example.taipham.myzingmp3.R;
import com.example.taipham.myzingmp3.Service.APIService;
import com.example.taipham.myzingmp3.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllAlbumActivity extends AppCompatActivity {

    private Toolbar toolbarAllAlbum;
    private RecyclerView recyclerViewAllAlbum;
    private AllAlbumAdapter allAlbumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_album);
        init();
        GetData();
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Album>> callback = dataservice.GetTatCaCacAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> albumArrayList = (ArrayList<Album>) response.body();
                allAlbumAdapter = new AllAlbumAdapter(AllAlbumActivity.this,albumArrayList);
                recyclerViewAllAlbum.setLayoutManager(new GridLayoutManager(AllAlbumActivity.this,2));
                recyclerViewAllAlbum.setAdapter(allAlbumAdapter);





//                Toast.makeText(AllAlbumActivity.this, "So album: " + albumArrayList.size(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    private void init() {
        toolbarAllAlbum = findViewById(R.id.toolbar_allalbum);
        recyclerViewAllAlbum = findViewById(R.id.rv_allalbum);
        setSupportActionBar(toolbarAllAlbum);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất cả các album");
        toolbarAllAlbum.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
