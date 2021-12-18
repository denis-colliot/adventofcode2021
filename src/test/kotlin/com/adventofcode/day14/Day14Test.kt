package com.adventofcode.day14

import kotlin.test.Test
import kotlin.test.assertEquals

class Day14Test {

    private val testInput = """
        NNCB

        CH -> B
        HH -> N
        CB -> H
        NH -> C
        HB -> C
        HC -> B
        HN -> C
        NN -> C
        BH -> H
        NC -> B
        NB -> B
        BN -> B
        BB -> N
        BC -> B
        CC -> N
        CN -> C
    """.trimIndent().lines()

    @Test
    fun `should parse input to polymer template and insertion rules`() {
        // When
        val (polymer, rules) = testInput.parseInput()

        // Then
        assertEquals(
            mapOf<String, Long>(
                "N" to 2,
                "C" to 1,
                "B" to 1,
                "NN" to 1,
                "NC" to 1,
                "CB" to 1
            ), polymer
        )
        assertEquals(
            mapOf(
                "CH" to 'B',
                "HH" to 'N',
                "CB" to 'H',
                "NH" to 'C',
                "HB" to 'C',
                "HC" to 'B',
                "HN" to 'C',
                "NN" to 'C',
                "BH" to 'H',
                "NC" to 'B',
                "NB" to 'B',
                "BN" to 'B',
                "BB" to 'N',
                "BC" to 'B',
                "CC" to 'N',
                "CN" to 'C'
            ), rules
        )
    }

    @Test
    fun `should apply insertion rules to polymer template`() {
        // Given
        val (polymer, rules) = testInput.parseInput()

        // When
        val result = polymer.applyRules(rules)

        // Then (NCNBCHB)
        assertEquals(
            mapOf<String, Long>(
                "N" to 2,
                "C" to 2,
                "B" to 2,
                "H" to 1,
                "NC" to 1,
                "CN" to 1,
                "NB" to 1,
                "BC" to 1,
                "CH" to 1,
                "HB" to 1
            ), result
        )
    }

    @Test
    fun `should apply insertion rules twice to polymer template`() {
        // Given
        val (polymer, rules) = testInput.parseInput()

        // When
        val result = polymer.applyRules(rules).applyRules(rules)

        // Then (NBCCNBBBCBHCB)
        assertEquals(
            mapOf<String, Long>(
                "N" to 2,
                "C" to 4,
                "B" to 6,
                "H" to 1,
                "NB" to 2,
                "BC" to 2,
                "CC" to 1,
                "CN" to 1,
                "BB" to 2,
                "CB" to 2,
                "BH" to 1,
                "HC" to 1,
            ), result
        )
    }

    @Test
    fun `should apply insertion rules third times to polymer template`() {
        // Given
        val (polymer, rules) = testInput.parseInput()

        // When
        val result = polymer.applyRules(rules).applyRules(rules).applyRules(rules)

        // Then (NBBBCNCCNBBNBNBBCHBHHBCHB)
        assertEquals(
            mapOf<String, Long>(
                "N" to 5,
                "C" to 5,
                "B" to 11,
                "H" to 4,
                "NB" to 4,
                "BB" to 4,
                "BC" to 3,
                "CN" to 2,
                "NC" to 1,
                "CC" to 1,
                "BN" to 2,
                "CH" to 2,
                "HB" to 3,
                "BH" to 1,
                "HH" to 1,
            ), result
        )
    }

    @Test
    fun `should return diff between most common element and least common element after step 10`() {
        // Given
        val (polymer, rules) = testInput.parseInput()

        // When
        val result = polymer.getMostCommonLeastCommonDiffAtStep(rules, 10)

        // Then
        assertEquals(1588, result)
    }

    @Test
    fun `should return diff between most common element and least common element after step 40`() {
        // Given
        val (polymer, rules) = testInput.parseInput()

        // When
        val result = polymer.getMostCommonLeastCommonDiffAtStep(rules, 40)

        // Then
        assertEquals(2188189693529, result)
    }
}
