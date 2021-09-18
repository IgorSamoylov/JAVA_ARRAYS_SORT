import org.junit.jupiter.api.*;

import java.util.Arrays;


class SortsTest {

    private static int[] A;
    private static int[] sortedA;

    @BeforeEach
    void generateArray() {
        A = java.util.stream.IntStream
                .generate(() -> (int) (Math.random() * 100))
                .limit(10)
                .toArray();

        sortedA = Arrays.copyOf(A, A.length);
        Arrays.sort(sortedA);
    }


    interface SortFunction {
        void sort(int[] array);
    }

    static void tester(SortFunction sortFunction) {
        sortFunction.sort(A);
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
        tester(Sorts::countingSort);
    }


    @AfterEach
    void printResult() {
        java.util.Arrays.stream(A).forEach(n -> System.out.print(n + " "));
        System.out.println();
    }
}