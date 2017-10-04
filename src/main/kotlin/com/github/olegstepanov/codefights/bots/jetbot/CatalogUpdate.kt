package com.github.olegstepanov.codefights.bots.jetbot

fun catalogUpdate(catalog: MutableList<MutableList<String>>, updates: MutableList<MutableList<String>>): MutableList<MutableList<String>> {
  main@ for (update in updates) {
    for ((index, category) in catalog.withIndex()) {
      if (category[0] > update[0]) {
        catalog.add(index, update)
        continue@main
      }
      if (category[0] == update[0]) {
        for (i in 1 until category.size)
          if (category[i] >= update[1]) {
            category.add(i, update[1])
            continue@main
          }
        category.add(update[1])
        continue@main
      }
    }
    catalog.add(update)
    continue@main
  }

  return catalog
}

fun main(args: Array<String>) {

}
