package com.github.olegstepanov.codefights.interviewpractice.trees.basic

class Trie(var value: String = "", var leaf: Boolean) {
  var children = mutableListOf<Trie>()

  fun print(indent: Int = 0) {
    print(" ".repeat(indent))
    println(value + if (leaf) "*" else "")
    for (child in children) {
      child.print(indent + 1)
    }
  }

  private fun length(): Int = value.length + (children.maxBy(Trie::length)?.length() ?: 0)

  fun sort() {
    children.sortByDescending(Trie::length)
    for (child in children) {
      child.sort()
    }
  }
}

fun findSubstrings(words: MutableList<String>, parts: MutableList<String>): MutableList<String> {
  val root = Trie(leaf = false)
  val result = mutableListOf<String>()

  for (part in parts) {
    var t = root
    var suffix = part

    main@ while (suffix.isNotEmpty()) {
      for (child in t.children) {
        val commonPrefix = suffix.commonPrefixWith(child.value)
        if (commonPrefix.isNotEmpty()) {
          if (commonPrefix.length == child.value.length) {
            t = child
          } else {
            val newT = Trie(commonPrefix, leaf = false)
            child.value = child.value.substring(commonPrefix.length)
            t.children.remove(child)
            t.children.add(newT)
            newT.children.add(child)
            t = newT
          }
          suffix = suffix.substring(commonPrefix.length)
          if (suffix.length == 0)
            t.leaf = true
          continue@main
        }
      }
      t.children.add(Trie(suffix, leaf = true))
      break@main
    }
  }

  root.sort()

  fun length(range: IntRange) = range.endInclusive - range.start

  // com.github.olegstepanov.codefights.basic.t.value has common prefix with word at index
  fun findLargestPart(word: String, index: Int, t: Trie): IntRange? {
    var largest1: IntRange? = null

    for (child in t.children) {
      if (word.startsWith(child.value, index + t.value.length)) {
        val largestInChild = findLargestPart(word, index + t.value.length, child)
        if (largestInChild != null) {
          if (largest1 != null && length(largest1) > length(largestInChild))
            continue
          largest1 = largestInChild
        }
      }
    }

    if (largest1 != null)
      largest1 = IntRange(largest1.start - t.value.length, largest1.endInclusive)
    else if (t.leaf)
      largest1 = IntRange(index, index + t.value.length - 1)

    return largest1
  }

  for (word in words) {
    var largest2: IntRange? = null

    for (child in root.children) {
      var index = -1
      do {
        index = word.indexOf(child.value, index + 1)
        if (index >= 0) {
          val largestInChild = findLargestPart(word, index, child)
          if (largestInChild != null) {
            if (largest2 != null) {
              if ((length(largest2) > length(largestInChild) ||
                      (length(largest2) == length(largestInChild) && largest2.start < largestInChild.start)))
                continue
            }
            largest2 = largestInChild
          }
        }
      } while (index >= 0)
    }

    var newWord = word
    if (largest2 != null)
      newWord = word.replaceRange(largest2, "[" + word.substring(largest2) + "]")

    result.add(newWord)
  }

  return result
}


fun main(args: Array<String>) {
  //com.github.olegstepanov.codefights.basic.com.github.olegstepanov.codefights.basic.test1()
  test5()
  //com.github.olegstepanov.codefights.basic.test10()
}

private fun test10() {
  println(findSubstrings(mutableListOf("Norris",
          "random",
          "bushmaster",
          "disposal",
          "territorial",
          "obstinate",
          "eigenstate",
          "momentum",
          "boatload",
          "citrus",
          "discriminatory",
          "miscible",
          "sadist",
          "nasty",
          "suey",
          "sacrilegious",
          "substrate",
          "doubleheader",
          "evade",
          "halocarbon",
          "backplate",
          "thespian",
          "folly",
          "sparling",
          "ambiance",
          "indeterminable",
          "gunny",
          "exorbitant",
          "protrude",
          "Bissau",
          "Korea",
          "salute",
          "Godfrey",
          "belief",
          "pate",
          "Mackey",
          "sing",
          "yaw",
          "raptor",
          "morass",
          "c",
          "Elisabeth",
          "mesmeric",
          "uproot",
          "buckle",
          "cannonball",
          "cataract",
          "already",
          "clout",
          "strategic",
          "down",
          "marvelous",
          "councilwomen",
          "FL",
          "billionth",
          "from",
          "concussion",
          "flimsy",
          "stuck",
          "reciprocate",
          "hellish",
          "feather",
          "papaw",
          "sobriety",
          "glow",
          "Wyatt",
          "Goodrich",
          "Preston",
          "tripe",
          "arum",
          "hologram",
          "tractor",
          "capsize",
          "McCracken",
          "trig",
          "Athens",
          "char",
          "experience",
          "psychopath",
          "prevail",
          "densitometer",
          "Mayflower",
          "event",
          "vacuole",
          "Hendrickson",
          "Edgerton",
          "instead",
          "twelfth",
          "palazzi",
          "passerby",
          "ball",
          "monastery",
          "courtesy",
          "coverage",
          "picayune",
          "besmirch",
          "conscious",
          "halve",
          "impediment",
          "cookbook"), mutableListOf(
          "WjN",
          "gr",
          "zWo",
          "qVRJI",
          "oGtu",
          "Rtae",
          "weY",
          "vtJVN",
          "UpvGj",
          "pkB",
          "iTtP",
          "pbW",
          "yvSlm",
          "ryCd",
          "eDCM",
          "fjpDW",
          "gbb",
          "OF",
          "vdz",
          "x",
          "NPS",
          "kuU",
          "irU",
          "qkEn",
          "aDpIy",
          "oOZYR",
          "PG",
          "plTjl",
          "zmpP",
          "C",
          "ZO",
          "vwQKC",
          "K",
          "S",
          "jod",
          "W",
          "gYOTF",
          "ObN",
          "WWW",
          "Zj",
          "k",
          "dYKay",
          "sZpnz",
          "uZpG",
          "w",
          "vKELP",
          "UD",
          "PdD",
          "GeS",
          "SpkW",
          "BKb",
          "VjVF",
          "DbKWv",
          "mHSo",
          "zyUPs",
          "fzE",
          "DHT",
          "JiVx",
          "fDI",
          "xC",
          "JFEU",
          "KJdW",
          "pYf",
          "ajIIy",
          "B",
          "J",
          "N",
          "VwM",
          "V",
          "mIzeb",
          "QMDe",
          "ycVQU",
          "uw",
          "j",
          "bpzvj",
          "mv",
          "z",
          "Ct",
          "nJ",
          "Yoj",
          "in",
          "YQ",
          "UfSS",
          "Dn",
          "IxDg",
          "ryfh",
          "KjaGa",
          "Xfz",
          "FVXL",
          "QCk",
          "RyAJ",
          "vIEM",
          "pnnRC",
          "Qj",
          "egG",
          "a",
          "e",
          "i",
          "incPb",
          "Ucy")))
}

private fun test5() {
  println(findSubstrings(mutableListOf("neuroses",
          "myopic",
          "sufficient",
          "televise",
          "coccidiosis",
          "gules",
          "during",
          "construe",
          "establish",
          "ethyl"),
          mutableListOf("aaaaa",
                  "Aaaa",
                  "E",
                  "z",
                  "Zzzzz",
                  "a",
                  "mel",
                  "lon",
                  "el",
                  "An",
                  "ise",
                  "d",
                  "g",
                  "wnoVV",
                  "i",
                  "IUMc",
                  "P",
                  "KQ",
                  "QfRz",
                  "Xyj",
                  "yiHS")))
}

private fun test1() {
  println(findSubstrings(mutableListOf("Apple",
          "Melon",
          "Orange",
          "Watermelon"), mutableListOf("a",
          "mel",
          "lon",
          "el",
          "An")))
}
