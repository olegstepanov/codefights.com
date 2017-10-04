package com.github.olegstepanov.codefights.bots.gobot

fun domainForwarding(redirects: MutableList<MutableList<String>>): MutableList<MutableList<String>> {
  val map = redirects.map { Pair(it[0], it[1]) }.toMap()
  val finalMap = mutableMapOf<String, MutableList<String>>()
  for (key in map.keys) {
    var domain = key
    while (true) {
      val next = map.get(domain)
      if (next == null) {
        finalMap.getOrPut(domain, { mutableListOf() }).add(key)
        break
      } else
        domain = next
    }
  }

  return finalMap.entries.sortedBy { it.key }.map { mutableListOf(it.key, *it.value.toTypedArray()).apply { sort() } }.toMutableList()
}

fun main(args: Array<String>) {
  println(domainForwarding(mutableListOf(mutableListOf("godaddy.net", "godaddy.com"),
          mutableListOf("godaddy.org", "godaddycares.com"),
          mutableListOf("godady.com", "godaddy.com"),
          mutableListOf("godaddy.ne", "godaddy.net"))))
}
