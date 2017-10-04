package com.github.olegstepanov.codefights.bots.dropbot

fun losslessDataCompression(inputString: String, width: Int): String {
  val s = inputString.toCharArray()

  var i = 0
  val result = StringBuilder()
  while (i < s.size) {
    var length = 0
    var startIndex = -1
    for (j in maxOf(0, i - width) until i) {
      var k = 0
      while (j + k < i && i + k < s.size && s[j + k] == s[i + k])
        k++
      if (k > length) {
        length = k
        startIndex = j
      }
    }
    if (length > 0) {
      result.append("($startIndex,$length)")
      i += length
    } else {
      result.append(s[i])
      i++
    }
  }

  return result.toString()
}

fun main(args: Array<String>) {
  println(losslessDataCompression("abacabadabacaba", 7))
  println(losslessDataCompression("abacabadabacaba", 8))
  println(losslessDataCompression("y", 1))
  println(losslessDataCompression("codefights", 20))
}
