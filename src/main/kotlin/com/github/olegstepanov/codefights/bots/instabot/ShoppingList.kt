package com.github.olegstepanov.codefights.bots.instabot

fun shoppingList(items: String): Double {
  val digits = CharArray(10) { '0' + it }
  var items = items
  var sum = 0.0
  var index = 0

  while (index < items.length) {
    val priceStart = items.indexOfAny(digits, index)
    if (priceStart < 0)
      break
    index = priceStart
    var decimalPoint = -1
    loop@while (index < items.length) {
      when (items[index]) {
        '.' -> decimalPoint = index
        in '0'..'9' -> {}
        else -> { break@loop }
      }
      if (decimalPoint >= 0 && index - decimalPoint > 2)
        break
      index++
    }
    sum += items.substring(priceStart, index).toDouble()
  }

  return sum
}

fun main(args: Array<String>) {
  println(shoppingList("Doughnuts, 4; doughnuts holes, 0.08; glue, 3.4"))
  println(shoppingList("blue suit for 24$, cape for 12.99$ & glasses for 15.70"))
  println(shoppingList(""))
}
