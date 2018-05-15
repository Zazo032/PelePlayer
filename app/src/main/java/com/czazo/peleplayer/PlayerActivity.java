package com.czazo.peleplayer;

import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
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
                MusicPlayer.getInstance().seekTo(nextSeekbarTime);
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MusicPlayer.getInstance().playPrevious();
                updatePlayerUI(MusicPlayer.getInstance().getCurrentSong());
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MusicPlayer.getInstance().playNext();
                updatePlayerUI(MusicPlayer.getInstance().getCurrentSong());
            }
        });
        playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation toPause = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.to_pause);
                Animation toPlay = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.to_play);
                if (MusicPlayer.getInstance().getStatus() == 0) {
                    MusicPlayer.getInstance().play(MusicPlayer.getInstance().getPosCurrentSong());
                    MusicPlayer.getInstance().seekTo(MusicPlayer.getInstance().getPausedTime());
                    playpause.startAnimation(toPause);
                    playpause.setImageResource(R.drawable.ic_player_pause);
                    MusicPlayer.getInstance().setStatus(1);
                } else {
                    MusicPlayer.getInstance().pause();
                    MusicPlayer.getInstance().setPausedTime(MusicPlayer.getInstance().getCurrentPosition());
                    playpause.startAnimation(toPlay);
                    playpause.setImageResource(R.drawable.ic_player_play);
                    MusicPlayer.getInstance().setStatus(0);
                }
            }
        });
        MusicPlayer.getInstance().setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                MusicPlayer.getInstance().playNext();
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