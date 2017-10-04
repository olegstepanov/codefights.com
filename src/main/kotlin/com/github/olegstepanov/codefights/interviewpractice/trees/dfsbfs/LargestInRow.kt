package com.github.olegstepanov.codefights.interviewpractice.trees.dfsbfs

import com.github.olegstepanov.codefights.interviewpractice.trees.Tree

fun largestValuesInTreeRows(t: Tree<Int>?): MutableList<Int> {
  val wave = mutableListOf<Tree<Int>>()
  val largestList = mutableListOf<Int>()
  if (t != null) {
    wave.add(t)

    while (wave.isNotEmpty()) {
      var largest = Int.MIN_VALUE

      val oldWave = wave.toTypedArray()
      wave.clear()
      for (node in oldWave) {
        if (node.left != null)
          wave.add(node.left!!)
        if (node.right != null)
          wave.add(node.right!!)

        if (node.value > largest)
          largest = node.value
      }

      largestList.add(largest)
    }
  }
  return largestList
}
