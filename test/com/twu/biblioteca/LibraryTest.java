package com.twu.biblioteca;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by root on 25/09/16.
 */
public class LibraryTest {

    //@Before
    //public void setUp() throws Exception {
    //    Library library = new Library();
    //    Book book = new Book("The Dispossessed", "Ursula K Le Guin", 1974);
    //    library.add(book);
    //}

    @Test
    public void testAddToLibrary() {
        Library library = new Library();
        Book book = new Book("The Dispossessed", "Ursula K Le Guin", 1974);
        library.add(book);
        assertEquals(1, library.size());
    }

    @Test
    public void testPrintLibrary() {
        Library library = new Library();
        Book book = new Book("The Dispossessed", "Ursula K Le Guin", 1974);
        library.add(book);
        book = new Book("Perdido Street Station", "China Mieville", 2000);
        library.add(book);
        assertEquals("The Dispossessed,Ursula K Le Guin,1974\nPerdido Street Station,China Mieville,2000",
                library.print());
    }

    @Test
    public void testHasBook() {
        Library library = new Library();
        Book book = new Book("The Dispossessed", "Ursula K Le Guin", 1974);
        library.add(book);
        assertTrue(library.hasBook(book));
    }

    @Test
    public void testDoesntHaveBook() {
        Library library = new Library();
        Book book = new Book("The Dispossessed", "Ursula K Le Guin", 1974);
        library.add(book);
        assertFalse(library.hasBook(new Book("The Dispossessed", "Ursula K Le Guin", 2000)));
    }

   // @Test
   // public void testBookIsAvailable() {
   //     Library library = new Library();
   //     Book book = new Book("The Dispossessed", "Ursula K Le Guin", 1974);
   //     library.add(book);
   //     assertTrue(library.isAvailable(book));
   // }
   // @Test
   // public void testCheckout() {
   //     Library library = new Library();
   //     Book book = new Book("The Dispossessed", "Ursula K Le Guin", 1974);
   //     library.add(book);
   //     library.checkout(book);
   //     assertEquals(true, library.isAvailable(book));
   // }
}