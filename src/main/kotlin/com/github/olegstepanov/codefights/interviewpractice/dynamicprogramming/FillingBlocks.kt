package com.github.olegstepanov.codefights.interviewpractice.dynamicprogramming

fun fillingBlocks(n: Int): Int {
  val blocks = IntArray(n)

  for (i in 1..n) {
    blocks[i - 1] = when (n) {
      1 -> 1
      2 -> 5
      3 -> 5 + 5 + 1 // (2 + 1) + (1 + 2) + 1 = 11
      4 -> 36 // 5 * 5 + 11
      5 -> 36 + 36 + 11 + 11 + 1 // (4 + 1) + (1 + 4) + (3 + 3 + 1) = 95
      6 -> 215 // 11 * 11 + 95
      7 -> 781 //
      8 -> 2245
      9 -> 6336 // 2245 + 2245 + 781 + 781 + 1
      else -> 0
    }
  }

  return 0
}
