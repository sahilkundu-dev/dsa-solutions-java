
/**
 * ╔══════════════════════════════════════════════════════════════╗
 * ║  Problem   : Two Sum                                        ║
 * ║  Link      : https://leetcode.com/problems/two-sum/         ║
 * ║  Difficulty: Easy                                           ║
 * ║  Pattern   : HashMap / Two Pass                             ║
 * ║  Topic     : Arrays & Strings                               ║
 * ╚══════════════════════════════════════════════════════════════╝
 *
 * PROBLEM STATEMENT:
 * Given an array of integers nums and a target integer,
 * return indices of the two numbers that add up to target.
 * You may not use the same element twice.
 *
 * EXAMPLES:
 *   Input:  nums = [2,7,11,15], target = 9
 *   Output: [0,1]   (because nums[0] + nums[1] = 2 + 7 = 9)
 *
 *   Input:  nums = [3,2,4], target = 6
 *   Output: [1,2]
 *
 * ── APPROACH 1: BRUTE FORCE ─────────────────────────────────────
 * For every element, scan all elements after it.
 * Check if any two sum to target.
 *
 *   Time  : O(n²)  — nested loops
 *   Space : O(1)   — no extra storage
 *
 * ── APPROACH 2: OPTIMAL (HashMap) ───────────────────────────────
 * For each number, compute complement = target - num.
 * Check if complement already exists in a HashMap.
 * If yes → found our pair. If no → store current number.
 *
 * Key insight: replace the O(n) inner loop search with O(1)
 * HashMap lookup. One pass, done.
 *
 *   Time  : O(n)   — single pass through array
 *   Space : O(n)   — HashMap stores at most n entries
 *
 * EDGE CASES HANDLED:
 *   ✓ null or empty array   → returns empty array
 *   ✓ same element twice    → check complement BEFORE inserting
 *   ✓ negative numbers      → HashMap handles naturally
 */

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class TwoSum {

    // Driver code to test the solution
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

    // ── OPTIMAL SOLUTION ────────────────────────────────────────
    public int[] twoSum(int[] nums, int target) {

        // Edge case: not enough elements
        if (nums == null || nums.length < 2) {
            return new int[] { -1, -1 };
        }

        // HashMap to store number and its index
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int compliment = target - nums[i];

            // Check if compliment exists in map
            if (map.containsKey(compliment)) {
                return new int[] { map.get(compliment), i };
            }

            // Store current number and index in map
            map.put(nums[i], i);
        }

        // No pair found
        return new int[] { -1, -1 };
    }

    // ── BRUTE FORCE SOLUTION ───────────────────────────────────
    public int[] twoSumBruteForce(int[] nums, int target) {
        // Edge case: not enough elements
        if (nums == null || nums.length < 2) {
            return new int[] { -1, -1 };
        }

        // Check every pair of numbers
        for (int i = 0; i < nums.length - 1; i++) {

            // For each number, check all numbers after it
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] { i, j };
                }
            }
        }

        // No pair found
        return new int[] { -1, -1 };
    }
}
