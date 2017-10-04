package com.github.olegstepanov.codefights.interviewpractice.trees.dfsbfs

import com.github.olegstepanov.codefights.interviewpractice.trees.Tree

fun digitTreeSum(t: Tree<Int>?): Long {
  var sum: Long = 0

  fun visitNode(n: Tree<Int>, value: Long) {
    val newValue = value * 10 + n.value

    if (n.left == null && n.right == null)
      sum += newValue
    else {
      if (n.left != null)
        visitNode(n.left!!, newValue)
      if (n.right != null)
        visitNode(n.right!!, newValue)
    }
  }

  if (t != null)
    visitNode(t, 0)
  return sum
}
