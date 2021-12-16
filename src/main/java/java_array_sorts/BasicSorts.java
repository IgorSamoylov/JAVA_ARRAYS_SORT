package java_array_sorts;

import java.util.Arrays;

/*
 *  Class contains simple O(N) = N^2 quadratic complexity algorithms
 *  for the array of integers
 */
public final class BasicSorts {
    // Do not instantiate this
    private BasicSorts() {
    }

    // Insertion sort with simple swaps for the items pairs
    public static void insertionSort(final int[] A) {

        for (int top = 1; top < A.length; top++) {
            int k = top;
            while (k > 0 && A[k - 1] > A[k]) {
                swap(A, k, k - 1);
                k--;
            }
        }
    }

    // Selection sort with simple swaps for the items pairs
    public static void selectionSort(final int[] A) {

        for (int pos = 0; pos < A.length - 1; pos++) {
            for (int k = pos + 1; k < A.length; k++) {
                if (A[pos] > A[k]) swap(A, pos, k);
            }
        }
    }

    // Selection sort without unnecessary swaps
    public static void selectionSort2(final int[] A) {

        for (int pos = 0; pos < A.length - 1; pos++) {
            int minValue = A[pos], minIndex = pos;
            for (int k = pos + 1; k < A.length; k++) {
                if (A[k] < minValue) {
                    minValue = A[k];
                    minIndex = k;
                }
            }
            // In-place swap
            int tmp = A[pos];
            A[pos] = minValue;
            A[minIndex] = tmp;
        }
    }

    // Bubble sort with checks that the array has been already sorted
    public static void bubbleSort(final int[] A) {

        for (int traverse = 1; traverse < A.length; traverse++) {
            boolean hasNotSwaps = true;
            for (int i = 0; i < A.length - traverse; i++) {
                if (A[i] > A[i + 1]) {
                    swap(A, i, i + 1);
                    hasNotSwaps = false;
                }
            }
            if (hasNotSwaps) return;
        }
    }

    // Simple util swap method that using a third variable
    private static void swap(final int[] A, final int n1, final int n2) {
        int tmp = A[n2];
        A[n2] = A[n1];
        A[n1] = tmp;
    }
}
