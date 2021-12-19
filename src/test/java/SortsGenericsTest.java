import com.example.java_array_sorts.ComparableClass;
import com.example.java_array_sorts.GenericsArraySorter;
import com.example.java_array_sorts.GenericsArraySorterReturn;
import com.example.java_array_sorts.SortsGenerics;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class SortsGenericsTest {
    private static final int ARRAY_LENGTH = 400;
    private static final int ARRAY_VALUES_RANGE = 100;
    private static final List<Integer> orderedIntegerList = new ArrayList<>();
    private static final List<ComparableClass> comparableClassList
            = new ArrayList<>();
    private static final List<ComparableClass> orderedComparableClassList
            = new ArrayList<>();
    private static List<Integer> integerList = new ArrayList<>();
    private static long init, end;

    private static void printArray(@NotNull List<?> array) {

        array.forEach(n -> System.out.print(n.toString() + " "));
    }

    private static void trivialTester(
            @NotNull GenericsArraySorter genericsArraySorter) {

        var arrayList1 = new ArrayList<Integer>();
        var arrayList2 = new ArrayList<Integer>();
        genericsArraySorter.sort(arrayList1);
        Assertions.assertIterableEquals(arrayList1, arrayList2);

        arrayList1.addAll(Arrays.asList(10, 20, 30, 40));
        arrayList2.addAll(arrayList1);
        genericsArraySorter.sort(arrayList1);
        Assertions.assertIterableEquals(arrayList1, arrayList2);

    }

    private static void tester(
            @NotNull GenericsArraySorter genericsArraySorter, String name) {
        Util.createRandomArrayList();
        init = System.nanoTime();
        genericsArraySorter.sort(integerList);
        end = System.nanoTime();
        System.out.println("\n\n" + name + ": IntegersList\n" +
                "Time elapsed: " + ((end - init) / 1000) + " mks");
        Assertions.assertIterableEquals(integerList, orderedIntegerList);
        printArray(integerList);
    }

    private static void testerReturn(
            @NotNull GenericsArraySorterReturn genArrSorterR, String name) {
        Util.createRandomArrayList();
        init = System.nanoTime();
        integerList = genArrSorterR.sort(integerList);
        end = System.nanoTime();
        System.out.println("\n\n" + name + ": IntegersList\n" +
                "Time elapsed: " + ((end - init) / 1000) + " mks");
        Assertions.assertIterableEquals(integerList, orderedIntegerList);
        printArray(integerList);
    }

    private static void testerObjectArray(
            @NotNull GenericsArraySorter genericsArraySorter, String name) {
        Util.createComparableObjectsArrayList();
        init = System.nanoTime();
        genericsArraySorter.sort(comparableClassList);
        end = System.nanoTime();
        System.out.println("\n\n" + name + ": CompObjectList\n" +
                "Time elapsed: " + ((end - init) / 1000) + " mks");
        Assertions.assertIterableEquals(comparableClassList,
                orderedComparableClassList);
        printArray(comparableClassList);
    }

    @Test
    void mergeSortTest() {
        testerReturn(SortsGenerics::mergeSort,
                "Generics Array Merge Sort");
    }

    @Test
    void mergeSort2Test() {
        trivialTester(SortsGenerics::mergeSort2);
        tester(SortsGenerics::mergeSort2,
                "Merge Sort for Generics with conversion to Object array");
        testerObjectArray(SortsGenerics::mergeSort2,
                "Merge Sort for Generics with conversion to Object array");
    }

    @Test
    void mergeSortIterativeTest() {
        trivialTester(SortsGenerics::mergeSortIterative);
        tester(SortsGenerics::mergeSortIterative,
                "Generics Array Iterative Merge Sort");
        testerObjectArray(SortsGenerics::mergeSortIterative,
                "Generics Array Iterative Merge Sort");
    }

    @Test
    void heapSortTest() {
        trivialTester(SortsGenerics::heapSort);
        tester(SortsGenerics::heapSort,
                "Generics Array Heap Sort");
        testerObjectArray(SortsGenerics::heapSort,
                "Generics Array Heap Sort");
    }

    private static class Util {
        static void createRandomArrayList() {
            integerList.clear();
            orderedIntegerList.clear();
            for (int i = 0; i < ARRAY_LENGTH; ++i) {
                int val = (int) (Math.random() * ARRAY_VALUES_RANGE);
                integerList.add(val);
                orderedIntegerList.add(val);
            }
            orderedIntegerList.sort(Comparator.naturalOrder());
        }


        static void createComparableObjectsArrayList() {
            comparableClassList.clear();
            orderedComparableClassList.clear();
            for (int i = 0; i < ARRAY_LENGTH; ++i) {
                var obj = new ComparableClass(
                        (int) (Math.random() * ARRAY_VALUES_RANGE));
                comparableClassList.add(obj);
                orderedComparableClassList.add(obj);
            }
            orderedComparableClassList.sort(Comparator.naturalOrder());
        }
    }
}