package com.example.mp3player.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mp3player.Adapter.RecylerViewSongAdapter;
import com.example.mp3player.Model.Song;
import com.example.mp3player.R;
import com.example.mp3player.Service.ApiService;
import com.example.mp3player.Service.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentHome extends Fragment {

    private RecyclerView rcvFavoriteSong;
    private RecylerViewSongAdapter adapter;
    private ArrayList<Song> listFavoriteSong;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        rcvFavoriteSong = view.findViewById(R.id.recyclerFavoriteSong);
        initDataFavoriteSong();

        return view;
    }

    private void initDataFavoriteSong() {

        ApiService apiService = RetrofitClient.getApiService();
        Call<List<Song>> callback = apiService.getListFavoriteSong();
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                listFavoriteSong = (ArrayList<Song>) response.body();
                adapter = new RecylerViewSongAdapter(listFavoriteSong, getActivity());
                LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                rcvFavoriteSong.setLayoutManager(manager);
                rcvFavoriteSong.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }
}
