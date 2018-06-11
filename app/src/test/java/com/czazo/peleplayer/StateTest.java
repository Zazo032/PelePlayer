package com.czazo.peleplayer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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

}