package com.adventofcode.day10

import com.adventofcode.util.printResultWithTime
import com.adventofcode.util.readResourceLines
import java.util.*

private val chunksOpenCloseChars = mapOf(
    '(' to ')',
    '[' to ']',
    '{' to '}',
    '<' to '>'
)

sealed class LineState {
    object Valid : LineState()
    data class Corrupted(val firstCorruptedChar: Char) : LineState()
    data class Incomplete(val missingClosingSequence: String) : LineState()
}

fun String.getLineState(index: Int = 0, chunksOpening: Stack<Char> = Stack()): LineState =
    when (val char = getOrNull(index)) {
        null -> when (chunksOpening.isEmpty()) {
            true -> LineState.Valid
            else -> LineState.Incomplete(
                missingClosingSequence = chunksOpening.asReversed()
                    .joinToString(separator = "") { opening -> chunksOpenCloseChars[opening].toString() }
            )
        }
        in chunksOpenCloseChars.keys -> getLineState(index + 1, chunksOpening.apply { push(char) })
        else -> when (chunksOpening.isEmpty()) {
            true -> LineState.Corrupted(firstCorruptedChar = char)
            else -> when (chunksOpenCloseChars[chunksOpening.pop()] == char) {
                true -> getLineState(index + 1, chunksOpening)
                else -> LineState.Corrupted(firstCorruptedChar = char)
            }
        }
    }

fun List<String>.countCorruptedLinesSyntaxErrorScore(): Long =
    mapNotNull { line -> line.getLineState() as? LineState.Corrupted }
        .fold(0) { totalScore, corruptedState ->
            totalScore + when (corruptedState.firstCorruptedChar) {
                ')' -> 3
                ']' -> 57
                '}' -> 1197
                '>' -> 25137
                else -> 0
            }
        }

fun List<String>.countIncompleteLinesMiddleScore(): Long =
    mapNotNull { line -> line.getLineState() as? LineState.Incomplete }
        .map { incompleteState ->
            incompleteState.missingClosingSequence.fold(0L) { totalScore, missingClosingChar ->
                totalScore * 5 + when (missingClosingChar) {
                    ')' -> 1L
                    ']' -> 2L
                    '}' -> 3L
                    '>' -> 4L
                    else -> 0L
                }
            }
        }.sorted().let { scores -> scores[scores.size / 2] }

fun main() {
    val input = readResourceLines("/day10/input")

    printResultWithTime {
        "Corrupted lines syntax error score = ${input.countCorruptedLinesSyntaxErrorScore()}"
    }

    printResultWithTime {
        "Incomplete lines middle score = ${input.countIncompleteLinesMiddleScore()}"
    }
}