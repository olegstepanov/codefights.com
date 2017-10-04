package com.github.olegstepanov.codefights.bots.uberbot

fun fareEstimator(ride_time: Int, ride_distance: Int, cost_per_minute: MutableList<Double>, cost_per_mile: MutableList<Double>): MutableList<Double> =
        cost_per_mile.zip(cost_per_minute).map { (perMile, perMinute) -> perMinute * ride_time + perMile * ride_distance }.toMutableList()
