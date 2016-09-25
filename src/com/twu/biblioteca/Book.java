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

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    @Override
    public boolean equals(Object obj) {
        Book other = (Book) obj;
        if(other.getAuthor() == author && other.getTitle() == title && other.getYear() == year) {
            return true;
        }
        return false;
    }
}