package com.adventofcode.day10

import kotlin.test.Test
import kotlin.test.assertEquals

class Day10Test {

    /**
     * **Corrupted** lines (index starts at `0`):
     * - 2: Expected `]`, but found `}` instead.
     * - 4: Expected `]`, but found `)` instead.
     * - 5: Expected `)`, but found `]` instead.
     * - 7: Expected `>`, but found `)` instead.
     * - 8: Expected `]`, but found `>` instead.
     *
     * All other lines are **incomplete**.
     */
    private val testInput = """
        [({(<(())[]>[[{[]{<()<>>
        [(()[<>])]({[<{<<[]>>(
        {([(<{}[<>[]}>{[]{[(<()>
        (((({<>}<{<{<>}{[]{[]{}
        [[<[([]))<([[{}[[()]]]
        [{[{({}]{}}([{[{{{}}([]
        {<[[]]>}<{[{[{[]{()[[[]
        [<(<(<(<{}))><([]([]()
        <{([([[(<>()){}]>(<<{{
        <{([{{}}[<[[[<>{}]]]>[]]
    """.trimIndent().lines()

    @Test
    fun `should return that given lines are corrupted`() {
        assertEquals(LineState.Corrupted(']'), "(]".getLineState())
        assertEquals(LineState.Corrupted('>'), "{()()()>".getLineState())
        assertEquals(LineState.Corrupted('}'), "(((()))}".getLineState())
        assertEquals(LineState.Corrupted(')'), "<([]){()}[{}])".getLineState())
    }

    @Test
    fun `should return that given lines are valid`() {
        assertEquals(LineState.Valid, "()".getLineState())
        assertEquals(LineState.Valid, "[]".getLineState())
        assertEquals(LineState.Valid, "([])".getLineState())
        assertEquals(LineState.Valid, "{()()()}".getLineState())
        assertEquals(LineState.Valid, "<([{}])>".getLineState())
        assertEquals(LineState.Valid, "[<>({}){}[([])<>]]".getLineState())
        assertEquals(LineState.Valid, "(((((((((())))))))))".getLineState())
    }

    @Test
    fun `should detect corrupted & incomplete lines in test input`() {
        assertEquals(LineState.Incomplete("}}]])})]"), testInput[0].getLineState())
        assertEquals(LineState.Incomplete(")}>]})"), testInput[1].getLineState())
        assertEquals(LineState.Corrupted('}'), testInput[2].getLineState())
        assertEquals(LineState.Incomplete("}}>}>))))"), testInput[3].getLineState())
        assertEquals(LineState.Corrupted(')'), testInput[4].getLineState())
        assertEquals(LineState.Corrupted(']'), testInput[5].getLineState())
        assertEquals(LineState.Incomplete("]]}}]}]}>"), testInput[6].getLineState())
        assertEquals(LineState.Corrupted(')'), testInput[7].getLineState())
        assertEquals(LineState.Corrupted('>'), testInput[8].getLineState())
        assertEquals(LineState.Incomplete("])}>"), testInput[9].getLineState())
    }

    @Test
    fun `should return a syntax error score of 26397 for given corrupted lines`() {
        assertEquals(26397, testInput.countCorruptedLinesSyntaxErrorScore())
    }

    @Test
    fun `should return a middle score of 288957 for given incomplete lines`() {
        assertEquals(288957, testInput.countIncompleteLinesMiddleScore())
    }
}