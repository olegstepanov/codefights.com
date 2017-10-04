package com.github.olegstepanov.codefights.interviewpractice.commontechniquesbasic

fun containsDuplicates(a: MutableList<Int>): Boolean {
  a.sort()
  if (a.size <= 1) return false
  for (i in 1 until a.size)
    if (a[i] == a[i - 1])
      return true
  return false
}
