package com.czazo.peleplayer;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.view.animation.AnimationUtils;

public class PlayCommand implements Command {
    private int pos, mode;
    private Activity act;

    /* @param mode:
     *      0: Launch Activity
     *      1: Modify Player UI
     */
    PlayCommand(int songPos, int launchMode, Activity activity){
        pos = songPos;
        mode = launchMode;
        act = activity;
    }

    public void execute() {
        MusicPlayer.getInstance().play(pos);
        MusicPlayer.getInstance().seekTo(MusicPlayer.getInstance().getPausedTime());
        MusicPlayer.getInstance().setStatus(1);
        switch (mode){
            case 0:
                act.startActivity(new Intent(act, PlayerActivity.class));
                break;
            case 1:
                FloatingActionButton fab = act.findViewById(R.id.button_playpause);
                fab.startAnimation(AnimationUtils.loadAnimation(act, R.anim.to_pause));
                fab.setImageResource(R.drawable.ic_player_pause);
                break;
        }
    }
}
