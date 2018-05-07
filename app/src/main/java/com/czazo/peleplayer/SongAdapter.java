package com.czazo.peleplayer;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    private ArrayList<Song> songList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView cover;
        TextView title;
        TextView author;
        TextView album;
        TextView duration;
        ViewHolder(View v) {
            super(v);
            cover = v.findViewById(R.id.cover);
            title = v.findViewById(R.id.title);
            author = v.findViewById(R.id.author);
            album = v.findViewById(R.id.album);
            duration = v.findViewById(R.id.duration);
        }
    }

    SongAdapter() {
        songList = new ArrayList<>();
        // TODO: Escanear almacenamiento para buscar canciones
    }

    @NonNull
    @Override
    public SongAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = songList.get(position);
        holder.title.setText(song.getTitle());
        holder.author.setText(song.getAuthor());
        holder.album.setText(song.getAlbum());
        holder.duration.setText(song.getDuration());
        holder.cover.setImageBitmap(song.getCoverArt());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Lanzar reproductor con la canción seleccionada
            }
        });
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }
}