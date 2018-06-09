package com.czazo.peleplayer;

public abstract class Observer {
    protected TimeSubject subject;
    public abstract void update();
}
