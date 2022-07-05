package leetcode

class P0128_LongestConsecutiveSequence {
    /*
    128. Longest Consecutive Sequence

    https://leetcode.com/problems/longest-consecutive-sequence/

Given an unsorted array of integers nums, return the length 
of the longest consecutive elements sequence.
You must write an algorithm that runs in O(n) time.

Example 1:
Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. 
Therefore its length is 4.

Example 2:
Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9

Constraints:
    0 <= nums.length <= 105
    -109 <= nums[i] <= 109

    Time complexity: O(n)
    Space complexity: O(n)
    */
    
    fun longestConsecutive(nums: IntArray): Int {
        val set = nums.toSet()
        
        var maxSeq = 0
        nums.forEach { n -> 
            if (!set.contains(n-1)) {
                var seq = 1
                var i = n + 1
                while(set.contains(i)) {
                    i++
                    seq++
                }
                
                maxSeq = maxOf(maxSeq, seq)
            }
        }
        
        return maxSeq
    }
}
