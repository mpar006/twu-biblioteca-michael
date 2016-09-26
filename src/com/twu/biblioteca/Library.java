package com.twu.biblioteca;

/**
 * Created by root on 26/09/16.
 */
public interface Library {
    public String listBooks();
    public boolean checkout(String title);
    public boolean returnBook(String title);
}
