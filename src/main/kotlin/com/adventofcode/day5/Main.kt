package com.adventofcode.day5

import com.adventofcode.util.printResultWithTime
import com.adventofcode.util.readResourceLines

data class Position(val x: Int, val y: Int)
data class Vector(val startX: Int, val startY: Int, val endX: Int, val endY: Int)

private val vectorRegex = "(\\d+),(\\d+) -> (\\d+),(\\d+)".toRegex()

fun List<String>.toVectors(): List<Vector> =
    mapNotNull { line -> vectorRegex.matchEntire(line)?.destructured }
        .map { (startX, startY, endX, endY) ->
            Vector(startX = startX.toInt(), startY = startY.toInt(), endX = endX.toInt(), endY = endY.toInt())
        }

fun Vector.positions(): Set<Position> {
    val xValues = (if (endX > startX) endX downTo startX else endX..startX).toList()
    val yValues = (if (endY > startY) endY downTo startY else endY..startY).toList()
    return when {
        xValues.size == 1 -> yValues.map { y -> Position(x = xValues.first(), y = y) }.toSet()
        yValues.size == 1 -> xValues.map { x -> Position(x = x, y = yValues.first()) }.toSet()
        else -> yValues.mapIndexed { yIndex, y -> Position(x = xValues[yIndex], y = y) }.toSet()
    }
}

private fun List<String>.toGrid(filter: ((Vector) -> Boolean)? = null): Map<Position, Int> =
    mutableMapOf<Position, Int>().also { grid ->
        toVectors()
            .filter { filter?.invoke(it) ?: true }
            .forEach { vector ->
                vector.positions().forEach { vectorPosition ->
                    grid.compute(vectorPosition) { _, counter -> (counter ?: 0) + 1 }
                }
            }
    }

// region Part 1: considering only horizontal & vertical vectors

fun List<String>.toHorizontalAndVerticalVectorsGrid(): Map<Position, Int> =
    toGrid { vector -> vector.startX == vector.endX || vector.startY == vector.endY }

fun List<String>.countOverlappingPointsConsideringOnlyHorizontalAndVerticalVectors(): Int =
    toHorizontalAndVerticalVectorsGrid().count { (_, count) -> count > 1 }

// endregion

// region Part 2: considering all vectors (horizontal, vertical & diagonal)

fun List<String>.toAllVectorsGrid(): Map<Position, Int> =
    toGrid()

fun List<String>.countOverlappingPointsConsideringAllVectors(): Int =
    toGrid().count { (_, count) -> count > 1 }

// endregion

fun main() {
    val input = readResourceLines("/day5/input")

    printResultWithTime {
        val result = input.countOverlappingPointsConsideringOnlyHorizontalAndVerticalVectors()
        "Number of overlapping points considering only horizontal & vertical vectors = $result"
    }

    printResultWithTime {
        val result = input.countOverlappingPointsConsideringAllVectors()
        "Number of overlapping points considering all vectors = $result"
    }
}