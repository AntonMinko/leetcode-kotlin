
import leetcode.hard.P0745PrefixAndSuffixSearch

object Application {
    @JvmStatic
    fun main(args: Array<String>) {
        val sut = P0745PrefixAndSuffixSearch(arrayOf("apple","banana"))
        println(sut.f("ap","le"))
        println(sut.f("bananad","banana"))
        println(sut.f("appp","le"))
    }
}
