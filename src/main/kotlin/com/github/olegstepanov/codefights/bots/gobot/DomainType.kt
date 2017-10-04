package com.github.olegstepanov.codefights.bots.gobot

fun domainType(domains: MutableList<String>): MutableList<String> =
        domains.map { when (it.substringAfterLast('.')) {
          "com" -> "commercial"
          "org" -> "organization"
          "net" -> "network"
          "info" -> "information"
          else -> ""
        } }.toMutableList()
