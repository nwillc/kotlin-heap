package com.github.nwillc.datastructures

import java.util.PriorityQueue

class MinWindow2(private val windowSize: Int = 10) {
    private var minHeap = PQHeap.minHeap<Int>()
    private val maxHeap = PQHeap.maxHeap<Int>()

    fun size() = minHeap.size
    fun min() = if (size() > 0) minHeap.peek() else Int.MIN_VALUE
    fun max() = if (size() > 0) maxHeap.peek() else Int.MAX_VALUE

    operator fun plusAssign(value: Int) {
        if (minHeap.size < windowSize) {
            minHeap.add(value)
            maxHeap.add(value)
        } else if (value <= max()) {
            val popped = maxHeap.pop()
            maxHeap.add(value)
            minHeap.remove(popped)
            minHeap.add(value)
        }
    }

    fun toList() = minHeap.toList().sorted()

    override fun toString(): String {
        return "{limit=$windowSize, size=${size()}, min=${min()}, max=${max()}, members=${toList()}}"
    }
}


