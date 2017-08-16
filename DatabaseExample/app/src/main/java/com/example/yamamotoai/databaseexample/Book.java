package com.example.yamamotoai.databaseexample;

/**
 * Created by yamamotoai on 2017-08-14.
 */

public class Book {
    private int id;
    private String title;
    private String author;

    public  Book(){}

      public Book(String title, String author){
          this.title = title;
          this.author = author;
      }


    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "BOOK [ id = " + id + "title = " + title + "author = " + author + " ]";
    }
}
