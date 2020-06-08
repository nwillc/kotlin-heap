package com.github.nwillc.xml

sealed class Node

data class TagNode (
    val tag: String,
    val children: List<Node>
) : Node()

data class BodyNode (
    val value: String
) : Node()
