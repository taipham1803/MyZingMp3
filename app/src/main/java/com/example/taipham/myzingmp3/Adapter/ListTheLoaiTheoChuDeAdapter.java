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
import com.example.taipham.myzingmp3.Activity.ListTheLoaiTheoChuDeActivity;
import com.example.taipham.myzingmp3.Model.TheLoai;
import com.example.taipham.myzingmp3.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListTheLoaiTheoChuDeAdapter extends RecyclerView.Adapter<ListTheLoaiTheoChuDeAdapter.ViewHolder>{

    private Context context;
    private ArrayList<TheLoai> theLoaiArrayList;

    public ListTheLoaiTheoChuDeAdapter(Context context, ArrayList<TheLoai> theLoaiArrayList) {
        this.context = context;
        this.theLoaiArrayList = theLoaiArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_theloai_theo_chude,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TheLoai theLoai = theLoaiArrayList.get(position);
        Picasso.with(context).load(theLoai.getHinhTheLoai()).into(holder.ivHinhNenTheloai);
        holder.tvTenTheLoai.setText(theLoai.getTenTheLoai());
    }

    @Override
    public int getItemCount() {
        return theLoaiArrayList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivHinhNenTheloai;
        TextView tvTenTheLoai;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivHinhNenTheloai = itemView.findViewById(R.id.iv_theloaitheochude);
            tvTenTheLoai = itemView.findViewById(R.id.tv_tentheloai_theochude);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,ListSongActivity.class);
                    intent.putExtra("idtheloai",theLoaiArrayList.get(getLayoutPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }
}
