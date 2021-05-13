package com.example.mp3player.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mp3player.Activity.PlayMusicActivity;
import com.example.mp3player.Adapter.RecyclerViewPlayMusicAdapter;
import com.example.mp3player.Adapter.RecylerViewSongAdapter;
import com.example.mp3player.R;

public class FragmentPlayMusicListSong extends Fragment {

    View view;
    RecyclerView recyclerView;
    RecyclerViewPlayMusicAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_music_list_song,container, false);
        recyclerView = view.findViewById(R.id.recyclerViewListSongPlayMusic);

        adapter = new RecyclerViewPlayMusicAdapter(PlayMusicActivity.listSongPlayMusic, getActivity());

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
