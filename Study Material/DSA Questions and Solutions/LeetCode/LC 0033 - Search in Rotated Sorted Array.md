## Problem Statement
There is an integer array `A` sorted in ascending order (with distinct values).

Prior to being passed to your function, `A` is **possibly rotated** at an unknown `pivot index k (1 <= k < nums.length)` such that the resulting array is `[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed)`. For example, `[0, 1, 2, 4, 5, 6, 7]` might be rotated at pivot index 3 and become `[4, 5, 6, 7, 0, 1, 2]`.

Given the array nums after the possible rotation and an integer `target`, return the index of `target` if it is in nums, or -1 if it is not in nums.

You must write an algorithm with `O(log n)` runtime complexity.

> ### Example 1:
> **`Input:`** <br> nums = [4,5,6,7,0,1,2], target = 0 <br>
> **`Output:`** <br> 4 <br><br>

**`Constraints:`**
- 1 <= nums.length <= 5000
- -10^4 <= nums[i] <= 10^4
- All values of nums are unique.
- nums is an ascending array that is possibly rotated.
- -10^4 <= target <= 10^4

## Solutions
### Approach 1 - Linear Search

```java
class Solution {
    public int search(int[] A, int B) {
        int N = A.length;

        for (int i = 0; i < N; i++) {
            if (A[i] == B) return i;
        }

        return -1;
    }
}
```

Time Complexity: O(N) <br>
Space Complexity: O(1)

---

### Approach 2 - Binary Search

Using Binary Search, our cases would be as follows:

```java
class Solution {
    public int search(int[] A, int B) {
        int N = A.length;
        int left = 0, right = N - 1, mid;

        while (left <= right) {
            mid = left + (right - left) / 2;

            if (A[mid] == B) return mid;

            // Searching in the first part of the Array.
            // All increasing elements.
            if (A[mid] >= A[0]) {
                // The value does not lie in the current search space.
                if (B < A[0] || A[mid] < B)
                    left = mid + 1;
                // The value lies in the current space.
                else
                    right = mid - 1;
            }
            // Searching in the second part of the array.
            // The pivot lies in this search space.
            // Therefore contains all increasing and decreasing sequence.
            else {
                // The value does not lie in the current search space.
                if (B < A[mid] || A[0] <= B) 
                    right = mid - 1;
                // The value lies in the current search space.
                else
                    left = mid + 1;
            }
        }

        return -1;
    }
}
```

**`Time Complexity:`** O(logN) <br>
**`Space Complexity:`** O(1)

---