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

    public static int[] quickSort(final int[] A) {

        if (A.length < 2) return A;

        // This make sense to prevent unnecessary recursive calls
        // for the short arrays snippets
        //if(A.length < 50) insertionSort(A);

        int[] lowerValues = new int[A.length];
        int lowerSize = 0;
        int[] higherValues = new int[A.length];
        int higherSize = 0;

        int pivotValue = A[0];

        for (int n = 1; n < A.length; n++) {
            if (A[n] < pivotValue) lowerValues[lowerSize++] = A[n];
            else higherValues[higherSize++] = A[n];
        }

        int[] lowerSortedArr =
                quickSort(Arrays.copyOf(lowerValues, lowerSize));
        int[] higherSortedArr =
                quickSort(Arrays.copyOf(higherValues, higherSize));

        System.arraycopy(lowerSortedArr, 0, A, 0, lowerSize);
        A[lowerSize] = pivotValue;
        System.arraycopy(higherSortedArr, 0, A, lowerSize + 1, higherSize);
        return A;
    }

    // QuickSort optimized algorithm that filling the result array with a pivot values
    public static int[] quickSort2(final int[] A) {

        if (A.length < 2) return A;

        int[] lowerValues = new int[A.length];
        int lowerSize = 0;
        int[] higherValues = new int[A.length];
        int higherSize = 0;

        int pivotValue = A[0];
        int pivotValueCounter = 1;

        for (int n = 1; n < A.length; n++) {
            if (A[n] < pivotValue) lowerValues[lowerSize++] = A[n];
            else if (A[n] == pivotValue) pivotValueCounter++;
            else higherValues[higherSize++] = A[n];
        }

        int[] lowerSortedArr =
                quickSort2(Arrays.copyOf(lowerValues, lowerSize));
        int[] higherSortedArr =
                quickSort2(Arrays.copyOf(higherValues, higherSize));

        System.arraycopy(lowerSortedArr, 0, A, 0, lowerSize);
        Arrays.fill(A, lowerSize, lowerSize + pivotValueCounter, pivotValue);
        System.arraycopy(higherSortedArr, 0, A,
                lowerSize + pivotValueCounter, higherSize);
        return A;
    }


    // Recursive implementation of merge sort
    public static int[] mergeSort(final int[] A) {
        if (A.length == 2) {
            if (A[0] > A[1]) swap(A, 0, 1);
            return A;
        } else if (A.length < 2) return A;

        // This make sense to prevent unnecessary recursive calls for the short arrays snippets
        // if(A.length < 10) {insertionSort(A); return A;}

        int halfSize = A.length / 2;
        int[] leftArray = mergeSort(Arrays.copyOfRange(A, 0, halfSize));
        int[] rightArray = mergeSort(Arrays.copyOfRange(A, halfSize, A.length));

        int leftIter = 0, rightIter = 0, resultIter = 0;
        while (leftIter < leftArray.length && rightIter < rightArray.length) {

            if (leftArray[leftIter] <= rightArray[rightIter])
                A[resultIter++] = leftArray[leftIter++];
            else A[resultIter++] = rightArray[rightIter++];
        }

        while (leftIter < leftArray.length)
            A[resultIter++] = leftArray[leftIter++];

        while (rightIter < rightArray.length)
            A[resultIter++] = rightArray[rightIter++];

        return A;
    }

    // Simple util swap method that using a third variable
    private static void swap(final int[] A, final int n1, final int n2) {
        int tmp = A[n2];
        A[n2] = A[n1];
        A[n1] = tmp;
    }
}
