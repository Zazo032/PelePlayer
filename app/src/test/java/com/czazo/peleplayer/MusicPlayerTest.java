package com.czazo.peleplayer;

import android.media.MediaPlayer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class MusicPlayerTest {

    @Mock
    MusicPlayer musicPlayer;

    @Test
    public void getInstance() {
        MusicPlayer.setInstance(musicPlayer);
        assertEquals(musicPlayer, MusicPlayer.getInstance());
    }
}