package com.czazo.peleplayer;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.SeekBar;
import android.widget.Toast;

public class PlayerActivity extends AppCompatActivity {
    SeekBar pb;
    FloatingActionButton prev, next, playpause;
    int state = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        pb = findViewById(R.id.seekBar);
        prev = findViewById(R.id.button_prev);
        next = findViewById(R.id.button_next);
        playpause = findViewById(R.id.button_playpause);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Prev", Toast.LENGTH_SHORT).show();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Next", Toast.LENGTH_SHORT).show();
            }
        });
        playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation toPause = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.to_pause);
                Animation toPlay = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.to_play);
                if (state == 0) {
                    playpause.startAnimation(toPause);
                    playpause.setImageResource(R.drawable.ic_player_pause);
                    state = 1;
                } else {
                    playpause.startAnimation(toPlay);
                    playpause.setImageResource(R.drawable.ic_player_play);
                    state = 0;
                }
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
