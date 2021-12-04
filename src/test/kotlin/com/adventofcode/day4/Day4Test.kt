package com.adventofcode.day4

import kotlin.test.*

class Day4Test {

    private val testInput = """
        7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1

        22 13 17 11  0
         8  2 23  4 24
        21  9 14 16  7
         6 10  3 18  5
         1 12 20 15 19
        
         3 15  0  2 22
         9 18 13 17  5
        19  8  7 25 23
        20 11 10 24  4
        14 21 16 12  6
        
        14 21 17 24  4
        10 16 15  9 19
        18  8 23 26 20
        22 11 13  6  5
         2  0 12  3  7
    """.trimIndent().lines()

    private val expectedBingoBoards = listOf(
        BingoBoard(
            numbers = listOf(
                BingoBoardNumber(row = 0, column = 0, number = 22),
                BingoBoardNumber(row = 0, column = 1, number = 13),
                BingoBoardNumber(row = 0, column = 2, number = 17),
                BingoBoardNumber(row = 0, column = 3, number = 11),
                BingoBoardNumber(row = 0, column = 4, number = 0),
                BingoBoardNumber(row = 1, column = 0, number = 8),
                BingoBoardNumber(row = 1, column = 1, number = 2),
                BingoBoardNumber(row = 1, column = 2, number = 23),
                BingoBoardNumber(row = 1, column = 3, number = 4),
                BingoBoardNumber(row = 1, column = 4, number = 24),
                BingoBoardNumber(row = 2, column = 0, number = 21),
                BingoBoardNumber(row = 2, column = 1, number = 9),
                BingoBoardNumber(row = 2, column = 2, number = 14),
                BingoBoardNumber(row = 2, column = 3, number = 16),
                BingoBoardNumber(row = 2, column = 4, number = 7),
                BingoBoardNumber(row = 3, column = 0, number = 6),
                BingoBoardNumber(row = 3, column = 1, number = 10),
                BingoBoardNumber(row = 3, column = 2, number = 3),
                BingoBoardNumber(row = 3, column = 3, number = 18),
                BingoBoardNumber(row = 3, column = 4, number = 5),
                BingoBoardNumber(row = 4, column = 0, number = 1),
                BingoBoardNumber(row = 4, column = 1, number = 12),
                BingoBoardNumber(row = 4, column = 2, number = 20),
                BingoBoardNumber(row = 4, column = 3, number = 15),
                BingoBoardNumber(row = 4, column = 4, number = 19),
            )
        ),
        BingoBoard(
            numbers = listOf(
                BingoBoardNumber(row = 0, column = 0, number = 3),
                BingoBoardNumber(row = 0, column = 1, number = 15),
                BingoBoardNumber(row = 0, column = 2, number = 0),
                BingoBoardNumber(row = 0, column = 3, number = 2),
                BingoBoardNumber(row = 0, column = 4, number = 22),
                BingoBoardNumber(row = 1, column = 0, number = 9),
                BingoBoardNumber(row = 1, column = 1, number = 18),
                BingoBoardNumber(row = 1, column = 2, number = 13),
                BingoBoardNumber(row = 1, column = 3, number = 17),
                BingoBoardNumber(row = 1, column = 4, number = 5),
                BingoBoardNumber(row = 2, column = 0, number = 19),
                BingoBoardNumber(row = 2, column = 1, number = 8),
                BingoBoardNumber(row = 2, column = 2, number = 7),
                BingoBoardNumber(row = 2, column = 3, number = 25),
                BingoBoardNumber(row = 2, column = 4, number = 23),
                BingoBoardNumber(row = 3, column = 0, number = 20),
                BingoBoardNumber(row = 3, column = 1, number = 11),
                BingoBoardNumber(row = 3, column = 2, number = 10),
                BingoBoardNumber(row = 3, column = 3, number = 24),
                BingoBoardNumber(row = 3, column = 4, number = 4),
                BingoBoardNumber(row = 4, column = 0, number = 14),
                BingoBoardNumber(row = 4, column = 1, number = 21),
                BingoBoardNumber(row = 4, column = 2, number = 16),
                BingoBoardNumber(row = 4, column = 3, number = 12),
                BingoBoardNumber(row = 4, column = 4, number = 6),
            )
        ),
        BingoBoard(
            numbers = listOf(
                BingoBoardNumber(row = 0, column = 0, number = 14),
                BingoBoardNumber(row = 0, column = 1, number = 21),
                BingoBoardNumber(row = 0, column = 2, number = 17),
                BingoBoardNumber(row = 0, column = 3, number = 24),
                BingoBoardNumber(row = 0, column = 4, number = 4),
                BingoBoardNumber(row = 1, column = 0, number = 10),
                BingoBoardNumber(row = 1, column = 1, number = 16),
                BingoBoardNumber(row = 1, column = 2, number = 15),
                BingoBoardNumber(row = 1, column = 3, number = 9),
                BingoBoardNumber(row = 1, column = 4, number = 19),
                BingoBoardNumber(row = 2, column = 0, number = 18),
                BingoBoardNumber(row = 2, column = 1, number = 8),
                BingoBoardNumber(row = 2, column = 2, number = 23),
                BingoBoardNumber(row = 2, column = 3, number = 26),
                BingoBoardNumber(row = 2, column = 4, number = 20),
                BingoBoardNumber(row = 3, column = 0, number = 22),
                BingoBoardNumber(row = 3, column = 1, number = 11),
                BingoBoardNumber(row = 3, column = 2, number = 13),
                BingoBoardNumber(row = 3, column = 3, number = 6),
                BingoBoardNumber(row = 3, column = 4, number = 5),
                BingoBoardNumber(row = 4, column = 0, number = 2),
                BingoBoardNumber(row = 4, column = 1, number = 0),
                BingoBoardNumber(row = 4, column = 2, number = 12),
                BingoBoardNumber(row = 4, column = 3, number = 3),
                BingoBoardNumber(row = 4, column = 4, number = 7),
            )
        )
    )

    @Test
    fun `should map given input to bingo numbers & (non winning) boards`() {
        // When
        val bingoNumbersAndBoards = testInput.toNumbersAndBoards()

        // Then
        assertEquals(
            listOf(7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24, 10, 16, 13, 6, 15, 25, 12, 22, 18, 20, 8, 19, 3, 26, 1)
                    to expectedBingoBoards,
            bingoNumbersAndBoards
        )
        expectedBingoBoards.forEach { board -> assertFalse(board.isAWinner()) }
    }

    @Test
    fun `should detect winning board configuration`() {
        // When
        val winningBoard = BingoBoard(
            numbers = listOf(
                BingoBoardNumber(row = 0, column = 0, number = 14).apply { isDrawn = true },
                BingoBoardNumber(row = 0, column = 1, number = 21).apply { isDrawn = true },
                BingoBoardNumber(row = 0, column = 2, number = 17).apply { isDrawn = true },
                BingoBoardNumber(row = 0, column = 3, number = 24).apply { isDrawn = true },
                BingoBoardNumber(row = 0, column = 4, number = 4).apply { isDrawn = true },
                BingoBoardNumber(row = 1, column = 0, number = 10),
                BingoBoardNumber(row = 1, column = 1, number = 16),
                BingoBoardNumber(row = 1, column = 2, number = 15),
                BingoBoardNumber(row = 1, column = 3, number = 9).apply { isDrawn = true },
                BingoBoardNumber(row = 1, column = 4, number = 19),
                BingoBoardNumber(row = 2, column = 0, number = 18),
                BingoBoardNumber(row = 2, column = 1, number = 8),
                BingoBoardNumber(row = 2, column = 2, number = 23).apply { isDrawn = true },
                BingoBoardNumber(row = 2, column = 3, number = 26),
                BingoBoardNumber(row = 2, column = 4, number = 20),
                BingoBoardNumber(row = 3, column = 0, number = 22),
                BingoBoardNumber(row = 3, column = 1, number = 11).apply { isDrawn = true },
                BingoBoardNumber(row = 3, column = 2, number = 13),
                BingoBoardNumber(row = 3, column = 3, number = 6),
                BingoBoardNumber(row = 3, column = 4, number = 5).apply { isDrawn = true },
                BingoBoardNumber(row = 4, column = 0, number = 2).apply { isDrawn = true },
                BingoBoardNumber(row = 4, column = 1, number = 0).apply { isDrawn = true },
                BingoBoardNumber(row = 4, column = 2, number = 12),
                BingoBoardNumber(row = 4, column = 3, number = 3),
                BingoBoardNumber(row = 4, column = 4, number = 7).apply { isDrawn = true },
            )
        )

        // Then
        assertTrue(winningBoard.isAWinner())
    }

    @Test
    fun `should return third board as first winning board when number 24 is drawn`() {
        // Given
        val (numbers, boards) = testInput.toNumbersAndBoards()

        // When
        val firstWinner = boards.findFirstWinner(numbers)

        // Then
        assertNotNull(firstWinner) { (number, board) ->
            assertEquals(24, number)
            assertEquals(expectedBingoBoards[2], board)
            assertEquals(188, board.unmarkedNumbersSum())
            assertEquals(4512, board.unmarkedNumbersSum() * number)
        }
    }

    @Test
    fun `should return second board as last winning board when number 13 is drawn`() {
        // Given
        val (numbers, boards) = testInput.toNumbersAndBoards()

        // When
        val lastWinner = boards.findLastWinner(numbers)

        // Then
        assertNotNull(lastWinner) { (number, board) ->
            assertEquals(13, number)
            assertEquals(expectedBingoBoards[1], board)
            assertEquals(148, board.unmarkedNumbersSum())
            assertEquals(1924, board.unmarkedNumbersSum() * number)
        }
    }

}