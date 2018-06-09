package com.czazo.peleplayer;

import android.app.Activity;

public interface Mediator {
    void play(int mode, int pos, Activity act);
    void pause(Activity act);
    void nextSong(Activity act);
    void prevSong(Activity act);
    void updatePlayerUI(Song s, Activity act, String command);
}
