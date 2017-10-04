package com.github.olegstepanov.codefights.bots.wizelinebot

fun chatBot(conversations: MutableList<MutableList<String>>, currentConversation: MutableList<String>): MutableList<String> {
  val conversationPair = conversations.map { Pair(it, it.toSet().count { currentConversation.contains(it) }) }.maxBy { it.second }

  if (conversationPair != null && conversationPair.second > 0) {
    val conversation = conversationPair.first
    val (index, b) = conversation.mapIndexed { index, s -> Pair(index, currentConversation.contains(s)) }.last { it.second }
    currentConversation.addAll(conversation.drop(index + 1))
  }

  return currentConversation
}

fun main(args: Array<String>) {
  println(chatBot(mutableListOf(mutableListOf("where","are","you","live","i","live","in","new","york"),
  mutableListOf("are","you","going","somewhere","tonight","no","i","am","too","tired","today"),
  mutableListOf("hello","what","is","your","name","my","name","is","john")),
          mutableListOf("hello",
                  "john",
                  "do",
                  "you",
                  "have",
                  "a",
                  "favorite",
                  "city",
                  "to",
                  "live",
                  "in",
                  "yes",
                  "it",
                  "is")))
}