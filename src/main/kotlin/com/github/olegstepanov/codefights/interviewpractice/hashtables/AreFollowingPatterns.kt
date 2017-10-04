package com.github.olegstepanov.codefights.interviewpractice.hashtables

fun areFollowingPatterns(strings: MutableList<String>, patterns: MutableList<String>): Boolean {
    val map1 = HashMap<String, String>()
    val map2 = HashMap<String, String>()
    for ((first, second) in strings.zip(patterns)) {
        val value1 = map1[first]
        val value2 = map2[second]
        if (value1 != null && value1 != second)
            return false
        if (value2 != null && value2 != first)
            return false
        if (value1 == null)
            map1.put(first, second)
        if (value2 == null)
            map2.put(second, first)
    }

    return true
}

fun main(args: Array<String>) {
    println(areFollowingPatterns(mutableListOf("a", "b", "b"), mutableListOf("x", "y", "y")))
    println(areFollowingPatterns(mutableListOf("a", "b", "c"), mutableListOf("x", "y", "y")))
}