package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

/**
 * Created by root on 26/09/16.
 */
public class BibliotecaTest {
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    Biblioteca b;// = new Biblioteca(new InMemoryLibrary(), outContent, new ByteArrayInputStream("".getBytes()));
    Library l;
    String expectedBookList = "The Dispossessed,Ursula K Le Guin,1974\n" +
            "Perdido Street Station,China Mieville,2000\n" +
            "The Call of Cthulhu,HP Lovecraft,1926\n";
    String expectedMovieList = "Sweeney Todd,2007,Tim Burton,7\nPulp Fiction,1994,Quentin Tarantino,9\n";

    private Biblioteca newInputStream(InputStream in) {
        return new Biblioteca(new InMemoryLibrary(), outContent, in);
    }

    @Test
    public void testWelcome() {
        b  = newInputStream(new ByteArrayInputStream("".getBytes()));
        b.welcome();
        assertEquals("Welcome to Biblioteca\n", outContent.toString());
    }

    @Test
    public void testListBooksOption() {
        b  = newInputStream(new ByteArrayInputStream("List Books".getBytes()));
        b.processCmds();
        assertEquals(expectedBookList, outContent.toString());
    }

    @Test
    public void testListMoviesOption() {
        b  = newInputStream(new ByteArrayInputStream("List Movies".getBytes()));
        b.processCmds();
        assertEquals(expectedMovieList, outContent.toString());
    }

    @Test
    public void testInvalidOption() {
        b = newInputStream(new ByteArrayInputStream("I'm invalid!".getBytes()));
        b.processCmds();
        assertEquals ("Select a valid option!\n", outContent.toString());
    }

    @Test
    public void testQuitOption() {
        b  = newInputStream(new ByteArrayInputStream("Quit Biblioteca".getBytes()));
        b.processCmds();
        assertEquals("Goodbye!\n", outContent.toString());
    }

    @Test
    public void testSuccessfulBookCheckout() {
        b  = newInputStream(new ByteArrayInputStream("User Login 111-1111 pass\nCheckout Book The Dispossessed".getBytes()));
        b.processCmds();
        assertEquals("Login successful\nThank you! Enjoy\n", outContent.toString());
    }

    @Test
    public void testUnsuccessfulCheckout() {
        b  = newInputStream(new ByteArrayInputStream("Checkout Book fake book".getBytes()));
        b.processCmds();
        assertEquals("That book is not available\n", outContent.toString());
    }

    @Test
    public void testSuccessfulMovieCheckout() {
        b  = newInputStream(new ByteArrayInputStream("User Login 111-1111 pass\nCheckout Movie Sweeney Todd".getBytes()));
        b.processCmds();
        assertEquals("Login successful\nThank you! Enjoy\n", outContent.toString());
    }

    @Test
    public void testUnsuccessfulMovieCheckout() {
        b  = newInputStream(new ByteArrayInputStream("Checkout Movie Sweeney Todd".getBytes()));
        b.processCmds();
        assertEquals("That movie is not available\n", outContent.toString());
    }

    @Test
    public void testSuccessfulReturnBook() {
        b  = newInputStream(new ByteArrayInputStream("User Login 111-1111 pass\nCheckout Book The Dispossessed\nReturn Book The Dispossessed".getBytes()));
        b.processCmds();
        assertEquals("Login successful\nThank you! Enjoy\nThank you for returning the book\n", outContent.toString());
    }

    @Test
    public void testUnsuccessfulReturn() {
        b  = newInputStream(new ByteArrayInputStream("Return Book fake book".getBytes()));
        b.processCmds();
        assertEquals("That is not a valid book to return\n", outContent.toString());
    }

    @Test
    public void testSuccessfulUserLogin() {
        b  = newInputStream(new ByteArrayInputStream("User Login 111-1111 pass".getBytes()));
        b.processCmds();
        assertEquals("Login successful\n", outContent.toString());
    }
}