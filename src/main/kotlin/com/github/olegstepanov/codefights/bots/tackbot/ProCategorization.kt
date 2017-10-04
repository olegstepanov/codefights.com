package com.github.olegstepanov.codefights.bots.tackbot

fun proCategorization(pros: MutableList<String>, preferences: MutableList<MutableList<String>>): MutableList<MutableList<MutableList<String>>> =
        preferences
                .flatMap { it }.toSet()
                .map {
                  mutableListOf(mutableListOf(it),
                          pros
                                  .filterIndexed { index, _ -> preferences[index].contains(it) }
                                  .toMutableList())
                }.toMutableList().apply { sortBy { it[0][0] } }
