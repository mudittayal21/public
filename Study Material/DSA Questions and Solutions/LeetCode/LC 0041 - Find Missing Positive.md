## Problem Statement
Given an unsorted integer array nums, return the smallest missing positive integer. <br>
You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary space.

> ### Example 1:
> **`Input:`** nums = [1,2,0] <br>
> **`Output:`** 3 <br>
> **`Explanation:`** The numbers in the range [1,2] are all in the array. <br><br>

**`Constraints:`**
- 1 <= nums.length <= 105
- -231 <= nums[i] <= 231 - 1

## Solutions
### Approach 1 - Using 2 for loops
The first for loop would be used to iterate over all the positive integers in the range `[1, N]`. <br>
The second for loop would be used to iterate over the elements of the array to check if the ith positive integer exist in the array or not.

```java
class Solution {
    public int firstMissingPositive(int[] A) {
        int N = A.length;
        boolean isFound = false;

        for (int i = 1; i <= N; i++) {
            isFound = false;

            for (int j = 0; j < N; j++) {
                if (A[j] == i) {
                    isFound = true;
                    break;
                }
            }

            if (!isFound) return i;
        }

        return N + 1;
    }
}
```

**`Time Complexity:`** O(N^2) <br>
**`Space Complexity:`** O(1)

---

### Approach 2 - Sorting the Data

In this approach, we have initialised the result with the first positive integer, `i.e. 1`. After the input array has been sorted, we would iterate all the elements of the array.

Any element less than 1 (result) would be ignore, since they are 0 or -ve numbers, which are out of scope for the given problem statement. <br>
Now, in the second for loop we would be comparing the ith element with the result. `Everytime, the comparison returns true the result variable is incremented`.

The first if statement will also handle the duplicate elements in the array, so suppose if we have the below input, the `first 1` would be compared with `result = 1`, after which 1 gets incremented to `result = 2` against which all the remaining 1's would be compared and ignored as per the first if condition.

```java
class Solution {
    public int firstMissingPositive(int[] A) {
        int N = A.length, result = 1;
        Arrays.sort(A);

        for (int i = 0; i < N; i++) {
            if (A[i] < result) continue;
            if (A[i] != result++) return --result;
        }

        return result;
    }
}
```

**`Time Complexity:`** O(NlogN) <br>
**`Space Complexity:`** O(1)

---

### Approach 3 - Swapping Elements

With this approach, we would be moving all the elements at there correct position. The position would be given by

`A[index] == index + 1`

We would be ignoring all the elements that are either less than 1 or more than N.

```java
class Solution {
    public int firstMissingPositive(int[] A) {
        int N = A.length, current;

        for (int i = 0; i < N; i++) {
            if (A[i] < 1 || A[i] > N) continue;
            
            current = A[i];
            if (A[current - 1] == current) continue;

            A[i] = A[current - 1];
            A[current - 1] = current;

            i--;
        }

        for (int i = 0; i < N; i++)
            if (A[i] != (i + 1))
                return (i + 1);

        return N + 1;
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(1)

---