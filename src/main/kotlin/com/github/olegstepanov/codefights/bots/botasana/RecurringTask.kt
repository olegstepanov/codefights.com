package com.github.olegstepanov.codefights.bots.botasana

import java.util.*

fun recurringTask(firstDate: String, k: Int, daysOfTheWeek: MutableList<String>, n: Int): MutableList<String> {
  val weekdayNames = listOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
  val daysInMonth = listOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
  var daysOfTheWeekIndexes = daysOfTheWeek.map { weekdayNames.indexOf(it) }.sorted()

  var (day, month, year) = with(firstDate.split('/').map { it.toInt() }) {
    Triple(this[0], this[1], this[2])
  }

  fun leap(year: Int) = ((year % 4 == 0) && (year % 100) != 0) || (year % 400 == 0)
  fun daysinmonth(m: Int) = if (leap(year) && m == 2) 29 else daysInMonth[m - 1]
  fun dayofyear() = (1..month - 1).map { daysinmonth(it) }.sum() + day - 1
  fun daysinceepoch() = (1900 until year).map { if (leap(it)) 366 else 365 }.sum() + dayofyear()
  fun dayofweek() = (daysinceepoch() + 1) % 7

  fun addday() {
    if (day < daysinmonth(month)) {
      day++
      return
    }
    day = 1
    if (month < 12) {
      month++
      return
    }
    month = 1
    year++
  }

  val result = mutableListOf<String>()

  fun <T> rot(list: List<T>) = if (list.size < 2) list else list.drop(1) + list[0]

  while (dayofweek() != daysOfTheWeekIndexes.first())
    daysOfTheWeekIndexes = rot(daysOfTheWeekIndexes)

  main@ while (result.size < n) {
    fun format2(n: Int) = if (n >= 10) "$n" else "0$n"

    result.add("${format2(day)}/${format2(month)}/$year")

    if (dayofweek() == daysOfTheWeekIndexes.last()) {
      do {
        addday()
      } while (dayofweek() != daysOfTheWeekIndexes.first())
      var n = 1
      while (n++ < k)
        repeat(7, { addday() })
    } else {
      do {
        addday()
      } while (!daysOfTheWeekIndexes.contains(dayofweek()))
    }
  }

  return result
}

fun main(args: Array<String>) {
  println(recurringTask("23/02/2000", 2, mutableListOf("Wednesday",
          "Friday"), 4))
  println(recurringTask("01/01/2015", 2, mutableListOf("Monday",
          "Thursday"), 4))
  println(recurringTask("01/02/2100", 4, mutableListOf("Sunday",
          "Monday"), 4))
}