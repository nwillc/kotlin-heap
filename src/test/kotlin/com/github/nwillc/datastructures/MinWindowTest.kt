package com.github.nwillc.datastructures

import com.github.nwillc.datastructures.HeapTest.Features.FROM_RANDOM
import com.github.nwillc.datastructures.HeapTest.Features.RANDOM
import com.github.nwillc.datastructures.HeapTest.Features.REPETITIONS
import com.github.nwillc.datastructures.HeapTest.Features.UNTIL_RANDOM
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MinWindowTest {
    @Test
    fun `should accept values`() {
        val window = MinWindow(5)

        window += 10
        window += 9
        window += 2
        window += 8
        window += 5

        assertThat(window.toList()).containsExactly(2, 5, 8, 9, 10)
    }

    @Test
    fun `should ignore a value over range`() {
        val window = MinWindow(5)

        window += 10
        window += 9
        window += 2
        window += 8
        window += 5
        window += 20

        assertThat(window.toList()).containsExactly(2, 5, 8, 9, 10)
    }

    @Test
    fun `should add a value at bottom of range`() {
        val window = MinWindow(5)

        window += 10
        window += 9
        window += 2
        window += 8
        window += 5
        window += 1

        assertThat(window.toList()).containsExactly(1, 2, 5, 8, 9)
    }

    @Test
    fun `should add a value middle of range`() {
        val window = MinWindow(5)

        window += 10
        window += 9
        window += 2
        window += 8
        window += 5
        window += 3

        assertThat(window.toList()).containsExactly(2, 3, 5, 8, 9)
    }

    @Test
    fun `should handle all sorts of sample lists in random window sizes`() {
        repeat(REPETITIONS) {
            val sampleSize = RANDOM.nextInt(1, REPETITIONS * 2)
            val windowSize = RANDOM.nextInt(1, REPETITIONS * 2)
            val window = MinWindow(windowSize)
            val values = mutableListOf<Int>()

            repeat(sampleSize) {
                values.add(RANDOM.nextInt(FROM_RANDOM, UNTIL_RANDOM))
            }

            values.forEach { window += it }
            assertThat(window.toList()).containsExactlyElementsOf(values.sorted().take(windowSize))
        }
    }
}
