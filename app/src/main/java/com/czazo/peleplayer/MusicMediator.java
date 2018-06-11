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
        switch (Context.getContext().getState().toString()){
            case "StopState": // Lanzar Activity
                act.startActivity(new Intent(act, PlayerActivity.class));
                break;
            case "PauseState": // Modificar IU reproductor
            case "PlayingState":
                if (act.getLocalClassName().equals("PlayerActivity")) {
                    FloatingActionButton fab = act.findViewById(R.id.button_playpause);
                    fab.startAnimation(AnimationUtils.loadAnimation(act, R.anim.to_pause));
                    fab.setImageResource(R.drawable.ic_player_pause);
                    MusicPlayer.getInstance().seekTo(MusicPlayer.getInstance().getPausedTime());
                } else {
                    // Si se elimina este bloque, si ya está el FAB visible (state = playing/pause),
                    // no se abriría el reproductor y simplemente se reproduciría la canción.
                    // Para abrir el reproductor se tendría que pulsar el FAB.
                    act.startActivity(new Intent(act, PlayerActivity.class));
                }
                break;
        }
        new PlayingState().doAction(Context.getContext());
    }

    @Override
    public void pause(Activity act) {
        MusicPlayer.getInstance().pause();
        MusicPlayer.getInstance().setPausedTime(MusicPlayer.getInstance().getCurrentPosition());
        FloatingActionButton fab = act.findViewById(R.id.button_playpause);
        fab.startAnimation(AnimationUtils.loadAnimation(act, R.anim.to_play));
        fab.setImageResource(R.drawable.ic_player_play);
        new PauseState().doAction(Context.getContext());
    }

    @Override
    public void nextSong(Activity act) {
        MusicPlayer.getInstance().playNext();
        if (Context.getContext().getState().toString().equals("PauseState")) {
            FloatingActionButton fab = act.findViewById(R.id.button_playpause);
            fab.startAnimation(AnimationUtils.loadAnimation(act, R.anim.to_pause));
            fab.setImageResource(R.drawable.ic_player_pause);
            new PlayingState().doAction(Context.getContext());
        }
    }

    @Override
    public void prevSong(Activity act) {
        MusicPlayer.getInstance().playPrevious();
        if (Context.getContext().getState().toString().equals("PauseState")) {
            FloatingActionButton fab = act.findViewById(R.id.button_playpause);
            fab.startAnimation(AnimationUtils.loadAnimation(act, R.anim.to_pause));
            fab.setImageResource(R.drawable.ic_player_pause);
            new PlayingState().doAction(Context.getContext());
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
        switch (Context.getContext().getState().toString()){
            case "PauseState":
                playpause.setImageResource(R.drawable.ic_player_play);
                break;
            case "PlayingState":
                playpause.setImageResource(R.drawable.ic_player_pause);
                break;
        }
    }
}
