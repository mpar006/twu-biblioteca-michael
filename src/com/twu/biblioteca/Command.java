package com.twu.biblioteca;

public enum Command {

    ListBooks {
        public void process() {
            System.out.println("books!");
        }
    },
    ListMovies {
        public void process() {
            System.out.println("movies!");
        }
    };

    abstract public void process();
}
