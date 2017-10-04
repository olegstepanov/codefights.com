package com.github.olegstepanov.codefights.bots.quorabot

fun mostViewedWriters(topicIds: MutableList<MutableList<Int>>, answerIds: MutableList<MutableList<Int>>, views: MutableList<MutableList<Int>>): MutableList<MutableList<MutableList<Int>>> {
  val answerTopics = topicIds
          .zip(answerIds)
          .flatMap { (topics, answers) ->
            answers.map { answer -> Pair(answer, topics) }
          }
          .toMap()

  val topics = topicIds.flatten().toSortedSet()

  val topicViews = sortedMapOf<Int, MutableMap<Int, Int>>()

  for ((answer, data) in views.map { Pair(it[0], Pair(it[1], it[2])) }) {
    val (author, answerViews) = data
    for (topic in answerTopics[answer]!!) {
      topicViews.getOrPut(topic, { mutableMapOf() }).compute(author, { _, v -> (v ?: 0) + answerViews })
    }
  }

  return topics
          .map { topicViews.getOrDefault(it, mutableMapOf()) }
          .map {
            it.toList()
                    .sortedByDescending { it.second }.take(10)
                    .map { (first, second) -> mutableListOf(first, second) }
                    .toMutableList()
          }.toMutableList()
}

fun main(args: Array<String>) {
  println(mostViewedWriters(mutableListOf(mutableListOf(5, 6, 81),
          mutableListOf(1, 3, 2),
          mutableListOf(10, 12, 34),
          mutableListOf(13, 14, 23, 43),
          mutableListOf(11, 22, 17)),
          mutableListOf(mutableListOf(1, 2, 3),
                  mutableListOf(),
                  mutableListOf(),
                  mutableListOf(4, 5, 6, 7),
                  mutableListOf(8, 9, 10, 11)),
          mutableListOf(mutableListOf(4, 18, 5),
                  mutableListOf(5, 23, 37),
                  mutableListOf(8, 1, 23),
                  mutableListOf(11, 18, 18),
                  mutableListOf(1, 7, 20),
                  mutableListOf(9, 239, 10),
                  mutableListOf(2, 239, 1),
                  mutableListOf(10, 18, 1),
                  mutableListOf(3, 239, 5),
                  mutableListOf(6, 169, 2),
                  mutableListOf(7, 800, 1))))
  println(mostViewedWriters(mutableListOf(), mutableListOf(), mutableListOf()))
}