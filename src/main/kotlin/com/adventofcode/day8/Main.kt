package com.adventofcode.day8

import com.adventofcode.util.printResultWithTime
import com.adventofcode.util.readResourceLines

/**
 * Digits with unique number of segments:
 * - `1` has 2 segments
 * - `7` has 3 segments
 * - `4` has 4 segments
 * - `8` has 7 segments
 */
fun List<String>.countDigitsWithUniqueSegmentsNumber(): Int =
    map { input -> input.split("|") }
        .flatMap { (_, outputValues) -> outputValues.trim().split(" ") }
        .count { it.length in arrayOf(2, 3, 4, 7) }

fun String.decodeSingleOutputDigits(): Int {
    val (uniqueSignals, outputValues) = split("|")

    val uniqueSignalsGroupedBySize = uniqueSignals.trim()
        .split(" ")
        .groupBy({ it.length }, { it.toSet() })

    val digit1 = uniqueSignalsGroupedBySize.getValue(2).first()
    val digit7 = uniqueSignalsGroupedBySize.getValue(3).first()
    val digit4 = uniqueSignalsGroupedBySize.getValue(4).first()
    val digit8 = uniqueSignalsGroupedBySize.getValue(7).first()
    val digit2or3or5 = uniqueSignalsGroupedBySize.getValue(5)
    val digit0or6or9 = uniqueSignalsGroupedBySize.getValue(6)

    val topSegment = digit7 - digit1
    val bottomSegment = digit2or3or5
        .map { it - (digit4 + topSegment) }
        .first { it.size == 1 }
    val lowerLeftSegment = digit2or3or5
        .map { digit8 - (digit4 + topSegment + bottomSegment) }
        .first { it.size == 1 }
    val middleSegment = digit2or3or5
        .map { it - (digit7 + bottomSegment + lowerLeftSegment) }
        .first { it.size == 1 }
    val upperLeftSegment = digit8 - (digit7 + middleSegment + bottomSegment + lowerLeftSegment)
    val upperRightSegment = digit0or6or9
        .map { digit8 - (it + lowerLeftSegment + middleSegment) }
        .first { it.size == 1 }
    val lowerRightSegment =
        digit8 - (topSegment + bottomSegment + lowerLeftSegment + middleSegment + upperLeftSegment + upperRightSegment)

    val digitsMap = mapOf(
        digit8 - middleSegment to "0",
        digit1 to "1",
        digit8 - upperLeftSegment - lowerRightSegment to "2",
        digit8 - upperLeftSegment - lowerLeftSegment to "3",
        digit4 to "4",
        digit8 - upperRightSegment - lowerLeftSegment to "5",
        digit8 - upperRightSegment to "6",
        digit7 to "7",
        digit8 to "8",
        digit8 - lowerLeftSegment to "9"
    )

    return outputValues.trim().split(" ")
        .joinToString(separator = "") { digitsMap.getValue(it.toCharArray().toSet()) }
        .toInt()
}

fun List<String>.sumAllDecodedDigits(): Int =
    sumOf { it.decodeSingleOutputDigits() }

fun main() {
    val input = readResourceLines("/day8/input")

    printResultWithTime {
        val result = input.countDigitsWithUniqueSegmentsNumber()
        "Digits with unique segments number = $result"
    }

    printResultWithTime {
        val result = input.sumAllDecodedDigits()
        "Sum of all decoded digits = $result"
    }
}