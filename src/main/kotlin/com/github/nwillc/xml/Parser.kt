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
        val nodes = mutableListOf<Node>()
        while (iterator.hasNext()) {
            val token = iterator.next()

            if (token is TagToken) {
                if (token.tokenType == TokenType.TAG_END) {
                    if (token.value == tag) {
                        return TagNode(tag, nodes)
                    } else {
                        throw IllegalArgumentException("Miss-matched begin/end $tag/${token.value}")
                    }
                } else {
                    nodes += inTag(token.value, iterator)
                }
            } else {
                nodes += BodyNode((token as BodyToken).value)
            }
        }
        throw IllegalArgumentException("Hanging open tag <$tag>")
    }
}
