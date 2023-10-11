## Problem Statement
There is an integer array `A` sorted in `non-decreasing order` (not necessarily with distinct values).

Before being passed to your function, `A` is rotated at an unknown pivot index k (0 <= k < `A`.length) such that the resulting array is [`A`[k], `A`[k+1], ..., `A`[n-1], `A`[0], `A`[1], ..., `A`[k-1]] (0-indexed). For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].

Given the array `A` after the rotation and an integer `target`, `return true if target is in A, or false if it is not in A`.

You must decrease the overall operation steps as much as possible.

> ### Example 1:
> **`Input:`** `A` = [2, 5, 6, 0, 0, 1, 2], target = 0 <br>
> **`Output:`** true <br><br>

**`Constraints:`**
- 1 <= `A`.length <= 5000
- -10^4 <= `A`[i] <= 10^4
- `A` is guaranteed to be rotated at some pivot.
- -10^4 <= target <= 10^4

## Solutions
### Approach 1 - Linear Search

```java
class Solution {
    public boolean search(int[] A, int target) {
        int N = A.length;

        for (int i = 0; i < N; i++) {
            if (A[i] == target) return true;
        }

        return false;
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(1)

---

### Approach 2 - Binary Search

```java
class Solution {
    public boolean search(int[] A, int target) {
        int N = A.length;
        int left = 0, right = N - 1, mid;

        while (left <= right) {
            mid = left + (right - left) / 2;

            if (A[mid] == target) return true;

            if (A[mid] == A[left] && A[mid] == A[right]) {
                left++;
                right--;
            }
            else if (A[mid] >= A[left]) {
                if (target > A[mid] || target < A[left]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            else {
                if (target < A[mid] || target >= A[left]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }

        return false;
    }
}
```

**`Time Complexity:`** O(logN) <br>
**`Space Complexity:`** O(1)

---