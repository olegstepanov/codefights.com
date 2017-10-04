package com.github.olegstepanov.codefights.bots.uberbot

fun perfectCity(departure: MutableList<Double>, destination: MutableList<Double>): Double {
  var distance = 0.0
  if (Math.floor(departure[0]) != Math.floor(destination[0]))
    distance += Math.abs(departure[0] - destination[0])
  else
    distance += Math.min(departure[0] % 1 + destination[0] % 1, 1 - departure[0] % 1 + 1 - destination[0] % 1)

  if (Math.floor(departure[1]) != Math.floor(destination[1]))
    distance += Math.abs(departure[1] - destination[1])
  else
    distance += Math.min(departure[1] % 1 + destination[1] % 1, 1 - departure[1] % 1 + 1 - destination[1] % 1)

  return distance
}

fun main(args: Array<String>) {
  println(perfectCity(mutableListOf(0.4, 1.0), mutableListOf(0.9, 3.0)))
}