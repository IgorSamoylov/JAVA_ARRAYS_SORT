package com.example.java_array_sorts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.swap;

public final class SortsGenerics {
    private SortsGenerics() { } // Do not instantiate this

    /*
    * Recursive implementation of Merge Sort, works with Comparable<T> generics
    * list. Returns result in the other List,
    * */
    public static <T extends Comparable<T>> List<T> mergeSort(final List<T> A) {
        int size = A.size();
        // Recursion end
        if (size == 2) {
            if (A.get(0).compareTo(A.get(1)) > 0) swap(A, 0, 1);
            return A;
        } else if (size < 2) return A;

        // Divide and conquer
        int halfSize = size / 2;
        List<T> leftArray = mergeSort(A.subList(0, halfSize));
        List<T> rightArray = mergeSort(A.subList(halfSize, size));

        // Preparing iterator variables
        int leftIter = 0;
        int rightIter = 0;
        int sizeL = leftArray.size();
        int sizeR = rightArray.size();
        List<T> resultArr = new ArrayList<>(size);

        // Merging
        while (leftIter < sizeL && rightIter < sizeR) {

            if (leftArray.get(leftIter)
                    .compareTo(rightArray.get(rightIter)) <= 0)
                resultArr.add(leftArray.get(leftIter++));
            else resultArr.add(rightArray.get(rightIter++));
        }
        // Appending tails
        while (leftIter < sizeL)
            resultArr.add(leftArray.get(leftIter++));
        while (rightIter < sizeR)
            resultArr.add(rightArray.get(rightIter++));

        return resultArr;
    }

    /*
    *  Function - adapter that converts List<Comparable <<T> to
    * primitive Object T[] array for mergeSortC recursive function
    * and receive the result T[] array from it with back conversion.
    * Works faster that the function with simple List operations only.
    * */
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> void mergeSort2(final List<T> A) {
        Object[] objectArray = new Object[A.size()];
        for (int i = 0; i < A.size(); ++i) {
            objectArray[i] = A.get(i);
        }
        objectArray = mergeSortC(objectArray);
        for (int i = 0; i < objectArray.length; ++i) {
            A.set(i, (T) objectArray[i]);
        }
    }

    /*
     *  Private recursive implementation of Merge sort for primitive Object array
     *  contains objects that implements Comparable interface
     * */
    @SuppressWarnings("unchecked")
    private static <T extends Comparable <T>> Object[] mergeSortC(final Object[] arr) {
        if (arr.length == 2) {
            // Swap values if array has 2 items at incorrect order
            if (((Comparable<T>)(arr[0])).compareTo((T)(arr[1])) > 0) {
                Object tmp = arr[1];
                arr[1] = arr[0];
                arr[0] = tmp;
            }
            return arr;
        } else if (arr.length < 2) return arr;

        int halfSize = arr.length / 2;
        Object[] leftArray = mergeSortC(
                Arrays.copyOfRange(arr, 0, halfSize));
        Object[] rightArray = mergeSortC(
                Arrays.copyOfRange(arr, halfSize, arr.length));

        int leftIter = 0;
        int rightIter = 0;
        int resultIter = 0;
        while (leftIter < leftArray.length && rightIter < rightArray.length) {

            if (((Comparable<T>)leftArray[leftIter])
                    .compareTo((T)rightArray[rightIter]) <= 0)
                arr[resultIter++] = leftArray[leftIter++];
            else arr[resultIter++] = rightArray[rightIter++];
        }

        while (leftIter < leftArray.length)
            arr[resultIter++] = leftArray[leftIter++];

        while (rightIter < rightArray.length)
            arr[resultIter++] = rightArray[rightIter++];

        return arr;
    }

    /* Iterative implementation for O(N)=N*log(N) MergeSort algorithm,
     * which used M(3*N) of memory
     * Used two primitive Object arrays as fast inner containers of generics type objects.
     */
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> void mergeSortIterative(final List<T> sourceList) {
        int size = sourceList.size();

        Object[] array = sourceList.toArray(); // Copying source to the primitive array
        Object[] buffer = new Object[size];

        for (int step = 1; step <= size; step *= 2) { // Half-step x2 magnifier
            int bufIter = 0;
            // step = 1  i = 0, 2, 4...
            // step = 2  i = 0, 4, 8...
            // step = 4  i = 0, 8, 16...
            for (int i = 0; i < size; i += (step << 1)) { // i means the first item of each processing group

                int middle = i + step; // = 1, 3, 5... = 2, 6, 10... = 4, 12, 20...
                int leftIter = i, rightIter = middle, end = middle + step;

                // Merging
                while (leftIter < middle && rightIter < end && rightIter < size) {
                    if (((Comparable<T>) array[leftIter])
                            .compareTo((T) array[rightIter]) <= 0)
                        buffer[bufIter++] = array[leftIter++];
                    else buffer[bufIter++] = array[rightIter++];
                }
                // Appending left or right tails
                while (leftIter < middle && leftIter < size)
                    buffer[bufIter++] = array[leftIter++];
                while (rightIter < end && rightIter < size)
                    buffer[bufIter++] = array[rightIter++];
            }
            // Drop buffer array to the first array on an each traversal
            System.arraycopy(buffer, 0, array, 0, size);
        }

        // Set values to source generics List
        for (int i = 0; i < size; ++i) {
            sourceList.set(i, (T) array[i]);
        }

    }

    public static <T extends Comparable<T>> void quickSortIterative(List<T> inputList) {

        Object[] A = inputList.toArray();

    }

    /*
     * Implementation of HeapSort for presented Generic List
     * with calls for makeHeap() method
     * */
    public static <T extends Comparable<T>> void heapSort(
            final List<T> arr) {
        int size = arr.size();
        // Making basic heap, starting at the half or array
        // and moving right to left
        for (int i = size / 2 - 1; i > -1; --i) {
            makeHeap(arr, i, size);
        }
        // Receiving top items from [0] index and swap it one-by-one
        // to the end of array, than create a new more shortest range
        // heap in array
        for (int i = size - 1; i > 0; --i) {
            swap(arr, 0, i);
            makeHeap(arr, 0, i);
        }
    }

    /* Makes heap structure in the presented integer array arr,
     * firstIndex means first item to proceed making heap,
     * processingLength means last index in array that not be proceeded.
     * */
    private static <T extends Comparable<T>> void makeHeap(
            List<T> arr, int firstIndex, int processingLength) {
        int currentLargest = firstIndex;
        int leftChild = 2 * firstIndex + 1;
        int rightChild = 2 * firstIndex + 2;

        if (leftChild < processingLength
                && arr.get(leftChild).compareTo(arr.get(currentLargest)) > 0) {
            currentLargest = leftChild;
        }
        if (rightChild < processingLength
                && arr.get(rightChild).compareTo(arr.get(currentLargest)) > 0) {
            currentLargest = rightChild;
        }

        if (currentLargest != firstIndex) {
            swap(arr, currentLargest, firstIndex);
            // Recursive inner call that
            // make heap in a remaining array range
            // right to left with this new value
            makeHeap(arr, currentLargest, processingLength);
        }
    }
}
