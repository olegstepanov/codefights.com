package com.github.olegstepanov.codefights.bots.instabot

fun isAdmissibleOverpayment(prices: MutableList<Double>, notes: MutableList<String>, x: Double): Boolean {
  fun noteToPercentDiff(note: String) : Double =
          when {
            note.startsWith("Same") -> 0.0
            else -> note.substringBefore('%').toDouble() * (if (note.contains("higher")) 1 else - 1)
          }

  val diffs = prices
          .zip(notes.map({ noteToPercentDiff(it) / 100 }))
          .map { it.first * it.second / (1 + it.second) }
  val overpayment = diffs.sum()

  return overpayment <= x
}

fun main(args: Array<String>) {
  println(isAdmissibleOverpayment(mutableListOf(110.0, 95.0, 70.0),
          mutableListOf("10.0% higher than in-store",
                  "5.0% lower than in-store",
                  "Same as in-store"), 5.0))
}
