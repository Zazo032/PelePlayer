package com.czazo.peleplayer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class PlayerActivity extends AppCompatActivity {
    ImageView cover;
    SeekBar sb;
    FloatingActionButton prev, next, playpause;
    TextView songTitle, currTime, maxTime;
    Command playCommand, pauseCommand, nextCommand, prevCommand;
    TimeSubject timeSubject;
    TimeObserver timeObserver;
    MusicMediator mediator;
    Activity act = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        timeSubject = new TimeSubject();
        timeObserver = new TimeObserver(timeSubject, this);
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
        mediator = new MusicMediator();

        mediator.updatePlayerUI(MusicPlayer.getInstance().getCurrentSong(), this, "play");
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
                timeSubject.setCurrentTime(nextSeekbarTime);
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
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextCommand = new NextCommand((Activity)view.getContext());
                MusicPlayer.getInstance().setCommand(nextCommand);
                MusicPlayer.getInstance().buttonPressed();
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
                nextCommand = new NextCommand(act);
                MusicPlayer.getInstance().setCommand(nextCommand);
                MusicPlayer.getInstance().buttonPressed();
            }
        });
        final Handler mHandler = new Handler();

        PlayerActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(MusicPlayer.isInstanciated()) {
                    int currentPosition = MusicPlayer.getInstance().getCurrentPosition()/1000;
                    timeSubject.setCurrentTime(currentPosition);
                }
                mHandler.postDelayed(this, 1000);
            }
        });
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