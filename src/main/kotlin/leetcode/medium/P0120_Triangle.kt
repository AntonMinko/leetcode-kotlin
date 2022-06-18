package leetcode.medium

class P0120_Triangle {
    fun minimumTotal(triangle: List<List<Int>>): Int {
        val n = triangle.size

        fun minAtPoint(i: Int, j: Int, memo: MutableMap<Pair<Int,Int>,Int>): Int {
            if (i == n) return 0
            if (j < 0 || j >= triangle[i].size) return Int.MAX_VALUE
            val key = Pair(i,j)
            if (!memo.containsKey(key)) {
                memo[key] = triangle[i][j] + minOf(minAtPoint(i+1,j,memo), minAtPoint(i+1,j+1,memo))
            }
            return memo[key]!!
        }

        return minAtPoint(0,0, mutableMapOf<Pair<Int,Int>,Int>())
    }
}
