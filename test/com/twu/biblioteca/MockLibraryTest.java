package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by root on 27/09/16.
 */
public class MockLibraryTest {

    MockLibrary l;

    @Before
    public void setUp() {
        l = new MockLibrary();
    }

    @Test
    public void testListBooks() {
        String expected = "The Dispossessed,Ursula K Le Guin,1974\n" +
                "Perdido Street Station,China Mieville,2000\n" +
                "The Call of Cthulhu,HP Lovecraft,1926";
        assertEquals(expected, l.list("book"));
    }

    @Test
    public void testGetBookFromTitle() {
        assertEquals(l.getLibraryItemFromTitle("The Dispossessed"), new Book("The Dispossessed", "Ursula K Le Guin", 1974));
    }

    @Test
    public void testCheckoutBook() {
        assertTrue(l.checkout("The Dispossessed"));
    }

    @Test
    public void testCheckoutMovie() {
        assertTrue(l.checkout("Sweeney Todd"));
    }

    @Test
    public void testCheckoutFakeItem() {
        assertFalse(l.checkout("foo"));
    }

    @Test
    public void testListBooksAfterCheckout() {
        String expected = "The Dispossessed,Ursula K Le Guin,1974\n" +
                "Perdido Street Station,China Mieville,2000";
        l.checkout("The Call of Cthulhu");
        assertEquals(expected, l.list("book"));
    }

    @Test
    public void testListBooksAfterCheckoutThenReturn() {
        String expected = "The Dispossessed,Ursula K Le Guin,1974\n" +
                "Perdido Street Station,China Mieville,2000\n" +
                "The Call of Cthulhu,HP Lovecraft,1926";
        l.checkout("The Call of Cthulhu");
        l.returnBook("The Call of Cthulhu");
        assertEquals(expected, l.list("book"));
    }

    @Test
    public void testUserLogin() {
        assertTrue(l.login("111-1111", "pass"));
    }

    @Test
    public void testFailUserLogin() {
        assertFalse(l.login("111-1111", "foo"));
    }

    @Test
    public void testUserIsLoggedIn() {
        l.login("111-1111", "pass");
        assertTrue(l.isLoggedIn());
    }

    @Test
    public void testDoubleLoginFail() {
        l.login("111-1111", "pass");
        assertFalse(l.login("000-0000", "pass"));
    }

    @Test
    public void testSuccessfulUserPrintDetails() {
        l.login("111-1111", "pass");
        assertEquals("Yvan,ymartin@thoughtworks.com,0434567524", l.printDetails());
    }
}