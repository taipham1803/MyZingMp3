package com.example.taipham.myzingmp3.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.taipham.myzingmp3.Adapter.AllChuDeAdapter;
import com.example.taipham.myzingmp3.Model.ChuDe;
import com.example.taipham.myzingmp3.R;
import com.example.taipham.myzingmp3.Service.APIService;
import com.example.taipham.myzingmp3.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListChuDeActivity extends AppCompatActivity {

    private Toolbar toolbarAllChuDe;
    private RecyclerView recyclerViewAllChuDe;
    private AllChuDeAdapter allChuDeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_chu_de);
        init();
        GetData();
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<ChuDe>> callback = dataservice.GetAllChuDe();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                ArrayList<ChuDe> chuDeArrayList = (ArrayList<ChuDe>) response.body();
//                Log.d("BBB",chuDeArrayList.get(0).getTenChuDe());
                allChuDeAdapter = new AllChuDeAdapter(ListChuDeActivity.this,chuDeArrayList);
                recyclerViewAllChuDe.setLayoutManager(new GridLayoutManager(ListChuDeActivity.this,1));
                recyclerViewAllChuDe.setAdapter(allChuDeAdapter);

            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });
    }

    private void init() {
        toolbarAllChuDe = findViewById(R.id.toolbar_allchude);
        recyclerViewAllChuDe = findViewById(R.id.rv_allchude);

        setSupportActionBar(toolbarAllChuDe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất cả chủ đề");
        toolbarAllChuDe.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
