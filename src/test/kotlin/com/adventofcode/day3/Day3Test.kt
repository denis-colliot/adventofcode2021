package com.adventofcode.day3

import kotlin.test.Test
import kotlin.test.assertEquals

class Day3Test {

    private val testInput = """
        00100
        11110
        10110
        10111
        10101
        01111
        00111
        11100
        10000
        11001
        00010
        01010
    """.trimIndent().lines()

    @Test
    fun `should return 198 when multiplying gamma rate (22) with epsilon rate (9) computed from given input`() {
        // When
        val gammaRate = getGammaRate(testInput)
        val epsilonRate = getEpsilonRate(testInput)

        // Then
        assertEquals(22, gammaRate)
        assertEquals(9, epsilonRate)
        assertEquals(198, gammaRate * epsilonRate)
    }

    @Test
    fun `should return life support rating of 230 when multiplying oxygen generator rating (23) with CO2 scrubber ration (10) computed from given input`() {
        // When
        val oxygenGeneratorRating = getOxygenGeneratorRating(testInput)
        val co2ScrubberGeneratorRating = getCO2ScrubberGeneratorRating(testInput)

        // Then
        assertEquals(23, oxygenGeneratorRating)
        assertEquals(10, co2ScrubberGeneratorRating)
        assertEquals(230, oxygenGeneratorRating * co2ScrubberGeneratorRating)
    }

}