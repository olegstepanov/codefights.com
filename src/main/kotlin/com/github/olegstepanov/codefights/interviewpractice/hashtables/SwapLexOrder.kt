package com.github.olegstepanov.codefights.interviewpractice.hashtables

fun swapLexOrder(str: String, pairs: MutableList<MutableList<Int>>): String {
    for (pair in pairs) {
        pair[0]--
        pair[1]--
    }

    if (pairs.size == 0)
        return str

    val rings = HashSet<HashSet<Int>>()
    val unsortedPairs = mutableListOf(*pairs.toTypedArray())

    while (unsortedPairs.isNotEmpty()) {
        val ring = HashSet<Int>()
        ring.addAll(unsortedPairs.removeAt(0))

        loop@ while (unsortedPairs.isNotEmpty()) {
            for (pair in unsortedPairs.toTypedArray()) {
                if (pair.any { ring.contains(it) }) {
                    ring.addAll(pair)
                    unsortedPairs.remove(pair)
                    continue@loop
                }
            }

            break@loop
        }

        rings.add(ring)
    }

    val array = str.toCharArray()

    for (ring in rings) {
        val letters = ring.map { str[it] }.sortedDescending()
        val zip = ring.toList().sorted().zip(letters)
        zip.forEach { array[it.first] = it.second }
    }

    return String(array)
}

fun main(args: Array<String>) {
    println(swapLexOrder("abdc", mutableListOf(
            mutableListOf(1, 4),
            mutableListOf(3, 4))
    ))

    println(swapLexOrder("abcdefgh", mutableListOf(
            mutableListOf(1, 4),
            mutableListOf(7, 8))
    ))

    println(swapLexOrder("acxrabdz", mutableListOf(
            mutableListOf(1, 3),
            mutableListOf(6, 8),
            mutableListOf(3, 8),
            mutableListOf(2, 7))
    ))

    println(swapLexOrder("fixmfbhyutghwbyezkveyameoamqoi",
            mutableListOf(mutableListOf(8, 5),
                    mutableListOf(10, 8),
                    mutableListOf(4, 18),
                    mutableListOf(20, 12),
                    mutableListOf(5, 2),
                    mutableListOf(17, 2),
                    mutableListOf(13, 25),
                    mutableListOf(29, 12),
                    mutableListOf(22, 2),
                    mutableListOf(17, 11))))
}