package com.example.mp3player.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mp3player.Activity.AllAlbumActivity;
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

public class FragmentAlbum extends Fragment {

    private RecyclerView recyclerViewAlbum;
    private TextView txtMoreAlbum;
    View view;
    ArrayList<Album> listAlbum;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album, container,false);
        recyclerViewAlbum = view.findViewById(R.id.recyclerViewAlbum);
        txtMoreAlbum = view.findViewById(R.id.textViewMoreAlbum);

        initDataAlbum();
        txtMoreAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myOnClick();
            }
        });


        return view;
    }

    private void myOnClick() {
        Intent intent = new Intent(getActivity(), AllAlbumActivity.class);
        startActivity(intent);
    }

    private void initDataAlbum() {


        ApiService apiService = RetrofitClient.getApiService();
        Call<List<Album>> callback = apiService.getListAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {

                listAlbum = (ArrayList<Album>) response.body();
                RecyclerViewAlbumAdapter adapter = new RecyclerViewAlbumAdapter(listAlbum, getActivity());
                LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                manager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewAlbum.setLayoutManager(manager);
                recyclerViewAlbum.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

                Toast.makeText(getActivity(), "Tải dữ liệu Album thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
