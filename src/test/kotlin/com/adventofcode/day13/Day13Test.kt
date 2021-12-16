package com.adventofcode.day13

import kotlin.test.Test
import kotlin.test.assertEquals

class Day13Test {

    private val testInput = """
        6,10
        0,14
        9,10
        0,3
        10,4
        4,11
        6,0
        6,12
        4,1
        0,13
        10,12
        3,4
        3,0
        8,4
        1,10
        2,14
        8,10
        9,0

        fold along y=7
        fold along x=5
    """.trimIndent().lines()

    @Test
    fun `should parse test input into convenient representation`() {
        // When
        val result = testInput.parseInput()

        // Then
        assertEquals(
            setOf(
                Position(x = 6, y = 10),
                Position(x = 0, y = 14),
                Position(x = 9, y = 10),
                Position(x = 0, y = 3),
                Position(x = 10, y = 4),
                Position(x = 4, y = 11),
                Position(x = 6, y = 0),
                Position(x = 6, y = 12),
                Position(x = 4, y = 1),
                Position(x = 0, y = 13),
                Position(x = 10, y = 12),
                Position(x = 3, y = 4),
                Position(x = 3, y = 0),
                Position(x = 8, y = 4),
                Position(x = 1, y = 10),
                Position(x = 2, y = 14),
                Position(x = 8, y = 10),
                Position(x = 9, y = 0)
            ) to listOf(
                Fold.AlongY(7),
                Fold.AlongX(5)
            ), result
        )
    }

    @Test
    fun `should count 17 dots after first fold`() {
        // Given
        val (positionsGrid, folds) = testInput.parseInput()

        // When
        val result = positionsGrid.applyFold(folds.first())

        // Then
        assertEquals(
            setOf(
                Position(x = 0, y = 0),
                Position(x = 2, y = 0),
                Position(x = 3, y = 0),
                Position(x = 6, y = 0),
                Position(x = 9, y = 0),
                Position(x = 0, y = 1),
                Position(x = 4, y = 1),
                Position(x = 6, y = 2),
                Position(x = 10, y = 2),
                Position(x = 0, y = 3),
                Position(x = 4, y = 3),
                Position(x = 1, y = 4),
                Position(x = 3, y = 4),
                Position(x = 6, y = 4),
                Position(x = 8, y = 4),
                Position(x = 9, y = 4),
                Position(x = 10, y = 4)
            ), result
        )
    }

    @Test
    fun `should count 17 dots after second fold`() {
        // Given
        val (positionsGrid, folds) = testInput.parseInput()

        // When
        val result = positionsGrid.applyFolds(folds)

        // Then
        assertEquals(
            setOf(
                Position(x = 0, y = 0),
                Position(x = 1, y = 0),
                Position(x = 2, y = 0),
                Position(x = 3, y = 0),
                Position(x = 4, y = 0),
                Position(x = 0, y = 1),
                Position(x = 4, y = 1),
                Position(x = 0, y = 2),
                Position(x = 4, y = 2),
                Position(x = 0, y = 3),
                Position(x = 4, y = 3),
                Position(x = 0, y = 4),
                Position(x = 1, y = 4),
                Position(x = 2, y = 4),
                Position(x = 3, y = 4),
                Position(x = 4, y = 4),
            ), result
        )
    }
}