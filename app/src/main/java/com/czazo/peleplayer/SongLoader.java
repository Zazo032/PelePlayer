package com.czazo.peleplayer;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;

class SongLoader {
    private ArrayList<String> songList;

    SongLoader() {
        File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Music/");
        songList = new ArrayList<>();
        if (dir.exists()) {
            if (dir.listFiles() != null) {
                for (File f : dir.listFiles()) {
                    if (f.isFile()) {
                        String path = f.getAbsolutePath();
                        if (path.contains(".mp3")) {
                            songList.add(path);
                        }
                    }
                }
            }
        }
    }

    ArrayList<String> getSongList() {
        return songList;
    }
}