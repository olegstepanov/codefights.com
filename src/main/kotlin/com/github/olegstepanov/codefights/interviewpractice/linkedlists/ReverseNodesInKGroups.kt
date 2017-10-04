package com.github.olegstepanov.codefights.interviewpractice.linkedlists

fun reverseNodesInKGroups(l: ListNode<Int>?, k: Int): ListNode<Int>? {
    var l = l
    var h: ListNode<Int>? = null
    var c: ListNode<Int>? = null

    while (l != null) {
        var idx = 0
        var nextChunk = l
        while (nextChunk != null && idx++ < k)
            nextChunk = nextChunk.next
        if (idx < k)
            break
        var p = nextChunk
        val nc = l
        while (l != null && l != nextChunk) {
            val next = l.next
            l.next = p
            p = l
            l = next
        }
        if (c != null)
            c.next = p
        else
            h = p
        c = nc
    }

    return h
}

fun main(args: Array<String>) {
  print(reverseNodesInKGroups(list(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11), 3))
  print(reverseNodesInKGroups(list(239), 1))
}
