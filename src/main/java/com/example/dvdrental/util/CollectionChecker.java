package com.example.dvdrental.util;

import java.util.Collection;

public class CollectionChecker {

    // Null-safe check
    // Check collection is empty or not

    public static boolean isEmpty(Collection collection) {
        if( collection == null )
            return true;
        else
            return collection.isEmpty();
    }

}
