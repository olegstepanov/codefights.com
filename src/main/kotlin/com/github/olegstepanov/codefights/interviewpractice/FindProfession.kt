package com.github.olegstepanov.codefights.interviewpractice

fun findProfession(level: Int, pos: Int): String = when (level) {
    1 -> "Engineer"
    else -> when (findProfession(level - 1, (pos - 1) / 2 + 1)) {
        "Engineer" -> arrayOf("Engineer", "Doctor")[(pos - 1) % 2]
        "Doctor" -> arrayOf("Doctor", "Engineer")[(pos - 1) % 2]
        else -> ""
    }
}

fun main(args: Array<String>) {
    println(findProfession(3, 3))
    println(findProfession(30, 163126329))
}

