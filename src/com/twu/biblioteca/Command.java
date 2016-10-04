package com.twu.biblioteca;

public enum Command {
    ListBooks("List Books") {
        public void process() {
            System.out.println("books!");
        }
    },
    ListMovies("List Movies") {
        public void process() {
            System.out.println("movies!");
        }
    },
    CheckoutBook("Checkout Book") {
        public void process() {
            System.out.println("checkout book");
        }
    },
    CheckoutMovie("Checkout Movie") {
        public void process() {
            System.out.println("checkout movie");
        }
    },
    Return("Return") {
        public void process() {
            System.out.println("return");
        }
    },
    Login("Login") {
        public void process() {
            System.out.println("login");
        }
    },
    Quit("Quit") {
        public void process() {
            System.out.println("quit");
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

    public Command retrieveByInput(String input) {
        for(Command c : Command.values()) {
            if(c.getInput().equals(input)) {
                return c;
            }
        }
        return null;
    }

    abstract public void process();
}
