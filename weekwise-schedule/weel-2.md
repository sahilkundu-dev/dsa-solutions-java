# 📅 WEEK 2 — Complete Day-by-Day Study Plan

**Sahil Kundu's FAANG Preparation · Month 1 · Week 2**

---

## 🎯 Week 2 Overview

**Dates:** [Your Week 2 Start] to [Your Week 2 End]

**Topics This Week:**
- Sliding Window (Advanced) — Variable constraints, Multiple conditions
- String Manipulation — StringBuilder, Character arrays, In-place operations
- Advanced Array Problems — Pattern combination
- LinkedList Introduction — Pointer manipulation, Node traversal
- Stack Fundamentals — LIFO operations, Monotonic Stack intro

**Problems Target:** 12 problems
**Daily Commitment:** 6 hours weekdays · 10 hours weekend
**Week Total:** ~152 hours

**Success Criteria:**
By Sunday night, you should be able to:
- Identify Sliding Window variants in under 45 seconds
- Manipulate strings in-place without hesitation
- Combine two patterns (e.g., HashMap + Sliding Window) in one solution
- Explain when to use Stack vs other data structures
- Solve LinkedList pointer problems without drawing diagrams

**Week 2 Difficulty Jump:**
Week 1 taught you individual patterns in isolation. Week 2 teaches you to:
- **Recognize patterns** when they're not labeled
- **Combine patterns** when one isn't enough
- **Adapt patterns** to variants you haven't seen

This is where pattern recognition becomes a skill, not just memorization.

---

## 📖 How to Use This Plan

Same rules as Week 1:
1. Complete segments sequentially
2. Meet "Done When" criteria before moving forward
3. Take 10-15 min breaks between segments
4. Ask me questions immediately when stuck
5. Update PROGRESS.md daily
6. Log mistakes in ERROR_JOURNAL.md

**New this week:**
- **Pattern combination drills** — some problems need 2+ patterns
- **Timed problem-solving** — you'll have time targets per problem
- **Narration practice** — explain your approach out loud as you code

---

# Day 1 — Monday

**Daily Target:** 6 hours total
**Focus:** Sliding Window Advanced + String Fundamentals

---

## Segment 1: Week 1 Spaced Repetition — The Critical Three

**Estimated Time:** 25–30 minutes

### What You're Doing

Re-solve these three from Week 1 cold:

1. **Subarray Sum Equals K** (HashMap + Prefix) — 10 min target
2. **Maximum Subarray** (Kadane's) — 8 min target
3. **Sort Colors** (Dutch National Flag) — 10 min target

**Why these three:** They're the hardest patterns from Week 1. If you can solve them cold in under 30 minutes total, Week 1 patterns have solidified.

**Time limit:** 30 minutes strict. If any problem takes longer, you know which pattern needs review this week.

**Done When:**
- All three solved from memory in under 30 minutes
- Can explain the pattern in one sentence each
- No syntax errors

---

## Segment 2: Sliding Window Deep Dive — Variable Constraints

**Estimated Time:** 90–110 minutes

### What You're Learning

Week 1 introduced Sliding Window with simple constraints:
- Fixed size (max sum of k elements)
- Single constraint (no repeating characters)

Week 2 adds complexity:
- **Multiple constraints** — "at most k distinct" + "minimum length"
- **Frequency conditions** — "all characters must appear at least twice"
- **Nested validity checks** — "valid if sum ≥ target AND length ≥ minLen"

The template is the same. The complexity is in defining "valid."

### The Two Questions — Still Fundamental

Before writing any sliding window code, answer:

1. **What makes the window valid?**
2. **What forces the window to shrink?**

**Example 1: Longest Substring with At Most K Distinct Characters**

**Question 1:** Valid if number of distinct characters ≤ k  
**Question 2:** Shrink when distinct count > k

**Example 2: Minimum Window Substring**

**Question 1:** Valid if window contains all characters from pattern  
**Question 2:** Shrink while valid (to find minimum)

Notice: Sometimes we shrink to find a **maximum** (when condition is "at most"), sometimes to find a **minimum** (when condition is "at least").

### Pattern: At Most K Distinct Characters

**Problem:** Find the length of the longest substring with at most k distinct characters.

```
Input:  s = "eceba", k = 2
Output: 3
Explanation: "ece" has 2 distinct characters

Input:  s = "aa", k = 1
Output: 2
```

**Approach:**
- Expand window from right
- Track distinct characters in HashMap
- When distinct count > k, shrink from left until count ≤ k

**Code:**

```java
public int lengthOfLongestSubstringKDistinct(String s, int k) {
    if (k == 0) return 0;
    
    Map<Character, Integer> freq = new HashMap<>();
    int left = 0;
    int maxLen = 0;
    
    for (int right = 0; right < s.length(); right++) {
        char c = s.charAt(right);
        freq.put(c, freq.getOrDefault(c, 0) + 1);
        
        // Shrink while invalid (too many distinct)
        while (freq.size() > k) {
            char leftChar = s.charAt(left);
            freq.put(leftChar, freq.get(leftChar) - 1);
            if (freq.get(leftChar) == 0) {
                freq.remove(leftChar);  // distinct count decreases
            }
            left++;
        }
        
        maxLen = Math.max(maxLen, right - left + 1);
    }
    
    return maxLen;
}
```

**Time:** O(n)  
**Space:** O(k) — HashMap stores at most k distinct characters

**Key insight:** We only remove from HashMap when count reaches 0 — that's when distinct count decreases.

### Pattern: Minimum Window Substring (Advanced)

This is one of the hardest Sliding Window problems. Master it, and most interview sliding window questions become trivial.

**Minimum Window Substring** — LeetCode #76  
https://leetcode.com/problems/minimum-window-substring/

> Given strings `s` and `t`, return the minimum window substring of `s` such that every character in `t` (including duplicates) is included in the window. If no such window exists, return empty string.

**Examples:**
```
Input:  s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"

Input:  s = "a", t = "a"
Output: "a"

Input:  s = "a", t = "aa"
Output: ""
```

**Challenge:** You need ALL characters from `t`, respecting frequency.

**Approach:**
1. Build frequency map of `t` — what we need
2. Expand window, tracking what we have
3. When window is valid (contains all of `t`), try to shrink it while maintaining validity
4. Track the smallest valid window

**The "formed" counter trick:**
- Instead of comparing two HashMaps every iteration (expensive)
- Track: how many **distinct characters** have reached their required frequency
- When `formed == required`, window is valid

**Code:**

```java
public String minWindow(String s, String t) {
    if (s.isEmpty() || t.isEmpty()) return "";
    
    // What we need
    Map<Character, Integer> need = new HashMap<>();
    for (char c : t.toCharArray()) {
        need.put(c, need.getOrDefault(c, 0) + 1);
    }
    
    // What our current window has
    Map<Character, Integer> have = new HashMap<>();
    
    int required = need.size();  // distinct characters we need to satisfy
    int formed = 0;              // distinct characters currently satisfied
    
    int left = 0;
    int minLen = Integer.MAX_VALUE;
    int minLeft = 0, minRight = 0;
    
    for (int right = 0; right < s.length(); right++) {
        // Expand: add right character
        char c = s.charAt(right);
        have.put(c, have.getOrDefault(c, 0) + 1);
        
        // Check if this character's requirement is now satisfied
        if (need.containsKey(c) && have.get(c).intValue() == need.get(c).intValue()) {
            formed++;
        }
        
        // Shrink from left while valid (to find minimum)
        while (formed == required && left <= right) {
            // Update result if this window is smaller
            int windowLen = right - left + 1;
            if (windowLen < minLen) {
                minLen = windowLen;
                minLeft = left;
                minRight = right;
            }
            
            // Remove left character
            char leftChar = s.charAt(left);
            have.put(leftChar, have.get(leftChar) - 1);
            
            // Check if we just broke a requirement
            if (need.containsKey(leftChar) && have.get(leftChar) < need.get(leftChar)) {
                formed--;
            }
            
            left++;
        }
    }
    
    return minLen == Integer.MAX_VALUE ? "" : s.substring(minLeft, minRight + 1);
}
```

**Time:** O(|s| + |t|)  
**Space:** O(|s| + |t|)

**Trace example:**

```
s = "ADOBECODEBANC", t = "ABC"

need = {A:1, B:1, C:1}, required = 3

right=0 (A): have={A:1}, formed=1 (A satisfied)
right=1 (D): have={A:1, D:1}, formed=1
right=2 (O): have={A:1, D:1, O:1}, formed=1
right=3 (B): have={A:1, D:1, O:1, B:1}, formed=2 (B satisfied)
right=4 (E): have={A:1, D:1, O:1, B:1, E:1}, formed=2
right=5 (C): have={A:1, D:1, O:1, B:1, E:1, C:1}, formed=3 ✓ VALID

Now shrink:
  "ADOBEC" (len=6) → record
  Remove A: formed=2, invalid → stop shrinking
  
right=6 (O): still invalid (formed=2)
...continue until right=12 (C):
  "ODEBANC" formed=3 ✓ VALID
  Shrink: "DEBANC", "EBANC", "BANC" (len=4) ← MINIMUM

Result: "BANC"
```

### Resources

**YouTube:**
1. [Minimum Window Substring — NeetCode (18 min)](https://www.youtube.com/watch?v=jSto0O4AJbM) — excellent walkthrough
2. [Sliding Window Pattern — Back To Back SWE (15 min)](https://www.youtube.com/watch?v=MK-NZ4hN7rs) — general strategy
3. [Longest Substring K Distinct — takeUforward (14 min)](https://www.youtube.com/watch?v=teM9ZsVRQyc)

**Documentation:**
1. [Sliding Window Technique — LeetCode Explore](https://leetcode.com/explore/learn/card/sliding-window/)
2. [Minimum Window Substring — LeetCode Solution](https://leetcode.com/problems/minimum-window-substring/solution/)

**Done When:**
- You understand the "formed" counter optimization
- Can trace through Minimum Window Substring on paper
- Understand when to shrink for maximum vs minimum

---

## Segment 3: Problem — Permutation in String

**Estimated Time:** 35–45 minutes

### The Problem

**Permutation in String** — LeetCode #567  
https://leetcode.com/problems/permutation-in-string/

> Given two strings `s1` and `s2`, return `true` if `s2` contains a permutation of `s1`, or `false` otherwise.

**Examples:**
```
Input:  s1 = "ab", s2 = "eidbaooo"
Output: true
Explanation: s2 contains "ba" which is a permutation of "ab"

Input:  s1 = "ab", s2 = "eidboaoo"
Output: false
```

### Apply the 6-Phase Framework

**Phase 1: Understand**

> "Check if any substring of s2 is a permutation (anagram) of s1."

**Phase 2: Clarify**

- What's a permutation? Any rearrangement of characters
- Case sensitive? Yes
- s1 always shorter than s2? Not guaranteed — handle edge cases

**Phase 3: Brute Force**

Generate all permutations of s1, check if any appears in s2 — O(n! × m) where n=len(s1), m=len(s2). Horrible.

Better brute force: For each substring of s2 with length = len(s1), check if it's a permutation of s1 — O(m × n)

**Phase 4: Optimize**

This is a **fixed-size sliding window** problem!

Window size = len(s1). Slide through s2, checking if each window has the same character frequency as s1.

Use `int[26]` for frequency (faster than HashMap for lowercase letters).

**Phase 5: Code**

```java
public boolean checkInclusion(String s1, String s2) {
    if (s1.length() > s2.length()) return false;
    
    int[] s1Freq = new int[26];
    int[] windowFreq = new int[26];
    
    // Build s1 frequency
    for (char c : s1.toCharArray()) {
        s1Freq[c - 'a']++;
    }
    
    // Build first window
    for (int i = 0; i < s1.length(); i++) {
        windowFreq[s2.charAt(i) - 'a']++;
    }
    
    // Check first window
    if (matches(s1Freq, windowFreq)) return true;
    
    // Slide window
    for (int i = s1.length(); i < s2.length(); i++) {
        windowFreq[s2.charAt(i) - 'a']++;                    // add right
        windowFreq[s2.charAt(i - s1.length()) - 'a']--;     // remove left
        
        if (matches(s1Freq, windowFreq)) return true;
    }
    
    return false;
}

private boolean matches(int[] freq1, int[] freq2) {
    for (int i = 0; i < 26; i++) {
        if (freq1[i] != freq2[i]) return false;
    }
    return true;
}
```

**Time:** O(m) where m = len(s2), the matches() check is O(26) = O(1)  
**Space:** O(1) — two arrays of size 26

**Phase 6: Test**

```
s1 = "ab", s2 = "eidbaooo"

s1Freq = [1, 1, 0, ..., 0]  (a=1, b=1)

Windows:
"ei" → freq = [0,0,1,0,1,...] → no match
"id" → no match
"db" → no match
"ba" → freq = [1,1,0,...] → MATCH ✓

return true
```

### Write Solution File

Create `PermutationInString.java` with full template header.

**Resources:**
- [Permutation in String — NeetCode (10 min)](https://www.youtube.com/watch?v=UbyhOgBN834)

**Done When:**
- Solved in under 30 minutes
- Understand why we use int[26] instead of HashMap
- Can explain the sliding window approach

---

## Segment 4: String Manipulation Fundamentals

**Estimated Time:** 75–90 minutes

### What You're Learning

Strings in Java are **immutable** — every modification creates a new String object. This makes repeated concatenation in loops extremely inefficient.

**Bad:**
```java
String result = "";
for (int i = 0; i < n; i++) {
    result += "a";  // Creates n new String objects → O(n²)
}
```

**Good:**
```java
StringBuilder sb = new StringBuilder();
for (int i = 0; i < n; i++) {
    sb.append("a");  // O(1) amortized per append → O(n) total
}
String result = sb.toString();
```

### StringBuilder — Essential Operations

```java
StringBuilder sb = new StringBuilder();

// Append
sb.append('a');
sb.append("hello");
sb.append(123);

// Insert at position
sb.insert(0, 'X');  // insert at beginning

// Delete
sb.deleteCharAt(5);  // delete character at index 5
sb.delete(2, 5);     // delete characters from 2 to 4 (5 excluded)

// Replace
sb.replace(0, 2, "AB");  // replace substring [0,2) with "AB"

// Reverse
sb.reverse();

// Convert to String
String result = sb.toString();

// Length
int len = sb.length();

// Get character
char c = sb.charAt(3);
```

**Time complexity:**
- append, insert, delete — O(1) amortized
- reverse — O(n)
- toString — O(n)

### Character Array Manipulation

Sometimes you need to modify a string in-place. Convert to char array:

```java
String s = "hello";
char[] chars = s.toCharArray();

// Now you can modify
chars[0] = 'H';

// Convert back
String result = new String(chars);  // "Hello"
```

### Common String Operations

```java
String s = "Hello World";

// Length
int len = s.length();  // 11

// Character at index
char c = s.charAt(0);  // 'H'

// Substring
String sub = s.substring(6);      // "World"
String sub2 = s.substring(0, 5);  // "Hello" — [0,5), 5 excluded

// Index of
int idx = s.indexOf('W');         // 6
int idx2 = s.indexOf("World");    // 6
int lastIdx = s.lastIndexOf('o'); // 7

// Contains
boolean has = s.contains("Wor");  // true

// Replace
String replaced = s.replace('o', '0');  // "Hell0 W0rld"

// Split
String[] words = s.split(" ");    // ["Hello", "World"]

// Trim whitespace
String trimmed = "  hello  ".trim();  // "hello"

// Case conversion
String lower = s.toLowerCase();
String upper = s.toUpperCase();

// Comparison
boolean eq = s.equals("Hello World");  // true
boolean eqIgnoreCase = s.equalsIgnoreCase("hello world");  // true
int compare = s.compareTo("Hello");    // positive (comes after)
```

### Problem: Reverse Words in a String

**Reverse Words in a String** — LeetCode #151  
https://leetcode.com/problems/reverse-words-in-a-string/

> Given an input string `s`, reverse the order of the words.

**Examples:**
```
Input:  s = "the sky is blue"
Output: "blue is sky the"

Input:  s = "  hello world  "
Output: "world hello"
Explanation: Reversed string should not contain leading/trailing spaces

Input:  s = "a good   example"
Output: "example good a"
Explanation: Multiple spaces reduced to single space
```

**Approach:**
1. Split by spaces
2. Filter out empty strings (from multiple spaces)
3. Reverse the array
4. Join with single space

```java
public String reverseWords(String s) {
    // Split by spaces, filter empty
    String[] words = s.trim().split("\\s+");
    
    // Reverse array
    int left = 0, right = words.length - 1;
    while (left < right) {
        String temp = words[left];
        words[left] = words[right];
        words[right] = temp;
        left++;
        right--;
    }
    
    // Join with single space
    return String.join(" ", words);
}
```

**Time:** O(n)  
**Space:** O(n)

**Alternative — Using StringBuilder:**

```java
public String reverseWords(String s) {
    String[] words = s.trim().split("\\s+");
    StringBuilder sb = new StringBuilder();
    
    for (int i = words.length - 1; i >= 0; i--) {
        sb.append(words[i]);
        if (i > 0) sb.append(" ");
    }
    
    return sb.toString();
}
```

### Resources

**YouTube:**
1. [StringBuilder in Java — Coding with John (12 min)](https://www.youtube.com/watch?v=FNLd-pPMmPY)
2. [String Manipulation — Abdul Bari (15 min)](https://www.youtube.com/watch?v=p6RXP1_kHJE)

**Documentation:**
1. [Java String Class — Oracle](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html)
2. [StringBuilder — Baeldung](https://www.baeldung.com/java-string-builder-string-buffer)

**Done When:**
- Understand why String += in loops is O(n²)
- Can use StringBuilder fluently
- Know when to convert to char array vs using StringBuilder

---

## Segment 5: Problem — Longest Repeating Character Replacement

**Estimated Time:** 40–50 minutes

### The Problem

**Longest Repeating Character Replacement** — LeetCode #424  
https://leetcode.com/problems/longest-repeating-character-replacement/

This was your Week 1 test problem. Now solve it after learning the pattern properly.

> Given string `s` and integer `k`, you can replace any character with any other character at most `k` times. Return the length of the longest substring containing the same letter after replacements.

**Examples:**
```
Input:  s = "ABAB", k = 2
Output: 4
Explanation: Replace two 'B's → "AAAA"

Input:  s = "AABABBA", k = 1
Output: 4
```

**Pattern:** Variable Sliding Window

**Validity condition:**  
`(windowSize - maxFrequency) <= k`

The number of characters we need to replace = total characters - most frequent character. If that exceeds k, window is invalid.

**Code:**

```java
public int characterReplacement(String s, int k) {
    int[] freq = new int[26];
    int left = 0;
    int maxFreq = 0;
    int maxLen = 0;
    
    for (int right = 0; right < s.length(); right++) {
        freq[s.charAt(right) - 'A']++;
        maxFreq = Math.max(maxFreq, freq[s.charAt(right) - 'A']);
        
        // Check validity
        int windowSize = right - left + 1;
        if (windowSize - maxFreq > k) {
            freq[s.charAt(left) - 'A']--;
            left++;
        }
        
        maxLen = Math.max(maxLen, right - left + 1);
    }
    
    return maxLen;
}
```

**Time:** O(n)  
**Space:** O(1)

### Write Solution File

Create `LongestRepeatingCharacterReplacement.java`.

**Done When:**
- Solved in under 25 minutes
- Understand the validity condition completely
- Can explain why we track maxFreq

---

## Segment 6: Review + Update Progress

**Estimated Time:** 20 minutes

### Today's Review Checklist

1. Can you identify Sliding Window variants instantly?
2. Do you understand when to shrink for max vs min?
3. Can you use StringBuilder without looking up syntax?

### Update Files

**PROGRESS.md:**
- Fill in Day 1 of Week 2
- What clicked today?
- What's still unclear?

**ERROR_JOURNAL.md:**
- Any mistakes today? Log them

**Git commit:**
```bash
git add .
git commit -m "Week 2 Day 1: Advanced Sliding Window + String manipulation (3 problems)"
git push
```

**Done When:**
- All problems committed
- PROGRESS.md updated
- Can solve Permutation in String in under 20 minutes

---

## End of Day 1

**Tomorrow:** More strings + LinkedList introduction.

**Self-assessment:**
- Do I understand the "formed" counter trick in Minimum Window?
- Can I write StringBuilder operations from memory?
- Am I comfortable with fixed-size sliding windows?

**Week 2 is harder, but you're ready for it. Rest well.**

---

# Day 2 — Tuesday

**Daily Target:** 6 hours total
**Focus:** String Problems + LinkedList Introduction

---

## Segment 1: Warm-up — Three Pattern Speed Drill

**Estimated Time:** 25 minutes

### What You're Doing

Solve three problems from different patterns:

1. **Two Sum II** (Two Pointers) — 8 min target
2. **Find Pivot Index** (Prefix Sum) — 8 min target
3. **Permutation in String** (Sliding Window) — 10 min target

**Total target:** 26 minutes

**Why cross-pattern:** By Day 2 of Week 2, switching between patterns should be fluid. If you need more than 30 seconds to identify which pattern to use, that's the bottleneck to fix.

**Done When:**
- All three solved in under 26 minutes
- Pattern identified instantly for each
- Can state time/space complexity without hesitation

---

## Segment 2: String Problems — Advanced Manipulation

**Estimated Time:** 80–90 minutes

### What You're Learning

String problems in FAANG interviews test:
1. **Pattern recognition** — palindromes, anagrams, subsequences
2. **Efficiency** — O(n) vs O(n²), StringBuilder vs String concatenation
3. **Edge cases** — empty strings, single characters, special characters
4. **Two-pointer thinking** — even when not obvious

### Problem Pattern: Group Anagrams

**Group Anagrams** — LeetCode #49  
https://leetcode.com/problems/group-anagrams/

> Given an array of strings `strs`, group the anagrams together.

**Examples:**
```
Input:  strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

Input:  strs = [""]
Output: [[""]]

Input:  strs = ["a"]
Output: [["a"]]
```

**Key insight:** Anagrams have the same characters in different order. Two approaches:

**Approach 1: Sort each string as key**

```java
public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> map = new HashMap<>();
    
    for (String str : strs) {
        // Sort string to use as key
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        String key = new String(chars);
        
        // Add to corresponding group
        map.putIfAbsent(key, new ArrayList<>());
        map.get(key).add(str);
    }
    
    return new ArrayList<>(map.values());
}
```

**Time:** O(n × k log k) where n = number of strings, k = max length  
**Space:** O(n × k)

**Approach 2: Character count as key (faster)**

```java
public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> map = new HashMap<>();
    
    for (String str : strs) {
        // Count character frequency
        int[] count = new int[26];
        for (char c : str.toCharArray()) {
            count[c - 'a']++;
        }
        
        // Build key from count array
        StringBuilder keyBuilder = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            keyBuilder.append('#');
            keyBuilder.append(count[i]);
        }
        String key = keyBuilder.toString();
        
        map.putIfAbsent(key, new ArrayList<>());
        map.get(key).add(str);
    }
    
    return new ArrayList<>(map.values());
}
```

**Time:** O(n × k) — no sorting  
**Space:** O(n × k)

**Why the '#' delimiter?**

Without delimiter:
- "ab" → count = [1,1,0,...] → key = "110..."
- "az" → count = [1,0,...,1] → key = "10...1"

With multi-digit counts, "12" vs "1,2" would collide. The '#' separates them: "#1#2" vs "#12".

### Problem: Longest Palindromic Substring

**Longest Palindromic Substring** — LeetCode #5  
https://leetcode.com/problems/longest-palindromic-substring/

> Given a string `s`, return the longest palindromic substring in `s`.

**Examples:**
```
Input:  s = "babad"
Output: "bab" (or "aba")

Input:  s = "cbbd"
Output: "bb"
```

**Approach: Expand Around Center**

Key insight: Every palindrome has a center. For each possible center, expand outward while characters match.

**Two cases:**
- Odd-length palindrome: center is a single character (e.g., "aba")
- Even-length palindrome: center is between two characters (e.g., "abba")

```java
public String longestPalindrome(String s) {
    if (s == null || s.length() < 1) return "";
    
    int start = 0, end = 0;
    
    for (int i = 0; i < s.length(); i++) {
        // Odd-length palindrome (center = s[i])
        int len1 = expandAroundCenter(s, i, i);
        
        // Even-length palindrome (center = between s[i] and s[i+1])
        int len2 = expandAroundCenter(s, i, i + 1);
        
        int maxLen = Math.max(len1, len2);
        
        if (maxLen > end - start) {
            start = i - (maxLen - 1) / 2;
            end = i + maxLen / 2;
        }
    }
    
    return s.substring(start, end + 1);
}

private int expandAroundCenter(String s, int left, int right) {
    while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
        left--;
        right++;
    }
    // When loop exits, left and right are one step past the palindrome
    return right - left - 1;
}
```

**Time:** O(n²) — for each center (n), expand takes O(n)  
**Space:** O(1)

### Problem: Valid Anagram

**Valid Anagram** — LeetCode #242  
https://leetcode.com/problems/valid-anagram/

> Given two strings `s` and `t`, return `true` if `t` is an anagram of `s`, and `false` otherwise.

**Examples:**
```
Input:  s = "anagram", t = "nagaram"
Output: true

Input:  s = "rat", t = "car"
Output: false
```

**Approach: Character frequency (optimal)**

```java
public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) return false;
    
    int[] count = new int[26];
    
    for (int i = 0; i < s.length(); i++) {
        count[s.charAt(i) - 'a']++;
        count[t.charAt(i) - 'a']--;
    }
    
    for (int c : count) {
        if (c != 0) return false;
    }
    
    return true;
}
```

**Time:** O(n)  
**Space:** O(1) — array of fixed size 26

### Resources

**YouTube:**
1. [Group Anagrams — NeetCode (9 min)](https://www.youtube.com/watch?v=vzdNOK2oB2E)
2. [Longest Palindromic Substring — NeetCode (14 min)](https://www.youtube.com/watch?v=XYQecbcd6_c)
3. [Valid Anagram — NeetCode (5 min)](https://www.youtube.com/watch?v=9UtInBqnCgA)

**Done When:**
- Understand the frequency count technique for anagrams
- Can write the expand-around-center palindrome algorithm
- Solved all three problems

---

## Segment 3: Solve String Problems

**Estimated Time:** 60 minutes

Solve and write solution files for:

1. **Group Anagrams** — 25 min target
2. **Longest Palindromic Substring** — 30 min target
3. **Valid Anagram** — 10 min target

**Done When:**
- All three solved and committed with full headers

---

## Segment 4: LinkedList Introduction — Pointer Fundamentals

**Estimated Time:** 90–110 minutes

### What You're Learning

LinkedLists are the first **pointer-based** data structure. Unlike arrays where elements are contiguous, LinkedList nodes are scattered — each node points to the next.

### The Node Structure

```java
class ListNode {
    int val;
    ListNode next;
    
    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}
```

**Visualizing:**

```
    head
     ↓
   [1] → [2] → [3] → [4] → null
```

### Basic Operations

**Traverse:**

```java
public void printList(ListNode head) {
    ListNode current = head;
    while (current != null) {
        System.out.print(current.val + " → ");
        current = current.next;
    }
    System.out.println("null");
}
```

**Insert at beginning:**

```java
public ListNode insertAtBeginning(ListNode head, int val) {
    ListNode newNode = new ListNode(val);
    newNode.next = head;
    return newNode;  // new head
}
```

**Delete node:**

```java
public ListNode deleteNode(ListNode head, int val) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    
    ListNode current = dummy;
    while (current.next != null) {
        if (current.next.val == val) {
            current.next = current.next.next;
            break;
        }
        current = current.next;
    }
    
    return dummy.next;
}
```

### The Dummy Node Trick

Create a dummy node pointing to head to avoid special-casing head operations.

### Resources

**YouTube:**
1. [LinkedList Basics — NeetCode (15 min)](https://www.youtube.com/watch?v=njTh_OwMljA)
2. [LinkedList Operations — Coding with John (18 min)](https://www.youtube.com/watch?v=N6dOwBde7-M)

**Done When:**
- Understand Node structure
- Can write traverse, insert, delete from memory
- Understand dummy node trick

---

## Segment 5: Problem — Reverse Linked List

**Estimated Time:** 40–50 minutes

### The Problem

**Reverse Linked List** — LeetCode #206  
https://leetcode.com/problems/reverse-linked-list/

> Reverse a singly linked list.

**Solution:**

```java
public ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode current = head;
    
    while (current != null) {
        ListNode next = current.next;  // save next
        current.next = prev;           // reverse pointer
        prev = current;                // move prev forward
        current = next;                // move current forward
    }
    
    return prev;  // new head
}
```

**Time:** O(n), **Space:** O(1)

### Resources

- [Reverse Linked List — NeetCode (9 min)](https://www.youtube.com/watch?v=G0_I-ZF0S38)

**Done When:**
- Solved in under 25 minutes
- Can draw pointer movements on paper

---

## Segment 6: Review + Update Progress

**Estimated Time:** 20 minutes

Update PROGRESS.md and ERROR_JOURNAL.md. Commit all solutions.

```bash
git add .
git commit -m "Week 2 Day 2: String problems + LinkedList intro (4 problems)"
git push
```

**Done When:**
- All 4 problems committed
- Progress files updated

---

## End of Day 2

**Tomorrow:** More LinkedList + Stack introduction

**You're building momentum. Keep going.**

---

# Day 3 — Wednesday

**Daily Target:** 6 hours total
**Focus:** LinkedList Advanced + Stack Introduction

---

## Segment 1: Warm-up — Spaced Repetition Day

**Estimated Time:** 30 minutes

### What You're Doing

Re-solve these problems cold from earlier in the week:

1. **Longest Repeating Character Replacement** (Day 1 - Sliding Window) — 12 min target
2. **Group Anagrams** (Day 2 - HashMap + String) — 10 min target
3. **Reverse Linked List** (Day 2 - Pointers) — 8 min target

**Total target:** 30 minutes

**Why these three:** They represent the three skill tracks you're building this week — sliding window, string manipulation, and pointer logic. If any takes significantly longer than the target, that's your weak area to focus on today.

**Done When:**
- All three solved from memory in under 30 minutes
- No looking at previous solutions
- Can explain the approach for each in under 60 seconds

---

## Segment 2: LinkedList Advanced — Fast & Slow Pointer (Floyd's Algorithm)

**Estimated Time:** 90–110 minutes

### What You're Learning

The **Fast & Slow Pointer** technique (also called Floyd's Cycle Detection Algorithm) is one of the most elegant algorithms in computer science. It solves problems that seem to require O(n) extra space with just O(1) space by using two pointers moving at different speeds.

**The core insight:** If there's a cycle in a linked list, a fast pointer (moving 2 steps) will eventually meet a slow pointer (moving 1 step) inside the cycle.

### The Metaphor — The Running Track

Imagine two runners on a circular track:
- Runner A (slow) runs at 1 lap per hour
- Runner B (fast) runs at 2 laps per hour

If they start at the same point, when will they meet again? After 1 hour, Runner B will have lapped Runner A once — they meet at the starting point.

**In a linked list with a cycle, the same principle applies.** The fast pointer will eventually catch up to the slow pointer.

### Problem 1: Linked List Cycle

**Linked List Cycle** — LeetCode #141  
https://leetcode.com/problems/linked-list-cycle/

> Given `head`, determine if the linked list has a cycle in it.

**Examples:**
```
Input:  3 → 2 → 0 → -4 → (back to 2)
Output: true

Input:  1 → 2 → (back to 1)
Output: true

Input:  1 → null
Output: false
```

**Approach: Fast & Slow Pointer**

```java
public boolean hasCycle(ListNode head) {
    if (head == null || head.next == null) return false;
    
    ListNode slow = head;
    ListNode fast = head;
    
    while (fast != null && fast.next != null) {
        slow = slow.next;           // move 1 step
        fast = fast.next.next;      // move 2 steps
        
        if (slow == fast) {
            return true;            // they met → cycle exists
        }
    }
    
    return false;                   // fast reached end → no cycle
}
```

**Time:** O(n)  
**Space:** O(1)

**Why this works:**

If there's no cycle:
- `fast` will reach `null` in O(n/2) steps
- Return false

If there's a cycle:
- Once both pointers enter the cycle, the distance between them decreases by 1 each iteration
- Eventually distance becomes 0 → they meet
- In worst case, fast needs to traverse the cycle once to catch slow → O(n)

**Visualization:**

```
No cycle:
slow:  1 → 2 → 3 → 4 → null
fast:  1 → 3 → null  (reached end)

With cycle:
       ↓-------↑
slow:  1 → 2 → 3 → 4
              ↑---------↓
fast:  1 → 3 → 4 → 3 → 4  (will meet slow at 3 or 4)
```

### Problem 2: Find the Duplicate Number

**Find the Duplicate Number** — LeetCode #287  
https://leetcode.com/problems/find-the-duplicate-number/

> Given an array of integers `nums` containing `n + 1` integers where each integer is in the range `[1, n]` inclusive, there is only **one repeated number**. Return this repeated number.
>
> **Constraint:** You must solve it **without modifying the array** and uses **only constant extra space**.

**Examples:**
```
Input:  nums = [1,3,4,2,2]
Output: 2

Input:  nums = [3,1,3,4,2]
Output: 3
```

**Key insight:** Treat the array as a linked list where `nums[i]` is a pointer to index `nums[i]`.

```
nums = [1,3,4,2,2]
index:  0 1 2 3 4

Think of it as:
0 → 1 → 3 → 2 → 4 → 2 (cycle back to 2)
```

Since there's a duplicate, there MUST be a cycle. The duplicate number is the entry point of the cycle.

**Approach: Floyd's Cycle Detection + Find Entry Point**

```java
public int findDuplicate(int[] nums) {
    // Phase 1: Detect cycle (find meeting point)
    int slow = nums[0];
    int fast = nums[0];
    
    do {
        slow = nums[slow];           // move 1 step
        fast = nums[nums[fast]];     // move 2 steps
    } while (slow != fast);
    
    // Phase 2: Find cycle entry point
    slow = nums[0];                  // reset slow to start
    while (slow != fast) {
        slow = nums[slow];           // both move 1 step
        fast = nums[fast];
    }
    
    return slow;                     // cycle entry = duplicate
}
```

**Time:** O(n)  
**Space:** O(1)

**Why the two-phase approach works:**

**Phase 1:** Detect that a cycle exists (like the previous problem).

**Phase 2:** Mathematical proof that cycle entry can be found by:
- Reset slow to start
- Move both pointers 1 step at a time
- They meet at the cycle entry point

**The math:**
- Let distance from start to cycle entry = `F`
- Let distance from cycle entry to meeting point = `a`
- Let cycle length = `C`

When they meet:
- Slow traveled: `F + a`
- Fast traveled: `F + a + nC` (where n = number of complete cycles fast made)

Since fast moves 2× speed:
- `2(F + a) = F + a + nC`
- `F + a = nC`
- `F = nC - a`

This means: distance from start to entry = distance from meeting point to entry (after going around cycle).

So if we reset slow to start and move both 1 step at a time, they meet at entry.

### Problem 3: Middle of the Linked List

**Middle of the Linked List** — LeetCode #876  
https://leetcode.com/problems/middle-of-the-linked-list/

> Given the head of a singly linked list, return the middle node. If there are two middle nodes, return the **second middle** node.

**Examples:**
```
Input:  1 → 2 → 3 → 4 → 5
Output: 3 (middle node)

Input:  1 → 2 → 3 → 4 → 5 → 6
Output: 4 (second middle)
```

**Approach: Fast & Slow Pointer**

```java
public ListNode middleNode(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;
    
    while (fast != null && fast.next != null) {
        slow = slow.next;           // move 1 step
        fast = fast.next.next;      // move 2 steps
    }
    
    return slow;                    // slow is at middle
}
```

**Time:** O(n)  
**Space:** O(1)

**Why this works:**

When fast reaches the end (or second-to-last node), slow is at the middle:
- Fast moves 2× speed
- When fast has moved `n` steps, slow has moved `n/2` steps
- `n/2` is the middle

**Trace:**

```
1 → 2 → 3 → 4 → 5

Iteration 1:
slow at 1, fast at 1
slow → 2, fast → 3

Iteration 2:
slow at 2, fast at 3
slow → 3, fast → 5

Iteration 3:
slow at 3, fast at 5
fast.next == null → stop

Return slow = 3 ✓
```

### Resources

**YouTube:**
1. [Floyd's Cycle Detection — NeetCode (12 min)](https://www.youtube.com/watch?v=gBTe7lFR3vc) — clear explanation
2. [Find Duplicate Number — NeetCode (11 min)](https://www.youtube.com/watch?v=wjYnzkAhcNk) — the hardest variant
3. [Fast & Slow Pointer Technique — takeUforward (18 min)](https://www.youtube.com/watch?v=Lhu3MsXZy-Q)

**Documentation:**
1. [Floyd's Cycle Detection — GeeksforGeeks](https://www.geeksforgeeks.org/floyds-cycle-finding-algorithm/)
2. [Linked List Cycle — LeetCode Solution](https://leetcode.com/problems/linked-list-cycle/solution/)

**Done When:**
- Understand the fast & slow pointer technique
- Can explain why Floyd's algorithm works
- Understand the two-phase approach in Find Duplicate

---

## Segment 3: Solve LinkedList Problems

**Estimated Time:** 60–70 minutes

### Apply the 6-Phase Framework

Solve these three problems:

1. **Linked List Cycle** — 20 min target
2. **Find the Duplicate Number** — 30 min target (hardest one)
3. **Middle of the Linked List** — 15 min target

**Write solution files** with full template headers for each.

**Done When:**
- All three solved and committed
- Can draw the pointer movements for each
- Understand when to use fast & slow pointer

---

## Segment 4: Stack Fundamentals — LIFO Operations

**Estimated Time:** 90–110 minutes

### What You're Learning

A **Stack** is a Last-In-First-Out (LIFO) data structure. Think of a stack of plates: the last plate you put on top is the first one you take off.

**Core operations:**
- `push(x)` — add element to top
- `pop()` — remove and return top element
- `peek()` — view top element without removing
- `isEmpty()` — check if stack is empty

All operations are **O(1)**.

### Stack in Java — Use Deque

**Don't use the `Stack` class** — it's legacy and synchronized (slow).  
**Use `Deque<Integer> stack = new ArrayDeque<>();`** instead.

```java
import java.util.Deque;
import java.util.ArrayDeque;

Deque<Integer> stack = new ArrayDeque<>();

// Push
stack.push(1);
stack.push(2);
stack.push(3);

// Peek (view top without removing)
int top = stack.peek();        // 3

// Pop (remove and return top)
int removed = stack.pop();     // 3
// Stack now: [1, 2]

// Check if empty
boolean empty = stack.isEmpty();  // false

// Size
int size = stack.size();          // 2
```

**Visualization:**

```
After push(1), push(2), push(3):

    ┌───┐
 3  │ 3 │ ← top (push/pop here)
    ├───┤
 2  │ 2 │
    ├───┤
 1  │ 1 │
    └───┘

After pop():
    ┌───┐
 2  │ 2 │ ← new top
    ├───┤
 1  │ 1 │
    └───┘
```

### When to Use Stack — Recognition Signals

Look for these in problem statements:

- **Balanced parentheses** — matching brackets/braces
- **Next greater/smaller element** — monotonic stack
- **Expression evaluation** — infix to postfix, calculate result
- **Function call tracking** — recursion simulation
- **Undo/redo operations**
- **Backtracking problems** — path tracking
- **Browser history** — back button

**Key insight:** Whenever you need to "remember recent items and access them in reverse order," think Stack.

### Problem: Valid Parentheses

**Valid Parentheses** — LeetCode #20  
https://leetcode.com/problems/valid-parentheses/

> Given a string `s` containing just the characters `'(', ')', '{', '}', '[', ']'`, determine if the input string is valid.
>
> An input string is valid if:
> 1. Open brackets must be closed by the same type of brackets.
> 2. Open brackets must be closed in the correct order.
> 3. Every close bracket has a corresponding open bracket of the same type.

**Examples:**
```
Input:  s = "()"
Output: true

Input:  s = "()[]{}"
Output: true

Input:  s = "(]"
Output: false

Input:  s = "([)]"
Output: false
Explanation: brackets are interleaved, not properly nested

Input:  s = "{[]}"
Output: true
```

**Approach: Stack**

**Algorithm:**
1. For each character:
   - If opening bracket `(`, `{`, `[` → push to stack
   - If closing bracket `)`, `}`, `]`:
     - Check if stack is empty → invalid
     - Pop from stack and check if it matches → if not, invalid
2. After processing all characters, stack should be empty

```java
public boolean isValid(String s) {
    Deque<Character> stack = new ArrayDeque<>();
    
    for (char c : s.toCharArray()) {
        // Opening brackets → push
        if (c == '(' || c == '{' || c == '[') {
            stack.push(c);
        }
        // Closing brackets → check match
        else {
            if (stack.isEmpty()) return false;
            
            char top = stack.pop();
            if (c == ')' && top != '(') return false;
            if (c == '}' && top != '{') return false;
            if (c == ']' && top != '[') return false;
        }
    }
    
    return stack.isEmpty();  // all brackets matched
}
```

**Time:** O(n) — single pass  
**Space:** O(n) — stack stores at most n/2 opening brackets

**Trace example:**

```
s = "([{}])"

c = '(' : push '('    stack = ['(']
c = '[' : push '['    stack = ['(', '[']
c = '{' : push '{'    stack = ['(', '[', '{']
c = '}' : pop '{', match ✓   stack = ['(', '[']
c = ']' : pop '[', match ✓   stack = ['(']
c = ')' : pop '(', match ✓   stack = []

Stack empty → return true ✓
```

**Alternative — Cleaner with HashMap:**

```java
public boolean isValid(String s) {
    Deque<Character> stack = new ArrayDeque<>();
    Map<Character, Character> pairs = Map.of(
        ')', '(',
        '}', '{',
        ']', '['
    );
    
    for (char c : s.toCharArray()) {
        if (pairs.containsKey(c)) {
            // Closing bracket
            if (stack.isEmpty() || stack.pop() != pairs.get(c)) {
                return false;
            }
        } else {
            // Opening bracket
            stack.push(c);
        }
    }
    
    return stack.isEmpty();
}
```

### Problem: Min Stack

**Min Stack** — LeetCode #155  
https://leetcode.com/problems/min-stack/

> Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

**Operations:**
- `void push(int val)` — push element
- `void pop()` — remove top element
- `int top()` — get top element
- `int getMin()` — retrieve minimum element

**Example:**
```
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   // -3
minStack.pop();
minStack.top();      // 0
minStack.getMin();   // -2
```

**Challenge:** All operations must be O(1).

**Approach: Two Stacks**

Maintain two stacks:
1. Main stack — stores all elements
2. Min stack — stores minimum at each level

```java
class MinStack {
    private Deque<Integer> stack;
    private Deque<Integer> minStack;
    
    public MinStack() {
        stack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }
    
    public void push(int val) {
        stack.push(val);
        
        // Update min stack
        if (minStack.isEmpty()) {
            minStack.push(val);
        } else {
            minStack.push(Math.min(val, minStack.peek()));
        }
    }
    
    public void pop() {
        stack.pop();
        minStack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}
```

**Time:** All operations O(1)  
**Space:** O(n) for the min stack

**Trace:**

```
Operation         Main Stack    Min Stack
push(-2)          [-2]          [-2]
push(0)           [-2, 0]       [-2, -2]
push(-3)          [-2, 0, -3]   [-2, -2, -3]
getMin()          → -3
pop()             [-2, 0]       [-2, -2]
top()             → 0
getMin()          → -2
```

**Key insight:** At every level, minStack stores the minimum element seen so far. When we pop, we also pop from minStack, restoring the previous minimum.

### Resources

**YouTube:**
1. [Valid Parentheses — NeetCode (8 min)](https://www.youtube.com/watch?v=WTzjTskDFMg)
2. [Min Stack — NeetCode (10 min)](https://www.youtube.com/watch?v=qkLl7nAwDPo)
3. [Stack Data Structure — Coding with John (14 min)](https://www.youtube.com/watch?v=I37kGX-nZEI)

**Documentation:**
1. [Stack — GeeksforGeeks](https://www.geeksforgeeks.org/stack-data-structure/)
2. [Java Deque — Oracle](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Deque.html)

**Done When:**
- Understand LIFO principle
- Know when to use Stack vs other data structures
- Can implement Valid Parentheses and Min Stack

---

## Segment 5: Solve Stack Problems

**Estimated Time:** 50–60 minutes

### Apply the 6-Phase Framework

Solve these two problems:

1. **Valid Parentheses** — 25 min target
2. **Min Stack** — 30 min target

**Write solution files** with full template headers.

**Done When:**
- Both problems solved and committed
- Understand the two-stack technique in Min Stack
- Can explain why HashMap makes Valid Parentheses cleaner

---

## Segment 6: Review + Update Progress

**Estimated Time:** 20 minutes

### Today's Achievements

- ✅ Solved 3 LinkedList problems (fast & slow pointer technique)
- ✅ Learned Floyd's Cycle Detection algorithm
- ✅ Learned Stack fundamentals and LIFO operations
- ✅ Solved 2 Stack problems

**Total problems today:** 5

### Update Files

**PROGRESS.md:**
- Fill in Day 3 of Week 2
- What clicked today? (Fast & slow pointer should have clicked)
- What's still unclear?

**ERROR_JOURNAL.md:**
- Any pointer manipulation mistakes? Log them
- Any stack operation mistakes? Log them

**Git commit:**
```bash
git add .
git commit -m "Week 2 Day 3: LinkedList fast/slow + Stack fundamentals (5 problems)"
git push
```

**Done When:**
- All 5 problems committed
- PROGRESS.md updated
- Can solve any of today's problems in under 20 minutes

---

## End of Day 3

**Tomorrow:** Monotonic Stack + More LinkedList problems

**Self-assessment:**
- Do I understand Floyd's Cycle Detection algorithm?
- Can I identify when to use fast & slow pointer?
- Do I understand when to use Stack vs other data structures?
- Can I implement Min Stack from memory?

**You've learned two powerful techniques today: Floyd's algorithm and Stack. Tomorrow you'll combine Stack with a monotonic invariant — one of the most elegant patterns in DSA.**

---

# Day 4 — Thursday

**Daily Target:** 6 hours total
**Focus:** Monotonic Stack + LinkedList Advanced Problems

---

## Segment 1: Warm-up — Pattern Recognition Drill

**Estimated Time:** 30 minutes

### What You're Doing

Solve these four problems from Week 2 cold:

1. **Valid Palindrome** (Week 1 carryover - Two Pointer) — 7 min target
2. **Subarray Sum Equals K** (Week 1 carryover - HashMap + Prefix) — 10 min target
3. **Linked List Cycle** (Day 3 - Fast & Slow) — 8 min target
4. **Valid Parentheses** (Day 3 - Stack) — 8 min target

**Total target:** 33 minutes

**Why these four:** They cover all major patterns from Weeks 1-2. If you can solve all four in under 35 minutes, pattern recognition is becoming automatic.

**Done When:**
- All four solved in under 35 minutes
- Can identify the pattern for each in under 30 seconds
- No hesitation on which data structure to use

---

## Segment 2: Monotonic Stack — The Next Greater Element Pattern

**Estimated Time:** 100–120 minutes

### What You're Learning

A **Monotonic Stack** maintains elements in either strictly increasing or strictly decreasing order. When a new element violates this order, we pop elements until the invariant is restored.

**This pattern solves a specific class of problems:** "For each element, find the next (or previous) element that is greater (or smaller) than it."

The brute force approach is O(n²) — for each element, scan the rest of the array. Monotonic stack solves it in O(n).

### The Core Intuition — The Viewpoint Problem

Imagine you're standing in a line of people of different heights. For each person, you want to know: "Who is the next taller person I can see?"

**Brute force:** Each person looks to their right until they find someone taller — O(n²).

**Monotonic stack approach:**
- Maintain a stack of people in **decreasing height order**
- When a tall person arrives, they "block the view" of all shorter people behind them
- Pop all shorter people (they've found their "next taller")
- Push the tall person onto the stack

This ensures each person is pushed and popped exactly once — O(n).

### Problem 1: Daily Temperatures

**Daily Temperatures** — LeetCode #739  
https://leetcode.com/problems/daily-temperatures/

> Given an array of integers `temperatures` represents the daily temperatures, return an array `answer` such that `answer[i]` is the number of days you have to wait after the `i`-th day to get a warmer temperature. If there is no future day for which this is possible, keep `answer[i] == 0`.

**Examples:**
```
Input:  temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]

Explanation:
Day 0 (73): Next warmer is day 1 (74) → wait 1 day
Day 1 (74): Next warmer is day 2 (75) → wait 1 day
Day 2 (75): Next warmer is day 6 (76) → wait 4 days
Day 3 (71): Next warmer is day 5 (72) → wait 2 days
...
Day 6 (76): No warmer day → 0
Day 7 (73): No warmer day → 0
```

**Approach: Monotonic Decreasing Stack**

**Key insight:**
- Stack stores **indices** (not values) in decreasing temperature order
- When we encounter a warmer day, it "resolves" all previous colder days on the stack

**Algorithm:**
```
For each day i:
    While stack is not empty AND temperatures[i] > temperatures[stack.top]:
        Pop index j from stack
        answer[j] = i - j  (number of days to wait)
    Push i onto stack
```

**Code:**

```java
public int[] dailyTemperatures(int[] temperatures) {
    int n = temperatures.length;
    int[] answer = new int[n];
    Deque<Integer> stack = new ArrayDeque<>();  // stores indices
    
    for (int i = 0; i < n; i++) {
        // Pop all days colder than today
        while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
            int prevDay = stack.pop();
            answer[prevDay] = i - prevDay;
        }
        
        stack.push(i);
    }
    
    // Remaining indices on stack have no warmer day → answer[i] = 0 (already initialized)
    return answer;
}
```

**Time:** O(n) — each element pushed and popped at most once  
**Space:** O(n) — stack stores at most n elements

**Trace example:**

```
temperatures = [73, 74, 75, 71, 69, 72, 76, 73]
                0   1   2   3   4   5   6   7

i=0 (73):
  stack empty → push 0
  stack = [0]

i=1 (74):
  74 > 73 (stack.peek) → pop 0
  answer[0] = 1 - 0 = 1
  push 1
  stack = [1]

i=2 (75):
  75 > 74 → pop 1
  answer[1] = 2 - 1 = 1
  push 2
  stack = [2]

i=3 (71):
  71 < 75 → push 3
  stack = [2, 3]

i=4 (69):
  69 < 71 → push 4
  stack = [2, 3, 4]

i=5 (72):
  72 > 69 → pop 4, answer[4] = 5 - 4 = 1
  72 > 71 → pop 3, answer[3] = 5 - 3 = 2
  72 < 75 → push 5
  stack = [2, 5]

i=6 (76):
  76 > 72 → pop 5, answer[5] = 6 - 5 = 1
  76 > 75 → pop 2, answer[2] = 6 - 2 = 4
  push 6
  stack = [6]

i=7 (73):
  73 < 76 → push 7
  stack = [6, 7]

End: Remaining indices [6, 7] have no warmer day → answer[6] = 0, answer[7] = 0

Result: [1, 1, 4, 2, 1, 1, 0, 0] ✓
```

### Problem 2: Next Greater Element I

**Next Greater Element I** — LeetCode #496  
https://leetcode.com/problems/next-greater-element-i/

> You are given two integer arrays `nums1` and `nums2`, where `nums1` is a subset of `nums2`. For each element in `nums1`, find the next greater element in `nums2`.
>
> The next greater element of `x` is the first greater element to the right of `x` in `nums2`. If it does not exist, return `-1`.

**Examples:**
```
Input:  nums1 = [4,1,2], nums2 = [1,3,4,2]
Output: [-1,3,-1]

Explanation:
4 in nums2: next greater = none → -1
1 in nums2: next greater = 3
2 in nums2: next greater = none → -1

Input:  nums1 = [2,4], nums2 = [1,2,3,4]
Output: [3,-1]
```

**Approach: Monotonic Stack + HashMap**

**Algorithm:**
1. Use monotonic stack to find next greater for ALL elements in `nums2`
2. Store results in HashMap
3. Look up each element of `nums1` in the HashMap

**Code:**

```java
public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    Map<Integer, Integer> nextGreater = new HashMap<>();
    Deque<Integer> stack = new ArrayDeque<>();
    
    // Build next greater map for nums2
    for (int num : nums2) {
        while (!stack.isEmpty() && num > stack.peek()) {
            nextGreater.put(stack.pop(), num);
        }
        stack.push(num);
    }
    
    // Elements remaining on stack have no next greater
    while (!stack.isEmpty()) {
        nextGreater.put(stack.pop(), -1);
    }
    
    // Build result for nums1
    int[] result = new int[nums1.length];
    for (int i = 0; i < nums1.length; i++) {
        result[i] = nextGreater.get(nums1[i]);
    }
    
    return result;
}
```

**Time:** O(n + m) where n = nums2.length, m = nums1.length  
**Space:** O(n) — HashMap + stack

### Problem 3: Largest Rectangle in Histogram

**Largest Rectangle in Histogram** — LeetCode #84  
https://leetcode.com/problems/largest-rectangle-in-histogram/

> Given an array of integers `heights` representing the histogram's bar height where the width of each bar is `1`, return the area of the largest rectangle in the histogram.

**Examples:**
```
Input:  heights = [2,1,5,6,2,3]
Output: 10
Explanation: The largest rectangle has height 5 and width 2 → area = 10

Input:  heights = [2,4]
Output: 4
```

**Visualization:**

```
heights = [2, 1, 5, 6, 2, 3]

    6 ┤     ▓
    5 ┤   ▓ ▓
    4 ┤   ▓ ▓
    3 ┤   ▓ ▓   ▓
    2 ┤ ▓ ▓ ▓ ▓ ▓
    1 ┤ ▓ ▓ ▓ ▓ ▓ ▓
      └─┴─┴─┴─┴─┴─
       0 1 2 3 4 5

Largest rectangle:
    6 ┤     
    5 ┤   █ █
    4 ┤   █ █
    3 ┤   █ █   
    2 ┤   █ █ 
    1 ┤         
      └─┴─┴─┴─┴─┴─
       0 1 2 3 4 5

Height = 5, Width = 2, Area = 10
```

**Approach: Monotonic Increasing Stack**

**Key insight:**
- For each bar, find how far left and right it can extend (while maintaining its height)
- It can extend left until it hits a shorter bar
- It can extend right until it hits a shorter bar
- Use monotonic stack to find these boundaries

**Algorithm:**
- Maintain stack in **increasing height order**
- When a shorter bar arrives, it means previous bars can't extend further right
- Pop taller bars and calculate their max area

**Code:**

```java
public int largestRectangleArea(int[] heights) {
    Deque<Integer> stack = new ArrayDeque<>();  // stores indices
    int maxArea = 0;
    int n = heights.length;
    
    for (int i = 0; i < n; i++) {
        // Pop all bars taller than current
        while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
            int height = heights[stack.pop()];
            
            // Width = distance from left boundary to right boundary
            int width = stack.isEmpty() ? i : i - stack.peek() - 1;
            
            maxArea = Math.max(maxArea, height * width);
        }
        
        stack.push(i);
    }
    
    // Process remaining bars in stack
    while (!stack.isEmpty()) {
        int height = heights[stack.pop()];
        int width = stack.isEmpty() ? n : n - stack.peek() - 1;
        maxArea = Math.max(maxArea, height * width);
    }
    
    return maxArea;
}
```

**Time:** O(n) — each element pushed and popped once  
**Space:** O(n)

**Trace example:**

```
heights = [2, 1, 5, 6, 2, 3]
           0  1  2  3  4  5

i=0 (2):
  stack empty → push 0
  stack = [0]

i=1 (1):
  1 < 2 → pop 0
  height=2, width=1 (stack empty, so width=i=1), area=2
  push 1
  stack = [1]

i=2 (5):
  5 > 1 → push 2
  stack = [1, 2]

i=3 (6):
  6 > 5 → push 3
  stack = [1, 2, 3]

i=4 (2):
  2 < 6 → pop 3
  height=6, width=4-2-1=1, area=6
  2 < 5 → pop 2
  height=5, width=4-1-1=2, area=10 ← MAX
  2 > 1 → push 4
  stack = [1, 4]

i=5 (3):
  3 > 2 → push 5
  stack = [1, 4, 5]

End: Process remaining
  pop 5: height=3, width=6-4-1=1, area=3
  pop 4: height=2, width=6-1-1=4, area=8
  pop 1: height=1, width=6, area=6

maxArea = 10 ✓
```

### When to Use Monotonic Stack

**Recognition signals:**
- "Next greater/smaller element"
- "Previous greater/smaller element"
- "Largest rectangle" in histogram-like problems
- "Maximum area" with height constraints
- "Stock span" problems

**The pattern:**
- Need to find something to the **left** or **right** of each element
- That something is based on a **comparison** (greater, smaller)
- Brute force would be O(n²)

### Resources

**YouTube:**
1. [Daily Temperatures — NeetCode (10 min)](https://www.youtube.com/watch?v=cTBiBSnjO3c)
2. [Largest Rectangle in Histogram — NeetCode (15 min)](https://www.youtube.com/watch?v=zx5Sw9130L0)
3. [Monotonic Stack Explained — takeUforward (18 min)](https://www.youtube.com/watch?v=Dt8xIMmPiSc)

**Documentation:**
1. [Monotonic Stack Pattern — LeetCode Discuss](https://leetcode.com/problems/sum-of-subarray-minimums/solutions/178876/stack-solution-with-very-detailed-explanation-step-by-step/)
2. [Next Greater Element — GeeksforGeeks](https://www.geeksforgeeks.org/next-greater-element/)

**Done When:**
- Understand why monotonic stack is O(n)
- Can identify when to use increasing vs decreasing stack
- Understand the histogram rectangle problem

---

## Segment 3: Solve Monotonic Stack Problems

**Estimated Time:** 75–90 minutes

### Apply the 6-Phase Framework

Solve these three problems:

1. **Daily Temperatures** — 25 min target
2. **Next Greater Element I** — 25 min target
3. **Largest Rectangle in Histogram** — 35 min target (hardest)

**Write solution files** with full template headers.

**Done When:**
- All three solved and committed
- Can explain why we store indices not values
- Understand the width calculation in Histogram problem

---

## Segment 4: LinkedList Problem — Merge Two Sorted Lists

**Estimated Time:** 40–50 minutes

### The Problem

**Merge Two Sorted Lists** — LeetCode #21  
https://leetcode.com/problems/merge-two-sorted-lists/

> Merge two sorted linked lists and return it as a **new sorted list**. The new list should be made by splicing together the nodes of the first two lists.

**Examples:**
```
Input:  list1 = 1 → 2 → 4, list2 = 1 → 3 → 4
Output: 1 → 1 → 2 → 3 → 4 → 4

Input:  list1 = [], list2 = []
Output: []

Input:  list1 = [], list2 = 0
Output: 0
```

**Approach: Iterative with Dummy Node**

**Key insight:** Use a dummy node to avoid special-casing the head.

**Algorithm:**
```
Create dummy node
current = dummy

While both lists have nodes:
    If list1.val <= list2.val:
        Attach list1 node to result
        Move list1 forward
    Else:
        Attach list2 node to result
        Move list2 forward
    Move current forward

Attach remaining nodes from non-empty list
Return dummy.next
```

**Code:**

```java
public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    ListNode dummy = new ListNode(0);
    ListNode current = dummy;
    
    while (list1 != null && list2 != null) {
        if (list1.val <= list2.val) {
            current.next = list1;
            list1 = list1.next;
        } else {
            current.next = list2;
            list2 = list2.next;
        }
        current = current.next;
    }
    
    // Attach remaining nodes
    if (list1 != null) {
        current.next = list1;
    } else {
        current.next = list2;
    }
    
    return dummy.next;
}
```

**Time:** O(n + m)  
**Space:** O(1) — no new nodes created, just relink existing

**Trace:**

```
list1: 1 → 2 → 4
list2: 1 → 3 → 4

dummy → null
current at dummy

Step 1: 1 <= 1, attach list1
dummy → 1 (from list1)
list1 = 2 → 4

Step 2: 2 > 1, attach list2
dummy → 1 → 1 (from list2)
list2 = 3 → 4

Step 3: 2 <= 3, attach list1
dummy → 1 → 1 → 2
list1 = 4

Step 4: 4 > 3, attach list2
dummy → 1 → 1 → 2 → 3
list2 = 4

Step 5: 4 <= 4, attach list1
dummy → 1 → 1 → 2 → 3 → 4
list1 = null

Attach remaining list2:
dummy → 1 → 1 → 2 → 3 → 4 → 4

Return dummy.next ✓
```

**Recursive approach (elegant but uses O(n) space):**

```java
public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    if (list1 == null) return list2;
    if (list2 == null) return list1;
    
    if (list1.val <= list2.val) {
        list1.next = mergeTwoLists(list1.next, list2);
        return list1;
    } else {
        list2.next = mergeTwoLists(list1, list2.next);
        return list2;
    }
}
```

### Write Solution File

Create `MergeTwoSortedLists.java` with both iterative and recursive solutions.

**Resources:**
- [Merge Two Sorted Lists — NeetCode (8 min)](https://www.youtube.com/watch?v=XIdigk956u0)

**Done When:**
- Solved in under 25 minutes
- Understand why dummy node simplifies the code
- Can write both iterative and recursive versions

---

## Segment 5: Review + Pattern Map Update

**Estimated Time:** 30 minutes

### Today's Achievements

- ✅ Learned Monotonic Stack pattern
- ✅ Solved 3 monotonic stack problems
- ✅ Solved 1 LinkedList merge problem

**Total problems today:** 4

### Update Your Pattern Recognition Map

Add a new entry:

```
═══════════════════════════════════════════════════
MONOTONIC STACK
═══════════════════════════════════════════════════
Trigger words:
  - Next greater/smaller element
  - Previous greater/smaller element
  - Largest rectangle / maximum area
  - Daily temperatures / stock span

Template:
  Deque<Integer> stack = new ArrayDeque<>();  // stores indices
  
  for (int i = 0; i < arr.length; i++) {
      while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
          int idx = stack.pop();
          // Process: found next greater for idx
      }
      stack.push(i);
  }

Stack order:
  - Increasing: to find next SMALLER
  - Decreasing: to find next GREATER

Complexity: O(n) time, O(n) space

Common mistakes:
  - Storing values instead of indices
  - Wrong stack order (increasing vs decreasing)
  - Forgetting to process remaining stack elements
═══════════════════════════════════════════════════
```

### Update Files

**PROGRESS.md:**
- Fill in Day 4 of Week 2
- Monotonic Stack should have clicked
- Problems solved: 4 today

**ERROR_JOURNAL.md:**
- Any mistakes with stack order? Log them
- Did you store values instead of indices? Log it

**Git commit:**
```bash
git add .
git commit -m "Week 2 Day 4: Monotonic Stack pattern + LinkedList merge (4 problems)"
git push
```

**Done When:**
- All 4 problems committed
- Pattern map updated
- Can identify monotonic stack problems instantly

---

## End of Day 4

**Tomorrow:** Binary Search review + Week 2 integration

**Self-assessment:**
- Do I understand why monotonic stack is O(n)?
- Can I identify when to use increasing vs decreasing stack?
- Do I understand the histogram rectangle problem?
- Can I merge two sorted lists without drawing diagrams?

**You've learned one of the most elegant patterns in DSA today. Monotonic Stack appears in 15% of FAANG coding rounds — you're ready for it.**

---

# Day 5 — Friday

**Daily Target:** 6 hours total
**Focus:** Binary Search Review + Integration + Week 2 Test Prep

---

## Segment 1: Warm-up — Cross-Pattern Speed Test

**Estimated Time:** 35 minutes

### What You're Doing

Solve these five problems representing every major pattern from Weeks 1-2:

1. **Container With Most Water** (Week 1 - Two Pointers) — 8 min
2. **Maximum Subarray** (Week 1 - Kadane's) — 7 min
3. **Longest Substring Without Repeating** (Week 2 - Sliding Window) — 10 min
4. **Reverse Linked List** (Week 2 - Pointers) — 7 min
5. **Daily Temperatures** (Week 2 - Monotonic Stack) — 10 min

**Total target:** 42 minutes

**Why five:** This is your Week 2 readiness check. If you can solve all five in under 45 minutes, you're ready for tomorrow's test. If any pattern takes significantly longer, focus extra time on that segment today.

**Done When:**
- All five solved in under 45 minutes
- Pattern identified in under 20 seconds for each
- No looking at notes or previous solutions

---

## Segment 2: Binary Search — Deep Review + Advanced Variants

**Estimated Time:** 90–110 minutes

### What You're Learning

Binary Search from Week 1 was introduced briefly. Today you'll master it completely, including variants that appear in 25% of FAANG coding rounds.

### Binary Search Fundamentals — The Three Forms

**Form 1: Classic Binary Search (Find Target)**

```java
public int binarySearch(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;  // avoid overflow
        
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    
    return -1;  // not found
}
```

**Form 2: Find Left Boundary (First Occurrence)**

```java
public int leftBound(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    int result = -1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (nums[mid] == target) {
            result = mid;      // found, but keep searching left
            right = mid - 1;
        } else if (nums[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    
    return result;
}
```

**Form 3: Find Right Boundary (Last Occurrence)**

```java
public int rightBound(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    int result = -1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (nums[mid] == target) {
            result = mid;      // found, but keep searching right
            left = mid + 1;
        } else if (nums[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    
    return result;
}
```

### Problem 1: Search in Rotated Sorted Array

**Search in Rotated Sorted Array** — LeetCode #33  
https://leetcode.com/problems/search-in-rotated-sorted-array/

> Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand. You are given a target value. Return the index of target if it is in the array, otherwise return -1.
>
> **Constraint:** O(log n) runtime

**Examples:**
```
Input:  nums = [4,5,6,7,0,1,2], target = 0
Output: 4

Input:  nums = [4,5,6,7,0,1,2], target = 3
Output: -1
```

**Key insight:** Even though the array is rotated, at least one half is always sorted.

```
[4, 5, 6, 7, 0, 1, 2]
 ↑           ↑
left         mid: 7

Left half [4,5,6,7] is sorted
```

**Algorithm:**
1. Find mid
2. Determine which half is sorted
3. Check if target is in the sorted half
4. If yes, search that half; otherwise search the other half

**Code:**

```java
public int search(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (nums[mid] == target) return mid;
        
        // Determine which half is sorted
        if (nums[left] <= nums[mid]) {
            // Left half is sorted
            if (nums[left] <= target && target < nums[mid]) {
                right = mid - 1;  // target in sorted left half
            } else {
                left = mid + 1;   // target in right half
            }
        } else {
            // Right half is sorted
            if (nums[mid] < target && target <= nums[right]) {
                left = mid + 1;   // target in sorted right half
            } else {
                right = mid - 1;  // target in left half
            }
        }
    }
    
    return -1;
}
```

**Time:** O(log n)  
**Space:** O(1)

### Problem 2: Find Minimum in Rotated Sorted Array

**Find Minimum in Rotated Sorted Array** — LeetCode #153  
https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/

> Suppose an array sorted in ascending order is rotated. Find the minimum element.

**Examples:**
```
Input:  nums = [3,4,5,1,2]
Output: 1

Input:  nums = [4,5,6,7,0,1,2]
Output: 0

Input:  nums = [11,13,15,17]
Output: 11 (no rotation)
```

**Key insight:** The minimum is the "rotation point" — where the array breaks from increasing order.

**Algorithm:**
- If `nums[mid] > nums[right]`, minimum is in right half (mid cannot be minimum)
- If `nums[mid] <= nums[right]`, minimum is in left half or mid itself

**Code:**

```java
public int findMin(int[] nums) {
    int left = 0, right = nums.length - 1;
    
    while (left < right) {
        int mid = left + (right - left) / 2;
        
        if (nums[mid] > nums[right]) {
            // Minimum is in right half
            left = mid + 1;
        } else {
            // Minimum is in left half (including mid)
            right = mid;
        }
    }
    
    return nums[left];
}
```

**Time:** O(log n)  
**Space:** O(1)

**Trace:**

```
nums = [4, 5, 6, 7, 0, 1, 2]
        0  1  2  3  4  5  6

left=0, right=6:
  mid=3, nums[3]=7 > nums[6]=2 → left=4

left=4, right=6:
  mid=5, nums[5]=1 <= nums[6]=2 → right=5

left=4, right=5:
  mid=4, nums[4]=0 <= nums[5]=1 → right=4

left=4, right=4 → stop
return nums[4] = 0 ✓
```

### Problem 3: Koko Eating Bananas (Binary Search on Answer Space)

**Koko Eating Bananas** — LeetCode #875  
https://leetcode.com/problems/koko-eating-bananas/

> Koko loves to eat bananas. There are `n` piles of bananas, the `i`-th pile has `piles[i]` bananas. Koko can decide her bananas-per-hour eating speed `k`. Each hour, she chooses a pile and eats `k` bananas from it. If the pile has less than `k` bananas, she eats all of them and will not eat any more bananas during this hour.
>
> Return the minimum integer `k` such that she can eat all the bananas within `h` hours.

**Examples:**
```
Input:  piles = [3,6,7,11], h = 8
Output: 4

Explanation: With speed k=4:
Hour 1: eat 3 bananas (pile 1 done)
Hour 2: eat 4 bananas from pile 2 (2 left)
Hour 3: eat 2 bananas from pile 2 (pile 2 done)
Hour 4: eat 4 bananas from pile 3 (3 left)
Hour 5: eat 3 bananas from pile 3 (pile 3 done)
Hour 6: eat 4 bananas from pile 4 (7 left)
Hour 7: eat 4 bananas from pile 4 (3 left)
Hour 8: eat 3 bananas from pile 4 (pile 4 done)
Total: 8 hours ✓

Input:  piles = [30,11,23,4,20], h = 5
Output: 30
Explanation: Need to eat fastest pile in 1 hour
```

**Key insight:** This is **binary search on the answer space**.

**Answer space:** k can range from 1 to max(piles). We want the **minimum k** that works.

**Feasibility check:** For a given speed k, can Koko finish in h hours?

```java
boolean canFinish(int[] piles, int h, int k) {
    int hours = 0;
    for (int pile : piles) {
        hours += (pile + k - 1) / k;  // ceiling division
    }
    return hours <= h;
}
```

**Binary search template for minimization:**

```java
public int minEatingSpeed(int[] piles, int h) {
    int left = 1;
    int right = Arrays.stream(piles).max().getAsInt();
    
    while (left < right) {
        int mid = left + (right - left) / 2;
        
        if (canFinish(piles, h, mid)) {
            right = mid;      // mid works, try smaller
        } else {
            left = mid + 1;   // mid too slow, need faster
        }
    }
    
    return left;
}

private boolean canFinish(int[] piles, int h, int k) {
    int hours = 0;
    for (int pile : piles) {
        hours += (pile + k - 1) / k;  // ceiling: pile / k
    }
    return hours <= h;
}
```

**Time:** O(n log m) where n = piles.length, m = max(piles)  
**Space:** O(1)

**Why ceiling division?**

```
pile = 7, k = 4
Regular division: 7 / 4 = 1 (wrong, she needs 2 hours)
Ceiling division: (7 + 4 - 1) / 4 = 10 / 4 = 2 ✓
```

### Binary Search Decision Tree

```
Is the array sorted?
├── YES, searching for a value
│   ├── Find exact match → Classic binary search
│   ├── Find first occurrence → Left boundary search
│   └── Find last occurrence → Right boundary search
│
├── YES, but rotated
│   ├── Search for value → Rotated array search
│   └── Find minimum → Find rotation point
│
└── NO, but answer space is sorted
    └── Find minimum/maximum that satisfies condition
        → Binary search on answer space
```

### Resources

**YouTube:**
1. [Search in Rotated Array — NeetCode (12 min)](https://www.youtube.com/watch?v=U8XENwh8IOq)
2. [Find Minimum Rotated — NeetCode (9 min)](https://www.youtube.com/watch?v=nIVW4P8b1VA)
3. [Koko Eating Bananas — NeetCode (13 min)](https://www.youtube.com/watch?v=U2SozAs9RzA)

**Documentation:**
1. [Binary Search — LeetCode Explore](https://leetcode.com/explore/learn/card/binary-search/)
2. [Binary Search on Answer — CP-Algorithms](https://cp-algorithms.com/num_methods/binary_search.html)

**Done When:**
- Understand all three binary search forms
- Can identify rotated array problems
- Understand binary search on answer space

---

## Segment 3: Solve Binary Search Problems

**Estimated Time:** 60–75 minutes

### Apply the 6-Phase Framework

Solve these three problems:

1. **Search in Rotated Sorted Array** — 25 min target
2. **Find Minimum in Rotated Sorted Array** — 20 min target
3. **Koko Eating Bananas** — 30 min target

**Write solution files** with full template headers.

**Done When:**
- All three solved and committed
- Can explain the sorted-half logic in rotated array search
- Understand ceiling division in Koko problem

---

## Segment 4: Week 2 Integration — Combine Patterns

**Estimated Time:** 75–90 minutes

### What You're Learning

Week 2 is about **pattern combination** — some problems require using 2+ patterns together.

### Problem: Remove Nth Node From End of List

**Remove Nth Node From End of List** — LeetCode #19  
https://leetcode.com/problems/remove-nth-node-from-end-of-list/

> Given the head of a linked list, remove the `n`-th node from the end of the list and return its head.

**Examples:**
```
Input:  head = 1 → 2 → 3 → 4 → 5, n = 2
Output: 1 → 2 → 3 → 5

Input:  head = 1, n = 1
Output: []

Input:  head = 1 → 2, n = 1
Output: 1
```

**Approach: Fast & Slow Pointer + Dummy Node**

**Two techniques combined:**
1. Fast & slow pointer to find the position
2. Dummy node to handle edge case (removing head)

**Algorithm:**
- Move fast pointer n steps ahead
- Move both pointers until fast reaches end
- Slow is now at the node before the one to delete

**Code:**

```java
public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    
    ListNode fast = dummy;
    ListNode slow = dummy;
    
    // Move fast n+1 steps ahead
    for (int i = 0; i <= n; i++) {
        fast = fast.next;
    }
    
    // Move both until fast reaches end
    while (fast != null) {
        fast = fast.next;
        slow = slow.next;
    }
    
    // Remove node
    slow.next = slow.next.next;
    
    return dummy.next;
}
```

**Time:** O(n)  
**Space:** O(1)

### Problem: Reorder List

**Reorder List** — LeetCode #143  
https://leetcode.com/problems/reorder-list/

> Reorder the list to be on the following form: `L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → ...`

**Examples:**
```
Input:  1 → 2 → 3 → 4
Output: 1 → 4 → 2 → 3

Input:  1 → 2 → 3 → 4 → 5
Output: 1 → 5 → 2 → 4 → 3
```

**Approach: Three-step combination**

**Three techniques combined:**
1. Fast & slow pointer to find middle
2. Reverse the second half
3. Merge two lists

**Code:**

```java
public void reorderList(ListNode head) {
    if (head == null || head.next == null) return;
    
    // Step 1: Find middle using fast & slow
    ListNode slow = head;
    ListNode fast = head;
    while (fast.next != null && fast.next.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    
    // Step 2: Reverse second half
    ListNode secondHalf = reverseList(slow.next);
    slow.next = null;  // cut the list
    
    // Step 3: Merge two halves
    ListNode firstHalf = head;
    while (secondHalf != null) {
        ListNode temp1 = firstHalf.next;
        ListNode temp2 = secondHalf.next;
        
        firstHalf.next = secondHalf;
        secondHalf.next = temp1;
        
        firstHalf = temp1;
        secondHalf = temp2;
    }
}

private ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode current = head;
    while (current != null) {
        ListNode next = current.next;
        current.next = prev;
        prev = current;
        current = next;
    }
    return prev;
}
```

**Time:** O(n)  
**Space:** O(1)

### Problem: Merge K Sorted Lists

**Merge K Sorted Lists** — LeetCode #23  
https://leetcode.com/problems/merge-k-sorted-lists/

> Merge k sorted linked lists and return it as one sorted list.

**Examples:**
```
Input:  lists = [
  1 → 4 → 5,
  1 → 3 → 4,
  2 → 6
]
Output: 1 → 1 → 2 → 3 → 4 → 4 → 5 → 6
```

**Approach: Merge two at a time (divide and conquer)**

**Code:**

```java
public ListNode mergeKLists(ListNode[] lists) {
    if (lists == null || lists.length == 0) return null;
    
    return mergeHelper(lists, 0, lists.length - 1);
}

private ListNode mergeHelper(ListNode[] lists, int left, int right) {
    if (left == right) return lists[left];
    
    int mid = left + (right - left) / 2;
    ListNode l1 = mergeHelper(lists, left, mid);
    ListNode l2 = mergeHelper(lists, mid + 1, right);
    
    return mergeTwoLists(l1, l2);
}

private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode current = dummy;
    
    while (l1 != null && l2 != null) {
        if (l1.val <= l2.val) {
            current.next = l1;
            l1 = l1.next;
        } else {
            current.next = l2;
            l2 = l2.next;
        }
        current = current.next;
    }
    
    current.next = (l1 != null) ? l1 : l2;
    return dummy.next;
}
```

**Time:** O(n log k) where n = total nodes, k = number of lists  
**Space:** O(log k) — recursion stack

### Solve Integration Problems

Solve these three:

1. **Remove Nth Node From End** — 25 min
2. **Reorder List** — 30 min
3. **Merge K Sorted Lists** — 35 min

**Done When:**
- All three solved
- Understand how patterns combine
- Can identify which patterns to use for each

---

## Segment 5: Week 2 Test Preparation

**Estimated Time:** 45 minutes

### Tomorrow's Test Format

**2 unseen problems, 60 minutes each**

**Problem categories likely:**
- Sliding Window (advanced variant)
- LinkedList (fast & slow pointer or reversal)
- Stack (monotonic or design)
- String manipulation
- Binary Search (rotated or answer space)

### Test Prep Checklist

Go through your Pattern Recognition Map and for each pattern, answer:

1. **What triggers this pattern?** (can you identify it in 30 seconds?)
2. **What's the template?** (can you write the core loop from memory?)
3. **What's the complexity?** (time and space)
4. **What are common mistakes?** (check your ERROR_JOURNAL.md)

### Speed Drill

Solve these two in test conditions (60 min each):

1. **Longest Substring with At Most K Distinct Characters** (if you have LeetCode Premium)  
   OR **Fruit Into Baskets** — LeetCode #904 (same problem, free)

2. **Linked List Cycle II** — LeetCode #142  
   (Find where the cycle begins, not just detect it)

**Time yourself strictly.** This is test practice.

**Done When:**
- Both problems solved in under 120 minutes total
- Pattern map reviewed
- Weak areas identified

---

## Segment 6: Review + Git + Sleep Prep

**Estimated Time:** 20 minutes

### Final Week 2 Checklist

Review your work from Monday-Friday:

**Monday:** Sliding Window advanced, Permutation in String  
**Tuesday:** String problems, LinkedList basics, Reverse List  
**Wednesday:** Fast & slow pointer, Stack fundamentals  
**Thursday:** Monotonic Stack, Merge Two Lists  
**Friday:** Binary Search variants, Integration problems

**Total problems Week 2 so far:** 19 problems

### Update Files

**PROGRESS.md:**
- Fill in Day 5 of Week 2
- Week 2 summary
- Test readiness: Yes / No (be honest)

**Git commit:**
```bash
git add .
git commit -m "Week 2 Day 5: Binary Search + Integration + Test prep complete"
git push
```

### Sleep Strategy

**Critical:** Get 7-8 hours of sleep tonight.

**Why:** Problem-solving ability drops 30-40% when sleep-deprived. A well-rested brain is worth more than cramming tonight.

**Don't:**
- Stay up late reviewing
- Drink caffeine after 6pm
- Look at screens 1 hour before bed

**Do:**
- Review pattern map for 20 minutes max
- Go to bed at normal time
- Wake up refreshed

**Done When:**
- All commits pushed
- Pattern map ready for tomorrow
- You're confident and ready to test

---

## End of Day 5

**Tomorrow:** Week 2 Test (Saturday) — 2 unseen problems, timed

**You've covered:**
- Advanced Sliding Window
- String manipulation
- LinkedList (basic + fast & slow pointer)
- Stack (basic + monotonic)
- Binary Search (all variants)
- Pattern combination

**You're ready. Trust the process. Tomorrow you'll prove it.**

---

# Day 6 — Saturday

**Daily Target:** 10 hours (test + review + deep practice)
**Focus:** Week 2 Assessment + Pattern Mastery

---

## Segment 1: Week 2 Test — Timed Assessment

**Estimated Time:** 2 hours (strict)

### Test Format

You will solve **2 unseen problems** in test conditions:
- 60 minutes per problem (120 min total)
- No looking at notes, solutions, or YouTube
- No web search or documentation
- Scratch paper allowed
- Must write full solution with template header

### Test Environment Setup

**Before starting:**
1. Close all browser tabs except LeetCode
2. Put phone in another room
3. Set timer for 120 minutes
4. Have scratch paper and pen ready
5. Open a blank file for each solution

### The Two Test Problems

---

**PROBLEM 1 — Medium Difficulty (Sliding Window + HashMap)**

**Longest Substring with At Most Two Distinct Characters** — LeetCode #159 (Premium)  
**Alternative (Free):** **Fruit Into Baskets** — LeetCode #904

> You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array `fruits` where `fruits[i]` is the type of fruit the `i`-th tree produces.
>
> You want to collect as much fruit as possible. However, the owner has some strict rules:
> - You only have two baskets, and each basket can only hold a single type of fruit.
> - Once you reach a tree, you must pick all of its fruit.
> - You can only move to the right (cannot go back).
>
> Return the maximum number of fruits you can collect with this procedure.

**Examples:**
```
Input:  fruits = [1,2,1]
Output: 3
Explanation: Pick all three fruits

Input:  fruits = [0,1,2,2]
Output: 3
Explanation: Pick [1,2,2]

Input:  fruits = [1,2,3,2,2]
Output: 4
Explanation: Pick [2,3,2,2]

Input:  fruits = [3,3,3,1,2,1,1,2,3,3,4]
Output: 5
Explanation: Pick [1,2,1,1,2]
```

**Hints (only read if stuck for 20+ minutes):**
- This is a sliding window problem
- "At most 2 distinct" means valid window has ≤ 2 fruit types
- Use HashMap to track fruit type frequencies
- Shrink when distinct count > 2

**Time limit:** 60 minutes

---

**PROBLEM 2 — Medium Difficulty (LinkedList + Two Pointer)**

**Linked List Cycle II** — LeetCode #142

> Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return `null`.
>
> **Constraint:** Do not modify the linked list. Can you solve it using O(1) space?

**Examples:**
```
Input:  head = 3 → 2 → 0 → -4 → (back to 2)
Output: Node with value 2

Input:  head = 1 → 2 → (back to 1)
Output: Node with value 1

Input:  head = 1 → null
Output: null
```

**Hints (only read if stuck for 20+ minutes):**
- Use Floyd's Cycle Detection (fast & slow pointer)
- Phase 1: Detect if cycle exists (like Week 2 Day 3)
- Phase 2: Find the entry point (reset one pointer to head, move both 1 step at a time)
- Mathematical proof ensures they meet at cycle start

**Time limit:** 60 minutes

---

### Test Rules

1. **Start timer:** 120 minutes total
2. **No pausing:** Bathroom breaks count against time
3. **Stuck?** Move to next problem after 60 min, don't waste time
4. **Submit when done** or when time expires

### After Test — Immediate Recording

**Don't look up solutions yet.** First, write in a test log:

**Problem 1:**
- Time taken: _____ minutes
- Pattern identified: _____
- Approach used: _____
- Got stuck on: _____
- Syntax errors: _____ (count)
- Test cases passed: _____ / _____
- Self-score: _____ / 10

**Problem 2:**
- Time taken: _____ minutes
- Pattern identified: _____
- Approach used: _____
- Got stuck on: _____
- Syntax errors: _____ (count)
- Test cases passed: _____ / _____
- Self-score: _____ / 10

**Save this — you'll compare against actual solutions in Segment 2.**

---

## Segment 2: Test Solutions + Deep Analysis

**Estimated Time:** 90 minutes

### Solution 1: Fruit Into Baskets (Longest Substring At Most K Distinct)

**Pattern:** Sliding Window (Variable) + HashMap

**Key insight:** "At most 2 distinct fruit types" = "longest substring with at most 2 distinct characters"

**Validity condition:** `distinct fruit types ≤ 2`

**Solution:**

```java
/**
 * ╔══════════════════════════════════════════════════════════════╗
 * ║  Problem   : Fruit Into Baskets                             ║
 * ║  Link      : https://leetcode.com/problems/fruit-into-baskets/ ║
 * ║  Difficulty: Medium                                          ║
 * ║  Pattern   : Sliding Window + HashMap                        ║
 * ║  Topic     : Arrays & Strings                                ║
 * ╚══════════════════════════════════════════════════════════════╝
 *
 * PROBLEM STATEMENT:
 * Find the longest contiguous subarray with at most 2 distinct elements.
 *
 * EXAMPLES:
 *   Input:  fruits = [1,2,1]
 *   Output: 3
 *
 *   Input:  fruits = [1,2,3,2,2]
 *   Output: 4  (subarray [2,3,2,2])
 *
 * ── APPROACH: SLIDING WINDOW ────────────────────────────────────
 * Maintain a window with at most 2 distinct fruit types.
 * Expand right: add new fruit to window.
 * Shrink left: when distinct types > 2, remove from left.
 *
 * Use HashMap to track fruit type frequencies.
 *
 *   Time  : O(n)  — each element enters/leaves window once
 *   Space : O(1)  — HashMap stores at most 3 types (before shrinking)
 *
 * EDGE CASES:
 *   ✓ All same fruit → return length
 *   ✓ Empty array → return 0
 *   ✓ Only 1 or 2 types total → return entire length
 */

import java.util.HashMap;
import java.util.Map;

public class FruitIntoBaskets {
    
    public int totalFruit(int[] fruits) {
        if (fruits == null || fruits.length == 0) return 0;
        
        Map<Integer, Integer> basket = new HashMap<>();
        int left = 0;
        int maxFruits = 0;
        
        for (int right = 0; right < fruits.length; right++) {
            // Add fruit to basket
            basket.put(fruits[right], basket.getOrDefault(fruits[right], 0) + 1);
            
            // Shrink while more than 2 types
            while (basket.size() > 2) {
                int leftFruit = fruits[left];
                basket.put(leftFruit, basket.get(leftFruit) - 1);
                
                if (basket.get(leftFruit) == 0) {
                    basket.remove(leftFruit);  // type no longer in window
                }
                
                left++;
            }
            
            maxFruits = Math.max(maxFruits, right - left + 1);
        }
        
        return maxFruits;
    }
}
```

**Time:** O(n)  
**Space:** O(1) — HashMap stores at most 3 entries (2 valid + 1 being removed)

**Trace example:**

```
fruits = [1, 2, 3, 2, 2]

right=0 (1): basket={1:1}, size=1, valid, maxFruits=1
right=1 (2): basket={1:1,2:1}, size=2, valid, maxFruits=2
right=2 (3): basket={1:1,2:1,3:1}, size=3, INVALID
  Shrink: remove fruits[0]=1
  basket={2:1,3:1}, size=2, valid, maxFruits=2
right=3 (2): basket={2:2,3:1}, size=2, valid, maxFruits=3
right=4 (2): basket={2:3,3:1}, size=2, valid, maxFruits=4

Result: 4 ✓
```

### Solution 2: Linked List Cycle II

**Pattern:** Fast & Slow Pointer (Floyd's Cycle Detection) — Two Phases

**Key insight:** Mathematical proof shows that after meeting point, resetting one pointer to head and moving both 1 step at a time makes them meet at cycle start.

**Solution:**

```java
/**
 * ╔══════════════════════════════════════════════════════════════╗
 * ║  Problem   : Linked List Cycle II                           ║
 * ║  Link      : https://leetcode.com/problems/linked-list-cycle-ii/ ║
 * ║  Difficulty: Medium                                          ║
 * ║  Pattern   : Fast & Slow Pointer (Floyd's Algorithm)        ║
 * ║  Topic     : Linked List                                     ║
 * ╚══════════════════════════════════════════════════════════════╝
 *
 * PROBLEM STATEMENT:
 * Find the node where the cycle begins in a linked list.
 * Return null if no cycle exists.
 *
 * EXAMPLES:
 *   Input:  3 → 2 → 0 → -4 → (back to 2)
 *   Output: Node with value 2
 *
 *   Input:  1 → null
 *   Output: null
 *
 * ── APPROACH: FLOYD'S TWO-PHASE ALGORITHM ───────────────────────
 * Phase 1: Detect cycle using fast & slow pointer.
 *   - slow moves 1 step, fast moves 2 steps
 *   - If they meet, cycle exists
 *
 * Phase 2: Find cycle entry.
 *   - Reset slow to head
 *   - Move both 1 step at a time
 *   - They meet at cycle start
 *
 * Mathematical proof:
 *   Let F = distance from head to cycle start
 *   Let a = distance from cycle start to meeting point
 *   Let C = cycle length
 *
 *   When they meet:
 *     slow traveled: F + a
 *     fast traveled: F + a + nC (n complete cycles)
 *
 *   Since fast = 2 × slow:
 *     2(F + a) = F + a + nC
 *     F + a = nC
 *     F = nC - a
 *
 *   This means: distance from head to cycle start = 
 *               distance from meeting point to cycle start
 *
 *   Time  : O(n)
 *   Space : O(1)
 */

public class LinkedListCycleII {
    
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        
        // Phase 1: Detect cycle
        ListNode slow = head;
        ListNode fast = head;
        boolean hasCycle = false;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }
        
        if (!hasCycle) return null;
        
        // Phase 2: Find cycle start
        slow = head;  // reset slow to head
        
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        
        return slow;  // cycle start
    }
}
```

**Time:** O(n)  
**Space:** O(1)

### Your Analysis

Compare your solutions to these. For each problem:

**Did you:**
- Identify the correct pattern?
- Choose the optimal approach?
- Handle edge cases?
- Write clean, readable code?
- State correct complexity?

**Error analysis:**
- What mistakes did you make?
- Were they conceptual (wrong pattern) or implementation (syntax)?
- How would you avoid them in a real interview?

**Update ERROR_JOURNAL.md** with every mistake from this test.

---

## Segment 3: Deep Practice — Weak Pattern Focus

**Estimated Time:** 2–3 hours

### Based on Test Performance

**If you solved both problems correctly in under 100 minutes:**
- Excellent! You're ahead of schedule.
- Spend this time on **hard variants**:
  - Sliding Window Maximum (LeetCode #239)
  - Minimum Window Substring (review from Day 1, solve again)
  - Remove Nth From End (solve again faster)

**If you solved both but took 100–120 minutes:**
- Good, but need more speed.
- Re-solve both test problems from scratch without notes
- Then solve 3 similar problems:
  - Longest Substring At Most K Distinct (if you have Premium)
  - Find Duplicate Number (LeetCode #287 - Floyd's algorithm)
  - Remove Duplicates from Sorted List II (LeetCode #82)

**If you solved 1 problem or struggled:**
- Focus on the pattern you struggled with
- If Sliding Window was hard:
  - Re-watch NeetCode Sliding Window playlist
  - Solve: Longest Substring Without Repeating (again)
  - Solve: Permutation in String (again)
  - Solve: Minimum Window Substring
- If LinkedList was hard:
  - Re-watch Floyd's Cycle Detection explanation
  - Solve: Linked List Cycle (again)
  - Solve: Middle of Linked List (again)
  - Solve: Palindrome Linked List (LeetCode #234)

**If you solved neither or didn't finish:**
- **Stop. Don't move to Week 3 yet.**
- Week 2 patterns aren't solidified.
- Spend Sunday re-doing Week 2 Days 1-5
- Re-take this test next Saturday
- Only move to Week 3 when you can solve both problems in under 100 minutes

---

## Segment 4: Spaced Repetition — Full Week 2 Review

**Estimated Time:** 2–3 hours

### The Full Week 2 Problem List

Re-solve these 12 core problems from Week 2 cold:

**Day 1:**
1. Permutation in String
2. Longest Repeating Character Replacement
3. Minimum Window Substring (if you solved it)

**Day 2:**
4. Group Anagrams
5. Valid Anagram
6. Reverse Linked List

**Day 3:**
7. Linked List Cycle
8. Find the Duplicate Number
9. Valid Parentheses

**Day 4:**
10. Daily Temperatures
11. Largest Rectangle in Histogram
12. Merge Two Sorted Lists

**Target time:** 15 minutes average per problem = 3 hours total

**Why this matters:** These 12 problems cover every pattern from Week 2. If you can solve all 12 without hesitation, the patterns have moved to long-term memory.

**Record your times:**

| # | Problem | Time Taken | Stuck? | Notes |
|---|---------|------------|--------|-------|
| 1 | Permutation in String | ___ min | Yes/No | |
| 2 | Longest Repeating Char | ___ min | Yes/No | |
| ... | | | | |

**Any problem that takes >20 minutes:** Mark it for extra review tomorrow.

---

## Segment 5: Week 2 Final Assessment + PROGRESS.md Update

**Estimated Time:** 45 minutes

### Week 2 Complete — Self-Evaluation

Answer honestly:

**Pattern Mastery:**
- Can I identify Sliding Window variants in under 30 seconds? Yes / No
- Can I write fast & slow pointer code from memory? Yes / No
- Do I understand monotonic stack deeply? Yes / No
- Can I solve rotated array binary search? Yes / No

**Speed:**
- Can I solve Medium sliding window in under 20 minutes? Yes / No
- Can I solve Medium LinkedList in under 20 minutes? Yes / No
- Can I solve Medium stack in under 15 minutes? Yes / No

**Readiness for Week 3:**
- If you answered "Yes" to 6+ of the above: **Ready for Week 3**
- If you answered "Yes" to 4-5: **Almost ready — review weak areas Sunday**
- If you answered "Yes" to <4: **Not ready — repeat Week 2**

### Update PROGRESS.md — Week 2 Summary

Fill in the complete Week 2 section:

```markdown
## Week 2 Summary

**Dates:** [Start] to [End]

**Topics Covered:**
- Sliding Window (advanced variants)
- String manipulation (anagrams, palindromes)
- LinkedList (basic + fast & slow pointer)
- Stack (basic + monotonic)
- Binary Search (rotated arrays, answer space)

**Problems Solved:** 21 total
- Day 1: 3 problems
- Day 2: 4 problems
- Day 3: 5 problems
- Day 4: 4 problems
- Day 5: 5 problems

**Test Score:** X/20

**Patterns I Own (solve in <15 min):**
- [List them]

**Patterns Still Shaky:**
- [List them]

**Week 3 Carry-Forward Problems:**
(Hardest 3 from Week 2 to re-solve in Week 3 Day 1)
1. 
2. 
3. 

**Week 2 Closed:** [Date]
**Ready for Week 3:** Yes / No
```

### Update ERROR_JOURNAL.md

**Mistake pattern analysis:**

Look at all mistakes from Week 2. What patterns do you see?

| Root Cause | Count This Week | Total |
|------------|----------------|-------|
| Jumped to code without thinking | ___ | ___ |
| Wrong pattern chosen | ___ | ___ |
| Off-by-one error | ___ | ___ |
| Forgot edge case | ___ | ___ |
| ... | | |

**Which mistake happened most this week?** That's your Week 3 focus.

### Git — Week 2 Complete

```bash
git add .
git commit -m "Week 2 complete: 21 problems solved, test score X/20, full review done"
git push
```

---

## End of Day 6

**Tomorrow is REST + Week 3 preview.**

**You've completed Week 2:**
- Learned advanced sliding window
- Mastered fast & slow pointer (Floyd's algorithm)
- Understood monotonic stack
- Reviewed binary search variants
- Solved 21+ problems
- Tested under pressure

**Week 3 preview:**
- Stacks & Queues (advanced)
- More LinkedList problems
- Binary Search mastery
- Week 3 is integration-heavy — combining 3+ patterns

**Rest tomorrow. You earned it.**

---

# Day 7 — Sunday

**Daily Target:** 4 hours (rest + light review + Week 3 preview)
**Focus:** Recovery + Reflection + Project Work

---

## Segment 1: Morning — No Screens (First 3 Hours)

**Duration:** First 3 hours after waking up

**Do:**
- Physical activity (run, gym, walk, sports)
- Read something non-technical (fiction, biography, anything)
- Spend time with family/friends
- Cook a good meal
- Sleep extra if needed

**Don't:**
- Open laptop
- Review flashcards
- Watch coding videos
- Think about DSA

**Why:** Your brain is consolidating Week 2 learning right now. Rest accelerates this process. The patterns you struggled with will feel clearer tomorrow after rest.

---

## Segment 2: Sunday Project — URL Shortener Week 2

**Estimated Time:** 3–4 hours

### Week 2 Project Work: Core Functionality Implementation

**What you'll build today:**

Last Sunday (Week 1) you set up:
- ✅ Spring Boot project structure
- ✅ Database schema (JPA entity)
- ✅ Repository interface
- ✅ Professional README

**Today (Week 2) you build:**
- DTO classes (Request/Response objects)
- Service layer with Base62 encoding
- POST /api/shorten endpoint
- GET /{shortCode} redirect endpoint
- Unit tests

**By end of today:** You'll have a working URL shortener you can test in Postman.

**Detailed plan in:** `SUNDAY_PROJECT_WEEK_2.md` (I'll create this next if you want)

**Time:** 3.5 hours

**Done When:**
- Can shorten URLs via POST request
- Can redirect via GET request
- All endpoints tested in Postman
- Unit tests written and passing

---

## Segment 3: Afternoon — Passive Week 2 Review

**Estimated Time:** 45 minutes

### Mental Pattern Drill (No Coding)

For each problem statement, answer **mentally** (don't write code):
- What pattern?
- What data structure?
- What's the time complexity?

**Problems:**
1. "Find the longest substring with at most k distinct characters"
2. "Detect if linked list has a cycle"
3. "Find next greater element for each element in array"
4. "Search for value in rotated sorted array"
5. "Check if string has valid balanced parentheses"
6. "Find where linked list cycle begins"
7. "Remove all elements from array that equal val, in-place"
8. "Find minimum in rotated sorted array"

**Answers:**
1. Sliding Window + HashMap, O(n)
2. Fast & Slow Pointer, O(n) / O(1)
3. Monotonic Stack, O(n) / O(n)
4. Binary Search (rotated variant), O(log n)
5. Stack, O(n) / O(n)
6. Floyd's Two-Phase, O(n) / O(1)
7. Two Pointer (same direction), O(n) / O(1)
8. Binary Search (find rotation point), O(log n)

If you got all 8 correct instantly — Week 2 patterns are solidified.

---

## Segment 4: Week 3 Preview — Read Only (No Solving)

**Estimated Time:** 30 minutes

### What's Coming in Week 3

**Topics:**
- Binary Search (complete mastery — all variants)
- Stacks & Queues (advanced problems)
- LinkedList (hard problems — multi-technique combination)
- Integration (3-pattern problems)

**Why Week 3 is different:**
- Week 1 taught individual patterns
- Week 2 taught pattern variants
- **Week 3 teaches pattern synthesis** — when a single pattern isn't enough

**Sample Week 3 problem types:**
- "Implement LRU Cache" — HashMap + Doubly Linked List
- "Trapping Rain Water" — Two Pointer + Stack (two solutions)
- "Binary Search in 2D Matrix" — Binary Search variant

**Your Week 3 prep:**
- Make sure Week 2 patterns are solid
- Review your ERROR_JOURNAL for repeated mistakes
- Get excited — Week 3 is where it gets interesting

**Do NOT start studying Week 3 content today.** Just preview.

---

## Segment 5: Reflection + Goal Adjustment

**Estimated Time:** 20 minutes

### Honest Assessment

**Week 1 vs Week 2 comparison:**
- Which week felt harder? Why?
- Which pattern took longest to click?
- Did your daily 6-hour target hold? Or did you cut corners?

**Sustainability check:**
- Is this pace sustainable for 16 more weeks?
- Do you need to adjust anything?
- Are you getting enough sleep?
- Is your schedule realistic?

**Month 1 target:** 40 problems in 4 weeks  
**Current progress:** ~21 problems in 2 weeks  
**On track?** Yes / No

**If behind:** What needs to change in Week 3-4?

---

## Segment 6: Physical Prep for Week 3

**Estimated Time:** 15 minutes

**Workspace:**
- Clean desk for Monday
- Charge all devices
- Stock snacks and water
- Ensure quiet study space

**Schedule:**
- Block Monday-Friday 6-hour chunks
- Block Saturday 10 hours
- Protect Sunday 4 hours (project + rest)

**Accountability:**
- Who's checking your progress?
- Who sees your GitHub commits?
- Who knows about your 18-month plan?

**If no one:** Find one person this week. Accountability increases follow-through by 65%.

---

## End of Week 2

**You've completed:**
- 21 problems solved (Week 2)
- 18 problems solved (Week 1)
- **Total: 39 problems in 2 weeks**

**You're 1% ahead of your target** (38 expected, 39 done).

**Week 3 starts tomorrow. Here's what's waiting:**

On Monday morning, just say **"Week 3"** and I'll deliver the complete Day 1-7 plan with the same depth as Week 1 and Week 2.

**Rest tonight. Reflect on how far you've come. Two weeks ago you couldn't solve Two Sum in 15 minutes. Now you can solve medium LinkedList problems with pointer manipulation.**

**The gap between you and FAANG is shrinking every single day.**

**See you in Week 3.**

---