
/**
 * ╔══════════════════════════════════════════════════════════════╗
 * ║  Problem   : Valid Palindrome                               ║
 * ║  Link      : https://leetcode.com/problems/valid-palindrome/║
 * ║  Difficulty: Easy                                           ║
 * ║  Pattern   : Two Pointer (Converging)                       ║
 * ║  Topic     : Arrays & Strings                               ║
 * ╚══════════════════════════════════════════════════════════════╝
 *
 * PROBLEM STATEMENT:
 * Check if a string is a palindrome, considering only alphanumeric
 * characters and ignoring case.
 *
 * EXAMPLES:
 *   Input:  "A man, a plan, a canal: Panama"
 *   Output: true
 *
 *   Input:  "race a car"
 *   Output: false
 *
 * ── APPROACH 1: BRUTE FORCE ─────────────────────────────────────
 * Filter to alphanumeric only, convert to lowercase, compare with reverse.
 *
 *   Time  : O(n)
 *   Space : O(n) — filtered string stored
 *
 * ── APPROACH 2: OPTIMAL (Two Pointers) ──────────────────────────
 * Use two pointers starting at both ends.
 * Skip non-alphanumeric characters.
 * Compare characters case-insensitively.
 * Return false on first mismatch.
 *
 *   Time  : O(n)  — single pass, each char visited at most twice
 *   Space : O(1)  — only two pointer variables
 *
 * EDGE CASES:
 *   ✓ Empty string → true
 *   ✓ Only non-alphanumeric → true
 *   ✓ Single character → true
 */

import java.util.Scanner;

public class TwoSumII {
    // ── DRIVER CODE: To test the solution
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the size of the array: ");
        int n = sc.nextInt();

        System.out.println("Enter the elements of the array: ");
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        System.out.println("Enter the target sum: ");
        int target = sc.nextInt();

        TwoSum solution = new TwoSum();

        nums = solution.twoSum(nums, target);
        System.out.print("[");
        for (int i = 0; i < 2; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println("]");
    }

    // ── APPROACH 2: OPTIMAL (Two Pointers) ──────────────────────────
    public int[] twoSum(int[] numbers, int target) {

        // Edge case: not enough elements
        if (numbers == null || numbers.length < 2) {
            return new int[] {};
        }

        // Take two pointers
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {

            // Take the sum of the left index element and right index element
            int sum = numbers[left] + numbers[right];

            // First check for is sum equal to target
            if (sum == target) {
                // return the indexes of left and right pointer
                return new int[] { left + 1, right + 1 };
            }

            // Check whether sum is less than target
            else if (sum < target) {
                // move the left pointer forward
                left++;
            }

            // Check whether sum is greater than target
            else {
                // move the right pointer backward
                right++;
            }
        }

        return new int[] {};
    }
}
