import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class SortsTest {

    private static final int TEST_ARRAY_LENGTH = 2000;
    private static final int TEST_ARRAY_VALUES_RANGE = 100;
    private static int[] A;
    private static int[] sortedA;

    static Long init, end;

    // Tests result array for a random generated input data
    static void tester(SortFunction sortFunction) {
        Util.generateRandomArray();
        init = System.nanoTime();
        sortFunction.sort(A);
        end = System.nanoTime();
        long timeResult = (end - init) / 1000;
        System.out.println("Time elapsed: " + timeResult + " mks");
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
        trivialTester(BasicSorts::insertionSort);
        tester(BasicSorts::insertionSort);
    }

    @Test
    void testSelectionSort() {
        System.out.println("Selection Sort");
        trivialTester(BasicSorts::selectionSort);
        tester(BasicSorts::selectionSort);
    }

    @Test
    void testSelectionSort2() {
        System.out.println("Selection Sort2");
        trivialTester(BasicSorts::selectionSort2);
        tester(BasicSorts::selectionSort2);
    }

    @Test
    void testBubbleSort() {
        System.out.println("Bubble Sort");
        trivialTester(BasicSorts::bubbleSort);
        tester(BasicSorts::bubbleSort);
    }

    @Test
    void testCountingSort() {
        System.out.println("Counting Sort");
        BasicSorts.CountingSort2.prepare(TEST_ARRAY_VALUES_RANGE);
        tester(BasicSorts.CountingSort2::countingSort);
    }

    @Test
    void testQuickSort() {
        System.out.println("Quick Sort");
        trivialTester(BasicSorts::quickSort);
        tester(BasicSorts::quickSort);
    }

    @Test
    void testQuickSort2() {
        System.out.println("Quick Sort - 2");
        trivialTester(BasicSorts::quickSort2);
        tester(BasicSorts::quickSort2);
    }

    @Test
    void testMergeSort() {
        System.out.println("Merge Sort");
        trivialTester(BasicSorts::mergeSort);
        tester(BasicSorts::mergeSort);
    }

    @Test
    void testIterativeMergeSort() {
        System.out.println("Iterative Merge Sort");
        trivialTester(IterativeFastSorts::mergeSort);
        tester(IterativeFastSorts::mergeSort);
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