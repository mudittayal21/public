## Problem Statement
Given an integer N, return the number of structurally unique BST's (binary search trees) which has exactly N nodes of unique values from 1 to N.

> ### Example 1:
> ![Emaple 1](./images/LC_0096.jpg)
> **`Input:`** N = 3 <br>
> **`Output:`** 5 <br><br>

**`Constraints:`**
- 1 <= n <= 19

## Solutions
### Approach 1 - Using Recursion

```java
class Solution {
    public int numTrees(int N) {
        if (N <= 1) return 1;
        
        int result = 0;
        for (int i = 1; i <= N; i++) {
            result += numTrees(i - 1) * numTrees(N - i);
        }

        return result;
    }
}
```

**`Time Complexity:`** O(N ^ 2) <br>
**`Space Complexity:`** O(N)

---

### Approach 2 - Using Recursion and Memoisation

```java
class Solution {
    int[] dp = new int[20];

    public int numTrees(int N) {
        if (N <= 1) return 1;
        if (dp[N] > 0) return dp[N];

        int result = 0;
        for (int i = 1; i <= N; i++) {
            result += numTrees(i - 1) * numTrees(N - i);
        }

        return dp[N] = result;
    }
}
```

**`Time Complexity:`** O(N ^ 2) <br>
**`Space Complexity:`** O(N)

---