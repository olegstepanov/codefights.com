package com.github.olegstepanov.codefights.bots.wizelinebot

import java.util.Comparator

fun roadmap(tasks: MutableList<MutableList<String>>, queries: MutableList<MutableList<String>>): MutableList<MutableList<String>> {
  data class Date(val year: Int, val month: Int, val day: Int) {
    fun less(b: Date) =
            this.year < b.year ||
                    ((this.year == b.year) && (
                            this.month < b.month ||
                                    (this.month == b.month && this.day < b.day)
                            ))

    fun between(start: Date, end: Date): Boolean {
      return !(this.less(start) || end.less(this))
    }
  }

  fun date(s: String) = with(s.split('-').map { it.toInt() }) {
    Date(this[0], this[1], this[2])
  }

  data class Task(val title: String, val start: Date, val end: Date, val people: List<String>)

  val tasks = tasks.map { Task(it[0], date(it[1]), date(it[2]), it.drop(3)) }
  val result = mutableListOf<MutableList<String>>()

  for (query in queries) {
    val name = query[0]
    val date = date(query[1])

    val matchingTasks =
            tasks
                    .filter { it.people.contains(name) && date.between(it.start, it.end) }
                    .sortedWith(Comparator { o1, o2 ->
                      val end1 = o1!!.end
                      val end2 = o2!!.end

                      when {
                        end1 == end2 -> o1.title.compareTo(o2.title)
                        end1.less(end2) -> -1
                        else -> 1
                      }
                    })

    result.add(matchingTasks.map { it.title }.toMutableList())
  }

  return result
}
