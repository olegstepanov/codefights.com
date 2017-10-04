package com.github.olegstepanov.codefights.interviewpractice.commontechniquesbasic

fun findLongestSubarrayBySum(s: Int, arr: MutableList<Int>): MutableList<Int> {
  var start = -1
  var end = -2

  if (arr.size == 0) return mutableListOf()

  var i = 0
  var p = 0
  var ips = 0

  while (i < arr.size) {
    ips += arr[i]
    while (ips > s && p < i) {
      ips -= arr[p]
      p++
    }

    if (ips == s && (end - start) < (i - p)) {
      start = p
      end = i
    }
    i++
  }

  return if (start < 0) mutableListOf(-1) else mutableListOf(start + 1, end + 1)
}

fun main(args: Array<String>) {
  println(findLongestSubarrayBySum(189, mutableListOf(164, 141, 52, 63, 30, 101, 114, 159, 179, 166, 8, 78, 1, 59, 40, 104, 161, 158, 125, 78, 109, 114, 88, 2, 51)))
  println(findLongestSubarrayBySum(354, mutableListOf(17, 53, 9, 63, 34, 55, 104, 35, 104, 57, 149, 125, 118, 14, 110, 29, 1, 81, 119, 59, 51, 156, 162, 65, 104, 77, 44, 110, 103, 162, 90, 149, 83, 54, 75, 21, 3, 124, 32, 170, 79, 60, 9, 20, 172, 4, 146, 182, 105, 193, 86, 114, 99, 190, 123, 139, 38, 11, 62, 35, 109, 162, 160, 94, 116, 70, 138, 70, 59, 101, 172, 65, 118, 16, 156, 16, 131, 40, 13, 89, 83, 155, 86, 111, 85, 175, 181, 16, 152, 142, 116, 80, 111, 99)))
  println(findLongestSubarrayBySum(3, mutableListOf(3)))
  println(findLongestSubarrayBySum(0, mutableListOf(1, 0, 2)))
}