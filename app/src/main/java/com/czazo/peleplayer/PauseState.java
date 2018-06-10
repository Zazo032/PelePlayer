package com.czazo.peleplayer;

public class PauseState implements State {
    @Override
    public void doAction(Context context) {
        context.setState(this);
    }

    @Override
    public String toString() {
        return "PauseState";
    }
}
