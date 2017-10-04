package com.github.olegstepanov.codefights.interviewpractice.hashtables

fun possibleSums(coins: MutableList<Int>, quantity: MutableList<Int>): Int {
    val sums = HashSet<Int>()
    val cur = mutableListOf(*Array(quantity.size) { 0 })

    main@ while (true) {
        val sum = cur.zip(coins).sumBy { it.first * it.second }
        if (sum != 0)
            sums.add(sum)

        for (i in 0 until quantity.size) {
            if (cur[i] < quantity[i]) {
                cur[i]++
                for (j in 0 until i)
                    cur[j] = 0
                break
            } else if (i == quantity.size - 1)
                break@main
        }
    }

    return sums.size
}

fun main(args: Array<String>) {
    println(possibleSums(mutableListOf(10, 50, 100, 500), mutableListOf(5, 3, 2, 2)))
}
