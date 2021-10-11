import java.util.Arrays;

public class IterativeFastSorts {

    // Iterative implementation for MergeSort algorithm, which used M(2*n) of memory
    public static void mergeSort(int[] A) {
        int[] buffer = new int[A.length];

        for(int m = 1; m < A.length ; m *= 2) { // Step / 2 magnifier
            int bufIter = 0;
            // m = 1  i = 0, 2, 4...
            // m = 2  i = 0, 4, 8...
            // m = 4  i = 0, 8, 16...
            for(int i = 0; i < A.length; i += 2 * m) { // i means the first item of each processing group
                int middle = i + m; // = 1, 3, 5... = 2, 6, 10... = 4, 12, 20...
                int leftIter = i, rightIter = middle;
                while(leftIter < middle && rightIter < middle + m && rightIter < A.length) { // Merging
                    if(A[leftIter] < A[rightIter]) buffer[bufIter++] = A[leftIter++];
                    else buffer[bufIter++] = A[rightIter++];
                }
                while(leftIter < middle && leftIter < A.length) buffer[bufIter++] = A[leftIter++]; // Appending left or right tails
                while(rightIter < middle + m && rightIter < A.length) buffer[bufIter++] = A[rightIter++];
            }
            System.arraycopy(buffer, 0, A, 0, A.length); // Drop buffer array to the source array on an each traversal
        }
    }

    public static void main(String[] args) {
        int[] A = {6, 7, 4, 3, 2, 1, 5, 0, 9};
        mergeSort(A);
        System.out.println(Arrays.toString(A));
    }
}
