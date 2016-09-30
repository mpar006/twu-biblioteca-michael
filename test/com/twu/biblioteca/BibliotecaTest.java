package com.twu.biblioteca;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Created by root on 26/09/16.
 */
public class BibliotecaTest {
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    Biblioteca b = new Biblioteca(new InMemoryLibrary(), outContent);
    Library l;
    String expectedBookList = "The Dispossessed,Ursula K Le Guin,1974\n" +
            "Perdido Street Station,China Mieville,2000\n" +
            "The Call of Cthulhu,HP Lovecraft,1926\n";
    String expectedMovieList = "Sweeney Todd,2007,Tim Burton,7\nPulp Fiction,1994,Quentin Tarantino,9\n";

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
        b.login("111-1111", "pass");
        b.checkoutBook("The Dispossessed");
        b.listBooks();
        assertEquals("Perdido Street Station,China Mieville,2000\n" +
                "The Call of Cthulhu,HP Lovecraft,1926\n", outContent.toString ());
    }

    @Test
    public void testCheckoutMovie() {
        b.login("111-1111", "pass");
        b.checkoutMovie("Sweeney Todd");
        b.listMovies();
        assertEquals("Pulp Fiction,1994,Quentin Tarantino,9\n", outContent.toString());
    }

    @Test
    public void testGetCheckoutTitle() {
        assertEquals(b.getCheckoutTitle("Checkout The Dispossessed"), "The Dispossessed");
    }

    @Test
    public void testSuccessfulBookCheckout() {
        System.setIn(new ByteArrayInputStream("Login 111-1111 pass".getBytes()));
        b.processCmds();
        System.setIn(new ByteArrayInputStream("Checkout book The Dispossessed".getBytes()));
        b.processCmds();
        assertEquals("Login successful\nThank you! Enjoy\n", outContent.toString());
    }

    @Test
    public void testUnsuccessfulCheckout() {
        System.setIn(new ByteArrayInputStream("Checkout book fake book".getBytes()));
        b.processCmds();
        assertEquals("That book is not available\n", outContent.toString());
    }

    @Test
    public void testSuccessfulMovieCheckout() {
        System.setIn(new ByteArrayInputStream("Login 111-1111 pass".getBytes()));
        b.processCmds();
        System.setIn(new ByteArrayInputStream("Checkout movie Sweeney Todd".getBytes()));
        b.processCmds();
        assertEquals("Login successful\nThank you! Enjoy\n", outContent.toString());
    }

    @Test
    public void testUnsuccessfulMovieCheckout() {
        System.setIn(new ByteArrayInputStream("Checkout movie Sweeney Todd".getBytes()));
        b.processCmds();
        assertEquals("That movie is not available\n", outContent.toString());
    }

    @Test
    public void testReturnBook() {
        b.returnBook("The Dispossessed");
        b.listBooks();
        assertEquals(expectedBookList, outContent.toString());
    }

    @Test
    public void testSuccessfulReturnBook() {
        System.setIn(new ByteArrayInputStream("Login 111-1111 pass".getBytes()));
        b.processCmds();
        System.setIn(new ByteArrayInputStream("Checkout book The Dispossessed".getBytes()));
        b.processCmds();
        System.setIn(new ByteArrayInputStream("Return The Dispossessed".getBytes()));
        b.processCmds();
        assertEquals("Login successful\nThank you! Enjoy\nThank you for returning the book\n", outContent.toString());
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

    @Test
    public void testSuccessfulUserLogin() {
        System.setIn(new ByteArrayInputStream("Login 111-1111 pass".getBytes()));
        b.processCmds();
        assertEquals("Login successful\n", outContent.toString());
    }
}