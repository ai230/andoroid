package com.example.yamamotoai.movielist;

/**
 * Created by yamamotoai on 2017-07-19.
 */

public class Movie {

    private String title, genre, year, img;

    public Movie(String title, String genre, String year, String img) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.img = img;
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

    public String getImg() {
        return img;
    }


}
