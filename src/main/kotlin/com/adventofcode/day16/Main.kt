package com.adventofcode.day16

import com.adventofcode.util.printResultWithTime
import com.adventofcode.util.readResourceLines

sealed class Packet {
    abstract val version: Int

    abstract fun sumOfVersions(): Int
    abstract fun computeValue(): Long

    data class LiteralValue(
        override val version: Int,
        val fullBits: String,
        val decimalValue: Long
    ) : Packet() {
        override fun sumOfVersions(): Int = version
        override fun computeValue(): Long = decimalValue
    }

    data class Operator(
        override val version: Int,
        val type: Int,
        val subPackets: List<Packet>
    ) : Packet() {
        override fun sumOfVersions(): Int = version + subPackets.sumOf(Packet::sumOfVersions)
        override fun computeValue(): Long = when (type) {
            0 -> subPackets.sumOf(Packet::computeValue)
            1 -> subPackets.fold(1L) { acc, subPacket -> acc * subPacket.computeValue() }
            2 -> subPackets.minOf(Packet::computeValue)
            3 -> subPackets.maxOf(Packet::computeValue)
            5 -> if (subPackets.first().computeValue() > subPackets.last().computeValue()) 1 else 0
            6 -> if (subPackets.first().computeValue() < subPackets.last().computeValue()) 1 else 0
            7 -> if (subPackets.first().computeValue() == subPackets.last().computeValue()) 1 else 0
            else -> error("Invalid packet type `$type`")
        }
    }
}

fun Char.hexadecimalToBits(): String =
    when (this) {
        '0' -> "0000"
        '1' -> "0001"
        '2' -> "0010"
        '3' -> "0011"
        '4' -> "0100"
        '5' -> "0101"
        '6' -> "0110"
        '7' -> "0111"
        '8' -> "1000"
        '9' -> "1001"
        'A' -> "1010"
        'B' -> "1011"
        'C' -> "1100"
        'D' -> "1101"
        'E' -> "1110"
        'F' -> "1111"
        else -> error("Not a valid hexadecimal value")
    }

fun String.hexadecimalToBits(): String =
    toCharArray().joinToString(separator = "") { it.hexadecimalToBits() }

fun String.hexadecimalToPacketsTree(): Packet =
    hexadecimalToBits().toPacketsTree().first()

// Should always return a list with a single root packet
private fun String.toPacketsTree(): List<Packet> {
    if (all { it == '0' }) {
        return emptyList()
    }
    val version = take(3).toInt(radix = 2)
    return when (val type = drop(3).take(3).toInt(radix = 2)) {
        4 /* Literal value */ -> {
            val (fullValueBits, valueRealValueBits) = drop(6).parseLiteralValue()
            val packet = Packet.LiteralValue(
                version = version,
                fullBits = fullValueBits,
                decimalValue = valueRealValueBits.toLong(radix = 2)
            )
            listOf(packet) + drop(6 + fullValueBits.length).toPacketsTree()
        }
        else /* Operator */ -> when (val lengthTypeId = drop(6).take(1)) {
            "0" /* Operator with length of sub-packets */ -> {
                val subPacketsLength = drop(7).take(15).toInt(radix = 2)
                listOf(
                    Packet.Operator(
                        version = version,
                        type = type,
                        subPackets = drop(22).take(subPacketsLength).toPacketsTree()
                    )
                ) + drop(22 + subPacketsLength).toPacketsTree()
            }
            "1" /* Operator with number of sub-packets */ -> {
                val subPacketsNumber = drop(7).take(11).toInt(radix = 2)
                val subPackets = drop(18).toPacketsTree()
                listOf(
                    Packet.Operator(
                        version = version,
                        type = type,
                        subPackets = subPackets.take(subPacketsNumber)
                    )
                ) + subPackets.drop(subPacketsNumber)
            }
            else -> error("Invalid length type id `$lengthTypeId`")
        }
    }
}

/**
 * @return Full bits of literal value with bits of decoded decimal value.
 */
fun String.parseLiteralValue(previousBits: String = "", valueBits: String = ""): Pair<String, String> {
    val bitsGroup = take(5)
    return when (val valueGroupHeaderBit = bitsGroup.first()) {
        '1' -> drop(5).parseLiteralValue(previousBits + bitsGroup, valueBits + bitsGroup.drop(1))
        '0' -> previousBits + bitsGroup to valueBits + bitsGroup.drop(1)
        else -> error("Invalid header bit value `$valueGroupHeaderBit`")
    }
}

fun main() {
    val input = readResourceLines("/day16/input").first()

    printResultWithTime {
        val packet = input.hexadecimalToPacketsTree()
        val sumOfVersions = packet.sumOfVersions()
        "Sum of packet versions = $sumOfVersions"
    }

    printResultWithTime {
        val packet = input.hexadecimalToPacketsTree()
        val packetValue = packet.computeValue()
        "Packet value = $packetValue"
    }
}