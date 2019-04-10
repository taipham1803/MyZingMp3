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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSongAdapter extends RecyclerView.Adapter<ListSongAdapter.ViewHolder>{

    Context context;
    ArrayList<Baihat> baihatArrayList;

    public ListSongAdapter(Context context, ArrayList<Baihat> baihatArrayList) {
        this.context = context;
        this.baihatArrayList = baihatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_listsong,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Baihat baihat = baihatArrayList.get(position);
        viewHolder.tvTenCaSi.setText(baihat.getCasi());
        viewHolder.tvTenBaiHat.setText(baihat.getTenbaihat());
        viewHolder.tvIndex.setText(position+1+"");
//        viewHolder.ivHinhBaiHat.
//        Picasso.with(context).load(baihat.getHinhbaihat()).into(viewHolder.ivLuotThich);
    }

    @Override
    public int getItemCount() {
        return baihatArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvIndex;
        private TextView tvTenCaSi;
        private TextView tvTenBaiHat;
        private ImageView ivLuotThich;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvIndex = itemView.findViewById(R.id.tv_danhsach_index);
            tvTenBaiHat = itemView.findViewById(R.id.tv_title_song);
            tvTenCaSi = itemView.findViewById(R.id.tv_tencasi);
            ivLuotThich = itemView.findViewById(R.id.iv_soluotthich);

            ivLuotThich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(context, "Ten ca si: " + baihatArrayList.get(getLayoutPosition()).getTenbaihat(), Toast.LENGTH_SHORT).show();
                    ivLuotThich.setImageResource(R.drawable.iconloved);

                    Dataservice dataservice = APIService.getService();
                    Call<String> callback = dataservice.UpdateLuotThich("1",baihatArrayList.get(getPosition()).getIdbaihat());
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
                    Intent intent = new Intent(context,PlayMusicActivity.class);
//                    intent.putExtra("song",baihatArrayList.get(getLayoutPosition()));
                    intent.putExtra("songalbum",baihatArrayList.get(getLayoutPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }

}
