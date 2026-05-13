# 📅 WEEK 1 — Complete Day-by-Day Study Plan

**Sahil Kundu's FAANG Preparation · Month 1 · Week 1**

---

## 🎯 Week 1 Overview

**Dates:** [11th May, 2026] to [17th May, 2026]

**Topics This Week:**
- Java fundamentals for DSA (arrays, collections, complexity)
- Arrays & Strings — foundation data structures
- Two Pointers pattern
- Prefix Sum pattern
- Kadane's Algorithm
- Sliding Window (intro)

**Problems Target:** 18 problems
**Daily Commitment:** 6 hours weekdays · 10 hours weekend
**Week Total:** ~152 hours

**Success Criteria:**
By Sunday night, you should be able to:
- Write any array manipulation in Java without syntax hesitation
- Identify Two Pointer and Prefix Sum patterns in under 60 seconds
- Solve any Easy array problem in under 10 minutes
- Explain Big-O for every solution you write

---

## 📖 How to Use This Plan

1. **Complete segments sequentially** — don't skip ahead
2. **Meet the "Done When" criteria** — don't fake completion
3. **Take breaks between segments** — 10-15 min, no screens
4. **Ask me questions immediately** — the moment something is unclear
5. **Write notes in your own words** — parroting doesn't count
6. **End each day by updating PROGRESS.md** — what clicked, what didn't

---

# Day 1 — Monday

**Daily Target:** 6 hours total
**Focus:** Java for DSA + Big-O + First Problem

---

## Segment 1: Java Arrays — Memory, Syntax, Operations

**Estimated Time:** 60–90 minutes

### What You're Learning

Arrays in Java are *contiguous blocks of memory*. This is not trivia — it's why `arr[i]` is O(1) and why inserting in the middle is O(n). You're not just learning syntax; you're learning why the syntax behaves the way it does.

### The Three Forms of Array Declaration

```java
// Form 1: Declaration + initialization separately
int[] nums;                    // nums is null right now
nums = new int[5];             // now nums points to [0,0,0,0,0]

// Form 2: Declaration + initialization together
int[] nums = new int[5];       // one line, same result

// Form 3: Initialize with values
int[] nums = {2, 7, 11, 15};   // length inferred, values set
```

**Key insight:** Form 1 and 2 create arrays filled with **default values** (0 for int, false for boolean, null for objects). Form 3 sets the values explicitly.

### Why `nums.length` Is a Field, Not a Method

In Java, `nums.length` is a **public final field** — not a method like `s.length()` for Strings. This is because arrays are special — they're not full objects with methods; they're primitives with a single exposed property: their size.

```java
int[] arr = {1, 2, 3};
int size = arr.length;        // NO parentheses — it's a field
```

Mess this up once, see the compile error, never forget.

### Array Indexing — Zero-Based, Inclusive Start, Exclusive End

```java
int[] arr = {10, 20, 30, 40, 50};
//           0   1   2   3   4   ← indices

arr[0]    // 10  — first element
arr[4]    // 50  — last element
arr[5]    // ArrayIndexOutOfBoundsException — one past the end
```

Subarray ranges in Java use **inclusive start, exclusive end**: `[start, end)`. This convention makes math cleaner:
- Range `[0, 3)` means indices 0, 1, 2 — length is `3 - 0 = 3`
- Empty range: `[i, i)` has length `i - i = 0`

### Iterating Over Arrays — Three Ways

```java
int[] nums = {2, 7, 11, 15};

// Way 1: Index-based (when you need the index)
for (int i = 0; i < nums.length; i++) {
    System.out.println(i + ": " + nums[i]);
}

// Way 2: Enhanced for-loop (when you only need values)
for (int num : nums) {
    System.out.println(num);
}

// Way 3: While loop (rare, but used in two-pointer problems)
int i = 0;
while (i < nums.length) {
    System.out.println(nums[i]);
    i++;
}
```

**When to use each:**
- Index needed → Way 1
- Just values → Way 2 (cleaner, less error-prone)
- Complex pointer logic → Way 3

### Common Operations — Practice These

**Reverse an array in-place:**

```java
public void reverse(int[] arr) {
    int left = 0, right = arr.length - 1;
    while (left < right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
        left++;
        right--;
    }
}
```

**Find max element:**

```java
public int findMax(int[] arr) {
    if (arr == null || arr.length == 0) {
        throw new IllegalArgumentException("Array is empty");
    }
    
    int max = arr[0];    // seed with first element, NOT 0
    for (int i = 1; i < arr.length; i++) {
        if (arr[i] > max) max = arr[i];
    }
    return max;
}
```

**Why seed with `arr[0]` not 0?** If all elements are negative, max should be the least negative number, not 0.

**Copy an array:**

```java
int[] original = {1, 2, 3};

// Shallow copy (fine for primitives)
int[] copy1 = Arrays.copyOf(original, original.length);

// Manual copy
int[] copy2 = new int[original.length];
for (int i = 0; i < original.length; i++) {
    copy2[i] = original[i];
}
```

### Resources

**YouTube:**
1. [Introduction to Arrays and ArrayList in Java — Kunal Kushwaha (1hr 45min)](https://www.youtube.com/watch?v=n60Dn0UsbEk&list=PL9gnSGHSqcnr_DxHsP7AW9ftq0AtAyYqJ&index=12) — covers how arrays works, the memory management, input/output, multidimensional arrays, dynamic arrays and working with functions.
2. [Mastering Java Arrays: 1D, 2D, and Jagged Arrays Explained — Engineering Digest (35 min)](https://www.youtube.com/watch?v=UPjMnMkwKOQ) — memory model, common operations

**Documentation:**
1. [Oracle Java Arrays Tutorial](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/arrays.html) — official reference
2. [Java Arrays Class — Baeldung](https://www.baeldung.com/java-arrays-guide) — practical guide with examples

### Practice Tasks

Write these methods from scratch in a blank file. No copy-paste. Type them out.

1. `public void reverse(int[] arr)` — reverse in-place
2. `public int findMax(int[] arr)` — return max element
3. `public int findMin(int[] arr)` — return min element
4. `public int[] copyArray(int[] arr)` — return a copy

**Done When:**
- You can write all 4 methods without looking at notes
- You can explain why `arr.length` has no parentheses
- You understand why we seed max/min with `arr[0]` not 0 or Integer.MIN_VALUE

---

## Segment 2: Big-O Notation — The Language of Complexity

**Estimated Time:** 90–120 minutes

### What You're Learning

Big-O is not optional trivia you mention at the end. It's the primary lens through which FAANG evaluates solutions. Every coding round ends with "what's the time and space complexity?" — if you hesitate, you signal uncertainty about your own solution.

### The Core Concept — What Does O(n) Actually Mean?

Big-O describes how an algorithm's runtime **scales** as input size grows. It's not about exact seconds — it's about the **shape of growth**.

```
Input size doubles → runtime...
O(1)       → stays the same
O(log n)   → increases by 1 step
O(n)       → doubles
O(n log n) → doubles × a little more
O(n²)      → quadruples
O(2ⁿ)      → squares
```

**Key insight:** We care about the **worst-case** scenario. In interviews, always analyze worst case unless explicitly told otherwise.

### The Complexity Hierarchy (Fastest to Slowest)

```
O(1) < O(log n) < O(n) < O(n log n) < O(n²) < O(n³) < O(2ⁿ) < O(n!)

Good ────────────────────────────────────────────────────→ Unusable
```

**What "good" means for different input sizes:**

| Input size n | O(n) | O(n log n) | O(n²) | O(2ⁿ) |
|--------------|------|------------|-------|-------|
| 10 | ✅ instant | ✅ instant | ✅ instant | ⚠️ 1024 ops |
| 100 | ✅ instant | ✅ instant | ⚠️ 10K ops | ❌ heat death of universe |
| 1,000 | ✅ instant | ✅ instant | ⚠️ 1M ops | ❌ impossible |
| 10,000 | ✅ fast | ✅ fast | ❌ 100M ops | ❌ impossible |
| 100,000 | ✅ fast | ✅ fast | ❌ timeout | ❌ impossible |
| 1,000,000 | ✅ acceptable | ✅ acceptable | ❌ timeout | ❌ impossible |

**LeetCode time limits:** Most problems timeout at ~10⁸ operations (100 million). Rough rule:
- n ≤ 10: any complexity works
- n ≤ 100: O(n³) is the limit
- n ≤ 1,000: O(n²) is the limit
- n ≤ 10,000: O(n log n) required
- n ≤ 1,000,000: O(n) or O(log n) required

### How to Analyze Time Complexity — The 4-Step Method

**Step 1: Identify the input size variable**
Usually `n` = array length, string length, number of nodes, etc.

**Step 2: Count operations inside loops**

```java
for (int i = 0; i < n; i++) {
    // single operation: O(1)
}
// Loop runs n times → total: O(n)
```

**Step 3: Multiply nested loops**

```java
for (int i = 0; i < n; i++) {           // n times
    for (int j = 0; j < n; j++) {       // n times for each i
        // O(1) operation
    }
}
// Total: O(n × n) = O(n²)
```

**Step 4: Add sequential sections, take the largest**

```java
for (int i = 0; i < n; i++) { }         // O(n)
for (int i = 0; i < n; i++) { }         // O(n)
for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) { }     // O(n²)
}
// Total: O(n) + O(n) + O(n²) → O(n²)  (largest term dominates)
```

### Common Patterns — Recognize These Instantly

**O(1) — Constant**
```java
int x = arr[5];           // array access
int y = x + 2;            // arithmetic
if (x > 10) { }           // comparison
map.get(key);             // HashMap get (average case)
```

**O(log n) — Logarithmic**
```java
// Binary search — halves the search space each time
int left = 0, right = n - 1;
while (left <= right) {
    int mid = left + (right - left) / 2;
    if (arr[mid] == target) return mid;
    else if (arr[mid] < target) left = mid + 1;
    else right = mid - 1;
}
// Each iteration cuts n in half → log₂(n) iterations
```

**O(n) — Linear**
```java
// Single pass through array
int sum = 0;
for (int num : arr) {
    sum += num;
}
```

**O(n log n) — Linearithmic**
```java
Arrays.sort(arr);         // Java's sort — dual-pivot quicksort
// Merge sort, heap sort — all O(n log n)
```

**O(n²) — Quadratic**
```java
// Nested loop — check every pair
for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
        if (arr[i] + arr[j] == target) { }
    }
}
```

### Dropping Constants and Lower-Order Terms

```java
// This code:
for (int i = 0; i < n; i++) { }       // n ops
for (int i = 0; i < n; i++) { }       // n ops
for (int i = 0; i < n; i++) { }       // n ops

// Is technically 3n operations
// But we write it as O(n), not O(3n)
// Why? Constants don't change the growth rate shape
```

**Rule:** Drop constants, keep only the highest-order term.

```
O(3n² + 5n + 10)  →  O(n²)
O(2n log n + n)   →  O(n log n)
O(n/2 + 100)      →  O(n)
```

### Space Complexity — Same Rules, Different Question

Time = "how many operations?"
Space = "how much extra memory?"

**Don't count:**
- Input array (given to you)
- Output array (required by the problem)

**Do count:**
- Any extra arrays you create
- HashMap/HashSet storage
- Recursion call stack depth

```java
// Time: O(n), Space: O(n)
public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();  // O(n) space
    for (int i = 0; i < nums.length; i++) {       // O(n) time
        int complement = target - nums[i];
        if (map.containsKey(complement)) {
            return new int[]{map.get(complement), i};
        }
        map.put(nums[i], i);
    }
    return new int[]{};
}
```

### Resources

**YouTube:**
1. [Big-O Notation — HackerRank (9 min)](https://www.youtube.com/watch?v=v4cd1O4zkGw) — visual, beginner-friendly
2. [Time Complexity Analysis — Abdul Bari (Playlist)](https://www.youtube.com/playlist?list=PLepRVAscJb6hLEJu77Y9jon8e8S4iUYAg) — detailed with examples
3. [Big-O Cheat Sheet — NeetCode (20 min)](https://www.youtube.com/watch?v=BgLTDT03QtU) — practical pattern recognition

**Documentation:**
1. [Big-O Cheat Sheet](https://www.bigocheatsheet.com/) — visual reference for common algorithms
2. [Time Complexity — Interview Cake](https://www.interviewcake.com/article/java/big-o-notation-time-and-space-complexity) — excellent written explanation

### Practice Tasks

Analyze the time and space complexity of these code snippets. Write your answer BEFORE checking.

```java
// 1.
int sum = 0;
for (int i = 0; i < n; i++) {
    sum += arr[i];
}

// 2.
for (int i = 0; i < n; i++) {
    for (int j = i + 1; j < n; j++) {
        if (arr[i] + arr[j] == target) { }
    }
}

// 3.
Arrays.sort(arr);
for (int num : arr) {
    System.out.println(num);
}

// 4.
Map<Integer, Integer> map = new HashMap<>();
for (int num : arr) {
    map.put(num, map.getOrDefault(num, 0) + 1);
}

// 5.
int left = 0, right = n - 1;
while (left < right) {
    int mid = left + (right - left) / 2;
    if (arr[mid] == target) return mid;
    else if (arr[mid] < target) left = mid + 1;
    else right = mid - 1;
}
```

**Answers:**
1. Time O(n), Space O(1)
2. Time O(n²), Space O(1)
3. Time O(n log n), Space O(1) — sort dominates
4. Time O(n), Space O(n) — HashMap stores n entries
5. Time O(log n), Space O(1) — binary search

**Done When:**
- You can analyze any loop structure and state its complexity
- You can explain why O(3n) becomes O(n)
- You understand the difference between time and space complexity
- You got all 5 practice tasks correct with justification

---

## Segment 3: Java Collections — ArrayList, HashMap, HashSet

**Estimated Time:** 75–90 minutes

### What You're Learning

Arrays are fixed-size. Collections are dynamic. In DSA problems, you need both — arrays for performance, collections for flexibility. These three classes appear in 80% of all solutions: ArrayList, HashMap, HashSet.

### ArrayList — Dynamic Array

**What it is:** Resizable array. Automatically grows when full.

**When to use:** You need array-like access (`get(i)` is O(1)) but don't know the size upfront.

```java
import java.util.ArrayList;
import java.util.List;

List<Integer> list = new ArrayList<>();

// Add elements — O(1) amortized
list.add(10);
list.add(20);
list.add(30);

// Access by index — O(1)
int val = list.get(1);        // 20

// Size — O(1)
int size = list.size();       // 3

// Check if contains — O(n) — linear search
boolean has = list.contains(20);  // true

// Remove by index — O(n) — must shift elements
list.remove(1);               // removes 20, list is now [10, 30]

// Iterate
for (int num : list) {
    System.out.println(num);
}
```

**Key complexity:**
- `get(i)`, `set(i, val)`, `add()` → O(1)
- `contains()`, `remove(val)` → O(n)

**Common mistake:** Using `list.contains()` in a loop → O(n²). Use HashSet instead.

### HashMap — Key-Value Store

**What it is:** Hash table. Maps keys to values. O(1) average lookup.

**When to use:** You need to look up values by key fast. "Have I seen this number before?" "How many times does this character appear?"

```java
import java.util.HashMap;
import java.util.Map;

Map<String, Integer> map = new HashMap<>();

// Put key-value — O(1) average
map.put("apple", 5);
map.put("banana", 3);

// Get value by key — O(1) average
int count = map.get("apple");          // 5

// Get with default if absent — O(1) average
int val = map.getOrDefault("orange", 0);  // 0

// Check if key exists — O(1) average
boolean has = map.containsKey("apple");   // true

// Update value safely
map.put("apple", map.getOrDefault("apple", 0) + 1);  // increment

// Better way to increment — O(1)
map.merge("apple", 1, Integer::sum);     // map["apple"]++

// Iterate over entries
for (Map.Entry<String, Integer> entry : map.entrySet()) {
    System.out.println(entry.getKey() + ": " + entry.getValue());
}
```

**Key complexity:**
- `put()`, `get()`, `containsKey()`, `remove()` → O(1) average
- Worst case (hash collision) → O(n), but rare with good hash function

**When HashMap beats array:** Counting frequency of elements.

```java
// Count frequency using HashMap — O(n) time, O(k) space where k = distinct elements
Map<Integer, Integer> freq = new HashMap<>();
for (int num : arr) {
    freq.merge(num, 1, Integer::sum);
}
```

### HashSet — Unique Elements Only

**What it is:** Hash table of keys only (no values). Stores unique elements.

**When to use:** "Remove duplicates", "have I seen this before?", "does this exist?"

```java
import java.util.HashSet;
import java.util.Set;

Set<Integer> set = new HashSet<>();

// Add element — O(1) average
set.add(10);
set.add(20);
set.add(10);        // duplicate — ignored

// Size — O(1)
int size = set.size();      // 2 (only 10 and 20)

// Check if contains — O(1) average
boolean has = set.contains(10);  // true

// Remove — O(1) average
set.remove(10);

// Iterate
for (int num : set) {
    System.out.println(num);  // order not guaranteed
}
```

**Key complexity:**
- `add()`, `contains()`, `remove()` → O(1) average

**When HashSet beats array:** Detecting duplicates in O(n).

```java
// Detect if array has duplicates — O(n) time, O(n) space
public boolean hasDuplicate(int[] arr) {
    Set<Integer> seen = new HashSet<>();
    for (int num : arr) {
        if (seen.contains(num)) return true;  // O(1) check
        seen.add(num);
    }
    return false;
}
```

### Quick Comparison Table

| Operation | ArrayList | HashMap | HashSet |
|-----------|-----------|---------|---------|
| Access by index | O(1) | ❌ no index | ❌ no index |
| Search for value | O(n) | O(1) by key | O(1) |
| Add | O(1) amortized | O(1) | O(1) |
| Remove | O(n) | O(1) | O(1) |
| Duplicates? | ✅ allowed | Keys unique, values can duplicate | ❌ unique only |
| Ordered? | ✅ insertion order | ❌ no order | ❌ no order |

### Resources

**YouTube:**
1. [ArrayList vs Array — Coding with John (17 min)](https://www.youtube.com/watch?v=NbYgm0r7u6o)
2. [Introduction to HashMap & HashTable in Java — Kunal Kushwaha (1hr 40 min)](https://www.youtube.com/watch?v=XLbvmMz8Fr8)
3. [Set and HashSet in Java — Coding with John (21 min)](https://www.youtube.com/watch?v=QvHBHuuddYk)

**Documentation:**
1. [Java Collections — Oracle Tutorial](https://docs.oracle.com/javase/tutorial/collections/index.html)
2. [HashMap Guide — Baeldung](https://www.baeldung.com/java-hashmap)

### Practice Tasks

Write these methods:

```java
// 1. Count character frequency in a string using HashMap
public Map<Character, Integer> charFrequency(String s) {
    // Your code
}
// Answer : 
public Map<Character, Integer> charFrequency(String s) {
    Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
    return freq;
}
// Time Complexity: O(n)
// Space Complexity: O(k) -> where 'k' is the no. of unique elements

// 2. Check if array has duplicates using HashSet
public boolean hasDuplicates(int[] arr) {
    // Your code
}
// Answer :
public boolean hasDuplicates(int[] arr) {
    Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
    return false;
}
// Time Complexity: O(n)
// Space Complexity: O(n)

// 3. Find first unique character in string using HashMap
public char firstUnique(String s) {
    // Your code — return '\0' if none
}
// Answer : 
public char firstUnique(String s) {     
    Map<Character, Integer> found = new HashMap<>();
        for (char c : s.toCharArray()) {
            found.put(c, found.getOrDefault(c, 0) + 1);
        }
        for (char c : s.toCharArray()) {
            if (found.get(c) == 1) {
                return c;
            }
        }
    return '\0';
} 
// Time Complexity: O(n)
// Space Complexity: O(n)
```

**Done When:**
- You can write all 3 methods without looking
- You can explain when to use ArrayList vs HashMap vs HashSet
- You understand why `map.get()` is O(1) but `list.contains()` is O(n)

---

## Segment 4: First DSA Problem — Two Sum (Complete Walkthrough)

**Estimated Time:** 90–120 minutes

### The Problem

**Two Sum** — LeetCode #1
https://leetcode.com/problems/two-sum/

> Given an array of integers `nums` and an integer `target`, return indices of the two numbers such that they add up to `target`.
> 
> You may assume that each input would have exactly one solution, and you may not use the same element twice.

**Example:**
```
Input:  nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: nums[0] + nums[1] = 2 + 7 = 9
```

### The 6-Phase Framework (Learn This Now — Use It Forever)

Every FAANG coding problem follows this exact structure:

1. **Understand** — restate the problem in your own words
2. **Clarify** — ask questions about edge cases and constraints
3. **Brute Force** — state the obvious O(n²) approach
4. **Optimize** — think through how to improve it
5. **Code** — write the optimal solution
6. **Test** — trace through examples and edge cases

**You will use this framework on every single problem for the next 18 months.**

---

### Phase 1: Understand

Restate in your own words:

> "I need to find two numbers in the array that add up to the target. I return their indices, not the values themselves. The problem guarantees exactly one solution exists."

---

### Phase 2: Clarify

Ask these questions in a real interview:

**Q:** Can the array be empty?
**A:** Problem guarantees a solution exists, so minimum 2 elements.

**Q:** Can the same index be used twice?
**A:** No — "you may not use the same element twice."

**Q:** Can the array contain negative numbers?
**A:** Yes, no constraints on values.

**Q:** Is the array sorted?
**A:** No, assume unsorted.

**Q:** Can there be duplicate values in the array?
**A:** Yes, but doesn't matter since we return indices.

---

### Phase 3: Brute Force

**Approach:** Check every possible pair.

```java
public int[] twoSumBrute(int[] nums, int target) {
    // Check every pair (i, j) where i < j
    for (int i = 0; i < nums.length; i++) {
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[i] + nums[j] == target) {
                return new int[]{i, j};
            }
        }
    }
    return new int[]{};  // no solution (unreachable per problem guarantee)
}
```

**Time Complexity:** O(n²) — nested loops
**Space Complexity:** O(1) — no extra storage

**Why this works:** We literally check every combination. If a solution exists, we'll find it.

**Why this is bad:** For n=10,000, we do 50 million comparisons. LeetCode will timeout.

---

### Phase 4: Optimize

**Key insight:** The inner loop is doing a *linear search* for the complement `target - nums[i]`. Can we make that search faster?

**Yes — use a HashMap.**

For each number `nums[i]`, the complement is `target - nums[i]`. Instead of searching the entire array for the complement, we store every number we've seen so far in a HashMap. Then checking "have I seen this complement?" becomes O(1).

**Algorithm:**
1. Create empty HashMap (number → index)
2. For each number at index `i`:
   - Calculate `complement = target - nums[i]`
   - Check if `complement` exists in HashMap
   - If yes → we found the pair → return `[map.get(complement), i]`
   - If no → add `nums[i]` to HashMap with value `i`

**Why we add to map AFTER checking:** If we add first, we might match the same element with itself.

---

### Phase 5: Code

```java
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    
    public int[] twoSum(int[] nums, int target) {
        // Edge case guard
        if (nums == null || nums.length < 2) {
            return new int[]{};
        }
        
        // value → index map for O(1) complement lookup
        Map<Integer, Integer> seen = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            
            // Check if complement was seen before
            if (seen.containsKey(complement)) {
                return new int[]{seen.get(complement), i};
            }
            
            // Add current number to map AFTER checking
            // This prevents using the same element twice
            seen.put(nums[i], i);
        }
        
        return new int[]{};  // unreachable per problem guarantee
    }
}
```

**Time Complexity:** O(n) — single pass through array, HashMap operations are O(1) average
**Space Complexity:** O(n) — HashMap stores at most n entries

---

### Phase 6: Test

**Test Case 1:** Normal case
```
nums = [2, 7, 11, 15], target = 9

i=0: nums[0]=2, complement=9-2=7
     seen.containsKey(7)? No
     seen = {2: 0}

i=1: nums[1]=7, complement=9-7=2
     seen.containsKey(2)? YES → return [0, 1] ✓
```

**Test Case 2:** Solution at the end
```
nums = [3, 2, 4], target = 6

i=0: complement=3, seen={}, add 3
i=1: complement=4, seen={3:0}, add 2
i=2: complement=2, seen={3:0, 2:1}, found! return [1, 2] ✓
```

**Test Case 3:** Negative numbers
```
nums = [-1, -2, -3, -4], target = -6

i=0: complement=-5, add -1
i=1: complement=-4, add -2
i=2: complement=-3, add -3
i=3: complement=-2, seen contains -2 at index 1, return [1, 3] ✓
```

**Edge Case:** Same value appears twice
```
nums = [3, 3], target = 6

i=0: complement=3, seen={}, add 3→0
i=1: complement=3, seen contains 3 at index 0, return [0, 1] ✓
```

**Why adding after checking matters:** If we put `nums[i]` into the map BEFORE checking, in the edge case above, at i=0 we'd check if 3 exists (yes, we just added it) and return [0, 0] — wrong, using same element twice.

---

### Write Your Solution File

Create `TwoSum.java` in your DSA repo using this exact format:

```java
/**
 * ╔══════════════════════════════════════════════════════════════╗
 * ║  Problem   : Two Sum                                        ║
 * ║  Link      : https://leetcode.com/problems/two-sum/         ║
 * ║  Difficulty: Easy                                           ║
 * ║  Pattern   : HashMap / Complement Lookup                    ║
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
 *   Output: [0,1]
 *
 *   Input:  nums = [3,2,4], target = 6
 *   Output: [1,2]
 *
 * ── APPROACH 1: BRUTE FORCE ─────────────────────────────────────
 * Check every possible pair using nested loops.
 * For each i, check all j where j > i.
 *
 *   Time  : O(n²)  — nested loops
 *   Space : O(1)   — no extra storage
 *
 * ── APPROACH 2: OPTIMAL (HashMap) ───────────────────────────────
 * For each number, compute complement = target - num.
 * Check if complement exists in HashMap (O(1) lookup).
 * If yes → found our pair.
 * If no → store current number.
 *
 * Key insight: replace O(n) inner loop search with O(1) HashMap lookup.
 *
 *   Time  : O(n)   — single pass
 *   Space : O(n)   — HashMap stores at most n entries
 *
 * EDGE CASES HANDLED:
 *   ✓ null or length < 2   → returns empty array
 *   ✓ negative numbers      → HashMap handles naturally
 *   ✓ same value twice      → checking before inserting prevents self-use
 */

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    // ── OPTIMAL SOLUTION ────────────────────────────────────────
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) return new int[]{};

        Map<Integer, Integer> seen = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (seen.containsKey(complement)) {
                return new int[]{seen.get(complement), i};
            }

            seen.put(nums[i], i);
        }

        return new int[]{};
    }

    // ── BRUTE FORCE (reference only) ─────────────────────────────
    public int[] twoSumBrute(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }
}
```

**Commit this file to your repo:**

```bash
git add 01-arrays-and-strings/two-pointers/TwoSum.java
git commit -m "Add: TwoSum - HashMap O(n) solution [Easy]"
git push
```

---

### Resources

**YouTube:**
1. [Two Sum — NeetCode (8 min)](https://www.youtube.com/watch?v=KLlXCFG5TnA) — excellent visual walkthrough
2. [2 Sum Problem — take U Forward (18 min)](https://www.youtube.com/watch?v=UXDSeD9mN-k&t=953s) — More in-depth, conceptual

**Documentation:**
1. [LeetCode Two Sum Solution Article](https://leetcode.com/problems/two-sum/solution/) — official explanations

**Done When:**
- You can solve Two Sum from scratch in under 15 minutes
- You can explain both brute force and optimal approaches
- You can trace through the HashMap approach with any example
- You've written and committed the solution file
- You understand why we check BEFORE adding to the map

---

## End of Day 1

**Before you close your laptop:**

1. **Update PROGRESS.md** — fill in the Week 1 Day 1 entry
2. **Write in ERROR_JOURNAL.md** — if anything tripped you up today, log it
3. **Tomorrow's prep** — skim the topics for Day 2 (just titles, don't study yet)

**Self-assessment questions** (answer honestly):

- Can I write Java array operations without looking up syntax? (reverse, findMax)
- Can I analyze any loop and state its Big-O complexity?
- Can I explain the difference between ArrayList, HashMap, and HashSet?
- Can I solve Two Sum from scratch in under 15 minutes?

If any answer is "no" — that segment needs more time tomorrow morning before moving forward.

**You've completed Day 1. Rest well. Tomorrow we go deeper.**

---

# Day 2 — Tuesday

**Daily Target:** 6 hours total
**Focus:** Two Pointers Pattern Deep Dive + 3 Problems

---

## Segment 1: Warm-up — Re-solve Two Sum Cold

**Estimated Time:** 15–20 minutes

### What You're Doing

Open a blank file. Solve Two Sum entirely from memory. No looking at yesterday's notes. No looking at LeetCode. Just you and the problem.

**Why this matters:** This is spaced repetition Day 1. If you can't solve it cold today, you didn't actually learn it yesterday — you just followed along. The inability to reproduce signals where your understanding is still shaky.

**Time limit:** 15 minutes. If you can't finish in 15 minutes, you need to re-study Two Sum before moving forward today.

**Done When:**
- Complete solution written from memory in under 15 minutes
- Code compiles with no syntax errors
- You can explain the approach without notes

---

## Segment 2: Two Pointers Pattern — Concept from First Principles

**Estimated Time:** 75–90 minutes

### What You're Learning

Two Pointers is one of the top 5 most frequent patterns in FAANG coding rounds. Master it now, recognize it forever. It appears in problems that don't look like "two pointer problems" at first — the skill is pattern recognition.

### The Core Intuition — Why Two Pointers Works

Imagine you're standing in a sorted line of people by height. You need to find two people whose combined height equals exactly 180cm.

**Brute force approach:**
Pick person 1, check them against everyone else (n-1 comparisons). Pick person 2, check against everyone after (n-2 comparisons). Total: O(n²).

**Two pointer approach:**
- Start with shortest person (left) and tallest person (right)
- If their sum < 180cm → the left person is too short for *anyone* to their right, move left++
- If their sum > 180cm → the right person is too tall for *anyone* to their left, move right--
- If sum = 180cm → found it

Total comparisons: at most n (each pointer moves at most n positions total). O(n).

**The key insight:** When the array is sorted, comparing the extremes (smallest + largest) gives you information that lets you safely eliminate entire regions of the search space.

### When Two Pointers Works — The Proof

Two Pointers requires a **monotonic property**: moving one pointer changes the sum in a predictable direction.

In a sorted array:
- Moving `left` right → increases the sum (we're picking a larger number)
- Moving `right` left → decreases the sum (we're picking a smaller number)

This monotonicity is what makes skipping safe. If `nums[left] + nums[right] > target`, we know that `nums[left]` paired with *anyone* to the right of `right` will also be > target (because all those people are taller/larger). So we can eliminate the entire right side for this `left` value.

**Without sorting, there's no monotonic property — two pointers doesn't work.**

### The Two Forms — Converging vs Same-Direction

**Form 1: Converging Pointers (opposite ends)**

Start at opposite ends, move toward each other based on a condition.

```
[1, 2, 3, 4, 5, 6, 7, 8, 9]
 ↑                       ↑
left                   right

Condition determines which pointer moves.
Pointers meet in the middle.
```

**Use when:**
- Array is sorted (or sortable)
- Looking for pairs/triplets with a target property
- Palindrome checking
- Container/trap/area problems with boundaries

**Form 2: Same-Direction Pointers (fast & slow)**

Both start at the beginning, move at different speeds or conditions.

```
[0, 0, 1, 1, 1, 2, 2, 3, 3, 4]
 ↑
slow, fast

slow: last confirmed unique position
fast: scanner that finds next unique
```

**Use when:**
- Removing duplicates in-place
- Partitioning array (Dutch National Flag)
- Cycle detection (Floyd's algorithm — in LinkedList week)

### The Universal Template — Converging Form

```java
public void twoPointerConverging(int[] nums, int target) {
    // Step 1: Sort if not already sorted
    // Arrays.sort(nums);  // O(n log n) — only if needed
    
    int left = 0;
    int right = nums.length - 1;
    
    // Step 2: Move pointers based on condition
    while (left < right) {                    // STRICT <, not <=
        
        int sum = nums[left] + nums[right];   // or whatever computation
        
        if (sum == target) {
            // Found what we're looking for
            // Record result, then decide: continue or return
            return;
        } 
        else if (sum < target) {
            // Current sum too small
            // Need to increase → move left pointer right
            left++;
        } 
        else {
            // Current sum too large
            // Need to decrease → move right pointer left
            right--;
        }
    }
}
```

**Critical detail:** Use `left < right`, NOT `left <= right`. When pointers meet (`left == right`), you're looking at a single element, which can't form a pair with itself.

### Common Mistakes — Avoid These

**Mistake 1: Using `left <= right`**
```java
while (left <= right) {  // WRONG for pairs
    // When left == right, you're checking element against itself
}
```

**Fix:** Use strict `<` for pair-finding problems.

**Mistake 2: Applying to unsorted arrays without sorting first**
```java
int[] nums = {3, 1, 4, 1, 5};  // unsorted
// two pointer here will give wrong results
```

**Fix:** Always sort first if the problem doesn't guarantee sorted input.

**Mistake 3: Not handling duplicates in 3Sum-like problems**
```java
// After finding a valid triplet, failing to skip duplicates
// Results in duplicate triplets in final answer
```

**Fix:** After recording result, skip all equal values on both sides.

### Example Problem Walkthrough — Valid Palindrome

**Problem:** Given a string, determine if it's a palindrome, considering only alphanumeric characters and ignoring case.

```
Input:  "A man, a plan, a canal: Panama"
Output: true

Input:  "race a car"
Output: false
```

**Approach:**
1. Two pointers at start and end
2. Skip non-alphanumeric characters
3. Compare characters (case-insensitive)
4. Move pointers inward if match, return false if mismatch

**Code:**

```java
public boolean isPalindrome(String s) {
    if (s == null || s.isEmpty()) return true;
    
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
        
        // Compare characters (case-insensitive)
        if (Character.toLowerCase(s.charAt(left)) != 
            Character.toLowerCase(s.charAt(right))) {
            return false;
        }
        
        left++;
        right--;
    }
    
    return true;
}
```

**Time Complexity:** O(n) — each character visited at most twice (once by each pointer)
**Space Complexity:** O(1) — only two pointer variables

**Trace through example:**

```
"A man, a plan, a canal: Panama"
 ↑                             ↑
 left                        right

Iteration 1:
  left='A', right='a' → both alphanumeric → compare 'a'=='a' ✓ → move both

Iteration 2:
  left=' ' → skip
  left='m', right='m' → compare ✓ → move both

... continues until pointers meet

All characters matched → return true
```

### Resources

**YouTube:**
1. [Two Pointers Technique — NeetCode (15 min)](https://www.youtube.com/watch?v=On03HWe2tZM) — pattern recognition
2. [Two Pointers Explained — Back To Back SWE (21 min)](https://www.youtube.com/watch?v=kW_A91xfb3M) — deep dive with examples
3. [Two Sum II — Two Pointers — NeetCode (7 min)](https://www.youtube.com/watch?v=cQ1Oz4ckceM) — sorted array variant

**Documentation:**
1. [Two Pointers Pattern Guide — LeetCode Explore](https://leetcode.com/explore/learn/card/array-and-string/205/array-two-pointer-technique/) — interactive
2. [Two Pointers — AlgoExpert](https://www.algoexpert.io/questions/two-number-sum) — conceptual explanation

### Practice — Pattern Recognition Drill

For each problem statement, answer: "Would two pointers work? Why or why not?"

1. **"Find two numbers in a sorted array that sum to target."**
   - Answer: ?

2. **"Find two numbers in an unsorted array that sum to target."**
   - Answer: ?

3. **"Check if a string is a palindrome."**
   - Answer: ?

4. **"Find the maximum sum of any subarray of size k."**
   - Answer: ?

5. **"Remove duplicates from a sorted array in-place."**
   - Answer: ?

**Answers:**
1. Yes — sorted array, looking for pair, converging pointers work perfectly
2. No (not directly) — unsorted means no monotonic property. Need HashMap (Two Sum approach) or sort first then use two pointers
3. Yes — compare characters from both ends moving inward
4. No — this is Sliding Window (fixed size), not Two Pointers
5. Yes — same-direction pointers (slow = last unique, fast = scanner)

**Done When:**
- You can explain why Two Pointers requires sorted input (or sortable problem)
- You can write the converging two pointer template from memory
- You understand the difference between `left < right` and `left <= right`
- You got all 5 pattern recognition questions correct

---

## Segment 3: Problem — Valid Palindrome

**Estimated Time:** 30–40 minutes

### The Problem

**Valid Palindrome** — LeetCode #125
https://leetcode.com/problems/valid-palindrome/

> A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward.
>
> Given a string `s`, return `true` if it is a palindrome, or `false` otherwise.

**Examples:**
```
Input:  s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome

Input:  s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome

Input:  s = " "
Output: true
Explanation: Empty string after removing non-alphanumeric is palindrome
```

### Apply the 6-Phase Framework

**Phase 1: Understand**

> "I need to check if the string reads the same forwards and backwards, but I only consider letters and digits — I ignore spaces, punctuation, and case doesn't matter."

**Phase 2: Clarify**

- What counts as alphanumeric? Letters (a-z, A-Z) and digits (0-9)
- Empty string after filtering? → True (empty is a palindrome)
- String with only non-alphanumeric characters? → True

**Phase 3: Brute Force**

Filter the string to only alphanumeric, convert to lowercase, then check if it equals its reverse.

```java
public boolean isPalindromeBrute(String s) {
    StringBuilder filtered = new StringBuilder();
    for (char c : s.toCharArray()) {
        if (Character.isLetterOrDigit(c)) {
            filtered.append(Character.toLowerCase(c));
        }
    }
    String clean = filtered.toString();
    String reversed = filtered.reverse().toString();
    return clean.equals(reversed);
}
```

**Time:** O(n) — single pass to filter, single pass to reverse
**Space:** O(n) — StringBuilder stores filtered string

**Phase 4: Optimize**

Can we avoid building the filtered string?

Yes — use two pointers. Move both inward, skipping non-alphanumeric characters, comparing as we go. If any character pair mismatches → not a palindrome.

**Phase 5: Code**

```java
public boolean isPalindrome(String s) {
    if (s == null) return true;
    
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
        char leftChar = Character.toLowerCase(s.charAt(left));
        char rightChar = Character.toLowerCase(s.charAt(right));
        
        if (leftChar != rightChar) {
            return false;
        }
        
        left++;
        right--;
    }
    
    return true;
}
```

**Time:** O(n) — each character visited at most twice
**Space:** O(1) — only two pointer variables

**Phase 6: Test**

Test Case 1:
```
"A man, a plan, a canal: Panama"
After skipping/lowercasing: amanaplanacanalpanama
Palindrome ✓
```

Test Case 2:
```
"race a car"
After skipping/lowercasing: raceacar
'r' != 'r' (last), 'a' != 'a', ... 'e' != 'c' → false ✓
```

Test Case 3: Edge case
```
".,!@#"
All non-alphanumeric → skipped → empty → true ✓
```

### Write Your Solution File

Create `ValidPalindrome.java`:

```java
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

public class ValidPalindrome {

    public boolean isPalindrome(String s) {
        if (s == null) return true;
        
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
            
            // Compare characters (case-insensitive)
            if (Character.toLowerCase(s.charAt(left)) != 
                Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            
            left++;
            right--;
        }
        
        return true;
    }
}
```

**Resources:**
- [Valid Palindrome — NeetCode (6 min)](https://www.youtube.com/watch?v=jJXJ16kPFWg)

**Done When:**
- Solved in under 20 minutes from scratch
- Can explain why we check `left < right` in the inner while loops
- Solution file written and committed

---

## Segment 4: Problem — Two Sum II (Sorted Array Variant)

**Estimated Time:** 35–45 minutes

### The Problem

**Two Sum II - Input Array Is Sorted** — LeetCode #167
https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/

> Given a **1-indexed** array of integers `numbers` that is **already sorted in non-decreasing order**, find two numbers such that they add up to a specific `target` number.
>
> Return the indices of the two numbers (1-indexed) as an integer array `[index1, index2]` where `1 <= index1 < index2 <= numbers.length`.

**Examples:**
```
Input:  numbers = [2,7,11,15], target = 9
Output: [1,2]
Explanation: 2 + 7 = 9. Return indices 1 and 2 (1-indexed).

Input:  numbers = [2,3,4], target = 6
Output: [1,3]

Input:  numbers = [-1,0], target = -1
Output: [1,2]
```

### Key Difference from Two Sum #1

1. **Array is sorted** — this changes everything
2. **1-indexed output** — return `[i+1, j+1]` not `[i, j]`
3. **Guaranteed solution** — exactly one solution exists

### Apply the Framework

**Phase 1: Understand**

> "Find two numbers in a sorted array that sum to target. Return their 1-indexed positions."

**Phase 2: Clarify**

- Sorted in ascending order? Yes
- Can use same element twice? No
- Guaranteed solution? Yes
- Negative numbers? Yes

**Phase 3: Brute Force**

Could use HashMap like Two Sum #1 — but we're not exploiting the sorted property.

**Phase 4: Optimize**

Since array is sorted, use two pointers:
- Start: left=0, right=n-1
- If sum < target → increase sum → move left right
- If sum > target → decrease sum → move right left
- If sum = target → found

**Why this works:** Sorted array gives us monotonic property. Moving left pointer right always increases the sum. Moving right pointer left always decreases the sum.

**Phase 5: Code**

```java
public int[] twoSum(int[] numbers, int target) {
    int left = 0;
    int right = numbers.length - 1;
    
    while (left < right) {
        int sum = numbers[left] + numbers[right];
        
        if (sum == target) {
            return new int[]{left + 1, right + 1};  // 1-indexed
        } else if (sum < target) {
            left++;     // need bigger sum
        } else {
            right--;    // need smaller sum
        }
    }
    
    return new int[]{};  // unreachable per problem guarantee
}
```

**Time:** O(n) — each pointer moves at most n times total
**Space:** O(1) — only two variables

**Phase 6: Test**

```
numbers = [2, 7, 11, 15], target = 9

left=0, right=3: sum=2+15=17 > 9 → right--
left=0, right=2: sum=2+11=13 > 9 → right--
left=0, right=1: sum=2+7=9 = 9 → return [1, 2] ✓
```

### Write Solution File

Create `TwoSumII.java` with full template header.

**Resources:**
- [Two Sum II — NeetCode (7 min)](https://www.youtube.com/watch?v=cQ1Oz4ckceM)

**Done When:**
- Solved in under 20 minutes
- Can explain why two pointers works here but HashMap is better for unsorted
- Remember to return 1-indexed positions

---

## Segment 5: Problem — Best Time to Buy and Sell Stock

**Estimated Time:** 40–50 minutes

### The Problem

**Best Time to Buy and Sell Stock** — LeetCode #121
https://leetcode.com/problems/best-time-to-buy-and-sell-stock/

> You are given an array `prices` where `prices[i]` is the price of a given stock on the `i`-th day.
>
> You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
>
> Return the maximum profit you can achieve from this transaction. If no profit, return 0.

**Examples:**
```
Input:  prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price=1), sell on day 5 (price=6), profit = 6-1 = 5

Input:  prices = [7,6,4,3,1]
Output: 0
Explanation: No profit possible (prices only decrease)
```

### Apply the Framework

**Phase 1: Understand**

> "Find the maximum difference between two prices where the buy happens before the sell."

**Phase 2: Clarify**

- Must buy before selling? Yes
- Can buy and sell on same day? Yes, but profit would be 0
- Return 0 if no profit possible? Yes

**Phase 3: Brute Force**

Check every pair (i, j) where i < j. Track max profit.

```java
public int maxProfitBrute(int[] prices) {
    int maxProfit = 0;
    for (int i = 0; i < prices.length; i++) {
        for (int j = i + 1; j < prices.length; j++) {
            int profit = prices[j] - prices[i];
            maxProfit = Math.max(maxProfit, profit);
        }
    }
    return maxProfit;
}
```

**Time:** O(n²)
**Space:** O(1)

**Phase 4: Optimize**

Key insight: For each sell day, we want to know the minimum price seen so far (best buy day before this).

Track minimum price as we scan left to right. At each day, calculate profit if we sell today (current price - min so far). Track max profit.

**Phase 5: Code**

```java
public int maxProfit(int[] prices) {
    if (prices == null || prices.length < 2) return 0;
    
    int minPrice = prices[0];
    int maxProfit = 0;
    
    for (int i = 1; i < prices.length; i++) {
        // Calculate profit if we sell today
        int profit = prices[i] - minPrice;
        maxProfit = Math.max(maxProfit, profit);
        
        // Update minimum price seen so far
        minPrice = Math.min(minPrice, prices[i]);
    }
    
    return maxProfit;
}
```

**Time:** O(n) — single pass
**Space:** O(1)

**Phase 6: Test**

```
prices = [7, 1, 5, 3, 6, 4]

i=1: price=1, profit=1-7=-6, max=0, min=1
i=2: price=5, profit=5-1=4, max=4, min=1
i=3: price=3, profit=3-1=2, max=4, min=1
i=4: price=6, profit=6-1=5, max=5, min=1
i=5: price=4, profit=4-1=3, max=5, min=1

return 5 ✓
```

### Note: This is NOT Two Pointers

This problem looks like it could use two pointers, but it's actually a **greedy** single-pass algorithm. We're not moving two pointers — we're tracking one value (min price) and computing profit at each step.

**Important distinction:** Two Pointers requires comparing/combining values at two positions. This problem only needs to track one historical value (minimum).

### Write Solution File

Create `BestTimeToBuyAndSellStock.java`.

**Resources:**
- [Best Time to Buy Sell Stock — NeetCode (9 min)](https://www.youtube.com/watch?v=1pkOgXD63yU)

**Done When:**
- Solved in under 20 minutes
- Understand it's greedy, not two pointers
- Can explain why we update minPrice after calculating profit

---

## Segment 6: Review + Update Progress

**Estimated Time:** 30 minutes

### Today's Review Checklist

1. **Re-solve all 3 problems mentally** — can you trace through each without looking?
2. **Pattern check** — can you now identify two pointer problems vs greedy vs HashMap?
3. **Complexity check** — state time/space for all 3 solutions from memory

### Update Your Files

**PROGRESS.md:**
- Fill in Day 2 problems table
- Write "What clicked today" and "What didn't click"
- Log any mistakes in ERROR_JOURNAL.md

**Git commit:**
```bash
git add .
git commit -m "Day 2: Two Pointers pattern + 3 problems (Valid Palindrome, Two Sum II, Stock)"
git push
```

### Prepare for Tomorrow

Tomorrow: Prefix Sum pattern + 3 problems. Skim the topic title in the Week 1 plan, but don't study yet.

**Done When:**
- All 3 solution files committed
- PROGRESS.md updated
- Can solve any of today's problems in under 15 minutes

---

## End of Day 2

**Self-assessment:**

- Can I identify when Two Pointers works vs when it doesn't?
- Do I understand why sorted arrays enable two pointers?
- Can I write the converging two pointer template from memory?
- Did I solve all 3 problems independently (no copy-paste from explanations)?

**Tomorrow you learn Prefix Sum — one of the most elegant patterns in DSA. Rest well.**

---

# Day 3 — Wednesday

**Daily Target:** 6 hours total
**Focus:** Prefix Sum Pattern + HashMap Combination

---

## Segment 1: Warm-up — Spaced Repetition (Day 1 + Day 2)

**Estimated Time:** 25–30 minutes

### What You're Doing

Re-solve two problems cold:
1. **Two Sum** (from Day 1) — HashMap approach
2. **Valid Palindrome** (from Day 2) — Two pointer approach

**Time limit:** 12 minutes each. If either takes longer, that's a signal you need to review that pattern before moving forward.

**Why this matters:** Today is Day 3 for Two Sum — second spaced repetition cycle. If you can't solve it cold in 12 minutes, the pattern hasn't moved to long-term memory yet.

**Done When:**
- Both problems solved from memory in under 25 minutes total
- No syntax errors
- Correct time/space complexity stated

---

## Segment 2: Prefix Sum Pattern — From First Principles

**Estimated Time:** 90–110 minutes

### What You're Learning

Prefix Sum transforms a problem about **ranges** into a problem about **single values**. Instead of repeatedly calculating the sum of elements from index i to j (which is O(n) per query), we pre-compute cumulative sums once (O(n)) and then answer each range query in O(1).

This is a classic **time-space tradeoff**: spend O(n) extra space to save massive time on repeated queries.

### The Core Intuition — Rainfall Analogy

Imagine you have daily rainfall data for a year. Someone asks: "How much rain fell between March 15 and June 20?"

**Naive approach:**
Add up rainfall for each day from March 15 to June 20. If that's 97 days, you do 97 additions.

**Prefix Sum approach:**
Before anyone asks questions, create a "total rainfall up to this day" array:
- Jan 31: 120mm total so far
- Feb 28: 185mm total so far
- Mar 15: 220mm total so far
- Jun 20: 540mm total so far

Now the query becomes one subtraction: `540mm - 220mm = 320mm`. Answer in O(1).

### The Math — How Prefix Sum Works

Given array: `arr = [3, 1, 4, 1, 5, 9, 2]`

Build prefix array: `prefix[i]` = sum of all elements from index 0 to i

```
arr    = [3,  1,  4,  1,  5,  9,  2]
prefix = [3,  4,  8,  9, 14, 23, 25]
         ↑   ↑   ↑   ↑   ↑   ↑   ↑
         3  3+1 4+4 8+1 ...
```

**Formula:**
```
prefix[0] = arr[0]
prefix[i] = prefix[i-1] + arr[i]    for i > 0
```

**Range query:**
Sum from index L to R (inclusive):
```
sum(L, R) = prefix[R] - prefix[L-1]
```

**Special case:** If L = 0, just return `prefix[R]`.

**Why it works:**
```
prefix[R]   = arr[0] + arr[1] + ... + arr[L-1] + arr[L] + ... + arr[R]
prefix[L-1] = arr[0] + arr[1] + ... + arr[L-1]

prefix[R] - prefix[L-1] = arr[L] + arr[L+1] + ... + arr[R]  ✓
```

### Example Walkthrough

```
arr = [2, 8, 3, 9, 1, 5]

Build prefix:
prefix[0] = 2
prefix[1] = 2 + 8 = 10
prefix[2] = 10 + 3 = 13
prefix[3] = 13 + 9 = 22
prefix[4] = 22 + 1 = 23
prefix[5] = 23 + 5 = 28

prefix = [2, 10, 13, 22, 23, 28]

Query: sum(2, 4) = arr[2] + arr[3] + arr[4] = 3 + 9 + 1 = 13

Using prefix: sum(2, 4) = prefix[4] - prefix[1]
                        = 23 - 10
                        = 13  ✓
```

### Basic Prefix Sum Template

```java
public class PrefixSum {
    
    // Build prefix sum array — O(n) time, O(n) space
    public int[] buildPrefix(int[] arr) {
        if (arr == null || arr.length == 0) return new int[0];
        
        int n = arr.length;
        int[] prefix = new int[n];
        
        prefix[0] = arr[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + arr[i];
        }
        
        return prefix;
    }
    
    // Range sum query — O(1) time after prefix built
    public int rangeSum(int[] prefix, int left, int right) {
        if (left == 0) return prefix[right];
        return prefix[right] - prefix[left - 1];
    }
}
```

### The Power Combo — HashMap + Prefix Sum

This is where Prefix Sum becomes a FAANG interview favorite. The pattern solves: **"How many subarrays sum to target?"**

**Problem setup:**
Given `arr = [1, 2, 3]` and `target = 3`, count subarrays that sum to 3.

Answer: 2 subarrays → `[1, 2]` and `[3]`

**Naive approach:** Check every possible subarray — O(n²)

**Prefix Sum + HashMap approach:** O(n)

**Key insight:**
If `prefix[j] - prefix[i] = target`, then subarray from `i+1` to `j` sums to target.

Rearranging: `prefix[i] = prefix[j] - target`

**Algorithm:**
```
For each index j:
    Calculate current prefix sum
    Check: have we seen (prefixSum - target) before?
    If yes → count those occurrences (each is a valid subarray ending at j)
    Store current prefixSum in HashMap
```

**Why seed with {0: 1}?**
To handle subarrays starting at index 0. If `prefixSum = target` at some index j, that means `arr[0...j]` sums to target — which is a valid subarray.

### Full HashMap + Prefix Template

```java
public int subarraySum(int[] nums, int target) {
    int count = 0;
    int prefixSum = 0;
    
    // Map: prefixSum → frequency
    // Seed with 0:1 to handle subarrays starting at index 0
    Map<Integer, Integer> seen = new HashMap<>();
    seen.put(0, 1);
    
    for (int num : nums) {
        prefixSum += num;
        
        // Check if (prefixSum - target) exists
        // If yes, we found subarray(s) summing to target
        int complement = prefixSum - target;
        count += seen.getOrDefault(complement, 0);
        
        // Store current prefixSum
        seen.merge(prefixSum, 1, Integer::sum);  // seen[prefixSum]++
    }
    
    return count;
}
```

### Trace Through Example

```
nums = [1, 2, 3], target = 3

Initial: seen = {0: 1}, prefixSum = 0, count = 0

i=0, num=1:
  prefixSum = 0 + 1 = 1
  complement = 1 - 3 = -2
  count += seen[-2] = 0 (not found)
  seen = {0: 1, 1: 1}

i=1, num=2:
  prefixSum = 1 + 2 = 3
  complement = 3 - 3 = 0
  count += seen[0] = 1  ← Found! Subarray [1,2] sums to 3
  seen = {0: 1, 1: 1, 3: 1}

i=2, num=3:
  prefixSum = 3 + 3 = 6
  complement = 6 - 3 = 3
  count += seen[3] = 1  ← Found! Subarray [3] sums to 3
  seen = {0: 1, 1: 1, 3: 1, 6: 1}

return count = 2  ✓
```

**The subarrays found:**
- When prefixSum=3, complement=0 was in map → subarray from start to current = `[1, 2]`
- When prefixSum=6, complement=3 was in map → subarray after index where sum was 3 = `[3]`

### When to Use Prefix Sum — Recognition Signals

Look for these in problem statements:

- **"Sum of subarray from i to j"** — especially if asked multiple times
- **"Number of subarrays with sum = k"**
- **"Find subarray with sum closest to k"**
- **"Pivot index"** — where left sum equals right sum
- **"Product of array except self"** — prefix product variant
- **Anything asking about ranges** in an array repeatedly

### Common Mistakes

**Mistake 1: Not seeding HashMap with {0: 1}**
```java
Map<Integer, Integer> seen = new HashMap<>();
// Missing: seen.put(0, 1);
```
**Result:** Subarrays starting at index 0 are missed.

**Mistake 2: Checking before updating in wrong problems**
In Two Sum, we check BEFORE adding to avoid self-use. In Prefix Sum, we check BEFORE adding because we want to count previous occurrences, not include current.

**Mistake 3: Using wrong formula for range sum**
```java
// WRONG:
sum = prefix[right] - prefix[left];  // off by one

// CORRECT:
if (left == 0) return prefix[right];
return prefix[right] - prefix[left - 1];
```

### Resources

**YouTube:**
1. [Prefix Sum Explained — Abdul Bari (16 min)](https://www.youtube.com/watch?v=pVS3yhlzrlQ) — visual explanation with examples
2. [Subarray Sum Equals K — NeetCode (12 min)](https://www.youtube.com/watch?v=fFVZt-6sgyo) — HashMap + Prefix walkthrough
3. [Prefix Sum Technique — takeUforward (20 min)](https://www.youtube.com/watch?v=20v8zSo2v18) — multiple examples

**Documentation:**
1. [Prefix Sum — CP Algorithms](https://cp-algorithms.com/data_structures/sparse-table.html#toc-tgt-1) — comprehensive guide
2. [Subarray Sum — LeetCode Solution](https://leetcode.com/problems/subarray-sum-equals-k/solution/) — official explanation

### Practice — Pattern Recognition

For each problem, identify: Prefix Sum? HashMap+Prefix? Neither?

1. **"Given array, answer Q queries: sum(L, R) for different ranges"**
   - Answer: ?

2. **"Count number of subarrays with sum divisible by k"**
   - Answer: ?

3. **"Find two numbers that sum to target"**
   - Answer: ?

4. **"Find pivot index where left sum = right sum"**
   - Answer: ?

**Answers:**
1. Prefix Sum — build once, answer each query in O(1)
2. HashMap + Prefix Sum — `prefixSum % k` appears multiple times → subarrays between them divisible by k
3. Neither — Two Sum uses HashMap for complement lookup, not prefix sum
4. Prefix Sum — calculate total, then at each index check if leftSum = total - leftSum - arr[i]

**Done When:**
- You can build a prefix array from scratch
- You understand why `seen.put(0, 1)` is needed in HashMap+Prefix
- You can trace through the HashMap+Prefix algorithm on paper
- You got all 4 pattern recognition questions correct

---

## Segment 3: Problem — Running Sum of 1D Array

**Estimated Time:** 20–25 minutes

### The Problem

**Running Sum of 1D Array** — LeetCode #1480
https://leetcode.com/problems/running-sum-of-1d-array/

> Given an array `nums`, return the running sum array where `runningSum[i] = sum(nums[0]...nums[i])`.

**Examples:**
```
Input:  nums = [1,2,3,4]
Output: [1,3,6,10]
Explanation: [1, 1+2, 1+2+3, 1+2+3+4]

Input:  nums = [1,1,1,1,1]
Output: [1,2,3,4,5]
```

### This Is Literally Building a Prefix Sum Array

This problem is asking you to build the prefix sum array — that's it.

### Apply the Framework

**Phase 3: Brute Force**
For each index i, sum all elements from 0 to i — O(n²)

**Phase 4: Optimize**
Reuse previous sum: `runningSum[i] = runningSum[i-1] + nums[i]` — O(n)

**Phase 5: Code**

```java
public int[] runningSum(int[] nums) {
    if (nums == null || nums.length == 0) return new int[0];
    
    int n = nums.length;
    int[] runningSum = new int[n];
    
    runningSum[0] = nums[0];
    
    for (int i = 1; i < n; i++) {
        runningSum[i] = runningSum[i - 1] + nums[i];
    }
    
    return runningSum;
}
```

**Time:** O(n)
**Space:** O(n) — output array (doesn't count against space complexity per LeetCode rules, but worth noting)

**Can we do it in-place?**

Yes, if we're allowed to modify the input:

```java
public int[] runningSum(int[] nums) {
    for (int i = 1; i < nums.length; i++) {
        nums[i] += nums[i - 1];
    }
    return nums;
}
```

Now space is O(1).

**Resources:**
- [Running Sum — NeetCode (4 min)](https://www.youtube.com/watch?v=kY5vT8lMyVI)

**Done When:** Solved in under 10 minutes

---

## Segment 4: Problem — Find Pivot Index

**Estimated Time:** 30–35 minutes

### The Problem

**Find Pivot Index** — LeetCode #724
https://leetcode.com/problems/find-pivot-index/

> Given an array of integers `nums`, calculate the pivot index of this array.
>
> The pivot index is the index where the sum of all numbers strictly to the left equals the sum of all numbers strictly to the right.
>
> If the index is on the left/right edge, the opposite side sum is 0.
>
> Return the leftmost pivot index. If none exists, return -1.

**Examples:**
```
Input:  nums = [1,7,3,6,5,6]
Output: 3
Explanation: 
  Left sum  = 1 + 7 + 3 = 11
  Right sum = 5 + 6 = 11

Input:  nums = [1,2,3]
Output: -1
Explanation: No pivot index exists

Input:  nums = [2,1,-1]
Output: 0
Explanation: 
  Left sum  = 0
  Right sum = 1 + (-1) = 0
```

### Apply the Framework

**Phase 1: Understand**

> "Find an index where the sum of elements to its left equals the sum of elements to its right."

**Phase 2: Clarify**

- What if multiple pivot indices exist? Return the leftmost
- Edge case: leftmost index (0) has left sum = 0
- Edge case: rightmost index has right sum = 0

**Phase 3: Brute Force**

For each index i, calculate left sum (sum of 0 to i-1) and right sum (sum of i+1 to n-1). Check if equal.

```java
public int pivotIndexBrute(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
        int leftSum = 0, rightSum = 0;
        
        for (int j = 0; j < i; j++) leftSum += nums[j];
        for (int j = i + 1; j < nums.length; j++) rightSum += nums[j];
        
        if (leftSum == rightSum) return i;
    }
    return -1;
}
```

**Time:** O(n²) — for each index, we scan the entire array
**Space:** O(1)

**Phase 4: Optimize**

Use prefix sum concept without building the full array.

Key insight:
```
leftSum + nums[i] + rightSum = totalSum

If leftSum = rightSum, then:
leftSum + nums[i] + leftSum = totalSum
2 * leftSum + nums[i] = totalSum
leftSum = (totalSum - nums[i]) / 2
```

Better approach:
- Calculate total sum
- Track leftSum as we iterate
- At each index: rightSum = totalSum - leftSum - nums[i]
- Check if leftSum == rightSum

**Phase 5: Code**

```java
public int pivotIndex(int[] nums) {
    if (nums == null || nums.length == 0) return -1;
    
    // Calculate total sum
    int totalSum = 0;
    for (int num : nums) {
        totalSum += num;
    }
    
    int leftSum = 0;
    
    for (int i = 0; i < nums.length; i++) {
        // rightSum = totalSum - leftSum - nums[i]
        int rightSum = totalSum - leftSum - nums[i];
        
        if (leftSum == rightSum) {
            return i;
        }
        
        leftSum += nums[i];  // update leftSum for next iteration
    }
    
    return -1;
}
```

**Time:** O(n) — two passes (one for total, one for checking)
**Space:** O(1) — only variables, no arrays

**Phase 6: Test**

```
nums = [1, 7, 3, 6, 5, 6]
totalSum = 28

i=0: leftSum=0, rightSum=28-0-1=27, 0≠27
     leftSum=1
i=1: leftSum=1, rightSum=28-1-7=20, 1≠20
     leftSum=8
i=2: leftSum=8, rightSum=28-8-3=17, 8≠17
     leftSum=11
i=3: leftSum=11, rightSum=28-11-6=11, 11=11 ✓
     return 3
```

**Resources:**
- [Find Pivot Index — NeetCode (8 min)](https://www.youtube.com/watch?v=u89i60lYx8U)

**Done When:** Solved in under 20 minutes

---

## Segment 5: Problem — Subarray Sum Equals K

**Estimated Time:** 50–60 minutes

### The Problem

**Subarray Sum Equals K** — LeetCode #560
https://leetcode.com/problems/subarray-sum-equals-k/

> Given an array of integers `nums` and an integer `k`, return the total number of continuous subarrays whose sum equals `k`.

**Examples:**
```
Input:  nums = [1,1,1], k = 2
Output: 2
Explanation: Subarrays [1,1] (indices 0-1 and 1-2)

Input:  nums = [1,2,3], k = 3
Output: 2
Explanation: Subarrays [1,2] and [3]

Input:  nums = [1,-1,0], k = 0
Output: 3
Explanation: Subarrays [1,-1], [-1,0], [1,-1,0]
```

### This Is THE Classic HashMap + Prefix Sum Problem

This is the problem that teaches the pattern. Master this, and a dozen other problems become trivial.

### Apply the Framework

**Phase 1: Understand**

> "Count how many contiguous subarrays sum to k."

**Phase 2: Clarify**

- Can array have negative numbers? Yes
- Can k be negative? Yes
- Empty subarray? No, need at least one element

**Phase 3: Brute Force**

Check every possible subarray:

```java
public int subarraySum(int[] nums, int k) {
    int count = 0;
    
    for (int i = 0; i < nums.length; i++) {
        int sum = 0;
        for (int j = i; j < nums.length; j++) {
            sum += nums[j];
            if (sum == k) count++;
        }
    }
    
    return count;
}
```

**Time:** O(n²)
**Space:** O(1)

**Phase 4: Optimize**

Use HashMap + Prefix Sum.

**Core insight:**
- If `prefix[j] - prefix[i] = k`, then subarray from `i+1` to `j` sums to k
- Rearranging: `prefix[i] = prefix[j] - k`
- So for each j, check if `(currentPrefixSum - k)` was seen before

**Algorithm:**
```
Initialize: seen = {0: 1}, prefixSum = 0, count = 0

For each number:
    prefixSum += num
    complement = prefixSum - k
    count += seen[complement]  (how many times we've seen this complement)
    seen[prefixSum]++
```

**Phase 5: Code**

```java
public int subarraySum(int[] nums, int k) {
    int count = 0;
    int prefixSum = 0;
    
    Map<Integer, Integer> seen = new HashMap<>();
    seen.put(0, 1);  // Handle subarrays starting at index 0
    
    for (int num : nums) {
        prefixSum += num;
        
        // Check if (prefixSum - k) exists
        int complement = prefixSum - k;
        count += seen.getOrDefault(complement, 0);
        
        // Store current prefixSum
        seen.merge(prefixSum, 1, Integer::sum);
    }
    
    return count;
}
```

**Time:** O(n) — single pass
**Space:** O(n) — HashMap stores at most n unique prefix sums

**Phase 6: Test**

```
nums = [1, 2, 3], k = 3

seen = {0: 1}, prefixSum = 0, count = 0

num=1:
  prefixSum = 1
  complement = 1 - 3 = -2
  count += seen[-2] = 0
  seen = {0: 1, 1: 1}

num=2:
  prefixSum = 3
  complement = 3 - 3 = 0
  count += seen[0] = 1  ✓  (subarray [1,2])
  seen = {0: 1, 1: 1, 3: 1}

num=3:
  prefixSum = 6
  complement = 6 - 3 = 3
  count += seen[3] = 1  ✓  (subarray [3])
  seen = {0: 1, 1: 1, 3: 1, 6: 1}

return 2 ✓
```

### Why Seed with {0: 1}?

```
nums = [3], k = 3

Without seed:
  prefixSum = 3, complement = 0
  count += seen[0] = 0  ✗  (missed the subarray [3])

With seed:
  prefixSum = 3, complement = 0
  count += seen[0] = 1  ✓
```

The seed represents "before the array starts, the sum is 0, and this state exists once."

### Write Solution File

Create `SubarraySumEqualsK.java` with full header template.

**Resources:**
- [Subarray Sum Equals K — NeetCode (12 min)](https://www.youtube.com/watch?v=fFVZt-6sgyo)
- [Prefix Sum HashMap — takeUforward (18 min)](https://www.youtube.com/watch?v=xvNwoz-ufXA)

**Done When:**
- Solved in under 30 minutes
- Can explain why we seed with {0: 1}
- Can trace through on paper with any example

---

## Segment 6: Review + Complexity Practice

**Estimated Time:** 30 minutes

### Today's Review

For each problem solved today, state from memory:

1. **Running Sum**: Pattern? Time? Space?
2. **Find Pivot Index**: Pattern? Time? Space?
3. **Subarray Sum Equals K**: Pattern? Time? Space? Why HashMap?

### Update Files

**PROGRESS.md:**
- Fill in Day 3 problems
- Write "What clicked" — Prefix Sum should have clicked
- Write "What didn't click" — be honest

**ERROR_JOURNAL.md:**
- If you forgot to seed {0: 1}, log it
- If you used wrong range sum formula, log it

**Git commit:**
```bash
git add .
git commit -m "Day 3: Prefix Sum pattern + HashMap combo (3 problems)"
git push
```

**Done When:**
- All 3 problems committed
- Can solve any of today's problems in under 20 minutes
- Understand the HashMap + Prefix pattern deeply

---

## End of Day 3

**Tomorrow: Kadane's Algorithm + Dutch National Flag — two of the most elegant algorithms in arrays. Get ready.**

---

# Day 4 — Thursday

**Daily Target:** 6 hours total
**Focus:** Kadane's Algorithm + Advanced Two Pointer (3-way partition) + Container Problems

---

## Segment 1: Warm-up — Spaced Repetition Triple

**Estimated Time:** 30–35 minutes

### What You're Doing

Re-solve three problems cold:
1. **Two Sum** (Day 1) — HashMap O(n)
2. **Valid Palindrome** (Day 2) — Two pointers
3. **Subarray Sum Equals K** (Day 3) — HashMap + Prefix

**Time limit:** 30 minutes total (10 min each). These should be getting faster — if not, the patterns haven't solidified yet.

**Why three today:** Day 4 is when you start to see if patterns are moving from conscious effort to pattern recognition. If any of these three takes more than 12 minutes, spend an extra 15 minutes reviewing that pattern before moving forward.

**Done When:**
- All three solved from memory in under 30 minutes
- No looking at notes or past solutions
- Can explain the pattern for each in one sentence

---

## Segment 2: Kadane's Algorithm — Maximum Subarray

**Estimated Time:** 75–90 minutes

### What You're Learning

Kadane's Algorithm is one of the most beautiful pieces of algorithmic thinking in all of DSA. It takes a problem that seems to require checking every possible subarray (O(n²)) and solves it with a single pass (O(n)) by making one elegant observation.

### The Problem It Solves

**Maximum Subarray** — LeetCode #53
https://leetcode.com/problems/maximum-subarray/

> Given an integer array `nums`, find the contiguous subarray with the largest sum, and return its sum.

**Examples:**
```
Input:  nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: Subarray [4,-1,2,1] has sum = 6

Input:  nums = [1]
Output: 1

Input:  nums = [5,4,-1,7,8]
Output: 23
Explanation: Entire array
```

### The Brute Force — Check Every Subarray

```java
public int maxSubArrayBrute(int[] nums) {
    int maxSum = Integer.MIN_VALUE;
    
    for (int i = 0; i < nums.length; i++) {
        int currentSum = 0;
        for (int j = i; j < nums.length; j++) {
            currentSum += nums[j];
            maxSum = Math.max(maxSum, currentSum);
        }
    }
    
    return maxSum;
}
```

**Time:** O(n²) — nested loops
**Space:** O(1)

For n=10,000, this does 50 million operations. LeetCode times out.

### The Key Insight — Kadane's Observation

At every index, you face exactly one decision:

**"Should I extend the previous subarray, or start a new one here?"**

```
currentSum = Math.max(nums[i], currentSum + nums[i])
             \_______/  \___________________/
              start         extend
              fresh       previous
```

**Why this works:**

If `currentSum + nums[i] < nums[i]`, that means `currentSum` is negative. A negative sum can only hurt us — we're better off ditching everything before and starting fresh at `nums[i]`.

If `currentSum + nums[i] >= nums[i]`, the previous subarray is still helping — extend it.

**This is dynamic programming compressed to a single variable.**

### The Intuition — The Debt Analogy

Imagine you're walking through an array where each number represents money you gain (positive) or debt you take on (negative).

At each step:
- If your current total is positive, keep going — you're in profit
- If your current total goes negative, you've accumulated too much debt — declare bankruptcy, start fresh from here with $0

Your maximum wealth ever achieved during this journey is the answer.

### Kadane's Algorithm — Full Code

```java
public int maxSubArray(int[] nums) {
    if (nums == null || nums.length == 0) return 0;
    
    int currentSum = nums[0];  // Current subarray sum
    int maxSum = nums[0];      // Best we've seen so far
    
    for (int i = 1; i < nums.length; i++) {
        // The core decision: extend or restart?
        currentSum = Math.max(nums[i], currentSum + nums[i]);
        
        // Track the maximum
        maxSum = Math.max(maxSum, currentSum);
    }
    
    return maxSum;
}
```

**Time:** O(n) — single pass
**Space:** O(1) — two variables

### Why Seed with nums[0], Not 0?

```java
// WRONG:
int currentSum = 0;
int maxSum = 0;

// For nums = [-3, -2, -1], this returns 0
// But the answer should be -1 (best single element)
```

If all numbers are negative, seeding with 0 implies an empty subarray is valid (sum = 0). But the problem requires a non-empty subarray.

**Correct:** Seed both with `nums[0]` — minimum subarray is the first element alone.

### Trace Through Example

```
nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]

i=0: current=-2, max=-2

i=1: current = max(1, -2+1=-1) = 1  (restart at 1)
     max = max(-2, 1) = 1

i=2: current = max(-3, 1+(-3)=-2) = -3  (restart)
     max = max(1, -3) = 1

i=3: current = max(4, -3+4=1) = 4  (restart at 4)
     max = max(1, 4) = 4

i=4: current = max(-1, 4+(-1)=3) = 3  (extend)
     max = max(4, 3) = 4

i=5: current = max(2, 3+2=5) = 5  (extend)
     max = max(4, 5) = 5

i=6: current = max(1, 5+1=6) = 6  (extend)
     max = max(5, 6) = 6

i=7: current = max(-5, 6+(-5)=1) = 1  (extend)
     max = max(6, 1) = 6

i=8: current = max(4, 1+4=5) = 5  (extend)
     max = max(6, 5) = 6

return 6 ✓

Subarray found: [4, -1, 2, 1] from indices 3-6
```

### Variant — Maximum Product Subarray

**Maximum Product Subarray** — LeetCode #152

> Find the contiguous subarray with the largest product.

**Key difference:** With products, two negatives multiply to positive. So we need to track BOTH maximum and minimum products.

```java
public int maxProduct(int[] nums) {
    int maxProd = nums[0];
    int minProd = nums[0];  // Track min for negative flips
    int result = nums[0];
    
    for (int i = 1; i < nums.length; i++) {
        // If current number is negative, max and min swap roles
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

**Why track minimum?**
```
nums = [2, 3, -2, 4]

At index 2 (num = -2):
  Previous max = 6 (from 2*3)
  Previous min = 6
  
  -2 is negative, so:
    maxProd * -2 = 6 * -2 = -12  (becomes min)
    minProd * -2 = 6 * -2 = -12  (becomes... wait)
    
Actually at i=2:
  Before swap: maxProd=6, minProd=2
  After swap:  maxProd=2, minProd=6
  maxProd = max(-2, 2*-2=-4) = -2
  minProd = min(-2, 6*-2=-12) = -12
  
At index 3 (num = 4):
  maxProd = max(4, -2*4=-8) = 4
  But we lost the path...
```

Actually, let me retrace correctly:

```
nums = [2, 3, -2, 4]

i=1: max=max(3,2*3=6)=6, min=min(3,2*3=6)=2
i=2: swap (negative), max=2, min=6
     max=max(-2, 2*-2=-4)=-2
     min=min(-2, 6*-2=-12)=-12
i=3: max=max(4, -2*4=-8)=4
     But the correct answer is 6 from [2,3]

The algorithm works because it tracks the best at each position, but the global result is updated continuously.
```

### Resources

**YouTube:**
1. [Kadane's Algorithm — Abdul Bari (13 min)](https://www.youtube.com/watch?v=86CQq3pKSUw) — excellent visual explanation
2. [Maximum Subarray — NeetCode (10 min)](https://www.youtube.com/watch?v=5WZl3MMT0Eg) — clean walkthrough
3. [Maximum Product Subarray — NeetCode (13 min)](https://www.youtube.com/watch?v=lXVy6YWFcRM) — explains the min tracking

**Documentation:**
1. [Kadane's Algorithm — GeeksforGeeks](https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/) — detailed explanation
2. [Maximum Subarray — LeetCode Solution](https://leetcode.com/problems/maximum-subarray/solution/)

### Practice Exercise

Trace Kadane's on paper:

```
nums = [5, -3, 5]

What is currentSum and maxSum at each step?
What is the final answer?
```

**Done When:**
- You can write Kadane's algorithm from memory
- You understand why we seed with nums[0] not 0
- You can trace through any example on paper
- You understand the difference between max subarray sum vs product

---

## Segment 3: Problem — Maximum Subarray (Apply Kadane's)

**Estimated Time:** 25–30 minutes

### Now Solve It

**Maximum Subarray** — LeetCode #53

You've learned the algorithm. Now implement it from scratch using the 6-phase framework.

**Phase 1-2:** Already done above

**Phase 3:** Brute force (you wrote it above)

**Phase 4:** Kadane's insight (you learned it)

**Phase 5:** Code from memory

**Phase 6:** Test with `[-2,1,-3,4,-1,2,1,-5,4]`

### Write Solution File

Create `MaximumSubarray.java` with the full template header.

**Done When:**
- Solved from memory in under 15 minutes
- Full template header included
- Committed to repo

---

## Segment 4: Dutch National Flag — Three-Way Partitioning

**Estimated Time:** 60–75 minutes

### What You're Learning

The Dutch National Flag algorithm solves: **"Sort an array containing only 0s, 1s, and 2s in a single pass with O(1) space."**

It's called "Dutch National Flag" because the Dutch flag has three horizontal stripes (red, white, blue) — we're partitioning into three regions.

### The Problem

**Sort Colors** — LeetCode #75
https://leetcode.com/problems/sort-colors/

> Given an array `nums` with n objects colored red, white, or blue (represented as 0, 1, 2), sort them in-place so that objects of the same color are adjacent, in the order red, white, blue.

**Examples:**
```
Input:  nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]

Input:  nums = [2,0,1]
Output: [0,1,2]
```

**Constraints:**
- Must sort in-place
- One-pass algorithm preferred
- O(1) extra space

### Brute Force — Use Sorting

```java
Arrays.sort(nums);  // O(n log n)
```

Works, but we can do better by exploiting that there are only 3 distinct values.

### Better — Two-Pass Counting

Count 0s, 1s, 2s. Then overwrite array.

```java
public void sortColors(int[] nums) {
    int count0 = 0, count1 = 0, count2 = 0;
    
    for (int num : nums) {
        if (num == 0) count0++;
        else if (num == 1) count1++;
        else count2++;
    }
    
    int i = 0;
    while (count0-- > 0) nums[i++] = 0;
    while (count1-- > 0) nums[i++] = 1;
    while (count2-- > 0) nums[i++] = 2;
}
```

**Time:** O(n) — two passes
**Space:** O(1)

But the problem asks for **one-pass** if possible.

### Optimal — Dutch National Flag (One Pass)

Use three pointers:
- `low` — boundary of 0s region (everything before low is 0)
- `mid` — current element being examined
- `high` — boundary of 2s region (everything after high is 2)

```
[0, 0, 0, ?, ?, ?, ?, 2, 2, 2]
         ↑        ↑  ↑
        low      mid high

Regions:
  [0, low)      → all 0s
  [low, mid)    → all 1s
  [mid, high]   → unprocessed
  (high, end]   → all 2s
```

**Algorithm:**
```
While mid <= high:
    if nums[mid] == 0:
        swap nums[low] and nums[mid]
        low++, mid++
    else if nums[mid] == 1:
        mid++  (already in correct region)
    else:  // nums[mid] == 2
        swap nums[mid] and nums[high]
        high--
        (do NOT increment mid — need to examine swapped element)
```

### The Code

```java
public void sortColors(int[] nums) {
    int low = 0;
    int mid = 0;
    int high = nums.length - 1;
    
    while (mid <= high) {
        if (nums[mid] == 0) {
            swap(nums, low, mid);
            low++;
            mid++;
        } 
        else if (nums[mid] == 1) {
            mid++;
        } 
        else {  // nums[mid] == 2
            swap(nums, mid, high);
            high--;
            // Note: do NOT increment mid here
        }
    }
}

private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}
```

**Time:** O(n) — single pass, each element examined at most once
**Space:** O(1) — three pointers

### Why Don't We Increment mid After Swapping with high?

When we swap `nums[mid]` with `nums[high]`, the element that comes to `mid` is **unexamined** — we don't know if it's 0, 1, or 2. We need to check it in the next iteration.

When we swap with `low`, the element coming to `mid` is from the "already processed 1s region" — we know it's a 1, so it's safe to move mid forward.

### Trace Through Example

```
nums = [2, 0, 2, 1, 1, 0]
        ↑
      l,m              h

Step 1: nums[0]=2, swap with high
  [0, 0, 2, 1, 1, 2]
   ↑           ↓
  l,m          h

Step 2: nums[0]=0, swap with low
  [0, 0, 2, 1, 1, 2]
      ↑        ↓
     l,m       h

Step 3: nums[1]=0, swap with low
  [0, 0, 2, 1, 1, 2]
         ↑     ↓
        l,m    h

Step 4: nums[2]=2, swap with high
  [0, 0, 1, 1, 2, 2]
         ↑  ↓
        l,m h

Step 5: nums[2]=1, mid++
  [0, 0, 1, 1, 2, 2]
         ↑  ↑↓
        l   mh

Step 6: nums[3]=1, mid++
  [0, 0, 1, 1, 2, 2]
         ↑    ↓↑
        l     hm

mid > high, stop
Result: [0, 0, 1, 1, 2, 2] ✓
```

### Resources

**YouTube:**
1. [Sort Colors — NeetCode (9 min)](https://www.youtube.com/watch?v=4xbWSRZHqac)
2. [Dutch National Flag — Abdul Bari (15 min)](https://www.youtube.com/watch?v=ER4ivZosqCg)

**Done When:**
- Can write the algorithm from memory
- Understand why mid doesn't increment after swapping with high
- Can trace through an example on paper

---

## Segment 5: Problems — Container With Most Water + 3Sum

**Estimated Time:** 60–75 minutes

### Problem 1: Container With Most Water

**Container With Most Water** — LeetCode #11
https://leetcode.com/problems/container-with-most-water/

> Given array `height` where `height[i]` represents the height of a line at position i, find two lines that together with the x-axis form a container that holds the most water.

**Example:**
```
Input:  height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: Lines at index 1 (height 8) and index 8 (height 7)
             Width = 8 - 1 = 7
             Area = min(8, 7) * 7 = 49
```

**Key insight:**
- Area = `min(height[left], height[right]) * (right - left)`
- Two pointers: start at both ends (maximum width)
- Always move the pointer with the shorter height (why?)

**Why move the shorter side?**

If `height[left] < height[right]`:
- Current area is limited by `height[left]`
- Moving `right` inward decreases width AND area is still limited by `height[left]` → can only get worse
- Moving `left` inward decreases width BUT might find a taller line → possibility of improvement

```java
public int maxArea(int[] height) {
    int left = 0;
    int right = height.length - 1;
    int maxArea = 0;
    
    while (left < right) {
        int width = right - left;
        int area = Math.min(height[left], height[right]) * width;
        maxArea = Math.max(maxArea, area);
        
        // Move the pointer with shorter height
        if (height[left] < height[right]) {
            left++;
        } else {
            right--;
        }
    }
    
    return maxArea;
}
```

**Time:** O(n)
**Space:** O(1)

### Problem 2: 3Sum

**3Sum** — LeetCode #15
https://leetcode.com/problems/3sum/

> Given integer array `nums`, return all unique triplets `[nums[i], nums[j], nums[k]]` such that `i != j != k` and `nums[i] + nums[j] + nums[k] == 0`.

**Example:**
```
Input:  nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]

Input:  nums = [0,1,1]
Output: []

Input:  nums = [0,0,0]
Output: [[0,0,0]]
```

**Approach:**
1. Sort the array — O(n log n)
2. Fix one element (i), use two pointers on the rest
3. Skip duplicates to avoid duplicate triplets

```java
public List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(nums);  // MUST sort first
    
    for (int i = 0; i < nums.length - 2; i++) {
        // Skip duplicate values for i
        if (i > 0 && nums[i] == nums[i - 1]) continue;
        
        int left = i + 1;
        int right = nums.length - 1;
        int target = -nums[i];  // We want nums[left] + nums[right] = target
        
        while (left < right) {
            int sum = nums[left] + nums[right];
            
            if (sum == target) {
                result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                
                // Skip duplicates for left
                while (left < right && nums[left] == nums[left + 1]) left++;
                // Skip duplicates for right
                while (left < right && nums[right] == nums[right - 1]) right--;
                
                left++;
                right--;
            } 
            else if (sum < target) {
                left++;
            } 
            else {
                right--;
            }
        }
    }
    
    return result;
}
```

**Time:** O(n²) — O(n log n) sort + O(n) outer loop * O(n) two pointer
**Space:** O(1) — output doesn't count

**Critical:** Duplicate skipping is essential. Without it, you get duplicate triplets.

### Write Both Solution Files

Create:
- `ContainerWithMostWater.java`
- `ThreeSum.java`

**Resources:**
- [Container With Most Water — NeetCode (11 min)](https://www.youtube.com/watch?v=UuiTKBwPgAo)
- [3Sum — NeetCode (12 min)](https://www.youtube.com/watch?v=jzZsG8n2R9A)

**Done When:**
- Both solved and committed
- Understand why we move the shorter side in Container
- Understand the duplicate skipping in 3Sum

---

## Segment 6: Review + Update Progress

**Estimated Time:** 20 minutes

**Update:**
- PROGRESS.md Day 4 entry
- ERROR_JOURNAL.md if needed
- Git commit

**Tomorrow:** Matrix problems + Sliding Window intro + Saturday test prep

---

## End of Day 4

**Self-assessment:**
- Can I write Kadane's algorithm from memory?
- Do I understand the Dutch National Flag three-pointer technique?
- Can I solve Container With Most Water in under 15 minutes?
- Do I understand duplicate skipping in 3Sum?

**You've conquered the hardest array patterns. Tomorrow: clean-up and test prep.**

---

# Day 5 — Friday

**Daily Target:** 6 hours total
**Focus:** Matrix Problems + Sliding Window Intro + Week 1 Integration

---

## Segment 1: Warm-up — Pattern Speed Drill

**Estimated Time:** 25 minutes

### What You're Doing

Solve these three in sequence, as fast as possible:

1. **Maximum Subarray** (Kadane's) — 8 min target
2. **Valid Palindrome** (Two Pointer) — 7 min target
3. **Subarray Sum Equals K** (HashMap + Prefix) — 10 min target

**Total target:** 25 minutes

**Why this drill:** By Day 5, pattern recognition should be instant. If you're still thinking "which pattern is this?" for more than 30 seconds, you need more deliberate practice on that pattern.

**Done When:**
- All three solved in under 25 minutes
- No looking at notes
- Can state the pattern name instantly upon reading the problem

---

## Segment 2: Matrix Traversal + In-Place Operations

**Estimated Time:** 60–75 minutes

### What You're Learning

A 2D array (matrix) is just an array of arrays. But matrix problems test your ability to think in two dimensions — navigating rows and columns, handling boundaries, and often modifying in-place.

**Key skills:**
1. **Accessing elements:** `matrix[row][col]`
2. **Getting dimensions:** `rows = matrix.length`, `cols = matrix[0].length`
3. **Boundary checking:** avoiding `ArrayIndexOutOfBoundsException`
4. **In-place operations:** modifying without extra O(n²) space

### The Standard 4-Direction Movement Template

```java
// Copy this template — use it in EVERY matrix problem

int[][] directions = {
    {0, 1},   // right
    {0, -1},  // left
    {1, 0},   // down
    {-1, 0}   // up
};

for (int[] dir : directions) {
    int newRow = row + dir[0];
    int newCol = col + dir[1];
    
    // Always check bounds before accessing
    if (newRow >= 0 && newRow < rows && 
        newCol >= 0 && newCol < cols) {
        // Safe to access matrix[newRow][newCol]
    }
}
```

**Why this template:**
- No magic numbers scattered in code
- Easy to extend to 8 directions (add diagonals)
- Makes code self-documenting

### Problem: Rotate Image (90° Clockwise)

**Rotate Image** — LeetCode #48
https://leetcode.com/problems/rotate-image/

> Rotate an n×n matrix 90 degrees clockwise in-place.

**Example:**
```
Input:
[1, 2, 3]
[4, 5, 6]
[7, 8, 9]

Output:
[7, 4, 1]
[8, 5, 2]
[9, 6, 3]
```

**Brute Force:** Create new matrix, copy rotated values — O(n²) space

**Optimal:** Two-step in-place transformation

**Step 1: Transpose** (swap across diagonal)
```
[1, 2, 3]       [1, 4, 7]
[4, 5, 6]  →    [2, 5, 8]
[7, 8, 9]       [3, 6, 9]

Swap matrix[i][j] with matrix[j][i]
```

**Step 2: Reverse each row**
```
[1, 4, 7]       [7, 4, 1]
[2, 5, 8]  →    [8, 5, 2]
[3, 6, 9]       [9, 6, 3]
```

**Code:**

```java
public void rotate(int[][] matrix) {
    int n = matrix.length;
    
    // Step 1: Transpose
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {  // j starts at i+1 to avoid double-swap
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
            matrix[i][left] = matrix[i][right];
            matrix[i][right] = temp;
            left++;
            right--;
        }
    }
}
```

**Time:** O(n²) — visit each cell twice
**Space:** O(1) — in-place

**Critical detail:** In transpose, `j` starts at `i + 1`, not `0`. Starting at 0 would swap each pair twice, resetting them.

### Problem: Spiral Matrix

**Spiral Matrix** — LeetCode #54
https://leetcode.com/problems/spiral-matrix/

> Return all elements of an m×n matrix in spiral order.

**Example:**
```
Input:
[1, 2, 3]
[4, 5, 6]
[7, 8, 9]

Output: [1,2,3,6,9,8,7,4,5]
```

**Approach:** Track four boundaries, shrink them as you traverse each layer.

```java
public List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> result = new ArrayList<>();
    if (matrix == null || matrix.length == 0) return result;
    
    int top = 0;
    int bottom = matrix.length - 1;
    int left = 0;
    int right = matrix[0].length - 1;
    
    while (top <= bottom && left <= right) {
        // Traverse right
        for (int col = left; col <= right; col++) {
            result.add(matrix[top][col]);
        }
        top++;
        
        // Traverse down
        for (int row = top; row <= bottom; row++) {
            result.add(matrix[row][right]);
        }
        right--;
        
        // Traverse left (if still have rows)
        if (top <= bottom) {
            for (int col = right; col >= left; col--) {
                result.add(matrix[bottom][col]);
            }
            bottom--;
        }
        
        // Traverse up (if still have columns)
        if (left <= right) {
            for (int row = bottom; row >= top; row--) {
                result.add(matrix[row][left]);
            }
            left++;
        }
    }
    
    return result;
}
```

**Time:** O(m×n) — visit each cell once
**Space:** O(1) — output doesn't count

**Critical:** The `if (top <= bottom)` and `if (left <= right)` checks prevent duplicate traversal in single-row or single-column cases.

### Problem: Set Matrix Zeroes

**Set Matrix Zeroes** — LeetCode #73
https://leetcode.com/problems/set-matrix-zeroes/

> If an element is 0, set its entire row and column to 0. Do it in-place.

**Example:**
```
Input:
[1, 1, 1]
[1, 0, 1]
[1, 1, 1]

Output:
[1, 0, 1]
[0, 0, 0]
[1, 0, 1]
```

**Challenge:** Can't mark zeros during first pass — we'd lose track of original zeros.

**Approach:** Use first row and first column as markers.

```java
public void setZeroes(int[][] matrix) {
    int rows = matrix.length;
    int cols = matrix[0].length;
    boolean firstRowZero = false;
    boolean firstColZero = false;
    
    // Check if first row has any zero
    for (int j = 0; j < cols; j++) {
        if (matrix[0][j] == 0) firstRowZero = true;
    }
    
    // Check if first column has any zero
    for (int i = 0; i < rows; i++) {
        if (matrix[i][0] == 0) firstColZero = true;
    }
    
    // Use first row and column as markers
    for (int i = 1; i < rows; i++) {
        for (int j = 1; j < cols; j++) {
            if (matrix[i][j] == 0) {
                matrix[i][0] = 0;  // mark row
                matrix[0][j] = 0;  // mark column
            }
        }
    }
    
    // Set zeros based on markers (skip first row/col for now)
    for (int i = 1; i < rows; i++) {
        for (int j = 1; j < cols; j++) {
            if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                matrix[i][j] = 0;
            }
        }
    }
    
    // Handle first row
    if (firstRowZero) {
        for (int j = 0; j < cols; j++) matrix[0][j] = 0;
    }
    
    // Handle first column
    if (firstColZero) {
        for (int i = 0; i < rows; i++) matrix[i][0] = 0;
    }
}
```

**Time:** O(m×n)
**Space:** O(1)

### Resources

**YouTube:**
1. [Rotate Image — NeetCode (11 min)](https://www.youtube.com/watch?v=fMSJSS7eO1w)
2. [Spiral Matrix — NeetCode (10 min)](https://www.youtube.com/watch?v=BJnMZNwUk1M)
3. [Set Matrix Zeroes — NeetCode (13 min)](https://www.youtube.com/watch?v=T41rL0L3Pnw)

**Done When:**
- Can write the transpose + reverse rotation from memory
- Understand the four-boundary spiral traversal
- Understand why we need firstRowZero/firstColZero flags

---

## Segment 3: Sliding Window — Introduction

**Estimated Time:** 75–90 minutes

### What You're Learning

Sliding Window is the fourth major array pattern (after Two Pointers, Prefix Sum, Kadane's). It solves problems about **contiguous subarrays/substrings with a dynamic constraint**.

**The pattern:** Maintain a "window" that expands from the right and contracts from the left based on a validity condition.

### The Two Critical Questions

Before writing any sliding window code, answer:

1. **What makes the window valid?**
2. **What forces the window to shrink?**

Answer these first. The code writes itself after.

### Fixed-Size Window

**Problem:** Find maximum sum of any subarray of size k.

```
nums = [2, 1, 5, 1, 3, 2], k = 3

Windows:
[2, 1, 5] → sum = 8
[1, 5, 1] → sum = 7
[5, 1, 3] → sum = 9  ← max
[1, 3, 2] → sum = 6

Answer: 9
```

**Template:**

```java
public int maxSumFixedWindow(int[] nums, int k) {
    // Build first window
    int windowSum = 0;
    for (int i = 0; i < k; i++) {
        windowSum += nums[i];
    }
    
    int maxSum = windowSum;
    
    // Slide window
    for (int i = k; i < nums.length; i++) {
        windowSum += nums[i];        // add right element
        windowSum -= nums[i - k];    // remove left element
        maxSum = Math.max(maxSum, windowSum);
    }
    
    return maxSum;
}
```

**Time:** O(n) — each element enters and leaves window once
**Space:** O(1)

### Variable-Size Window

**Problem:** Longest substring without repeating characters.

```
s = "abcabcbb"

Windows:
a → valid, len=1
ab → valid, len=2
abc → valid, len=3
abca → INVALID (duplicate 'a') → shrink
bca → valid, len=3
bcab → INVALID (duplicate 'b') → shrink
...

Answer: 3 ("abc" or "bca" or "cab")
```

**Template:**

```java
public int lengthOfLongestSubstring(String s) {
    Set<Character> window = new HashSet<>();
    int left = 0;
    int maxLen = 0;
    
    for (int right = 0; right < s.length(); right++) {
        char c = s.charAt(right);
        
        // Shrink from left until window is valid again
        while (window.contains(c)) {
            window.remove(s.charAt(left));
            left++;
        }
        
        window.add(c);  // expand window
        maxLen = Math.max(maxLen, right - left + 1);
    }
    
    return maxLen;
}
```

**Time:** O(n) — each character enters and leaves set at most once
**Space:** O(min(n, m)) where m = alphabet size

**Key insight:** The `while` loop for shrinking might run multiple times per right position — that's fine. Total iterations across all right positions is still O(n).

### Problem: Longest Substring Without Repeating Characters

Implement the solution above. This is LeetCode #3.

### Resources

**YouTube:**
1. [Sliding Window Technique — NeetCode (15 min)](https://www.youtube.com/watch?v=p-ss2JNynmw)
2. [Longest Substring Without Repeating — NeetCode (11 min)](https://www.youtube.com/watch?v=wiGpQwVHdE0)

**Done When:**
- Understand the difference between fixed and variable window
- Can answer the two questions for any sliding window problem
- Solved longest substring without repeating in under 20 minutes

---

## Segment 4: Week 1 Integration — Pattern Map

**Estimated Time:** 45 minutes

### What You're Doing

Create a **Pattern Recognition Map** — a one-page cheat sheet you'll reference tomorrow during the test.

### Format

For each pattern, write:

1. **Pattern name**
2. **When to recognize it** (trigger words)
3. **Template** (core 5 lines of code)
4. **Complexity** (time/space)
5. **Common mistakes**

**Example entry:**

```
═══════════════════════════════════════════════════
TWO POINTERS (Converging)
═══════════════════════════════════════════════════
Trigger words:
  - Sorted array + find pair/triplet
  - Palindrome check
  - Container/area between boundaries

Template:
  left = 0, right = n-1
  while (left < right):
      if condition: return/record
      elif need_bigger: left++
      else: right--

Complexity: O(n) time, O(1) space

Common mistakes:
  - Using <= instead of <
  - Not sorting first when needed
  - Not skipping duplicates in 3Sum
═══════════════════════════════════════════════════
```

Create entries for:
1. HashMap (Two Sum style)
2. Two Pointers (Converging)
3. Two Pointers (Same Direction)
4. Three Pointers (Dutch National Flag)
5. Prefix Sum (Basic)
6. Prefix Sum + HashMap
7. Kadane's Algorithm
8. Sliding Window (Fixed)
9. Sliding Window (Variable)

**Done When:**
- One-page map completed
- Can explain each pattern in under 30 seconds
- Map is handwritten or typed and ready to reference tomorrow

---

## Segment 5: Solve One Problem from Each Pattern

**Estimated Time:** 90 minutes

### What You're Doing

Solve one problem from each major pattern to test pattern recognition:

1. **HashMap:** Two Sum
2. **Two Pointer:** Valid Palindrome
3. **Prefix Sum:** Find Pivot Index
4. **Kadane's:** Maximum Subarray
5. **Sliding Window:** Longest Substring Without Repeating

**Target:** 15 minutes per problem = 75 minutes total

**Why this drill:** Tomorrow's test will give you unseen problems. Your pattern recognition speed determines success. If you can't identify the pattern in 60 seconds, you can't solve it in interview time limits.

**Done When:**
- All 5 solved in under 90 minutes
- Pattern identified within first minute for each
- No notes or references used

---

## End of Day 5

**Tomorrow:** Week 1 Test — 2 unseen problems, timed, in test conditions.

**Prepare:**
- Review your Pattern Recognition Map
- Get good sleep (problem-solving ability drops 30% when tired)
- Have scratch paper ready for tomorrow
- Clear 3 hours of uninterrupted time

**You're ready.**

---

# Day 6 — Saturday

**Daily Target:** 10 hours (test + deep review)
**Focus:** Week 1 Assessment + Full Pattern Review

---

## Segment 1: Week 1 Test — Timed Assessment

**Estimated Time:** 2 hours (strict)

### Test Format

You will solve **2 unseen problems** in test conditions:
- 60 minutes per problem (120 min total)
- No looking at notes or past solutions
- No YouTube or documentation
- Scratch paper allowed
- Must write full solution with template header

### The Two Test Problems

---

**PROBLEM 1 — Medium Difficulty**

**Longest Repeating Character Replacement** — LeetCode #424
https://leetcode.com/problems/longest-repeating-character-replacement/

> You are given a string `s` and an integer `k`. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most `k` times.
>
> Return the length of the longest substring containing the same letter you can get after performing the above operations.

**Examples:**
```
Input:  s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'B's with 'A's → "AAAA"

Input:  s = "AABABBA", k = 1
Output: 4
Explanation: Replace one 'A' with 'B' in "AABA" → "AABB" or "BBBB"
```

**Time limit:** 60 minutes

---

**PROBLEM 2 — Medium Difficulty**

**Product of Array Except Self** — LeetCode #238
https://leetcode.com/problems/product-of-array-except-self/

> Given an integer array `nums`, return an array `answer` such that `answer[i]` is equal to the product of all elements of `nums` except `nums[i]`.
>
> You must write an algorithm that runs in O(n) time and without using the division operation.

**Examples:**
```
Input:  nums = [1,2,3,4]
Output: [24,12,8,6]
Explanation: [2*3*4, 1*3*4, 1*2*4, 1*2*3]

Input:  nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]
```

**Follow-up:** Can you solve it in O(1) extra space? (Output array doesn't count.)

**Time limit:** 60 minutes

---

### Test Rules

1. **Start timer:** 120 minutes total
2. **No pausing:** Bathroom breaks count against time
3. **Submit when done** or when time expires
4. **Record:**
   - Time taken per problem
   - Number of attempts before working solution
   - Pattern identified (what made you choose it?)
   - Mistakes made

### Scoring

For each problem:
- Pattern identified correctly: 2 points
- Brute force stated: 1 point
- Optimal approach explained: 2 points
- Code compiles: 1 point
- Code passes examples: 2 points
- Correct complexity stated: 1 point
- Clean code (template header, comments): 1 point

**Total possible: 20 points (10 per problem)**

**Grading:**
- 18–20: Excellent — ready for Week 2
- 15–17: Good — review weak areas Sunday
- 12–14: Needs work — spend Sunday re-solving Week 1 problems
- <12: Reset — re-do Week 1 before moving to Week 2

### After Test — Immediate Debrief

**Don't look up solutions yet.** First, write:

1. What pattern did you identify for each problem?
2. What was your approach?
3. Where did you get stuck?
4. What would you do differently?

**Save this — you'll compare against solutions in Segment 2.**

---

## Segment 2: Test Solutions + Analysis

**Estimated Time:** 90 minutes

### Solution 1: Longest Repeating Character Replacement

**Pattern:** Sliding Window (Variable)

**Key insight:** 
- Valid window if `(windowSize - maxFrequency) <= k`
- windowSize - maxFrequency = number of characters we need to replace
- If that exceeds k, window is invalid → shrink

**Solution:**

```java
public int characterReplacement(String s, int k) {
    int[] freq = new int[26];  // frequency of each letter in window
    int left = 0;
    int maxFreq = 0;  // most frequent character count in current window
    int maxLen = 0;
    
    for (int right = 0; right < s.length(); right++) {
        freq[s.charAt(right) - 'A']++;
        maxFreq = Math.max(maxFreq, freq[s.charAt(right) - 'A']);
        
        // Check if window is valid
        int windowSize = right - left + 1;
        if (windowSize - maxFreq > k) {
            // Invalid — shrink from left
            freq[s.charAt(left) - 'A']--;
            left++;
        }
        
        maxLen = Math.max(maxLen, right - left + 1);
    }
    
    return maxLen;
}
```

**Time:** O(n)
**Space:** O(1) — array of size 26

**Common mistakes:**
- Using HashMap instead of int[26] (slower)
- Not understanding the validity condition
- Updating maxFreq incorrectly

### Solution 2: Product of Array Except Self

**Pattern:** Prefix Product (variant of Prefix Sum)

**Key insight:**
- `result[i] = (product of all left) × (product of all right)`
- Two passes: left-to-right for left products, right-to-left for right products
- Use output array for left products, then multiply in-place with right products → O(1) space

**Solution:**

```java
public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] result = new int[n];
    
    // Pass 1: result[i] = product of all elements LEFT of i
    result[0] = 1;  // no elements to the left of index 0
    for (int i = 1; i < n; i++) {
        result[i] = result[i - 1] * nums[i - 1];
    }
    
    // Pass 2: multiply by product of all elements RIGHT of i
    int rightProduct = 1;
    for (int i = n - 1; i >= 0; i--) {
        result[i] *= rightProduct;
        rightProduct *= nums[i];
    }
    
    return result;
}
```

**Time:** O(n) — two passes
**Space:** O(1) — output array doesn't count per problem rules

**Trace example:**
```
nums = [1, 2, 3, 4]

After pass 1 (left products):
result = [1, 1, 2, 6]
         [-, 1, 1*2, 1*2*3]

Pass 2 (multiply by right products):
i=3: result[3] *= 1 → result[3] = 6*1 = 6, rightProd = 1*4 = 4
i=2: result[2] *= 4 → result[2] = 2*4 = 8, rightProd = 4*3 = 12
i=1: result[1] *= 12 → result[1] = 1*12 = 12, rightProd = 12*2 = 24
i=0: result[0] *= 24 → result[0] = 1*24 = 24

result = [24, 12, 8, 6] ✓
```

**Common mistakes:**
- Using division (problem explicitly forbids it)
- Creating two extra arrays instead of reusing output
- Off-by-one errors in loop bounds

### Your Analysis

Compare your solutions to these. For each problem:

**Did you:**
- Identify the correct pattern?
- Choose the optimal approach?
- Handle edge cases?
- State correct complexity?

**Error Journal entries:**
- Log every mistake you made
- Write the root cause
- Create a personal rule

---

## Segment 3: Deep Review — Weak Pattern Focus

**Estimated Time:** 2–3 hours

### Based on Test Performance

**If you scored 18–20:**
- Pick your hardest problem from Week 1
- Re-solve it from scratch
- Then solve 2 similar problems on LeetCode

**If you scored 15–17:**
- Identify which pattern you struggled with most
- Re-watch the YouTube video for that pattern
- Re-solve all problems using that pattern
- Do 2 more LeetCode problems in that pattern

**If you scored 12–14:**
- Re-do the entire pattern lesson for your weakest pattern
- Solve all problems from that pattern again
- Create flashcards for pattern recognition

**If you scored <12:**
- You're not ready for Week 2 yet
- Spend Sunday re-doing Days 1-5
- Re-take the test Monday
- Don't move to Week 2 until you score 15+

---

## Segment 4: Spaced Repetition — Week 1 Problems

**Estimated Time:** 2 hours

### The List

Re-solve these problems from memory:

1. Two Sum
2. Valid Palindrome
3. Two Sum II
4. Running Sum
5. Find Pivot Index
6. Subarray Sum Equals K
7. Maximum Subarray
8. Sort Colors
9. Container With Most Water
10. 3Sum

**Target:** 12 minutes average per problem = 2 hours total

**Why this matters:** These 10 problems cover every pattern from Week 1. If you can solve all 10 without hesitation, the patterns are solidifying.

**Record your times** — they should be faster than the first time you solved each.

---

## Segment 5: Update PROGRESS.md + ERROR_JOURNAL.md

**Estimated Time:** 45 minutes

### PROGRESS.md Week 1 Summary

Fill in:
- Total problems solved this week
- Test score (X/20)
- Patterns that clicked
- Patterns still shaky
- Biggest mistake of the week
- Week 2 carry-forward problems (hardest 3 from Week 1 to re-solve in Week 2)

### ERROR_JOURNAL.md — Week 1 Close

Review all entries from this week. Look for patterns:
- Same mistake type appearing multiple times?
- Root cause category with highest count?
- Any mistake you repeated after logging it?

**Update mistake summary table** with Week 1 totals.

### Git — Week 1 Complete

```bash
git add .
git commit -m "Week 1 complete: 18 problems solved, test score X/20, full review done"
git push
```

---

## End of Day 6

**Tomorrow is REST. No coding. Review only.**

---

# Day 7 — Sunday

**Daily Target:** 4 hours (light review + Week 2 prep)
**Focus:** Rest + Reflection + Week 2 Preview

---

## Segment 1: Morning — No Screens

**Duration:** First 3 hours after waking up

**Do:**
- Physical activity (walk, run, gym, yoga)
- Read something non-technical
- Spend time with family/friends
- Cook a good meal

**Don't:**
- Open laptop
- Think about coding
- Review flashcards
- Watch YouTube tutorials

**Why:** Your brain consolidates learning during rest. The patterns you learned this week are being wired into long-term memory right now. Interrupting that process with more input is counterproductive.

---

## Segment 2: Afternoon — Passive Review

**Estimated Time:** 90 minutes

### What You're Doing

**Not coding.** Just reading and thinking.

1. **Read through your Pattern Recognition Map** (from Day 5)
2. **Read through all your solution files** (don't run them, just read)
3. **Read your ERROR_JOURNAL.md** — what patterns do you see?
4. **Read Week 1 PROGRESS.md** — are you proud of what you see?

### Mental Pattern Drill

For each problem below, **without writing code**, answer:
- What pattern?
- What data structure?
- What's the time complexity?

Problems:
1. Find two numbers that sum to target (unsorted array)
2. Find two numbers that sum to target (sorted array)
3. Check if string is palindrome
4. Count subarrays with sum = k
5. Find maximum sum of any contiguous subarray
6. Sort array of 0s, 1s, 2s in one pass
7. Find longest substring with no repeating characters

**Answers:**
1. HashMap, O(n)
2. Two Pointers, O(n)
3. Two Pointers, O(n)
4. HashMap + Prefix Sum, O(n)
5. Kadane's, O(n)
6. Dutch National Flag (three pointers), O(n)
7. Sliding Window + HashSet, O(n)

If you got all 7 correct instantly — Week 1 is solidified.

---

## Segment 3: Week 2 Preview — Read Only

**Estimated Time:** 60 minutes

### What's Coming in Week 2

**Topics:**
- Sliding Window (advanced)
- String manipulation
- More array problems (harder variants)
- Integration week — combining multiple patterns

**Why Week 2 is harder:**
- Problems won't clearly label which pattern to use
- You'll need to recognize patterns from problem descriptions
- Some problems require combining two patterns

**Your prep:**
- Make sure your Week 1 patterns are solid
- If test score was <15, redo Week 1
- Get mentally ready for pattern recognition under pressure

**Do NOT start studying Week 2 content today.** Just preview.

---

## Segment 4: Reflection + Goal Setting

**Estimated Time:** 30 minutes

### Write Honest Answers

**Week 1 Reflection:**
1. What was harder than you expected?
2. What was easier than you expected?
3. What pattern clicked immediately?
4. What pattern still feels shaky?
5. Are you spending the full 6 hours on weekdays? (Be honest.)
6. Is your daily schedule sustainable?
7. Do you need to adjust anything for Week 2?

**Week 2 Goals:**
1. Target problems to solve
2. Test score target (should be higher than Week 1)
3. One specific improvement (e.g., "identify pattern in <30 seconds")

**Write these in PROGRESS.md under a new section.**

---

## Segment 5: Physical Prep for Week 2

**Estimated Time:** 30 minutes

### Set Yourself Up for Success

**Workspace:**
- Clean desk
- Notebook and pen ready
- Water bottle filled
- Phone in another room during study hours

**Schedule:**
- Block 6-hour chunks on weekdays in calendar
- Block 10-hour chunks on Saturday
- Sunday 4 hours only

**Accountability:**
- Tell someone you're doing this 18-month plan
- Share your GitHub repo with one person
- Commit to updating PROGRESS.md every Sunday

**Nutrition:**
- Meal prep for Monday-Wednesday
- Brain needs glucose — don't skip meals during study hours
- Avoid energy drinks (crash hurts more than help)

---

## End of Week 1

**You've completed:**
- 18 problems solved
- 5 patterns mastered
- 1 week of consistent 6-hour days
- 152 hours of focused preparation

**That's 152 more hours than 99% of people who say "I want to work at FAANG."**

**Week 2 starts tomorrow. You're ready.**

**On Sunday next week, just say "Week 2" and I'll deliver the full plan.**

---

# 🎯 Week 1 Complete — Summary

**Problems Solved:** 18
**Patterns Mastered:** 5
- HashMap (Two Sum style)
- Two Pointers (Converging + Same Direction)
- Prefix Sum + HashMap Combo
- Kadane's Algorithm
- Sliding Window (Intro)

**Test Score:** ___/20 (fill in your actual score)

**Ready for Week 2:** Yes / No (be honest)

**Next Action:** Rest tonight. Tomorrow morning, dive into Week 2 with fresh energy.

**Remember:** Consistency beats intensity. 6 hours daily for 18 months will take you further than 12 hours daily for 3 months.

**See you in Week 2.**