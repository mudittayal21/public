## Problem Statement
You are climbing a staircase. It takes N steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

> ### Example 1:
> **`Input:`** N = 2 <br>
> **`Output:`** 2 <br>
> **`Explanation:`** <br>
> There are two ways to climb to the top. <br>
> 1. 1 step + 1 step
> 1. 2 steps <br><br>

**`Constraints:`**
- 1 <= n <= 45

## Solutions
### Approach 1 - Recursion

```java
class Solution {
    int count = 0;
    public int climbStairs(int N) {
        check(N);
        return count;
    }

    void check(int N) {
        if (N == 0) count++;
        if (N <= 0) return;

        check(N - 1);
        check(N - 2);
    }
}
```
`OR`
```java
class Solution {
    public int climbStairs(int N) {
        if (N < 2) return 1;
        else return climbStairs(N - 1) + climbStairs(N - 2);        
    }
}
```

**`Time Complexity:`** O(2 ^ N) <br>
**`Space Complexity:`** O(N), Recursive Stack Space

---
### Approach 2 - Bottom Up - Backtracking

In the above approach, the sub problems repeats itself. Therefore, the same can be improved using Dynamic Programming.

`Memoisation`
```java
class Solution {
    int[] memo;
    public int climbStairs(int N) {
        memo = new int[N + 1];
        Arrays.fill(memo, -1);

        return countWays(N);
    }

    int countWays(int N) {
        if (memo[N] != -1) return memo[N];

        if (N <= 1) return memo[N] = 1;
        else return memo[N] = countWays(N - 1) + countWays(N - 2);
    }
}
```

`Iterative DP`
```java
class Solution {
    public int climbStairs(int N) {
        int[] dp = new int[N + 1];
        dp[0] = dp[1] = 1;

        for (int i = 2; i <= N; i++)
            dp[i] = dp[i - 1] + dp[i - 2];

        return dp[N];
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(N)

---
### Approach 3 - Fibonacci
Since with the approach, for any N we are only concerned about the number of ways to reach (N - 1) and (N - 2), these values can be held in variables instead of the array.

```java
class Solution {
    public int climbStairs(int N) {
        if (N <= 1) return 1;

        int A = 1, B = 1;
        int result = 0;

        for (int i = 2; i <= N; i++) {
            result = A + B;
            A = B;
            B = result;
        }

        return result;
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(1)

---