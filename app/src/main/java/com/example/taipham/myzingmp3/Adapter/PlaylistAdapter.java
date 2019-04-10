package com.example.taipham.myzingmp3.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.taipham.myzingmp3.Model.Playlist;
import com.example.taipham.myzingmp3.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlaylistAdapter extends ArrayAdapter<Playlist> {
    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<Playlist> objects) {
        super(context, resource, objects);
    }

    class ViewHolder{
        private TextView tvTitlePlaylist;
        private ImageView ivPlaylist;
        private  ImageView ivBackgroundPlaylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.dong_playlist,null);
            viewHolder = new ViewHolder();
            viewHolder.tvTitlePlaylist = convertView.findViewById(R.id.tv_title_playlist);
            viewHolder.ivPlaylist = convertView.findViewById(R.id.iv_playlist);
            viewHolder.ivBackgroundPlaylist = convertView.findViewById(R.id.iv_background_playlist);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Playlist playlist = getItem(position);
        Picasso.with(getContext()).load(playlist.getHinhPlaylist()).into(viewHolder.ivBackgroundPlaylist);
        Picasso.with(getContext()).load(playlist.getIcon()).into(viewHolder.ivPlaylist);
        viewHolder.tvTitlePlaylist.setText(playlist.getTen());
        return convertView;
    }


}
