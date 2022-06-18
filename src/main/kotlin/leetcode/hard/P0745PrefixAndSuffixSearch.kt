package leetcode.hard

class P0745PrefixAndSuffixSearch(words: Array<String>) {
    /*
745. Prefix and Suffix Search
https://leetcode.com/problems/prefix-and-suffix-search/

Design a special dictionary with some words that searchs the words in it by a prefix and a suffix.

Implement the WordFilter class:
    WordFilter(string[] words) Initializes the object with the words in the dictionary.
    f(string prefix, string suffix) Returns the index of the word in the dictionary,
    which has the prefix prefix and the suffix suffix. If there is more than one valid index,
    return the largest of them. If there is no such word in the dictionary, return -1.

Example 1:
Input
["WordFilter", "f"]
[[["apple"]], ["a", "e"]]
Output
[null, 0]

Explanation
WordFilter wordFilter = new WordFilter(["apple"]);
wordFilter.f("a", "e"); // return 0, because the word at index 0 has prefix = "a" and suffix = 'e".

Constraints:
    1 <= words.length <= 15000
    1 <= words[i].length <= 10
    1 <= prefix.length, suffix.length <= 10
    words[i], prefix and suffix consist of lower-case English letters only.
    At most 15000 calls will be made to the function f.

    Time complexity: O(n) where n - number of chars in words
    Space complexity: O(n)
    */

    data class TrieNode(
        val indexes: MutableList<Int> = mutableListOf(),
        val children: Array<TrieNode?> = Array(26, { _ -> null })
    ) {

        fun addWord(word: String, index: Int) =
            word.fold(mutableListOf(this)) { nodes, ch ->
                val i = ch - 'a'
                val cur = nodes.last()
                if (cur.children[i] == null) {
                    cur.children[i] = TrieNode()
                }
                cur.children[i]!!.indexes.add(index)
                nodes.add(cur.children[i]!!)
                nodes
            }

        fun findIndexes(prefix: String): List<Int> {
            var cur = this
            for(ch in prefix) {
                val i = ch - 'a'
                if (cur.children[i] == null) return listOf()
                cur = cur.children[i]!!
            }
            return cur.indexes
        }
    }

    private val prefixes = TrieNode()
    private val suffixes = TrieNode()
    private val memo = mutableMapOf<Pair<String,String>,Int>()

    init {
        words.forEachIndexed { i, w -> prefixes.addWord(w, i) }
        words.forEachIndexed {i, w -> suffixes.addWord(w.reversed(), i) }
    }

    fun f(prefix: String, suffix: String): Int {
        val key = Pair(prefix,suffix)
        if (!memo.containsKey(key)) {
            val pref = prefixes.findIndexes(prefix)
            val suff = suffixes.findIndexes(suffix.reversed())
            memo[key] = pref.intersect(suff).maxOrNull() ?: -1
        }

        return memo[key]!!
    }
}
