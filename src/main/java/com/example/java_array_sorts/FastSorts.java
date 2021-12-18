package com.example.java_array_sorts;

import java.util.Arrays;

/*
 * Class contains recursive implementations of
 * fast O(N) = N * log(N) integer array sorts.
 *
 */

public final class FastSorts {
    // Lambda that swaps items by index x and y in the gotten array.
    private static final Swapper swapper = (array, x, y) -> {
        int tmp = array[x];
        array[x] = array[y];
        array[y] = tmp;
    };

    private FastSorts() {
    } // Do not instantiate this

    /*
        Quick Sort recursive algorithm for an integer array.
    * */
    public static int[] quickSort(final int[] arr) {

        int length = arr.length;
        if (length < 2) return arr;

        // This make sense to prevent unnecessary recursive calls
        // for the short arrays snippets
        // if(a.length < 50) insertionSort(a);

        int[] lowerValues = new int[arr.length];
        int lowerSize = 0;
        int[] higherValues = new int[arr.length];
        int higherSize = 0;

        int pivotIndex = (int) (Math.random() * length);
        int pivotValue = arr[pivotIndex];

        for (int n = 0; n < length; n++) {
            if (n == pivotIndex) continue;
            if (arr[n] < pivotValue) lowerValues[lowerSize++] = arr[n];
            else higherValues[higherSize++] = arr[n];
        }

        int[] lowerSortedArr =
                quickSort(Arrays.copyOf(lowerValues, lowerSize));
        int[] higherSortedArr =
                quickSort(Arrays.copyOf(higherValues, higherSize));

        System.arraycopy(lowerSortedArr, 0, arr, 0, lowerSize);
        arr[lowerSize] = pivotValue;
        System.arraycopy(higherSortedArr, 0, arr, lowerSize + 1, higherSize);
        return arr;
    }

    /*
     * QuickSort optimized algorithm that filling the
     * result array with the pivot values
     * */
    public static int[] quickSort2(final int[] arr) {

        int length = arr.length;
        if (length < 2) return arr;

        int[] lowerValues = new int[length];
        int lowerSize = 0;
        int[] higherValues = new int[length];
        int higherSize = 0;

        int pivotIndex = (int) (Math.random() * length);
        int pivotValue = arr[pivotIndex];
        int pivotValueCounter = 1;

        for (int n = 0; n < length; n++) {
            if (n == pivotIndex) continue;
            if (arr[n] < pivotValue) lowerValues[lowerSize++] = arr[n];
            else if (arr[n] == pivotValue) pivotValueCounter++;
            else higherValues[higherSize++] = arr[n];
        }

        int[] lowerSortedArr =
                quickSort2(Arrays.copyOf(lowerValues, lowerSize));
        int[] higherSortedArr =
                quickSort2(Arrays.copyOf(higherValues, higherSize));

        System.arraycopy(lowerSortedArr, 0, arr, 0, lowerSize);
        Arrays.fill(arr, lowerSize, lowerSize + pivotValueCounter, pivotValue);
        System.arraycopy(higherSortedArr, 0, arr,
                lowerSize + pivotValueCounter, higherSize);
        return arr;
    }

    /*
     *  Recursive implementation of Merge sort
     * */
    public static int[] mergeSort(final int[] arr) {
        if (arr.length == 2) {
            // Swap values if array has 2 items at incorrect order
            if (arr[0] > arr[1]) {
                swapper.swap(arr, 0, 1);
            }
            return arr;
        } else if (arr.length < 2) return arr;

        // This make sense to prevent unnecessary recursive calls
        // for the short arrays snippets
        // if(A.length < 10) {insertionSort(A); return arr;}

        int halfSize = arr.length / 2;
        int[] leftArray = mergeSort(
                Arrays.copyOfRange(arr, 0, halfSize));
        int[] rightArray = mergeSort(
                Arrays.copyOfRange(arr, halfSize, arr.length));

        int leftIter = 0;
        int rightIter = 0;
        int resultIter = 0;
        while (leftIter < leftArray.length && rightIter < rightArray.length) {

            if (leftArray[leftIter] <= rightArray[rightIter])
                arr[resultIter++] = leftArray[leftIter++];
            else arr[resultIter++] = rightArray[rightIter++];
        }

        while (leftIter < leftArray.length)
            arr[resultIter++] = leftArray[leftIter++];

        while (rightIter < rightArray.length)
            arr[resultIter++] = rightArray[rightIter++];

        return arr;
    }

    /*
     * Implementation of HeapSort for an integer array
     * that used lambda recursive calls without
     * extern methods calls.
     * */
    public static void heapSort(final int[] arr) {

        HeapMakerRecursive heapMakerRec
                = (heapMaker, firstIndex, length) -> {
            int currentLargest = firstIndex;
            int leftChild = 2 * firstIndex + 1;
            int rightChild = 2 * firstIndex + 2;

            if (leftChild < length && arr[leftChild] > arr[currentLargest])
                currentLargest = leftChild;
            if (rightChild < length && arr[rightChild] > arr[currentLargest])
                currentLargest = rightChild;

            if (currentLargest != firstIndex) {
                swapper.swap(arr, currentLargest, firstIndex);
                // Recursive inner call that
                // make heap in a remaining array range
                // right to left with this new value
                heapMaker.heapMake(currentLargest, length);
            }
        };
        // Making basic heap, starting at the half or array
        // and moving right to left
        for (int i = arr.length / 2 - 1; i > -1; --i) {
            heapMakerRec.heapMake(i, arr.length);
        }
        // Receiving top items from [0] index and swap it one-by-one
        // to the end of array, than create a new more shortest heap
        for (int i = arr.length - 1; i > 0; --i) {
            swapper.swap(arr, 0, i);
            heapMakerRec.heapMake(0, i);
        }

    }

    @FunctionalInterface
    interface HeapMaker {
        void heapMake(int firstIndex, int length);
    }

    @FunctionalInterface
    interface HeapMakerRecursive extends HeapMaker {
        void heapMake(HeapMaker hm, int firstIndex, int length);

        default void heapMake(int firstIndex, int length) {
            heapMake(this, firstIndex, length);
        }
    }

    @FunctionalInterface
    interface Swapper {
        void swap(int[] arr, int x, int y);
    }
}
