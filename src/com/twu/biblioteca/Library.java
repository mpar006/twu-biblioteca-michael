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
        for(Map.Entry<Book, Boolean> entry : inventory.entrySet()) {
            if(entry.getValue() == true) {
                Book b = entry.getKey();
                inventoryList += b.getTitle() + "," + b.getAuthor() + "," + b.getYear() + "\n";
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

    public boolean returnBook(Book book) {
        if(inventory.containsKey(book) && inventory.get(book) == false) {
            inventory.put(book, true);
            return true;
        }
        return false;
    }

    public void start() {
        this.add(new Book("The Dispossessed", "Ursula K Le Guin", 1974));
        this.add(new Book("The Call of Cthulhu", "HP Lovecraft", 1926));
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter(System.getProperty("line.separator"));
        String cmd;
        while(scanner.hasNext()) {
            cmd = scanner.next();
            if(cmd.contains("List")) {
                System.out.print(this.print());
            } else if(cmd.contains("Checkout")) {
                Book book = getBookFromCmd(cmd);
                if (this.checkout(book)) {
                    System.out.println("Thank you! Enjoy the book");
                } else {
                    System.out.println("That book is not available");
                }
            } else if(cmd.contains("Return")) {
                Book book = getBookFromCmd(cmd);
                if (this.returnBook(book)) {
                    System.out.println("Thank you for returning the book");
                } else {
                    System.out.println("That is not a valid book for return");
                }
            } else if(cmd.contains("Quit")) {
                break;
            } else {
                System.out.println("Select a valid option!");
            }
        }
    }

    private Book getBookFromCmd(String cmd) {
        return (new Book("The Call of Cthulhu", "HP Lovecraft", 1926));
    }
}
