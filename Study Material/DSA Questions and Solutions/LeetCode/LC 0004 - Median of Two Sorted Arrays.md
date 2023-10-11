## Problem Statement
Given `two sorted arrays A and B` of size N and M respectively, return the median of the two sorted arrays. The overall run time complexity should be `O(log (m+n))`.

> ### Example 1:
> 
> **`Input:`** A = [1,3], B = [2] <br>
> **`Output:`** 2.00000 <br>
> **`Explanation:`** Merged Array = [1,2,3] and median is 2.


> ### Example 2:
>
> **`Input:`** A = [1,2], B = [3,4] <br>
> **`Output:`** 2.50000 <br>
> **`Explanation:`** Merged Array = [1,2,3,4] and Median is (2 + 3) / 2 = 2.5.

**`Constraints:`**
- nums1.length == m
- nums2.length == n
- 0 <= m <= 1000
- 0 <= n <= 1000
- 1 <= m + n <= 2000
- -106 <= nums1[i], nums2[i] <= 106

## Solutions

### Approach 1 - Brute Force
**`Merging the Two Arrays, followed by sorting the newly created array`**

There are two ways the data can be merged.

`1. Dumping all the data in the new array and then sorting the same.`

``` java
class Solution {
    public double findMedianSortedArrays(int[] A, int[] B) {
        int N = A.length, M = B.length, Z = N + M, index = 0;
        int[] C = new int[Z];

        for (int i = 0; i < N; i++) C[index++] = A[i];
        for (int i = 0; i < M; i++) C[index++] = B[i];

        Arrays.sort(C);

        if ((Z & 1) == 1) return (double) C[Z >> 1];
        else return (double) ((C[Z >> 1] + C[(Z >> 1) - 1]) / 2.0);
    }
}
```

**`Time Complexity:`** <br>
O(N + M + (N + M) * log(N + M)) <br>
= O((N + M) * log(N + M))

**`Space Complexity:`** O(N + M)

`2. Dumping the data in the new array while comparing the two sorted arrays.`

``` java
class Solution {
    public double findMedianSortedArrays(int[] A, int[] B) {
        int N = A.length, M = B.length, Z = N + M;
        int i = 0, j = 0, index = 0;
        int[] C = new int[Z];

        while (i < N && j < M) {
            if (A[i] <= B[j]) C[index++] = A[i++];
            else C[index++] = B[j++];
        }

        while (i < N) C[index++] = A[i++];
        while (j < M) C[index++] = B[j++];

        if ((Z & 1) == 1) return (double) C[Z >> 1];
        else return (double) ((C[Z >> 1] + C[(Z >> 1) - 1]) / 2.0);
    }
}
```

**`Time Complexity:`** O(N + M) <br>
**`Space Complexity:`** O(N + M)

---

### Approach 2 - Using Partitions - Binary Search

Consider the input arrays as `A` and `B` of length `N` and `M respectively`. For this approach we would be keeping A and B in such a way that `N < M`. <br>
Let us consider the below given inputs:

`A = [1, 2, 3, 4, 5]` <br>
`B = [1, 2, 3, 4, 5, 6, 7, 8]`

The merged array would look as mentioned below with 4 as the Median. <br>
`[1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 7, 8]`

By definition, a median would roughly have `(N + M) / 2` elements on the either side. <br>
Thus, taking the smaller array as the reference for binary search, we will calculate the partition for array A and accordingly calculate the partition for array B.

``` java
int start = 0, end = num1.length;
while (start <= end) {
    partition1 = (start + end) / 2;
    partition2 = ((N + M + 1) / 2) - partition1;

    ...
```

`Checking if the correct partitions were obtained.` <br>
**`Case 1: When left1 > right2`** <br>
Then the partition1 should move towards the smaller elements. <br>
Therefore,
``` java
end = partition1 - 1;
```

**`Case 2: When left2 > right1`** <br>
Then the partition1 should move towards the larger elements. <br>
Therefore,
``` java
start = partition1 + 1;
```

**`Case 3: When left2 < right1 && left1 < right2`** <br>
A correct partition has been obtained. <br>
Therefore,
``` java
if ((n1+n2)%2 == 0) {
    return (
        (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0
    );
} else {
    return Math.max(left1, left2);
}
```

**`Putting it all together`**

```java
class Solution {
    public double findMedianSortedArrays(int[] num1, int[] num2) {
        if (num1.length > num2.length) {
            return findMedianSortedArrays(num2, num1);
        }
        
        int start = 0, end = num1.length, n1 = num1.length, n2 = num2.length, partition1, partition2, left1, left2, right1, right2;
        while (start <= end) {
            partition1 = (start + end) / 2;
            partition2 = ((n1 + n2 + 1) / 2) - partition1;
            
            left1 = partition1 == 0 ? Integer.MIN_VALUE : num1[partition1 - 1];
            left2 = partition2 == 0 ? Integer.MIN_VALUE : num2[partition2 - 1];
            
            right1 = partition1 == n1 ? Integer.MAX_VALUE : num1[partition1];
            right2 = partition2 == n2 ? Integer.MAX_VALUE : num2[partition2];
            
            if (left1 <= right2 && left2 <= right1) {
                if ((n1+n2)%2 == 0) {
                    return ((Math.max(left1, left2) + Math.min(right1, right2)) / 2.0);
                } else {
                    return Math.max(left1, left2);
                }
            } else if (left2 > right1) {
                start = partition1 + 1;
            } else {
                end = partition1 - 1;
            }
        }
        return 0.0;
    }
}
```

**`Time Complexity:`** O(log(N + M)) <br>
**`Space Complexity:`** O(1)

---