package com.github.olegstepanov.codefights.interviewpractice.dynamicprogramming

fun composeRanges(nums: MutableList<Int>): MutableList<String> {
  val result = mutableListOf<Pair<Int, Int>>()

  for (num in nums) {
    if (!result.isEmpty() && result.last().second == num - 1)
      result[result.size - 1] = Pair(result.last().first, num)
    else
      result.add(Pair(num, num))
  }

  return result.map { (start, end) ->
    when (end) {
      start -> "$start"
      else -> "$start->$end"
    }
  }.toMutableList()
}

fun main(args: Array<String>) {
  println(composeRanges(mutableListOf(0, 1, 2, 4, 5, 7)))
}
