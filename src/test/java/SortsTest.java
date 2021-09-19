import org.junit.jupiter.api.*;

import java.util.Arrays;


class SortsTest {

    private static int[] A;
    private static int[] sortedA;

    private final int TEST_ARRAY_LENGTH = 150;
    private final int TEST_ARRAY_VALUES_RANGE = 100;

    @BeforeEach
    void generateArray() {
        A = java.util.stream.IntStream
                .generate(() -> (int) (Math.random() * TEST_ARRAY_VALUES_RANGE))
                .limit(TEST_ARRAY_LENGTH)
                .toArray();

        sortedA = Arrays.copyOf(A, A.length);
        Arrays.sort(sortedA);
    }


    interface SortFunction {
        void sort(int[] array);
    }

    static void tester(SortFunction sortFunction) {
        long init = System.nanoTime();
        sortFunction.sort(A);
        long time = System.nanoTime() - init;
        System.out.println("Time elapsed: " + time / 1000 + "ms");
        Assertions.assertArrayEquals(A, sortedA);
    }

    @Test
    void testInsertionSort() {
        System.out.println("Insertion Sort");
        tester(Sorts::insertionSort);
    }

    @Test
    void testSelectionSort() {
        System.out.println("Selection Sort");
        tester(Sorts::selectionSort);
    }

    @Test
    void testBubbleSort() {
        System.out.println("Bubble Sort");
        tester(Sorts::bubbleSort);
    }

    @Test
    void testCountingSort() {
        System.out.println("Counting Sort");
        Sorts.CountingSort2.prepare(TEST_ARRAY_VALUES_RANGE);
        tester(Sorts.CountingSort2::countingSort);
    }


    @AfterEach
    void printResult() {
        java.util.Arrays.stream(A).forEach(n -> System.out.print(n + " "));
        System.out.println();
    }
}