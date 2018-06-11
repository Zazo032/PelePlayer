package com.czazo.peleplayer;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;

public class Song {
    private String filePath;
    private Bitmap coverArt;
    private String title;
    private String author;
    private String album;
    private String duration;
    private int durationTime;

    Song() {
    }

    @SuppressLint("DefaultLocale")
    Song(String filePath) {
        this.filePath = filePath;
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(filePath);
        byte[] artBytes = mmr.getEmbeddedPicture();
        if(artBytes != null) {
            coverArt = BitmapFactory.decodeByteArray(artBytes, 0, artBytes.length);
        }
        album = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
        if (album == null) album = "Álbum desconocido";
        title = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        if (title == null) title = "Sin título";
        author = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_AUTHOR);
        if (author == null) author = "Autor desconocido";
        durationTime = Integer.parseInt(mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION));
        int mns = (durationTime / 60000) % 60000;
        int scs = durationTime % 60000 / 1000;
        duration = String.format("%d:%02d",  mns, scs);
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getCoverArt() {
        return coverArt;
    }

    public String getAlbum() {
        return album;
    }

    public String getAuthor() {
        return author;
    }

    public String getDuration() {
        return duration;
    }

    public int getDurationTime() {
        return durationTime;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getTitle() {
        return title;
    }
}
