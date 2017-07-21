package com.example.yamamotoai.movielist;

/**
 * Created by yamamotoai on 2017-07-19.
 */

public class Movie {

    private String title, detail, year, img;

    public Movie(String title, String detail, String year, String img) {
        this.title = title;
        this.detail = detail;
        this.year = year;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public String getYear() {
        return year;
    }

    public String getImg() {
        return img;
    }


}
