package com.czazo.peleplayer;

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

    public Song(String filePath) {
        this.filePath = filePath;
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(filePath);
        byte[] artBytes = mmr.getEmbeddedPicture();
        if(artBytes != null) {
            coverArt = BitmapFactory.decodeByteArray(artBytes, 0, artBytes.length);
        } else {
            //Make the covert art be the default one
        }
        album = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
        title = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        author = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_AUTHOR);
        duration = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCoverArt(Bitmap coverArt) {
        this.coverArt = coverArt;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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

    public String getFilePath() {
        return filePath;
    }

    public String getTitle() {
        return title;
    }
}
