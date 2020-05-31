package com.github.nwillc.datastructures

// Implementation of Min Heap
class MinHeap : Heap {
    private var heapSize: Int = 0
    private val array = mutableListOf(Int.MIN_VALUE)

    // Function to remove and return the minimum element from the heap
    override fun pop(): Int {
        require(size() > 0) { "Heap is empty" }

        val popped = peek()
        array[FRONT] = array[heapSize--]
        minHeapify(FRONT)
        return popped
    }

    override fun peek() = if (heapSize != 0) array[1] else error("Heap is empty")
    override fun size() = heapSize

    // Function to insert a node into the heap
    override operator fun plusAssign(value: Int) {
        if (array.size > heapSize + 1)
            array[++heapSize] = value
        else {
            array.add(value)
            heapSize++
        }
        var current = heapSize
        var parent = parent(current)
        while (parent != 0 && array[current] < array[parent]) {
            swap(current, parent)
            current = parent.also { parent = parent(parent)}
        }
    }
    
    private fun minHeapify(i: Int) {
        val left = left(i)
        val right = right(i)

        var smallest = when {
            left <= heapSize && array[i] < array[left] -> i
            left <= heapSize -> left
            else -> i
        }

        if (right <= heapSize && array[smallest] > array[right])
            smallest = right

        if (smallest != i) {
            swap(smallest, i)
            minHeapify(smallest)
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
