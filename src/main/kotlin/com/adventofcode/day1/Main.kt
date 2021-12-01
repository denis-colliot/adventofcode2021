package com.adventofcode.day1

import com.adventofcode.util.printResultWithTime
import com.adventofcode.util.readResourceLines

fun countIncrease(depths: List<Int>): Int =
    depths.windowed(size = 2)
        .map { (previousDepth, nextDepth) -> nextDepth > previousDepth }
        .count { it }

fun countWindowedIncrease(depths: List<Int>): Int =
    countIncrease(
        depths.windowed(size = 3).map(List<Int>::sum)
    )

fun main() {
    val depths = readResourceLines("/day1/input").map(String::toInt)
    printResultWithTime {
        "Increase number: ${countIncrease(depths)}"
    }
    printResultWithTime {
        "Windowed increase number: ${countWindowedIncrease(depths)}"
    }
}