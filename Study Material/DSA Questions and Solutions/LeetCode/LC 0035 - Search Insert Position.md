## Problem Statement
Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You must write an algorithm with O(log n) runtime complexity.

> ### Example 1:
> **`Input:`** nums = [1,3,5,6], target = 5 <br>
> **`Output:`** 2 <br><br>

**`Constraints:`**
- 1 <= nums.length <= 10^4
- -10^4 <= nums[i] <= 10^4
- nums contains distinct values sorted in ascending order.
- -10^4 <= target <= 10^4

## Solutions
### Approach 1 - Linear Search

```java
class Solution {
    public int searchInsert(int[] A, int B) {
        int N = A.length, i;
        for (i = 0; i < N; i++)
            if (A[i] >= B) break;
        return i;
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(1)

---

### Approach 2 - Binary Search

```java
class Solution {
    public int searchInsert(int[] A, int B) {
        int N = A.length, result = 0;
        int left = 0, right = N - 1, mid;

        if (B < A[0]) return 0;
        if (B > A[N - 1]) return N;

        while (left <= right) {
            mid = left + (right - left) / 2;

            if (A[mid] == B) return mid;

            if (A[mid] > B) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }
}
```

**`Time Complexity:`** O(logN) <br>
**`Space Complexity:`** O(1)

---