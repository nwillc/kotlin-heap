package com.github.nwillc.xml

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TokenTest {
    @Test
    fun `should create body token`() {
        val bodyValue = "hello world"
        val token = BodyToken(bodyValue)

        assertThat(token.value).isEqualTo(bodyValue)
    }

    @Test
    fun `should create html tag start`() {
        val tag = "HTML"
        val token = TagToken(tag)

        assertThat(token.value).isEqualTo(tag)
        assertThat(token.tokenType).isEqualTo(TokenType.TAG_BEGIN)
    }

    @Test
    fun `should create html tag end`() {
        val tag = "HTML"
        val token = TagToken(tag, TokenType.TAG_END)

        assertThat(token.value).isEqualTo(tag)
        assertThat(token.tokenType).isEqualTo(TokenType.TAG_END)
    }
}
