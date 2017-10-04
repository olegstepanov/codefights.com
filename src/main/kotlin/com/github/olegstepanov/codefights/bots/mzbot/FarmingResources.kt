package com.github.olegstepanov.codefights.bots.mzbot

fun farmingResources(friendlyTroops: MutableList<Int>, enemyTroops: MutableList<Int>, loggingCamp: MutableList<Int>, impassableCells: MutableList<MutableList<Int>>): Boolean {
  fun findDistance(from: List<Int>, to: List<Int>): Int {
    val map = Array(43) { IntArray(43) { Int.MAX_VALUE } }

    map[from[0] + 21][from[1] + 21] = 0
    for (impassableCell in impassableCells) {
      map[impassableCell[0] + 21][impassableCell[1] + 21] = -1
    }

    var changed = false
    do {
      changed = false
      fun adjacentCells(cell: List<Int>) =
              listOf(
                      listOf(cell[0], cell[1] - 1),
                      listOf(cell[0] + 1, cell[1] - 1),
                      listOf(cell[0] - 1, cell[1]),
                      listOf(cell[0] + 1, cell[1]),
                      listOf(cell[0] - 1, cell[1] + 1),
                      listOf(cell[0], cell[1] + 1)
              )

      for (i in -20..20)
        for (j in -20..20) {
          if (map[i + 21][j + 21] in 0 until Int.MAX_VALUE) {
            for (adjacentCell in adjacentCells(listOf(i, j))) {
              if (map[adjacentCell[0] + 21][adjacentCell[1] + 21] > map[i + 21][j + 21] + 1) {
                map[adjacentCell[0] + 21][adjacentCell[1] + 21] = map[i + 21][j + 21] + 1
                changed = true
              }
            }
          }
        }
    } while (changed)

    return map[to[0] + 21][to[1] + 21]
  }

  val friendlyDistance = findDistance(friendlyTroops, loggingCamp)
  val enemyDistance = findDistance(enemyTroops, loggingCamp)
  println(friendlyDistance)
  println(enemyDistance)

  return friendlyDistance * friendlyTroops[2] < enemyDistance * enemyTroops[2]
}

fun main(args: Array<String>) {
  println(farmingResources(mutableListOf(-2, 2, 3), mutableListOf(1, 0, 9), mutableListOf(0, 0), mutableListOf()))
  println(farmingResources(mutableListOf(-2, 2, 3), mutableListOf(1, 0, 9), mutableListOf(0, 0), mutableListOf(mutableListOf(-1, 1))))
}



