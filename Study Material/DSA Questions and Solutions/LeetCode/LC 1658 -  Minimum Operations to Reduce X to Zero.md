## Problem Statement
You are given an integer array **`nums`** and an integer **`x`**. In one operation, you can either remove the leftmost or the rightmost element from the array **`nums`** and subtract its value from **`x`**. Note that this **`modifies`** the array for future operations.

Return the **`minimum number of operations to reduce x to exactly 0`** if it is possible, otherwise, return **`-1`**.

> ### Example 1:
> **`Input:`** A = [1, 1, 4, 2, 3], B = 5 <br>
> **`Output:`** 2 <br>
> **`Explanation:`** The optimal solution is to remove the last two elements to reduce x to zero. <br><br>

**`Constraints:`**
- 1 <= A.length <= 10^5
- 1 <= A[i] <= 10^4
- 1 <= X <= 10^9

## Solutions

### Approach 1 - Using Recursion

```java
class Solution {
    public int minOperations(int[] A, int X) {
        int N = A.length;
        long result = calculate(A, X, 0, N - 1);

        return (result > Integer.MAX_VALUE) ? -1 : (int) result;
    }

    long calculate(int[] A, int X, int i, int j) {
        int N = A.length;
        
        if (X == 0) return 0;
        if (j < 0 || i >= N || i > j || X < 0) return Integer.MAX_VALUE;

        long left = calculate(A, X - A[i], i + 1, j);
        long right = calculate(A, X - A[j], i, j - 1);

        return 1 + Math.min(left, right);
    }
}
```

**`The above solution would throw a TLE error.`**

**`Time Complexity:`** O(2 ^ N) <br>
**`Space Complexity:`** O(1)

---

### Approach 2 - Using Recursion and Memoisation

```java
class Solution {
    long[][][] dp;

    public int minOperations(int[] A, int X) {
        int N = A.length;
        dp = new long[N][N][X + 1];

        long result = calculate(A, X, 0, N - 1);

        return (result > Integer.MAX_VALUE) ? -1 : (int) result;
    }

    long calculate(int[] A, int X, int i, int j) {
        int N = A.length;
        
        if (X == 0) return 0;
        if (j < 0 || i >= N || i > j || X < 0) return Integer.MAX_VALUE;

        if (dp[i][j][X] != 0) return dp[i][j][X];

        long left = calculate(A, X - A[i], i + 1, j);
        long right = calculate(A, X - A[j], i, j - 1);

        return dp[i][j][X] = 1 + Math.min(left, right);
    }
}
```

**`The above solution would throw a Memory Limit Exceeded error.`**

**`Time Complexity:`** O(2 ^ N) <br>
**`Space Complexity:`** O(1)

---

### Approach 3 - Using Sliding Window

With this approach, we would be **`transforming the current given problem to its Negation`**. <br>
Consider the below input:

```java
int[] nums = new int[] { 1, 1, 4, 2, 3 };
int target = 5;
```

Given that we can select any number of consecutive elements from the either sides of the array such that the sum is equal to the target.

```java
int sumOfTheArray = (1 + 1 + 4 + 2 + 3) = 11;
int targetInverse = 11 - 5 = 6;
```

Therefore, now we just need to find the longest subarray with sum equal to `targetInverse`. <br><br>

```java
class Solution {
    public int minOperations(int[] A, int target) {
        int N = A.length, result = Integer.MAX_VALUE;

        int targetInverse = - target;
        for (int i = 0; i < N; i++) targetInverse += A[i];

        if (target == 0) return N;
        if (target < 0) return -1;

        int left = 0, right = 0;
        int current = 0;

        while (right < N) {
            current += A[right++];

            while (current > targetInverse && left < right)
                current -= A[left++];

            if (current == targetInverse) {
                result = Math.min(result, N - (right - left));
            }
        }

        return (result == Integer.MAX_VALUE) ? -1 : result;
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(1)

---