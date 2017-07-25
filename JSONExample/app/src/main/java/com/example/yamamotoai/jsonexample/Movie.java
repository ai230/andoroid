package com.example.yamamotoai.jsonexample;

import java.io.Serializable;

/**
 * Created by yamamotoai on 2017-07-25.
 */

public class Movie implements Serializable {
    private String title;
    private String  genre, year,cast;
    private int thumbnail;
    private boolean isSelected; //boolean varaiable for a movie

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getYear() {
        return year;
    }

    public String getCast() {
        return cast;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
