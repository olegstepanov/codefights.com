package com.github.olegstepanov.codefights.bots.mzbot

fun allianceVersusMonster(healthPoints: MutableList<Int>, attackDamage: MutableList<Int>): Int {
  var monsterHealth = healthPoints[0]
  val monsterDamage = attackDamage[0]

  class Warrior(var health: Int, val attackDamage: Int)

  val warrirors = healthPoints.zip(attackDamage).drop(1).map { Warrior(it.first, it.second) }.toMutableList()

  while (monsterHealth > 0 && warrirors.size > 0) {
    var warrior = warrirors.filter { it.health > monsterDamage }.maxBy { it.attackDamage }
    if (warrior == null)
      warrior = warrirors.maxBy { it.attackDamage }!!
    print("$monsterHealth x ${warrior.health} -> ")
    var battles = maxOf(minOf(monsterHealth / warrior.attackDamage, warrior.health / monsterDamage), 1)
    if (warrior.health - monsterDamage * battles == 0 && battles > 1 && warrirors.size > 1)
      battles--
    monsterHealth -= warrior.attackDamage * battles
    warrior.health -= monsterDamage * battles
    println("$monsterHealth x ${warrior.health}")
    if (warrior.health <= 0 && monsterHealth > 0)
      warrirors.remove(warrior)
  }

  return warrirors.size
}

fun main(args: Array<String>) {
  println(allianceVersusMonster(mutableListOf(110, 30, 50), mutableListOf(12, 11, 20)))
  println(allianceVersusMonster(mutableListOf(4, 10, 10, 10), mutableListOf(10, 1, 1, 1)))
  println(allianceVersusMonster(mutableListOf(2000000000, 2000000000), mutableListOf(1, 1)))
}