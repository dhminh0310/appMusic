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
import com.example.mp3player.Model.Album;
import com.example.mp3player.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAlbumAdapter extends RecyclerView.Adapter<RecyclerViewAlbumAdapter.ViewHolder>{

    public RecyclerViewAlbumAdapter(List<Album> listAlbum, Context context) {
        this.listAlbum = listAlbum;
        this.context = context;
    }

    List<Album> listAlbum;
    Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.line_album, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtAlbumName.setText(listAlbum.get(position).getTenAlbum());
        holder.txtAlbumSinger.setText(listAlbum.get(position).getTenCaSiAlbum());
        Picasso.with(context).load(listAlbum.get(position).getHinhAlbum()).into(holder.imgAlbumImage);
    }

    @Override
    public int getItemCount() {
        return listAlbum.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgAlbumImage;
        TextView txtAlbumName, txtAlbumSinger;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAlbumImage = itemView.findViewById(R.id.imageViewAlbumImage);
            txtAlbumName = itemView.findViewById(R.id.textViewAlbumName);
            txtAlbumSinger = itemView.findViewById(R.id.textViewAlbumSinger);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myOnClick(listAlbum.get(getAdapterPosition()));
                }
            });
        }
    }

    private void myOnClick(Album album) {

        Intent intent = new Intent(context, SongActivity.class);
        intent.putExtra("Album", album);
        context.startActivity(intent);
    }
}
