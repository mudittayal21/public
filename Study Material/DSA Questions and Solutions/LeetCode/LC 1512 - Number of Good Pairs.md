## Problem Statement
Given an array of integers nums, return the number of `good pairs`. <br>
A pair `(i, j)` is called good if `nums[i] == nums[j] and i < j`.

> ### Example 1:
> **`Input:`** nums = [1,2,3,1,1,3] <br>
> **`Output:`** 4 <br>
> **`Explanation:`** There are 4 good pairs (0,3), (0,4), (3,4), (2,5) 0-indexed. <br><br>

**`Constraints:`**
1 <= nums.length <= 100
1 <= nums[i] <= 100

## Solutions

### Approach 1 - Using two FOR LOOPS

```java
class Solution {
    public int numIdenticalPairs(int[] A) {
        int N = A.length, count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = (i + 1); j < N; j++) {
                if (A[i] == A[j]) count++;
            }
        }

        return count;
    }
}
```

**`Time Complexity:`** O(N ^ 2) <br>
**`Space Complexity:`** O(1)

---

### Approach 2 - Using a Count Array Helper

```java
class Solution {
    public int numIdenticalPairs(int[] A) {
        int N = A.length, result = 0;
        int[] count = new int[101];

        for (int i = 0; i < N; i++)
            count[A[i]]++;

        for (int i = 1; i < 101; i++) {
            if (count[i] <= 1) continue;

            int current = count[i];
            result += (current * (current - 1)) / 2;
        }

        return result;
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(1)

---