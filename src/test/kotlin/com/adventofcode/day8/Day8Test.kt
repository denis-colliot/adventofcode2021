package com.adventofcode.day8

import kotlin.test.Test
import kotlin.test.assertEquals

class Day8Test {

    private val testInput = """
        be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe
        edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc
        fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg
        fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb
        aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea
        fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb
        dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe
        bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef
        egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb
        gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce
    """.trimIndent().lines()

    @Test
    fun `should count 26 output values with unique number of segments`() {
        // When
        val result = testInput.countDigitsWithUniqueSegmentsNumber()

        // Then
        assertEquals(26, result)
    }

    @Test
    fun `should decode 5353 as four output digits for given example input line`() {
        // Given
        val input = "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf"

        // When
        val result = input.decodeSingleOutputDigits()

        // Then
        assertEquals(5353, result)
    }

    @Test
    fun `should decode 8394 as four output digits for given input line with index 0`() {
        // Given
        val input = testInput[0]

        // When
        val result = input.decodeSingleOutputDigits()

        // Then
        assertEquals(8394, result)
    }

    @Test
    fun `should decode 9781 as four output digits for given input line with index 1`() {
        // Given
        val input = testInput[1]

        // When
        val result = input.decodeSingleOutputDigits()

        // Then
        assertEquals(9781, result)
    }

    @Test
    fun `should decode 1197 as four output digits for given input line with index 2`() {
        // Given
        val input = testInput[2]

        // When
        val result = input.decodeSingleOutputDigits()

        // Then
        assertEquals(1197, result)
    }

    @Test
    fun `should decode 9361 as four output digits for given input line with index 3`() {
        // Given
        val input = testInput[3]

        // When
        val result = input.decodeSingleOutputDigits()

        // Then
        assertEquals(9361, result)
    }

    @Test
    fun `should decode 4873 as four output digits for given input line with index 4`() {
        // Given
        val input = testInput[4]

        // When
        val result = input.decodeSingleOutputDigits()

        // Then
        assertEquals(4873, result)
    }

    @Test
    fun `should decode 8418 as four output digits for given input line with index 5`() {
        // Given
        val input = testInput[5]

        // When
        val result = input.decodeSingleOutputDigits()

        // Then
        assertEquals(8418, result)
    }

    @Test
    fun `should decode 4548 as four output digits for given input line with index 6`() {
        // Given
        val input = testInput[6]

        // When
        val result = input.decodeSingleOutputDigits()

        // Then
        assertEquals(4548, result)
    }

    @Test
    fun `should decode 1625 as four output digits for given input line with index 7`() {
        // Given
        val input = testInput[7]

        // When
        val result = input.decodeSingleOutputDigits()

        // Then
        assertEquals(1625, result)
    }

    @Test
    fun `should decode 8717 as four output digits for given input line with index 8`() {
        // Given
        val input = testInput[8]

        // When
        val result = input.decodeSingleOutputDigits()

        // Then
        assertEquals(8717, result)
    }

    @Test
    fun `should decode 4315 as four output digits for given input line with index 9`() {
        // Given
        val input = testInput[9]

        // When
        val result = input.decodeSingleOutputDigits()

        // Then
        assertEquals(4315, result)
    }

    @Test
    fun `should decode 61229 as sum of all decoded digits`() {
        // When
        val result = testInput.sumAllDecodedDigits()

        // Then
        assertEquals(61229, result)
    }
}