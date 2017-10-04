package com.github.olegstepanov.codefights.bots.instabot

fun deliveryFee(intervals: MutableList<Int>, fees: MutableList<Int>, deliveries: MutableList<MutableList<Int>>): Boolean {
  val deliveriesHours = deliveries.map { it[0] }
  var lastRatio = -1.0

  intervals.add(24)
  for (i in 0 until intervals.size - 1) {
    val interval = intervals[i] until intervals[i + 1]
    val count = deliveriesHours.count { it in interval }

    val ratio = fees[i].toDouble() / count
    if (i != 0 && ratio != lastRatio)
      return false
    lastRatio = ratio
  }

  return true
}
