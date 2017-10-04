package com.github.olegstepanov.codefights.bots.dropbot

fun incorrectPasscodeAttempts(passcode: String, attempts: MutableList<String>): Boolean =
        attempts.map { it == passcode }.fold(Pair(false, 0), { acc, b ->
          when (b) {
            true -> Pair(acc.first, 0)
            false -> Pair(acc.first || acc.second + 1 >= 10, acc.second + 1)
          }
        }).first

fun main(args: Array<String>) {
  print(incorrectPasscodeAttempts("1111", mutableListOf("1111",
          "4444",
          "9999",
          "3333",
          "8888",
          "2222",
          "7777",
          "0000",
          "6666",
          "7285",
          "5555",
          "1111")))
}
