package com.example.java_array_sorts;

public class FastSortsExperimental {
    // Lambda that swaps items by index x and y in the gotten array.
    private static final Swapper swapper = (array, x, y) -> {
        int tmp = array[x];
        array[x] = array[y];
        array[y] = tmp;
    };

    /*
     * Implementation of HeapSort for an integer array
     * that used lambda recursive calls without
     * extern methods calls.
     * */
    public static void heapSortL(final int[] arr) {

        HeapMakerRecursive heapMakerRec
                = (heapMaker, firstIndex, length) -> {
            int currentLargest = firstIndex;
            int leftChild = 2 * firstIndex + 1;
            int rightChild = 2 * firstIndex + 2;

            if (leftChild < length && arr[leftChild] > arr[currentLargest])
                currentLargest = leftChild;
            if (rightChild < length && arr[rightChild] > arr[currentLargest])
                currentLargest = rightChild;

            if (currentLargest != firstIndex) {
                swapper.swap(arr, currentLargest, firstIndex);
                // Recursive inner call that
                // make heap in a remaining array range
                // right to left with this new value
                heapMaker.heapMake(currentLargest, length);
            }
        };
        // Making basic heap, starting at the half or array
        // and moving right to left
        for (int i = arr.length / 2 - 1; i > -1; --i) {
            heapMakerRec.heapMake(i, arr.length);
        }
        // Receiving top items from [0] index and swap it one-by-one
        // to the end of array, than create a new more shortest heap
        for (int i = arr.length - 1; i > 0; --i) {
            swapper.swap(arr, 0, i);
            heapMakerRec.heapMake(0, i);
        }
    }

    @FunctionalInterface
    interface HeapMaker {
        void heapMake(int firstIndex, int length);
    }

    @FunctionalInterface
    interface HeapMakerRecursive extends HeapMaker {
        void heapMake(HeapMaker hm, int firstIndex, int length);

        default void heapMake(int firstIndex, int length) {
            heapMake(this, firstIndex, length);
        }
    }

    @FunctionalInterface
    interface Swapper {
        void swap(int[] arr, int x, int y);
    }
}
