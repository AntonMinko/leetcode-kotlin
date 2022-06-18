package hard

import leetcode.hard.P0745PrefixAndSuffixSearch
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class P0745PrefixAndSuffixSearchTests {
    @Test
    fun test_1() {
        val input = arrayOf(
            "a", "b", "ba", "bca", "bda", "baba"
        )
        val sut = P0745PrefixAndSuffixSearch(input)
        val result = sut.f("ba", "ba")
        assertEquals(5, result)
    }

    @Test
    fun test_2() {
        val input = arrayOf(
            "apple","banana"
        )
        val sut = P0745PrefixAndSuffixSearch(input)
        val result = sut.f("banana","banana")
        assertEquals(1, result)
    }
}
