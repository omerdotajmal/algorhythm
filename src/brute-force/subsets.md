## Bitmasking for Subset Generation (Brute Force)

To generate all subsets of a set with **n** elements, we use this idea:

- **Total subsets = 2^n**
- For each subset, we create an **n-bit binary number (mask)**.
- **Each bit decides include/exclude** one element.

---

### Step 1: Index the elements

Let the set be:

- `A = {1, 2, 3}`

Assign indices:

- `A[0] = 1`
- `A[1] = 2`
- `A[2] = 3`

---

### Step 2: What does a mask mean?

A mask is a **3-bit** binary number (because `n = 3`):

- **Bit 0** (rightmost) controls `A[0] = 1`
- **Bit 1** controls `A[1] = 2`
- **Bit 2** controls `A[2] = 3`

**Rule:**

- If bit `i = 1` → **include** `A[i]`
- If bit `i = 0` → **exclude** `A[i]`

---

### Step 3: Enumerate masks from `0` to `2^n − 1`

Here, `n = 3`, so masks go from:

- `0` to `2^3 − 1 = 7`
- i.e., `0..7`

| Mask (decimal) | Mask (binary) | Include? (A[2] A[1] A[0]) | Subset    |
| -------------- | ------------- | ------------------------- | --------- |
| 0              | 000           | 0 0 0                     | {}        |
| 1              | 001           | 0 0 1                     | {1}       |
| 2              | 010           | 0 1 0                     | {2}       |
| 3              | 011           | 0 1 1                     | {1, 2}    |
| 4              | 100           | 1 0 0                     | {3}       |
| 5              | 101           | 1 0 1                     | {1, 3}    |
| 6              | 110           | 1 1 0                     | {2, 3}    |
| 7              | 111           | 1 1 1                     | {1, 2, 3} |

---

### Step 4: How do we “check” a bit in code?

We use:

```
(mask & (1 << i)) != 0
```

Meaning:

- `1 << i` creates a number where **only bit i is 1**
- `&` checks whether **mask’s bit i is set**

**Example: mask = 5 (binary 101)**

- `i = 0` → `(101 & 001) != 0` → include `A[0] = 1`
- `i = 1` → `(101 & 010) == 0` → exclude `A[1] = 2`
- `i = 2` → `(101 & 100) != 0` → include `A[2] = 3`

So subset = `{1, 3}`
