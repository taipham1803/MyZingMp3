package com.example.taipham.myzingmp3.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.taipham.myzingmp3.Adapter.BannerAdapter;
import com.example.taipham.myzingmp3.Model.Quangcao;
import com.example.taipham.myzingmp3.R;
import com.example.taipham.myzingmp3.Service.APIService;
import com.example.taipham.myzingmp3.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Banner extends Fragment {

    private View view;
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private BannerAdapter bannerAdapter;
    private Handler handler;
    private Runnable runnable;
    private int currentItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner,container,false);
        initWidget();
        GetData();
        return view;
    }

    private void initWidget() {
        viewPager = view.findViewById(R.id.viewpagerBanner);
        circleIndicator = view.findViewById(R.id.indicatordefault);
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Quangcao>> callback = dataservice.GetDataBanner();
        callback.enqueue(new Callback<List<Quangcao>>() {
            @Override
            public void onResponse(final Call<List<Quangcao>> call, Response<List<Quangcao>> response) {
                ArrayList<Quangcao> banners = (ArrayList<Quangcao>) response.body();
                bannerAdapter = new BannerAdapter(getActivity(),banners);
                viewPager.setAdapter(bannerAdapter);
                circleIndicator.setViewPager(viewPager);
                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        currentItem = viewPager.getCurrentItem();
                        currentItem++;
                        if(currentItem>= viewPager.getAdapter().getCount()){
                            currentItem = 0;
                        }

                        viewPager.setCurrentItem(currentItem,true);
                        handler.postDelayed(runnable,4500);

                    }
                };



                if (banners != null){
//                    Toast.makeText(getContext(), "banners is not null", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getContext(), "Ten bai hat: " + banners.get(0).getTenBaiHat(), Toast.LENGTH_SHORT).show();
                }else {
//                    Toast.makeText(getContext(), "banners is null", Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onFailure(Call<List<Quangcao>> call, Throwable t) {
                Log.d("Fail cmnr!","Khong lay duoc response");
            }
        });
    }
}
