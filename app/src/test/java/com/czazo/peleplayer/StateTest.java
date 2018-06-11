package com.czazo.peleplayer;

import org.junit.Test;

import static org.junit.Assert.*;

public class StateTest {

    @Test
    public void changeToStopState(){
        new StopState().doAction(Context.getContext());
        assertEquals("StopState", Context.getContext().getState().toString());
    }

    @Test
    public void changeToPlayingState() {
        new PlayingState().doAction(Context.getContext());
        assertEquals("PlayingState", Context.getContext().getState().toString());
    }

    @Test
    public void changeToPauseState() {
        new PauseState().doAction(Context.getContext());
        assertEquals("PauseState", Context.getContext().getState().toString());
    }

}