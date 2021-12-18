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
    private static final int ARRAY_LENGTH = 1000;
    private static final int ARRAY_VALUES_RANGE = 100;
    private static List<Long> A;
    private static List<Long> orderedA;
    private static long init, end;

    static void printArray(@NotNull List<?> array) {

        array.forEach(n -> System.out.print(n + " "));
    }

    @Test
    void mergeSortTest() {
        Util.createRandomArray();
        init = System.nanoTime();
        A = SortsGenerics.mergeSort(A);
        end = System.nanoTime();
        System.out.println("\nGenerics Array Merge Sort\n" +
                "Time elapsed: " + ((end - init) / 1000) + " mks");
        Assertions.assertIterableEquals(A, orderedA);
        printArray(A);
    }

    @Test
    void mergeSortIterativeTest() {
        Util.createRandomArray();
        init = System.nanoTime();
        SortsGenerics.mergeSortIterative(A);
        end = System.nanoTime();
        System.out.println("\nGenerics Array Iterative Merge Sort\n" +
                "Time elapsed: " + ((end - init) / 1000) + " mks");
        Assertions.assertIterableEquals(A, orderedA);
        printArray(A);
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