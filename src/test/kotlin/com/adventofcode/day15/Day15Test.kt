package com.adventofcode.day15

import kotlin.test.Test
import kotlin.test.assertEquals

class Day15Test {

    private val testInput = """
        1163751742
        1381373672
        2136511328
        3694931569
        7463417111
        1319128137
        1359912421
        3125421639
        1293138521
        2311944581
    """.trimIndent().lines()

    @Test
    fun `should parse test input`() {
        // When
        val result = testInput.parseInput()

        // Then
        assertEquals(100, result.size)
        assertEquals(9, result.keys.maxOf { it.x })
        assertEquals(9, result.keys.maxOf { it.y })
        assertEquals(
            mapOf(
                // y=0 (1163751742)
                Position(x = 0, y = 0) to 1,
                Position(x = 1, y = 0) to 1,
                Position(x = 2, y = 0) to 6,
                Position(x = 3, y = 0) to 3,
                Position(x = 4, y = 0) to 7,
                Position(x = 5, y = 0) to 5,
                Position(x = 6, y = 0) to 1,
                Position(x = 7, y = 0) to 7,
                Position(x = 8, y = 0) to 4,
                Position(x = 9, y = 0) to 2
            ), result.filterKeys { position -> position.y == 0 }
        )
        assertEquals(
            mapOf(
                // y=9 (2311944581)
                Position(x = 0, y = 9) to 2,
                Position(x = 1, y = 9) to 3,
                Position(x = 2, y = 9) to 1,
                Position(x = 3, y = 9) to 1,
                Position(x = 4, y = 9) to 9,
                Position(x = 5, y = 9) to 4,
                Position(x = 6, y = 9) to 4,
                Position(x = 7, y = 9) to 5,
                Position(x = 8, y = 9) to 8,
                Position(x = 9, y = 9) to 1
            ), result.filterKeys { position -> position.y == 9 }
        )
    }

    @Test
    fun `should return 40 as the lowest total risk`() {
        // Given
        val cavernRiskLevels = testInput.parseInput()

        // When
        val result = cavernRiskLevels.getLowestTotalRiskLevel(goal = Position(9, 9))

        // Then
        assertEquals(40, result)
    }
}