package com.example.taipham.myzingmp3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taipham.myzingmp3.Activity.PlayMusicActivity;
import com.example.taipham.myzingmp3.Model.Baihat;
import com.example.taipham.myzingmp3.R;
import com.example.taipham.myzingmp3.Service.APIService;
import com.example.taipham.myzingmp3.Service.Dataservice;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayMusicAdapter extends RecyclerView.Adapter<PlayMusicAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Baihat> listSongPlayMusic;

    public PlayMusicAdapter(Context context, ArrayList<Baihat> listSongPlayMusic) {
        this.context = context;
        this.listSongPlayMusic = listSongPlayMusic;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_playmusic,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Baihat baihat = listSongPlayMusic.get(i);
        viewHolder.tvSongIndex.setText(i + 1 + "");
        viewHolder.tvTitleSong.setText(baihat.getTenbaihat());
        viewHolder.tvNameSinger.setText(baihat.getCasi());
    }

    @Override
    public int getItemCount() {
        return listSongPlayMusic.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitleSong;
        TextView tvNameSinger;
        TextView tvSongIndex;
        ImageView ivLuotThich;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            tvTitleSong = itemView.findViewById(R.id.tv_title_song_playmusic);
            tvNameSinger = itemView.findViewById(R.id.tv_name_singer_playmusic);
            tvSongIndex = itemView.findViewById(R.id.tv_song_index_playmusic);
            ivLuotThich = itemView.findViewById(R.id.iv_soluotthich);

            ivLuotThich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ivLuotThich.setImageResource(R.drawable.iconloved);

                    Dataservice dataservice = APIService.getService();
                    Call<String> callback = dataservice.UpdateLuotThich("1",listSongPlayMusic.get(getPosition()).getIdbaihat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if (ketqua.equals("Success!")){
                                Toast.makeText(context, "Đã thích!", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context, "Bị Lỗi!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });

                    ivLuotThich.setEnabled(false);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    PlayMusicActivity.listSongToPlay.add(listSongPlayMusic.get(getLayoutPosition()));

                    PlayMusicActivity.baihatlive = listSongPlayMusic.get(getLayoutPosition());
                }
            });

        }
    }
}
