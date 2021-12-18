package com.example.java_array_sorts;

import java.util.List;

public interface GenericsArraySorterReturn {
    <T extends Comparable<T>> List<T> sort(List<T> input);
}
