package com.adventofcode.day13

import com.adventofcode.util.printResultWithTime
import com.adventofcode.util.readResourceLines
import kotlin.math.abs

data class Position(val x: Int, val y: Int)

sealed class Fold {
    data class AlongX(val x: Int) : Fold()
    data class AlongY(val y: Int) : Fold()
}

fun List<String>.parseInput(): Pair<Set<Position>, List<Fold>> {
    val positionsMap = mapNotNull { line -> "(\\d+),(\\d+)".toRegex().matchEntire(line)?.destructured }
        .map { (x, y) -> Position(x = x.toInt(), y = y.toInt()) }
        .toSet()
    val folds = mapNotNull { line -> "fold along (x|y)=(\\d+)".toRegex().matchEntire(line)?.destructured }
        .mapNotNull { (axis, index) ->
            when (axis) {
                "x" -> Fold.AlongX(index.toInt())
                "y" -> Fold.AlongY(index.toInt())
                else -> null
            }
        }
    return positionsMap to folds
}

fun Set<Position>.applyFold(fold: Fold): Set<Position> =
    when (fold) {
        is Fold.AlongY -> {
            val (keep, toUpdate) = partition { it.y < fold.y }
            keep.toSet() + toUpdate.map { position ->
                position.copy(y = fold.y - abs(fold.y - position.y))
            }.toSet()
        }
        is Fold.AlongX -> {
            val (keep, toUpdate) = partition { it.x < fold.x }
            keep.toSet() + toUpdate.map { position ->
                position.copy(x = fold.x - abs(fold.x - position.x))
            }.toSet()
        }
    }

fun Set<Position>.applyFolds(folds: List<Fold>): Set<Position> {
    var currentGrid = this
    folds.forEach { fold ->
        currentGrid = currentGrid.applyFold(fold)
    }
    return currentGrid
}

fun Set<Position>.toGridString(): String {
    val maxX = maxOf { position -> position.x }
    val maxY = maxOf { position -> position.y }
    val map = associate { it to "#" }
    val chars = (0..maxY).map { yIndex ->
        (0..maxX).map { xIndex ->
            map.getOrDefault(Position(x = xIndex, y = yIndex), ".")
        }
    }
    return chars.joinToString(separator = "\n") { it.joinToString(separator = "") }
}

fun main() {
    val input = readResourceLines("/day13/input")

    printResultWithTime {
        val (positions, folds) = input.parseInput()
        "Number of # after first fold = ${positions.applyFold(folds.first()).size}"
    }

    printResultWithTime {
        val (positions, folds) = input.parseInput()
        val finalResult = positions.applyFolds(folds)
        "Final result after all folds = \n${finalResult.toGridString()}"
    }
}