import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.random.Random

internal class SortsKtTest {
    private val ARRAY_SIZE = 450
    private val VALUES_RANGE = 100
    private var A: Array<Int> = Array(ARRAY_SIZE) { Random.nextInt(0, VALUES_RANGE) }
    private var sortedA: Array<Int> = A.sortedArray()
    private var init: Long = 0L
    private var end: Long = 0L

    @Test
    fun mergeSortT() {
        val testArray = A.copyOf()
        init = System.nanoTime();
        sortsKt.mergeSortT(testArray)
        end = System.nanoTime();
        println("Merge Sort Recursive T \n Time elapsed: ${ (end - init) / 1000 }  mks")
        printArray(testArray)
        Assertions.assertArrayEquals(testArray, sortedA)
    }

    @Test
    fun mergeSortInt() {
        val testArray = A.copyOf()
        init = System.nanoTime();
        sortsKt.mergeSortInt(testArray)
        end = System.nanoTime();
        println("Merge Sort Recursive For Ints \n Time elapsed: ${ (end - init) / 1000 }  mks")
        printArray(testArray)
        Assertions.assertArrayEquals(testArray, sortedA)
    }

    @Test
    fun mergeSortItInt() {
        val intArray = A.toIntArray()
        val sortedIntArray = intArray.copyOf().sortedArray()
        init = System.nanoTime();
        sortsKt.mergeSortItInt(intArray)
        end = System.nanoTime();
        println("Merge Sort Iterative \n Time elapsed: ${ (end - init) / 1000 }  mks")
        intArray.forEach { n -> print("$n ")}
        Assertions.assertArrayEquals(intArray, sortedIntArray)
    }

    @Test
    fun mergeSortItT() {
        val testArray = A.copyOf()
        init = System.nanoTime();
        sortsKt.mergeSortItT(testArray)
        end = System.nanoTime();
        println("Merge Sort Iterative T \n Time elapsed: ${ (end - init) / 1000 }  mks")
        printArray(testArray)
        Assertions.assertArrayEquals(testArray, sortedA)
    }


    private fun <T> printArray(A: Array<T>) {
        A.forEach { n -> print("$n ") }
    }
}