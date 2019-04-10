package com.example.taipham.myzingmp3.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.taipham.myzingmp3.Adapter.MainViewPagerAdapter;
import com.example.taipham.myzingmp3.Fragment.Fragment_Bai_Hat_Hot;
import com.example.taipham.myzingmp3.Fragment.Fragment_Home;
import com.example.taipham.myzingmp3.Fragment.Fragment_Search;
import com.example.taipham.myzingmp3.R;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(MainActivity.this, FlashActivity.class);
        startActivity(intent);

        initWidget();
        itit();
    }

    private void itit() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_Home(),"Home");
        mainViewPagerAdapter.addFragment(new Fragment_Bai_Hat_Hot(),"Search");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconmusic);
    }

    private void initWidget(){
        viewPager = findViewById(R.id.myViewPager);
        tabLayout = findViewById(R.id.myTabLayout);
    }

}
