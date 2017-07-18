package com.example.yamamotoai.layoutcollection;

/**
 * Created by yamamotoai on 2017-07-18.
 */

public class RecyclerViewMovie {

    private String title, genre, year;

    public RecyclerViewMovie(String title, String genre, String year) {
        this.title = title;
        this.genre = genre;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getYear() {
        return year;
    }
}

