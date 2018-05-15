package com.czazo.peleplayer;

import android.content.Intent;
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
        ArrayList<String> pathList = new SongLoader().getSongList();
        for (String path : pathList) {
            Song s = SongFactory.getSong(path);
            songList.add(s);
        }
        MusicPlayer.getInstance().setSongs(songList);
    }

    @NonNull
    @Override
    public SongAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Song song = songList.get(position);
        holder.title.setText(song.getTitle());
        holder.author.setText(song.getAuthor());
        holder.album.setText(song.getAlbum());
        holder.duration.setText(song.getDuration());
        if (song.getCoverArt() != null) {
            holder.cover.setImageBitmap(song.getCoverArt());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MusicPlayer.getInstance().play(holder.getAdapterPosition());
                MusicPlayer.getInstance().setStatus(1);
                Intent i = new Intent(view.getContext(), PlayerActivity.class);
                view.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }
}