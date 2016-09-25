package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Iterator;
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

    public String print() {
        String inventoryList = "";
        Iterator<Book> inventoryIterator = inventory.iterator();
        for(int i = 0; i < inventory.size(); i++){
            Book b = inventoryIterator.next();
            inventoryList += b.getTitle() + "," + b.getAuthor() + "," + b.getYear();
            if(inventoryIterator.hasNext()) {
                inventoryList += "\n";
            }
        }
        return inventoryList;
    }

    public boolean hasBook(Book book) {
        return inventory.contains(book);
    }

    public boolean isAvailable(Book book) {
        return true;
    }
}
