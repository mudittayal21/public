## Problem Statement
Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.

Consider the number of elements in nums which are not equal to val be k, to get accepted, you need to do the following things:

- Change the array nums such that the first k elements of nums contain the elements which are not equal to val. The remaining elements of nums are not important as well as the size of nums.
- Return k.

> ### Example 1:
> **`Input:`** <br> nums = [3,2,2,3], val = 3 <br>
> **`Output:`** <br> 2, nums = [2,2,_,_] <br>
> **`Explanation:`** <br> Your function should return k = 2, with the first two elements of nums being 2. <br>
It does not matter what you leave beyond the returned k (hence they are underscores). <br><br>

**`Constraints:`**
- 0 <= nums.length <= 100
- 0 <= nums[i] <= 50
- 0 <= val <= 100

## Solutions
### Approach 1 - Using a Second Array

```java
class Solution {
    public int removeElement(int[] A, int B) {
        int N = A.length, index = 0;
        int[] temp = new int[N];

        for (int i = 0; i < N; i++) {
            if (A[i] != B) temp[index++] = A[i];
        }

        for (int i = 0; i < index; i++) {
            A[i] = temp[i];
        }

        return index;
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(N)

---

### Approach 2 - Using Pointers

Whenever the element B is found in the array, the same is swapped with the pointer `index` (which is initialised as the last array index). Thus, after one whole iteration gets completed all the occurances of the array would move to the end.

```java
class Solution {
    public int removeElement(int[] A, int B) {
        int N = A.length, index = 0;

        for (int i = 0; i < N; i++) {
            if (A[i] != B) {
                A[index++] = A[i];
            }
        }

        return index;
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(1)

---