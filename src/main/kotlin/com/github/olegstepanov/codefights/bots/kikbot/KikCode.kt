package com.github.olegstepanov.codefights.bots.kikbot

import java.math.BigInteger

fun kikCode(userId: String): MutableList<MutableList<MutableList<Int>>> {
  fun prepend(s: String) = ("0".repeat(52 - s.length) + s).reversed()
  val binary = BigInteger(userId).toString(2).let { prepend(it) }

  val groups = listOf(3, 4, 8, 10, 12, 15)
  assert(groups.sum() == binary.length)

  val result = mutableListOf<MutableList<MutableList<Int>>>()
  var radius = 1
  for (i in 0 until groups.size) {
    val bitsInGroup = groups[i]
    var bits = String(binary.drop(groups.take(i).sum()).take(bitsInGroup).toCharArray())
    println(bits)

    val oneSector = 360 / bitsInGroup
    var start = 0
    var end = 0

    fun addSegment() {
      result.add(mutableListOf(mutableListOf(radius, start), mutableListOf(radius, end)))
    }

    val gap = bits.indexOf('0')
    if (gap > 0 && bits.last() == '1' && bits.first() == '1') {
      bits = bits.substring(gap) + bits.substring(0, gap)
      start = gap * oneSector
      end = start
    }

    var lastWas1 = false
    for (b in 0 until bits.length) {
      if (bits[b] == '1') {
        if (!lastWas1)
          start = end
        end += oneSector
        lastWas1 = true
      } else {
        if (lastWas1)
          addSegment()
        end += oneSector
        start = end
        lastWas1 = false
      }
    }

    if (lastWas1)
      addSegment()

    radius++
  }

  return result
}

fun main(args: Array<String>) {
  println(kikCode("1851027803204441"))
  println(kikCode("0"))
}
