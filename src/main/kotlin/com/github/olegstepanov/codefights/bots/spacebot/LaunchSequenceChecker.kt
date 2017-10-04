package com.github.olegstepanov.codefights.bots.spacebot

fun launchSequenceChecker(systemNames: MutableList<String>, stepNumbers: MutableList<Int>): Boolean {
  val map = mutableMapOf<String, Int>()

  if (systemNames.isEmpty()) return false

  for (i in 0 until systemNames.size) {
    val systemName = systemNames[i]
    val stepNumber = stepNumbers[i]

    if (map.containsKey(systemName)) {
      if (map[systemName]!! >= stepNumber)
        return false
    }
    map.put(systemName, stepNumber)
  }

  return true
}

fun main(args: Array<String>) {
  println(launchSequenceChecker(mutableListOf("stage_1",
          "stage_2",
          "dragon",
          "stage_1",
          "stage_2",
          "dragon"), mutableListOf(1, 10, 11, 2, 12, 111)))
}