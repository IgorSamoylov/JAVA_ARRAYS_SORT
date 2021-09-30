import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.random.Random

internal class SortsKtTest {
    private var A: Array<Int> = Array(400) { Random.nextInt(0, 100) }
    private var sortedA: Array<Int> = A.sortedArray()


    @Test
    fun mergeSort() {
        sortsKt.mergeSort(A)
        Assertions.assertArrayEquals(A, sortedA)
        printArray()
    }


    fun printArray() {
        A.forEach { n -> print("$n ") }
    }
}