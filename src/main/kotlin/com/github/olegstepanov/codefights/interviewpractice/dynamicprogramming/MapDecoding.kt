package com.github.olegstepanov.codefights.interviewpractice.dynamicprogramming

fun mapDecoding(message: String): Int {
  val decodings = IntArray(message.length + 1)

  for (i in 0..message.length) {
    fun singleDigit() = if (message[i - 1] != '0') decodings[i - 1] else 0

    decodings[i] = (when (i) {
      0 -> 1
      1 -> singleDigit()
      else -> {
        val singleDigit = singleDigit()
        when (message[i - 2]) {
          '1' -> decodings[i - 2] + singleDigit
          '2' -> if (message[i - 1] in '1'..'6') decodings[i - 2] + singleDigit else singleDigit
          else -> singleDigit
        }
      }
    }) % 1000000007
//    if (i > 0) println("${message.substring(0, i - 1)} -> ${decodings[i]}")
  }


  return decodings[message.length]
}

fun main(args: Array<String>) {
  println(mapDecoding("301"))
  println(mapDecoding("10122110"))
  println(mapDecoding(""))
  println(mapDecoding("11115112112"))
  println(mapDecoding("2871221111122261"))
  println(mapDecoding("1221112111122221211221221212212212111221222212122221222112122212121212221212122221211112212212211211"))
}