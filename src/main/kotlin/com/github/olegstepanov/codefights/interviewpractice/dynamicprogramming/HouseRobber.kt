package com.github.olegstepanov.codefights.interviewpractice.dynamicprogramming

fun houseRobber(nums: MutableList<Int>): Int {
  val robs = IntArray(nums.size)

  when (nums.size) {
    0 -> return 0
    1 -> return nums[0]
  }

  for (i in 0 until nums.size) {
    robs[i] = when (i) {
      0 -> nums[0]
      1 -> maxOf(nums[0], nums[1])
      else -> maxOf(robs[i - 1], robs[i - 2] + nums[i])
    }
  }

  return maxOf(robs[nums.size - 1], robs[nums.size - 2])
}

fun main(args: Array<String>) {
  println(houseRobber(mutableListOf(1, 1, 1)))
  println(houseRobber(mutableListOf(2, 1)))
  println(houseRobber(mutableListOf(155, 44, 52, 58, 250, 225, 109, 118, 211, 73, 137, 96, 137, 89, 174, 66, 134, 26, 25, 205, 239, 85, 146, 73, 55, 6, 122, 196, 128, 50, 61, 230, 94, 208, 46, 243, 105, 81, 157, 89, 205, 78, 249, 203, 238, 239, 217, 212, 241, 242, 157, 79, 133, 66, 36, 165)))
}
