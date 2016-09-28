package com.twu.biblioteca;

import java.util.Scanner;

/**
 * Created by root on 26/09/16.
 */
public class Biblioteca {

    private Library l;

    public Biblioteca(Library library) {
        l = library;
    }

    public void start() {
        welcome();
        processCmds();
    }

    public void welcome() {
        System.out.println("Welcome to Biblioteca");
    }

    public void processCmds() {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter(System.getProperty("line.separator"));
        String cmd;
        while(scanner.hasNext()) {
            cmd = scanner.next();
            if(cmd.contains("List Books")) {
                listBooks();
            } else if(cmd.contains("List Movies")) {
                listMovies();
            } else if(cmd.contains("Checkout")) {
                processCheckout(cmd);
            } else if(cmd.contains("Return")) {
                processReturn(cmd);
            } else if(cmd.contains("Quit")) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Select a valid option!");
            }
        }
    }

    private void processCheckout(String cmd) {
        if(checkoutBook(getCheckoutTitle(cmd))) {
            System.out.println("Thank you! Enjoy");
        } else {
            System.out.println("That book is not available");
        }
    }

    private void processReturn(String cmd) {
        if(returnBook(getReturnTitle(cmd))) {
            System.out.println("Thank you for returning the book");
        } else {
            System.out.println("That is not a valid book to return");
        }
    }

    String getReturnTitle(String cmd) {
        return cmd.substring("Return ".length());
    }

    String getCheckoutTitle(String cmd) {
        return cmd.substring("Checkout ".length());
    }

    void listBooks() {
        System.out.println(l.list("book"));
    }

    void listMovies() {
        System.out.println(l.list("movie"));
    }

    boolean checkoutBook(String title) {
        return l.checkout(title);
    }

    boolean returnBook(String title) {
        return l.returnBook(title);
    }
}
