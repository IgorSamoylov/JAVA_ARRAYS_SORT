package com.example.java_array_sorts;

import java.util.Arrays;

/*
 * Class contains recursive implementations of
 * fast O(N) = N * log(N) integer array sorts.
 *
 */

public final class FastSorts {
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
     * Recursive QuickSort optimized algorithm that filling the
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
                swap(arr, 0, 1);
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
     * Implementation of HeapSort for presented integer array
     * with calls for makeHeap() method
     * */
    public static void heapSort(final int[] arr) {
        // Making basic heap, starting at the half or array
        // and moving right to left
        for (int i = arr.length / 2 - 1; i > -1; --i) {
            makeHeap(arr, i, arr.length);
        }
        // Receiving top items from [0] index and swap it one-by-one
        // to the end of array, than create a new more shortest range
        // heap in array
        for (int i = arr.length - 1; i > 0; --i) {
            swap(arr, 0, i);
            makeHeap(arr, 0, i);
        }
    }

    /* Makes heap structure in the presented integer array arr,
     * firstIndex means first item to proceed making heap,
     * processingLength means last index in array that not be proceeded.
     * */
    private static void makeHeap(int[] arr, int firstIndex, int processingLength) {
        int currentLargest = firstIndex;
        int leftChild = 2 * firstIndex + 1;
        int rightChild = 2 * firstIndex + 2;

        if (leftChild < processingLength && arr[leftChild] > arr[currentLargest])
            currentLargest = leftChild;
        if (rightChild < processingLength && arr[rightChild] > arr[currentLargest])
            currentLargest = rightChild;

        if (currentLargest != firstIndex) {
            swap(arr, currentLargest, firstIndex);
            // Recursive inner call that
            // make heap in a remaining array range
            // right to left with this new value
            makeHeap(arr, currentLargest, processingLength);
        }
    }

    public static void heapSort2(final int[] arr) {
        for (int i = arr.length / 2; i >= 0; i--) {
            siftDown(arr, i, arr.length);
        }
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[0];
            swap(arr, 0, arr.length - 1 - i);
            siftDown(arr, 0, arr.length - 1 - i);
        }
        System.arraycopy(result, 0, arr, 0, result.length);
    }

    private static void siftDown(final int[] arr, int index, int size) {
        while (index * 2 + 1 < size) {
            int leftChild = index * 2 + 1;
            int rightChild = index * 2 + 2;
            int biggestItem = leftChild;

            if (rightChild < size && arr[rightChild] < arr[leftChild]) {
                biggestItem = rightChild;
            }
            if (arr[index] <= arr[biggestItem])
                break;
            swap(arr, index, biggestItem);
            index = biggestItem;
        }
    }
    private static void siftUp(final int[] arr, int index) {
        int parent = (index - 1) / 2;
        while (index != 0 && arr[index] < arr[parent]) {
            swap(arr, index, parent);
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    // Auxiliary method that swaps items by index x and y in the gotten array.
    private static void swap(int[] array, int x, int y) {
        int tmp = array[x];
        array[x] = array[y];
        array[y] = tmp;
    }
}
