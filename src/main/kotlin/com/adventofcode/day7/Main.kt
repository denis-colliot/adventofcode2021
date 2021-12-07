package com.adventofcode.day7

import com.adventofcode.util.printResultWithTime
import com.adventofcode.util.readResourceLines
import kotlin.math.abs

fun String.toHorizontalPositions(): List<Int> =
    split(",").map(String::toInt)

private fun List<Int>.getMinFuelCost(fuelCostCalculator: (Int, Int) -> Int): Int =
    with(sorted()) {
        (first()..last()).minOf { hPosition ->
            sumOf { otherHPosition -> fuelCostCalculator(hPosition, otherHPosition) }
        }
    }

fun List<Int>.getMinFuelCostWithConstantRate(): Int =
    getMinFuelCost { hPosition1, hPosition2 -> abs(hPosition1 - hPosition2) }

fun List<Int>.getMinFuelCostWithoutConstantRate(): Int =
    getMinFuelCost { hPosition1, hPosition2 -> abs(hPosition1 - hPosition2).consecutiveSum() }

fun Int.consecutiveSum(): Int =
    this * (1 + this) / 2

fun main() {
    val input = readResourceLines("/day7/input").first()

    printResultWithTime {
        val result = input.toHorizontalPositions().getMinFuelCostWithConstantRate()
        "Min fuel total cost with constant rate = $result"
    }

    printResultWithTime {
        val result = input.toHorizontalPositions().getMinFuelCostWithoutConstantRate()
        "Min fuel total cost without constant rate = $result"
    }
}