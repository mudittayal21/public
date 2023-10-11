## Problem Statement
Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same. Then return the number of unique elements in nums.

Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things:

- Change the array nums such that the first k elements of nums contain the unique elements in the order they were present in nums initially. The remaining elements of nums are not important as well as the size of nums.
- Return k.

**`Constraints:`**
- 1 <= nums.length <= 3 * 104
- -100 <= nums[i] <= 100
- nums is sorted in non-decreasing order.

## Solutions

### Approach 1
Creating a new array and copying all the unique elements to the array. Then in-place replacing the input array.

```java
class Solution {
    public int removeDuplicates(int[] A) {
        int N = A.length, prev = Integer.MIN_VALUE, index = 0;
        int[] temp = new int[N];

        for (int i = 0; i < N; i++) {
            if (prev != A[i]) temp[index++] = A[i];
            prev = A[i];
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

### Approach 2
In-place swapping the elements.

```java
class Solution {
    public int removeDuplicates(int[] A) {
        int N = A.length, index = 1, temp;

        for (int i = 1; i < N; i++) {
            if (A[i] != A[i - 1]) {
                A[index++] = A[i];
            }
        }

        return index;
    }
}
```

**`Time Complexity:`** O(N)
**`Space Complexity:`** O(1)

---