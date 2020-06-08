package com.github.nwillc.xml

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ParserTest {
    @Test
    fun `should throw error if first token is a end`() {
        val tokens = listOf(TagToken("HTML", TokenType.TAG_END))
        assertThrows<IllegalArgumentException>("Expect Begin") { Parser.parse(tokens) }
    }

    @Test
    fun `should throw error if first token is a body`() {
        val tokens = listOf(BodyToken("Hello world"))
        assertThrows<IllegalArgumentException>("Expect Begin") { Parser.parse(tokens) }
    }

    @Test
    fun `should not end with open tag`() {
        val tag = "HTML"
        val tokens = listOf(TagToken(tag, TokenType.TAG_BEGIN))
        assertThrows<IllegalArgumentException>("Hanging open tag <$tag>") {
            Parser.parse(tokens)
        }
    }

    @Test
    fun `should match begin and end`() {
        val tag1 = "HTML"
        val tag2 = "P"
        val tokens = listOf(
            TagToken(tag1, TokenType.TAG_BEGIN),
            TagToken(tag2, TokenType.TAG_END)
        )
        assertThrows<IllegalArgumentException>("Miss-matched begin/end $tag1/$tag2") {
            Parser.parse(tokens)
        }
    }

    @Test
    fun `should handle a simple tag pair`() {
        val tag = "HTML"

        val tokens = listOf(
            TagToken(tag, TokenType.TAG_BEGIN),
            TagToken(tag, TokenType.TAG_END)
        )

        val node = Parser.parse(tokens)
        assertThat(node).isInstanceOf(TagNode::class.java)
        assertThat((node as TagNode).tag).isEqualTo(tag)
    }

    @Test
    fun `should handle a tag with body`() {
        val tag = "HTML"
        val body = "hello"

        val tokens = listOf(
            TagToken(tag, TokenType.TAG_BEGIN),
            BodyToken(body),
            TagToken(tag, TokenType.TAG_END)
        )

        val node = Parser.parse(tokens)
        assertThat(node).isInstanceOf(TagNode::class.java)
        with(node as TagNode) {
            assertThat(tag).isEqualTo(tag)
            assertThat(children).hasSize(1)
            val child = children[0]
            assertThat(child).isInstanceOf(BodyNode::class.java)
            assertThat((child as BodyNode).value).isEqualTo(body)
        }
    }

    @Test
    fun `should handle a tag in tag`() {
        val tag1 = "HTML"
        val tag2 = "P"

        val tokens = listOf(
            TagToken(tag1, TokenType.TAG_BEGIN),
            TagToken(tag2, TokenType.TAG_BEGIN),
            TagToken(tag2, TokenType.TAG_END),
            TagToken(tag1, TokenType.TAG_END)
        )

        val node = Parser.parse(tokens)
        assertThat(node).isInstanceOf(TagNode::class.java)
        with(node as TagNode) {
            assertThat(tag).isEqualTo(tag1)
            assertThat(children).hasSize(1)
            assertThat((children[0] as TagNode).tag).isEqualTo(tag2)
        }
    }

    @Test
    fun `should handle tag lists`() {
        val tag1 = "HTML"
        val tag2 = "P"

        val tokens = listOf(
            TagToken(tag1, TokenType.TAG_BEGIN),
            TagToken(tag2, TokenType.TAG_BEGIN),
            TagToken(tag2, TokenType.TAG_END),
            TagToken(tag2, TokenType.TAG_BEGIN),
            TagToken(tag2, TokenType.TAG_END),
            TagToken(tag1, TokenType.TAG_END)
        )

        val node = Parser.parse(tokens)
        assertThat(node).isInstanceOf(TagNode::class.java)
        with(node as TagNode) {
            assertThat(tag).isEqualTo(tag1)
            assertThat(children).hasSize(2)
        }
    }
}
