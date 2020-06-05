package com.github.nwillc.datastructures

import java.util.PriorityQueue

class PQHeap<T: Comparable<T>>(comparator: Comparator<T>) : HeapI<T> {
    private val priorityQueue: PriorityQueue<T> = PriorityQueue(comparator)

    companion object {
        fun <T: Comparable<T>> minHeap() = PQHeap(MinHeapComparator<T>())
        fun <T: Comparable<T>> maxHeap() = PQHeap(MaxHeapComparator<T>())
        private class MinHeapComparator<T: Comparable<T>>: Comparator<T> {
                override fun compare(o1: T, o2: T): Int = o1.compareTo(o2)
            }
        private class MaxHeapComparator<T: Comparable<T>>: Comparator<T> {
            override fun compare(o1: T, o2: T): Int = o2.compareTo(o1)
        }
    }

    override val size: Int
        get() = priorityQueue.size

    override fun add(value: T) {
        priorityQueue.add(value)
    }

    override fun pop(): T = priorityQueue.poll()

    override fun peek(): T = priorityQueue.peek()

    override fun toList(): List<T> = priorityQueue.toList()

    fun remove(value: T): Boolean = priorityQueue.remove(value)
}
