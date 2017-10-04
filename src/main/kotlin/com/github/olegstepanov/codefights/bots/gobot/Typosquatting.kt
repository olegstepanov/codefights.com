package com.github.olegstepanov.codefights.bots.gobot

fun typosquatting(n: Int, domain: String): Int {
  val domains = mutableSetOf<String>(domain)

  fun typos(s: String) =
            (1 until s.length).filter { s[it] != '.' && s[it - 1] != '.' }.map { String(s.toCharArray().apply {
              val t = this[it - 1]
              this[it - 1] = this[it]
              this[it] = t
            }) }

  var options: List<String> = listOf(domain)
  var k = 0
  var added = true
  var stopRaisingK = false
  while (added) {
    added = false
    for (option in options) {
      for (typo in typos(option))
        if (domains.add(typo))
          added = true
    }
    options = domains.toList()
    if (domains.size > n + 1) {
      stopRaisingK = true
      break
    }
    if (!stopRaisingK)
      k++
//    println("$k: ${domains.size} $added")
  }

//  println(domains)

  return if (!stopRaisingK) -1 else k
}

fun main(args: Array<String>) {
  println(typosquatting(7, "godaddy.com"))
  println(typosquatting(9, "omg.tv"))
  println(typosquatting(9, "aaa.aaa"))
  println(typosquatting(0, "aaa.aaa"))
  println(typosquatting(85, "godaddy.godaddy.com"))
}