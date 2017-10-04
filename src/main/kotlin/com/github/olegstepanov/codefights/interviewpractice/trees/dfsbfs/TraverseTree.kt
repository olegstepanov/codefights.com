package com.github.olegstepanov.codefights.interviewpractice.trees.dfsbfs

import com.github.olegstepanov.codefights.interviewpractice.trees.Tree
import com.github.olegstepanov.codefights.interviewpractice.trees.t

fun traverseTree(t: Tree<Int>?): MutableList<Int> {
  val result = mutableListOf<Tree<Int>>()
  if (t != null) {
    result.add(t)
    var pos = 0

    do {
      val endPos = result.size
      for (i in pos until endPos) {
        val node = result[i]
        if (node.left != null)
          result.add(node.left!!)
        if (node.right != null)
          result.add(node.right!!)
      }
      pos = endPos
    } while (pos < result.size)
  }
  return result.map { it.value }.toMutableList()
}

fun main(args: Array<String>) {
  println(traverseTree(t(1, t(2), t(3))))
}
