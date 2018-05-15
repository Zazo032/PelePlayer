package com.czazo.peleplayer;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class PlayerActivity extends AppCompatActivity {
    ImageView cover;
    SeekBar sb;
    FloatingActionButton prev, next, playpause;
    TextView songTitle, currTime, maxTime;
    Command playCommand, pauseCommand, nextCommand, prevCommand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        cover = findViewById(R.id.imageView);
        sb = findViewById(R.id.seekBar);
        prev = findViewById(R.id.button_prev);
        next = findViewById(R.id.button_next);
        playpause = findViewById(R.id.button_playpause);
        songTitle = findViewById(R.id.song_title);
        currTime = findViewById(R.id.current_time);
        maxTime = findViewById(R.id.max_time);

        updatePlayerUI(MusicPlayer.getInstance().getCurrentSong());
        sb.setMin(0);
        sb.incrementProgressBy(1);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int nextSeekbarTime;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                nextSeekbarTime = i;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                MusicPlayer.getInstance().seekTo(nextSeekbarTime*1000);
                if (MusicPlayer.getInstance().getStatus() == 0) {
                    MusicPlayer.getInstance().setPausedTime(nextSeekbarTime*1000);
                }
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prevCommand = new PrevCommand((Activity)view.getContext());
                MusicPlayer.getInstance().setCommand(prevCommand);
                MusicPlayer.getInstance().buttonPressed();
                updatePlayerUI(MusicPlayer.getInstance().getCurrentSong());
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextCommand = new NextCommand((Activity)view.getContext());
                MusicPlayer.getInstance().setCommand(nextCommand);
                MusicPlayer.getInstance().buttonPressed();
                updatePlayerUI(MusicPlayer.getInstance().getCurrentSong());
            }
        });
        playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (MusicPlayer.getInstance().getStatus()) {
                    case 0:
                        playCommand = new PlayCommand(MusicPlayer.getInstance().getPosCurrentSong(), 1, (Activity)view.getContext());
                        MusicPlayer.getInstance().setCommand(playCommand);
                        break;
                    case 1:
                        pauseCommand = new PauseCommand((Activity)view.getContext());
                        MusicPlayer.getInstance().setCommand(pauseCommand);
                        break;
                }
                MusicPlayer.getInstance().buttonPressed();
            }
        });
        MusicPlayer.getInstance().setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                MusicPlayer.getInstance().setCommand(nextCommand);
                MusicPlayer.getInstance().buttonPressed();
                updatePlayerUI(MusicPlayer.getInstance().getCurrentSong());
            }
        });
    }

    private void updatePlayerUI(Song s){
        if (s.getCoverArt() != null) {
            cover.setImageBitmap(s.getCoverArt());
        } else {
            cover.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.default_cover));
        }
        songTitle.setText(s.getTitle());
        maxTime.setText(s.getDuration());
        sb.setProgress(0);
        sb.setMax(s.getDurationTime()/1000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        switch (MusicPlayer.getInstance().getStatus()){
            case 0:
                playpause.setImageResource(R.drawable.ic_player_play);
                break;
            case 1:
                playpause.setImageResource(R.drawable.ic_player_pause);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            finish();
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}