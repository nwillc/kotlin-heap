package com.github.nwillc.xml

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class NodeTest {
    @Test
    fun `should handle simple toString`() {
        val body = "the quick brown fox"
        val tag = "P"
        val node = TagNode(tag, listOf(BodyNode(body)))
        assertThat(node.toString()).isEqualTo("<$tag>$body</$tag>")
    }
}
