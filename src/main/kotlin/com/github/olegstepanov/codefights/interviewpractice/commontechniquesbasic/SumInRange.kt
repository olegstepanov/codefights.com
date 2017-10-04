package com.github.olegstepanov.codefights.interviewpractice.commontechniquesbasic

import java.util.*

fun sumInRangeP(nums: MutableList<Int>, queries: MutableList<MutableList<Int>>): Int {
  val numsLen = nums.size
  val prefixsums = IntArray(numsLen + 1)

  var i = 0
  var prefix = 0
  while (i < numsLen) {
    prefix += nums[i]
    prefixsums[i + 1] = prefix
    i++
  }

  var q = 0
  val queriesLen = queries.size
  var sum = 0L
  while (q < queriesLen) {
    val query = queries[q++]
    sum += prefixsums[query[1] + 1] - prefixsums[query[0]]
  }

  var rem = (sum % 1000000007).toInt()
  if (rem < 0)
    rem += 1000000007
  return rem
}


fun sumInRangeOld(nums: MutableList<Int>, queries: MutableList<MutableList<Int>>): Int {
  val counts = IntArray(nums.size)

  var q = 0
  val queriesCount = queries.size
  while (q < queriesCount) {
    val query = queries[q++]
    var i = query[0]
    val end = query[1]
    while (i <= end)
      counts[i++]++
  }

  var i = 0
  val len = counts.size
  var sum = 0L
  while (i < len) {
    while (i < len && (counts[i] == 0 || nums[i] == 0)) { i++ }
    if (i < len) {
      sum += nums[i] * counts[i]
      i++
    }
  }

  var rem = (sum % 1000000007).toInt()
  if (rem < 0)
    rem += 1000000007
  return rem
}

fun sumInRange(nums: MutableList<Int>, queries: MutableList<MutableList<Int>>): Int =
        sumInRangeP(nums, queries)

fun main(args: Array<String>) {
  println(sumInRange(mutableListOf(3, 0, -2, 6, -3, 2), mutableListOf(mutableListOf(0,2),
          mutableListOf(2,5),
          mutableListOf(0,5))))
  println(sumInRange(
          mutableListOf(-77, 54, -59, -94, -13, -78, -81, -38, -26, 17, -73, -88, 90, -42, -63, -36, 37, 25, -22, 4, 25, -86, -44, 88, 2, -47, -29, 71, 54, -42),
          mutableListOf(mutableListOf(2, 2),
                  mutableListOf(4, 7),
                  mutableListOf(2, 4),
                  mutableListOf(0, 2),
                  mutableListOf(3, 6),
                  mutableListOf(6, 6),
                  mutableListOf(3, 3),
                  mutableListOf(2, 7),
                  mutableListOf(3, 4),
                  mutableListOf(3, 3),
                  mutableListOf(2, 9),
                  mutableListOf(0, 1),
                  mutableListOf(4, 4),
                  mutableListOf(2, 3),
                  mutableListOf(0, 6),
                  mutableListOf(4, 4),
                  mutableListOf(2, 3),
                  mutableListOf(0, 5),
                  mutableListOf(2, 5),
                  mutableListOf(4, 5))))

  val rand = Random()
  val nums = Array(100000) { rand.nextInt(2001) - 1000 }.toMutableList()
  val queries = Array(300000) {
    val start = rand.nextInt(nums.size)
    mutableListOf(start, start + rand.nextInt(nums.size - start))
  }.toMutableList()
  val start = System.currentTimeMillis()
  var first = 0L
  var sumInRange = 0
  val runs = 5
  for (i in 1..runs) {
    sumInRange = sumInRange(nums, queries)
    if (i == 1)
      first = (System.currentTimeMillis() - start)
  }
  println(first)
  println((System.currentTimeMillis() - start) / runs)
  println(sumInRange)
}
