package com.example.mp3player.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mp3player.Activity.SongActivity;
import com.example.mp3player.Model.Playlist;
import com.example.mp3player.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class RecyclerViewAllPlaylistAdapter extends RecyclerView.Adapter<RecyclerViewAllPlaylistAdapter.ViewHolder>{

    private List<Playlist> listPlaylist;

    public RecyclerViewAllPlaylistAdapter(List<Playlist> listPlaylist, Context context) {
        this.listPlaylist = listPlaylist;
        this.context = context;
    }

    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.line_playlist_on_allplaylist_activity, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtPlaylistName.setText(listPlaylist.get(position).getTenPlaylist());
        Picasso.with(context).load(listPlaylist.get(position).getHinhNenPlaylist()).into(holder.imgPlaylistImage);

    }

    @Override
    public int getItemCount() {
        return listPlaylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgPlaylistImage;
        TextView txtPlaylistName;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            imgPlaylistImage = itemView.findViewById(R.id.imageViewAllPlaylistImage);
            txtPlaylistName = itemView.findViewById(R.id.textViewAllPlaylistName);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myOnClick(listPlaylist.get(getAdapterPosition()));
                }
            });
        }
    }
    private void myOnClick(Playlist playlist){
        Intent intent = new Intent(context, SongActivity.class);
        intent.putExtra("Playlist", (Serializable) playlist);
        context.startActivity(intent);
    }
}
