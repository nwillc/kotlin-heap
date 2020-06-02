package com.github.nwillc.datastructures

import org.junit.jupiter.api.Test

class MinWindow2Test {
    @Test
    fun `should accept values`() {
        val window = MinWindow2(5)

        window += 10
        window += 9
        window += 2
        window += 8
        window += 5

        println(window)
    }

    @Test
    fun `should ignore a value over range`() {
        val window = MinWindow2(5)

        window += 10
        window += 9
        window += 2
        window += 8
        window += 5
        window += 20

        println(window)
    }

    @Test
    fun `should add a value at bottom of range`() {
        val window = MinWindow2(5)

        window += 10
        window += 9
        window += 2
        window += 8
        window += 5
        window += 1

        println(window)
    }

    @Test
    fun `should add a value middle of range`() {
        val window = MinWindow2(5)

        window += 10
        window += 9
        window += 2
        window += 8
        window += 5
        window += 3

        println(window)
    }

    @Test
    fun `should handle random values`() {
        val size = 20
        val window = MinWindow2(5)
        val values = mutableListOf<Int>()

        repeat(size) {
            val value = HeapTest.RANDOM.nextInt(HeapTest.FROM_RANDOM, HeapTest.UNTIL_RANDOM)
            window += value
            values.add(value)
        }

        // [13, 78, 43, 28, 84, 87, 72, 24, 64, 89, 44, 8, 28, 99, 3, 3, 51, 47, 85, 55]
        println(values)
        println(window)
        println(values.sorted())
    }
}
