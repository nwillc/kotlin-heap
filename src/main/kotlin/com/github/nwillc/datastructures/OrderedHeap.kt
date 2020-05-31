package com.github.nwillc.datastructures

class OrderedHeap private constructor(
    size: Int,
    seed: Int,
    val compare: (Int, Int) -> Boolean
) {
    private var heapSize = 0
    private val array = IntArray(size + 1).also { it[0] = seed }

    companion object {
        private const val FRONT = 1

        fun minHeap(size: Int = 10): OrderedHeap = OrderedHeap(size, Int.MIN_VALUE) { a, b -> a < b }
        fun maxHeap(size: Int = 10): OrderedHeap = OrderedHeap(size, Int.MAX_VALUE) { a, b -> a > b }
    }

    fun pop(): Int {
        require(size() > 0) { "Heap is empty" }

        val popped = peek()
        array[FRONT] = array[heapSize--]
        orderedHeapify(FRONT)
        return popped
    }

    fun peek() = if (heapSize != 0) array[1] else error("Heap is empty")

    fun size() = heapSize

    operator fun plusAssign(value: Int) {
        if (array.size > heapSize + 1)
            array[++heapSize] = value
        else {
            error("At max size")
        }
        var current = heapSize
        var parent = parent(current)
        while (parent != 0 && compare(array[current], array[parent])) {
            swap(parent, current)
            current = parent.also { parent = parent(parent) }
        }
    }

    private fun parent(i: Int) = i shr 1
    private fun left(i: Int) = i shl 1
    private fun right(i: Int) = left(i) + 1

    private fun swap(i: Int, j: Int) {
        array[i] = array[j].also { array[j] = array[i] }
    }

    private tailrec fun orderedHeapify(i: Int) {
        val left = left(i)
        val right = right(i)

        var ordered = when {
            left <= heapSize && compare(array[i], array[left]) -> i
            left <= heapSize -> left
            else -> i
        }

        if (right <= heapSize && !compare(array[ordered], array[right]))
            ordered = right

        if (ordered != i) {
            swap(ordered, i)
            orderedHeapify(ordered)
        }
    }

    override fun toString(): String {
        return array
            .drop(1)
            .joinToString(", ", "[", "]")
    }
}
