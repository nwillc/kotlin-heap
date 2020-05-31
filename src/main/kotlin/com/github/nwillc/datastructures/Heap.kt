package com.github.nwillc.datastructures

interface Heap {
    fun pop(): Int
    fun peek(): Int
    fun size(): Int
    operator fun plusAssign(value: Int)

    fun parent(i: Int) = i shr 1
    fun left(i: Int) = i shl 1
    fun right(i: Int) = left(i) + 1
}
