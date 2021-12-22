package com.adventofcode.day17

import kotlin.test.Test
import kotlin.test.assertEquals

class Day17Test {

    private val testInput = "target area: x=20..30, y=-10..-5"

    @Test
    fun `should parse input into x and y ranges`() {
        // When
        val result = testInput.parseInput()

        // Then
        assertEquals(20..30 to -10..-5, result)
    }

    @Test
    fun `should return 45 as maximum y position reached with start velocity 6,9`() {
        // Given
        val targetArea = testInput.parseInput()

        // When
        val result = targetArea.findMaxYPositionReached()

        // Then
        assertEquals(45, result)
    }
}