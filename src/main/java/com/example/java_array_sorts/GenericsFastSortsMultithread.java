package com.example.java_array_sorts;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class GenericsFastSortsMultithread {
    private GenericsFastSortsMultithread() {} // Do not instantiate this

    /*
     *  Method - adapter that converts List<Comparable <<T> to
     * primitive Object T[] array for mergeSortC recursive function
     * and receive the result T[] array from it with back conversion.
     * Works faster that the function with simple List operations only.
     * */
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> void mergeSortMultithread(final List<T> A) {
        Object[] objectArray = new Object[A.size()];
        for (int i = 0; i < A.size(); ++i) {
            objectArray[i] = A.get(i);
        }

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SortTaskRecursive<T> sortTaskRecursive = new SortTaskRecursive<>();
        sortTaskRecursive.setRawResult(objectArray);
        objectArray = forkJoinPool.invoke(sortTaskRecursive);
        for (int i = 0; i < objectArray.length; ++i) {
            A.set(i, (T) objectArray[i]);
        }
    }
}

class SortTaskRecursive<T extends Comparable<T>> extends ForkJoinTask<Object[]> {
    SortTaskRecursive() {
        //System.out.println(Thread.currentThread().getName() + " works!");
    }
    // Field contains objects that implement Comparable interface
    private Object[] arr;

    // Gives input data into class object for performing async work
    @Override
    protected void setRawResult(Object[] value) {
        arr = value;
    }
    // Returns completed result of async work
    @Override
    public Object[] getRawResult() {
        return arr;
    }

    /*
     *  Executes recursive Merge sort for primitive Object array and
     * sends sub-arrays to a new recursive instanced classes in common
     * ForkJoinPool
     * */
    @SuppressWarnings("unchecked")
    @Override
    protected boolean exec() {
        /*if (arr.length == 2) {
            // Swap values if array has 2 items at incorrect order
            if (((Comparable<T>) (arr[0])).compareTo((T) (arr[1])) > 0) {
                Object tmp = arr[1];
                arr[1] = arr[0];
                arr[0] = tmp;
            }
            return true;
        } else if (arr.length < 2) return true;*/
        if (arr.length < 50) {
            boolean noSwaps = false;
            int traversals = arr.length;

            while (!noSwaps || traversals > 0) {
                noSwaps = true;
                for (int i = 0; i < traversals - 1; ++i) {
                    if (((Comparable<T>) (arr[i])).compareTo((T) (arr[i + 1])) > 0) {
                        Object tmp = arr[1];
                        arr[1] = arr[0];
                        arr[0] = tmp;
                        noSwaps = false;
                    }
                    traversals--;
                }
            }
            return true;
        }

        int halfSize = arr.length / 2;
        // Creating new half-sized sub-arrays
        Object[] leftArray = Arrays.copyOfRange(arr, 0, halfSize);
        Object[] rightArray = Arrays.copyOfRange(arr, halfSize, arr.length);
        // Recursively creating task objects
        SortTaskRecursive<T> sortTaskRecursiveLeft
                = new SortTaskRecursive<>();
        SortTaskRecursive<T> sortTaskRecursiveRight
                = new SortTaskRecursive<>();
        // Sending data
        sortTaskRecursiveLeft.setRawResult(leftArray);
        sortTaskRecursiveRight.setRawResult(rightArray);
        // Forking tasks
        sortTaskRecursiveLeft.fork();
        sortTaskRecursiveRight.fork();
        // Receiving results
        leftArray = sortTaskRecursiveLeft.join();
        rightArray = sortTaskRecursiveRight.join();

        // Merging
        int leftIter = 0;
        int rightIter = 0;
        int resultIter = 0;
        while (leftIter < leftArray.length && rightIter < rightArray.length) {

            if (((Comparable<T>) leftArray[leftIter])
                    .compareTo((T) rightArray[rightIter]) <= 0)
                arr[resultIter++] = leftArray[leftIter++];
            else arr[resultIter++] = rightArray[rightIter++];
        }

        while (leftIter < leftArray.length)
            arr[resultIter++] = leftArray[leftIter++];

        while (rightIter < rightArray.length)
            arr[resultIter++] = rightArray[rightIter++];

        return true;
    }
}
