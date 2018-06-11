package com.czazo.peleplayer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;

import static org.junit.Assert.*;

public class SongFactoryTest {

    @Test
    public void getSong() {
        Object song = SongFactory.getSong("");
        assertNotNull(song);
        assertTrue(song instanceof Song);
    }
}