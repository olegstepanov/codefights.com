package com.github.olegstepanov.codefights.interviewpractice.hashtables

import kotlin.collections.HashSet

fun containsCloseNums(nums: MutableList<Int>, k: Int): Boolean {
    val set = HashSet<Int>()
    for (i in 0 until minOf(k + 1, nums.size)) {
        if (!set.add(nums[i]))
            return true
    }
    for (i in 1..nums.size - k - 1) {
        set.remove(nums[i - 1])
        if (!set.add(nums[i - 1 + k + 1]))
            return true
    }

    return false
}

fun main(args: Array<String>) {
    println(containsCloseNums(mutableListOf(0, 1, 2, 3, 5, 2), 2))
    println(containsCloseNums(mutableListOf(0, 1, 2, 3, 5, 2), 3))
}