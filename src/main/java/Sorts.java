import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

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

    public static void selectionSort(int[] A) {

        for (int pos = 0; pos < A.length - 1; pos++) {
            for (int k = pos + 1; k < A.length; k++) {
                if (A[pos] > A[k]) swap(A, pos, k);
            }
        }
    }

    public static void bubbleSort(int[] A) {

        for (int traverse = 1; traverse < A.length; traverse++) {
            for (int i = 0; i < A.length - traverse; i++) {
                if (A[i] > A[i + 1]) swap(A, i, i + 1);
            }
        }
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

            for(int i : A) counter[i]++; // Index of counter array = value of input array

            int n = 0;
            for (int j = 0; j < counter.length; j++)
                for (int freq = counter[j]; freq > 0; freq--) A[n++] = j;
        }
    }
}
