package com.github.olegstepanov.codefights.interviewpractice.backtracking

fun nQueens(n: Int): MutableList<MutableList<Int>> {
  val solution = IntArray(n) { -1 }
  val solutions = mutableListOf<MutableList<Int>>()

  fun dontAttack(s: Int): Boolean {
    fun _fun(): Boolean {
      for (i in 0..s) {
        for (j in 0..s) {
          if (j >= i) continue
          if (solution[i] == solution[j])
            return false
          if (i - j == Math.abs(solution[i] - solution[j]))
            return false
        }
      }

      return true
    }

    val ret = _fun()
//    println("${solution.slice(0..s)} : $ret")
    return ret
  }

  main@ while (true) {
    val index = solution.indexOfFirst { it == -1 }

    if (index < 0)
      break

    val nextQueen = (1..n).firstOrNull { solution[index] = it; dontAttack(index) }

    if (nextQueen != null) {
      solution[index] = nextQueen

      if (index != n - 1)
        continue@main
      else {
        solutions.add(solution.toMutableList())
      }
    }

    for (i in n - 1 downTo 0) {
      val nextNextQueen = (1..n).firstOrNull {
        when {
          it <= solution[i] -> false
          else -> {
            solution[i] = it
            dontAttack(i)
          }
        }
      }
      if (nextNextQueen != null) {
        solution[i] = nextNextQueen
        break
      } else {
        if (i == 0)
          break@main

        solution[i] = -1
      }
    }
  }

  return solutions
}


fun main(args: Array<String>) {
  println(nQueens(1))
  println(nQueens(4))
  println(nQueens(2))
  println(nQueens(3))
  println(nQueens(6))
}