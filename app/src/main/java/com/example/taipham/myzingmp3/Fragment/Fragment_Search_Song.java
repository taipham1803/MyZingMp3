package com.example.taipham.myzingmp3.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import com.example.taipham.myzingmp3.Adapter.SearchSongAdapter;
import com.example.taipham.myzingmp3.Model.Baihat;
import com.example.taipham.myzingmp3.R;
import com.example.taipham.myzingmp3.Service.APIService;
import com.example.taipham.myzingmp3.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Search_Song extends Fragment {

    private View view;
    private AppBarLayout appBarLayoutSearchSong;
    private Toolbar toolbarSearchSong;
    private RecyclerView rvSearchSong;
    private TextView tvDataNullSeachSong;
    private SearchSongAdapter searchSongAdapter;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search_song,container,false);
        appBarLayoutSearchSong = view.findViewById(R.id.appbarlayout_searchsong);
        toolbarSearchSong = view.findViewById(R.id.toolbar_searchsong);
        rvSearchSong = view.findViewById(R.id.rv_searchsong);
        tvDataNullSeachSong = view.findViewById(R.id.tv_datanull_searchsong);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbarSearchSong);
        toolbarSearchSong.setTitle("");
        setHasOptionsMenu(true);


        return view;
    }

    @Override
    public void onCreateOptionsMenu(final Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_view,menu);
        MenuItem menuItem = menu.findItem(R.menu.search_view);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                Toast.makeText(getContext(), "Query: " + query, Toast.LENGTH_SHORT).show();
                SearchKeyWordSong(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }


    public void SearchKeyWordSong(String query){
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetSearchSong(query);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                ArrayList<Baihat> listsongsearch = (ArrayList<Baihat>) response.body();
                if (listsongsearch.size() > 0){
                    searchSongAdapter = new SearchSongAdapter(getActivity(),listsongsearch);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    rvSearchSong.setLayoutManager(linearLayoutManager);
                    rvSearchSong.setAdapter(searchSongAdapter);
                    tvDataNullSeachSong.setVisibility(View.GONE);
                    rvSearchSong.setVisibility(View.VISIBLE);
                }else {
                    rvSearchSong.setVisibility(View.GONE);
                    tvDataNullSeachSong.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }
}
