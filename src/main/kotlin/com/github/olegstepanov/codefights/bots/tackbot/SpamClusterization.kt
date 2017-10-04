package com.github.olegstepanov.codefights.bots.tackbot

fun spamClusterization(requests: MutableList<String>, ids: MutableList<Int>, threshold: Double): MutableList<MutableList<Int>> {
  val sets = requests.map {
    it.toLowerCase().split(*it.filter { !it.isLetter() }.toCharArray()).filter { it.isNotBlank() }.toSet()
  }

  val indexes = Array(sets.size) { DoubleArray(sets.size) { -1.0 } }
  for (i in 0 until sets.size) {
    for (j in 0 until i) {
      val index = sets[i].intersect(sets[j]).size.toDouble() / sets[i].union(sets[j]).size
      indexes[i][j] = index
      indexes[j][i] = index
    }
  }

  indexes.forEach { it.forEach { if (it < 0) print("---- ") else System.out.format("%.2f ", it) }; println() }

  val clusters = mutableListOf<Set<Int>>()
  for (i in 0 until sets.size) {
    (0 until i)
            .filter { indexes[i][it] >= threshold }
            .mapTo(clusters) { setOf(i, it) }
  }

  main@ while (true) {
    for (i in 0 until clusters.size) {
      for (j in i + 1 until clusters.size) {
        if (clusters[i].intersect(clusters[j]).isNotEmpty()) {
          val union = clusters[i].union(clusters[j])
          clusters.removeAt(j)
          clusters.removeAt(i)
          clusters.add(union)
          continue@main
        }
      }
    }
    break
  }

  return clusters.filter { it.size > 1 }.map {
    mutableListOf<Int>().apply {
      it.mapTo(this) { ids[it] }
      sort()
    }
  }.toMutableList()
}

fun main(args: Array<String>) {
  /*println(spamClusterization(mutableListOf("I need a new window.",
          "I really, really want to replace my window!",
          "Replace my window.",
          "I want a new window.",
          "I want a new carpet, I want a new carpet, I want a new carpet.",
          "Replace my carpet"),
          mutableListOf(374, 2845, 83, 1848, 1837, 1500),
          0.5))*/

  println(spamClusterization(mutableListOf("I need a new window",
          "I really, really want to replace my window",
          "Replace mY !!.windoW........",
          "I want a new window?",
          "I want a new carpet, i want a new carpet, I WANT A NEW CARPET",
          "RePlAcE!!! !!!My!!! !!!CaRpEt!!!!"),
          mutableListOf(374, 2845, 83, 1848, 1837, 1500),
          0.5))
}