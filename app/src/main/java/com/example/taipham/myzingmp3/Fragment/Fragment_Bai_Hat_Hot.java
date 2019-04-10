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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.taipham.myzingmp3.Adapter.HotSongAdapter;
import com.example.taipham.myzingmp3.Model.Baihat;
import com.example.taipham.myzingmp3.R;
import com.example.taipham.myzingmp3.Service.APIService;
import com.example.taipham.myzingmp3.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Bai_Hat_Hot extends Fragment {
    private View view;
    private RecyclerView recyclerViewBaiHatHot;
    private HotSongAdapter hotSongAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bai_hat_hot,container,false);
        initWidget();
        GetData();
        return view;
    }

    private void initWidget() {
        recyclerViewBaiHatHot = view.findViewById(R.id.rv_baihathot);

        // Cmt nè các má: Cái này là để recyclerView đỡ bị lag nha, khi mà cho recyclerview vào trong gridview
        recyclerViewBaiHatHot.setNestedScrollingEnabled(false);
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetBaiHatHot();
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                ArrayList<Baihat> baihatArrayList = (ArrayList<Baihat>) response.body();
                hotSongAdapter = new HotSongAdapter(getActivity(),baihatArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewBaiHatHot.setLayoutManager(linearLayoutManager);
                recyclerViewBaiHatHot.setAdapter(hotSongAdapter);


//                Toast.makeText(getContext(), "baihatArrayList: " + baihatArrayList.get(0).getTenbaihat(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {
//                Toast.makeText(getContext(), "baihatArrayList Loi roi nhe!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
