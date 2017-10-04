package com.github.olegstepanov.codefights.bots.uberbot

fun parkingSpot(carDimensions: MutableList<Int>, parkingLot: MutableList<MutableList<Int>>, luckySpot: MutableList<Int>): Boolean {
  fun checkrect(rows: IntRange, columns: IntRange) = rows.all { i -> columns.all { j -> parkingLot[i][j] == 0 } }

  return checkrect(0..luckySpot[2], luckySpot[1]..luckySpot[3]) || checkrect(luckySpot[2] until parkingLot.size, luckySpot[1]..luckySpot[3]) ||
          checkrect(luckySpot[0]..luckySpot[2], 0..luckySpot[3]) || checkrect(luckySpot[0]..luckySpot[2], luckySpot[1] until parkingLot[0].size)
}

fun main(args: Array<String>) {
  println(parkingSpot(mutableListOf(3, 2), mutableListOf(
          mutableListOf(1, 0, 1, 0, 1, 0),
          mutableListOf(1, 0, 0, 0, 1, 0),
          mutableListOf(0, 0, 0, 0, 0, 1),
          mutableListOf(1, 0, 0, 0, 1, 1)), mutableListOf(1, 1, 2, 3)))
}