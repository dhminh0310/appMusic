package com.example.mp3player.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mp3player.Activity.PlayMusicActivity;
import com.example.mp3player.Model.Song;
import com.example.mp3player.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class RecylerViewSongAdapter extends RecyclerView.Adapter<RecylerViewSongAdapter.ViewHolder> {

    private List<Song> listSong;
    private Context context;

    public RecylerViewSongAdapter(List<Song> listSong, Context context) {
        this.listSong = listSong;
        this.context = context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgSongImage;
        TextView txtSongName, txtSingerName;
        ImageButton imgbtnLike;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSongImage = itemView.findViewById(R.id.imageViewSongActSongImage);
            txtSongName = itemView.findViewById(R.id.textViewSongActSongName);
            txtSingerName = itemView.findViewById(R.id.textViewSongActSingerName);
            imgbtnLike = itemView.findViewById(R.id.imageButtonSongActLike);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayMusicActivity.class);
                    intent.putExtra("Song", (Serializable) listSong.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.line_song, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = listSong.get(position);
        holder.txtSongName.setText(song.getTenBaiHat());
        holder.txtSingerName.setText(song.getTenCaSi());
        Picasso.with(context).load(song.getHinhBaiHat()).into(holder.imgSongImage);
    }

    @Override
    public int getItemCount() {
        return listSong.size();
    }
}
