package com.example.taipham.myzingmp3.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taipham.myzingmp3.Activity.ListSongActivity;
import com.example.taipham.myzingmp3.Activity.ListplaylistActivity;
import com.example.taipham.myzingmp3.Adapter.PlaylistAdapter;
import com.example.taipham.myzingmp3.Model.Playlist;
import com.example.taipham.myzingmp3.R;
import com.example.taipham.myzingmp3.Service.APIService;
import com.example.taipham.myzingmp3.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Playlist extends Fragment {
    private View view;
    private TextView tvTitlePlaylist;
    private TextView tvViewMorePlaylist;
    private ListView lvPlaylist;
    private PlaylistAdapter playlistAdapter;
    private ArrayList<Playlist> arrayPlayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlist,container,false);
        initWidget();
        GetData();

        tvViewMorePlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ListplaylistActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }

    private void initWidget() {
        tvTitlePlaylist = view.findViewById(R.id.tv_title_playlist);
        tvViewMorePlaylist = view.findViewById(R.id.tv_viewmoreplaylist);
        lvPlaylist = view.findViewById(R.id.lv_playlist);
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Playlist>> callback = dataservice.GetPlayListCurrentDay();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                arrayPlayList = (ArrayList<Playlist>) response.body();
//                Toast.makeText(getContext(), "Ten Playlist: " + arrayPlayList.get(0).getTen(), Toast.LENGTH_SHORT).show();
                playlistAdapter = new PlaylistAdapter(getActivity(),android.R.layout.simple_list_item_1,arrayPlayList);
                lvPlaylist.setAdapter(playlistAdapter);
                setListViewHeightBasedOnChildren(lvPlaylist);

                lvPlaylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        Intent intent = new Intent(getActivity(),ListSongActivity.class);
                        intent.putExtra("itemplaylist",arrayPlayList.get(position));
                        startActivity(intent);
                    }
                });





            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if(listItem != null){
                // This next line is needed before you call measure or else you won't get measured height at all. The listitem needs to be drawn first to know the height.
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
