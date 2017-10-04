package com.github.olegstepanov.codefights.interviewpractice.trees.basic

import com.github.olegstepanov.codefights.interviewpractice.trees.Tree
import com.github.olegstepanov.codefights.interviewpractice.trees.t

fun hasPathWithGivenSum(t: Tree<Int>?, s: Int): Boolean =
        when  {
            t == null -> s == 0
            t.left == null && t.right == null -> s == t.value
            t.left != null && hasPathWithGivenSum(t.left, s - t.value) -> true
            t.right != null && hasPathWithGivenSum(t.right, s - t.value) -> true
            else -> false
        }

fun main(args: Array<String>) {
    println(hasPathWithGivenSum(
            t(4,
                    t(1,
                            t(-2, null, t(3)), null),
                    t(3, t(1), t(2, t(-2), t(-3)))), 7))
}
