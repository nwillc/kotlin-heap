package com.github.nwillc.datastructures

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MinWindowTest {
    @Test
    fun `should accept values`() {
        val window = MinWindow(5)

        window.add(10)
        window.add(9)
        window.add(2)
        window.add(8)
        window.add(3)
        window.add(1)
        window.add(11)
        window.add(0)

        assertThat(window.winMin).isEqualTo(0)
    }
}
