package com.github.olegstepanov.codefights.interviewpractice.backtracking

fun climbingStaircase(n: Int, k: Int): MutableList<MutableList<Int>> {
  val result = mutableListOf<MutableList<Int>>()
  val solution = mutableListOf<Int>()

  fun addOne() = solution.add(1)
  fun removeLast() = solution.removeAt(solution.size - 1)
  fun incrementLast() { solution[solution.size - 1] = solution.last() + 1 }

  if (k > 0 && n > 0 && n >= k) {
    while (true) {
      println(solution)

      addOne()

      if (solution.sum() > n)
        removeLast()
      if (solution.sum() == n) {
        result.add(solution.toMutableList())
        removeLast()
        while (solution.isNotEmpty() && solution.last() == k)
          removeLast()
        if (solution.isEmpty())
          break
        incrementLast()
      }
    }
  } else {
    result.add(mutableListOf())
  }

  return result
}

fun main(args: Array<String>) {
  println(climbingStaircase(4, 2))
  println(climbingStaircase(0, 0))
}
