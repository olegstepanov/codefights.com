package com.github.olegstepanov.codefights.challenges

import java.util.*

fun <T> iterable(f: () -> Pair<T, Boolean>): Iterable<T> = object : Iterable<T> {
  override fun iterator(): Iterator<T> = object : Iterator<T> {
    var t: Pair<T, Boolean>? = null

    override fun hasNext(): Boolean {
      if (t == null)
        t = f()
      return t!!.second
    }

    override fun next(): T {
      val item = t!!.first
      t = null
      return item
    }
  }
}

fun enumerateSubsets(setSize: Int, subsetSize: Int) : Iterable<Collection<Int>> {
  val indexes = BitSet(setSize)

  return iterable {
    do {
      for (i in setSize - 1 downTo 0) {
        if (!indexes[i]) {
          indexes[i] = true
          for (j in i + 1 until setSize)
            indexes[j] = false
          break
        }
      }
    } while (indexes.nextClearBit(0) in 0 until setSize && indexes.stream().count() != subsetSize.toLong())

    fun setIndexes() : List<Int> {
      var i = indexes.nextSetBit(0)
      val result = mutableListOf<Int>()
      while (i >= 0)
      {
        result.add(i++)
        i = indexes.nextSetBit(i)
      }
      return result
    }

    Pair(setIndexes(), indexes.nextClearBit(0) in 0 until setSize)
  }
}

fun numberOfTriangles2(sticks: MutableList<Int>): Int = enumerateSubsets(sticks.size, 3).count {
  val triangleSides = it.map { sticks[it] }.sorted()
  triangleSides[2] < triangleSides[0] + triangleSides[1]
}

fun main(args: Array<String>) {
  val setSize = 4
  for (subset in enumerateSubsets(setSize, 3)) {
    for (i in 0 until setSize)
      print(if (subset.contains(i)) "1" else "0")
    println()
  }
}
