package com.github.nwillc.datastructures

import java.lang.Math.max


class MinWindow(val size: Int = 10) {
    var winMin = Int.MAX_VALUE
    var winMax = Int.MIN_VALUE
    val members = HashSet<Int>()

    fun add(value: Int) {
        if (members.contains(value))
            return

        println("--> $value")
        if (value < winMin) {
            winMin = value
            if (members.isEmpty()) {
                winMax = value
            }
            members.add(winMin)
            if (members.count() > size) {
                val prior = winMax
                members.remove(prior)
                winMax = members.max() ?: prior
            }
        }
        if (members.count() < size) {
            winMax = max(winMax,value)
            members.add(value)
        } else if (value < winMax) {
            val priorMax = winMax
            members.add(value)
            if (members.count() > size) {
                members.remove(priorMax)
                winMax = members.max() ?: value
            }
        }
        println(this)
    }

    override fun toString(): String {
        return "MinWindow(size=$size, winMin=$winMin, winMax=$winMax, members=$members)"
    }
}
