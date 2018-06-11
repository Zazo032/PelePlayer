package com.czazo.peleplayer;

import android.app.Activity;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ObserverTest {

    @Mock
    PlayerActivity mockActivity;

    @Test
    public void attach() {
        TimeSubject timeSubject = new TimeSubject();
        TimeObserver timeObserver = new TimeObserver(timeSubject, mockActivity);
        assertTrue(timeSubject.getObservers().contains(timeObserver));
    }

}