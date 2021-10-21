import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

class SortsGenericsTest {
    private static final int ARRAY_LENGTH = 2000;
    private static final int ARRAY_VALUES_RANGE = 100;
    private static List<Long> A;
    private static List<Long> orderedA;
    private static long init, end;

    static void printArray(List<?> array) {
        array.forEach(n -> System.out.print(n + " "));
    }

    @Test
    void mergeSort() {
        Util.createRandomArray();
        init = System.nanoTime();
        A = SortsGenerics.mergeSort(A);
        end = System.nanoTime();
        System.out.println("Time elapsed: " + ((end - init) / 1000) + " mks");
        Assertions.assertIterableEquals(A, orderedA);
        printArray(A);
    }

    static class Util {
        static void createRandomArray() {
            A = LongStream.generate(() -> (long) (Math.random() * ARRAY_VALUES_RANGE))
                    .limit(ARRAY_LENGTH).boxed().collect(Collectors.toList());
            orderedA = new ArrayList<>(A);
            orderedA.sort(Comparator.naturalOrder());
        }
    }
}