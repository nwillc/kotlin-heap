package com.github.nwillc.datastructures

class MinWindow2(val size: Int = 10) {
    var minHeap = Heap.minHeap(size)
    var maxHeap = Heap.maxHeap(size)

    operator fun plusAssign(value: Int) {
        if (minHeap.size() < size) {
            minHeap += value
            maxHeap += value
        } else {
            if (value <= maxHeap.peek()) {
                val newMin = Heap.minHeap(size)
                val newMax = Heap.minHeap(size)

                newMin += value
                newMax += value
                repeat(size - 1) {
                    val popped = minHeap.pop()
                    newMin += popped
                    newMax += popped
                }
                minHeap = newMin
                maxHeap = newMax
            }
        }
    }

    override fun toString(): String {
        return "MinWindow2(size=$size, members=${minHeap.members()}, maxHeap=$maxHeap)"
    }
}
