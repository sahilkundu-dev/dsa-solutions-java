
/**
 * ╔═════════════════════════════════════════════════════════════════════════════════════════╗
 * ║  Problem   : Best Time to Buy and Sell Stock                                           ║
 * ║  Link      : https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/║
 * ║  Difficulty: Easy                                                                      ║
 * ║  Pattern   : Not Particulary a Two Pointer (Greedy Approach)                           ║
 * ║  Topic     : Arrays & Strings                                                          ║
 * ╚══════════════════════════════════════════════════════════════╝
 *
 * PROBLEM STATEMENT:
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * 
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different
 * day in the future to sell that stock.
 * 
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit,
 * return 0.
 * 
 *
 * EXAMPLES:
 *   Input:  prices = [7,1,5,3,6,4]
 *   Output: 5
 *
 *   Input:  prices = [7,6,4,3,1]
 *   Output: 0
 *
 * ── APPROACH 1: BRUTE FORCE ─────────────────────────────────────
 * Check every pair (i, j) where i < j. Track max profit.
 *
 *   Time  : O(n²) — nested loops
 *   Space : O(1)  - no extra storage 
 *
 * ── APPROACH 2: OPTIMAL ──────────────────────────
 * Key insight: For each sell day, we want to know the minimum price seen so far (best buy day before this).
 * Track minimum price as we scan left to right. 
 * At each day, calculate profit if we sell today (current price - min so far). 
 * Track max profit.
 *
 *   Time  : O(n)  — single pass
 *   Space : O(1)  - no extra storage 
 *
 * EDGE CASES:
 *   ✓ Empty array → return 0
 *   ✓ Return 0 if no profit possible
 */

import java.util.Scanner;

public class BestTimeToBuyAndSellStock {

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

        int profit = maxProfit(arr);
        System.out.println(profit);
    }

    // ── APPROACH 1: BRUTE FORCE ─────────────────────────────────────
    // public static int maxProfit(int[] arr) {
    //     int maxProfit = 0;
    //     for (int i = 0; i < arr.length; i++) {
    //         for (int j = i + 1; j < arr.length; j++) {
    //             int profit = arr[j] - arr[i];
    //             maxProfit = Math.max(maxProfit, profit);
    //         }
    //     }

    //     return maxProfit;
    // }

    // ── APPROACH 2: OPTIMAL ──────────────────────────
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length < 2) {
            return 0;
        }

        int minPrice = prices[0];
        int maxProfit = 0;

        for(int i=1; i<prices.length; i++) {
            int profit = prices[i] - minPrice;
            maxProfit = Math.max(maxProfit, profit);

            minPrice = Math.min(minPrice, prices[i]);
        }

        return maxProfit;
    }
}
