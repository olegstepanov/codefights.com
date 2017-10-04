package com.github.olegstepanov.codefights.interviewpractice.commontechniquesbasic

fun arrayMaxConsecutiveSum2(inputArray: MutableList<Int>): Int {
  val size = inputArray.size
  val prefixsums = IntArray(size + 1)
  val minsums = IntArray(size)

  var i = 0
  var prefix = 0
  var minsum = 0
  var maxsubset = Int.MIN_VALUE
  while (i < size) {
    prefix += inputArray[i]
    prefixsums[i + 1] = prefix
    minsums[i] = minsum
    val subset = prefix - minsum
    if (subset > maxsubset)
      maxsubset = subset
    if (prefix < minsum)
      minsum = prefix
    i++
  }

  return maxsubset
}

fun main(args: Array<String>) {
  println(arrayMaxConsecutiveSum2(mutableListOf(-2, 2, 5, -11, 6)))
  println(arrayMaxConsecutiveSum2(mutableListOf(89, 96, 60, 10, 24, 30, 72, 40, 74, 49, 38, 87, 55, 46, 44, 14, 49, 88, 93, 11)))
  println(arrayMaxConsecutiveSum2(mutableListOf(-3, -2, -1, -4)))
}