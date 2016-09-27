package com.twu.biblioteca;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by root on 26/09/16.
 */
public class MockLibrary implements Library {
    // The boolean value corresponding to a particular key (book) is it's availability
    // LinkedHashMap is used to preserve iteration order
    private Map<Book, Boolean> inventory;

    public MockLibrary() {
        inventory = new LinkedHashMap<Book, Boolean>();
        inventory.put(new Book("The Dispossessed", "Ursula K Le Guin", 1974), true);
        inventory.put(new Book("Perdido Street Station", "China Mieville", 2000), true);
        inventory.put(new Book("The Call of Cthulhu", "HP Lovecraft", 1926), true);
    }

    @Override
    public String listBooks() {
        String inventoryList = "";
        for(Map.Entry<Book, Boolean> entry : inventory.entrySet()) {
            if(entry.getValue() == true) {
                Book b = entry.getKey();
                inventoryList += b.getTitle() + "," + b.getAuthor() + "," + b.getYear() + "\n";
            }
        }
        return inventoryList.trim();
    }

    @Override
    public boolean checkout(String title) {
        Book b = getBookFromTitle(title);
        if(inventory.containsKey(b) && inventory.get(b) == true) {
            inventory.put(b, false);
            return true;
        }
        return false;
    }

    @Override
    public boolean returnBook(String title) {
        Book b = getBookFromTitle(title);
        if(inventory.containsKey(b) && inventory.get(b) == false) {
            inventory.put(b, true);
            return true;
        }
        return false;
    }

    Book getBookFromTitle(String title) {
        for(Map.Entry<Book, Boolean> entry : inventory.entrySet()) {
            if(entry.getKey().getTitle().equals(title)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
