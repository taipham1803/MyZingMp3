package com.example.taipham.myzingmp3.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.taipham.myzingmp3.Adapter.ListTheLoaiTheoChuDeAdapter;
import com.example.taipham.myzingmp3.Model.ChuDe;
import com.example.taipham.myzingmp3.Model.TheLoai;
import com.example.taipham.myzingmp3.R;
import com.example.taipham.myzingmp3.Service.APIService;
import com.example.taipham.myzingmp3.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTheLoaiTheoChuDeActivity extends AppCompatActivity {

    private ChuDe chuDe;
    private Toolbar toolbarTheloaitheochude;
    private RecyclerView recyclerViewTheloaitheochude;
    private ListTheLoaiTheoChuDeAdapter listTheLoaiTheoChuDeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_the_loai_theo_chu_de);
        GetIntent();
        init();
        GetData();

    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<TheLoai>> callback = dataservice.GetTheLoaiTheoChuDe(chuDe.getIdChuDe());
        callback.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                ArrayList<TheLoai> arrayTheloai = (ArrayList<TheLoai>) response.body();

                listTheLoaiTheoChuDeAdapter = new ListTheLoaiTheoChuDeAdapter(ListTheLoaiTheoChuDeActivity.this,arrayTheloai);
                recyclerViewTheloaitheochude.setLayoutManager(new GridLayoutManager(ListTheLoaiTheoChuDeActivity.this,2));
                recyclerViewTheloaitheochude.setAdapter(listTheLoaiTheoChuDeAdapter);


//                if (arrayTheloai.size() == 0){
//                    Toast.makeText(ListTheLoaiTheoChuDeActivity.this, "arrayTheloai is null!", Toast.LENGTH_SHORT).show();
//                }
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });
    }

    private void init() {
        toolbarTheloaitheochude = findViewById(R.id.toolbar_theloaitheochude);
        recyclerViewTheloaitheochude = findViewById(R.id.rv_theloaitheochude);
        setSupportActionBar(toolbarTheloaitheochude);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chuDe.getTenChuDe());
        toolbarTheloaitheochude.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void GetIntent() {
        Intent intent = getIntent();
        if(intent.hasExtra("chude")){
            chuDe = (ChuDe) intent.getSerializableExtra("chude");
            Toast.makeText(this, "Ten chu de: " + chuDe.getTenChuDe(), Toast.LENGTH_SHORT).show();
        }
    }
}
