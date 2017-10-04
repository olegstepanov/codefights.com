package com.github.olegstepanov.codefights.interviewpractice.trees.basic

import com.github.olegstepanov.codefights.interviewpractice.trees.Tree
import com.github.olegstepanov.codefights.interviewpractice.trees.t

fun deleteFromBST(t: Tree<Int>?, queries: MutableList<Int>): Tree<Int>? {
  fun findNode(t: Tree<Int>?, parent: Tree<Int>?, query: Int): Pair<Tree<Int>, Tree<Int>?>? =
          when {
            t == null -> null
            t.value == query -> Pair(t, parent)
            t.value > query -> findNode(t.left, t, query)
            t.value < query -> findNode(t.right, t, query)
            else -> null
          }

  fun findRightmost(t: Tree<Int>, parent: Tree<Int>): Pair<Tree<Int>, Tree<Int>> =
          when {
            t.right != null -> findRightmost(t.right!!, t)
            else -> Pair(t, parent)
          }

  var t = t

  for (query in queries) {
    val (node, parent) = findNode(t, null, query) ?: continue
    var newNode: Tree<Int>? = null

    if (node.left != null) {
      val (rightmost, rightmostParent) = findRightmost(node.left!!, node)
      if (rightmostParent != node) {
        rightmostParent.right = rightmost.left
        rightmost.left = node.left
      }
      rightmost.right = node.right
      newNode = rightmost
    } else
      newNode = node.right

    if (parent != null) {
      when (node) {
        parent.left -> parent.left = newNode
        parent.right -> parent.right = newNode
      }
    } else {
      t = newNode
    }
  }

  return t
}

fun main(args: Array<String>) {
  test2()
}

private fun test2() {
  val t = t(5, t(2, t(1), t(3)), t(6, null, t(8, t(7))))
  print(t)
  print(deleteFromBST(t, mutableListOf(4, 5, 6)))
}

private fun test1() {
  val t = t(3, t(2, t(1)), t(5))
  print(t)
  print(deleteFromBST(t, mutableListOf(3)))
}