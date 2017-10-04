package com.github.olegstepanov.codefights.interviewpractice.linkedlists

fun rearrangeLastN(l: ListNode<Int>?, n: Int): ListNode<Int>? {
    var count = 0
    var cur = l
    var last: ListNode<Int>? = null
    while (cur != null) {
        count++
        last = cur
        cur = cur.next
    }

    if (count == n)
        return l

    cur = l
    var idx = 0
    while (idx++ < count - n - 1) {
        cur = cur!!.next
    }

    last?.next = l
    val head = cur?.next
    cur?.next = null

    return head
}

fun main(args: Array<String>) {
  print(rearrangeLastN(list(1, 2, 3, 4, 5), 2))
  print(rearrangeLastN(list(1, 2, 3, 4, 5), 3))
  print(rearrangeLastN(list(1, 2, 3, 4, 5), 4))
  print(rearrangeLastN(list(1, 2, 3, 4, 5), 5))
}