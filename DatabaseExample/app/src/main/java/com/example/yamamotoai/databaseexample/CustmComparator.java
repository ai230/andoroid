package com.example.yamamotoai.databaseexample;

import java.util.*;

/**
 * Created by yamamotoai on 2017-08-16.
 */

public abstract class CustmComparator implements Comparator<Book>{

    public static Comparator<Book> sortTitle = new Comparator<Book>() {
        @Override
        public int compare(Book o1, Book o2) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
    };

    public static Comparator<Book> sortAuthor = new Comparator<Book>() {
        @Override
        public int compare(Book o1, Book o2) {
            return o1.getAuthor().compareTo(o2.getAuthor());
        }
    };
}
