package com.twu.biblioteca;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by root on 26/09/16.
 */
public class Biblioteca {

    private Library l;
    private PrintStream out;
    private Command c;

    public Biblioteca(Library library, OutputStream outStream) {
        l = library;
        out = new PrintStream(outStream);
    }

    public void start() {
        welcome();
        processCmds();
    }

    public void welcome() {
        out.println("Welcome to Biblioteca");
    }

    public void processCmds() {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter(System.getProperty("line.separator"));
        String cmd;
        while(scanner.hasNext()) {
            cmd = scanner.next();
//            if(cmd.contains("List Books")) {
//                c.ListBooks.process();
//            }
            if(cmd.contains("List Books")) {
                listBooks();
            } else if(cmd.contains("List Movies")) {
                listMovies();
            } else if(cmd.contains("Checkout book")) {
                processBookCheckout(cmd);
            } else if(cmd.contains("Checkout movie")) {
                processMovieCheckout(cmd);
            } else if(cmd.contains("Return")) {
                processReturn(cmd);
            } else if(cmd.contains("Login")) {
                processLogin(cmd);
            } else if(cmd.contains("Quit")) {
                out.println("Goodbye!");
                break;
            } else {
                out.println("Select a valid option!");
            }
        }
    }

    void processLogin(String cmd) {
        String[] args = cmd.split(" ");
        if(login(args[1], args[2])) {
            out.println("Login successful");
        } else {
            out.println("Login failure");
        }
    }

    boolean login(String id, String password) {
        return l.login(id, password);
    }

    private void processBookCheckout(String cmd) {
        if(checkoutBook(getSecondCmdArg(cmd, "Checkout book "))) {
            out.println("Thank you! Enjoy");
        } else {
            out.println("That book is not available");
        }
    }

    private void processMovieCheckout(String cmd) {
        if(checkoutBook(getSecondCmdArg(cmd, "Checkout movie "))) {
            out.println("Thank you! Enjoy");
        } else {
            out.println("That movie is not available");
        }
    }

    private void processReturn(String cmd) {
        if(returnBook(getReturnTitle(cmd))) {
            out.println("Thank you for returning the book");
        } else {
            out.println("That is not a valid book to return");
        }
    }

    String getSecondCmdArg(String cmd, String firstArg) {
        return cmd.substring(firstArg.length());
    }

    String getReturnTitle(String cmd) {
        return cmd.substring("Return ".length());
    }

    String getCheckoutTitle(String cmd) {
        return cmd.substring("Checkout ".length());
    }

    void listBooks() {
        out.println(l.list("book"));
    }

    void listMovies() {
        out.println(l.list("movie"));
    }

    boolean checkoutBook(String title) {
        return l.checkout(title);
    }

    boolean checkoutMovie(String title) {
        return l.checkout(title);
    }

    boolean returnBook(String title) {
        return l.returnBook(title);
    }
}
