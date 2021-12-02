package com.adventofcode.day2

import com.adventofcode.util.printResultWithTime
import com.adventofcode.util.readResourceLines

private val commandRegex = "([a-z]+) (\\d+)".toRegex()

private fun List<String>.toDirectionUnitList(): List<Pair<String, Int>> =
    mapNotNull { command ->
        commandRegex.matchEntire(command)?.destructured
            ?.let { (direction, value) -> direction to value.toInt() }
    }

fun getHorizontalPositionAndDepth(commands: List<String>): Pair<Int, Int> =
    commands.toDirectionUnitList()
        .fold(0 to 0) { (hPosition, depth), (direction, unit) ->
            when (direction) {
                "forward" -> hPosition + unit to depth
                "up" -> hPosition to depth - unit
                "down" -> hPosition to depth + unit
                else -> hPosition to depth
            }
        }

fun getHorizontalPositionAndDepthConsideringAim(commands: List<String>): Pair<Int, Int> =
    commands.toDirectionUnitList()
        .fold(Triple(0, 0, 0)) { (hPosition, depth, aim), (direction, unit) ->
            when (direction) {
                "forward" -> Triple(hPosition + unit, depth + (aim * unit), aim)
                "up" -> Triple(hPosition, depth, aim - unit)
                "down" -> Triple(hPosition, depth, aim + unit)
                else -> Triple(hPosition, depth, aim)
            }
        }.let { (hPosition, depth, _) -> hPosition to depth }

fun main() {
    val commands = readResourceLines("/day2/input")
    printResultWithTime {
        val (horizontalPosition, depth) = getHorizontalPositionAndDepth(commands)
        "[Without aim] Horizontal position ($horizontalPosition) x Depth ($depth) = ${horizontalPosition * depth}"
    }
    printResultWithTime {
        val (horizontalPosition, depth) = getHorizontalPositionAndDepthConsideringAim(commands)
        "[Wit aim] Horizontal position ($horizontalPosition) x Depth ($depth) = ${horizontalPosition * depth}"
    }
}