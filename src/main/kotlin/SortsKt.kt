package sortsKt

fun <T : Comparable<T>> mergeSortT(A: Array<T>): Array<T> {

    if (A.size == 2) {
        if (A[0] > A[1]) {
            A[0] = A[1].also { A[1] = A[0] }
        }
        return A
    } else if (A.size < 2) return A

    val halfSize = A.size / 2
    val leftArray = mergeSortT(A.sliceArray(0 until halfSize))
    val rightArray = mergeSortT(A.sliceArray(halfSize until A.size))

    var leftIter = 0
    var rightIter = 0
    var resultIter = 0
    while (leftIter < leftArray.size && rightIter < rightArray.size) {

        if (leftArray[leftIter] <= rightArray[rightIter])
            A[resultIter++] = leftArray[leftIter++]
        else A[resultIter++] = rightArray[rightIter++]
    }

    while (leftIter < leftArray.size) A[resultIter++] = leftArray[leftIter++]

    while (rightIter < rightArray.size) A[resultIter++] = rightArray[rightIter++]

    return A
}


fun mergeSortInt(A: Array<Int>): Array<Int> {

    if (A.size == 2) {
        if (A[0] > A[1]) {
            A[0] = A[1].also { A[1] = A[0] }
        }
        return A
    } else if (A.size < 2) return A

    val halfSize = A.size / 2
    val leftArray = mergeSortInt(A.sliceArray(0 until halfSize))
    val rightArray = mergeSortInt(A.sliceArray(halfSize until A.size))

    var leftIter = 0
    var rightIter = 0
    var resultIter = 0
    while (leftIter < leftArray.size && rightIter < rightArray.size) {

        if (leftArray[leftIter] <= rightArray[rightIter])
            A[resultIter++] = leftArray[leftIter++]
        else A[resultIter++] = rightArray[rightIter++]
    }

    while (leftIter < leftArray.size) A[resultIter++] = leftArray[leftIter++]

    while (rightIter < rightArray.size) A[resultIter++] = rightArray[rightIter++]

    return A
}

// Iterative version of O(N)=N*log(N) MergeSort Algorithm
// for Generics Array that using M(2*N) of memory
fun <T :Comparable<T>> mergeSortItT(A: Array<T>) {
    val buffer = A.copyOf()
    var m = 1
    while (m < A.size) {
        // Step / 2 magnifier
        var bufIter = 0
        // m = 1  i = 0, 2, 4...
        // m = 2  i = 0, 4, 8...
        // m = 4  i = 0, 8, 16...
        var i = 0
        while (i < A.size) {
            // i means the first item of each processing group
            val middle = i + m // = 1, 3, 5... = 2, 6, 10... = 4, 12, 20...
            var leftIter = i
            var rightIter = middle
            while (leftIter < middle && rightIter < middle + m && rightIter < A.size) { // Merging
                if (A[leftIter] < A[rightIter]) buffer[bufIter++] = A[leftIter++] else buffer[bufIter++] =
                    A[rightIter++]
            }
            while (leftIter < middle && leftIter < A.size) buffer[bufIter++] =
                A[leftIter++] // Appending left or right tails
            while (rightIter < middle + m && rightIter < A.size) buffer[bufIter++] = A[rightIter++]
            i += 2 * m
        }
        System.arraycopy(buffer, 0, A, 0, A.size) // Drop buffer array to the source array on an each traversal
        m *= 2
    }
}


// Iterative version of MergeSort Algorithm for IntArray that using M(2 * n) of memory
fun mergeSortItInt(A: IntArray) {
    val buffer = IntArray(A.size)
    var m = 1
    while (m < A.size) {
        // Step / 2 magnifier
        var bufIter = 0
        // m = 1  i = 0, 2, 4...
        // m = 2  i = 0, 4, 8...
        // m = 4  i = 0, 8, 16...
        var i = 0
        while (i < A.size) {
            // i means the first item of each processing group
            val middle = i + m // = 1, 3, 5... = 2, 6, 10... = 4, 12, 20...
            var leftIter = i
            var rightIter = middle
            while (leftIter < middle && rightIter < middle + m && rightIter < A.size) { // Merging
                if (A[leftIter] < A[rightIter]) buffer[bufIter++] = A[leftIter++] else buffer[bufIter++] =
                    A[rightIter++]
            }
            while (leftIter < middle && leftIter < A.size) buffer[bufIter++] =
                A[leftIter++] // Appending left or right tails
            while (rightIter < middle + m && rightIter < A.size) buffer[bufIter++] = A[rightIter++]
            i += 2 * m
        }
        System.arraycopy(buffer, 0, A, 0, A.size) // Drop buffer array to the source array on an each traversal
        m *= 2
    }
}





