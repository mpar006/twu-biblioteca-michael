package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 25/09/16.
 */
public class Library {
    private List<Book> inventory = new ArrayList<Book>();

    public boolean add(Book book) {
        return inventory.add(book);
    }

    public int size() {
        return inventory.size();
    }
}
