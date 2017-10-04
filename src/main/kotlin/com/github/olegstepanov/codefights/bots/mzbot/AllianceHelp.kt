package com.github.olegstepanov.codefights.bots.mzbot

fun allianceHelp(t: Int, allianceSize: Int): Int = maxOf(0, t - minOf(allianceSize, 10) * maxOf(60, t / 10))

fun main(args: Array<String>) {
  println(allianceHelp(1000, 10))
  println(allianceHelp(999, 11))


}