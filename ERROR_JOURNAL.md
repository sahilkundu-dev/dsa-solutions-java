# 🔴 ERROR_JOURNAL.md — Sahil Kundu

> **Purpose:** Every mistake I make, logged honestly.
> Root cause analysed. Personal rule created. Never repeated.
>
> **The Rule:** If I look at a hint, get a wrong answer, or feel uncertain
> about my solution — it goes in here. No sugarcoating.
>
> **Updated:** After every problem that doesn't go perfectly.

---

## 📌 How to Use This Journal

Each entry has 5 fields:

| Field | What to write |
|-------|--------------|
| **Problem** | Name + difficulty + link |
| **What went wrong** | Exactly what happened — be specific |
| **Root cause** | WHY it went wrong — the real reason |
| **Fix** | What the correct approach is |
| **My rule** | One sentence rule to never repeat this |

---

## 📊 Mistake Pattern Summary

> Updated monthly. Look for patterns — the same root cause appearing 3+ times means it's a habit to break.

| Root Cause Category | Count | Last Occurred |
|--------------------|-------|---------------|
| Jumped to code without thinking | 0 | — |
| Missed edge case (null/empty) | 1 | 13th May, 2026 |
| Wrong pattern chosen | 0 | — |
| Off-by-one error | 0 | — |
| Incorrect complexity analysis | 0 | — |
| Went silent (no narration) | 0 | — |
| Ignored interviewer hint | 0 | — |
| Poor variable names | 0 | — |
| Skipped brute force step | 0 | — |
| Overcomplicated solution | 0 | — |
| Did not test after coding | 0 | — |
| Wrong data structure chosen | 0 | — |

---

## 📅 Month 1 Entries

---

### Entry #001

**Date:** — 13th May, 2026
**Problem:** [Two Sum] · [Easy] · [https://leetcode.com/problems/two-sum/]
**Week:** Week 1 · Month 1

**What went wrong:**
> *It was mentioned in the Question, that there must be atleast two elements present in the array. What I did wrong was, when writing the code, I missed out on the Edge case, i.e, "if(nums == null || nums.length < 2) { return new int[] {-1, -1}; }", this will not cause any error, but according to any Interview approach, I was not covering all the grounds for each and every Edge Cases.*

**Root cause:**
> *Due to lack of Concentration, not reading the question properly and forgetting minute details while writing the code.*

**Correct approach:**
> *The right way, to avoid this mistake is to take small notes from the question in own words, and using comments in the code to not forget, before writing the code.*

**My personal rule:**
> *My personal rule would be - take down small notes and write them in comments format in the code editor itself to not forget.*

**Will I repeat this?** *(Cross this out when you've gone 2 weeks without this mistake)*

---

### Entry #002

**Date:** —
**Problem:** —
**Week:** —

*(Copy the format from Entry #001)*

---

*New entries go here, newest at the top of their month section.*

---

## 📅 Month 2 Entries

*Entries added as Month 2 progresses.*

---

## 📅 Month 3 Entries

*Entries added as Month 3 progresses.*

---

## 📅 Month 4 Entries

*Entries added as Month 4 progresses.*

---

## 🏆 Resolved Mistakes

> Mistakes I've fully fixed — no longer a habit. Moved here from active entries.
> A mistake is "resolved" when I've gone 3+ weeks without repeating it.

| # | Problem | Mistake Type | Date Resolved | Weeks to Fix |
|---|---------|-------------|---------------|-------------|
| — | — | — | — | — |

---

## 💡 Golden Rules — Built From Mistakes

> Every rule below came from a real mistake in this journal.
> These are my personal FAANG interview laws.

*(Rules get added here as mistakes accumulate. Each rule links to the entry that created it.)*

1. *(Your first rule — added after your first mistake)*
2. —
3. —

---

## 📋 Pre-Problem Checklist

> Run through this before starting EVERY problem — in practice and in real interviews.
> These exist because I once violated each one.

- [ ] Read the full problem before touching the keyboard
- [ ] Paraphrase the problem back in my own words
- [ ] Ask: what is the input? What is the output? What are the constraints?
- [ ] Ask clarifying questions (empty input? negatives? duplicates? input size?)
- [ ] State the brute force approach verbally — even if obvious
- [ ] State the brute force time and space complexity
- [ ] Think through the optimal approach with an example before coding
- [ ] Only then — open the editor
- [ ] Use meaningful variable names throughout
- [ ] Handle edge cases explicitly at the top
- [ ] Narrate every line as I write it — no silence over 30 seconds
- [ ] After last line: trace through 2 examples manually (happy path + edge case)
- [ ] State final time and space complexity with justification
- [ ] Only then say "done"
