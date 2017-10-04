package com.github.olegstepanov.codefights.bots.jetbot

fun packageBoxing(pkg: MutableList<Int>, boxes: MutableList<MutableList<Int>>): Int {
  pkg.sort()
  boxes.forEach { it.sort() }

  val indexedBoxes =
          (0 until boxes.size)
                  .zip(boxes)
                  .sortedBy { (_, dimensions) -> dimensions.fold(1, { acc, i -> acc * i }) }

  return indexedBoxes
          .firstOrNull{
            (_, dimensions) ->
              dimensions.zip(pkg).all { (box, pkg) -> box >= pkg } }?.first ?: -1
}


