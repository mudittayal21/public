## Problem Statement
You are given an integer array `height` of length `N`. There are `N` vertical lines drawn such that the two endpoints of the ith line are `(i, 0) and (i, height[i])`.

Find two lines that together with the x-axis form a container, such that the container contains the most water. Return the maximum amount of water a container can store.

![Example 1 Representation](./images/LC_11.jpg)

> ### Example 1
> **`Input:`** height = [1, 8, 6, 2, 5, 4, 8, 3, 7] <br>
> **`Output:`** 49 <br>
> **`Explanation:`** <br> The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49. <br><br>

**`Constraints:`**
- N == height.length
- 2 <= N <= 105
- 0 <= height[i] <= 104

## Solutions

### Approach 1 - Brute Force
`Using Two For Loops`

```java
class Solution {
    public int maxArea(int[] height) {
        int N = height.length, result = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                result = Math.max(
                    result,
                    Math.min(height[i], height[j]) * (j - i)
                );
            }
        }

        return result;
    }
}
```

**`Time Complexity:`** O(N^2) <br>
**`Space Complexity:`** O(1)

---

### Approach 2 - Two Pointers
Using two pointers we would be placing them at the below mentioned indexes.

```java
int i = 0, j = N - 1;
```

Next, with every pointer position we would be calulating the maximum area between the ith and the jth pillars. <br> Then moving the smaller pillar towards the larger one.

```java
class Solution {
    public int maxArea(int[] height) {
        int N = height.length, result = 0;
        int i = 0, j = N - 1;

        while (i < j) {
            result = Math.max(
                result,
                Math.min(height[i], height[j]) * (j - i)
            );

            if (height[i] <= height[j]) i++;
            else j--;
        }

        return result;
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(1)

---