
import java.util.Arrays;

public class Sorts {

    public static void insertionSort(int[] A) {

        for (int top = 1; top < A.length; top++) {
            int k = top;
            while (k > 0 && A[k - 1] > A[k]) {
                swap(A, k, k - 1);
                k--;
            }
        }
    }
    // Selection sort with simple swaps for the items pairs
    public static void selectionSort(int[] A) {

        for (int pos = 0; pos < A.length - 1; pos++) {
            for (int k = pos + 1; k < A.length; k++) {
                if (A[pos] > A[k]) swap(A, pos, k);
            }
        }
    }
    // Selection sort without unnecessary swaps
    public static void selectionSort2(int[] A) {

        for(int pos = 0; pos < A.length - 1; pos++) {
            int minValue = A[pos], minIndex = pos;
            for (int k = pos + 1; k < A.length; k++) {
                if (A[k] <= minValue) {minValue = A[k]; minIndex = k;}
            }

            int tmp = A[pos];
            A[pos] = minValue;
            A[minIndex] = tmp;
        }
    }

    // TODO: make M(n * 2) version
    public static int[] selectionSort3(int[] A) {

        int[] resultArray = new int[A.length];
        int lastMin = -1;
        for(int pos = 0; pos < A.length; pos++) {
            int minValue = Integer.MAX_VALUE;
            for (int i : A) {
                if (i > lastMin && i <= minValue) minValue = i;
            }

            resultArray[pos] = minValue;
            lastMin = minValue;

        }
        return resultArray;
    }


    public static void bubbleSort(int[] A) {

        for (int traverse = 1; traverse < A.length; traverse++) {
            for (int i = 0; i < A.length - traverse; i++) {
                if (A[i] > A[i + 1]) swap(A, i, i + 1);
            }
        }
    }

    public static int[] quickSort(int[] A) {

        if(A.length < 2) return A;

        int[] lowerValues = new int[A.length]; int lowerSize = 0;
        int[] higherValues = new int[A.length]; int higherSize = 0;

        //int pivotIndex = (int)(Math.random() * A.length);
        int pivotIndex = A.length >> 1;
        int pivotValue = A[pivotIndex];

        for(int n = 0; n < A.length; n++) {
            if(n == pivotIndex) continue;
            if(A[n] < pivotValue) lowerValues[lowerSize++] = A[n];
            else higherValues[higherSize++] = A[n];
        }

        int[] lowerSortedArr = quickSort(Arrays.copyOf(lowerValues, lowerSize));
        int[] higherSortedArr = quickSort(Arrays.copyOf(higherValues, higherSize));

        System.arraycopy(lowerSortedArr, 0, A, 0, lowerSize);
        A[lowerSize] = pivotValue;
        System.arraycopy(higherSortedArr, 0, A, lowerSize + 1, higherSize);
        return A;
    }


    public static int[] mergeSort(int[] A) {
        if(A.length == 2) {
            if(A[0] > A[1]) swap(A, 0, 1);
            return A;
        }
        else if(A.length < 2) return A;

        int halfSize = A.length / 2;
        int[] leftArray = mergeSort(Arrays.copyOfRange(A, 0, halfSize));
        int[] rightArray = mergeSort(Arrays.copyOfRange(A, halfSize, A.length));

        int leftIter = 0, rightIter = 0, resultIter = 0;
        while(leftIter < leftArray.length && rightIter < rightArray.length) {

                if (leftArray[leftIter] <= rightArray[rightIter])
                    A[resultIter++] = leftArray[leftIter++];
                else A[resultIter++] = rightArray[rightIter++];
        }

        while(leftIter < leftArray.length) A[resultIter++] = leftArray[leftIter++];

        while(rightIter < rightArray.length) A[resultIter++] = rightArray[rightIter++];

        return A;
    }

    private static void swap(int[] A, int n1, int n2) {
        int tmp = A[n2];
        A[n2] = A[n1];
        A[n1] = tmp;
    }

    static class CountingSort2 {
        private static int[] counter;
        public static void prepare(int valuesRange) {
            counter = new int[valuesRange];
        }

        public static void countingSort(int[] A) {
            // Index of an counter array item means value of the input array item
            // Value of an counter array item = number of a repeating items in the input array
            for (int i : A) counter[i]++;
            int n = 0;
            for (int j = 0; j < counter.length; j++)
                for (int valAmount = counter[j]; valAmount > 0; valAmount--) A[n++] = j;
        }
    }
}
