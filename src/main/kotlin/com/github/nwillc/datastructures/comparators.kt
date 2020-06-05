package com.github.nwillc.datastructures

internal class MinHeapComparator<T : Comparable<T>> : Comparator<T> {
    override fun compare(o1: T, o2: T): Int = o1.compareTo(o2)
}

internal class MaxHeapComparator<T : Comparable<T>> : Comparator<T> {
    override fun compare(o1: T, o2: T): Int = o2.compareTo(o1)
}
