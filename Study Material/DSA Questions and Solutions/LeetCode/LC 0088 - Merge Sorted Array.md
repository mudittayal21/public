## Problem Statement
You are given two integer arrays `A` and `B`, sorted in non-decreasing order, and two integers `N` and `M`, representing the number of elements in `A` and `B` respectively.

Merge `A` and `B` into a single array sorted in non-decreasing order.

The final sorted array should not be returned by the function, but instead be stored inside the array `A`. To accommodate this, `A` has a length of `N` + n, where the first `N` elements denote the elements that should be merged, and the last `M` elements are set to 0 and should be ignored. `B` has a length of n.

> ### Example 1:
> **`Input:`** `A` = [1,2,3,0,0,0], `N` = 3, `B` = [2,5,6], `M` = 3 <br>
> **`Output:`** [1,2,2,3,5,6] <br><br>

**`Constraints:`**
- `A`.length == `N` + `M`
- `B`.length == `M`
- 0 <= `N`, `M` <= 200
- 1 <= `N` + `M` <= 200
- -10^9 <= `A`[i], `B`[j] <= 10^9

## Solutions
### Approach 1 - Using a Result Array

```java
class Solution {
    public void merge(int[] A, int N, int[] B, int M) {
        int[] result = new int[N + M];
        int i = 0, j = 0, index = 0;

        if (M == 0) return;

        while (i < N && j < M) {
            if (B[j] < A[i]) result[index++] = B[j++];
            else result[index++] = A[i++];
        }

        while (i < N) result[index++] = A[i++];
        while (j < M) result[index++] = B[j++];

        for (index = 0; index < (N + M); index++)
            A[index] = result[index];
    }
}
```

**`Time Complexity:`** O(N + M) <br>
**`Space Complexity:`** O(N + M)

---
### Approach 2 - In-place replacing

```java
class Solution {
    public void merge(int[] A, int N, int[] B, int M) {
        int index = (N + M - 1), i = (N - 1), j = (M - 1);

        while (i >= 0 || j >= 0) {
            int X = (i >= 0) ? A[i] : Integer.MIN_VALUE;
            int Y = (j >= 0) ? B[j] : Integer.MIN_VALUE;

            if (X <= Y) {
                A[index--] = Y;
                j--;
            } else {
                A[index--] = X;
                i--;
            }
        }
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(1)

---