import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SortsGenericsTest {
    private static List<Long> A;
    private static List<Long> orderedA;
    private static final int ARRAY_LENGTH = 400;
    private static final int ARRAY_VALUES_RANGE = 100;


    @Test
    void mergeSort() {
        Util.createRandomArray();
        A = SortsGenerics.mergeSort(A);
        Assertions.assertIterableEquals(A, orderedA);
        printArray(A);
    }


    static void printArray(List<?> array) {
        array.forEach(n -> {System.out.print(n + " ");});
    }


    static class Util {
        static void createRandomArray() {
            A = LongStream.generate(() -> (long)(Math.random() * ARRAY_VALUES_RANGE))
                    .limit(ARRAY_LENGTH).boxed().collect(Collectors.toList());
            orderedA = new ArrayList<>(A);
            orderedA.sort(Comparator.naturalOrder());
        }
    }
}