package com.example.mp3player.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mp3player.Activity.AllPlaylistActivity;
import com.example.mp3player.Activity.SongActivity;
import com.example.mp3player.Adapter.RecylerViewPlaylistAdapter;
import com.example.mp3player.Model.Playlist;
import com.example.mp3player.R;
import com.example.mp3player.Service.ApiService;
import com.example.mp3player.Service.RetrofitClient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPlaylist extends Fragment {

    private Button btnMorePlaylist;
    private RecyclerView rclvPlaylist;
    private RecylerViewPlaylistAdapter adapter;
    private ArrayList<Playlist> listPlaylist;

    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlist, container, false);

        InitUI();
        getDataPlaylist();

        btnMorePlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myOnClick();
            }
        });

        return view;
    }

    private void myOnClick() {

        ApiService apiService = RetrofitClient.getApiService();
        Call<List<Playlist>> callback = apiService.getListAllPlaylist();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                listPlaylist = (ArrayList<Playlist>) response.body();

                Intent intent = new Intent(getActivity(), AllPlaylistActivity.class);
                intent.putParcelableArrayListExtra("ListPlaylist", listPlaylist);
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {
                Toast.makeText(getActivity(), "Lấy danh sách Playlist thất bại", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getDataPlaylist() {
        ApiService apiService = RetrofitClient.getApiService();
        Call<List<Playlist>> callback = apiService.getListPlaylist();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                listPlaylist = (ArrayList<Playlist>) response.body();
                adapter = new RecylerViewPlaylistAdapter(listPlaylist, getContext());

                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                manager.setOrientation(RecyclerView.VERTICAL);
                rclvPlaylist.setLayoutManager(manager);
                rclvPlaylist.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {
                Log.e("AAA","Error");
            }
        });
    }

    private void InitUI() {
        btnMorePlaylist = view.findViewById(R.id.buttonMorePlaylist);
        rclvPlaylist = view.findViewById(R.id.recylerViewPlaylist);
    }

}
