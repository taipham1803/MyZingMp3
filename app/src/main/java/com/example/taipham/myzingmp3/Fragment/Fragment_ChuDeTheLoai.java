package com.example.taipham.myzingmp3.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taipham.myzingmp3.Activity.ListChuDeActivity;
import com.example.taipham.myzingmp3.Activity.ListSongActivity;
import com.example.taipham.myzingmp3.Activity.ListTheLoaiTheoChuDeActivity;
import com.example.taipham.myzingmp3.Model.ChuDe;
import com.example.taipham.myzingmp3.Model.ChuDeTheLoai;
import com.example.taipham.myzingmp3.Model.TheLoai;
import com.example.taipham.myzingmp3.Model.Theloaitrongngay;
import com.example.taipham.myzingmp3.R;
import com.example.taipham.myzingmp3.Service.APIService;
import com.example.taipham.myzingmp3.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_ChuDeTheLoai extends Fragment {
    private View view;
    private HorizontalScrollView hsvChuDeTheLoai;
    private TextView tvXemThemChuDeTheLoai;
    private TextView tvChuDeTheLoai;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chudetheloai,container,false);
        initWidget();
        onAction();
        GetData();
        return view;
    }

    private void onAction() {
        tvXemThemChuDeTheLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ListChuDeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initWidget() {
        hsvChuDeTheLoai = view.findViewById(R.id.hsv_chudetheloai);
        tvChuDeTheLoai = view.findViewById(R.id.tv_chudetheloai);
        tvXemThemChuDeTheLoai = view.findViewById(R.id.tv_xemthem);
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<ChuDeTheLoai> callback = dataservice.GetChuDeTheLoai();
        callback.enqueue(new Callback<ChuDeTheLoai>() {
            @Override
            public void onResponse(Call<ChuDeTheLoai> call, Response<ChuDeTheLoai> response) {
                ChuDeTheLoai chuDeTheLoai = response.body();

                if (chuDeTheLoai == null){
//                    Toast.makeText(getContext(), "chuDeTheLoai is Null!", Toast.LENGTH_SHORT).show();
                }

                final ArrayList<TheLoai> theLoaiArrayList = new ArrayList<>();
                theLoaiArrayList.addAll(chuDeTheLoai.getTheLoai());

                if (theLoaiArrayList == null){
//                    Toast.makeText(getContext(), "theLoaiArrayList is Null!", Toast.LENGTH_SHORT).show();
                }else {
//                    for (int i=0;i<theLoaiArrayList.size();i++){
//                        Toast.makeText(getContext(), "Cac The loai: " + theLoaiArrayList.get(i).getTenTheLoai(), Toast.LENGTH_SHORT).show();
//                    }
                }

                final ArrayList<ChuDe> chuDeArrayList = new ArrayList<>();
                chuDeArrayList.addAll(chuDeTheLoai.getChuDe());

                if (chuDeArrayList == null){
//                    Toast.makeText(getContext(), "chuDeArrayList is Null!", Toast.LENGTH_SHORT).show();
                }else {
//                    for (int i=0;i<chuDeArrayList.size();i++){
//                        Toast.makeText(getContext(), "Cac Chu de: " + chuDeArrayList.get(i).getTenChuDe(), Toast.LENGTH_SHORT).show();
//                    }
                }


                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(10,20,10,30);

//                for (int j = 0;j<(theLoaiArrayList.size());j++){
//                    CardView cardView = new CardView(getActivity());
//                    cardView.setRadius(10);
//                    ImageView imageView = new ImageView(getActivity());
//                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//
//                    if (theLoaiArrayList.get(j).getHinhTheLoai() != null){
//                        Picasso.with(getActivity()).load(theLoaiArrayList.get(j).getHinhTheLoai()).into(imageView);
//                    }
//                    cardView.setLayoutParams(layoutParams);
//                    cardView.addView(imageView);
//                    linearLayout.addView(cardView);
//
//                    final int finalJ = j;
//
//                    imageView.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            Intent intent = new Intent(getActivity(),ListSongActivity.class);
//                            intent.putExtra("theloai",theLoaiArrayList.get(finalJ));
//                            startActivity(intent);
//                        }
//                    });
//                }

                for (int i = 0;i<(chuDeArrayList.size());i++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    if (chuDeArrayList.get(i).getHinhChuDe() != null){
                        Picasso.with(getActivity()).load(chuDeArrayList.get(i).getHinhChuDe()).into(imageView);
                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);

                    final int finalI = i;

                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(),ListTheLoaiTheoChuDeActivity.class);
                            intent.putExtra("chude",chuDeArrayList.get(finalI));
                            startActivity(intent);
                        }
                    });
                }

                hsvChuDeTheLoai.addView(linearLayout);

            }

            @Override
            public void onFailure(Call<ChuDeTheLoai> call, Throwable t) {
//                Toast.makeText(getContext(), "Loi chuDeTheLoai Cmnr!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
