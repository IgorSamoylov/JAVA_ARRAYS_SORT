import java_array_sorts.BasicSorts;
import java_array_sorts.CountingSort;
import java_array_sorts.IterativeFastSorts;
import java_array_sorts.SortMethod;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class SortsTest {
    private SortsTest() {} // Do not instantiate this

    private static final int TEST_ARRAY_LENGTH = 1000;
    private static final int TEST_ARRAY_VALUES_RANGE = 100;
    static Long init, end;
    private static int[] A;
    private static int[] sortedA;

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
        trivialTester(BasicSorts::quickSort);
        tester(BasicSorts::quickSort);
    }

    @Test
    void testQuickSort2() {
        System.out.println("\nQuick Sort 2");
        trivialTester(BasicSorts::quickSort2);
        tester(BasicSorts::quickSort2);
    }

    @Test
    void testMergeSort() {
        System.out.println("\nMerge Sort");
        trivialTester(BasicSorts::mergeSort);
        tester(BasicSorts::mergeSort);
    }

    @Test
    void testIterativeMergeSort() {
        System.out.println("\nIterative Merge Sort");
        trivialTester(IterativeFastSorts::mergeSort);
        tester(IterativeFastSorts::mergeSort);
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