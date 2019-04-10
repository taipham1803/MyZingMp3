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

public class SearchSongAdapter extends RecyclerView.Adapter<SearchSongAdapter.ViewHoler>{
    Context context;
    ArrayList<Baihat> baihatArrayList;

    public SearchSongAdapter(Context context, ArrayList<Baihat> baihatArrayList) {
        this.context = context;
        this.baihatArrayList = baihatArrayList;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.dong_search_song,viewGroup,false);

        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler viewHoler, int i) {
        Baihat baihat = baihatArrayList.get(i);
        viewHoler.tvTenBaiHat.setText(baihat.getTenbaihat());
        viewHoler.tvTenCaSi.setText(baihat.getCasi());
        Picasso.with(context).load(baihat.getLinkbaihat()).into(viewHoler.ivHinhBaiHat);
    }

    @Override
    public int getItemCount() {
        return baihatArrayList.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {

        private TextView tvTenBaiHat;
        private TextView tvTenCaSi;
        private ImageView ivHinhBaiHat;
        private ImageView ivLuotThich;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            tvTenBaiHat = itemView.findViewById(R.id.tv_tenbaihat_searchsong);
            tvTenCaSi = itemView.findViewById(R.id.tv_tencasi_searchsong);
            ivHinhBaiHat = itemView.findViewById(R.id.iv_hinhbaihat_seachsong);
            ivLuotThich = itemView.findViewById(R.id.iv_luotthich_searchsong);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,PlayMusicActivity.class);
                    intent.putExtra("song",baihatArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });

            ivLuotThich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ivLuotThich.setImageResource(R.drawable.iconloved);
                    Dataservice dataservice = APIService.getService();
                    Call<String> callback = dataservice.UpdateLuotThich("1",baihatArrayList.get(getPosition()).getIdbaihat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String result = response.body();
                            if (result.equals("success")) {
                                Toast.makeText(context, "Da thich rui nha", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context, "Loi luot thich!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    ivLuotThich.setEnabled(false);
                }
            });
        }
    }
}
