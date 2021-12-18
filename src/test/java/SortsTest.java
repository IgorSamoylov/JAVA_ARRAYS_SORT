import com.example.java_array_sorts.*;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class SortsTest {
    private static final int TEST_ARRAY_LENGTH = 400;
    private static final int TEST_ARRAY_VALUES_RANGE = 100;
    static Long init, end;
    private static int[] A;
    private static int[] sortedA;
    private SortsTest() {
    } // Do not instantiate this

    // Tests the result array for a random generated input data
    static void tester(@NotNull SortMethod sortMethod) {
        Util.generateRandomArray();
        init = System.nanoTime();
        sortMethod.sort(A);
        end = System.nanoTime();
        long timeResult = (end - init) / 1000;
        System.out.println("Time elapsed: " + timeResult + " mks");
        Assertions.assertArrayEquals(A, sortedA);
    }

    // Tests the result arrays in a trivial cases
    static void trivialTester(@NotNull SortMethod sortMethod) {
        Util.generateVoidArray();
        sortMethod.sort(A);
        Assertions.assertArrayEquals(A, sortedA);

        Util.generateUniformArray();
        sortMethod.sort(A);
        Assertions.assertArrayEquals(A, sortedA);

        Util.generateSimpleArray();
        sortMethod.sort(A);
        Assertions.assertArrayEquals(A, sortedA);
    }

    @Test
    void testInsertionSort() {
        System.out.println("\nInsertion Sort");
        trivialTester(BasicSorts::insertionSort);
        tester(BasicSorts::insertionSort);
    }

    @Test
    void testSelectionSort() {
        System.out.println("\nSelection Sort");
        trivialTester(BasicSorts::selectionSort);
        tester(BasicSorts::selectionSort);
    }

    @Test
    void testSelectionSort2() {
        System.out.println("\nSelection Sort 2");
        trivialTester(BasicSorts::selectionSort2);
        tester(BasicSorts::selectionSort2);
    }

    @Test
    void testBubbleSort() {
        System.out.println("\nBubble Sort");
        trivialTester(BasicSorts::bubbleSort);
        tester(BasicSorts::bubbleSort);
    }

    @Test
    void testCountingSort() {
        System.out.println("\nCounting Sort");
        CountingSort.prepare(TEST_ARRAY_VALUES_RANGE);
        tester(CountingSort::countingSort);
    }

    @Test
    void testQuickSort() {
        System.out.println("\nQuick Sort");
        trivialTester(FastSorts::quickSort);
        tester(FastSorts::quickSort);
    }

    @Test
    void testQuickSort2() {
        System.out.println("\nQuick Sort 2");
        trivialTester(FastSorts::quickSort2);
        tester(FastSorts::quickSort2);
    }

    @Test
    void testMergeSort() {
        System.out.println("\nMerge Sort");
        trivialTester(FastSorts::mergeSort);
        tester(FastSorts::mergeSort);
    }

    @Test
    void testHeapSort() {
        System.out.println("\nHeap Sort");
        trivialTester(FastSorts::heapSort);
        tester(FastSorts::heapSort);
    }

    @Test
    void testIterativeMergeSort() {
        System.out.println("\nIterative Merge Sort");
        trivialTester(IterativeFastSorts::mergeSortIterative);
        tester(IterativeFastSorts::mergeSortIterative);
    }

    @AfterEach
    void printResult() {
        java.util.Arrays.stream(A).forEach(n -> System.out.print(n + " "));
        System.out.println();
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