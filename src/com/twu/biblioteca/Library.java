package com.twu.biblioteca;

import java.util.*;

/**
 * Created by root on 25/09/16.
 */
public class Library {
    // The value corresponding to a particular key (book) is it's availability
    // LinkedHashMap is used to preserve iteration order
    private Map<Book, Boolean> inventory = new LinkedHashMap<Book, Boolean>();

    public void add(Book book) {
        inventory.put(book, true);
    }

    public int size() {
        return inventory.size();
    }

    public String print() {
        String inventoryList = "";
        Iterator<Map.Entry<Book, Boolean>> inventoryIterator = inventory.entrySet().iterator();
        for(int i = 0; i < inventory.size(); i++){
            Map.Entry<Book, Boolean> tmp = inventoryIterator.next();
            Book b = tmp.getKey();
            inventoryList += b.getTitle() + "," + b.getAuthor() + "," + b.getYear();
            if(inventoryIterator.hasNext()) {
                inventoryList += "\n";
            }
        }
        return inventoryList;
    }

    public boolean hasBook(Book book) {
        return inventory.containsKey(book);
    }

    public boolean isAvailable(Book book) {
        if(inventory.containsKey(book)) {
            return inventory.get(book);
        }
        return false;
    }

    public boolean checkout(Book book) {
        if(inventory.containsKey(book) && inventory.get(book) == true) {
            inventory.put(book, false);
            return true;
        }
        return false;
    }
}
