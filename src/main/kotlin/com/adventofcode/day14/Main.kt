package com.adventofcode.day14

import com.adventofcode.util.printResultWithTime
import com.adventofcode.util.readResourceLines

typealias Polymer = Map<String, Long>
typealias Rules = Map<String, Char>

fun List<String>.parseInput(): Pair<Polymer, Rules> {
    val rules = mapNotNull { "(\\w\\w) -> (\\w)".toRegex().matchEntire(it)?.destructured }
        .associate { (adjacentChars, insertionChar) -> adjacentChars to insertionChar.first() }

    val elementCounts = first().groupBy { it.toString() }
        .mapValues { (_, elementOccurrences) -> elementOccurrences.size.toLong() }

    val polymer = first().windowed(size = 2)
        .filter { pair -> rules.containsKey(pair) }
        .groupBy { it }
        .mapValues { (_, occurrences) -> occurrences.size.toLong() } + elementCounts

    return polymer to rules
}

fun Polymer.applyRules(rules: Rules): Polymer {
    val newPolymer = filterKeys { it.length == 1 }.toMutableMap()
    filterKeys { it.length == 2 }.map { (elementsPair, elementsPairCount) ->
        val insertedElement = rules.getValue(elementsPair)
        arrayOf(elementsPair.first(), insertedElement, elementsPair.last())
            .joinToString(separator = "")
            .windowed(size = 2)
            .filter { pair -> rules.containsKey(pair) }
            .groupBy { it }
            .mapValues { (_, occurrences) -> occurrences.size * elementsPairCount }
            .forEach { (newElement, newOccurrence) ->
                newPolymer.compute(newElement) { _, value -> (value ?: 0) + newOccurrence }
            }
        newPolymer.compute(insertedElement.toString()) { _, value -> (value ?: 0) + elementsPairCount }
    }
    return newPolymer
}

fun Polymer.getMostCommonLeastCommonDiffAtStep(rules: Rules, step: Int): Long {
    var currentPolymer = this
    (1..step).forEach { _ -> currentPolymer = currentPolymer.applyRules(rules) }
    val max = currentPolymer.filterKeys { it.length == 1 }.maxOf { (_, count) -> count }
    val min = currentPolymer.filterKeys { it.length == 1 }.minOf { (_, count) -> count }
    return max - min
}

fun main() {
    val input = readResourceLines("/day14/input")

    printResultWithTime {
        val (polymer, rules) = input.parseInput()
        val result = polymer.getMostCommonLeastCommonDiffAtStep(rules, step = 10)
        "(Step 10) Quantity of the most common element - Quantity of the least common element = $result"
    }

    printResultWithTime {
        val (polymer, rules) = input.parseInput()
        val result = polymer.getMostCommonLeastCommonDiffAtStep(rules, step = 40)
        "(Step 40) Quantity of the most common element - Quantity of the least common element = $result"
    }
}