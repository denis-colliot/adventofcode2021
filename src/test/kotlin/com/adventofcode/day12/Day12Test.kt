package com.adventofcode.day12

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Day12Test {

    @Test
    fun `should detect small caves`() {
        assertTrue { "a".isSmallCave() }
        assertTrue { "start".isSmallCave() }
        assertTrue { "end".isSmallCave() }
        assertTrue { "xy".isSmallCave() }
        assertFalse { "A".isSmallCave() }
        assertFalse { "XY".isSmallCave() }
    }

    @Test
    fun `should return graph representation of given input`() {
        // Given
        val input = """
            start-A
            start-b
            A-c
            A-b
            b-d
            A-end
            b-end
        """.trimIndent().lines()

        // When
        val result = input.toGraph()

        // Then
        assertEquals(
            mapOf(
                "start" to setOf("A", "b"),
                "A" to setOf("start", "c", "b", "end"),
                "b" to setOf("start", "d", "end", "A"),
                "c" to setOf("A"),
                "d" to setOf("b"),
                "end" to setOf("A", "b")
            ), result
        )
    }

    @Test
    fun `should return 10 paths visiting small caves at most once from given input`() {
        // Given
        val input = """
            start-A
            start-b
            A-c
            A-b
            b-d
            A-end
            b-end
        """.trimIndent().lines()

        // When
        val result = input.toGraph().getPathsVisitingSmallCavesAtMostOnce()

        // Then
        assertEquals(
            setOf(
                listOf("start", "A", "b", "A", "c", "A", "end"),
                listOf("start", "A", "b", "A", "end"),
                listOf("start", "A", "b", "end"),
                listOf("start", "A", "c", "A", "b", "A", "end"),
                listOf("start", "A", "c", "A", "b", "end"),
                listOf("start", "A", "c", "A", "end"),
                listOf("start", "A", "end"),
                listOf("start", "b", "A", "c", "A", "end"),
                listOf("start", "b", "A", "end"),
                listOf("start", "b", "end")
            ), result
        )
    }

    @Test
    fun `should return 19 paths visiting small caves at most once from given input`() {
        // Given
        val input = """
            dc-end
            HN-start
            start-kj
            dc-start
            dc-HN
            LN-dc
            HN-end
            kj-sa
            kj-HN
            kj-dc
        """.trimIndent().lines()

        // When
        val result = input.toGraph().getPathsVisitingSmallCavesAtMostOnce()

        // Then
        assertEquals(
            setOf(
                listOf("start", "HN", "dc", "HN", "end"),
                listOf("start", "HN", "dc", "HN", "kj", "HN", "end"),
                listOf("start", "HN", "dc", "end"),
                listOf("start", "HN", "dc", "kj", "HN", "end"),
                listOf("start", "HN", "end"),
                listOf("start", "HN", "kj", "HN", "dc", "HN", "end"),
                listOf("start", "HN", "kj", "HN", "dc", "end"),
                listOf("start", "HN", "kj", "HN", "end"),
                listOf("start", "HN", "kj", "dc", "HN", "end"),
                listOf("start", "HN", "kj", "dc", "end"),
                listOf("start", "dc", "HN", "end"),
                listOf("start", "dc", "HN", "kj", "HN", "end"),
                listOf("start", "dc", "end"),
                listOf("start", "dc", "kj", "HN", "end"),
                listOf("start", "kj", "HN", "dc", "HN", "end"),
                listOf("start", "kj", "HN", "dc", "end"),
                listOf("start", "kj", "HN", "end"),
                listOf("start", "kj", "dc", "HN", "end"),
                listOf("start", "kj", "dc", "end")
            ), result
        )
    }

    @Test
    fun `should return 256 paths visiting small caves at most once from given input`() {
        // Given
        val input = """
            fs-end
            he-DX
            fs-he
            start-DX
            pj-DX
            end-zg
            zg-sl
            zg-pj
            pj-he
            RW-he
            fs-DX
            pj-RW
            zg-RW
            start-pj
            he-WI
            zg-he
            pj-fs
            start-RW
        """.trimIndent().lines()

        // When
        val result = input.toGraph().getPathsVisitingSmallCavesAtMostOnce()

        // Then
        assertEquals(226, result.size)
    }

    @Test
    fun `should return 36 paths visiting single small cave twice from given input`() {
        // Given
        val input = """
            start-A
            start-b
            A-c
            A-b
            b-d
            A-end
            b-end
        """.trimIndent().lines()

        // When
        val result = input.toGraph().getPathsVisitingSingleSmallCaveTwice()

        // Then
        assertEquals(
            setOf(
                listOf("start", "A", "b", "A", "b", "A", "c", "A", "end"),
                listOf("start", "A", "b", "A", "b", "A", "end"),
                listOf("start", "A", "b", "A", "b", "end"),
                listOf("start", "A", "b", "A", "c", "A", "b", "A", "end"),
                listOf("start", "A", "b", "A", "c", "A", "b", "end"),
                listOf("start", "A", "b", "A", "c", "A", "c", "A", "end"),
                listOf("start", "A", "b", "A", "c", "A", "end"),
                listOf("start", "A", "b", "A", "end"),
                listOf("start", "A", "b", "d", "b", "A", "c", "A", "end"),
                listOf("start", "A", "b", "d", "b", "A", "end"),
                listOf("start", "A", "b", "d", "b", "end"),
                listOf("start", "A", "b", "end"),
                listOf("start", "A", "c", "A", "b", "A", "b", "A", "end"),
                listOf("start", "A", "c", "A", "b", "A", "b", "end"),
                listOf("start", "A", "c", "A", "b", "A", "c", "A", "end"),
                listOf("start", "A", "c", "A", "b", "A", "end"),
                listOf("start", "A", "c", "A", "b", "d", "b", "A", "end"),
                listOf("start", "A", "c", "A", "b", "d", "b", "end"),
                listOf("start", "A", "c", "A", "b", "end"),
                listOf("start", "A", "c", "A", "c", "A", "b", "A", "end"),
                listOf("start", "A", "c", "A", "c", "A", "b", "end"),
                listOf("start", "A", "c", "A", "c", "A", "end"),
                listOf("start", "A", "c", "A", "end"),
                listOf("start", "A", "end"),
                listOf("start", "b", "A", "b", "A", "c", "A", "end"),
                listOf("start", "b", "A", "b", "A", "end"),
                listOf("start", "b", "A", "b", "end"),
                listOf("start", "b", "A", "c", "A", "b", "A", "end"),
                listOf("start", "b", "A", "c", "A", "b", "end"),
                listOf("start", "b", "A", "c", "A", "c", "A", "end"),
                listOf("start", "b", "A", "c", "A", "end"),
                listOf("start", "b", "A", "end"),
                listOf("start", "b", "d", "b", "A", "c", "A", "end"),
                listOf("start", "b", "d", "b", "A", "end"),
                listOf("start", "b", "d", "b", "end"),
                listOf("start", "b", "end")
            ), result
        )
    }

    @Test
    fun `should return 103 paths visiting single small cave twice from given input`() {
        // Given
        val input = """
            dc-end
            HN-start
            start-kj
            dc-start
            dc-HN
            LN-dc
            HN-end
            kj-sa
            kj-HN
            kj-dc
        """.trimIndent().lines()

        // When
        val result = input.toGraph().getPathsVisitingSingleSmallCaveTwice()

        // Then
        assertEquals(103, result.size)
    }

    @Test
    fun `should return 3509 paths visiting single small cave twice from given input`() {
        // Given
        val input = """
            fs-end
            he-DX
            fs-he
            start-DX
            pj-DX
            end-zg
            zg-sl
            zg-pj
            pj-he
            RW-he
            fs-DX
            pj-RW
            zg-RW
            start-pj
            he-WI
            zg-he
            pj-fs
            start-RW
        """.trimIndent().lines()

        // When
        val result = input.toGraph().getPathsVisitingSingleSmallCaveTwice()

        // Then
        assertEquals(3509, result.size)
    }
}