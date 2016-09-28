package com.twu.biblioteca;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

/**
 * Created by michaelparker on 28/09/2016.
 */
public class MovieTest {

    Movie movie;
    @Before
    public void setUp() {
        movie = new Movie("Sweeney Todd", 2007, "Tim Burton", 7);
    }

    @Test
    public void testEqual() {
        assertEquals(new Movie("Sweeney Todd", 2007, "Tim Burton", 7), movie);
    }

    @Test
    public void testNotEqual() {
        assertNotEquals(new Movie("Sweey Todd", 2007, "Tim Burton", 7), movie);
    }
}