package com.github.olegstepanov.codefights.interviewpractice.linkedlists

data class ListNode<T>(var value: T) {
    var next: ListNode<T>? = null;
}

//
fun addTwoHugeNumbers(a: ListNode<Int>?, b: ListNode<Int>?): ListNode<Int>? {
    fun rev(n: ListNode<Int>?): ListNode<Int>? {
        var n = n
        var p: ListNode<Int>? = null

        while (n != null) {
            val next = n.next
            n.next = p
            p = n
            n = next
        }

        return p
    }

    var a = rev(a)
    var b = rev(b)
    var rem = 0
    var c: ListNode<Int>? = null

    while (a != null || b != null) {
        val vala = a?.value ?: 0
        val valb = b?.value ?: 0
        val valc = vala + valb + rem
        rem = if (valc >= 10000) 1 else 0
        println("$vala $valb $valc")

        c = ListNode(valc % 10000).apply { next = c }
        a = a?.next
        b = b?.next
    }

    if (rem > 0)
        c = ListNode(rem).apply { next = c }

    return c
}

fun print(v: ListNode<Int>?) {
    var v = v
    while (v != null) {
        print("${v.value} ")
        v = v.next
    }
    println()
}

fun main(args: Array<String>) {
  print(addTwoHugeNumbers(list(123, 4, 5), list(9000, 9000, 3212)))
}