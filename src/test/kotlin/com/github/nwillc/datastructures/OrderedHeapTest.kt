package com.github.nwillc.datastructures

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.Instant
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

class OrderedHeapTest {
    @Test
    fun `should correctly order min heap`() {
        val heap = OrderedHeap.minHeap(SIZE)
        var goal = Int.MAX_VALUE
        val values = mutableListOf<Int>()

        repeat(SIZE) {
            val value = RANDOM.nextInt(FROM_RANDOM, UNTIL_RANDOM)
            goal = min(goal, value)
            values.add(value)
            heap += value
        }
        assertThat(heap.peek()).isEqualTo(goal)
        assertThat(heap.size()).isEqualTo(SIZE)
        val popped = mutableListOf<Int>()
        while (heap.size() > 0) {
            popped.add(heap.pop())
        }
        assertThat(popped).containsExactlyElementsOf(values.sorted())
    }

    @Test
    fun `should correctly order max heap`() {
        val heap = OrderedHeap.maxHeap(SIZE)
        var goal = Int.MIN_VALUE
        val values = mutableListOf<Int>()

        repeat(SIZE) {
            val value = RANDOM.nextInt(FROM_RANDOM, UNTIL_RANDOM)
            goal = max(goal, value)
            values.add(value)
            heap += value
        }
        assertThat(heap.peek()).isEqualTo(goal)
        assertThat(heap.size()).isEqualTo(SIZE)
        val popped = mutableListOf<Int>()
        while (heap.size() > 0) {
            popped.add(heap.pop())
        }
        assertThat(popped).containsExactlyElementsOf(values.sortedDescending())
    }

    companion object Features {
        private const val SIZE = 13
        private val RANDOM = Random(Instant.now().epochSecond)
        private const val FROM_RANDOM = 0
        private const val UNTIL_RANDOM = 100
    }
}
