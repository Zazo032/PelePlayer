package com.czazo.peleplayer;

import android.app.Activity;

public class PlayCommand implements Command {
    private int pos, mode;
    private Activity act;
    private MusicMediator mediator;

    PlayCommand(int songPos, int launchMode, Activity activity){
        pos = songPos;
        mode = launchMode;
        act = activity;
        mediator = new MusicMediator();
    }

    public void execute() {
        mediator.play(mode, pos, act);
        try {
            mediator.updatePlayerUI(MusicPlayer.getInstance().getCurrentSong(), act, "play");
        } catch (Exception e) {
            // No se puede modificar la IU del reproductor si no estás en dicha actividad
        }
    }
}
