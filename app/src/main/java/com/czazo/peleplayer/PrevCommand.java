package com.czazo.peleplayer;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.view.animation.AnimationUtils;

public class PrevCommand implements Command {
    private Activity act;

    PrevCommand(Activity activity){
        act = activity;
    }

    public void execute(){
        MusicPlayer.getInstance().playPrevious();
        if (MusicPlayer.getInstance().getStatus() == 0) {
            FloatingActionButton fab = act.findViewById(R.id.button_playpause);
            fab.startAnimation(AnimationUtils.loadAnimation(act, R.anim.to_pause));
            fab.setImageResource(R.drawable.ic_player_pause);
            MusicPlayer.getInstance().setStatus(1);
        }
    }
}