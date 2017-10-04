package com.github.olegstepanov.codefights.bots.instabot

fun dailyIntake(caloricValue: MutableList<Int>): MutableList<Int> {
  val solution = mutableListOf<Int>()
  var bestSolution : IntArray? = null

  fun value() = solution.map { caloricValue[it] }.sum()

  fun addOne() {
    solution.add((0 until caloricValue.size).first { !solution.contains(it) })
  }

  return bestSolution!!.toMutableList()
}
