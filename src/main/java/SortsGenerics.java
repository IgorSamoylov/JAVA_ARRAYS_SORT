import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.swap;

public final class SortsGenerics {
    private SortsGenerics() {
        // Do not instantiate this
    }

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
}
