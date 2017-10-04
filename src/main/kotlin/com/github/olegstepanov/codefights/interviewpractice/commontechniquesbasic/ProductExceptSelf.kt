package com.github.olegstepanov.codefights.interviewpractice.commontechniquesbasic

fun productExceptSelf(nums: MutableList<Int>, m: Int): Int {
  val rems = Array(m) { IntArray(100) }
  for (i in 1 until m) {
    for (j in 1..100) {
      rems[i][j - 1] = (i * j) % m
    }
  }

  val counts = IntArray(100)
  for (i in 0 until nums.size) {
    counts[nums[i] - 1]++
  }

  val products = IntArray(100)
  for (i in 1..100) {
    var p = 1
    if (counts[i - 1] > 0) {
      for (j in 1..100) {
        for (c in 1..(if (i != j) counts[j - 1] else counts[j - 1] - 1))
          p = rems[p][j - 1]
      }
    }
    products[i - 1] = p
  }

  var g = 0
  for (i in 0 until nums.size) {
    g = (g + products[nums[i] - 1]) % m
  }

  return g
}

fun main(args: Array<String>) {
  println(productExceptSelf(mutableListOf(1, 2, 3, 4), 12))
}
