package com.example.mp3player.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.mp3player.Adapter.ViewPagerAdapter;
import com.example.mp3player.Fragment.FragmentPlayMusic;
import com.example.mp3player.Fragment.FragmentPlayMusicListSong;
import com.example.mp3player.Model.Song;
import com.example.mp3player.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;


public class PlayMusicActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private Toolbar mToolBar;
    private SeekBar sbPlayMusic;
    private TextView txtCurrentTime, txtTotalTime;
    private ImageButton imgbtnShuffle, imgbtnPre, imgbtnPlay, imgbtnNext, imgbtnRepeat;
    private ViewPagerAdapter adapter;
    private boolean isRepeat = false;
    private boolean isShuffle = false;

    private static int position = 0;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
    public static MediaPlayer mediaPlayer = new MediaPlayer();
    public static ArrayList<Song> listSongPlayMusic = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        getDataFromIntent();
        initUI();
        initToolBar();
        addFragment();
        eventClick();
    }

    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (adapter.getItem(1) != null) {
                    if (listSongPlayMusic.size() > 0) {
                        FragmentPlayMusic fragment = (FragmentPlayMusic) adapter.getItem(1);
                        fragment.ChangeImage(listSongPlayMusic.get(0).getHinhBaiHat());
                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this, 300);
                    }
                }
            }
        }, 500);

        imgbtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    imgbtnPlay.setImageResource(R.drawable.iconplay);
                    FragmentPlayMusic.animator.pause();
                } else {
                    mediaPlayer.start();
                    imgbtnPlay.setImageResource(R.drawable.iconpause);
                    FragmentPlayMusic.animator.start();
                }
            }
        });

        sbPlayMusic.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtCurrentTime.setText(simpleDateFormat.format(sbPlayMusic.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(sbPlayMusic.getProgress());
                txtCurrentTime.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
            }
        });

        imgbtnRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRepeat) {
                    imgbtnRepeat.setImageResource(R.drawable.iconsyned);
                    isRepeat = true;
                    imgbtnShuffle.setImageResource(R.drawable.iconsuffle);
                } else {
                    imgbtnRepeat.setImageResource(R.drawable.iconrepeat);
                    isRepeat = false;
                }
            }
        });

        imgbtnShuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isShuffle) {
                    imgbtnRepeat.setImageResource(R.drawable.iconrepeat);
                    isShuffle = true;
                    imgbtnShuffle.setImageResource(R.drawable.iconshuffled);
                } else {
                    imgbtnShuffle.setImageResource(R.drawable.iconsuffle);
                    isShuffle = false;
                }
            }
        });

        imgbtnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgbtnShuffle.setImageResource(R.drawable.iconsuffle);
                imgbtnRepeat.setImageResource(R.drawable.iconrepeat);
                position--;
                if (position < 0) {
                    position = listSongPlayMusic.size() - 1;
                }
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }
                createMedia();


            }
        });

        imgbtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgbtnShuffle.setImageResource(R.drawable.iconsuffle);
                imgbtnRepeat.setImageResource(R.drawable.iconrepeat);

                position++;
                if (position > listSongPlayMusic.size() - 1) {
                    position = 0;
                }
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }
                createMedia();
            }
        });
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("Song")) {
            listSongPlayMusic.clear();
            Song song = (Song) intent.getSerializableExtra("Song");
            if (song != null) {
                listSongPlayMusic.add(song);
            }
        }

        if (intent.hasExtra("ListSong")) {
            ArrayList<Song> listSong = intent.getParcelableArrayListExtra("ListSong");
            if (listSong.size() > 0) {
                listSongPlayMusic.clear();
                listSongPlayMusic = listSong;
            }
        }
    }

    private void addFragment() {
        FragmentPlayMusicListSong fragmentListMusic = new FragmentPlayMusicListSong();
        FragmentPlayMusic fragmentPlayMusic = new FragmentPlayMusic();
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(fragmentListMusic, "a");
        adapter.addFragment(fragmentPlayMusic, "b");
        mViewPager.setAdapter(adapter);

        if (listSongPlayMusic.size() > 0) {

            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }

            getSupportActionBar().setTitle(listSongPlayMusic.get(0).getTenBaiHat());
            new playMusic().execute(listSongPlayMusic.get(0).getLinkBaiHat());
            imgbtnPlay.setImageResource(R.drawable.iconpause);
        }
    }

    private void initToolBar() {
        setSupportActionBar(mToolBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    private void initUI() {
        mViewPager = findViewById(R.id.viewPagerPlayMusic);
        mToolBar = findViewById(R.id.toolBarPlayMusic);
        txtCurrentTime = findViewById(R.id.textViewCurrentTimeSong);
        txtTotalTime = findViewById(R.id.textViewTotalTimeSong);
        imgbtnShuffle = findViewById(R.id.imgbtnShuffle);
        imgbtnPre = findViewById(R.id.imgbtnPreview);
        imgbtnPlay = findViewById(R.id.imgbtnPlay);
        imgbtnNext = findViewById(R.id.imgbtnNext);
        imgbtnRepeat = findViewById(R.id.imgbtnRepeat);
        sbPlayMusic = findViewById(R.id.seekBarPlaySong);
    }

    class playMusic extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String linkBaiHat) {
            super.onPostExecute(linkBaiHat);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setDataSource(linkBaiHat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            onMediaPlayerProgressChange();
            TimeSong();
        }
    }

    private void TimeSong() {
        txtTotalTime.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        sbPlayMusic.setMax(mediaPlayer.getDuration());
    }

    private void onMediaPlayerProgressChange() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                sbPlayMusic.setProgress(mediaPlayer.getCurrentPosition());
                txtCurrentTime.setText(simpleDateFormat.format(sbPlayMusic.getProgress()));

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        if (isRepeat) {
                            createMedia();
                        } else if (isShuffle) {
                            Random rand = new Random();
                            position = rand.nextInt(listSongPlayMusic.size() - 1);

                            createMedia();
                        } else {
                            position++;
                            if (position > listSongPlayMusic.size() - 1) {
                                position = 0;
                            }
                            new playMusic().execute(listSongPlayMusic.get(position).getLinkBaiHat());

                            createMedia();
                        }


                        /*final Handler mhandler = new Handler();
                        mhandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                if(adapter.getItem(1) != null){
                                    if(listSongPlayMusic.size() > 0){
                                        FragmentPlayMusic fragment = (FragmentPlayMusic) adapter.getItem(1);
                                        fragment.ChangeImage(listSongPlayMusic.get(position).getHinhBaiHat());
                                        getSupportActionBar().setTitle(listSongPlayMusic.get(position).getTenBaiHat());
                                        mhandler.removeCallbacks(this);
                                    }else{
                                        mhandler.postDelayed(this, 300);
                                    }
                                }
                            }
                        }, 300);*/
                    }
                });

                handler.postDelayed(this, 500);
            }
        }, 500);
    }

    private void createMedia() {
        new playMusic().execute(listSongPlayMusic.get(position).getLinkBaiHat());

        if (adapter.getItem(1) != null) {
            if (listSongPlayMusic.size() > 0) {
                FragmentPlayMusic fragment = (FragmentPlayMusic) adapter.getItem(1);
                fragment.ChangeImage(listSongPlayMusic.get(position).getHinhBaiHat());
                getSupportActionBar().setTitle(listSongPlayMusic.get(position).getTenBaiHat());
            }
        }
    }
}
