package com.github.olegstepanov.codefights.bots.tackbot

fun ratingThreshold(threshold: Double, ratings: MutableList<MutableList<Int>>): MutableList<Int> =
        ratings
                .mapIndexed { index, ratings -> Pair(index, ratings.sum().toDouble() / ratings.size) }
                .filter { (_, rating) -> rating < threshold }
                .map { (index, _) -> index }
                .toMutableList()

fun main(args: Array<String>) {
  println(ratingThreshold(3.5, mutableListOf(mutableListOf(3, 4),
          mutableListOf(3, 3, 3, 4),
          mutableListOf(4))))
}