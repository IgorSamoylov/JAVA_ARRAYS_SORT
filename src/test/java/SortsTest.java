import org.junit.jupiter.api.*;

import java.util.Arrays;


class SortsTest {

    private static int[] A;
    private static int[] sortedA;

    private static final int TEST_ARRAY_LENGTH = 450;
    private static final int TEST_ARRAY_VALUES_RANGE = 100;

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
        A = new int[] {1, 2, 3, 4};
        sortedA = new int[] {1, 2, 3, 4};
    }
    //@BeforeEach
    static void generateRandomArray() {
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
    interface SortFunctionReturn {
        int[] sort(int[] array);
    }

    static void tester(SortFunction sortFunction) {
        generateRandomArray();
        //long init = System.nanoTime();
        sortFunction.sort(A);
        //long time = System.nanoTime() - init;
        //System.out.println("Time elapsed: " + time / 1000 + "ms");
        Assertions.assertArrayEquals(A, sortedA);
    }

    static void trivialTester(SortFunction sortFunction) {
        generateVoidArray();
        sortFunction.sort(A);
        Assertions.assertArrayEquals(A, sortedA);

        generateUniformArray();
        sortFunction.sort(A);
        Assertions.assertArrayEquals(A, sortedA);

        generateSimpleArray();
        sortFunction.sort(A);
        Assertions.assertArrayEquals(A, sortedA);
    }

   /* static void testerReturn(SortFunctionReturn sortFunctionReturn) {
        generateRandomArray();
        //long init = System.nanoTime();
        A = sortFunctionReturn.sort(A);
       // long time = System.nanoTime() - init;
        //System.out.println("Time elapsed: " + time / 1000 + "ms");
        Assertions.assertArrayEquals(A, sortedA);
    }*/


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

   /* @Test
    void testSelectionSort3() {
        System.out.println("Selection Sort3");
        testerReturn(Sorts::selectionSort3);
    }*/

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
}