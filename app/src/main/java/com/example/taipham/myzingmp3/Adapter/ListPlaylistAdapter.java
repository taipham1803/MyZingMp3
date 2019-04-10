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
import com.example.taipham.myzingmp3.Activity.ListplaylistActivity;
import com.example.taipham.myzingmp3.Model.Playlist;
import com.example.taipham.myzingmp3.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListPlaylistAdapter extends RecyclerView.Adapter<ListPlaylistAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Playlist> arrayPlaylist;

    public ListPlaylistAdapter(Context context, ArrayList<Playlist> arrayPlaylist) {
        this.context = context;
        this.arrayPlaylist = arrayPlaylist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_list_playlist,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Playlist playlist = arrayPlaylist.get(position);
        Picasso.with(context).load(playlist.getHinhPlaylist()).into(holder.ivHinhNen);
        holder.tvTenPlaylist.setText(playlist.getTen());

    }

    @Override
    public int getItemCount() {
        return arrayPlaylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivHinhNen;
        TextView tvTenPlaylist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivHinhNen = itemView.findViewById(R.id.iv_listplaylist);
            tvTenPlaylist = itemView.findViewById(R.id.tv_title_listplaylist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,ListSongActivity.class);
                    intent.putExtra("itemplaylist",arrayPlaylist.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });


        }
    }
}
