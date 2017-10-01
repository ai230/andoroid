package com.project.ai.todolist;

import java.util.Comparator;

/**
 * Created by yamamotoai on 2017-09-11.
 */

public class MyComparator implements Comparator {


    public static Comparator<TODO> DateComparator = new Comparator<TODO>(){

        @Override
        public int compare(TODO t1, TODO t2) {
            return t1.getDate().compareTo(t2.getDate());
        }

    };

    @Override
    public int compare(Object o, Object t1) {
        return 0;
    }
}
