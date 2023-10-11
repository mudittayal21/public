## Problem Statement
You are given an integer array `A`. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.

Return `true` if you can reach the last index, or `false` otherwise.

> ### Example 1:
> **`Input:`** nums = [2,3,1,1,4] <br>
> **`Output:`** true <br>
> **`Explanation:`** Jump 1 step from index 0 to 1, then 3 steps to the last index. <br><br>

**`Constraints:`**
- 1 <= nums.length <= 10^4
- 0 <= nums[i] <= 10^5

## Solutions

### Approach 1 - Brute Force - Recursion - Memoisation

```java
class Solution {
    int[] memo;

    public boolean canJump(int[] A) {
        int N = A.length;

        memo = new int[N];
        Arrays.fill(memo, -1);
        return check(A, 0);
    }

    boolean check(int[] A, int index) {
        if ((index + 1) >= A.length) return true;
        if (memo[index] != -1) return memo[index] == 1;

        int current = A[index];

        if (current == 0)
            memo[index] = 0;
        else {
            for (int i = current; i >= 1; i--) {
                if (check(A, index + i)) {
                    memo[index] = 1;
                    break;
                }
            }
        }

        return memo[index] == 1;
    }
}
```

**`Time Complexity:`** O(N ^ K), where K is the sum of all the elements <br>
**`Space Complexity:`** O(N) - Recursion Call Stack

---

### Approach 2 - Greedy - Linear Solution

With this approach, we would be finding the result by checking how far we can go from every element. At any position if the current index is more than the farthest parameter, that means that particular element cannot be accessed by the previous jumps at all.

```java
class Solution {
    public boolean canJump(int[] A) {
        int N = A.length, farthest = 0;
        
        for (int i = 0; i <= farthest && i < N; i++) {
            farthest = Math.max(farthest, i + A[i]);
            if (farthest >= (N - 1)) return true;
        }

        return false;
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(1)

---