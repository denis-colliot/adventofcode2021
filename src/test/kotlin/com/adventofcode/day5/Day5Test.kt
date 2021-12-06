package com.adventofcode.day5

import kotlin.test.Test
import kotlin.test.assertEquals

class Day5Test {

    private val testInput = """
        0,9 -> 5,9
        8,0 -> 0,8
        9,4 -> 3,4
        2,2 -> 2,1
        7,0 -> 7,4
        6,4 -> 2,0
        0,9 -> 2,9
        3,4 -> 1,4
        0,0 -> 8,8
        5,5 -> 8,2
    """.trimIndent().lines()

    @Test
    fun `should map given input to vectors with coordinates`() {
        // When
        val vectors = testInput.toVectors()

        // Then
        assertEquals(
            listOf(
                Vector(startX = 0, startY = 9, endX = 5, endY = 9),
                Vector(startX = 8, startY = 0, endX = 0, endY = 8),
                Vector(startX = 9, startY = 4, endX = 3, endY = 4),
                Vector(startX = 2, startY = 2, endX = 2, endY = 1),
                Vector(startX = 7, startY = 0, endX = 7, endY = 4),
                Vector(startX = 6, startY = 4, endX = 2, endY = 0),
                Vector(startX = 0, startY = 9, endX = 2, endY = 9),
                Vector(startX = 3, startY = 4, endX = 1, endY = 4),
                Vector(startX = 0, startY = 0, endX = 8, endY = 8),
                Vector(startX = 5, startY = 5, endX = 8, endY = 2)
            ),
            vectors
        )
    }

    @Test
    fun `should return positions { (1,1),(1,2),(1,3) } when converting vector(1,1--1,3) to positions`() {
        // Given
        val vector = Vector(startX = 1, startY = 1, endX = 1, endY = 3)

        // When
        val positions = vector.positions()

        // Then
        assertEquals(
            setOf(
                Position(x = 1, y = 1),
                Position(x = 1, y = 2),
                Position(x = 1, y = 3)
            ), positions
        )
    }

    @Test
    fun `should return positions { (9,7),(8,7),(7,7) } when converting vector(9,7--7,7) to positions`() {
        // Given
        val vector = Vector(startX = 9, startY = 7, endX = 7, endY = 7)

        // When
        val positions = vector.positions()

        // Then
        assertEquals(
            setOf(
                Position(x = 9, y = 7),
                Position(x = 8, y = 7),
                Position(x = 7, y = 7)
            ), positions
        )
    }

    @Test
    fun `should return positions { (6,4),(5,3),(4,2),(3,1),(2,0) } when converting vector(6,4--2,0) to positions`() {
        // Given
        val vector = Vector(startX = 6, startY = 4, endX = 2, endY = 0)

        // When
        val positions = vector.positions()

        // Then
        assertEquals(
            setOf(
                Position(x = 6, y = 4),
                Position(x = 5, y = 3),
                Position(x = 4, y = 2),
                Position(x = 3, y = 1),
                Position(x = 2, y = 0)
            ), positions
        )
    }

    @Test
    fun `should return positions { (6,4),(5,3),(4,2),(3,1),(2,0) } when converting vector(5,5--8,2) to positions`() {
        // Given
        val vector = Vector(startX = 5, startY = 5, endX = 8, endY = 2)

        // When
        val positions = vector.positions()

        // Then
        assertEquals(
            setOf(
                Position(x = 5, y = 5),
                Position(x = 6, y = 4),
                Position(x = 7, y = 3),
                Position(x = 8, y = 2)
            ), positions
        )
    }

    @Test
    fun `should map test input to grid of positions considering only horizontal & vertical vectors`() {
        // When
        val grid = testInput.toHorizontalAndVerticalVectorsGrid()

        // Then
        assertEquals(
            mapOf(
                Position(x = 5, y = 9) to 1,
                Position(x = 4, y = 9) to 1,
                Position(x = 3, y = 9) to 1,
                Position(x = 2, y = 9) to 2,
                Position(x = 1, y = 9) to 2,
                Position(x = 0, y = 9) to 2,
                Position(x = 3, y = 4) to 2,
                Position(x = 4, y = 4) to 1,
                Position(x = 5, y = 4) to 1,
                Position(x = 6, y = 4) to 1,
                Position(x = 7, y = 4) to 2,
                Position(x = 8, y = 4) to 1,
                Position(x = 9, y = 4) to 1,
                Position(x = 2, y = 1) to 1,
                Position(x = 2, y = 2) to 1,
                Position(x = 7, y = 3) to 1,
                Position(x = 7, y = 2) to 1,
                Position(x = 7, y = 1) to 1,
                Position(x = 7, y = 0) to 1,
                Position(x = 1, y = 4) to 1,
                Position(x = 2, y = 4) to 1
            ), grid
        )
    }

    @Test
    fun `should return 5 overlapping points in grid considering only horizontal & vertical vectors`() {
        // When
        val result = testInput.countOverlappingPointsConsideringOnlyHorizontalAndVerticalVectors()

        // Then
        assertEquals(5, result)
    }

    @Test
    fun `should map test input to grid of positions considering all vectors`() {
        // When
        val grid = testInput.toAllVectorsGrid()

        // Then
        assertEquals(39, grid.size)
        assertEquals(3, grid[Position(x = 4, y = 4)])
        assertEquals(1, grid[Position(x = 5, y = 4)])
        assertEquals(3, grid[Position(x = 6, y = 4)])
        assertEquals(2, grid[Position(x = 7, y = 4)])
    }

    @Test
    fun `should return 12 overlapping points in grid considering all vectors`() {
        // When
        val result = testInput.countOverlappingPointsConsideringAllVectors()

        // Then
        assertEquals(12, result)
    }

}