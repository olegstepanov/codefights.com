package com.github.olegstepanov.codefights.bots.botasana

fun smartAssigning(
        names: MutableList<String>,
        statuses: MutableList<Boolean>,
        projects: MutableList<Int>,
        tasks: MutableList<Int>): String =

        names[(0 until names.size)
                .filter { !statuses[it] }
                .sortedWith(Comparator
                { i1, i2 -> if (tasks[i1] != tasks[i2]) tasks[i1].compareTo(tasks[i2]) else projects[i1].compareTo(projects[i2]) })
                .first()]
