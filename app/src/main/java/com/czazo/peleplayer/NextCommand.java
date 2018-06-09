package com.czazo.peleplayer;

import android.app.Activity;

public class NextCommand implements Command {
    private Activity act;
    private MusicMediator mediator;

    NextCommand(Activity activity){
        act = activity;
        mediator = new MusicMediator();
    }

    public void execute(){
        mediator.nextSong(act);
        mediator.updatePlayerUI(MusicPlayer.getInstance().getCurrentSong(), act, "next");
    }
}
