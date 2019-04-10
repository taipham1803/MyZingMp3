package com.example.taipham.myzingmp3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taipham.myzingmp3.Activity.ListSongActivity;
import com.example.taipham.myzingmp3.Model.Quangcao;
import com.example.taipham.myzingmp3.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<Quangcao> arrayListbanner;
    private ImageView ivBackgroundBanner;
    private ImageView ivBanner;
    private TextView tvTitleBannerSong;
    private TextView tvDescriptionSong;


//    private LayoutInflater inflater = LayoutInflater.from(context);
//    private View view = inflater.inflate(R.layout.dong_banner,null);

    public BannerAdapter(Context context, ArrayList<Quangcao> arrayListbanner) {
        this.context = context;
        this.arrayListbanner = arrayListbanner;
    }

    @Override
    public int getCount() {
        return arrayListbanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_banner,null);
        ivBackgroundBanner = view.findViewById(R.id.iv_background_banner);
        ivBanner = view.findViewById(R.id.iv_banner);
        tvTitleBannerSong = view.findViewById(R.id.tv_title_banner_song);
        tvDescriptionSong = view.findViewById(R.id.tv_description_song);

//        initWidget();
        Picasso.with(context).load(arrayListbanner.get(position).getHinhanh()).into(ivBackgroundBanner);
        Picasso.with(context).load(arrayListbanner.get(position).getHinhBaiHat()).into(ivBanner);
        tvDescriptionSong.setText(arrayListbanner.get(position).getNoidung());
        tvTitleBannerSong.setText(arrayListbanner.get(position).getTenBaiHat());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "Da click vao page!", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(context,ListSongActivity.class);
//                intent.putExtra("banner",arrayListbanner.get(position));


                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id="+arrayListbanner.get(position).getLinkBaiHat()));
//                startActivity(intent);
                context.startActivity(intent);
            }
        });

        onEvent();

        container.addView(view);
        return view;
    }

    private void onEvent() {

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

//    private void initWidget(){
//        ivBackgroundBanner = view.findViewById(R.id.iv_background_banner);
//        ivBanner = view.findViewById(R.id.iv_banner);
//        tvTitleBannerSong = view.findViewById(R.id.tv_title_banner_song);
//        tvDescriptionSong = view.findViewById(R.id.tv_description_song);
//    }



}
