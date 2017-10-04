package com.github.olegstepanov.codefights.bots.cfbot

fun taskMaker(source: MutableList<String>, challengeId: Int): MutableList<String> {
  val challengePrefix = "//DB $challengeId//"

  fun isSpecialLine(s: String) = s.trimStart().startsWith("//DB")

  var i = 0
  while (i < source.size) {
    if (i < source.size - 1 && !isSpecialLine(source[i]) && isSpecialLine(source[i + 1]))
      source.removeAt(i)
    else {
      when {
        source[i].trimStart().startsWith(challengePrefix) -> source[i] = source[i].substringBefore(challengePrefix) + source[i].substringAfter(challengePrefix)
        isSpecialLine(source[i]) -> source.removeAt(i)
        else -> {}
      }
      i++
    }
  }

  return source
}

fun main(args: Array<String>) {
  println(taskMaker(mutableListOf("ans = 0",
          "for i in range(n):",
          "    for j in range(n):",
          "    //DB 3//for j in range(1, n):",
          "    //DB 2//for j in rafnge(n + 1):",
          "        ans += 1",
          "return ans"), 3))
}