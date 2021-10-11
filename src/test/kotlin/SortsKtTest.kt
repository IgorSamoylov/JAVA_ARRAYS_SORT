import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.random.Random

internal class SortsKtTest {
    private var A: Array<Int> = Array(450) { Random.nextInt(0, 100) }
    private var sortedA: Array<Int> = A.sortedArray()
    private var init: Long = 0L
    private var end: Long = 0L

    @Test
    fun mergeSort() {
        init = System.nanoTime();
        sortsKt.mergeSort(A)
        end = System.nanoTime();
        println("Time elapsed: ${ (end - init) / 1000 }  mks")
        Assertions.assertArrayEquals(A, sortedA)
        printArray()
    }


    private fun printArray() {
        A.forEach { n -> print("$n ") }
    }
}