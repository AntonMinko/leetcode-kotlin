package leetcode

class P0665_NonDecreasingArray {
    /*
        665. Non-decreasing Array
        https://leetcode.com/problems/non-decreasing-array/

    Given an array nums with n integers, your task is to check if it could become
    non-decreasing by modifying at most one element.
    We define an array is non-decreasing if nums[i] <= nums[i + 1] holds for
    every i (0-based) such that (0 <= i <= n - 2).

    Example 1:
    Input: nums = [4,2,3]
    Output: true
    Explanation: You could modify the first 4 to 1 to get a non-decreasing array.

    Example 2:
    Input: nums = [4,2,1]
    Output: false
    Explanation: You can't get a non-decreasing array by modify at most one element.

    Constraints:
        n == nums.length
        1 <= n <= 104
        -105 <= nums[i] <= 105

        Time complexity: O(n)
        Space complexity: O(1)
        */

    fun checkPossibility(nums: IntArray): Boolean {
        // Start with scanning pairs of adjacent elements (i == 1):
        // 2,(3,1),4
        // When there is misplaced element, there are two ways to fix it
        // depending on the elements before and after the current pair:
        // - make the left element smaller: (1,1)
        // - make the right element larger: (3,3)
        // It can also be that both elements outside of the pair are smaller
        // than the pair element.
        // 1,(4,3),2
        // In this case we cannot construct non-decreasing array (the last else branch).

        val n = nums.size
        if (n <= 2) return true

        var isModified = false
        for (i in 0..(n - 2)) {
            if (nums[i] <= nums[i + 1]) continue
            if (isModified) return false

            when {
                isModified -> return false
                i == 0 || nums[i - 1] <= nums[i + 1] -> {
                    nums[i] = nums[i + 1]
                    isModified = true
                }
                i == n - 2 || nums[i] <= nums[i + 2] -> {
                    nums[i + 1] = nums[i]
                    isModified = true
                }
                else -> return false
            }
        }

        return true
    }
}
