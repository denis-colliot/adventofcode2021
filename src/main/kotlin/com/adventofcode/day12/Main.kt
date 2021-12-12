package com.adventofcode.day12

import com.adventofcode.util.printResultWithTime
import com.adventofcode.util.readResourceLines

typealias Graph = Map<String, Set<String>>
typealias Path = List<String>

fun List<String>.toGraph(): Graph {
    val graph = mutableMapOf<String, MutableSet<String>>()
    mapNotNull { line -> "(.+)-(.+)".toRegex().matchEntire(line)?.destructured }
        .forEach { (source, destination) ->
            graph.computeIfAbsent(source) { mutableSetOf() }.add(destination)
            graph.computeIfAbsent(destination) { mutableSetOf() }.add(source)
        }
    return graph
}

fun Graph.getPathsVisitingSmallCavesAtMostOnce(
    currentCave: String = "start",
    visitedCaves: Path = emptyList()
): Set<Path> =
    when (currentCave) {
        "end" -> setOf(visitedCaves + currentCave)
        else -> {
            val destinationCaves = getValue(currentCave) - visitedCaves.filter { it.isSmallCave() }.toSet()
            destinationCaves.fold(emptySet()) { paths, destinationCave ->
                paths + getPathsVisitingSmallCavesAtMostOnce(destinationCave, visitedCaves + currentCave)
            }
        }
    }

fun Graph.getPathsVisitingSingleSmallCaveTwice(
    currentCave: String = "start",
    visitedCaves: Path = emptyList()
): Set<Path> =
    when (currentCave) {
        "end" -> setOf(visitedCaves + currentCave)
        else -> {
            val smallCavesVisitedMoreThanOnce = (visitedCaves + currentCave).groupBy { it }
                .filterKeys { it.isSmallCave() }
                .filterValues { caveOccurrences -> caveOccurrences.size > 1 }
                .keys

            val forbiddenCaveDestinations = when (smallCavesVisitedMoreThanOnce.isEmpty()) {
                true -> setOf("start")
                else -> visitedCaves.filter { it.isSmallCave() }.toSet()
            }

            val destinationCaves = getValue(currentCave) - forbiddenCaveDestinations
            destinationCaves.fold(emptySet()) { paths, destinationCave ->
                paths + getPathsVisitingSingleSmallCaveTwice(destinationCave, visitedCaves + currentCave)
            }
        }
    }

fun String.isSmallCave(): Boolean =
    all { it.isLowerCase() }

fun main() {
    val input = readResourceLines("/day12/input")

    printResultWithTime {
        val paths = input.toGraph().getPathsVisitingSmallCavesAtMostOnce()
        "Number of paths that visit small caves at most once = ${paths.size}"
    }

    printResultWithTime {
        val paths = input.toGraph().getPathsVisitingSingleSmallCaveTwice()
        "Number of paths that visit single small cave twice = ${paths.size}"
    }
}