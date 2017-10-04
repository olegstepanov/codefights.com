package com.github.olegstepanov.codefights.interviewpractice.trees.basic

import com.github.olegstepanov.codefights.interviewpractice.trees.Tree


fun isTreeSymmetric(t: Tree<Int>?): Boolean {
    fun areSymmetric(t1: Tree<Int>?, t2: Tree<Int>?):Boolean {
        if (t1 == null && t2 != null)
            return false
        if (t2 == null && t1 != null)
            return false
        if (t1 == null && t2 == null)
            return true
        if (t1!!.value != t2!!.value)
            return false
        return areSymmetric(t1.right, t2.left) && areSymmetric(t1.left, t2.right)
    }

    return areSymmetric(t?.left, t?.right)
}
