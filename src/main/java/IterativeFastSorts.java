import java.util.Arrays;

public class IterativeFastSorts {

    // Iterative implementation for MergeSort algorithm, which used M(2*n) of memory
    public static void mergeSort(int[] A) {
        int size = A.length;
        int[] buffer = new int[size];

        for(int step = 1; step <= size; step *= 2) { // Half-step x2 magnifier
            int bufIter = 0;
            // step = 1  i = 0, 2, 4...
            // step = 2  i = 0, 4, 8...
            // step = 4  i = 0, 8, 16...
            for(int i = 0; i < size; i += (step << 1)) { // i means the first item of each processing group

                int middle = i + step; // = 1, 3, 5... = 2, 6, 10... = 4, 12, 20...
                int leftIter = i, rightIter = middle, end = middle + step;
                while(leftIter < middle && rightIter < end && rightIter < size) { // Merging
                    if(A[leftIter] <= A[rightIter]) buffer[bufIter++] = A[leftIter++];
                    else buffer[bufIter++] = A[rightIter++];
                }
                while(leftIter < middle && leftIter < size) buffer[bufIter++] = A[leftIter++]; // Appending left or right tails
                while(rightIter < end && rightIter < size) buffer[bufIter++] = A[rightIter++];
            }
            System.arraycopy(buffer, 0, A, 0, size); // Drop buffer array to the source array on an each traversal
        }
    }



    public static void main(String[] args) {
        int[] A = {6, 7, 4, 3, 2, 1, 5, 0, 9, 17};
        mergeSort(A);
        System.out.println(Arrays.toString(A));
    }
}
