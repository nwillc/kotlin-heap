package com.github.nwillc.xml

sealed class Node

data class TagNode(
    val tag: String,
    val children: List<Node>
) : Node() {
    override fun toString(): String {
        val content = children.joinToString("") { it.toString() }
        return "<$tag>$content</$tag>"
    }
}

data class BodyNode(
    val value: String
) : Node() {
    override fun toString(): String = value
}
