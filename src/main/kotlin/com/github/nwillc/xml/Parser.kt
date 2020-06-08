package com.github.nwillc.xml

object Parser {
    fun parse(iterable: Iterable<Token>): Node {
        val iterator = iterable.iterator()
        if (iterator.hasNext()) {
            val token = iterator.next()
            require(token is TagToken && token.tokenType == TokenType.TAG_BEGIN) { "Expect Begin" }
            return inTag(token.value, iterator)
        }
        throw IllegalStateException("Unknown state")
    }

    private fun inTag(tag: String, iterator: Iterator<Token>): Node {
        require(iterator.hasNext()) { "Hanging open tag <$tag>" }
        val token = iterator.next()

        return if (token is TagToken) {
            if (token.tokenType == TokenType.TAG_END) {
                if (token.value == tag) {
                    TagNode(tag, emptyList())
                } else {
                    throw IllegalArgumentException("Miss-matched begin/end $tag/${token.value}")
                }
            } else {
                TagNode(tag, listOf(inTag(token.value, iterator)))
            }
        } else {
            TagNode(tag, listOf(BodyNode((token as BodyToken).value)))
        }
    }
}
