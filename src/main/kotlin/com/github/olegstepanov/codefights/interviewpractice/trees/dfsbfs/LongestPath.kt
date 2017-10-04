package com.github.olegstepanov.codefights.interviewpractice.trees.dfsbfs

fun longestPath(fileSystem: String): Int {
  for (c in fileSystem.codePoints()) {
    print("$c ")
  }
  println()

  val path = mutableListOf<String>()
  var longest = 0
  for (line in fileSystem.splitToSequence("\\r")) {
    var name = line
    while (name.startsWith("\\t"))
      name = name.substring("\\t".length)
    val level = (line.length - name.length) / "\\t".length
    val pathLevel = path.size - 1
    if (pathLevel < level)
      assert(pathLevel == level - 1)
    else if (pathLevel >= level) {
      for (i in level..pathLevel)
        path.removeAt(path.size - 1)
    }
    path.add(name)
    if (name.contains('.')) {
      val pathString = path.joinToString("/")
      if (pathString.length > longest)
        longest = pathString.length
    }
  }
  return longest
}

fun main(args: Array<String>) {
  println(longestPath("""user\r\tpictures\r\tdocuments\r\t\tnotes.txt"""))
  //println(longestPath("user\r\tpictures\r\t\tphoto.png\r\t\tcamera\r\tdocuments\r\t\tlectures\r\t\t\tnotes.txt"))
  //println(longestPath("a\r\tb\r\t\tc"))
}
