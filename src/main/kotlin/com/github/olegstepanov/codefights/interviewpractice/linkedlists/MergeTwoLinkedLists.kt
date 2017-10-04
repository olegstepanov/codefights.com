package com.github.olegstepanov.codefights.interviewpractice.linkedlists

fun mergeTwoLinkedLists(l1: ListNode<Int>?, l2: ListNode<Int>?): ListNode<Int>? {
    var l1 = l1
    var l2 = l2
    var head: ListNode<Int>? = null
    var tail = head

    fun next(nl: ListNode<Int>?) {
        if (tail != null) tail!!.next = nl
        tail = nl
        if (head == null) head = tail
    }

    while (l1 != null || l2 != null) {
        var idx = -1

        if (l1 == null)
            idx = 2
        else if (l2 == null)
            idx = 1
        else if (l1.value < l2.value)
            idx = 1
        else
            idx = 2

        if (idx == 1) {
            next(l1)
            l1 = l1!!.next
        } else {
            next(l2)
            l2 = l2!!.next
        }
    }

    return head
}

fun main(args: Array<String>) {
  print(mergeTwoLinkedLists(list(1, 2, 3), list(4, 5, 6)))
}