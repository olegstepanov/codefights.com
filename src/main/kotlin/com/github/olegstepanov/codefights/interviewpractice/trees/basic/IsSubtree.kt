package com.github.olegstepanov.codefights.interviewpractice.trees.basic

import com.github.olegstepanov.codefights.interviewpractice.trees.Tree

fun equal(t1: Tree<Int>?, t2: Tree<Int>?) : Boolean =
        when {
            t1 === t2 -> true
            t1 == null || t2 == null -> false
            t1.value == t2.value -> equal(t1.left, t2.left) &&
                    equal(t1.right, t2.right)
            else -> false
        }

fun isSubtree(t1: Tree<Int>?, t2: Tree<Int>?): Boolean =
        when {
            t2 == null -> true
            t1 == null -> false
            equal(t1, t2) -> true
            isSubtree(t1.left, t2) -> true
            isSubtree(t1.right, t2) -> true
            else -> false
        }

