package com.github.olegstepanov.codefights.interviewpractice.backtracking

fun sumSubsets(arr: MutableList<Int>, num: Int): MutableList<MutableList<Int>> {
  abstract class Subset
  class Leaf(val value: Int) : Subset()
  class Empty : Subset()
  class Node(val sub1: Subset, val sub2: Subset) : Subset()

  fun sum(from: Int, to: Int, num: Int, supply: (Subset) -> Unit) {
    if (num < 0)
      return
    if (num == 0) {
      supply(Empty())
      return
    }
    if (from > to)
      return
    sum(from + 1, to, num - arr[from]) {
      supply(Node(Leaf(arr[from]), it))
    }
    sum(from + 1, to, num) {
      supply(it)
    }
  }

  val solutions = mutableListOf<MutableList<Int>>()
  sum(0, arr.size - 1, num) {
    val solution = mutableListOf<Int>()

    fun visit(s: Subset): Any = when (s) {
      is Leaf -> {
        solution.add(s.value)
      }
      is Node -> {
        visit(s.sub1)
        visit(s.sub2)
      }
      else -> {
      }
    }

    visit(it)

    main@ for (oldSolution in solutions) {
      if (oldSolution.size == solution.size) {
        for (i in 0 until solution.size)
          if (solution[i] != oldSolution[i])
            continue@main
        return@sum
      }
    }
    solutions.add(solution)
  }

  return solutions
}

fun main(args: Array<String>) {
  println(sumSubsets(mutableListOf(1, 2, 3, 4, 5), 5))
  println(sumSubsets(mutableListOf(1, 2, 2, 3, 4, 5), 5))
  println(sumSubsets(mutableListOf(), 0))
  println(sumSubsets(mutableListOf(1, 1, 1, 1, 1, 1, 1, 1, 1), 9))
  println(sumSubsets(mutableListOf(1, 1, 2, 2, 2, 5, 5, 6, 8, 8), 9))
  println(sumSubsets(mutableListOf(1, 1, 2, 4, 4, 4, 7, 9, 9, 13, 13, 13, 15, 15, 16, 16, 16, 19, 19, 20), 36))
}