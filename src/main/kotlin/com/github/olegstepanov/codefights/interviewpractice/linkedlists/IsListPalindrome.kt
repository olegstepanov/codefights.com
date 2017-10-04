package com.github.olegstepanov.codefights.interviewpractice.linkedlists

import java.io.File
import java.util.*



//
fun isListPalindrome(l: ListNode<Int>?): Boolean {
    var n = l
    var len = 0

    while (n != null) {
        len++
        n = n.next
    }

    var i = 0
    n = l
    var p: ListNode<Int>? = null
    while (n != null) {
        i++

        val next = n.next
        if (i >= len.toDouble() / 2) {
            n.next = p
        }

        p = n
        n = next
    }

    var beg = l
    var end = p
    i = 0
    while (beg != null && end != null && i++ < len / 2) {
        if (beg.value != end.value)
            return false

        if (beg.next === end || beg.next === end.next)
            break

        beg = beg.next
        end = end.next
    }

    return true
}

fun list(vararg args: Int): ListNode<Int>? {
    var head: ListNode<Int>? = null
    var tail = head
    for (arg in args) {
        val node = ListNode(arg)
        if (tail == null) {
            head = node
            tail = node
        } else {
            tail.next = node
            tail = node
        }
    }

    return head
}

fun rand(len: Int, sym: Boolean): ListNode<Int>? {
    val rand = Random()
    var array = (0..len).map { rand.nextInt() }.toIntArray()
    if (sym)
       array += array.reversed()
    return list(*array)
}

fun main(args: Array<String>) {
    println(isListPalindrome(list(
            *File("/Users/olegstepanov/Downloads/test_20").readText().split(", ").map { it.trim().toInt() }.toIntArray())))

    println(isListPalindrome(list(
            1, 2, 3, 4, 5, 3, 2, 1
    )))


    println(isListPalindrome(list(
            1, 2, 3, 5, 5, 3, 2, 1
    )))
}