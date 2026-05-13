<div align="center">

# 📚 DSA Solutions — Java
### Sahil Kundu's FAANG Preparation Repository

[![Java](https://img.shields.io/badge/Language-Java-ED8B00?style=flat-square&logo=openjdk&logoColor=white)](https://www.java.com)
[![LeetCode](https://img.shields.io/badge/Platform-LeetCode-FFA116?style=flat-square&logo=leetcode&logoColor=white)](https://leetcode.com/YOUR_LEETCODE)
[![Problems](https://img.shields.io/badge/Problems_Solved-0%20%2F%20280-brightgreen?style=flat-square)](#-progress-tracker)
[![Streak](https://img.shields.io/badge/Daily_Streak-Day_1-FF6B6B?style=flat-square)](#)
[![Last Updated](https://img.shields.io/badge/Last_Updated-Week_1_Month_1-blue?style=flat-square)](#)

<br/>

**280+ LeetCode problems solved in Java — organized by pattern, not just topic.**
Every solution has: problem link · brute force · optimal approach · Big-O analysis · edge cases.

[📊 Progress Tracker](#-progress-tracker) &nbsp;•&nbsp;
[🗺️ Pattern Index](#️-pattern-index) &nbsp;•&nbsp;
[📁 Repository Structure](#-repository-structure) &nbsp;•&nbsp;
[📋 Solution Format](#-solution-format) &nbsp;•&nbsp;
[🗓️ Weekly Log](#️-weekly-log)

</div>

---

## 🎯 What This Repository Is

This is **not** a random dump of LeetCode solutions.

Every problem here is part of a deliberate, structured 18-month plan to go from zero DSA knowledge to cracking FAANG. Problems are organized by **pattern** — because FAANG interviews don't test whether you've seen a specific problem before. They test whether you can recognize a pattern and apply it to something new.

**The core philosophy:**
- 🧩 **Patterns over problems** — master 15 patterns, recognize any variant
- 📝 **Understand over memorize** — every solution explains *why*, not just *how*
- 🔁 **Spaced repetition** — problems are revisited at Day 3, Day 7, Day 15
- 🗣️ **Narration-first** — every approach is written as if explaining to an interviewer
- 📈 **Consistency over intensity** — one commit every single day, no exceptions

---

## 📊 Progress Tracker

> Updated every Sunday. &nbsp;|&nbsp; LeetCode profile: [18_sahil](https://leetcode.com/u/18_sahil/)

| # | Topic | Patterns | Easy | Medium | Hard | Total | Month |
|---|-------|----------|------|--------|------|-------|-------|
| 01 | [Arrays & Strings](#01-arrays--strings) | Two Pointer · Prefix Sum · Sliding Window · Kadane's | 0 | 0 | 0 | **0** | 1 |
| 02 | [Linked List](#02-linked-list) | Fast & Slow · Dummy Node · Reversal | 0 | 0 | 0 | **0** | 1 |
| 03 | [Stacks & Queues](#03-stacks--queues) | Monotonic Stack · Two Stacks · Deque | 0 | 0 | 0 | **0** | 1 |
| 04 | [Binary Search](#04-binary-search) | Classic · Rotated · Search on Answer | 0 | 0 | 0 | **0** | 1 |
| 05 | [Trees](#05-trees) | DFS · BFS · BST · Path Sum · LCA | 0 | 0 | 0 | **0** | 2 |
| 06 | [Graphs](#06-graphs) | BFS · DFS · Topo Sort · Union Find · Dijkstra | 0 | 0 | 0 | **0** | 2 |
| 07 | [Heaps & Priority Queues](#07-heaps--priority-queues) | Top K · Two Heaps · K-way Merge | 0 | 0 | 0 | **0** | 2 |
| 08 | [Hashing & Two Pointers](#08-hashing--two-pointers) | HashMap · HashSet · Multi-pointer | 0 | 0 | 0 | **0** | 2 |
| 09 | [Backtracking](#09-backtracking) | Subsets · Permutations · Pruning | 0 | 0 | 0 | **0** | 3 |
| 10 | [Dynamic Programming](#10-dynamic-programming) | 1D · 2D · Knapsack · LCS · String · Intervals | 0 | 0 | 0 | **0** | 3 |
| 11 | [Tries](#11-tries) | Insert/Search · Prefix · XOR Trie | 0 | 0 | 0 | **0** | 3 |
| 12 | [Intervals & Greedy](#12-intervals--greedy) | Merge · Scheduling · Jump Game | 0 | 0 | 0 | **0** | 3 |
| 13 | [Bit Manipulation](#13-bit-manipulation) | XOR · Bitmask · Brian Kernighan | 0 | 0 | 0 | **0** | 4 |
| 14 | [Advanced](#14-advanced) | Segment Tree · Monotonic Deque | 0 | 0 | 0 | **0** | 4 |
| | **TOTAL** | | **0** | **0** | **0** | **0 / 280** | |

```
Overall Progress
░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░  1 / 280 (0.3%)

Easy    ░░░░░░░░░░░░░░░░░░░░  1 / 75
Medium  ░░░░░░░░░░░░░░░░░░░░  0 / 155
Hard    ░░░░░░░░░░░░░░░░░░░░  0 / 50
```

---

## 🗺️ Pattern Index

> Each pattern links to its folder. Each folder has its own `README.md` explaining the pattern, the Java template, when to use it, and all problems solved.

### 01 Arrays & Strings

| Pattern | When to Use | Folder |
|---------|-------------|--------|
| **Two Pointers** | Sorted array · Find pair/triplet · Palindrome check · Remove duplicates | [`/01-arrays-and-strings/two-pointers/`](./01-arrays-and-strings/two-pointers/) |
| **Prefix Sum** | Range sum queries · Subarray with target sum · Pivot index | [`/01-arrays-and-strings/prefix-sum/`](./01-arrays-and-strings/prefix-sum/) |
| **Sliding Window** | Contiguous subarray/substring with a dynamic constraint | [`/01-arrays-and-strings/sliding-window/`](./01-arrays-and-strings/sliding-window/) |
| **Kadane's Algorithm** | Maximum/minimum subarray sum · Max product subarray | [`/01-arrays-and-strings/kadanes/`](./01-arrays-and-strings/kadanes/) |
| **Matrix Traversal** | 2D array rotation · Spiral · Set zeroes | [`/01-arrays-and-strings/matrix/`](./01-arrays-and-strings/matrix/) |

### 02 Linked List

| Pattern | When to Use | Folder |
|---------|-------------|--------|
| **Fast & Slow Pointer** | Cycle detection · Middle of list · Nth from end | [`/02-linked-list/fast-slow-pointer/`](./02-linked-list/fast-slow-pointer/) |
| **In-place Reversal** | Reverse whole list · Reverse in groups · Reorder | [`/02-linked-list/reversal/`](./02-linked-list/reversal/) |
| **Dummy Node** | Insert/delete where head might change · Merge lists | [`/02-linked-list/dummy-node/`](./02-linked-list/dummy-node/) |

### 03 Stacks & Queues

| Pattern | When to Use | Folder |
|---------|-------------|--------|
| **Monotonic Stack** | Next greater/smaller element · Histogram area · Temperature | [`/03-stacks-and-queues/monotonic-stack/`](./03-stacks-and-queues/monotonic-stack/) |
| **Stack Design** | Min/max stack · Queue using stacks · Expression eval | [`/03-stacks-and-queues/stack-design/`](./03-stacks-and-queues/stack-design/) |
| **Monotonic Deque** | Sliding window maximum/minimum | [`/03-stacks-and-queues/monotonic-deque/`](./03-stacks-and-queues/monotonic-deque/) |

### 04 Binary Search

| Pattern | When to Use | Folder |
|---------|-------------|--------|
| **Classic Binary Search** | Search in sorted array · Left/right boundary | [`/04-binary-search/classic/`](./04-binary-search/classic/) |
| **Rotated Array Search** | Array rotated at unknown pivot | [`/04-binary-search/rotated/`](./04-binary-search/rotated/) |
| **Search on Answer Space** | Minimize max · Maximize min · Feasibility check | [`/04-binary-search/search-on-answer/`](./04-binary-search/search-on-answer/) |
| **2D Matrix Search** | Sorted matrix row+col search | [`/04-binary-search/matrix-search/`](./04-binary-search/matrix-search/) |

### 05 Trees

| Pattern | When to Use | Folder |
|---------|-------------|--------|
| **DFS — Preorder/Inorder/Postorder** | Path problems · Serialize · Validate BST | [`/05-trees/dfs/`](./05-trees/dfs/) |
| **BFS — Level Order** | Level-by-level processing · Zigzag · Right side view | [`/05-trees/bfs/`](./05-trees/bfs/) |
| **BST Operations** | Insert · Delete · Kth smallest · Validate | [`/05-trees/bst/`](./05-trees/bst/) |
| **Path Sum Problems** | Root-to-leaf paths · Max path sum · Path = target | [`/05-trees/path-sum/`](./05-trees/path-sum/) |
| **LCA** | Lowest Common Ancestor variants | [`/05-trees/lca/`](./05-trees/lca/) |

### 06 Graphs

| Pattern | When to Use | Folder |
|---------|-------------|--------|
| **BFS** | Shortest path (unweighted) · Level traversal · Islands | [`/06-graphs/bfs/`](./06-graphs/bfs/) |
| **DFS** | Connected components · Cycle detection · Path existence | [`/06-graphs/dfs/`](./06-graphs/dfs/) |
| **Topological Sort** | Course schedule · Task ordering · Build systems | [`/06-graphs/topological-sort/`](./06-graphs/topological-sort/) |
| **Union Find (DSU)** | Connected components · Redundant connection · MST | [`/06-graphs/union-find/`](./06-graphs/union-find/) |
| **Dijkstra** | Shortest path in weighted graph | [`/06-graphs/dijkstra/`](./06-graphs/dijkstra/) |

### 07 Heaps & Priority Queues

| Pattern | When to Use | Folder |
|---------|-------------|--------|
| **Top K Elements** | Kth largest/smallest · K frequent elements | [`/07-heaps/top-k/`](./07-heaps/top-k/) |
| **Two Heaps** | Median maintenance · Data stream | [`/07-heaps/two-heaps/`](./07-heaps/two-heaps/) |
| **K-way Merge** | Merge K sorted lists/arrays | [`/07-heaps/k-way-merge/`](./07-heaps/k-way-merge/) |

### 08 Backtracking

| Pattern | When to Use | Folder |
|---------|-------------|--------|
| **Subsets & Combinations** | Generate all subsets · Combination sum | [`/08-backtracking/subsets/`](./08-backtracking/subsets/) |
| **Permutations** | All arrangements · Next permutation | [`/08-backtracking/permutations/`](./08-backtracking/permutations/) |
| **Constraint Satisfaction** | N-Queens · Sudoku · Word search | [`/08-backtracking/constraint/`](./08-backtracking/constraint/) |

### 09 Dynamic Programming

| Pattern | When to Use | Folder |
|---------|-------------|--------|
| **1D DP** | Climbing stairs · House robber · Decode ways | [`/09-dynamic-programming/1d/`](./09-dynamic-programming/1d/) |
| **2D Grid DP** | Unique paths · Min path sum · Dungeon game | [`/09-dynamic-programming/2d-grid/`](./09-dynamic-programming/2d-grid/) |
| **Knapsack Variants** | 0/1 Knapsack · Coin change · Partition equal subset | [`/09-dynamic-programming/knapsack/`](./09-dynamic-programming/knapsack/) |
| **LCS / LIS** | Longest common subsequence · Longest increasing subseq | [`/09-dynamic-programming/lcs-lis/`](./09-dynamic-programming/lcs-lis/) |
| **String DP** | Edit distance · Word break · Palindrome partition | [`/09-dynamic-programming/string-dp/`](./09-dynamic-programming/string-dp/) |
| **Interval DP** | Burst balloons · Matrix chain multiplication | [`/09-dynamic-programming/interval-dp/`](./09-dynamic-programming/interval-dp/) |

### 10 Tries

| Pattern | When to Use | Folder |
|---------|-------------|--------|
| **Standard Trie** | Prefix search · Autocomplete · Word dictionary | [`/10-tries/standard/`](./10-tries/standard/) |
| **XOR Trie** | Maximum XOR · Bit-prefix problems | [`/10-tries/xor-trie/`](./10-tries/xor-trie/) |

### 11 Intervals & Greedy

| Pattern | When to Use | Folder |
|---------|-------------|--------|
| **Merge Intervals** | Overlapping intervals · Insert interval | [`/11-intervals-and-greedy/merge/`](./11-intervals-and-greedy/merge/) |
| **Greedy Scheduling** | Meeting rooms · Non-overlapping intervals | [`/11-intervals-and-greedy/scheduling/`](./11-intervals-and-greedy/scheduling/) |
| **Jump Game** | Can reach end · Min jumps · Gas station | [`/11-intervals-and-greedy/jump/`](./11-intervals-and-greedy/jump/) |

### 12 Bit Manipulation

| Pattern | When to Use | Folder |
|---------|-------------|--------|
| **XOR Tricks** | Find single/missing number · Pairs cancel out | [`/12-bit-manipulation/xor/`](./12-bit-manipulation/xor/) |
| **Bitmask** | Subset enumeration · State compression DP | [`/12-bit-manipulation/bitmask/`](./12-bit-manipulation/bitmask/) |

---

## 📁 Repository Structure

```
dsa-solutions-java/
│
├── README.md                          ← You are here
├── PROGRESS.md                        ← Detailed weekly log
├── ERROR_JOURNAL.md                   ← Mistakes + root causes + fixes
├── .gitignore                         ← Ignores: *.class, /target, /.idea
├── pom.xml                            ← Maven project root
│
├── 01-arrays-and-strings/
│   ├── README.md                      ← Pattern explanations + problem table
│   ├── two-pointers/
│   │   ├── TwoSum.java
│   │   ├── ValidPalindrome.java
│   │   ├── ContainerWithMostWater.java
│   │   ├── ThreeSum.java
│   │   └── TrappingRainWater.java
│   ├── prefix-sum/
│   │   ├── RunningSumArray.java
│   │   ├── FindPivotIndex.java
│   │   ├── SubarraySumEqualsK.java
│   │   └── ProductArrayExceptSelf.java
│   ├── sliding-window/
│   │   ├── LongestSubstringWithoutRepeating.java
│   │   ├── MinimumWindowSubstring.java
│   │   ├── PermutationInString.java
│   │   ├── LongestRepeatingCharReplacement.java
│   │   └── FruitsIntoBaskets.java
│   ├── kadanes/
│   │   ├── MaximumSubarray.java
│   │   └── MaximumProductSubarray.java
│   └── matrix/
│       ├── RotateImage.java
│       ├── SpiralMatrix.java
│       └── SetMatrixZeroes.java
│
├── 02-linked-list/
│   ├── README.md
│   ├── fast-slow-pointer/
│   │   ├── LinkedListCycle.java
│   │   └── FindDuplicateNumber.java
│   ├── reversal/
│   │   ├── ReverseLinkedList.java
│   │   └── ReorderList.java
│   └── dummy-node/
│       ├── MergeTwoSortedLists.java
│       ├── RemoveNthFromEnd.java
│       └── MergeKSortedLists.java
│
├── 03-stacks-and-queues/
│   ├── README.md
│   ├── monotonic-stack/
│   │   ├── DailyTemperatures.java
│   │   ├── LargestRectangleHistogram.java
│   │   └── NextGreaterElement.java
│   ├── stack-design/
│   │   ├── ValidParentheses.java
│   │   └── MinStack.java
│   └── monotonic-deque/
│       └── SlidingWindowMaximum.java
│
├── 04-binary-search/
│   ├── README.md
│   ├── classic/
│   │   ├── BinarySearch.java
│   │   ├── FirstBadVersion.java
│   │   └── SearchInsertPosition.java
│   ├── rotated/
│   │   ├── SearchInRotatedArray.java
│   │   └── FindMinimumRotated.java
│   ├── search-on-answer/
│   │   ├── KokoEatingBananas.java
│   │   └── CapacityToShipPackages.java
│   └── matrix-search/
│       └── Search2DMatrix.java
│
├── 05-trees/             ← Month 2
├── 06-graphs/            ← Month 2
├── 07-heaps/             ← Month 2
├── 08-backtracking/      ← Month 3
├── 09-dynamic-programming/ ← Month 3
├── 10-tries/             ← Month 3
├── 11-intervals-and-greedy/ ← Month 3
├── 12-bit-manipulation/  ← Month 4
└── 13-advanced/          ← Month 4
```

---

## 📋 Solution Format

Every `.java` file in this repository follows the exact same structure.
This consistency means you can open any file and immediately find what you need.

```java
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

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    // ── OPTIMAL SOLUTION ────────────────────────────────────────
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) return new int[]{};

        // value → index map for O(1) complement lookup
        Map<Integer, Integer> seen = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (seen.containsKey(complement)) {
                return new int[]{seen.get(complement), i};
            }

            seen.put(nums[i], i); // add AFTER check — no self-use
        }

        return new int[]{}; // guaranteed solution per constraints
    }

    // ── BRUTE FORCE (reference only — do not submit) ─────────────
    public int[] twoSumBrute(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++)
            for (int j = i + 1; j < nums.length; j++)
                if (nums[i] + nums[j] == target)
                    return new int[]{i, j};
        return new int[]{};
    }
}
```

---

## 🗓️ Weekly Log

> Every Sunday: what I covered, how many problems, test score, and the one biggest mistake of the week.

| Week | Month | Topics | Problems | Test Score | Biggest Mistake This Week |
|------|-------|--------|----------|------------|--------------------------|
| 01 | 1 | Arrays · Two Pointers · Prefix Sum · Kadane's | 0 / 18 | — | — |
| 02 | 1 | LinkedList · Stacks · Monotonic Stack | 0 / 12 | — | — |
| 03 | 1 | Binary Search — all 3 forms | 0 / 10 | — | — |
| 04 | 1 | Integration · Mock interview · Full revision | 0 / 0 | — | — |
| 05 | 2 | Trees — DFS, BFS, BST | — | — | — |
| 06 | 2 | Trees — Paths, LCA · Heaps intro | — | — | — |
| 07 | 2 | Graphs — BFS, DFS, Union Find | — | — | — |
| 08 | 2 | Graphs — Topological Sort, Dijkstra · Mock | — | — | — |
| 09 | 3 | Backtracking — Subsets, Permutations | — | — | — |
| 10 | 3 | Backtracking — Constraint · DP intro | — | — | — |
| 11 | 3 | DP — 1D, 2D Grid, Knapsack | — | — | — |
| 12 | 3 | DP — LCS, String DP, Intervals · Mock | — | — | — |
| 13 | 4 | DP — advanced · Tries | — | — | — |
| 14 | 4 | Intervals & Greedy · Bit Manipulation | — | — | — |
| 15 | 4 | Advanced patterns · Hard problems | — | — | — |
| 16 | 4 | Full integration · Final mock · Month 4 close | — | — | — |

---

## 📖 Error Journal Summary

> Full error journal: [`ERROR_JOURNAL.md`](./ERROR_JOURNAL.md)
> Every mistake I made, its root cause, and my personal rule to never repeat it.

| # | Mistake Type | Example | My Rule |
|---|-------------|---------|---------|
| — | *Logged weekly* | — | — |

---

## 🔁 Spaced Repetition Schedule

Every problem I solve is reviewed on this schedule to move it from short-term to long-term memory:

```
Solve on Day 0
    ↓
Re-solve cold on Day 3  (can I still do it without notes?)
    ↓
Re-solve cold on Day 7  (can I do it in under 15 min?)
    ↓
Re-solve cold on Day 15 (can I do it in under 10 min clean?)
    ↓
✅ OWNED — move to monthly revision pool
```

---

## 🧰 Java Templates

Quick-reference templates I use for the most common patterns.
Full explanations in each topic's `README.md`.

<details>
<summary><strong>Two Pointers (Converging)</strong></summary>

```java
int left = 0, right = nums.length - 1;
while (left < right) {
    int sum = nums[left] + nums[right];
    if (sum == target) { /* found */ break; }
    else if (sum < target) left++;
    else right--;
}
```
</details>

<details>
<summary><strong>Sliding Window (Variable Size)</strong></summary>

```java
int left = 0, result = 0;
Map<Character, Integer> window = new HashMap<>();
for (int right = 0; right < s.length(); right++) {
    char c = s.charAt(right);
    window.merge(c, 1, Integer::sum);          // expand
    while (/* window invalid */) {
        char l = s.charAt(left++);
        window.merge(l, -1, Integer::sum);     // shrink
        if (window.get(l) == 0) window.remove(l);
    }
    result = Math.max(result, right - left + 1);
}
```
</details>

<details>
<summary><strong>Binary Search (Universal Template)</strong></summary>

```java
int left = 0, right = nums.length - 1;
while (left <= right) {
    int mid = left + (right - left) / 2;  // no overflow
    if (nums[mid] == target) return mid;
    else if (nums[mid] < target) left = mid + 1;
    else right = mid - 1;
}
return -1; // not found
```
</details>

<details>
<summary><strong>Binary Search on Answer Space</strong></summary>

```java
int left = minPossible, right = maxPossible;
while (left < right) {
    int mid = left + (right - left) / 2;
    if (isFeasible(mid)) right = mid;     // try smaller
    else left = mid + 1;                  // need bigger
}
return left;

// isFeasible(x): can we solve the problem with answer = x?
```
</details>

<details>
<summary><strong>BFS (Graph / Tree Level Order)</strong></summary>

```java
Queue<Integer> queue = new LinkedList<>();
Set<Integer> visited = new HashSet<>();
queue.offer(start);
visited.add(start);
while (!queue.isEmpty()) {
    int node = queue.poll();
    for (int neighbor : graph.get(node)) {
        if (!visited.contains(neighbor)) {
            visited.add(neighbor);
            queue.offer(neighbor);
        }
    }
}
```
</details>

<details>
<summary><strong>DFS (Recursive)</strong></summary>

```java
void dfs(int node, Set<Integer> visited, List<List<Integer>> graph) {
    visited.add(node);
    for (int neighbor : graph.get(node)) {
        if (!visited.contains(neighbor)) {
            dfs(neighbor, visited, graph);
        }
    }
}
```
</details>

<details>
<summary><strong>Backtracking (Universal Template)</strong></summary>

```java
void backtrack(int start, List<Integer> current, List<List<Integer>> result) {
    result.add(new ArrayList<>(current));       // record state
    for (int i = start; i < nums.length; i++) {
        current.add(nums[i]);                   // choose
        backtrack(i + 1, current, result);      // explore
        current.remove(current.size() - 1);    // unchoose
    }
}
```
</details>

<details>
<summary><strong>Monotonic Stack (Next Greater Element)</strong></summary>

```java
int[] result = new int[nums.length];
Deque<Integer> stack = new ArrayDeque<>(); // stores indices
for (int i = 0; i < nums.length; i++) {
    while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
        result[stack.pop()] = nums[i];     // found next greater
    }
    stack.push(i);
}
// remaining elements in stack have no next greater → result[i] = 0
```
</details>

---

## 🙋 About Me

**Sahil Kundu** — Associate Software Engineer from Bengaluru, India 🇮🇳

Starting from zero DSA knowledge. Following an 18-month structured plan to crack FAANG.
Every line of code in this repository is part of that journey.

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0A66C2?style=flat-square&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/sahil-kundu-122a7b23a/)
[![LeetCode](https://img.shields.io/badge/LeetCode-FFA116?style=flat-square&logo=leetcode&logoColor=white)](https://leetcode.com/u/18_sahil/)
[![Profile README](https://img.shields.io/badge/GitHub_Profile-181717?style=flat-square&logo=github&logoColor=white)](https://github.com/sahilkundu-dev)

---

<div align="center">

*Last updated: Week 1 · Month 1 · Day 1 of 540*

**"The expert in anything was once a beginner who refused to quit."**

</div>
