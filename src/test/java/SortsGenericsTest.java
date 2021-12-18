import com.example.java_array_sorts.GenericsArraySorter;
import com.example.java_array_sorts.GenericsArraySorterReturn;
import com.example.java_array_sorts.SortsGenerics;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

class SortsGenericsTest {
    private static final int ARRAY_LENGTH = 400;
    private static final int ARRAY_VALUES_RANGE = 100;
    private static List<Long> A;
    private static List<Long> orderedA;
    private static long init, end;

    private static void printArray(@NotNull List<?> array) {

        array.forEach(n -> System.out.print(n + " "));
    }

    private static void tester(
            @NotNull GenericsArraySorter genericsArraySorter, String name) {
        Util.createRandomArray();
        init = System.nanoTime();
        genericsArraySorter.sort(A);
        end = System.nanoTime();
        System.out.println("\n\n" + name + "\n" +
                "Time elapsed: " + ((end - init) / 1000) + " mks");
        Assertions.assertIterableEquals(A, orderedA);
        printArray(A);
    }

    private static void testerReturn(
            @NotNull GenericsArraySorterReturn gasr, String name) {
        Util.createRandomArray();
        init = System.nanoTime();
        A = gasr.sort(A);
        end = System.nanoTime();
        System.out.println("\n\n" + name + "\n" +
                "Time elapsed: " + ((end - init) / 1000) + " mks");
        Assertions.assertIterableEquals(A, orderedA);
        printArray(A);
    }

    @Test
    void mergeSortTest() {
        testerReturn(SortsGenerics::mergeSort,
                "Generics Array Merge Sort");
    }

    @Test
    void mergeSort2Test() {
        tester(SortsGenerics::mergeSort2,
                "Merge Sort for Generics with conversion to Object array");
    }

    @Test
    void mergeSortIterativeTest() {
        tester(SortsGenerics::mergeSortIterative,
                "Generics Array Iterative Merge Sort");
    }

    @Test
    void heapSortTest() {
        tester(SortsGenerics::heapSort,
                "Generics Array Heap Sort");
    }

    private static class Util {
        static void createRandomArray() {
            if (A != null) A.clear();
            A = LongStream.generate(() -> (long) (Math.random() * ARRAY_VALUES_RANGE))
                    .limit(ARRAY_LENGTH).boxed().collect(Collectors.toList());
            orderedA = new ArrayList<>(A);
            orderedA.sort(Comparator.naturalOrder());
        }
    }
}