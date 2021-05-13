package com.example.mp3player.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mp3player.Adapter.RecylerViewSongAdapter;
import com.example.mp3player.Model.Ads;
import com.example.mp3player.Model.Album;
import com.example.mp3player.Model.Playlist;
import com.example.mp3player.Model.Song;
import com.example.mp3player.R;
import com.example.mp3player.Service.ApiService;
import com.example.mp3player.Service.RetrofitClient;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongActivity extends AppCompatActivity {

    private CoordinatorLayout mCoordinatorLayout;
    private CollapsingToolbarLayout mCollapsingToolbar;
    private Toolbar mToolbar;
    private FloatingActionButton mFloatingButton;
    private ImageView mImageView;
    private RecyclerView mRecyclerView;
    private NestedScrollView mNestedScrollView;
    private ArrayList<Song> listSong;
    private RecylerViewSongAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        InitUI();

        InitToolbarAndCollapsingToolBar();

        mFloatingButton.setEnabled(false);

        getDataFromIntent();

    }

    private void getDataFromIntent(){
        Intent intent = getIntent();
        if(intent.hasExtra("Advertisiment")){
            Ads ads = (Ads) intent.getSerializableExtra("Advertisiment");
            if(ads != null && !ads.getTenBaiHat().equals("")){
                String idAds = String.valueOf(ads.getIdQuangCao());
                InitImageAndTitleAds(ads);
                getListSongByAdsId(idAds);
                onFloattingButtonClick();
            }
        }

        if(intent.hasExtra("Playlist")){
            Playlist playlist = (Playlist) intent.getSerializableExtra("Playlist");
            if(playlist != null){
                InitImageAndTitlePlaylist(playlist);
                getListSongByPlaylistId(playlist.getId());
                onFloattingButtonClick();
            }
        }

        if(intent.hasExtra("Album")){
            Album album = (Album) intent.getSerializableExtra("Album");
            if(album != null){
                InitImageAndTitleAlbum(album);
                getListSongByAlbumId(album.getIdAlbum());
                onFloattingButtonClick();
            }
        }
    }

    private void getListSongByAlbumId(String idAlbum) {

        ApiService apiService = RetrofitClient.getApiService();
        Call<List<Song>> callback = apiService.getListSongByAlbumId(idAlbum);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                listSong = (ArrayList<Song>) response.body();
                adapter = new RecylerViewSongAdapter(listSong, SongActivity.this);

                LinearLayoutManager manager = new LinearLayoutManager(SongActivity.this);
                manager.setOrientation(RecyclerView.VERTICAL);
                mRecyclerView.setLayoutManager(manager);
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                Toast.makeText(SongActivity.this, "Tải danh sách bài hát thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void InitImageAndTitleAlbum(Album album) {

        mCollapsingToolbar.setTitle(album.getTenAlbum());
        Picasso.with(this).load(album.getHinhAlbum()).into(mImageView);
    }

    private void getListSongByPlaylistId(String idPlaylist) {

        ApiService apiService = RetrofitClient.getApiService();
        Call<List<Song>> callback = apiService.getListSongByPlaylistId(idPlaylist);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                listSong = (ArrayList<Song>) response.body();
                adapter = new RecylerViewSongAdapter(listSong, SongActivity.this);

                LinearLayoutManager manager = new LinearLayoutManager(SongActivity.this);
                manager.setOrientation(RecyclerView.VERTICAL);
                mRecyclerView.setLayoutManager(manager);
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                Toast.makeText(SongActivity.this, "Tải danh sách bài hát thất bại", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void InitImageAndTitlePlaylist(Playlist playlist) {

        mCollapsingToolbar.setTitle(playlist.getTenPlaylist());
        Picasso.with(this).load(playlist.getHinhNenPlaylist()).into(mImageView);
    }

    private void getListSongByAdsId(String idAds) {
        ApiService apiService = RetrofitClient.getApiService();
        Call<List<Song>> callback = apiService.getListSongByAdsId(idAds);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                listSong = (ArrayList<Song>) response.body();

                adapter = new RecylerViewSongAdapter(listSong, SongActivity.this);

                LinearLayoutManager manager = new LinearLayoutManager(SongActivity.this);
                manager.setOrientation(LinearLayoutManager.VERTICAL);

                mRecyclerView.setLayoutManager(manager);
                mRecyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                Toast.makeText(SongActivity.this, "Load dữ liệu bài hát thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void InitImageAndTitleAds(Ads ads) {
        Picasso.with(this).load(ads.getHinhAnh()).into(mImageView);
        mCollapsingToolbar.setTitle(ads.getTenBaiHat());

    }

    private void InitToolbarAndCollapsingToolBar() {
        setSupportActionBar(mToolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mCollapsingToolbar.setExpandedTitleColor(Color.WHITE);
        mCollapsingToolbar.setCollapsedTitleTextColor(Color.WHITE);
    }

    private void InitUI() {
        mCoordinatorLayout = findViewById(R.id.coordinatorLayoutSong);
        mCollapsingToolbar = findViewById(R.id.collarsingToolbarSong);
        mFloatingButton = findViewById(R.id.floatingButtonSong);
        mImageView = findViewById(R.id.imageViewSong);
        mRecyclerView = findViewById(R.id.recyclerViewSong);
        mToolbar = findViewById(R.id.toolbarSong);
    }

    private void onFloattingButtonClick(){
        mFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SongActivity.this, PlayMusicActivity.class);
                intent.putParcelableArrayListExtra("ListSong", listSong);
                startActivity(intent);
            }
        });
        mFloatingButton.setEnabled(true);
    }
}
