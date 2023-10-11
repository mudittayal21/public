## Problem Statement
Given an integer array nums, find the subarray with the largest sum, and return its sum.

> ### Example 1:
> **`Input:`** nums = [-2,1,-3,4,-1,2,1,-5,4] <br>
> **`Output:`** 6 <br>
> **`Explanation:`** The subarray [4,-1,2,1] has the largest sum 6. <br><br>

**`Constraints:`**
- 1 <= nums.length <= 10^5
- -10^4 <= nums[i] <= 10^4

## Solutions

### Approach 1 - Using 3 for loops

```java
class Solution {
    public int maxSubArray(int[] A) {
        int N = A.length, current, result = 0;

        for (int start = 0; start < N; start++) {
            for (int end = start; end < N; end++) {
                current = 0;

                for (int i = start; i <= end; i++) current += A[i];

                result = Math.max(result, current);
            }
        }

        return result;
    }
}
```

**`Time Complexity:`** O(N^3) <br>
**`Space Complexity:`** O(1)

---

### Approach 2 - Using Two For Loops and Prefix Sum

```java
class Solution {
    public int maxSubArray(int[] A) {
        int N = A.length, current, result = 0;
        int[] pf = new int[N + 1];

        // Creating the Prefix Sum Array - O(N)
        for (int i = 0; i < N; i++) pf[i + 1] = pf[i] + A[i];

        // Checking all the subarrays - O(N^2)
        for (int start = 0; start < N; start++) {
            for (int end = start; end < N; end++) {
                current = pf[end + 1] - pf[start];
                result = Math.max(result, current);
            }
        }

        return result;
    }
}
```

**`Time Complexity:`** O(N^2) <br>
**`Space Complexity:`** O(N)

---

### Approach 3 - Using 2 For Loops and Carry Over

```java
class Solution {
    public int maxSubArray(int[] A) {
        int N = A.length, current, result = 0;

        for (int start = 0; start < N; start++) {
            current = 0;

            for (int i = start; i < N; i++) {
                current += A[i];
                result = Math.max(result, current);
            }
        }

        return result;
    }
}
```

**`Time Complexity:`** O(N^2) <br>
**`Space Complexity:`** O(1)

---

### Approach 4 - Kadane's Algorithm

```java
class Solution {
    public int maxSubArray(int[] A) {
        int N = A.length, current = 0, result = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            if (current < 0) current = 0;
            current += A[i];

            result = Math.max(result, current);
        }

        return result;
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(1)

---