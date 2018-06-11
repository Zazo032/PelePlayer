package com.czazo.peleplayer;

import org.junit.Test;

import static org.junit.Assert.*;

public class SongFactoryTest {

    @Test
    public void getSong() {
        Object song = SongFactory.getSong("");
        assertNotNull(song);
        assertTrue(song instanceof Song);
    }
}