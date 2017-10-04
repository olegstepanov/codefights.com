package com.github.olegstepanov.codefights.interviewpractice.hashtables

import java.util.*

fun groupingDishes(dishes: MutableList<MutableList<String>>): MutableList<MutableList<String>> {
    val map = TreeMap<String, MutableList<String>>()
    for (dish in dishes) {
        val name = dish.first()
        for (ingredient in dish.asIterable().drop(1)) {
            map.getOrPut(ingredient, { mutableListOf() }).add(name)
        }
    }

    return map
            .filter { it.value.size > 1 }
            .map { mutableListOf(it.key)
                    .apply { addAll(it.value.sorted()) } }.toMutableList()
}
