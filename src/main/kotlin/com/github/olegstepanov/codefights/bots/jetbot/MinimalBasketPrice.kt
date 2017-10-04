package com.github.olegstepanov.codefights.bots.jetbot

fun minimalBasketPrice(maxPrice: Int, vendorsDelivery: MutableList<Int>, vendorsProducts: MutableList<MutableList<Int>>): MutableList<Int> {
  val productsCount = vendorsProducts[0].size
  val chosenVendors = IntArray(productsCount) { -1 }
  var index = 0

  var minVendors: IntArray? = null

  fun delivery(vendors: IntArray = minVendors!!) = vendors.map { vendorsDelivery[it] }.max()!!
  fun chosenPrice() = chosenVendors.asSequence().take(index).mapIndexed { index, vendor -> vendorsProducts[vendor][index] }.sum()

  main@ do {
    val chosenPrice = chosenPrice()
    for (i in chosenVendors[index] + 1 until vendorsProducts.size) {
      val productPrice = vendorsProducts[i][index]
      if (productPrice >= 0 && productPrice + chosenPrice <= maxPrice) {
        chosenVendors[index] = i
        if (index == productsCount - 1) {
          if (minVendors == null || delivery(chosenVendors) < delivery(minVendors))
            minVendors = chosenVendors.copyOf()
        } else {
          index++
          continue@main
        }
      }
    }
    chosenVendors[index] = -1
    index--
  } while (index >= 0)

  return minVendors!!.toSet().toMutableList()
}

fun main(args: Array<String>) {
  print(minimalBasketPrice(7, mutableListOf(5, 4, 2, 3),
          mutableListOf(mutableListOf(1,1,1),
          mutableListOf(3,-1,3),
          mutableListOf(-1,2,2),
          mutableListOf(5,-1,-1))))
  print(minimalBasketPrice(0, (0..99).map { 0 }.toMutableList(),
          (0..99).map { (0..99).map { 0 }.toMutableList() }.toMutableList()))
}
