package com.adventofcode.day16

import kotlin.test.Test
import kotlin.test.assertEquals

class Day16Test {

    @Test
    fun `should transform hexadecimal value into bits`() {
        // Given
        val hexadecimal = "D2FE28"

        // When
        val bits = hexadecimal.hexadecimalToBits()

        // Then
        assertEquals("110100101111111000101000", bits)
    }

    @Test
    fun `should transform hexadecimal value '8A004A801A8002F478' into bits`() {
        // Given
        val hexadecimal = "8A004A801A8002F478"

        // When
        val bits = hexadecimal.hexadecimalToBits()

        // Then
        assertEquals("100010100000000001001010100000000001101010000000000000101111010001111000", bits)
    }

    @Test
    fun `should transform hexadecimal value '620080001611562C8802118E34' into bits`() {
        // Given
        val hexadecimal = "620080001611562C8802118E34"

        // When
        val bits = hexadecimal.hexadecimalToBits()

        // Then
        assertEquals(
            "01100010000000001000000000000000000101100001000101010110001011001000100000000010000100011000111000110100",
            bits
        )
    }

    @Test
    fun `should map hexadecimal '8A004A801A8002F478' to packets tree`() {
        // When
        val result = "8A004A801A8002F478".hexadecimalToPacketsTree()

        // Then
        assertEquals(
            Packet.Operator(
                version = 4,
                type = 2,
                subPackets = listOf(
                    Packet.Operator(
                        version = 1,
                        type = 2,
                        subPackets = listOf(
                            Packet.Operator(
                                version = 5,
                                type = 2,
                                subPackets = listOf(
                                    Packet.LiteralValue(
                                        version = 6,
                                        fullBits = "01111",
                                        decimalValue = 15
                                    )
                                )
                            )
                        )
                    )
                )
            ), result
        )
        assertEquals(16, result.sumOfVersions())
    }

    @Test
    fun `should map hexadecimal '620080001611562C8802118E34' to packets tree`() {
        // When
        val result = "620080001611562C8802118E34".hexadecimalToPacketsTree()

        // Then
        assertEquals(
            Packet.Operator(
                version = 3,
                type = 0,
                subPackets = listOf(
                    Packet.Operator(
                        version = 0,
                        type = 0,
                        subPackets = listOf(
                            Packet.LiteralValue(version = 0, fullBits = "01010", decimalValue = 10),
                            Packet.LiteralValue(version = 5, fullBits = "01011", decimalValue = 11)
                        )
                    ),
                    Packet.Operator(
                        version = 1,
                        type = 0,
                        subPackets = listOf(
                            Packet.LiteralValue(version = 0, fullBits = "01100", decimalValue = 12),
                            Packet.LiteralValue(version = 3, fullBits = "01101", decimalValue = 13)
                        )
                    )
                )
            ), result
        )
        assertEquals(12, result.sumOfVersions())
    }

    @Test
    fun `should map hexadecimal 'C0015000016115A2E0802F182340' to packets tree`() {
        // When
        val result = "C0015000016115A2E0802F182340".hexadecimalToPacketsTree()

        // Then
        assertEquals(
            Packet.Operator(
                version = 6,
                type = 0,
                subPackets = listOf(
                    Packet.Operator(
                        version = 0,
                        type = 0,
                        subPackets = listOf(
                            Packet.LiteralValue(version = 0, fullBits = "01010", decimalValue = 10),
                            Packet.LiteralValue(version = 6, fullBits = "01011", decimalValue = 11)
                        )
                    ),
                    Packet.Operator(
                        version = 4,
                        type = 0,
                        subPackets = listOf(
                            Packet.LiteralValue(version = 7, fullBits = "01100", decimalValue = 12),
                            Packet.LiteralValue(version = 0, fullBits = "01101", decimalValue = 13)
                        )
                    )
                )
            ), result
        )
        assertEquals(23, result.sumOfVersions())
    }

    @Test
    fun `should map hexadecimal 'A0016C880162017C3686B18A3D4780' to packets tree`() {
        // When
        val result = "A0016C880162017C3686B18A3D4780".hexadecimalToPacketsTree()

        // Then
        assertEquals(
            Packet.Operator(
                version = 5,
                type = 0,
                subPackets = listOf(
                    Packet.Operator(
                        version = 1,
                        type = 0,
                        subPackets = listOf(
                            Packet.Operator(
                                version = 3,
                                type = 0,
                                subPackets = listOf(
                                    Packet.LiteralValue(version = 7, fullBits = "00110", decimalValue = 6),
                                    Packet.LiteralValue(version = 6, fullBits = "00110", decimalValue = 6),
                                    Packet.LiteralValue(version = 5, fullBits = "01100", decimalValue = 12),
                                    Packet.LiteralValue(version = 2, fullBits = "01111", decimalValue = 15),
                                    Packet.LiteralValue(version = 2, fullBits = "01111", decimalValue = 15)
                                )
                            )
                        )
                    )
                )
            ), result
        )
        assertEquals(31, result.sumOfVersions())
    }

    @Test
    fun `should return 3 (1 + 2) as packet value for hexadecimal 'C200B40A82'`() {
        // Given
        val packet = "C200B40A82".hexadecimalToPacketsTree()

        // When
        val result = packet.computeValue()

        // Then
        assertEquals(3, result)
    }

    @Test
    fun `should return 54 (6 x 9) as packet value for hexadecimal '04005AC33890'`() {
        // Given
        val packet = "04005AC33890".hexadecimalToPacketsTree()

        // When
        val result = packet.computeValue()

        // Then
        assertEquals(54, result)
    }

    @Test
    fun `should return 7 (min of 7, 8, 9) as packet value for hexadecimal '880086C3E88112'`() {
        // Given
        val packet = "880086C3E88112".hexadecimalToPacketsTree()

        // When
        val result = packet.computeValue()

        // Then
        assertEquals(7, result)
    }

    @Test
    fun `should return 9 (max of 7, 8, 9) as packet value for hexadecimal 'CE00C43D881120'`() {
        // Given
        val packet = "CE00C43D881120".hexadecimalToPacketsTree()

        // When
        val result = packet.computeValue()

        // Then
        assertEquals(9, result)
    }

    @Test
    fun `should return 1 (5 less than 15) as packet value for hexadecimal 'D8005AC2A8F0'`() {
        // Given
        val packet = "D8005AC2A8F0".hexadecimalToPacketsTree()

        // When
        val result = packet.computeValue()

        // Then
        assertEquals(1, result)
    }

    @Test
    fun `should return 0 (5 not greater than 15) as packet value for hexadecimal 'F600BC2D8F'`() {
        // Given
        val packet = "F600BC2D8F".hexadecimalToPacketsTree()

        // When
        val result = packet.computeValue()

        // Then
        assertEquals(0, result)
    }

    @Test
    fun `should return 0 (5 not equal to 15) as packet value for hexadecimal '9C005AC2F8F0'`() {
        // Given
        val packet = "9C005AC2F8F0".hexadecimalToPacketsTree()

        // When
        val result = packet.computeValue()

        // Then
        assertEquals(0, result)
    }

    @Test
    fun `should return 1 (1 + 3 == 2 x 2) as packet value for hexadecimal '9C0141080250320F1802104A08'`() {
        // Given
        val packet = "9C0141080250320F1802104A08".hexadecimalToPacketsTree()

        // When
        val result = packet.computeValue()

        // Then
        assertEquals(1, result)
    }

}