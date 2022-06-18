package leetcode.medium

class P0583_DeleteForTwoStrings {
    /*
    583. Delete Operation for Two Strings
    https://leetcode.com/problems/delete-operation-for-two-strings/

Given two strings word1 and word2, return the minimum number of steps required 
to make word1 and word2 the same.
In one step, you can delete exactly one character in either string.

Example 1:
Input: word1 = "sea", word2 = "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".

Example 2:
Input: word1 = "leetcode", word2 = "etco"
Output: 4

Constraints:
    1 <= word1.length, word2.length <= 500
    word1 and word2 consist of only lowercase English letters.

    */
    fun minDistance(word1: String, word2: String): Int {
        val memo = mutableMapOf<Pair<Int,Int>, Int>()
        fun getDistance(i:Int, j:Int): Int {
            if (word1.length == i && word2.length == j) return 0
            if (word1.length == i) return word2.length - j
            if (word2.length == j) return word1.length - i
            if (word1[i] == word2[j]) return getDistance(i+1, j+1)
            
            val key = Pair(i,j)
            if (memo[key] == null) {
                memo[key] = minOf(getDistance(i+1, j), getDistance(i, j+1))
            }
            return memo[key]!! + 1
        }
        
        return getDistance(0,0)
    }
}
