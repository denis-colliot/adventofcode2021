package com.adventofcode.day15

import com.adventofcode.util.printResultWithTime
import com.adventofcode.util.readResourceLines
import java.util.*
import kotlin.math.abs

// Inspired from https://www.redblobgames.com/pathfinding/a-star/implementation.html

data class Position(val x: Int, val y: Int)

data class PriorityPosition(val position: Position, val priority: Int) : Comparable<PriorityPosition> {
    override fun compareTo(other: PriorityPosition): Int =
        priority.compareTo(other.priority)
}

typealias CavernRiskLevels = Map<Position, Int>

fun List<String>.parseInput(): CavernRiskLevels =
    flatMapIndexed { yIndex, riskLevels ->
        riskLevels.mapIndexed { xIndex, riskLevel -> Position(xIndex, yIndex) to riskLevel.digitToInt() }
    }.toMap()

fun CavernRiskLevels.getLowestTotalRiskLevel(start: Position = Position(0, 0), goal: Position): Int {
    val openPositions = PriorityQueue<PriorityPosition>()
        .apply { add(PriorityPosition(start, priority = 0)) }

    val cameFrom = mutableMapOf<Position, Position>()
    val costSoFar = mutableMapOf<Position, Int>()

    while (openPositions.isNotEmpty()) {
        val current = openPositions.poll()

        if (current.position == goal) {
            break
        }

        neighborsOf(current.position).forEach { nextPosition ->
            val newCost = costSoFar.getOrDefault(current.position, 0) + getValue(nextPosition)
            if (!costSoFar.containsKey(nextPosition) || newCost < costSoFar.getValue(nextPosition)) {
                costSoFar[nextPosition] = newCost
                val priority = newCost + heuristic(nextPosition, goal)
                openPositions.add(PriorityPosition(nextPosition, priority))
                cameFrom[nextPosition] = current.position
            }
        }
    }

    return costSoFar.getValue(goal)
}

private fun CavernRiskLevels.neighborsOf(current: Position): Set<Position> =
    setOfNotNull(
        current.copy(x = current.x - 1).takeIf { get(it) != null },
        current.copy(x = current.x + 1).takeIf { get(it) != null },
        current.copy(y = current.y + 1).takeIf { get(it) != null },
        current.copy(y = current.y - 1).takeIf { get(it) != null }
    )

private fun heuristic(position1: Position, position2: Position): Int =
    abs(position1.x - position2.x) + abs(position1.y - position2.y)

fun main() {
    val input = readResourceLines("/day15/input")

    printResultWithTime {
        val cavernRiskLevels = input.parseInput()
        val lowestTotalRiskLevel = cavernRiskLevels.getLowestTotalRiskLevel(goal = Position(99, 99))
        "Lowest total risk level = $lowestTotalRiskLevel"
    }
}