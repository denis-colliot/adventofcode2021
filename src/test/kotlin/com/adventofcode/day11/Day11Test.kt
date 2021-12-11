package com.adventofcode.day11

import kotlin.test.Test
import kotlin.test.assertEquals

class Day11Test {

    private val testInput = """
        5483143223
        2745854711
        5264556173
        6141336146
        6357385478
        4167524645
        2176841721
        6882881134
        4846848554
        5283751526
    """.trimIndent().lines()

    @Test
    fun `should count 9 flashes after 1 step`() {
        // Given
        val input = """
            11111
            19991
            19191
            19991
            11111
        """.trimIndent().lines()

        // When
        val result = input.countFlashesAfterStep(1)

        // Then
        assertEquals(9, result)
    }

    @Test
    fun `should count 9 flashes after 2 steps`() {
        // Given
        val input = """
            11111
            19991
            19191
            19991
            11111
        """.trimIndent().lines()

        // When
        val result = input.countFlashesAfterStep(2)

        // Then
        assertEquals(9, result)
    }

    @Test
    fun `should count 1656 flashes after 100 steps`() {
        // When
        val result = testInput.countFlashesAfterStep(100)

        // Then
        assertEquals(1656, result)
    }

    @Test
    fun `should return 195 as first step during which all octopuses flash`() {
        // When
        val result = testInput.findFirstStepDuringWhichAllOctopusesFlash()

        // Then
        assertEquals(195, result)
    }
}