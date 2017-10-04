package com.github.olegstepanov.codefights.interviewpractice.backtracking

fun wordBoggle(board: MutableList<MutableList<Char>>, words: MutableList<String>): MutableList<String> {
  val deltas = arrayOf(
          Pair(-1, -1), Pair(-1, 0), Pair(-1, 1),
          Pair(0, -1), Pair(0, 1),
          Pair(1, -1), Pair(1, 0), Pair(1, 1)
  )

  val found = mutableListOf<String>()

  fun search(i: Int, j: Int, word: String, index: Int, visited: MutableList<Array<Int>>): Boolean {
    visited.add(arrayOf(i, j))

    val letter = word[index]

    main@ for ((first, second) in deltas) {
      val ni = i + first
      val nj = j + second

      if (ni < 0 || nj < 0 || ni >= board.size || nj >= board[0].size)
        continue

      for (k in 0 until visited.size)
        if (visited[k][0] == ni && visited[k][1] == nj)
          continue@main

      if (board[ni][nj] == letter) {
        if (index == word.length - 1) {
          return true
        }

        if (search(ni, nj, word, index + 1, visited))
          return true
        else
          visited.removeAt(visited.size - 1)
      }
    }

    return false
  }

  for (i in 0 until board.size)
    for (j in 0 until board[i].size)
      for (word in words.toTypedArray())
        if (word.startsWith(board[i][j]))
          if (search(i, j, word, 1, mutableListOf())) {
            found.add(word)
            words.remove(word)
          }

  found.sort()
  return found
}

fun main(args: Array<String>) {
  println(wordBoggle(mutableListOf(
          "AAA".toCharArray().toMutableList(),
          "AAA".toCharArray().toMutableList(),
          "AAA".toCharArray().toMutableList()),
          mutableListOf("AAAAA",
                  "AA",
                  "AAA",
                  "AAAA", "AAAAAAAAA", "AAAAAAAAAA")))
  println(wordBoggle(mutableListOf(
          "GT".toCharArray().toMutableList(),
          "OA".toCharArray().toMutableList()),
          mutableListOf("TOGGLE",
                  "GOAT",
                  "GO",
                  "TOO")))
  println(wordBoggle(mutableListOf(), mutableListOf()))
}