package com.github.nwillc.datastructures

import java.util.PriorityQueue

fun <T> PriorityQueue<T>.pop(): T = this.poll()
fun <T> PriorityQueue<T>.size(): Int = this.size

