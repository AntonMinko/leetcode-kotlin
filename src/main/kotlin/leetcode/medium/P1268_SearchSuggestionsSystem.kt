package leetcode

class P1268_SearchSuggestionsSystem {
    /*
    1268. Search Suggestions System
    https://leetcode.com/problems/search-suggestions-system/

You are given an array of strings products and a string searchWord.
Design a system that suggests at most three product names from products after each 
character of searchWord is typed. Suggested products should have common prefix with searchWord. 
If there are more than three products with a common prefix return the three lexicographically 
minimums products.
Return a list of lists of the suggested products after each character of searchWord is typed.

Example 1:
Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
Output: [
["mobile","moneypot","monitor"],
["mobile","moneypot","monitor"],
["mouse","mousepad"],
["mouse","mousepad"],
["mouse","mousepad"]
]
Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
After typing mou, mous and mouse the system suggests ["mouse","mousepad"]

Example 2:
Input: products = ["havana"], searchWord = "havana"
Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]

Example 3:
Input: products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
Output: [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]

Constraints:
    1 <= products.length <= 1000
    1 <= products[i].length <= 3000
    1 <= sum(products[i].length) <= 2 * 104
    All the strings of products are unique.
    products[i] consists of lowercase English letters.
    1 <= searchWord.length <= 1000
    searchWord consists of lowercase English letters.
    */
    
    class TrieNode {
        val children: Array<TrieNode?> = Array(26, { _ -> null })
        var word: String? = null
        var memo: List<TrieNode>? = null
        
        fun addWord(word: String) {
            var cur = this
            for(ch in word) {
                val i = ch - 'a'
                if (cur.children[i] == null) {
                    cur.children[i] = TrieNode()
                }
                cur = cur.children[i]!!
            }
            cur.word = word
        }
        
        fun traverse(): List<TrieNode> {
            if (memo == null) {
                memo = listOf(this)
                .plus(children
                    .filterNotNull()
                    .map { it.traverse() }
                    .flatten())
                .filterNotNull()
            }
            return memo!!
        }
        
        fun traverseWhile(prefix: String): TrieNode? {
            var cur = this
            for(ch in prefix) {
                val i = ch - 'a'
                if (cur.children[i] != null) {
                    cur = cur.children[i]!!
                }
                else {
                    return null
                }
            }
            return cur
        }
        
        fun findWords(): List<String> =
            this.traverse()
                ?.filter { it.word != null }
                ?.map { it.word!! }
                ?: listOf()
         
    }
    
    fun suggestedProducts(products: Array<String>, searchWord: String): List<List<String>> {
        val trie = TrieNode().also { root -> 
            products.forEach { root.addWord(it) }
        }
        
        val result = mutableListOf<List<String>>()
        
        var cur: TrieNode? = trie
        for(ch in searchWord) {
            val i = ch - 'a'
            cur = cur?.children?.get(i)
            if (cur != null) {
                result += cur.findWords().take(3)
            }
            else {
                result += listOf<String>()
            }
        }
            
        return result
    }
}
