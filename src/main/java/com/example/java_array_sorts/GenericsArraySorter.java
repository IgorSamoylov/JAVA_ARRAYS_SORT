package com.example.java_array_sorts;

import java.util.List;

public interface GenericsArraySorter {
    <T extends Comparable<T>> void sort(List<T> input);
}

