package com.github.olegstepanov.codefights.bots.spacebot

fun packetDescrambler(seq: MutableList<Int>, fragmentData: MutableList<Char>, n: Int): String {
  val candidates = mutableMapOf<Int, MutableMap<Char, Int>>()
  val definite = mutableMapOf<Int, Char>()
  var last = -1

  for (i in 0 until seq.size) {
    val seqNo = seq[i]

    if (last >= 0 && seqNo >= last)
      continue

    if (definite.containsKey(seqNo))
      continue

    val char = fragmentData[i]
    val set = candidates.getOrPut(seqNo) { mutableMapOf() }

    set.put(char, set.getOrDefault(char, 0) + 1)
    if (set[char]!! > n / 2) {
      definite.put(seqNo, char)
      candidates.remove(seqNo)
      if (char == '#') {
        if (last < 0)
          last = seqNo
        else {
          last = -1;
          break
        }
      }
    }
  }

  if (last < 0 || candidates.isNotEmpty()
          || definite.keys.any { it > last }
          || (0..last).any { !definite.containsKey(it) })
    return ""

  return String((0..last).map { definite[it]!! }.toCharArray())
}

fun main(args: Array<String>) {
  println(packetDescrambler(mutableListOf(1, 1, 2, 2, 2, 0, 0, 0), mutableListOf('+',
          '+',
          'A',
          'A',
          'B',
          '#',
          '#',
          '#'), 3))
  println(packetDescrambler(mutableListOf(1, 1, 2, 2, 2, 4, 4), mutableListOf('+',
          '+',
          'A',
          'A',
          'B',
          '#',
          '#'), 3))
  println(packetDescrambler(mutableListOf(), mutableListOf(), 0))
  println(packetDescrambler(mutableListOf(), mutableListOf(), 1))
}