package com.adventofcode.day6

import kotlin.test.Test
import kotlin.test.assertEquals

class Day6Test {

    private val testInput = "3,4,3,1,2"

    @Test
    fun `should map input to fish ages list`() {
        // When
        val fishAges = testInput.toFishAgesMap()

        // Then
        assertEquals(mapOf(1 to 1L, 2 to 1L, 3 to 2L, 4 to 1L), fishAges)
    }

    @Test
    fun `should return a total number of 26 fish after day 18`() {
        // When
        val fishCountAfter18days = testInput.toFishAgesMap().countFishAfterDay(day = 18)

        // Then
        assertEquals(26, fishCountAfter18days)
    }

    @Test
    fun `should return a total number of 5934 fish after day 80`() {
        // When
        val fishCountAfter80days = testInput.toFishAgesMap().countFishAfterDay(day = 80)

        // Then
        assertEquals(5934, fishCountAfter80days)
    }

    @Test
    fun `should return a total number of 26984457539 fish after day 256`() {
        // When
        val fishCountAfter80days = testInput.toFishAgesMap().countFishAfterDay(day = 256)

        // Then
        assertEquals(26984457539, fishCountAfter80days)
    }

}