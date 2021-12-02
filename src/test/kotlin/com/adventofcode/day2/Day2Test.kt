package com.adventofcode.day2

import kotlin.test.Test
import kotlin.test.assertEquals

class Day2Test {

    @Test
    fun `should return 150 when multiplying horizontal position (15) with depth (10) after applying given commands`() {
        // Given
        val commands = listOf(
            "forward 5",
            "down 5",
            "forward 8",
            "up 3",
            "down 8",
            "forward 2"
        )

        // When
        val (horizontalPosition, depth) = getHorizontalPositionAndDepth(commands)

        // Then
        assertEquals(15, horizontalPosition)
        assertEquals(10, depth)
        assertEquals(150, horizontalPosition * depth)
    }

    @Test
    fun `should return 900 when multiplying horizontal position (15) with depth (60) considering aim after applying given commands`() {
        // Given
        val commands = listOf(
            "forward 5",
            "down 5",
            "forward 8",
            "up 3",
            "down 8",
            "forward 2"
        )

        // When
        val (horizontalPosition, depth) = getHorizontalPositionAndDepthConsideringAim(commands)

        // Then
        assertEquals(15, horizontalPosition)
        assertEquals(60, depth)
        assertEquals(900, horizontalPosition * depth)
    }

}