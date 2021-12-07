package com.adventofcode.day7

import kotlin.test.Test
import kotlin.test.assertEquals

class Day7Test {

    private val testInput = "16,1,2,0,4,2,7,1,2,14"

    @Test
    fun `should map given input into a list of integers`() {
        // When
        val result = testInput.toHorizontalPositions()

        // Then
        assertEquals(listOf(16, 1, 2, 0, 4, 2, 7, 1, 2, 14), result)
    }

    @Test
    fun `should return 37 as min fuel cost for given input`() {
        // When
        val result = testInput.toHorizontalPositions().getMinFuelCostWithConstantRate()

        // Then
        assertEquals(37, result)
    }

    @Test
    fun `should compute consecutive sum`() {
        // Then
        assertEquals(0, 0.consecutiveSum())
        assertEquals(1, 1.consecutiveSum())
        assertEquals(3, 2.consecutiveSum())
        assertEquals(6, 3.consecutiveSum())
        assertEquals(15, 5.consecutiveSum())
        assertEquals(66, 11.consecutiveSum())
        assertEquals(171, 18.consecutiveSum())
    }

    @Test
    fun `should return 168 as min fuel cost without constant rate for given input`() {
        // When
        val result = testInput.toHorizontalPositions().getMinFuelCostWithoutConstantRate()

        // Then
        assertEquals(168, result)
    }
}