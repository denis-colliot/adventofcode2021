package com.adventofcode.day9

import kotlin.test.Test
import kotlin.test.assertEquals

class Day9Test {

    private val testInput = """
        2199943210
        3987894921
        9856789892
        8767896789
        9899965678
    """.trimIndent()

    @Test
    fun `should return low points locations`() {
        // When
        val result = testInput.lines().findLowPointsLocations()

        // Then
        assertEquals(
            setOf(
                Location(x = 1, y = 0),
                Location(x = 9, y = 0),
                Location(x = 2, y = 2),
                Location(x = 6, y = 4),
            ), result
        )
    }

    @Test
    fun `should return 15 as sum of risk levels of all low points`() {
        // When
        val result = testInput.lines().lowPointsRiskLevelsSum()

        // Then
        assertEquals(15, result)
    }

    @Test
    fun `should return a basin of size 3 from low point (1,0)`() {
        // When
        val result = testInput.lines().findBasinLocations(Location(x = 1, y = 0))

        // Then
        assertEquals(
            setOf(
                Location(x = 0, y = 0),
                Location(x = 1, y = 0),
                Location(x = 0, y = 1)
            ), result
        )
    }

    @Test
    fun `should return a basin of size 9 from low point (9,0)`() {
        // When
        val result = testInput.lines().findBasinLocations(Location(x = 9, y = 0))

        // Then
        assertEquals(
            setOf(
                Location(x = 9, y = 0),
                Location(x = 8, y = 0),
                Location(x = 7, y = 0),
                Location(x = 6, y = 0),
                Location(x = 5, y = 0),
                Location(x = 6, y = 1),
                Location(x = 8, y = 1),
                Location(x = 9, y = 1),
                Location(x = 9, y = 2)
            ), result
        )
    }

    @Test
    fun `should return a basin of size 14 from low point (2,2)`() {
        // When
        val result = testInput.lines().findBasinLocations(Location(x = 2, y = 2))

        // Then
        assertEquals(
            setOf(
                Location(x = 2, y = 1),
                Location(x = 3, y = 1),
                Location(x = 4, y = 1),
                Location(x = 1, y = 2),
                Location(x = 2, y = 2),
                Location(x = 3, y = 2),
                Location(x = 4, y = 2),
                Location(x = 5, y = 2),
                Location(x = 0, y = 3),
                Location(x = 1, y = 3),
                Location(x = 2, y = 3),
                Location(x = 3, y = 3),
                Location(x = 4, y = 3),
                Location(x = 1, y = 4)
            ), result
        )
    }

    @Test
    fun `should return a basin of size 9 from low point (6,4)`() {
        // When
        val result = testInput.lines().findBasinLocations(Location(x = 6, y = 4))

        // Then
        assertEquals(
            setOf(
                Location(x = 7, y = 2),
                Location(x = 6, y = 3),
                Location(x = 7, y = 3),
                Location(x = 8, y = 3),
                Location(x = 5, y = 4),
                Location(x = 6, y = 4),
                Location(x = 7, y = 4),
                Location(x = 8, y = 4),
                Location(x = 9, y = 4)
            ), result
        )
    }

    @Test
    fun `should return 1134 as largest basins sizes multiplied together`() {
        // When
        val result = testInput.lines().largestBasinsSizeProduct()

        // Then
        assertEquals(1134, result)
    }
}