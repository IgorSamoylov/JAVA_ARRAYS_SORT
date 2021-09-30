
package sortsKt

        fun <T : Comparable<T>> mergeSort(A: Array<T>): Array<T> {

            if (A.size == 2) {
                if(A[0] > A[1]) {
                    A[0] = A[1].also { A[1] = A[0] }
                }
                return A
            } else if (A.size < 2) return A

            val halfSize = A.size / 2
            val leftArray = mergeSort(A.sliceArray(0 until halfSize))
            val rightArray = mergeSort(A.sliceArray(halfSize until A.size))

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






