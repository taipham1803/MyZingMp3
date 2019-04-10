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

import com.example.taipham.myzingmp3.Activity.ListSongActivity;
import com.example.taipham.myzingmp3.Model.Album;
import com.example.taipham.myzingmp3.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Album> arrayAlbum;

    public AlbumAdapter(Context context, ArrayList<Album> arrayAlbum) {
        this.context = context;
        this.arrayAlbum = arrayAlbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_album,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Album album = arrayAlbum.get(i);
        holder.tvTitleAlbum.setText(album.getTenAlbum());
        holder.tvTenCaSiAlbum.setText(album.getTenCaSiAlbum());
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.ivHinhAlbum);

    }

    @Override
    public int getItemCount() {
        return arrayAlbum.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivHinhAlbum;
        private TextView tvTitleAlbum;
        private TextView tvTenCaSiAlbum;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            ivHinhAlbum = itemView.findViewById(R.id.iv_album);
            tvTitleAlbum = itemView.findViewById(R.id.tv_titlealbum);
            tvTenCaSiAlbum = itemView.findViewById(R.id.tv_tencasialbum);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,ListSongActivity.class);
                    intent.putExtra("album",arrayAlbum.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }

    }
}
