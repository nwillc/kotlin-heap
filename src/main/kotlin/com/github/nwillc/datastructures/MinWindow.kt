package com.github.nwillc.datastructures

class MinWindow(private val windowSize: Int = 10) {
    var minHeap = Heap.minHeap(windowSize)
    var maxHeap = Heap.maxHeap(windowSize)

    fun size() = minHeap.size()
    fun min() = if (size() > 0) minHeap.peek() else Int.MIN_VALUE
    fun max() = if (size() > 0) maxHeap.peek() else Int.MAX_VALUE

    operator fun plusAssign(value: Int) {
        if (minHeap.size() < windowSize) {
            minHeap += value
            maxHeap += value
        } else {
            if (value <= max()) {
                val newMin = Heap.minHeap(windowSize)
                val newMax = Heap.maxHeap(windowSize)

                newMin += value
                newMax += value
                repeat(windowSize - 1) {
                    val popped = minHeap.pop()
                    newMin += popped
                    newMax += popped
                }
                minHeap = newMin
                maxHeap = newMax
            }
        }
    }

    fun toList() = minHeap.toList().sorted()

    @SuppressWarnings("MaxLineLength")
    override fun toString(): String {
        return "{limit=$windowSize, size=${size()}, min=${min()}, max=${max()}, members=${minHeap.toList()}, maxHeap=$maxHeap}"
    }
}
