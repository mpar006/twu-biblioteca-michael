package com.twu.biblioteca;

/**
 * Created by michaelparker on 28/09/2016.
 */
public class Movie implements LibraryItem {
    private String title;
    private int year;
    private String director;
    private int rating;

    public Movie(String title, int year, String director, int rating) {
        this.title = title;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public boolean equals(Object obj) {
        Movie other = (Movie) obj;
        if(other.getTitle() == title && other.getYear() == year && other.getDirector() == director
                && other.getRating() == rating) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String details() {
        return title + "," + year + "," + director + "," + rating + "\n";
    }
}
