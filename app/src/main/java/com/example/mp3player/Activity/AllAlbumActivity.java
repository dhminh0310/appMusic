package com.example.mp3player.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mp3player.Adapter.RecyclerViewAlbumAdapter;
import com.example.mp3player.Model.Album;
import com.example.mp3player.R;
import com.example.mp3player.Service.ApiService;
import com.example.mp3player.Service.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllAlbumActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private RecyclerViewAlbumAdapter adapter;
    private List<Album> listAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_album);

        mToolbar = findViewById(R.id.toolBarAllAlbum);
        mRecyclerView = findViewById(R.id.recyclerViewAllAlbum);

        setSupportActionBar(mToolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("Danh sách Album");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

        InitDataAlbum();
    }

    private void InitDataAlbum() {

        ApiService apiService = RetrofitClient.getApiService();
        Call<List<Album>> callback = apiService.getAllAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {

                listAlbum = response.body();
                adapter = new RecyclerViewAlbumAdapter(listAlbum, AllAlbumActivity.this);
                GridLayoutManager manager = new GridLayoutManager(AllAlbumActivity.this, 2);
                manager.setOrientation(GridLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(manager);
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                Toast.makeText(AllAlbumActivity.this, "Tải danh sách Album thất bại", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
