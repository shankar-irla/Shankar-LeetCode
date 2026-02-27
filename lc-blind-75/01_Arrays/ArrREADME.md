# 🧩 Arrays – Complete Concept Guide

Arrays are one of the most fundamental data structures in computer science.  
This document provides a structured understanding of array fundamentals, memory layout, complexity analysis, core patterns, and a practical problem-solving framework.

---

## 📌 1️⃣ What is an Array?

An array is a linear data structure that stores elements of the **same data type** in **contiguous memory locations**.

It provides:

- Constant-time access
- Sequential storage
- Index-based retrieval
- Efficient traversal

---

## 📌 Visual Representation

An array stores elements in contiguous memory blocks.

<p align="center">

| Index | 0 | 1 | 2 | 3 | 4 | 5 |
|-------|---|---|---|---|---|---|
| Value | 1 | 3 | 5 | 7 | 7 | 9 |

</p>

- Indexing starts from **0**
- Elements are stored sequentially in memory
- Accessing `arr[i]` takes **O(1)** time

---

## 📌 2️⃣ Internal Memory Representation

If:

- Base address = **B**
- Size of each element = **W bytes**
- Index = **i**

Then:

```
Address(arr[i]) = B + (i × W)
```

This direct computation enables constant-time element access.

---

## 📌 3️⃣ Time & Space Complexity

| Operation | Time Complexity |
|------------|-----------------|
| Access     | O(1)            |
| Update     | O(1)            |
| Search     | O(n)            |
| Insert     | O(n)            |
| Delete     | O(n)            |

Insertions and deletions may require shifting elements, resulting in linear time complexity.

---

## 📌 4️⃣ Core Array Patterns

Array problems are often solved using structured patterns.

---

### 🔹 1. Two Pointers

Used when:
- The array is sorted
- Searching for pairs or triplets
- Partitioning or rearranging elements

---

### 🔹 2. Sliding Window

Used for:
- Subarray-based problems
- Fixed or variable window sizes
- Maximum or minimum aggregate computations

Types:
- Fixed Window
- Variable Window

---

### 🔹 3. Prefix Sum

Used for:
- Range sum queries
- Subarray sum calculations

Formula:

```
prefix[i] = prefix[i-1] + arr[i]
```

---

### 🔹 4. Kadane’s Algorithm

Used for:
- Maximum Subarray Sum

Core idea:

```
currentSum = max(arr[i], currentSum + arr[i])
```

Time Complexity: **O(n)**  
Space Complexity: **O(1)**

---

### 🔹 5. Hashing with Arrays

Used for:
- Frequency counting
- Complement-based lookups

---

### 🔹 6. In-Place Modification

Used when:
- Extra space is restricted
- Rearrangement is required without auxiliary structures

---

### 🔹 7. Sorting-Based Approach

Sort → Apply logic

Used for:
- Triplet problems
- Interval merging
- Grouping related elements

Time Complexity: **O(n log n)**

---

## 📌 5️⃣ Edge Case Checklist

Before finalizing a solution:

- Is the array empty?
- Does it contain a single element?
- Are all elements identical?
- Are all elements negative?
- Is the input size large?
- Is the array sorted or unsorted?

---

## 📌 6️⃣ Optimization Strategy

1️⃣ Begin with a clear brute-force approach  
2️⃣ Identify the structural pattern  
3️⃣ Apply an appropriate optimization technique  
4️⃣ Reduce time complexity where possible  
5️⃣ Validate edge cases carefully  

---

## 📌 7️⃣ When to Use Which Pattern?

| Problem Type        | Recommended Pattern       |
|---------------------|--------------------------|
| Pair sum            | Two Pointer / HashMap    |
| Subarray sum        | Prefix Sum               |
| Maximum subarray    | Kadane                   |
| Fixed window        | Sliding Window           |
| Rearranging         | In-place                 |
| Range query         | Prefix Sum               |

---

## 📌 Problem-Solving Perspective

When solving array problems, the goal is not memorization,  
but identifying structure and applying logical reasoning.

A practical framework:

1️⃣ Clarify constraints  
   - Is the array sorted?  
   - Are duplicates allowed?  
   - What are the input limits?  

2️⃣ Identify the category  
   - Pair/triplet search → Two Pointers / Hashing  
   - Subarray condition → Sliding Window / Prefix Sum  
   - Maximum/minimum subarray → Kadane’s Algorithm  
   - Rearrangement → In-place technique  
   - Range query → Prefix computation  

3️⃣ Start simple  
   - Write a brute-force version  
   - Ensure correctness  

4️⃣ Optimize gradually  
   - Eliminate unnecessary nested loops  
   - Use auxiliary structures only when justified  
   - Aim for linear complexity where feasible  

5️⃣ Validate edge cases  
   - Empty input  
   - Single element  
   - All negative values  
   - Large datasets  

Arrays reward structured thinking, boundary awareness,  
and the ability to transform problems into simpler subproblems.

---

## 📁 Folder Structure

All array-related problems are organized inside:

```
01_Arrays/
```

## 🌟 Philosophy

> "Master arrays first.  
> Most advanced data structures build upon these fundamentals."  
> — Shankar