package com.adventofcode.util

import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

/**
 * @return Given [resourcePath] content lines.
 */
fun readResourceLines(resourcePath: String): List<String> =
    object {}.javaClass.getResource(resourcePath)!!.readText().lines()

/**
 * Print output result of given [block] in console with its execution time (in ms).
 */
@Suppress("EXPERIMENTAL_IS_NOT_ENABLED")
@OptIn(ExperimentalTime::class)
fun printResultWithTime(block: () -> Any) {
    val timedValue = measureTimedValue { block() }
    println("\n${timedValue.value}")
    println("(executed in ${timedValue.duration.inWholeMilliseconds} ms)")
}