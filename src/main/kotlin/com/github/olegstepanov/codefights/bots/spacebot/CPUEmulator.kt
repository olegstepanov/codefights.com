package com.github.olegstepanov.codefights.bots.spacebot

fun cpuEmulator(subroutine: MutableList<String>): String {
  val tainted = BooleanArray(43)
  val regs = object : ArrayList<Long>() {
    init {
      for (i in 0..42)
        add(0)
    }

    override fun set(index: Int, element: Long): Long {
      tainted[index] = true
      return super.set(index, element)
    }
  }
  var ep = 0

  fun printstate() {
    (0 until regs.size)
            .filter { tainted[it] }
            .forEach { println("R$it: ${regs[it]}") }
  }

  fun execute() {
    val command = subroutine[ep]
    val instruction = command.substringBefore(' ')
    val args = command.substring(instruction.length).split(',').map { it.trim() }

    fun value(i: Int) = args[i].toLong()
    fun reg(i: Int) = args[i].substring(1).toInt()

    fun dec(i: Long) = if (i > 0) i - 1 else 0xFFFFFFFF
    fun inc(i: Long) = if (i < 0xFFFFFFFF) i + 1 else 0
    fun inv(i: Long) = i xor 0xFFFFFFFF

    fun check() = assert(regs.all{ it in 0..0xFFFFFFFF })

    var nep = ep + 1

    check()
//    println("$ep: $command")
    when (instruction) {
      "NOP" -> {}
      "MOV" -> when {
        args[0].startsWith("R") -> { regs[reg(1)] = regs[reg(0)] }
        else -> regs[reg(1)] = value(0).toLong()
      }
      "ADD" -> regs[reg(0)] = (regs[reg(0)] + regs[reg(1)]) % 0x100000000
      "DEC" -> regs[reg(0)] = dec(regs[reg(0)])
      "INC" -> regs[reg(0)] = inc(regs[reg(0)])
      "INV" -> regs[reg(0)] = inv(regs[reg(0)])
      "JMP" -> nep = (value(0) - 1).toInt()
      "JZ" -> if (regs[0] == 0L) nep = (value(0) - 1).toInt()
    }
    ep = nep

    check()
//    printstate()
  }

  while (ep < subroutine.size) {
    execute()
  }

  return regs[42].toString()
}

fun main(args: Array<String>) {
  println(cpuEmulator(mutableListOf("MOV 12499,R00",
          "JZ 6",
          "DEC R00",
          "DEC R42",
          "JMP 2",
          "NOP",
          "NOP")))
}
