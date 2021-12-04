package com.adventofcode.day4

import com.adventofcode.util.printResultWithTime
import com.adventofcode.util.readResourceLines

// region Bingo object representation

data class BingoBoardNumber(val row: Int, val column: Int, val number: Int) {
    var isDrawn: Boolean = false
}

data class BingoBoard(val numbers: List<BingoBoardNumber>) {

    fun drawNumber(number: Int) {
        numbers.filter { it.number == number }.onEach { it.isDrawn = true }
    }

    fun isAWinner(): Boolean {
        val winningRow = numbers.groupBy(BingoBoardNumber::row)
            .any { (_, rowNumbers) -> rowNumbers.all(BingoBoardNumber::isDrawn) }
        val winningColumn = numbers.groupBy(BingoBoardNumber::column)
            .any { (_, columnNumbers) -> columnNumbers.all(BingoBoardNumber::isDrawn) }
        return winningRow || winningColumn
    }

    fun unmarkedNumbersSum(): Int =
        numbers.filter { !it.isDrawn }.sumOf(BingoBoardNumber::number)
}

// endregion

// region Mapping methods

fun List<String>.toNumbersAndBoards(): Pair<List<Int>, List<BingoBoard>> =
    first().split(",").map(String::toInt) to drop(2).toBingoBoards()

private fun List<String>.toBingoBoards(): List<BingoBoard> =
    when (isEmpty()) {
        true -> emptyList()
        else -> {
            var bingoSize = 0
            val bingoBoard = takeWhile { bingoSize++; it.isNotBlank() }.toBingoBoard()
            listOf(bingoBoard) + drop(bingoSize).toBingoBoards()
        }
    }

private fun List<String>.toBingoBoard(): BingoBoard =
    BingoBoard(
        numbers = flatMapIndexed { row, line ->
            line.trim().split("\\s+".toRegex()).mapIndexed { column, number ->
                BingoBoardNumber(row = row, column = column, number = number.toInt())
            }
        }
    )

// endregion

// region Let's play Bingo!

fun List<BingoBoard>.findFirstWinner(numbers: List<Int>, numberIndex: Int = 0): Pair<Int, BingoBoard>? {
    val currentNumber = numbers[numberIndex]
    val winners = findWinners(currentNumber)
    return when {
        winners.isEmpty() -> findFirstWinner(numbers, numberIndex + 1)
        else -> currentNumber to winners.first()
    }
}

fun List<BingoBoard>.findLastWinner(numbers: List<Int>, numberIndex: Int = 0): Pair<Int, BingoBoard>? {
    val currentNumber = numbers[numberIndex]
    return when (val winners = findWinners(currentNumber)) {
        this -> currentNumber to winners.first()
        else -> minus(winners.toSet()).findLastWinner(numbers, numberIndex + 1)
    }
}

private fun List<BingoBoard>.findWinners(number: Int): List<BingoBoard> =
    onEach { board -> board.drawNumber(number) }.filter(BingoBoard::isAWinner)

// endregion

fun main() {
    val bingoInput = readResourceLines("/day4/input")

    printResultWithTime {
        val (numbers, boards) = bingoInput.toNumbersAndBoards()
        when (val firstWinningBoard = boards.findFirstWinner(numbers)) {
            null -> "No winning board found!"
            else -> firstWinningBoard.let { (number, board) ->
                """
                First winning bingo board = $board
                First winning bingo board unmarked numbers sum = ${board.unmarkedNumbersSum()}
                First winning number = $number
                Result = ${board.unmarkedNumbersSum()} x $number = ${board.unmarkedNumbersSum() * number}
                """.trimIndent()
            }
        }
    }

    printResultWithTime {
        val (numbers, boards) = bingoInput.toNumbersAndBoards()
        when (val lastWinningBoard = boards.findLastWinner(numbers)) {
            null -> "No winning board found!"
            else -> lastWinningBoard.let { (number, board) ->
                """
                Last winning bingo board = $board
                Last winning bingo board unmarked numbers sum = ${board.unmarkedNumbersSum()}
                Last winning number = $number
                Result = ${board.unmarkedNumbersSum()} x $number = ${board.unmarkedNumbersSum() * number}
                """.trimIndent()
            }
        }
    }
}