package com.czazo.peleplayer;

import android.app.Activity;

public class PauseCommand implements Command {
    private Activity act;
    private MusicMediator mediator;

    PauseCommand(Activity activity){
        act = activity;
        mediator = new MusicMediator();
    }

    public void execute(){
        mediator.pause(act);
        mediator.updatePlayerUI(MusicPlayer.getInstance().getCurrentSong(), act, "pause");
    }
}
