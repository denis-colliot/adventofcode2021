package com.adventofcode.day21

import kotlin.test.Test
import kotlin.test.assertEquals

class Day21Test {

    private val testInput = """
        Player 1 starting position: 4
        Player 2 starting position: 8
    """.trimIndent().lines()

    @Test
    fun `should parse game input`() {
        // When
        val game = testInput.parseInput()

        // Then
        assertEquals(
            Game(
                player1 = Player(name = "Player 1", space = 4, score = 0, isWinner = false),
                player2 = Player(name = "Player 2", space = 8, score = 0, isWinner = false)
            ), game
        )
    }

    @Test
    fun `should die roll and return next values`() {
        assertEquals(listOf(1, 2, 3), 0.roll(3))
        assertEquals(listOf(9, 10, 11, 12), 8.roll(4))
        assertEquals(listOf(42, 43, 44), 41.roll(3))
        assertEquals(listOf(48, 49, 50), 47.roll(3))
        assertEquals(listOf(97, 98, 99, 100), 96.roll(4))
        assertEquals(listOf(99, 100, 1, 2, 3), 98.roll(5))
    }

    @Test
    fun `should return player at space 10 with score of 10 when playing die rolls 1,2,3 from space 4`() {
        // Given
        val player = Player(name = "X", space = 4)

        // When
        val result = player.play(listOf(1, 2, 3), winningScore = 1000)

        // Then
        assertEquals(Player(name = "X", space = 10, score = 10), result)
    }

    @Test
    fun `should return player at space 6 with score of 26 when playing die rolls 19,20,21 from space 6`() {
        // Given
        val player = Player(name = "X", space = 6, score = 20)

        // When
        val result = player.play(listOf(19, 20, 21), winningScore = 1000)

        // Then
        assertEquals(Player(name = "X", space = 6, score = 26), result)
    }

    @Test
    fun `should return player at space 6 with score of 1000 when playing die rolls 91,92,93 from space 4`() {
        // Given
        val player = Player(name = "X", space = 4, score = 990)

        // When
        val result = player.play(listOf(91, 92, 93), winningScore = 1000)

        // Then
        assertEquals(Player(name = "X", space = 10, score = 1000, isWinner = true), result)
    }

    @Test
    fun `should play game`() {
        // Given
        val game = testInput.parseInput()

        // When
        val result = game.play()

        // Then
        assertEquals(739785, result)
    }

    @Test
    fun `should play game with quantum die`() {
        // Given
        val game = testInput.parseInput()

        // When
        val result = game.quantumPlay()

        // Then
        assertEquals(444356092776315 to 341960390180808, result)
    }

}