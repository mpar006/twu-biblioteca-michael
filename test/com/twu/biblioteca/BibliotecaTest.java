package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Created by root on 26/09/16.
 */
public class BibliotecaTest {
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    Biblioteca b;
    Library l;
    String expectedBookList = "The Dispossessed,Ursula K Le Guin,1974\n" +
            "Perdido Street Station,China Mieville,2000\n" +
            "The Call of Cthulhu,HP Lovecraft,1926\n";
    String expectedMovieList = "Sweeney Todd,2007,Tim Burton,7\nPulp Fiction,1994,Quentin Tarantino,9\n";

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        b = new Biblioteca(new MockLibrary());
    }

    @After
    public void tearDown() {
        System.setOut(null);
    }

    @Test
    public void testWelcome() {
        b.welcome();
        assertEquals("Welcome to Biblioteca\n", outContent.toString());
    }

    @Test
    public void testBookDetails() {
        b.listBooks();
        assertEquals(expectedBookList, outContent.toString());
    }

    @Test
    public void testListBooksOption() {
        System.setIn(new ByteArrayInputStream("List Books".getBytes()));
        b.processCmds();
        assertEquals(expectedBookList, outContent.toString());
    }

    @Test
    public void testListMoviesOption() {
        System.setIn(new ByteArrayInputStream("List Movies".getBytes()));
        b.processCmds();
        assertEquals(expectedMovieList, outContent.toString());
    }

    @Test
    public void testInvalidOption() {
        System.setIn(new ByteArrayInputStream("I'm invalid!".getBytes()));
        b.processCmds();
        assertEquals ("Select a valid option!\n", outContent.toString());
    }

    @Test
    public void testQuitOption() {
        System.setIn(new ByteArrayInputStream("Quit".getBytes()));
        b.processCmds();
        assertEquals("Goodbye!\n", outContent.toString());
    }

    @Test
    public void testCheckoutBook() {
        b.checkoutBook("The Dispossessed");
        b.listBooks();
        assertEquals("Perdido Street Station,China Mieville,2000\n" +
                "The Call of Cthulhu,HP Lovecraft,1926\n", outContent.toString ());
    }

    @Test
    public void testCheckoutMovie() {

    }

    @Test
    public void testGetCheckoutTitle() {
        assertEquals(b.getCheckoutTitle("Checkout The Dispossessed"), "The Dispossessed");
    }

    @Test
    public void testSuccessfulCheckout() {
        System.setIn(new ByteArrayInputStream("Checkout The Dispossessed".getBytes()));
        b.processCmds();
        assertEquals("Thank you! Enjoy\n", outContent.toString());
    }

    @Test
    public void testUnsuccessfulCheckout() {
        System.setIn(new ByteArrayInputStream("Checkout fake book".getBytes()));
        b.processCmds();
        assertEquals("That book is not available\n", outContent.toString());
    }

    @Test
    public void testReturnBook() {
        b.returnBook("The Dispossessed");
        b.listBooks();
        assertEquals(expectedBookList, outContent.toString());
    }

    @Test
    public void testSuccessfulReturnBook() {
        System.setIn(new ByteArrayInputStream("Checkout The Dispossessed".getBytes()));
        b.processCmds();
        System.setIn(new ByteArrayInputStream("Return The Dispossessed".getBytes()));
        b.processCmds();
        assertEquals("Thank you! Enjoy\nThank you for returning the book\n", outContent.toString());
    }

    @Test
    public void testGetReturnTitle() {
        assertEquals(b.getReturnTitle("Return The Dispossessed"), "The Dispossessed");
    }

    @Test
    public void testUnsuccessfulReturn() {
        System.setIn(new ByteArrayInputStream("Return fake book".getBytes()));
        b.processCmds();
        assertEquals("That is not a valid book to return\n", outContent.toString());
    }
}