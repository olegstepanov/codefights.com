package com.github.olegstepanov.codefights.interviewpractice.trees.basic

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.github.olegstepanov.codefights.interviewpractice.trees.Tree

fun kthLargestInBST(t: Tree<Int>?, k: Int): Int {
    fun findKthIn(t: Tree<Int>?, found: Int) : Pair<Int, Int?> {
        assert(found < k)

        if (t == null)
            return Pair(found, null)

        val (foundLeft, kThFromLeft) = findKthIn(t.left, found)
        if (kThFromLeft != null)
            return Pair(foundLeft, kThFromLeft)
        if (foundLeft == k - 1)
            return Pair(foundLeft + 1, t.value)
        val (foundRight, kThFromRight) = findKthIn(t.right, foundLeft + 1)
        if (kThFromRight == null)
            assert(foundRight < k)
        return Pair(foundRight, kThFromRight)
    }

    return findKthIn(t, 0).second ?: 0
}

fun treeFromJson(o: JsonObject) : Tree<Int> {
    return Tree(o.map["value"] as Int).apply {
        left = o.map["left"]?.let { treeFromJson(it as JsonObject) }
        right = o.map["right"]?.let { treeFromJson(it as JsonObject) }
    }
}

fun main(args: Array<String>) {
    val parser = Parser()
    val obj = parser.parse("test/kthLargestInBST7.txt") as JsonObject
    println(kthLargestInBST(treeFromJson(obj), 37))
}