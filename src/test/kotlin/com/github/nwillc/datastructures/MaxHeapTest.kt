package com.github.nwillc.datastructures

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.Instant
import kotlin.math.max
import kotlin.random.Random

class MaxHeapTest {
    @Test
    fun `should pop values off in expected order`() {
        val size = 13
        val heap = MaxHeap(size)
        var goal = Int.MIN_VALUE
        val random = Random(Instant.now().epochSecond)
        val values = mutableListOf<Int>()

        repeat(size) {
            val value = random.nextInt(0, 100)
            goal = max(goal, value)
            values.add(value)
            heap += value
        }
        assertThat(heap.peek()).isEqualTo(goal)
        assertThat(heap.size()).isEqualTo(size)
        val popped = mutableListOf<Int>()
        while (heap.size() > 0) {
           popped.add(heap.pop())
        }
       assertThat(popped).containsExactlyElementsOf(values.sortedDescending())
    }
}
