package com.adventofcode.day9

import com.adventofcode.util.printResultWithTime
import com.adventofcode.util.readResourceLines

data class Location(val x: Int, val y: Int)

fun List<String>.findLowPointsLocations(): Set<Location> =
    foldIndexed(emptySet()) { lineIndex, lowPointLocations, heightsLine ->
        lowPointLocations + heightsLine
            .mapIndexed { columnIndex, height -> height to Location(x = columnIndex, y = lineIndex) }
            .filter { (height, location) ->
                listOfNotNull(
                    getOrNull(location.y - 1)?.get(location.x),
                    getOrNull(location.y + 1)?.get(location.x),
                    heightsLine.getOrNull(location.x - 1),
                    heightsLine.getOrNull(location.x + 1)
                ).all { adjacentHeight -> height < adjacentHeight }
            }
            .map { (_, location) -> location }
            .toSet()
    }

fun List<String>.lowPointsRiskLevelsSum(): Int =
    findLowPointsLocations().sumOf { lowPointLocation ->
        get(lowPointLocation.y)[lowPointLocation.x].digitToInt() + 1
    }

fun List<String>.findBasinLocations(location: Location): Set<Location> =
    when (val height = getOrNull(location.y)?.getOrNull(location.x)) {
        null, '9' -> emptySet()
        else -> setOf(
            location.copy(x = location.x + 1),
            location.copy(x = location.x - 1),
            location.copy(y = location.y + 1),
            location.copy(y = location.y - 1),
        ).filter { adjacentLocation ->
            getOrNull(adjacentLocation.y)?.getOrNull(adjacentLocation.x)
                ?.let { adjacentHeight -> adjacentHeight.digitToInt() > height.digitToInt() }
                ?: false
        }.flatMap(::findBasinLocations).toSet() + location
    }

fun List<String>.largestBasinsSizeProduct(): Int =
    findLowPointsLocations()
        .map { lowPointLocation -> findBasinLocations(lowPointLocation).size }
        .sortedDescending()
        .take(3)
        .fold(1) { acc, lowPointBasinSize -> acc * lowPointBasinSize }

fun main() {
    val input = readResourceLines("/day9/input")

    printResultWithTime {
        val result = input.lowPointsRiskLevelsSum()
        "Low points risk levels sum = $result"
    }

    printResultWithTime {
        val result = input.largestBasinsSizeProduct()
        "Three largest basins sizes product = $result"
    }
}