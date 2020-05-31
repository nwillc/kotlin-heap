package com.github.nwillc.datastructures

class MaxHeap : Heap {
    private val array = mutableListOf(0)
    private var heapSize = 0

    override fun pop(): Int {
        require(size() > 0) { "Heap is empty" }

        val popped = peek()
        array[FRONT] = array[heapSize--]
        maxHeapify(FRONT)
        return popped
    }

    override fun peek() = if (heapSize != 0) array[1] else error("Heap is empty")
    override fun size() = heapSize

    override operator fun plusAssign(value: Int) {
        if (array.size > heapSize + 1)
            array[++heapSize] = value
        else {
            array.add(value)
            heapSize++
        }
        var current = heapSize
        var parent = parent(current)
        while (parent != 0 && array[parent] < array[current]) {
            swap(parent, current)
            current = parent.also { parent = parent(parent) }
        }
    }

    private fun maxHeapify(i: Int) {
        val left = left(i)
        val right = right(i)

        var largest = when {
            left <= heapSize && array[i] > array[left] -> i
            left <= heapSize -> left
            else -> i
        }

        if (right <= heapSize && array[largest] < array[right])
            largest = right

        if (largest != i) {
            swap(largest, i)
            maxHeapify(largest)
        }
    }

    private fun swap(i: Int, j: Int) {
        array[i] = array[j].also { array[j] = array[i] }
    }

    override fun toString(): String {
        return array
            .drop(1)
            .joinToString(", ", "[", "]")
    }

    companion object {
        private const val FRONT = 1
    }
}
