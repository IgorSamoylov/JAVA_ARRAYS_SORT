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
    static void tester(@NotNull SortMethod sortMethod, String name) {
        Util.generateRandomArray();
        init = System.nanoTime();
        sortMethod.sort(A);
        end = System.nanoTime();
        long timeResult = (end - init) / 1000;
        System.out.println("\n" + name + "\nTime elapsed: "
                + timeResult + " mks");
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
        trivialTester(BasicSorts::insertionSort);
        tester(BasicSorts::insertionSort,
                "Insertion Sort");
    }

    @Test
    void testSelectionSort() {
        trivialTester(BasicSorts::selectionSort);
        tester(BasicSorts::selectionSort,
                "Selection Sort");
    }

    @Test
    void testSelectionSort2() {
        trivialTester(BasicSorts::selectionSort2);
        tester(BasicSorts::selectionSort2,
                "Selection Sort 2");
    }

    @Test
    void testBubbleSort() {
        trivialTester(BasicSorts::bubbleSort);
        tester(BasicSorts::bubbleSort,
                "Bubble Sort");
    }

    @Test
    void testCountingSort() {
        CountingSort.prepare(TEST_ARRAY_VALUES_RANGE);
        tester(CountingSort::countingSort,
                "Counting Sort");
    }

    @Test
    void testQuickSort() {
        trivialTester(FastSorts::quickSort);
        tester(FastSorts::quickSort, "Quick Sort");
    }

    @Test
    void testQuickSort2() {
        trivialTester(FastSorts::quickSort2);
        tester(FastSorts::quickSort2,
                "\nQuick Sort 2");
    }

    @Test
    void testMergeSort() {
        trivialTester(FastSorts::mergeSort);
        tester(FastSorts::mergeSort,
                "\nMerge Sort");
    }

    @Test
    void testHeapSort() {
        trivialTester(FastSorts::heapSort);
        tester(FastSorts::heapSort, "Heap Sort");
    }

    @Test
    void testHeapSort2() {
        trivialTester(FastSortsExperimental::heapSortL);
        tester(FastSortsExperimental::heapSortL,
                "Heap Sort on Lambdas");
    }

    @Test
    void testIterativeMergeSort() {
        trivialTester(IterativeFastSorts::mergeSortIterative);
        tester(IterativeFastSorts::mergeSortIterative,
                "Iterative Merge Sort");
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