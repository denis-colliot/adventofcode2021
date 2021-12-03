package com.adventofcode.day3

import com.adventofcode.util.printResultWithTime
import com.adventofcode.util.readResourceLines

private fun List<String>.getMostCommonBit(index: Int): Int =
    sumOf { it[index].digitToInt() }.let { firstBitsSum ->
        if (firstBitsSum >= size / 2.0) 1 else 0
    }

// region Part 1

private fun List<String>.getRate(commonBitCalculator: List<String>.(Int) -> Int): Int =
    first().mapIndexed { index, _ -> commonBitCalculator(index) }
        .joinToString(separator = "")
        .toInt(radix = 2)

fun getGammaRate(binaries: List<String>): Int =
    binaries.getRate { index -> getMostCommonBit(index) }

fun getEpsilonRate(binaries: List<String>): Int =
    binaries.getRate { index -> 1 - getMostCommonBit(index) }

// endregion

// region Part 2

private fun List<String>.getGeneratorRating(index: Int = 0, commonBitCalculator: List<String>.(Int) -> Int): Int =
    when (size) {
        1 -> first().toInt(radix = 2)
        else -> {
            val commonBit = commonBitCalculator(index)
            val filteredBinaries = filter { it[index].digitToInt() == commonBit }
            filteredBinaries.getGeneratorRating(index + 1, commonBitCalculator)
        }
    }

fun getOxygenGeneratorRating(binaries: List<String>): Int =
    binaries.getGeneratorRating { index -> getMostCommonBit(index) }

fun getCO2ScrubberGeneratorRating(binaries: List<String>): Int =
    binaries.getGeneratorRating { index -> 1 - getMostCommonBit(index) }

// endregion

fun main() {
    val commands = readResourceLines("/day3/input")
    printResultWithTime {
        val gammaRate = getGammaRate(commands)
        val epsilonRate = getEpsilonRate(commands)
        """
        Submarine power consumption (Gamma rate x Epsilon rate) 
         = $gammaRate x $epsilonRate
         = ${gammaRate * epsilonRate}
        """.trimIndent()
    }
    printResultWithTime {
        val oxygenGeneratorRating = getOxygenGeneratorRating(commands)
        val co2ScrubberGeneratorRating = getCO2ScrubberGeneratorRating(commands)
        """
        Submarine life support rating (O2 generator rating x CO2 generator rating) 
         = $oxygenGeneratorRating x $co2ScrubberGeneratorRating 
         = ${oxygenGeneratorRating * co2ScrubberGeneratorRating}
        """.trimIndent()
    }
}