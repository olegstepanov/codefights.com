package com.github.olegstepanov.codefights.bots.instabot

fun busyHolidays(shoppers: MutableList<MutableList<String>>,
                 orders: MutableList<MutableList<String>>,
                 leadTime: MutableList<Int>): Boolean {

  fun odersShoppers(shoppers: MutableList<MutableList<String>>, orders: MutableList<MutableList<String>>, leadTime: MutableList<Int>): Array<MutableList<Int>> {
    fun intTime(t: String) = t.filter { it != ':' }.toInt()
    fun addTime(time: Int, minutes: Int): Int {
      var newMinutes = time % 100 + minutes
      var newHours = time / 100
      when {
        newMinutes < 0 -> {
          newHours += Math.floor(newMinutes.toDouble() / 60).toInt()
          newMinutes = newMinutes % 60 + 60
        }
        newMinutes > 60 -> {
          newHours += newMinutes / 60
          newMinutes %= 60
        }
      }
      return newHours * 100 + newMinutes
    }

    fun intersectionLength(a: IntRange, b: IntRange): Int {
      val (a1, a2) = Pair(a.start, a.endInclusive)
      val (b1, b2) = Pair(b.start, b.endInclusive)

      fun lenInMunutes(t1: Int, t2: Int) =
              (t2 / 100 - t1 / 100) * 60 + (t2 % 100 - t1 % 100)

      fun len(p: Int, r: IntRange, firstPoint: Boolean): Int =
              when (p in r) {
                false -> -1
                true ->
                  when (firstPoint) {
                    true -> lenInMunutes(p, r.endInclusive)
                    false -> lenInMunutes(r.start, p)
                  }
              }

      return arrayOf(
              len(a1, b, true),
              len(a2, b, false),
              len(b1, a, true),
              len(b2, a, false)).max()!!
    }

    fun canDo(orderTime: IntRange, shopperTime: IntRange, leadTime: Int): Boolean {
      val intersectionLength = intersectionLength(orderTime, shopperTime)
//      println("orderTime = [${orderTime}], shopperTime = [${shopperTime}], leadTime = [${leadTime}]")
//      println("intersectionLength = ${intersectionLength}")
      return intersectionLength >= leadTime
    }

    val result = Array<MutableList<Int>>(orders.size) { mutableListOf() }

    val shopperTimes = shoppers.map { intTime(it[0])..intTime(it[1]) }.toTypedArray()

    for (i in 0 until orders.size) {
      val orderTime = intTime(orders[i][0])..intTime(orders[i][1])

      for (j in 0 until shoppers.size) {
        val shopperTime = shopperTimes[j]
        val canDo = canDo(orderTime, shopperTime, leadTime[i])

//        println("S: $shopperTime O: $orderTime T: $leadTime ${if (canDo) "Y" else "N"}")

        if (canDo)
          result[i].add(j)
      }
    }
    return result
  }

  fun hasDuplicates(a: IntArray): Boolean {
    val idx = IntArray(shoppers.size)
    for (i in a) {
      if (idx[i] > 0) return true
      idx[i] = 1
    }
    return false
  }

  val ordersShoppers = odersShoppers(shoppers, orders, leadTime)
  //println(ordersShoppers)

  ordersShoppers.sortBy { it.size }

  if (ordersShoppers.isEmpty() || ordersShoppers[0].size == 0)
    return false

  val orders = IntArray(ordersShoppers.size) { -1 }
  val shoppers = IntArray(shoppers.size)
  var index = 0

  loop@ while (index < ordersShoppers.size) {
    for (i in orders[index] + 1 until ordersShoppers[index].size) {
      val shopper = ordersShoppers[index][i]
      if (shoppers[shopper] == 0) {
        orders[index] = i
        shoppers[shopper] = 1
        index++
        continue@loop
      }
    }
    index--
    if (index < 0)
      return false
  }

  return true
}


fun main(args: Array<String>) {
  test1()
  test2()
  test3()
  test4()

  println(busyHolidays(mutableListOf(), mutableListOf(), mutableListOf()))

  println(busyHolidays(
          Array(100) {
            mutableListOf("00:00", "23:59")
          }.toMutableList(),
          Array(100) {
            mutableListOf("00:00", "23:59")
          }.toMutableList(),
          Array(100) {
            24 * 60
          }.toMutableList()
  ))
}

private fun test4() {
  println(busyHolidays(
          mutableListOf(
                  mutableListOf("23:00", "23:59"),
                  mutableListOf("22:30", "23:30")
          ),
          mutableListOf(
                  mutableListOf("23:15", "23:35"),
                  mutableListOf("23:00", "23:31")
          ),
          mutableListOf(20, 31)))
}

private fun test3() {
  println(busyHolidays(
          mutableListOf(
                  mutableListOf("23:00", "23:59"),
                  mutableListOf("22:30", "23:30")
          ),
          mutableListOf(
                  mutableListOf("23:15", "23:35"),
                  mutableListOf("23:00", "23:31")
          ),
          mutableListOf(20, 30)))
}

private fun test2() {
  println(busyHolidays(
          mutableListOf(
                  mutableListOf("15:10", "16:00"),
                  mutableListOf("17:40", "22:30")
          ),
          mutableListOf(
                  mutableListOf("17:30", "18:00"),
                  mutableListOf("15:00", "15:45")
          ),
          mutableListOf(15, 30)))
}

private fun test1() {
  println(busyHolidays(
          mutableListOf(
                  mutableListOf("15:10", "16:00"),
                  mutableListOf("17:50", "22:30"),
                  mutableListOf("13:00", "14:40")
          ),
          mutableListOf(
                  mutableListOf("14:30", "15:00")
          ),
          mutableListOf(15)))
}
