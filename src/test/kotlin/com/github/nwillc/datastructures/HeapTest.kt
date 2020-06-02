package com.github.nwillc.datastructures

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import java.time.Instant
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

class HeapTest {

    @Test
    fun `should respect size bounds`() {
        val heap = Heap.minHeap(SIZE)

        assertThatThrownBy { heap.pop() }
            .isInstanceOf(IllegalStateException::class.java)

        repeat(SIZE) { heap += RANDOM.nextInt(FROM_RANDOM, UNTIL_RANDOM) }

        assertThatThrownBy { heap += 1 }
            .isInstanceOf(IllegalStateException::class.java)
    }

    @Test
    fun `should pop and peek`() {
        val heap = Heap.minHeap(SIZE)
        heap += 42
        heap += 5
        heap += 7

        assertThat(heap.peek()).isEqualTo(5)
        assertThat(heap.pop()).isEqualTo(5)
        assertThat(heap.peek()).isEqualTo(7)
        assertThat(heap.pop()).isEqualTo(7)
        assertThat(heap.peek()).isEqualTo(42)
    }

    @Test
    fun `should correctly order min heap`() {
        val heap = Heap.minHeap(SIZE)
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
        val heap = Heap.maxHeap(SIZE)
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
        val RANDOM = Random(Instant.now().epochSecond)
        const val FROM_RANDOM = 0
        const val UNTIL_RANDOM = 100
    }
}
