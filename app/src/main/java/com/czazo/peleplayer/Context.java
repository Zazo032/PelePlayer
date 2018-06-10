package com.czazo.peleplayer;

public class Context {
    private State state;
    private static Context ctx = new Context();

    private Context() {
        state = null;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public static Context getContext() {
        return ctx;
    }
}
