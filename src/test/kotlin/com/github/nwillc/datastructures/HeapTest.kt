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

        repeat(SIZE) { heap.add(RANDOM.nextInt(FROM_RANDOM, UNTIL_RANDOM)) }

        assertThatThrownBy { heap.add(1) }
            .isInstanceOf(IllegalStateException::class.java)
    }

    @Test
    fun `should pop and peek`() {
        val heap = Heap.minHeap(SIZE)
        heap.add(42)
        heap.add(5)
        heap.add(7)

        assertThat(heap.peek()).isEqualTo(5)
        assertThat(heap.pop()).isEqualTo(5)
        assertThat(heap.peek()).isEqualTo(7)
        assertThat(heap.pop()).isEqualTo(7)
        assertThat(heap.peek()).isEqualTo(42)
    }

    @Test
    fun `should pop and peek PQHeap`() {
        val heap = PQHeap.minHeap<Int>()
        heap.add(42)
        heap.add(5)
        heap.add(7)

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
            heap.add(value)
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
    fun `should correctly order min PriorityQueue`() {
        val heap = PQHeap.minHeap<Int>()
        var goal = Int.MAX_VALUE
        val values = mutableListOf<Int>()

        repeat(SIZE) {
            val value = RANDOM.nextInt(FROM_RANDOM, UNTIL_RANDOM)
            goal = min(goal, value)
            values.add(value)
            heap.add(value)
        }
        assertThat(heap.peek()).isEqualTo(goal)
        assertThat(heap.size).isEqualTo(SIZE)
        val popped = mutableListOf<Int>()
        while (heap.size > 0) {
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
            heap.add(value)
        }
        assertThat(heap.peek()).isEqualTo(goal)
        assertThat(heap.size()).isEqualTo(SIZE)
        val popped = mutableListOf<Int>()
        while (heap.size() > 0) {
            popped.add(heap.pop())
        }
        assertThat(popped).containsExactlyElementsOf(values.sortedDescending())
    }

    @Test
    fun `should correctly order max PriorityQueue`() {
        val heap = PQHeap.maxHeap<Int>()
        var goal = Int.MIN_VALUE
        val values = mutableListOf<Int>()

        repeat(SIZE) {
            val value = RANDOM.nextInt(FROM_RANDOM, UNTIL_RANDOM)
            goal = max(goal, value)
            values.add(value)
            heap.add(value)
        }
        assertThat(heap.peek()).isEqualTo(goal)
        assertThat(heap.size).isEqualTo(SIZE)
        val popped = mutableListOf<Int>()
        while (heap.size > 0) {
            popped.add(heap.pop())
        }
        assertThat(popped).containsExactlyElementsOf(values.sortedDescending())
    }

//    @Test
//    fun `should be able to remove item from heap`() {
//        var failed = 0
//        val repetitions = REPETITIONS * 10
//        repeat(repetitions) { repetition ->
//            val size = RANDOM.nextInt(4, SIZE * 2)
//            val heap = Heap.minHeap(size)
//            val values = mutableListOf<Int>()
//
//            repeat(size) {
//                val value = RANDOM.nextInt(FROM_RANDOM, UNTIL_RANDOM)
//                values.add(value)
//                heap.add(value)
//            }
//
//            val target = values.random()
//            val indexOf = values.indexOf(target)
//            val before = heap.toList()
//            heap.remove(target)
//            val after = heap.toList()
//            val popped = mutableListOf<Int>()
//            while (heap.size() > 0) {
//                popped.add(heap.pop())
//            }
//            values.remove(target)
//            try {
//                assertThat(popped).containsExactlyElementsOf(values.sorted())
//                println("Passed $repetition: size=$size, removed=$target, index=$indexOf")
//            } catch (e: AssertionError) {
//                failed++
//                println("Failed $repetition: size=$size, removed=$target, index=$indexOf")
//                println("Values: ${values.sorted()}")
//                println("Popped: $popped")
//                println("Before: $before")
//                println("After:  $after")
//            }
//        }
//        assertThat(failed).describedAs("Failed $failed of $repetitions repetitions").isEqualTo(0)
//    }

    @Test
    fun `should be able to remove item from pqheap`() {
        var failed = 0
        val repetitions = REPETITIONS * 10
        repeat(repetitions) { repetition ->
            val size = RANDOM.nextInt(4, SIZE * 2)
            val heap = PQHeap.minHeap<Int>()
            val values = mutableListOf<Int>()

            repeat(size) {
                val value = RANDOM.nextInt(FROM_RANDOM, UNTIL_RANDOM)
                values.add(value)
                heap.add(value)
            }

            val target = values.random()
            val indexOf = values.indexOf(target)
            val before = heap.toList()
            heap.remove(target)
            val after = heap.toList()
            val popped = mutableListOf<Int>()
            while (heap.size > 0) {
                popped.add(heap.pop())
            }
            values.remove(target)
            try {
                assertThat(popped).containsExactlyElementsOf(values.sorted())
                println("Passed $repetition: size=$size, removed=$target, index=$indexOf")
            } catch (e: AssertionError) {
                failed++
                println("Failed $repetition: size=$size, removed=$target, index=$indexOf")
                println("Values: ${values.sorted()}")
                println("Popped: $popped")
                println("Before: $before")
                println("After:  $after")
            }
        }
        assertThat(failed).describedAs("Failed $failed of $repetitions repetitions").isEqualTo(0)
    }

    companion object Features {
        private const val SIZE = 13
        val RANDOM = Random(Instant.now().epochSecond)
        const val FROM_RANDOM = 0
        const val UNTIL_RANDOM = 100
        const val REPETITIONS = 50
    }
}

inline fun <reified T : Comparable<T>> arrayOf(size: Int, seed: T): Array<T> = Array(size) { _ -> seed }
