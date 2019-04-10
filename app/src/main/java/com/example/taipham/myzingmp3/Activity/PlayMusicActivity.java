package com.example.taipham.myzingmp3.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taipham.myzingmp3.Adapter.HotSongAdapter;
import com.example.taipham.myzingmp3.Fragment.Fragment_Dia_Nhac;
import com.example.taipham.myzingmp3.Fragment.Fragment_Play_Listsong;
import com.example.taipham.myzingmp3.Model.Baihat;
import com.example.taipham.myzingmp3.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayMusicActivity extends AppCompatActivity {

    public static MediaPlayer mediaPlayer = new MediaPlayer();
    public static Baihat baihatlive = null;

    private Toolbar toolbarPlaymusic;
    private ViewPager viewPagerPlaymusic;
    private TextView tvTimesong;
    private TextView tvTotalTimesong;
    private SeekBar seekBarPlaymusic;
    private ImageButton ibPlay;
    private ImageButton ibSuffle;
    private ImageButton ibPreview;
    private ImageButton ibNext;
    private ImageButton ibRepeat;
    public static ArrayList<Baihat> listSongToPlay = new ArrayList<>();

    public static ViewPagerPlayMusic adapternhac;
    private Fragment_Dia_Nhac fragment_dia_nhac;
    private Fragment_Play_Listsong fragment_play_listsong;

//    private static MediaPlayer mediaPlayer;

    private int position = 0;
    private boolean repeat = false;
    private boolean checkrandom = false;
    private boolean next = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        initWidget();
        setBackActivity();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        addFragment();
        GetDataFromIntent();

//        PlayMusic();
        eventClick();

    }


    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adapternhac.getItem(1) != null) {
                    if (listSongToPlay.size() > 0) {
                        fragment_dia_nhac.Playnhac(baihatlive.getHinhbaihat());
                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this, 300);
                    }
                }

            }
        }, 500);

        ibPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer != null) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                        ibPlay.setImageResource(R.drawable.iconplay);
                    } else {
                        mediaPlayer.start();
                        ibPlay.setImageResource(R.drawable.iconpause);
                    }
                }
            }
        });

        ibRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (repeat == false) {
                    if (checkrandom == true) {
                        checkrandom = false;
                        ibRepeat.setImageResource(R.drawable.iconsyned);
                        ibSuffle.setImageResource(R.drawable.iconsuffle);
                    }
                    ibRepeat.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                } else {
                    ibRepeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });

        ibSuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkrandom == false) {
                    if (repeat == true) {
                        repeat = false;
                        ibRepeat.setImageResource(R.drawable.iconrepeat);
                        ibSuffle.setImageResource(R.drawable.iconshuffled);
                    }
                    ibSuffle.setImageResource(R.drawable.iconshuffled);
                    checkrandom = true;
                } else {
                    ibSuffle.setImageResource(R.drawable.iconsuffle);
                    checkrandom = false;
                }
            }
        });

        seekBarPlaymusic.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        ibNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listSongToPlay.size() > 1) {
                    if (mediaPlayer != null || mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < listSongToPlay.size()) {
                        ibPlay.setImageResource(R.drawable.iconpause);
                        position++;
                        if (repeat == true) {
                            if (position == 0) {
                                position = listSongToPlay.size();
                            }
                            position -= 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(listSongToPlay.size());
                            if (index == position) {
                                position = index - 1;

                            }
                            position = index;
                        }
                        if (position > listSongToPlay.size() - 1) {
                            position = 0;

                        }
                        new PlayMp3().execute(listSongToPlay.get(position).getLinkbaihat());
                        fragment_dia_nhac.Playnhac(listSongToPlay.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(listSongToPlay.get(position).getTenbaihat());
                        UpdateTime();
                    }
                }
                ibPreview.setClickable(false);
                ibNext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ibPreview.setClickable(true);
                        ibNext.setClickable(true);
                    }
                }, 5000);
            }

        });

        ibPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listSongToPlay.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < listSongToPlay.size()) {
                        ibPlay.setImageResource(R.drawable.iconpause);
                        position--;
                        if (position < 0) {
                            position = listSongToPlay.size() - 1;
                        }


                        if (repeat == true) {
                            position += 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(listSongToPlay.size());
                            if (index == position) {
                                position = index - 1;

                            }
                            position = index;
                        }
                        new PlayMp3().execute(listSongToPlay.get(position).getLinkbaihat());
                        fragment_dia_nhac.Playnhac(listSongToPlay.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(listSongToPlay.get(position).getTenbaihat());
                        UpdateTime();
                    }
                }
                ibPreview.setClickable(false);
                ibNext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ibPreview.setClickable(true);
                        ibNext.setClickable(true);
                    }
                }, 3000);
            }
        });
    }

    public void PlayMusic() {
        fragment_dia_nhac = (Fragment_Dia_Nhac) adapternhac.getItem(0);
        if (listSongToPlay.size() > 0) {
            getSupportActionBar().setTitle(listSongToPlay.get(0).getTenbaihat());
            new PlayMp3().execute(listSongToPlay.get(0).getLinkbaihat());
            ibPlay.setImageResource(R.drawable.iconpause);
        }
    }

    public void PlayMusic(Baihat baihat) {
        fragment_dia_nhac = (Fragment_Dia_Nhac) adapternhac.getItem(0);
        getSupportActionBar().setTitle(baihat.getTenbaihat());
        new PlayMp3().execute(baihat.getLinkbaihat());
        ibPlay.setImageResource(R.drawable.iconpause);

    }

//    protected void addFragment(Fragment fragment) {
//
//        FragmentManager fmgr = getSupportFragmentManager();
//
//        FragmentTransaction ft = fmgr.beginTransaction();
//
//        ft.add(R.id.container_body, fragment);
//
//        ft.addToBackStack(fragment.getClass().getSimpleName());
//
//        ft.commit();
//
//    }

    private void addFragment() {
        fragment_dia_nhac = new Fragment_Dia_Nhac();
        fragment_play_listsong = new Fragment_Play_Listsong();
        adapternhac = new ViewPagerPlayMusic(getSupportFragmentManager());
        adapternhac.addFragment(fragment_dia_nhac);
        adapternhac.addFragment(fragment_play_listsong);
        viewPagerPlaymusic.setAdapter(adapternhac);
    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        listSongToPlay.clear();


        if (intent != null) {
            if (intent.hasExtra("song")) {
                baihatlive = intent.getParcelableExtra("song");
                listSongToPlay.add(baihatlive);
                PlayMusic(baihatlive);
            }
            if (intent.hasExtra("cacbaihat")) {
                ArrayList<Baihat> listsong = intent.getParcelableArrayListExtra("cacbaihat");
                listSongToPlay = listsong;
                baihatlive = listSongToPlay.get(0);
                PlayMusic();
            }
            if (intent.hasExtra("hotsong")) {
                baihatlive = intent.getParcelableExtra("hotsong");
                listSongToPlay.addAll(HotSongAdapter.baihathotArrayList);
                PlayMusic(baihatlive);
            }
            if (intent.hasExtra("songalbum")) {
                baihatlive = intent.getParcelableExtra("songalbum");
                listSongToPlay.addAll(ListSongActivity.baihatArrayList);
                PlayMusic(baihatlive);
            }
        }

    }

    private void setBackActivity() {
        setSupportActionBar(toolbarPlaymusic);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPlaymusic.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                mediaPlayer.stop();
                listSongToPlay.clear();
            }
        });
        toolbarPlaymusic.setTitleTextColor(Color.WHITE);
    }

    private void initWidget() {
        toolbarPlaymusic = findViewById(R.id.toolbar_playmusic);
        viewPagerPlaymusic = findViewById(R.id.viewpager_playmusic);
        tvTimesong = findViewById(R.id.tv_timesong_playmusic);
        tvTotalTimesong = findViewById(R.id.tv_totaltimesong_playmusic);
        seekBarPlaymusic = findViewById(R.id.seekbar_playmusic);
        ibPlay = findViewById(R.id.ib_play);
        ibSuffle = findViewById(R.id.ib_suffle);
        ibPreview = findViewById(R.id.ib_preview);
        ibNext = findViewById(R.id.ib_next);
        ibRepeat = findViewById(R.id.ib_repeat);

    }


    class PlayMp3 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            if (mediaPlayer != null) {
                if (mediaPlayer.isPlaying())
                    mediaPlayer.stop();
                mediaPlayer.reset();


                try {
//                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mediaPlayer.stop();
                            mediaPlayer.reset();
                        }
                    });
                    mediaPlayer.setDataSource(baihat);
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mediaPlayer.start();
                TimeSong();
                UpdateTime();

            }

        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        tvTotalTimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBarPlaymusic.setMax(mediaPlayer.getDuration());
    }

    private void UpdateTime() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    seekBarPlaymusic.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    tvTimesong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }, 300);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true) {
                    if (listSongToPlay.size() > 0) {
                        if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                            mediaPlayer.stop();
                            mediaPlayer.release();
                            mediaPlayer = null;
                        }
                        if (position < listSongToPlay.size()) {
                            ibPlay.setImageResource(R.drawable.iconpause);
                            position++;
                            if (repeat == true) {
                                if (position == 0) {
                                    position = listSongToPlay.size();
                                }
                                position -= 1;
                            }
                            if (checkrandom == true) {
                                Random random = new Random();
                                int index = random.nextInt(listSongToPlay.size());
                                if (index == position) {
                                    position = index - 1;

                                }
                                position = index;
                            }
                            if (position > listSongToPlay.size() - 1) {
                                position = 0;

                            }
                            new PlayMp3().execute(listSongToPlay.get(position).getLinkbaihat());
                            fragment_dia_nhac.Playnhac(listSongToPlay.get(position).getHinhbaihat());
                            getSupportActionBar().setTitle(listSongToPlay.get(position).getTenbaihat());
                        }
                    }
                    ibPreview.setClickable(false);
                    ibNext.setClickable(false);
                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ibPreview.setClickable(true);
                            ibNext.setClickable(true);
                        }
                    }, 5000);
                    next = false;
                    handler1.removeCallbacks(this);
                } else {
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 1000);

    }


}
