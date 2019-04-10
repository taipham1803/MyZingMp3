package com.example.taipham.myzingmp3.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.StrictMode;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.taipham.myzingmp3.Adapter.ListSongAdapter;
import com.example.taipham.myzingmp3.Model.Album;
import com.example.taipham.myzingmp3.Model.Baihat;
import com.example.taipham.myzingmp3.Model.Playlist;
import com.example.taipham.myzingmp3.Model.Quangcao;
import com.example.taipham.myzingmp3.Model.TheLoai;
import com.example.taipham.myzingmp3.R;
import com.example.taipham.myzingmp3.Service.APIService;
import com.example.taipham.myzingmp3.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSongActivity extends AppCompatActivity {

    private Quangcao quangcao;
    private CoordinatorLayout coordinatorLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private RecyclerView recyclerViewListSong;
    private FloatingActionButton floatingActionButtonListSong;
    private ImageView ivListSong;
    private ListSongAdapter listSongAdapter;
    private Playlist playlist;
    private TheLoai theLoai;
    private Album album;

    public static ArrayList<Baihat> baihatArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_song);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        DataIntent();
        initWidget();
        init();

        if(quangcao != null && !quangcao.getTenBaiHat().equals("")){
            setValueInView(quangcao.getTenBaiHat(),quangcao.getHinhBaiHat());
            GetDataQuangcao(quangcao.getIdQuangCao());
        }

        if (playlist != null && !playlist.getTen().equals("")){
            setValueInView(playlist.getTen(),playlist.getHinhPlaylist());
            GetDataPlaylist(playlist.getIdPlaylist());
        }
        if (theLoai != null && !theLoai.getTenTheLoai().equals("")){
            setValueInView(theLoai.getTenTheLoai(),theLoai.getHinhTheLoai());
            GetDataTheloai(theLoai.getIdTheLoai());
        }

        if (album != null && !album.getTenAlbum().equals("")){
            setValueInView(album.getTenAlbum(),album.getHinhAlbum());
            GetDatalbum(album.getIdAlbum());
        }

    }

    private void GetDatalbum(String idAlbum) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetDanhSachBaiHatTheoAlbum(idAlbum);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                baihatArrayList = (ArrayList<Baihat>) response.body();
                listSongAdapter = new ListSongAdapter(ListSongActivity.this,baihatArrayList);
                recyclerViewListSong.setLayoutManager(new LinearLayoutManager(ListSongActivity.this));
                recyclerViewListSong.setAdapter(listSongAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });

    }

    private void GetDataTheloai(String idtheloai) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetDanhSachBaiHatTheoTheLoai(idtheloai);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                baihatArrayList = (ArrayList<Baihat>) response.body();
                listSongAdapter = new ListSongAdapter(ListSongActivity.this,baihatArrayList);
                recyclerViewListSong.setLayoutManager(new LinearLayoutManager(ListSongActivity.this));
                recyclerViewListSong.setAdapter(listSongAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void GetDataPlaylist(String idplaylist) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetDanhsachbaihattheoplaylist(idplaylist);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                baihatArrayList = (ArrayList<Baihat>) response.body();
                listSongAdapter = new ListSongAdapter(ListSongActivity.this,baihatArrayList);
                recyclerViewListSong.setLayoutManager(new LinearLayoutManager(ListSongActivity.this));
                recyclerViewListSong.setAdapter(listSongAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });

    }

    private void setValueInView(String ten, String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        try {
            URL url = new URL(hinh);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bitmap);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                collapsingToolbarLayout.setBackground(bitmapDrawable);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Picasso.with(this).load(hinh).into(ivListSong);
    }

    private void GetDataQuangcao(String idquangcao) {

        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetDanhSachBaiHatTheoQuangCao(idquangcao);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                baihatArrayList = (ArrayList<Baihat>) response.body();
//                Toast.makeText(ListSongActivity.this, "Danh sach bai hat: "+ baihatArrayList.get(0).getTenbaihat(), Toast.LENGTH_SHORT).show();

                listSongAdapter = new ListSongAdapter(ListSongActivity.this,baihatArrayList);
                recyclerViewListSong.setLayoutManager(new LinearLayoutManager(ListSongActivity.this));
                recyclerViewListSong.setAdapter(listSongAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }


    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        floatingActionButtonListSong.setEnabled(false);
    }

    private void initWidget() {
        coordinatorLayout = findViewById(R.id.coordinatorLayoutListSong);
        collapsingToolbarLayout = findViewById(R.id.collapsing_listsong);
        toolbar = findViewById(R.id.toolbar_listsong);
        recyclerViewListSong = findViewById(R.id.rv_listsong);
        floatingActionButtonListSong = findViewById(R.id.floatingbutton_listsong);
        ivListSong = findViewById(R.id.iv_listSong);
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if (intent != null){
            if (intent.hasExtra("banner")){
                quangcao = (Quangcao) intent.getSerializableExtra("banner");
            }
        }
        if(intent.hasExtra("itemplaylist")){
            playlist = (Playlist) intent.getSerializableExtra("itemplaylist");
        }

        if (intent.hasExtra("idtheloai")){
            theLoai = (TheLoai) intent.getSerializableExtra("idtheloai");
        }

        if (intent.hasExtra("album")){
            album = (Album) intent.getSerializableExtra("album");
        }
    }

    private void eventClick(){
        floatingActionButtonListSong.setEnabled(true);
        floatingActionButtonListSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListSongActivity.this,PlayMusicActivity.class);
                intent.putExtra("cacbaihat",baihatArrayList);
                startActivity(intent);
            }
        });
    }
}
