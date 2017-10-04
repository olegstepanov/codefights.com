package com.github.olegstepanov.codefights.interviewpractice.trees

data class Tree<T>(var value: T) {
  var left: Tree<T>? = null;
  var right: Tree<T>? = null;
}

fun <T> t(value: T, left: Tree<T>? = null, right: Tree<T>? = null) = Tree(value)
        .apply {
          this.left = left
          this.right = right
        }

fun <T> printTree(t: Tree<T>?, indent: Int = 0) {
  print(" ".repeat(indent))
  println(t?.value ?: "null")
  if (t?.left != null)
    printTree(t.left, indent + 2)
  if (t?.right != null)
    printTree(t.right, indent + 2)
}
