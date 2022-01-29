package com.example.java_array_sorts;

import org.jetbrains.annotations.NotNull;

public class ComparableClass implements Comparable<ComparableClass> {
    int val;
    public ComparableClass(int val) {
        this.val = val;
    }

    @Override
    public int compareTo(@NotNull ComparableClass o) {
        return Integer.compare(val, o.val);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComparableClass comp)) return false;
        return val == comp.val;
    }

    @Override
    public int hashCode() {
        return val;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}
