# 01 — Arrays & Strings

> **Phase:** Month 1 · Week 1–2
> **Problems:** 18 total · 8 Easy · 8 Medium · 2 Hard
> **Patterns:** Two Pointers · Prefix Sum · Sliding Window · Kadane's · Matrix Traversal

---

## 📋 Table of Contents

- [Why Arrays & Strings First](#-why-arrays--strings-first)
- [Pattern 1 — Two Pointers](#-pattern-1--two-pointers)
- [Pattern 2 — Prefix Sum](#-pattern-2--prefix-sum)
- [Pattern 3 — Sliding Window](#-pattern-3--sliding-window)
- [Pattern 4 — Kadane's Algorithm](#-pattern-4--kadanes-algorithm)
- [Pattern 5 — Matrix Traversal](#-pattern-5--matrix-traversal)
- [Problem Index](#-problem-index)
- [Complexity Cheat Sheet](#-complexity-cheat-sheet)
- [Common Mistakes](#-common-mistakes)
- [Java Quick Reference](#-java-quick-reference)

---

## 🧠 Why Arrays & Strings First

Arrays are the substrate of DSA. Every other data structure — stacks, queues, heaps, graphs represented as adjacency matrices — is built on or interacts with arrays. Strings in Java are immutable character arrays.

Master this topic and you gain three things that carry into every future topic:

1. **Index fluency** — thinking in terms of positions, boundaries, and ranges without effort
2. **Pointer intuition** — mentally tracking multiple variables moving through a structure simultaneously  
3. **Java muscle memory** — `nums.length`, `s.charAt(i)`, `Arrays.sort()`, `new HashMap<>()` feel automatic

Every hour you spend here pays compound interest in Trees, Graphs, and DP later.

---

## ✌️ Pattern 1 — Two Pointers

### What It Is

Two Pointers places two index variables (`left` and `right`, or `slow` and `fast`) into an array or string and moves them according to a condition. Instead of checking every possible pair with nested loops (O(n²)), you eliminate impossible candidates in each step and arrive at the answer in a single pass (O(n)).

### The Core Intuition

Imagine you're looking for two people in a sorted line whose heights sum to exactly 180cm. You start with the shortest person (left) and the tallest (right). If their sum is too low, the left person is too short — move them right. If the sum is too high, the right person is too tall — move them left. You never need to check both people against everyone else.

**Key insight:** you can only skip candidates if you can *prove* the skipped ones can never be the answer. This is why Two Pointers usually requires a sorted array — sorting gives you the proof.

### When to Use It — Recognition Signals

Look for these in the problem statement:

- "Sorted array" + find pair/triplet with target sum
- "Palindrome" — check or find
- "Remove duplicates / elements in-place"
- "Minimum distance between two elements"
- "Container", "trap", "area" between two boundaries
- Brute force would use two nested loops → Two Pointer is the upgrade

### Two Forms

**Form 1 — Converging (opposite ends, move toward centre)**

```
[2, 7, 11, 15]   target = 9
 ↑           ↑
left        right

sum = 2+15 = 17  → too big  → right--
sum = 2+11 = 13  → too big  → right--
sum = 2+7  = 9   → found!   → return [0,1]
```

**Form 2 — Same Direction (both start at 0, different speeds)**

```
[0, 0, 1, 1, 2]   remove duplicates in-place
 s
 f

s points to last unique position.
f scans forward — when nums[f] != nums[s], copy it to s+1.
```

### Java Templates

**Converging Two Pointer:**

```java
public int[] twoPointerConverging(int[] nums, int target) {
    int left = 0, right = nums.length - 1;

    while (left < right) {                          // strict <, not <=
        int sum = nums[left] + nums[right];

        if (sum == target) {
            return new int[]{left, right};          // found
        } else if (sum < target) {
            left++;                                 // need bigger sum → move left up
        } else {
            right--;                                // need smaller sum → move right down
        }
    }

    return new int[]{};                             // no solution
}
```

**Same-Direction Two Pointer:**

```java
public int removeDuplicates(int[] nums) {
    if (nums.length == 0) return 0;

    int slow = 0;                                   // last confirmed unique position

    for (int fast = 1; fast < nums.length; fast++) {
        if (nums[fast] != nums[slow]) {             // new unique value found
            slow++;
            nums[slow] = nums[fast];                // place it after last unique
        }
        // if equal: fast advances, slow stays → effectively skips duplicate
    }

    return slow + 1;                                // length of deduplicated array
}
```

**Three Pointers (Dutch National Flag):**

```java
public void sortColors(int[] nums) {
    int low = 0, mid = 0, high = nums.length - 1;

    while (mid <= high) {
        if (nums[mid] == 0) {
            swap(nums, low++, mid++);               // 0: goes to front
        } else if (nums[mid] == 1) {
            mid++;                                  // 1: already in place
        } else {
            swap(nums, mid, high--);                // 2: goes to back
            // do NOT mid++ here — swapped element at mid is unexamined
        }
    }
}

private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}
```

### Complexity

| Form | Time | Space | Why |
|------|------|-------|-----|
| Converging | O(n) | O(1) | Each pointer moves at most n steps total |
| Same-direction | O(n) | O(1) | fast pointer visits each element once |
| With initial sort | O(n log n) | O(1) | sort dominates; pointers still O(n) |

### Trace-Through Example — 3Sum

Problem: find all unique triplets in `nums` that sum to zero.

```
nums = [-4, -1, -1, 0, 1, 2]   (sort first)

Fix i=0 → nums[i]=-4 → need two numbers summing to 4
  left=1(-1), right=5(2) → sum=-1+2=1 → too small → left++
  left=2(-1), right=5(2) → sum=-1+2=1 → too small → left++
  left=3(0),  right=5(2) → sum=0+2=2  → too small → left++
  left=4(1),  right=5(2) → sum=1+2=3  → too small → left++
  left meets right → done with i=0

Fix i=1 → nums[i]=-1 → need two numbers summing to 1
  left=2(-1), right=5(2) → sum=-1+2=1 → FOUND [-1,-1,2] ✓
  left=3(0),  right=4(1) → sum=0+1=1  → FOUND [-1,0,1]  ✓
  left meets right → done with i=1

Fix i=2 → nums[i]=-1 → skip (same as i=1, would give duplicates)
...

Result: [[-1,-1,2], [-1,0,1]]
```

---

## ➕ Pattern 2 — Prefix Sum

### What It Is

Prefix Sum pre-computes cumulative sums of an array so that any range sum query `sum(i, j)` can be answered in O(1) instead of O(n). You trade O(n) space for the ability to answer range queries instantly.

### The Core Intuition

Think of the prefix array as storing "how much total rain has fallen up to each day." If you want to know how much fell between Day 3 and Day 7, you subtract: `totalUpToDay7 - totalUpToDay2`. You never re-add up the individual days.

```
arr     =  [3,  1,  4,  1,  5,  9,  2]
prefix  =  [3,  4,  8,  9, 14, 23, 25]

sum(2,5) = prefix[5] - prefix[1]
         = 23 - 4
         = 19    ✓  (4 + 1 + 5 + 9 = 19)
```

### When to Use It — Recognition Signals

- "Sum of subarray from index i to j" asked multiple times
- "Number of subarrays with sum equal to k"
- "Pivot index" — where left sum equals right sum
- "Product of array except self"
- Any problem where you're repeatedly summing portions of an array

### The HashMap + Prefix Sum Combo

The most powerful Prefix Sum pattern — detects subarrays with a target sum in O(n):

**Core insight:** if `prefix[j] - prefix[i] = target`, then subarray `[i+1, j]` sums to target. Rearranging: `prefix[i] = prefix[j] - target`. So for every `j`, check if `prefix[j] - target` has been seen before.

```
nums = [1, 2, 3],  target = 3

j=0: prefixSum=1  → need 1-3=-2  → not in map → store {1:1}
j=1: prefixSum=3  → need 3-3=0   → 0 IS in map (initial) → count++  ([1,2])
j=2: prefixSum=6  → need 6-3=3   → 3 IS in map → count++  ([3])

count = 2  ✓
```

### Java Templates

**Basic Prefix Sum Array:**

```java
public int[] buildPrefixSum(int[] nums) {
    int n = nums.length;
    int[] prefix = new int[n];
    prefix[0] = nums[0];

    for (int i = 1; i < n; i++) {
        prefix[i] = prefix[i - 1] + nums[i];
    }

    return prefix;
}

// Range sum query — O(1) after O(n) build
public int rangeSum(int[] prefix, int left, int right) {
    if (left == 0) return prefix[right];
    return prefix[right] - prefix[left - 1];
}
```

**HashMap + Prefix Sum (subarray with target sum):**

```java
public int subarraySum(int[] nums, int target) {
    int count = 0;
    int prefixSum = 0;

    // Seed with 0:1 — handles subarrays starting at index 0
    Map<Integer, Integer> seen = new HashMap<>();
    seen.put(0, 1);

    for (int num : nums) {
        prefixSum += num;

        int complement = prefixSum - target;
        count += seen.getOrDefault(complement, 0); // add all ways to form complement

        seen.merge(prefixSum, 1, Integer::sum);    // seen[prefixSum]++
    }

    return count;
}
```

**Prefix Sum — In-place space optimisation (Product Except Self):**

```java
public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] result = new int[n];

    // Pass 1: result[i] = product of all elements LEFT of i
    result[0] = 1;
    for (int i = 1; i < n; i++) {
        result[i] = result[i - 1] * nums[i - 1];
    }

    // Pass 2: multiply by suffix product (tracked in a single variable)
    int suffixProduct = 1;
    for (int i = n - 1; i >= 0; i--) {
        result[i] *= suffixProduct;
        suffixProduct *= nums[i];
    }

    return result;    // O(1) extra space — result array doesn't count
}
```

### Complexity

| Approach | Time | Space | Use Case |
|----------|------|-------|----------|
| Build prefix array | O(n) | O(n) | Multiple range queries |
| Range query after build | O(1) | O(1) | Each individual query |
| HashMap + prefix | O(n) | O(n) | Count subarrays = target |
| In-place prefix × suffix | O(n) | O(1) | Product except self |

### Trace-Through — Find Pivot Index

```
nums = [1, 7, 3, 6, 5, 6]

Build prefix: [1, 8, 11, 17, 22, 28]
Total sum = 28

i=0: leftSum=0,  rightSum=28-1-0=27   → 0≠27  skip
i=1: leftSum=1,  rightSum=28-7-1=20   → 1≠20  skip
i=2: leftSum=8,  rightSum=28-3-8=17   → 8≠17  skip
i=3: leftSum=11, rightSum=28-6-11=11  → 11=11 → FOUND index 3 ✓
```

---

## 🪟 Pattern 3 — Sliding Window

### What It Is

Sliding Window maintains a contiguous subarray or substring "window" that expands from the right and contracts from the left, based on a validity condition. It converts O(n²) or O(n·k) brute force solutions into O(n) by reusing computation from the previous window instead of recalculating from scratch.

### The Core Intuition

Think of a physical sliding window on a train — as the train moves, the window frame stays the same but what you see through it shifts. You don't rebuild the scene from scratch each time — you lose what leaves the left edge and gain what enters the right edge.

```
"abcabcbb"  — find longest substring without repeating

Window: [a]         → valid, size 1
Window: [ab]        → valid, size 2
Window: [abc]       → valid, size 3
Window: [abca]      → INVALID (duplicate 'a') → shrink from left
Window: [bca]       → valid, size 3
Window: [bcab]      → INVALID (duplicate 'b') → shrink from left
Window: [cab]       → valid, size 3
...
Answer: 3
```

### When to Use It — Recognition Signals

- "Contiguous subarray/substring with property X"
- "Longest/shortest subarray satisfying condition"
- "Maximum sum of subarray of size k" (fixed window)
- "Minimum window containing all characters" (variable window)
- "At most k distinct elements" in a subarray

**The two questions to answer before coding any sliding window:**

> 1. **What makes the window valid?** (the condition that must hold)
> 2. **What forces the window to shrink?** (when the condition is violated)

Answer these two before writing a single line of code.

### Two Forms

**Fixed Size Window** — window size k never changes:

```java
public double findMaxAverage(int[] nums, int k) {
    // Build first window
    int windowSum = 0;
    for (int i = 0; i < k; i++) windowSum += nums[i];

    int maxSum = windowSum;

    // Slide: add right element, remove left element
    for (int i = k; i < nums.length; i++) {
        windowSum += nums[i];           // gain right
        windowSum -= nums[i - k];       // lose left
        maxSum = Math.max(maxSum, windowSum);
    }

    return (double) maxSum / k;
}
```

**Variable Size Window** — window expands and contracts:

```java
public int lengthOfLongestSubstring(String s) {
    Set<Character> window = new HashSet<>();
    int left = 0, maxLen = 0;

    for (int right = 0; right < s.length(); right++) {
        char c = s.charAt(right);

        // Shrink from left until window is valid again
        while (window.contains(c)) {
            window.remove(s.charAt(left));
            left++;
        }

        window.add(c);                              // expand right
        maxLen = Math.max(maxLen, right - left + 1);
    }

    return maxLen;
}
```

**Variable Window with HashMap (frequency tracking):**

```java
public int characterReplacement(String s, int k) {
    int[] freq = new int[26];                       // int[26] faster than HashMap for lowercase
    int left = 0, maxFreq = 0, maxLen = 0;

    for (int right = 0; right < s.length(); right++) {
        freq[s.charAt(right) - 'A']++;
        maxFreq = Math.max(maxFreq, freq[s.charAt(right) - 'A']);

        // Window size - most frequent char count = chars we need to replace
        // If that exceeds k → window invalid → shrink
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

**Full Sliding Window Template (Two HashMaps — Minimum Window Substring):**

```java
public String minWindow(String s, String t) {
    if (s.isEmpty() || t.isEmpty()) return "";

    // What we need
    Map<Character, Integer> need = new HashMap<>();
    for (char c : t.toCharArray()) need.merge(c, 1, Integer::sum);

    // What our current window has
    Map<Character, Integer> have = new HashMap<>();

    int left = 0;
    int formed = 0;                                 // chars satisfied at required frequency
    int required = need.size();                     // distinct chars we need to satisfy

    int minLen = Integer.MAX_VALUE;
    int resLeft = 0, resRight = 0;

    for (int right = 0; right < s.length(); right++) {
        // Expand: add right character to window
        char c = s.charAt(right);
        have.merge(c, 1, Integer::sum);

        // Check if this char's requirement is now satisfied
        if (need.containsKey(c) && have.get(c).equals(need.get(c))) {
            formed++;
        }

        // Shrink from left while window is valid (all requirements met)
        while (formed == required) {
            // Record result if this window is smaller
            if (right - left + 1 < minLen) {
                minLen = right - left + 1;
                resLeft = left;
                resRight = right;
            }

            // Remove left character
            char l = s.charAt(left++);
            have.merge(l, -1, Integer::sum);
            if (need.containsKey(l) && have.get(l) < need.get(l)) {
                formed--;
            }
        }
    }

    return minLen == Integer.MAX_VALUE ? "" : s.substring(resLeft, resRight + 1);
}
```

### Complexity

| Form | Time | Space | Note |
|------|------|-------|------|
| Fixed window | O(n) | O(1) | Sum/product tracked in single variable |
| Variable window (HashSet) | O(n) | O(min(n,m)) | Each element enters/leaves window once |
| Variable window (HashMap) | O(n) | O(k) | k = alphabet size or distinct chars |
| Two-map (min window) | O(n+m) | O(n+m) | n=s length, m=t length |

### Decision: Fixed vs Variable Window

```
Is the window size k given in the problem?
├── YES → Fixed window
│         Slide by: add nums[right], remove nums[right-k]
└── NO  → Variable window
          Define: what makes window VALID?
          Expand right freely.
          Shrink left when window becomes INVALID.
```

---

## 📈 Pattern 4 — Kadane's Algorithm

### What It Is

Kadane's Algorithm finds the maximum sum contiguous subarray in O(n) time and O(1) space. It is a special case of Dynamic Programming where the state compresses to a single variable.

### The Core Intuition

At every index `i`, you face a binary choice:

- **Extend** the previous subarray: `currentSum + nums[i]`
- **Start fresh** at `nums[i]` alone

You take whichever is larger. If the previous subarray has pulled your sum negative, cut it loose — starting fresh at `nums[i]` is better. If it's positive, it's worth extending.

```
nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]

i=0: cur = max(-2, -2)         = -2   | max = -2
i=1: cur = max(1,  -2+1=-1)    =  1   | max =  1
i=2: cur = max(-3,  1-3=-2)    = -2   | max =  1
i=3: cur = max(4,  -2+4= 2)    =  4   | max =  4
i=4: cur = max(-1,  4-1= 3)    =  3   | max =  4
i=5: cur = max(2,   3+2= 5)    =  5   | max =  5
i=6: cur = max(1,   5+1= 6)    =  6   | max =  6
i=7: cur = max(-5,  6-5= 1)    =  1   | max =  6
i=8: cur = max(4,   1+4= 5)    =  5   | max =  6

Answer: 6  →  subarray [4, -1, 2, 1]
```

### When to Use It — Recognition Signals

- "Maximum sum subarray" — textbook Kadane's
- "Maximum product subarray" — Kadane's variant (track max AND min)
- "Largest sum of a circular subarray"
- Any problem asking for the best contiguous segment where "best" is additive

### Java Templates

**Classic Kadane's (Maximum Sum Subarray):**

```java
public int maxSubArray(int[] nums) {
    if (nums == null || nums.length == 0) return 0;

    int currentSum = nums[0];
    int maxSum = nums[0];

    for (int i = 1; i < nums.length; i++) {
        // Core of Kadane's: extend or restart
        currentSum = Math.max(nums[i], currentSum + nums[i]);
        maxSum = Math.max(maxSum, currentSum);
    }

    return maxSum;
}
```

**Kadane's with subarray indices (find the actual subarray):**

```java
public int[] maxSubArrayWithIndices(int[] nums) {
    int currentSum = nums[0], maxSum = nums[0];
    int start = 0, end = 0, tempStart = 0;

    for (int i = 1; i < nums.length; i++) {
        if (nums[i] > currentSum + nums[i]) {
            currentSum = nums[i];
            tempStart = i;                          // potential new start
        } else {
            currentSum += nums[i];
        }

        if (currentSum > maxSum) {
            maxSum = currentSum;
            start = tempStart;
            end = i;
        }
    }

    return new int[]{start, end, maxSum};
}
```

**Kadane's Variant — Maximum Product Subarray:**

```java
public int maxProduct(int[] nums) {
    // Track BOTH max and min — two negatives multiply to positive
    int maxProd = nums[0];
    int minProd = nums[0];
    int result = nums[0];

    for (int i = 1; i < nums.length; i++) {
        // When nums[i] is negative, max and min swap roles
        if (nums[i] < 0) {
            int temp = maxProd;
            maxProd = minProd;
            minProd = temp;
        }

        maxProd = Math.max(nums[i], maxProd * nums[i]);
        minProd = Math.min(nums[i], minProd * nums[i]);

        result = Math.max(result, maxProd);
    }

    return result;
}
```

### Complexity

| Variant | Time | Space | Note |
|---------|------|-------|------|
| Classic Kadane's | O(n) | O(1) | Single pass, two variables |
| With index tracking | O(n) | O(1) | Three extra variables |
| Max product | O(n) | O(1) | Track max AND min simultaneously |

### Why currentSum Initialises to nums[0], Not 0

If you initialise `currentSum = 0`, you imply an empty subarray is valid (sum = 0). But the problem asks for a *non-empty* subarray. Setting `currentSum = nums[0]` means the minimum subarray considered is the first element alone — correct for non-empty constraint.

Always seed both `currentSum` and `maxSum` with `nums[0]`.

---

## 🔲 Pattern 5 — Matrix Traversal

### What It Is

2D arrays introduce a coordinate system — every operation maps `(row, col)` indices to a position. The key skills are: directional movement, boundary guards, in-place transformation, and recognising when a matrix problem is secretly a 1D problem in disguise.

### Java Template — 4-Directional Movement

```java
// Reuse this exact template in every grid problem forever
int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};  // right, left, down, up

for (int[] dir : dirs) {
    int newRow = row + dir[0];
    int newCol = col + dir[1];

    // Always guard bounds before accessing
    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
        // safe to access grid[newRow][newCol]
    }
}
```

**In-place Matrix Rotation (90° clockwise):**

```java
public void rotate(int[][] matrix) {
    int n = matrix.length;

    // Step 1: Transpose — swap matrix[i][j] with matrix[j][i]
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {          // j starts at i+1 to avoid double-swap
            int temp = matrix[i][j];
            matrix[i][j] = matrix[j][i];
            matrix[j][i] = temp;
        }
    }

    // Step 2: Reverse each row
    for (int i = 0; i < n; i++) {
        int left = 0, right = n - 1;
        while (left < right) {
            int temp = matrix[i][left];
            matrix[i][left++] = matrix[i][right];
            matrix[i][right--] = temp;
        }
    }
}
```

---

## 📑 Problem Index

| # | Problem | Difficulty | Pattern | Sub-folder | Key Insight |
|---|---------|------------|---------|------------|-------------|
| 1 | [Two Sum](https://leetcode.com/problems/two-sum/) | 🟢 Easy | HashMap | `two-pointers/` | Complement lookup — target - nums[i] |
| 2 | [Valid Palindrome](https://leetcode.com/problems/valid-palindrome/) | 🟢 Easy | Two Pointer | `two-pointers/` | Skip non-alphanumeric, converge |
| 3 | [Two Sum II](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/) | 🟡 Medium | Two Pointer | `two-pointers/` | Sorted input enables safe skip |
| 4 | [Best Time to Buy & Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/) | 🟢 Easy | Greedy | `two-pointers/` | Track running min price, one pass |
| 5 | [Container With Most Water](https://leetcode.com/problems/container-with-most-water/) | 🟡 Medium | Two Pointer | `two-pointers/` | Always move the shorter side |
| 6 | [3Sum](https://leetcode.com/problems/3sum/) | 🟡 Medium | Sort + Two Ptr | `two-pointers/` | Fix i, two pointer rest, skip dups |
| 7 | [Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/) | 🔴 Hard | Two Pointer | `two-pointers/` | water[i] = min(leftMax, rightMax) - h[i] |
| 8 | [Running Sum of 1D Array](https://leetcode.com/problems/running-sum-of-1d-array/) | 🟢 Easy | Prefix Sum | `prefix-sum/` | prefix[i] = prefix[i-1] + nums[i] |
| 9 | [Find Pivot Index](https://leetcode.com/problems/find-pivot-index/) | 🟢 Easy | Prefix Sum | `prefix-sum/` | leftSum == total - leftSum - nums[i] |
| 10 | [Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/) | 🟡 Medium | Prefix + HashMap | `prefix-sum/` | count += seen[prefixSum - k] |
| 11 | [Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/) | 🟡 Medium | Prefix Product | `prefix-sum/` | prefix pass then suffix pass |
| 12 | [Maximum Subarray](https://leetcode.com/problems/maximum-subarray/) | 🟡 Medium | Kadane's | `kadanes/` | cur = max(nums[i], cur + nums[i]) |
| 13 | [Maximum Product Subarray](https://leetcode.com/problems/maximum-product-subarray/) | 🟡 Medium | Kadane's variant | `kadanes/` | Track max AND min (negatives flip) |
| 14 | [Longest Substring No Repeat](https://leetcode.com/problems/longest-substring-without-repeating-characters/) | 🟡 Medium | Sliding Window | `sliding-window/` | HashSet window, shrink on duplicate |
| 15 | [Permutation in String](https://leetcode.com/problems/permutation-in-string/) | 🟡 Medium | Fixed Window | `sliding-window/` | int[26] freq compare, slide size=len(s1) |
| 16 | [Longest Repeating Char Replace](https://leetcode.com/problems/longest-repeating-character-replacement/) | 🟡 Medium | Sliding Window | `sliding-window/` | valid if (size - maxFreq) ≤ k |
| 17 | [Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/) | 🟡 Medium | Sliding Window | `sliding-window/` | need + have maps, formed counter |
| 18 | [Sort Colors](https://leetcode.com/problems/sort-colors/) | 🟡 Medium | 3 Pointers | `two-pointers/` | Dutch National Flag — low/mid/high |

---

## ⏱️ Complexity Cheat Sheet

| Pattern | Time | Space | When to use |
|---------|------|-------|-------------|
| Two Pointer (converging) | O(n) | O(1) | Sorted array, find pair |
| Two Pointer (same dir) | O(n) | O(1) | Remove/deduplicate in-place |
| Two Pointer + initial sort | O(n log n) | O(1) | 3Sum, unsorted find pair |
| Prefix Sum (build) | O(n) | O(n) | Multiple range queries |
| Prefix Sum + HashMap | O(n) | O(n) | Count subarrays = target |
| Sliding Window (fixed) | O(n) | O(1) | Max/min of size-k subarrays |
| Sliding Window (variable) | O(n) | O(k) | Longest/shortest with constraint |
| Kadane's | O(n) | O(1) | Max sum subarray |
| Kadane's product variant | O(n) | O(1) | Max product subarray |

---

## ⚠️ Common Mistakes

| Mistake | Example | Fix |
|---------|---------|-----|
| Two Pointer on unsorted array | Applying converging pointer without sorting first | Always sort if the problem doesn't guarantee sorted input |
| `left <= right` instead of `left < right` | Off-by-one causing index collision | Use strict `<` for converging — pointers should not cross |
| Forgetting duplicate skip in 3Sum | `while(l<r && nums[l]==nums[l-1]) l++` omitted | After recording result, skip all equal values on both sides |
| Prefix sum seed missing | Not putting `seen.put(0, 1)` in HashMap+prefix | Without seed, subarrays starting at index 0 are missed |
| Sliding window: shrinking too aggressively | `if` instead of `while` when contracting left | Use `while (window invalid)` not `if` — may need multiple shrinks |
| Kadane's seeded with 0 | `currentSum = 0` instead of `nums[0]` | Empty subarray not valid — seed with `nums[0]` |
| Dutch Flag: `mid++` after swap with `high` | Increments before examining swapped element | Only `mid++` after swap with `low` or no swap — never after `high` swap |
| Matrix: `j` starts at 0 in transpose | Double-swaps reset elements to original | In transpose, `j` must start at `i+1` |
| Integer overflow in mid calculation | `(left + right) / 2` overflows for large values | Always use `left + (right - left) / 2` |

---

## ☕ Java Quick Reference

Essential Java for Arrays & Strings — no more syntax hesitation.

```java
// ── Arrays ─────────────────────────────────────────────────────
int[] arr = new int[n];                 // default: all zeros
int[] arr = {1, 2, 3};                 // initialise with values
int len = arr.length;                   // field, NOT method
Arrays.sort(arr);                       // in-place O(n log n)
Arrays.sort(arr, 2, 5);                // sort subarray [2,5)
int[] copy = Arrays.copyOf(arr, n);    // copy first n elements
Arrays.fill(arr, 0);                   // fill entire array with 0
int idx = Arrays.binarySearch(arr, target); // sorted array only

// ── 2D Arrays ──────────────────────────────────────────────────
int[][] mat = new int[rows][cols];
int rows = mat.length;
int cols = mat[0].length;

// ── Strings ────────────────────────────────────────────────────
int len = s.length();                   // method, NOT field
char c = s.charAt(i);                  // O(1) character access
char[] chars = s.toCharArray();        // convert to char array
String sub = s.substring(i, j);       // [i, j) — j excluded
int idx = s.indexOf('c');             // first occurrence, -1 if absent
boolean has = s.contains("ab");
String lower = s.toLowerCase();
String trimmed = s.trim();
String[] parts = s.split(",");
String joined = String.join("-", "a", "b"); // "a-b"
boolean eq = s.equals(t);             // NEVER use == for strings

// ── StringBuilder (use instead of String += in loops) ──────────
StringBuilder sb = new StringBuilder();
sb.append('a');
sb.append("hello");
sb.insert(0, 'X');
sb.reverse();
sb.deleteCharAt(i);
String result = sb.toString();

// ── Character utilities ────────────────────────────────────────
Character.isLetterOrDigit(c)           // true for [a-z A-Z 0-9]
Character.isAlphabetic(c)
Character.isDigit(c)
Character.toLowerCase(c)
Character.toUpperCase(c)
int val = c - '0';                     // char digit to int (e.g. '7' → 7)
int idx = c - 'a';                     // lowercase letter to 0-based index

// ── HashMap ────────────────────────────────────────────────────
Map<Integer, Integer> map = new HashMap<>();
map.put(k, v);
map.get(k);                            // null if absent
map.getOrDefault(k, 0);               // safe default
map.containsKey(k);
map.remove(k);
map.merge(k, 1, Integer::sum);        // map[k]++ safely
for (Map.Entry<Integer,Integer> e : map.entrySet()) { e.getKey(); e.getValue(); }

// ── HashSet ────────────────────────────────────────────────────
Set<Integer> set = new HashSet<>();
set.add(x);
set.contains(x);                       // O(1)
set.remove(x);

// ── PriorityQueue (Heap) ───────────────────────────────────────
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
minHeap.offer(x);                      // add
minHeap.poll();                        // remove and return smallest
minHeap.peek();                        // view smallest without removing

// ── Deque (use as Stack AND Queue) ────────────────────────────
Deque<Integer> stack = new ArrayDeque<>();
stack.push(x);   stack.pop();   stack.peek();   // stack ops
stack.offer(x);  stack.poll();  stack.peekFirst(); // queue ops

// ── Useful Math ────────────────────────────────────────────────
Math.max(a, b);   Math.min(a, b);   Math.abs(x);
Math.pow(base, exp);                   // returns double
(int) Math.pow(base, exp);            // cast to int
Integer.MAX_VALUE;   Integer.MIN_VALUE;
```

---

<div align="center">

*Part of [dsa-solutions-java](https://github.com/YOUR_USERNAME/dsa-solutions-java) · Sahil Kundu's FAANG Preparation*

**Next topic → [02 — Linked List](../02-linked-list/README.md)**

</div>