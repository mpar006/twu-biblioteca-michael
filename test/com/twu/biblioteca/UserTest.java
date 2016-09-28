package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by michaelparker on 28/09/2016.
 */
public class UserTest {

    private User user;

    @Before
    public void setUp() {
        user = new User("000-0000", "pass", "Michael", "mparker@thoughtworks.com", "0429135190");
    }

    @Test
    public void testEquals() {
        assertEquals(user, new User("000-0000", "pass"));
    }

    @Test
    public void testNotEquals() {
        assertNotEquals(user, new User("111-1111", "pass", "Michael", "mparker@thoughtworks.com", "0429135190"));
    }

    @Test
    public void testBorrow() {
        user.borrow(new Book("The Dispossessed", "Ursula K Le Guin", 1974));
        assertEquals(user.showBorrowedTitles(), "The Dispossessed");
    }
}