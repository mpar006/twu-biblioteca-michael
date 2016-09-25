package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by root on 25/09/16.
 */
public class LibraryTest {

    @Test
    public void testAddToLibrary() {
        Library library = new Library();
        Book book = new Book("The Dispossessed", "Ursula K Le Guin", 1974);
        library.add(book);
        assertEquals(1, library.size());
    }

}