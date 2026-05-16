
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

public class ValidPalindrome {

    // ── DRIVER CODE: To Test the Solution
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a string: ");
        String str = sc.nextLine();

        ValidPalindrome obj = new ValidPalindrome();

        if (obj.isPalindrome(str)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }

    // ── APPROACH 1: BRUTE FORCE ─────────────────────────────────────
    // T.C -> O(N)
    // S.C -> O(N)
    // public boolean isPalindrome(String s) {
    //     StringBuilder filtered = new StringBuilder();

    //     for (char c : s.toCharArray()) {
    //         if (Character.isLetterOrDigit(c)) {
    //             filtered.append(Character.toLowerCase(c));
    //         }
    //     }

    //     String clean = filtered.toString();
    //     String reversed = filtered.reverse().toString();

    //     return clean.equals(reversed);
    // }

    // ── APPROACH 2: OPTIMAL (Two Pointers) ──────────────────────────
    // T.C -> O(N)
    // S.C -> O(1)
    public boolean isPalindrome(String s) {

        // Edge case: not enough elements
        if (s == null || s.isEmpty()) {
            return true;
        }

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {

            // Skip non-alphanumeric from left
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }

            // Skip non-alphanumeric from right
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            // Compare (case-insensitive)
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}
