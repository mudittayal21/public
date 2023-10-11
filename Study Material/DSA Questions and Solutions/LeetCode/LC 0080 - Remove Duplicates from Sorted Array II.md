## Problem Statement
Given an integer array `nums` sorted in `non-decreasing order`, remove some duplicates `in-place` such that each unique element appears `at most twice`. The `relative order` of the elements should be kept the `same`.

Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the `first part` of the array `nums`. More formally, if there are `k` elements after removing the duplicates, then the `first k elements` of nums should hold the final result. It does not matter what you leave beyond the first k elements.

`Return k after placing the final result in the first k slots of nums.`

> ### Example 1:
> **`Input:`** nums = [1,1,1,2,2,3] <br>
> **`Output:`** 5, nums = [1,1,2,2,3,_] <br>
> **`Explanation:`** <br>
Your function should return k = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores). <br><br>

**`Constraints:`**
- 1 <= nums.length <= 3 * 10^4
- -10^4 <= nums[i] <= 10^4
- nums is sorted in non-decreasing order.

## Solutions
### Approach 1 - Using Pointers

```java
class Solution {
    public int removeDuplicates(int[] A) {
        int N = A.length, j = 0;

        for (int i = 0; i < N; i++) {
            if (j < 2 || A[i] != A[j - 2]) {
                A[j] = A[i];
                j++;
            }
        }

        return j;
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(1)

---