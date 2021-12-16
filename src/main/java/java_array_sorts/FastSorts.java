package java_array_sorts;

import java.util.Arrays;
import java.util.Random;

/*
    * Class contains recursive implementations of
    * fast O(N) = N * log(N) integer array sorts.
    *
*/

public class FastSorts {
    private FastSorts() { } // Do not instantiate this

    // Nested class-wrapper to prevent numerous instantiations of java.util.Random class
    private static class RandomGenerator {
        private static final Random random = new Random();
        public static int getRandomInt(int bound) {
            return random.nextInt(bound);
        }
    }

    public static int[] quickSort(final int[] A) {

        int length = A.length;
        if (length < 2) return A;

        // This make sense to prevent unnecessary recursive calls
        // for the short arrays snippets
        //if(A.length < 50) insertionSort(A);

        int[] lowerValues = new int[A.length];
        int lowerSize = 0;
        int[] higherValues = new int[A.length];
        int higherSize = 0;

        int pivotIndex = RandomGenerator.getRandomInt(length - 1);
        int pivotValue = A[pivotIndex];

        for (int n = 0; n < length; n++) {
            if (n == pivotIndex) continue;
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

        int length = A.length;
        if (length < 2) return A;

        int[] lowerValues = new int[length];
        int lowerSize = 0;
        int[] higherValues = new int[length];
        int higherSize = 0;

        int pivotIndex = RandomGenerator.getRandomInt(length - 1);
        int pivotValue = A[pivotIndex];
        int pivotValueCounter = 1;

        for (int n = 0; n < length; n++) {
            if (n == pivotIndex) continue;
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
            if (A[0] > A[1]) { // Swap values if array has 2 items at incorrect order
                int tmp = A[1];
                A[1] = A[0];
                A[0] = tmp;
            };
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
}
