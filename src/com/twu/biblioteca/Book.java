package com.twu.biblioteca;

/**
 * Created by root on 25/09/16.
 */
public class Book {
    private String title;
    private String author;
    private int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    String name() {
        return title;
    }

    String author() {
        return author;
    }

    int year() {
        return year;
    }
}
