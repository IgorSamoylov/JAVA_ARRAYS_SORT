import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;


class SortsTest {

    private static final int TEST_ARRAY_LENGTH = 450;
    private static final int TEST_ARRAY_VALUES_RANGE = 100;
    private static int[] A;
    private static int[] sortedA;

   // static AtomicLong init, time;

    // Tests result array for a random generated input data
    static void tester(SortFunction sortFunction) {
        Util.generateRandomArray();
        //init = new AtomicLong(System.nanoTime());
        sortFunction.sort(A);
        //time = new AtomicLong(System.nanoTime() - init.longValue());
        //System.out.println("Time elapsed: " + time.longValue() / 1000 + "ms");
        Assertions.assertArrayEquals(A, sortedA);
    }

    // Tests result arrays in a trivial cases
    static void trivialTester(SortFunction sortFunction) {
        Util.generateVoidArray();
        sortFunction.sort(A);
        Assertions.assertArrayEquals(A, sortedA);

        Util.generateUniformArray();
        sortFunction.sort(A);
        Assertions.assertArrayEquals(A, sortedA);

        Util.generateSimpleArray();
        sortFunction.sort(A);
        Assertions.assertArrayEquals(A, sortedA);
    }

    @Test
    void testInsertionSort() {
        System.out.println("Insertion Sort");
        trivialTester(Sorts::insertionSort);
        tester(Sorts::insertionSort);
    }

    @Test
    void testSelectionSort() {
        System.out.println("Selection Sort");
        trivialTester(Sorts::selectionSort);
        tester(Sorts::selectionSort);
    }

    @Test
    void testSelectionSort2() {
        System.out.println("Selection Sort2");
        trivialTester(Sorts::selectionSort2);
        tester(Sorts::selectionSort2);
    }

    @Test
    void testBubbleSort() {
        System.out.println("Bubble Sort");
        trivialTester(Sorts::bubbleSort);
        tester(Sorts::bubbleSort);
    }

    @Test
    void testCountingSort() {
        System.out.println("Counting Sort");
        Sorts.CountingSort2.prepare(TEST_ARRAY_VALUES_RANGE);
        tester(Sorts.CountingSort2::countingSort);
    }

   /* @Test
    void testSelectionSort3() {
        System.out.println("Selection Sort3");
        testerReturn(Sorts::selectionSort3);
    }*/

    @Test
    void testQuickSort() {
        System.out.println("Quick Sort");
        trivialTester(Sorts::quickSort);
        tester(Sorts::quickSort);
    }

    @Test
    void testMergeSort() {
        System.out.println("Merge Sort");
        trivialTester(Sorts::mergeSort);
        tester(Sorts::mergeSort);
    }

    @AfterEach
    void printResult() {
        java.util.Arrays.stream(A).forEach(n -> System.out.print(n + " "));
        System.out.println();
    }

    interface SortFunction {
        void sort(int[] array);
    }

    static class Util {
        static void generateVoidArray() {
            A = new int[0];
            sortedA = new int[0];
        }

        static void generateUniformArray() {
            A = java.util.stream.IntStream
                    .generate(() -> 100)
                    .limit(TEST_ARRAY_LENGTH)
                    .toArray();
            sortedA = Arrays.copyOf(A, A.length);

        }

        static void generateSimpleArray() {
            A = new int[]{1, 2, 3, 4};
            sortedA = new int[]{1, 2, 3, 4};
        }

        static void generateRandomArray() {
            A = java.util.stream.IntStream
                    .generate(() -> (int) (Math.random() * TEST_ARRAY_VALUES_RANGE))
                    .limit(TEST_ARRAY_LENGTH)
                    .toArray();

            sortedA = Arrays.copyOf(A, A.length);
            Arrays.sort(sortedA);
        }
    }
}



 /*
  interface SortFunctionReturn {
        int[] sort(int[] array);
    }


 static void testerReturn(SortFunctionReturn sortFunctionReturn) {
        generateRandomArray();
        //long init = System.nanoTime();
        A = sortFunctionReturn.sort(A);
       // long time = System.nanoTime() - init;
        //System.out.println("Time elapsed: " + time / 1000 + "ms");
        Assertions.assertArrayEquals(A, sortedA);
    }*/