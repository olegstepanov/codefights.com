package com.github.olegstepanov.codefights.interviewpractice.dynamicprogramming

fun climbingStairs(n: Int): Int =
        when (n) {
          0 -> 0
          1 -> 1
          2 -> 2
          else -> climbingStairs(n - 1) + climbingStairs(n - 2)
        }

fun main(args: Array<String>) {
  println(climbingStairs(1))
  println(climbingStairs(2))
  println(climbingStairs(3))
  println(climbingStairs(23))
  println(climbingStairs(23))
}
