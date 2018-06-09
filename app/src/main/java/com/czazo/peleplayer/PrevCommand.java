package com.czazo.peleplayer;

import android.app.Activity;

public class PrevCommand implements Command {
    private Activity act;
    private MusicMediator mediator;

    PrevCommand(Activity activity){
        act = activity;
        mediator = new MusicMediator();
    }

    public void execute(){
        mediator.prevSong(act);
        mediator.updatePlayerUI(MusicPlayer.getInstance().getCurrentSong(), act, "prev");
    }
}