package com.adventofcode.day6

import com.adventofcode.util.printResultWithTime
import com.adventofcode.util.readResourceLines

fun String.toFishAgesMap(): Map<Int, Long> =
    split(",").map(String::toInt)
        .groupBy { age -> age }
        .mapValues { (_, ageValues) -> ageValues.size.toLong() }

fun Map<Int, Long>.countFishAfterDay(day: Int): Long =
    when (day) {
        0 -> values.sum()
        else -> (0..8).associate { age ->
            when (age) {
                6 -> age to getOrDefault(age + 1, 0) + getOrDefault(0, 0)
                8 -> age to getOrDefault(0, 0)
                else -> age to getOrDefault(age + 1, 0)
            }
        }.countFishAfterDay(day - 1)
    }

fun main() {
    val input = readResourceLines("/day6/input").first()

    printResultWithTime {
        val result = input.toFishAgesMap().countFishAfterDay(day = 80)
        "Number of fish after 80 days = $result"
    }

    printResultWithTime {
        val result = input.toFishAgesMap().countFishAfterDay(day = 256)
        "Number of fish after 256 days = $result"
    }
}