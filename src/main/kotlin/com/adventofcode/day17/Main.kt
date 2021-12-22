package com.adventofcode.day17

import com.adventofcode.util.printResultWithTime
import com.adventofcode.util.readResourceLines

fun String.parseInput(): Pair<IntRange, IntRange> =
    "target area: x=(.+)\\.\\.(.+), y=(.+)\\.\\.(.+)".toRegex()
        .matchEntire(this)!!.destructured
        .let { (xStart, xEnd, yStart, yEnd) -> xStart.toInt()..xEnd.toInt() to yStart.toInt()..yEnd.toInt() }

fun Pair<IntRange, IntRange>.findMaxYPositionReached(): Int {
    return 0
}

fun main() {
    val input = readResourceLines("/day17/input").first()

    printResultWithTime {
        "Day 17 result = TODO"
    }
}