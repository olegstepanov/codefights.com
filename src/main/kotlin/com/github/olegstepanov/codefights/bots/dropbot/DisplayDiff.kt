package com.github.olegstepanov.codefights.bots.dropbot

fun displayDiff(oldVersion: String, newVersion: String): String {
  val result = StringBuilder()

  var o = 0
  while (o < oldVersion.length && o < newVersion.length && oldVersion[o] == newVersion[o]) o++
  result.append(oldVersion.substring(0, o))

  if (o < oldVersion.length && o < newVersion.length) {
    var ki = o
    while (ki < oldVersion.length && oldVersion[ki] != newVersion[o])
      ki++
    var kj = o
    while (kj < newVersion.length && newVersion[kj] != oldVersion[o])
      kj++

    val v1 = displayDiff(oldVersion.substring(ki), newVersion.substring(o))
    val v2 = displayDiff(oldVersion.substring(o), newVersion.substring(kj))

    val v1f = v1.filter { it !in "[()]" }
    val v2f = v2.filter { it !in "[()]" }

    if (v1f.length + ki < v2f.length + kj || v1f.length + ki == v2f.length + kj && oldVersion[o] < newVersion[o]) {
      result.append("(${oldVersion.substring(o, ki)})$v1")
    } else {
      result.append("[${newVersion.substring(o, kj)}]$v2")
    }
  }
  else {
    if (o < oldVersion.length)
      result.append("(${oldVersion.substring(o)})")
    else if (o < newVersion.length)
      result.append("[${newVersion.substring(o)}]")
  }

  println("oldVersion = [${oldVersion}], newVersion = [${newVersion}], result = [${result.toString()}]")
  return result.toString()
}

fun main(args: Array<String>) {
  println(displayDiff("a2_3b42c_78d", "a_34c27_8ed"))
  println(displayDiff("a", "b"))
  println(displayDiff(
          "same_prefix_1233_same_suffix",
          "same_prefix23123_same_suffix"))
}
