package com.github.olegstepanov.codefights.interviewpractice.backtracking

fun combinationSum(a: MutableList<Int>, sum: Int): String {
  val builder = StringBuilder()
  val prefix = Array(a.size) { 0 }
  val a = HashSet<Int>(a).toMutableList()
  a.sort()

  fun sum(index: Int, sum: Int) {
    if (index == a.size) return
    if (sum < a[index]) return

    prefix[index] = sum / a[index]
    while (prefix[index] >= 0) {
      val prefixSum = prefix[index] * a[index]
      if (prefixSum == sum) {
        builder.append("(")
        var first = true
        for (i in 0..index) {
          for (j in 1..prefix[i]) {
            if (!first)
              builder.append(" ")
            builder.append(a[i])
            first = false
          }
        }
        builder.append(")")
      } else if (prefixSum < sum)
        sum(index + 1, sum - prefixSum)
      else {
        prefix[index] = 0
        sum(index + 1, sum)
        break
      }
      prefix[index]--
    }
  }

  sum(0, sum)

  if (builder.isEmpty())
    builder.append("Empty")
  return builder.toString()
}

fun main(args: Array<String>) {
  println(combinationSum(mutableListOf(8, 1, 8, 6, 8), 12))
}