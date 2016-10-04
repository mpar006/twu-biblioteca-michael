package com.twu.biblioteca;

import java.io.PrintStream;

public enum Command {
    ListBooks("List Books") {
        public void process(PrintStream out, Library l, String args) {
            out.println(l.list("book"));
        }
    },
    ListMovies("List Movies") {
        public void process(PrintStream out, Library l, String args) {
            out.println(l.list("movie"));
        }
    },
    CheckoutBook("Checkout Book") {
        public void process(PrintStream out, Library l, String args) {
            if(l.checkout(args)) {
                out.println("Thank you! Enjoy");
            } else {
                out.println("That book is not available");
            }
        }
    },
    CheckoutMovie("Checkout Movie") {
        public void process(PrintStream out, Library l, String args) {
            if(l.checkout(args)) {
                out.println("Thank you! Enjoy");
            } else {
                out.println("That movie is not available");
            }
        }
    },
    Return("Return Book") {
        public void process(PrintStream out, Library l, String args) {
            if(l.returnBook(args)) {
                out.println("Thank you for returning the book");
            } else {
                out.println("That is not a valid book to return");
            }
        }
    },
    UserLogin("User Login") {
        public void process(PrintStream out, Library l, String args) {
            String[] split = args.split(" ");
            if(l.login(split[0], split[1])) {
                out.println("Login successful");
            } else {
                out.println("Login failure");
            }
        }
    },
    Quit("Quit Biblioteca") {
        public void process(PrintStream out, Library l, String args) {
            out.println("Goodbye!");
        }
    },
    Default("") {
        public void process(PrintStream out, Library l, String args) {
            out.println("Select a valid option!");
        }
    };

    private String input;

    Command(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    @Override
    public String toString() {
        return input;
    }

    public static Command retrieveByInput(String input) {
        for(Command c : Command.values()) {
            if(c.getInput().equals(input)) {
                return c;
            }
        }
        return Default;
    }

    abstract public void process(PrintStream out, Library l, String args);
}
