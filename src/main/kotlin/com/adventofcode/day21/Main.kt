package com.adventofcode.day21

import com.adventofcode.util.printResultWithTime
import com.adventofcode.util.readResourceLines

typealias Die = Int
typealias DieRolls = List<Int>

data class Game(val player1: Player, val player2: Player)
data class Player(val name: String, val space: Int, val score: Int = 0, val isWinner: Boolean = false)

fun List<String>.parseInput(): Game {
    val regex = "Player \\d+ starting position: (\\d+)".toRegex()
    val player1 = regex.matchEntire(first())!!.destructured
        .let { (position) -> Player(name = "Player 1", space = position.toInt()) }
    val player2 = regex.matchEntire(last())!!.destructured
        .let { (position) -> Player(name = "Player 2", space = position.toInt()) }
    return Game(player1 = player1, player2 = player2)
}

fun Game.play(dieRollsTotalCount: Int = 0, latestDieRoll: Die = 0, currentPlayer: Player = player1): Int =
    when {
        player1.isWinner -> player2.score * dieRollsTotalCount
        player2.isWinner -> player1.score * dieRollsTotalCount
        else -> {
            val nextDieRolls = latestDieRoll.roll(3)
            when (currentPlayer) {
                player1 -> copy(player1 = player1.play(nextDieRolls)).play(
                    dieRollsTotalCount = dieRollsTotalCount + nextDieRolls.size,
                    latestDieRoll = nextDieRolls.last(),
                    currentPlayer = player2
                )
                else -> copy(player2 = player2.play(nextDieRolls)).play(
                    dieRollsTotalCount = dieRollsTotalCount + nextDieRolls.size,
                    latestDieRoll = nextDieRolls.last(),
                    currentPlayer = player1
                )
            }
        }
    }

fun Die.roll(rollsNumber: Int): DieRolls =
    (this + 1..this + rollsNumber).map { it.modulo(100) }

fun Player.play(dieRolls: DieRolls): Player {
    val newSpace = (space + dieRolls.sum()).modulo(10)
    val newScore = score + newSpace
    return Player(name = name, space = newSpace, score = newScore, isWinner = newScore >= 1000)
}

private fun Int.modulo(modulo: Int): Int =
    when (val moduloResult = this % modulo) {
        0 -> modulo
        else -> moduloResult
    }

fun main() {
    val input = readResourceLines("/day21/input")

    printResultWithTime {
        val game = input.parseInput()
        "Day 21 result = ${game.play()}"
    }
}