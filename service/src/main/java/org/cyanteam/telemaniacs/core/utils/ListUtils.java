package org.cyanteam.telemaniacs.core.utils;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {
    public static <T> List<T> createList(T... items) {
        List<T> list = new ArrayList<>();
        for (T item : items) {
            list.add(item);
        }

        return list;
    }
}
