package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 26/09/16.
 */
public class InMemoryLibrary implements Library {
    // The boolean value corresponding to a particular key (book) is it's availability
    // LinkedHashMap is used to preserve iteration order
    private Map<LibraryItem, Boolean> bookInventory;
    private Map<LibraryItem, Boolean> movieInventory;
    private List<User> userList;
    private User loggedIn;

    public InMemoryLibrary() {
        bookInventory = new LinkedHashMap<LibraryItem, Boolean>();
        bookInventory.put(new Book("The Dispossessed", "Ursula K Le Guin", 1974), true);
        bookInventory.put(new Book("Perdido Street Station", "China Mieville", 2000), true);
        bookInventory.put(new Book("The Call of Cthulhu", "HP Lovecraft", 1926), true);

        movieInventory = new LinkedHashMap<LibraryItem, Boolean>();
        movieInventory.put(new Movie("Sweeney Todd", 2007, "Tim Burton", 7), true);
        movieInventory.put(new Movie("Pulp Fiction", 1994, "Quentin Tarantino", 9), true);

        userList = new ArrayList<User>();
        userList.add(new User("111-1111", "pass", "Yvan", "ymartin@thoughtworks.com", "0434567524"));
        userList.add(new User("000-0000", "pass", "Michael", "mparker@thoughtworks.com", "0429135190"));

        loggedIn = null;
    }

    public String list(String itemType) {
        String list = "";
        if(itemType == "book") {
            list = listItems(bookInventory);
        } else if(itemType == "movie") {
            list = listItems(movieInventory);
        }
        return list;
    }

    private String listItems(Map<LibraryItem, Boolean> inventory) {
        String inventoryList = "";
        for(Map.Entry<LibraryItem, Boolean> entry : inventory.entrySet()) {
            if(entry.getValue() == true) {
                inventoryList += entry.getKey().details();
            }
        }
        return inventoryList.trim();
    }

    @Override
    public boolean checkout(String title) {
        if(isLoggedIn()) {
            return (checkout(bookInventory, title) || checkout(movieInventory, title));
        }
        return false;
    }

    @Override
    public boolean returnBook(String title) {
        LibraryItem b = getLibraryItemFromTitle(title);
        if(bookInventory.containsKey(b) && bookInventory.get(b) == false) {
            bookInventory.put(b, true);
            return true;
        }
        return false;
    }

    @Override
    //Only a single user should be able to log in at once
    public boolean login(String id, String pass) {
        if(loggedIn == null) {
            for (User user : userList) {
                if (user.equals(new User(id, pass))) {
                    loggedIn = user;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isLoggedIn() {
        return loggedIn != null;
    }

    @Override
    public String printDetails() {
        if(loggedIn != null) {
            return loggedIn.showDetails();
        }
        return "";
    }

    LibraryItem getLibraryItemFromTitle(String title) {
        for(Map.Entry<LibraryItem, Boolean> entry : bookInventory.entrySet()) {
            if(entry.getKey().getTitle().equals(title)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private boolean checkout(Map<LibraryItem, Boolean> inventory, String title) {
        for(Map.Entry<LibraryItem, Boolean> entry : inventory.entrySet()) {
            if(entry.getKey().getTitle().equals(title)) {
                inventory.put(entry.getKey(), false);
                return true;
            }
        }
        return false;
    }
}
