package com.twu.biblioteca;

public class BibliotecaApp {

    public static void main(String[] args) {
        Biblioteca b = new Biblioteca(new InMemoryLibrary(), System.out, System.in);
        b.start();
    }
}
