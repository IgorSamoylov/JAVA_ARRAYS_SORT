package java_array_sorts;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.swap;

public final class SortsGenerics {
    private SortsGenerics() { } // Do not instantiate this

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
}
