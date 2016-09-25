package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

import java.util.prefs.AbstractPreferences;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class BookTest {
    private Book book;

    @Before
    public void setUp() throws Exception {
        book = new Book("The Dispossessed", "Ursula K Le Guin", 1974);
    }

    @Test
    public void testBookName() {
        assertEquals("The Dispossessed", book.getTitle());
    }

    @Test
    public void testBookAuthor() {
        assertEquals("Ursula K Le Guin", book.getAuthor());
    }

    @Test
    public void testBookYear() {
        assertEquals(1974, book.getYear());
    }

    @Test
    public void testEqual() {
        assertEquals(new Book("The Dispossessed", "Ursula K Le Guin", 1974), book);
    }

    @Test
    public void testNotEqual() {
        assertNotEquals(new Book("The Dispossessed", "Ursula K Le Guin", 2000), book);
    }
}
