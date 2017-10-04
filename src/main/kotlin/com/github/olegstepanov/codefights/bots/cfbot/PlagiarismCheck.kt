package com.github.olegstepanov.codefights.bots.cfbot

fun plagiarismCheck(code1: MutableList<String>, code2: MutableList<String>): Boolean {
  if (code1.size != code2.size) return false

  fun isIdentifierChar(c: Char) = c.isLetterOrDigit() || c == '_'
  fun isFirstIdentifierChar(c: Char) = isIdentifierChar(c) && !c.isDigit()

  val map = mutableMapOf<String, String>()
  for (i in 0 until code1.size) {
    val line1 = code1[i]
    val line2 = code2[i]

    var j1 = 0
    var j2 = 0
    while (j1 < line1.length && j2 < line2.length) {
      if (isFirstIdentifierChar(line1[j1]) && isFirstIdentifierChar(line2[j2])) {
        val savedJ1 = j1
        while (j1 < line1.length && isIdentifierChar(line1[j1]))
          j1++
        val savedJ2 = j2
        while (j2 < line2.length && isIdentifierChar(line2[j2]))
          j2++

        val fragment1 = line1.substring(savedJ1, j1)
        val fragment2 = line2.substring(savedJ2, j2)

        if (map.getOrPut(fragment1, { fragment2 }) != fragment2)
          return false
      } else if (line1[j1] == line2[j2]) {
        j1++
        j2++
      } else
        return false
    }

    if (j1 < line1.length != j2 < line2.length)
      return false
  }

  return true
}

fun main(args: Array<String>) {
  println(plagiarismCheck(mutableListOf("def is_even_sum(a, b):",
          "    return (a + b) % 2 == 0"), mutableListOf("def is_even_sum(summand_1, summand_2):",
          "    return (summand_1 + summand_2) % 2 == 0")))
}
