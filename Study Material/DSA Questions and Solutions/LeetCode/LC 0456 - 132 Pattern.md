## Problem Statement
Given an array of `n` integers nums, a **`132 pattern`** is a subsequence of three integers `nums[i]`, `nums[j]` and `nums[k]` such that `i < j < k` and `nums[i] < nums[k] < nums[j]`.

Return true if there is a `132 pattern` in nums, otherwise, return `false`.

> ### Example 1:
> **`Input:`** nums = [1, 2, 3, 4] <br>
> **`Output:`** false <br>
> **`Explanation:`** There is no 132 pattern in the sequence. <br><br>

**`Constraints:`**
- n == nums.length
- 1 <= n <= 2 * 10^5
- -10^9 <= nums[i] <= 10^9

## Solutions

### Approach 1 - Using 3 for loops

```java
class Solution {
    public boolean find132pattern(int[] A) {
        int N = A.length;

        for (int i = 0; i < N; i++) {
            for (int j = (i + 1); j < N; j++) {
                for (int k = (j + 1); k < N; k++) {
                    if (A[i] < A[k] && A[k] < A[j]) return true;
                }
            }
        }

        return false;
    }
}
```

**`The above solution would throw a TLE error.`**

**`Time Complexity:`** O(N ^ 3) <br>
**`Space Complexity:`** O(1)

---

### Approach 2 - Using 2 for loops

```java
class Solution {
    public boolean find132pattern(int[] A) {
        int N = A.length;

        for (int i = 2; i < N; i++) {
            boolean iFound = false, jFound = false;

            for (int j = 0; j < i; j++) {
                if (A[j] < A[i]) iFound = true;
                if (A[j] > A[i]) jFound = true;

                if (iFound && jFound) return true;
            }
        }

        return false;
    }
}
```

**`Time Complexity:`** O(N ^ 2) <br>
**`Space Complexity:`** O(1)

---

### Approach 3 - Using Stacks

```java
class Solution {
    public boolean find132pattern(int[] A) {
        int N = A.length;
        int min = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();

        for (int i = (N - 1); i >= 0; i--) {
            if (A[i] < min) return true;

            while (!stack.isEmpty() && stack.peek() < A[i])
                min = stack.pop();

            stack.push(A[i]);
        }

        return false;
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(N)

---