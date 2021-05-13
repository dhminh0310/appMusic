package com.example.mp3player.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.mp3player.Adapter.RecyclerViewAllPlaylistAdapter;
import com.example.mp3player.Model.Playlist;
import com.example.mp3player.R;

import java.util.List;

public class AllPlaylistActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecylerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_playlist);

        mToolbar = findViewById(R.id.toolbarAllPlaylist);
        mRecylerView = findViewById(R.id.recylerViewAllPlaylist);

        initToolbar();

        initRecylerView();

    }

    private void initRecylerView() {
        Intent intent = getIntent();
        if(intent.hasExtra("ListPlaylist")){

            List<Playlist> listPlaylist = intent.getParcelableArrayListExtra("ListPlaylist");
            RecyclerViewAllPlaylistAdapter adapter = new RecyclerViewAllPlaylistAdapter(listPlaylist, this);

            GridLayoutManager manager = new GridLayoutManager(this, 2);
            mRecylerView.setLayoutManager(manager);
            mRecylerView.setAdapter(adapter);

        }
    }

    private void initToolbar(){
        setSupportActionBar(mToolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Danh sách Playlist");
        }
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mToolbar.setTitleTextColor(Color.rgb(205,0,0));
    };
}
