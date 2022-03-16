package com.example.java_array_sorts;

import java.util.Map;
import java.util.TreeMap;

public class CountingSortTM {


    public static void countingSortTM(final int[] A) {
        Map<Integer, Integer> counter = new TreeMap<>();

        for (int n : A) {
            counter.put(n, counter.getOrDefault(n, 0) + 1);
        }

        int count = 0;
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++)
                A[count++] = entry.getKey();
        }
    }
}
