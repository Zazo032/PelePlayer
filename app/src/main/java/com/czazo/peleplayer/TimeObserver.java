package com.czazo.peleplayer;

public class TimeObserver extends Observer {
    public TimeObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {

    }
}
