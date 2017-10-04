package com.github.olegstepanov.codefights.interviewpractice.trees.basic

import com.github.olegstepanov.codefights.interviewpractice.trees.Tree
import com.github.olegstepanov.codefights.interviewpractice.trees.printTree


fun restoreBinaryTree(inorder: MutableList<Int>, preorder: MutableList<Int>): Tree<Int>? {
    assert(inorder.size == preorder.size)

    if (inorder.size == 0)
        return null

    val root = preorder[0]
    val leftSize = inorder.indexOf(root)
    val left = restoreBinaryTree(inorder.subList(0, leftSize),
            preorder.subList(1, leftSize + 1))
    val right = restoreBinaryTree(inorder.subList(leftSize + 1, inorder.size), preorder.subList(leftSize + 1, preorder.size))

    return Tree(root).apply { this.left = left; this.right = right }
}

fun main(args: Array<String>) {
    val tree = restoreBinaryTree(mutableListOf(4, 2, 1, 5, 3, 6), mutableListOf(1, 2, 4, 3, 5, 6))
  printTree(tree)
}
