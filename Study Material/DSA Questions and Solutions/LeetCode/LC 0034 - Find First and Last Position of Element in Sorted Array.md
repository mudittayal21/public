## Problem Statement
Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.

If target is not found in the array, return [-1, -1].

You must write an algorithm with O(log n) runtime complexity.

> ### Example 1:
> **`Input:`** <br> nums = [5,7,7,8,8,10], target = 8 <br>
> **`Output:`** <br> [3,4] <br><br>

**`Constraints:`**
- 0 <= nums.length <= 10^5
- -10^9 <= nums[i] <= 10^9
- nums is a non-decreasing array.
- -10^9 <= target <= 10^9

## Solutions
### Approach 1 - Linear Search

```java
class Solution {
    public int[] searchRange(int[] A, int B) {
        int N = A.length, start = -1, end = -1;

        for (int i = 0; i < N; i++) {
            if (A[i] > B) break;

            if (A[i] == B) {
                if (start == -1) start = i;
                end = i;
            }
        }

        return new int[] {start, end};
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(1)

--- 

### Approach 2 - Binary Search

```java
class Solution {
    public int[] searchRange(int[] A, int B) {
        int start = -1, end = -1;

        start = findBound(A, B, 'l');
        end = findBound(A, B, 'u');

        return new int[] {start, end};
    }

    int findBound(int[] A, int B, int type) {
        int N = A.length;
        int left = 0, right = N - 1, mid;
        int result = -1;

        while (left <= right) {
            mid = left + (right - left) / 2;

            if (A[mid] == B) {
                result = mid;

                if (type == 'l')
                    right = mid - 1;
                else
                    left = mid + 1;
            }
            else if (A[mid] > B)
                right = mid - 1;
            else 
                left = mid + 1;
        }

        return result;
    }
}
```

**`Time Complexity:`** O(logN) <br>
**`Space Complexity:`** O(1)

---