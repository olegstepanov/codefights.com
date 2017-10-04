package com.github.olegstepanov.codefights.interviewpractice.commontechniquesbasic

fun minSubstringWithAllChars(s: String, t: String): String {
  var minmin = 0
  var minmax = -1

  val pos = IntArray(t.length) { -1 }
  for (i in 0 until s.length) {
    val index = t.indexOf(s[i])
    if (index < 0) continue

    pos[index] = i

    if (pos.all { it >= 0 }) {
      val min = pos.min()!!
      val max = pos.max()!!

      if (minmax < 0 || max - min < minmax - minmin) {
        minmin = min
        minmax = max
      }
    }
  }

  return s.substring(minmin, minmax + 1)
}

fun main(args: Array<String>) {
  println(minSubstringWithAllChars("adobecodebanc", "abc"))
  println(minSubstringWithAllChars("", ""))
}
