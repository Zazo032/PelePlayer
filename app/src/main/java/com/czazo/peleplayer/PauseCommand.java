package com.czazo.peleplayer;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.view.animation.AnimationUtils;

public class PauseCommand implements Command {
    private Activity act;

    PauseCommand(Activity activity){
        act = activity;
    }

    public void execute(){
        MusicPlayer.getInstance().pause();
        MusicPlayer.getInstance().setPausedTime(MusicPlayer.getInstance().getCurrentPosition());
        MusicPlayer.getInstance().setStatus(0);
        FloatingActionButton fab = act.findViewById(R.id.button_playpause);
        fab.startAnimation(AnimationUtils.loadAnimation(act, R.anim.to_play));
        fab.setImageResource(R.drawable.ic_player_play);
    }
}
