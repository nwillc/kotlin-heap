package com.github.nwillc.xml

sealed class Token(val tokenType: TokenType)

class TagToken(val value: String, tokenType: TokenType = TokenType.TAG_BEGIN) : Token(tokenType)
class BodyToken(val value: String): Token(TokenType.BODY)
