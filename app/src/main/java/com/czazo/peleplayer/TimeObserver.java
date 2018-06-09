package com.czazo.peleplayer;

import android.app.Activity;
import android.content.Context;
import android.widget.SeekBar;
import android.widget.TextView;

public class TimeObserver extends Observer {
    TimeSubject subject;
    Activity activity;

    public TimeObserver(TimeSubject subject, Activity activity) {
        this.subject = subject;
        this.activity = activity;

        this.subject.attach(this);
    }

    @Override
    public void update() {
        TextView currTime = activity.findViewById(R.id.current_time);
        SeekBar sb = activity.findViewById(R.id.seekBar);
        currTime.setText(secondsToClockFormat(subject.getCurrentTime()));
        sb.setProgress(subject.getCurrentTime());
    }

    private static String secondsToClockFormat(int timeSeconds) {
        int minutes = timeSeconds/60;
        int seconds = timeSeconds%60;
        String res = "";
        if (minutes < 10) res += "0";
        res += minutes + ":";
        if(seconds < 10) res += "0";
        res += Integer.toString(seconds);
        return res;
    }
}
