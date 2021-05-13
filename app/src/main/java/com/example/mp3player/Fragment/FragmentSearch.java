package com.example.mp3player.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class FragmentSearch extends Fragment {

    private EditText edtSearch;
    private RecyclerView rcvSearchSong;
    private ImageButton imgbtnSearch;

    ArrayList<Song> listSearchSong;
    RecylerViewSongAdapter adapter;

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);

        edtSearch = view.findViewById(R.id.editTextSearch);
        rcvSearchSong = view.findViewById(R.id.recyclerViewSearchSong);
        imgbtnSearch = view.findViewById(R.id.imageButtonSearch);

        imgbtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = edtSearch.getText().toString().trim();
                if(!key.equals("")){
                    eventSearchSong(key);
                }else {
                    Toast.makeText(getActivity(), "Nhập tên bài hát", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }

    private void eventSearchSong(String key) {

        ApiService apiService = RetrofitClient.getApiService();
        Call<List<Song>> callback = apiService.getListSearchSong(key);

        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                Log.e("AAA", "" + response.body());

                listSearchSong = (ArrayList<Song>) response.body();
                if(listSearchSong.size() > 0){
                    adapter = new RecylerViewSongAdapter(listSearchSong, getActivity());
                    LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                    manager.setOrientation(RecyclerView.VERTICAL);
                    rcvSearchSong.setLayoutManager(manager);
                    rcvSearchSong.setAdapter(adapter);
                }else{
                    Toast.makeText(getActivity(), "Không có bài hát tương ứng", Toast.LENGTH_SHORT).show();
                }

                /*if(response.body().toString().trim() == "null"){
                    Toast.makeText(getActivity(), "Không tồn tại bài hát", Toast.LENGTH_SHORT).show();
                }else{
                    listSearchSong = (ArrayList<Song>) response.body();
                    adapter = new RecylerViewSongAdapter(listSearchSong, getActivity());
                    LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                    manager.setOrientation(RecyclerView.VERTICAL);
                    rcvSearchSong.setLayoutManager(manager);
                    rcvSearchSong.setAdapter(adapter);
                }*/
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                Toast.makeText(getActivity(), "Xảy ra lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
