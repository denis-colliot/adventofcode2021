package com.adventofcode.day11

import com.adventofcode.util.printResultWithTime
import com.adventofcode.util.readResourceLines

typealias Grid = List<String>

data class Position(val x: Int, val y: Int)

fun Grid.increaseLevels(): Grid =
    map { line ->
        line.map { energyLevel ->
            if (energyLevel == '9') '0' else energyLevel + 1
        }.joinToString(separator = "")
    }

fun Grid.findAllFlashingPositions(): Set<Position> {
    val flashingPositions = mutableSetOf<Position>()
    forEachIndexed { yIndex, line ->
        line.forEachIndexed { xIndex, energyLevel ->
            if (energyLevel == '0') {
                flashingPositions.add(Position(x = xIndex, y = yIndex))
            }
        }
    }
    return flashingPositions
}

fun Grid.flashPosition(flashingPosition: Position): Grid {
    val adjacentPositions = setOf(
        Position(x = flashingPosition.x - 1, y = flashingPosition.y - 1),
        Position(x = flashingPosition.x, y = flashingPosition.y - 1),
        Position(x = flashingPosition.x + 1, y = flashingPosition.y - 1),
        Position(x = flashingPosition.x - 1, y = flashingPosition.y),
        Position(x = flashingPosition.x + 1, y = flashingPosition.y),
        Position(x = flashingPosition.x - 1, y = flashingPosition.y + 1),
        Position(x = flashingPosition.x, y = flashingPosition.y + 1),
        Position(x = flashingPosition.x + 1, y = flashingPosition.y + 1)
    )
    return mapIndexed { yIndex, line ->
        line.mapIndexed { xIndex, energyLevel ->
            if (Position(x = xIndex, y = yIndex) in adjacentPositions) {
                when (energyLevel) {
                    '0', '9' -> '0'
                    else -> energyLevel + 1
                }
            } else energyLevel
        }.joinToString(separator = "")
    }
}

fun Grid.flashPositions(flashingPositions: Set<Position>): Grid {
    var flashedGrid = this
    flashingPositions.forEach { flashedGrid = flashedGrid.flashPosition(it) }
    return flashedGrid
}

fun Grid.processFlashingPositions(
    flashingPositions: Set<Position>,
    alreadyFlashed: Set<Position> = emptySet()
): Pair<Grid, Long> =
    when (flashingPositions.isEmpty()) {
        true -> this to alreadyFlashed.size.toLong()
        else -> {
            val newGridStateAfterFlashes = flashPositions(flashingPositions)
            newGridStateAfterFlashes.processFlashingPositions(
                flashingPositions = newGridStateAfterFlashes.findAllFlashingPositions() - alreadyFlashed - flashingPositions,
                alreadyFlashed = alreadyFlashed + flashingPositions
            )
        }
    }

private fun Grid.applyStep(): Pair<Grid, Long> {
    val increased = increaseLevels()
    return increased.processFlashingPositions(increased.findAllFlashingPositions())
}

// Part 1
fun Grid.countFlashesAfterStep(stepsNumber: Int): Long {
    var count = 0L
    var currentState = this
    for (step in 1..stepsNumber) {
        val stepResult = currentState.applyStep()
        currentState = stepResult.first
        count += stepResult.second
    }
    return count
}

// Part 2
fun Grid.findFirstStepDuringWhichAllOctopusesFlash(): Int {
    var currentState = this
    var stepsNumber = 0
    while (!currentState.all { it == "0000000000" }) {
        val stepResult = currentState.applyStep()
        currentState = stepResult.first
        stepsNumber++
    }
    return stepsNumber
}

fun main() {
    val input = readResourceLines("/day11/input")

    printResultWithTime {
        val flashCount = input.countFlashesAfterStep(100)
        "Number of flashes after 100 steps = $flashCount"
    }

    printResultWithTime {
        val stepNumber = input.findFirstStepDuringWhichAllOctopusesFlash()
        "First step during which all octopuses flash = $stepNumber"
    }
}