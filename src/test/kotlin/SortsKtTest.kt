import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.random.Random

const val VALUES_RANGE = 100
const val ARRAY_SIZE = 450

internal class SortsKtTest {
    private val intsArr: Array<Int> = Array(ARRAY_SIZE) { Random.nextInt(0, VALUES_RANGE) }
    private var sortedA: Array<Int> = intsArr.sortedArray()

    @Test
    fun mergeSortT() {
        val testArray = intsArr.copyOf()
        val init = System.nanoTime()
        sortsKt.mergeSortT(testArray)
        val end = System.nanoTime()
        println("\nMerge Sort Recursive T \n Time elapsed: ${ (end - init) / 1000 }  mks")
        printArray(testArray)
        Assertions.assertArrayEquals(testArray, sortedA)
    }

    @Test
    fun mergeSortInt() {
        val testArray = intsArr.copyOf()
        val init = System.nanoTime()
        sortsKt.mergeSortInt(testArray)
        val end = System.nanoTime()
        println("\nMerge Sort Recursive For Ints \n Time elapsed: ${ (end - init) / 1000 }  mks")
        printArray(testArray)
        Assertions.assertArrayEquals(testArray, sortedA)
    }

    @Test
    fun mergeSortItInt() {
        val intArray = intsArr.toIntArray()
        val sortedIntArray = intArray.copyOf().sortedArray()
        val init = System.nanoTime()
        sortsKt.mergeSortItInt(intArray)
        val end = System.nanoTime()
        println("\nMerge Sort Iterative \n Time elapsed: ${ (end - init) / 1000 }  mks")
        intArray.forEach { n -> print("$n ")}
        Assertions.assertArrayEquals(intArray, sortedIntArray)
    }

    @Test
    fun mergeSortItT() {
        val testArray = intsArr.copyOf()
        val init = System.nanoTime()
        sortsKt.mergeSortItT(testArray)
        val end = System.nanoTime()
        println("\nMerge Sort Iterative T \n Time elapsed: ${ (end - init) / 1000 }  mks")
        printArray(testArray)
        Assertions.assertArrayEquals(testArray, sortedA)
    }


    private fun <T> printArray(A: Array<T>) {
        A.forEach { n -> print("$n ") }
    }
}