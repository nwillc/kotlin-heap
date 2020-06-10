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
            when (token.tokenType) {
                TokenType.BODY -> nodes += BodyNode((token as BodyToken).value)
                TokenType.TAG_BEGIN -> nodes += inTag((token as TagToken).value, iterator)
                TokenType.TAG_END -> {
                    with(token as TagToken) {
                        require(value == tag) { "Miss-matched begin/end $tag/$value" }
                        return TagNode(tag, nodes)
                    }
                }
            }
        }
        throw IllegalArgumentException("Hanging open tag <$tag>")
    }
}
