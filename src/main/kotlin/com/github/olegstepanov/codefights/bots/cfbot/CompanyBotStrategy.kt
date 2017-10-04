fun companyBotStrategy(trainingData: MutableList<MutableList<Int>>): Double =
        with(trainingData.filter { it[1] == 1 }) { if (size == 0) 0.0 else map { it[0] }.sum().toDouble() / size }