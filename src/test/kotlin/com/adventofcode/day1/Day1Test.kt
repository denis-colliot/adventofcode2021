package com.adventofcode.day1

import kotlin.test.Test
import kotlin.test.assertEquals

class Day1Test {

    @Test
    fun `should count 7 depth increase in given test input`() {
        // Given
        val depths = listOf(
            199,
            200,
            208,
            210,
            200,
            207,
            240,
            269,
            260,
            263
        )

        // When
        val result = countIncrease(depths)

        // Then
        assertEquals(7, result)
    }

    @Test
    fun `should count 5 windowed depth increase in given test input`() {
        // Given
        val depths = listOf(
            199,
            200,
            208,
            210,
            200,
            207,
            240,
            269,
            260,
            263
        )

        // When
        val result = countWindowedIncrease(depths)

        // Then
        assertEquals(5, result)
    }

}