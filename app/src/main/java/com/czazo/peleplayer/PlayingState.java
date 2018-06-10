package com.czazo.peleplayer;

public class PlayingState implements State {
    @Override
    public void doAction(Context context) {
        context.setState(this);
    }

    @Override
    public String toString() {
        return "PlayingState";
    }
}
