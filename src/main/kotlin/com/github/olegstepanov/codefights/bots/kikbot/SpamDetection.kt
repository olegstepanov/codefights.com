package com.github.olegstepanov.codefights.bots.kikbot

fun spamDetection(messages: MutableList<MutableList<String>>, spamSignals: MutableList<String>): MutableList<String> {
  var matchPattern1 = 0
  var matchPattern4 = 0
  val signalsFound = mutableSetOf<String>()

  val messagesTo = mutableMapOf<Int, MutableList<String>>()

  for ((text, to) in messages) {
    val words = text.split(*text.filter { !it.isLetter() }.toCharArray())
    if (words.size < 5)
      matchPattern1++

    messagesTo.getOrPut(to.toInt(), { mutableListOf() }).add(text)

    val foundSignals = spamSignals.filter { signal -> words.any { it.equals(signal, true) } }
    signalsFound.addAll(foundSignals)
    if (foundSignals.isNotEmpty())
      matchPattern4++
  }


  val recipientsWithSameMessages = messagesTo.filter { msgTo ->
    msgTo.value.size >= 2 && msgTo.value.groupBy { it }.any { it.value.size > 0.5 * msgTo.value.size }
  }

  val sameMessages = messages.groupBy { it[0] }.filter { it.value.size > 0.5 * messages.size }.map { it.key }

  fun reduce(num: Int, denom: Int) : Pair<Int, Int> {
    for (i in 2..num) {
      if (num % i == 0 && denom % i == 0)
        return reduce(num / i, denom / i)
    }

    return Pair(num, denom)
  }

  return mutableListOf(
          if (matchPattern1 <= 0.9 * messages.size) "passed" else "failed: ${reduce(matchPattern1, messages.size).let { "${it.first}/${it.second}" }}",
          if (recipientsWithSameMessages.isEmpty()) "passed" else "failed: ${recipientsWithSameMessages.map { it.key.toString() }.joinToString(separator = " ")}",
          if (messages.size < 2 || sameMessages.isEmpty()) "passed" else "failed: ${sameMessages.joinToString(separator = " ")}",
          if (matchPattern4 <= 0.5 * messages.size) "passed" else "failed: ${signalsFound.sorted().joinToString(separator = " ")}"
  )
}
