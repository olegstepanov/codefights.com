package com.github.olegstepanov.codefights.bots.kikbot

fun rateLimit(sentBatches: MutableList<MutableList<Int>>, receivedMessages: MutableList<MutableList<Int>>, startingAllowance: Int): MutableList<Int> {
  sentBatches.forEach { it.add(1, 1) }
  receivedMessages.forEach { it.add(1, 2) }

  val events = (sentBatches + receivedMessages).toMutableList()
  events.sortWith(Comparator { o1, o2 -> if (o1[0] == o2[0]) o2[1].compareTo(o1[1]) else o1[0].compareTo(o2[0]) })

  val allowances = mutableMapOf<Int, Int>()
  var dayOfYear = -1
  val result = mutableListOf<Int>()
  var index = 0
  for (event in events) {
    with(java.time.Instant.ofEpochSecond(event[0].toLong()).atZone(java.time.ZoneId.of("UTC")).dayOfYear) {
      if (dayOfYear != this) {
        allowances.clear()
        dayOfYear = this
      }
    }

    val correspondents = event.asSequence().drop(2)
    val correspondentAllowances = correspondents.map { allowances.putIfAbsent(it, startingAllowance) ?: startingAllowance }
    when (event[1]) {
      1 -> {
        if (correspondentAllowances.any { it <= 0 }) {
          result.add(index)
        } else {
          correspondents.forEach { allowances.compute(it, { _, v -> v!! - 1 }) }
        }
        index++
      }
      2 -> event.asSequence().drop(2).forEach { allowances.compute(it, { _, v -> v?.let { it + 1 } ?: startingAllowance + 1 }) }
    }
  }

  return result
}

fun main(args: Array<String>) {
  println(rateLimit(mutableListOf(mutableListOf(1471040000, 736273, 827482, 2738283),
          mutableListOf(1471040005, 736273, 2738283),
          mutableListOf(1471040010, 827482, 2738283),
          mutableListOf(1471040015, 827482, 2738283),
          mutableListOf(1471040025, 827482, 2738283),
          mutableListOf(1471046400, 736273, 827482, 2738283),
          mutableListOf(1471050010, 827482)), mutableListOf(mutableListOf(1471040001, 2738283),
          mutableListOf(1471040002, 2738283),
          mutableListOf(1471040020, 2738283),
          mutableListOf(1471040021, 2738283),
          mutableListOf(1471040022, 2738283),
          mutableListOf(1471040030, 827482),
          mutableListOf(1471040030, 2738283)), 1))
  println(rateLimit(mutableListOf(mutableListOf(1471040000, 736273, 827482, 2738283),
          mutableListOf(1471040005, 736273, 2738283),
          mutableListOf(1471040010, 827482, 2738283),
          mutableListOf(1471040015, 2738283),
          mutableListOf(1471040025, 827482),
          mutableListOf(1471046400, 736273, 827482, 2738283)),
          mutableListOf(mutableListOf(1471040001, 2738283),
                  mutableListOf(1471040002, 2738283),
                  mutableListOf(1471040010, 827482),
                  mutableListOf(1471040020, 2738283)), 1))
}
