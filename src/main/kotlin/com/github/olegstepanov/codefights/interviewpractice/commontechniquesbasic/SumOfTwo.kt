package com.github.olegstepanov.codefights.interviewpractice.commontechniquesbasic

fun sumOfTwo(a: MutableList<Int>, b: MutableList<Int>, v: Int): Boolean {
  val short = if (a.size < b.size) a else b
  val long = if (short === a) b else a
  long.sort()

  for (i in short) {
    if (long.binarySearch(v - i) >= 0) return true
  }

  return false
}

