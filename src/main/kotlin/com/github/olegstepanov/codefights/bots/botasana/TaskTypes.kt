package com.github.olegstepanov.codefights.bots.botasana

fun tasksTypes(deadlines: MutableList<Int>, day: Int): MutableList<Int> =
        with (deadlines.map {
          when {
            it <= day -> 1
            it in day + 1..day + 7 -> 2
            else -> 3
          }
        }) {
          return mutableListOf(count { it == 1 }, count { it == 2 }, count { it == 3 })
        }
