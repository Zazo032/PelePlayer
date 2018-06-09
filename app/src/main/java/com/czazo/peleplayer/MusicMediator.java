package com.czazo.peleplayer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MusicMediator implements Mediator {

    MusicMediator(){}

    @Override
    public void play(int mode, int pos, Activity act) {
        MusicPlayer.getInstance().play(pos);
        MusicPlayer.getInstance().seekTo(MusicPlayer.getInstance().getPausedTime());
        MusicPlayer.getInstance().setStatus(1);
        switch (mode){
            case 0: // Lanzar Activity
                act.startActivity(new Intent(act, PlayerActivity.class));
                break;
            case 1: // Modificar IU reproductor
                FloatingActionButton fab = act.findViewById(R.id.button_playpause);
                fab.startAnimation(AnimationUtils.loadAnimation(act, R.anim.to_pause));
                fab.setImageResource(R.drawable.ic_player_pause);
                break;
        }
    }

    @Override
    public void pause(Activity act) {
        MusicPlayer.getInstance().pause();
        MusicPlayer.getInstance().setPausedTime(MusicPlayer.getInstance().getCurrentPosition());
        MusicPlayer.getInstance().setStatus(0);
        FloatingActionButton fab = act.findViewById(R.id.button_playpause);
        fab.startAnimation(AnimationUtils.loadAnimation(act, R.anim.to_play));
        fab.setImageResource(R.drawable.ic_player_play);
    }

    @Override
    public void nextSong(Activity act) {
        MusicPlayer.getInstance().playNext();
        if (MusicPlayer.getInstance().getStatus() == 0) {
            FloatingActionButton fab = act.findViewById(R.id.button_playpause);
            fab.startAnimation(AnimationUtils.loadAnimation(act, R.anim.to_pause));
            fab.setImageResource(R.drawable.ic_player_pause);
            MusicPlayer.getInstance().setStatus(1);
        }
    }

    @Override
    public void prevSong(Activity act) {
        MusicPlayer.getInstance().playPrevious();
        if (MusicPlayer.getInstance().getStatus() == 0) {
            FloatingActionButton fab = act.findViewById(R.id.button_playpause);
            fab.startAnimation(AnimationUtils.loadAnimation(act, R.anim.to_pause));
            fab.setImageResource(R.drawable.ic_player_pause);
            MusicPlayer.getInstance().setStatus(1);
        }
    }

    @Override
    public void updatePlayerUI(Song s, Activity act, String command) {
        ImageView cover = act.findViewById(R.id.imageView);
        TextView songTitle = act.findViewById(R.id.song_title),
                maxTime = act.findViewById(R.id.max_time),
                currTime = act.findViewById(R.id.current_time);
        SeekBar sb = act.findViewById(R.id.seekBar);
        FloatingActionButton playpause = act.findViewById(R.id.button_playpause);
        if (s.getCoverArt() != null) {
            cover.setImageBitmap(s.getCoverArt());
        } else {
            cover.setImageBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.default_cover));
        }
        songTitle.setText(s.getTitle());
        maxTime.setText(s.getDuration());
        if (command.equals("prev") || command.equals("next")) {
            sb.setProgress(0);
            currTime.setText("00:00");
        }
        sb.setMax(s.getDurationTime()/1000);
        switch (MusicPlayer.getInstance().getStatus()){
            case 0:
                playpause.setImageResource(R.drawable.ic_player_play);
                break;
            case 1:
                playpause.setImageResource(R.drawable.ic_player_pause);
                break;
        }
    }
}
