package com.example.taipham.myzingmp3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.taipham.myzingmp3.Activity.ListTheLoaiTheoChuDeActivity;
import com.example.taipham.myzingmp3.Model.ChuDe;
import com.example.taipham.myzingmp3.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AllChuDeAdapter extends RecyclerView.Adapter<AllChuDeAdapter.ViewHolder>{
    private Context context;
    private ArrayList<ChuDe> arrayChuDe;

    public AllChuDeAdapter(Context context, ArrayList<ChuDe> arrayChuDe) {
        this.context = context;
        this.arrayChuDe = arrayChuDe;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_chude,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChuDe chuDe = arrayChuDe.get(position);
        Picasso.with(context).load(chuDe.getHinhChuDe()).into(holder.ivChuDe);

    }

    @Override
    public int getItemCount() {
        return arrayChuDe.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivChuDe;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivChuDe = itemView.findViewById(R.id.iv_dong_chude);
            ivChuDe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,ListTheLoaiTheoChuDeActivity.class);
                    intent.putExtra("chude",arrayChuDe.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
