## Problem Statement
Given an array of integers `heights` representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the `histogram`.

> ### Example 1:
> **`Input:`** heights = [2,1,5,6,2,3] <br>
> **`Output:`** 10 <br>
> **`Explanation:`** <br>
> The above is a histogram where width of each bar is 1. <br>
> The largest rectangle is shown in the red area, which has an area = 10 units. <br><br>

**`Constraints:`**
- 1 <= heights.length <= 10^5
- 0 <= heights[i] <= 10^4

## Solutions
### Approach 1 - Using 3 for loops

```java
class Solution {
    public int largestRectangleArea(int[] A) {
        int N = A.length, result = 0, min;
        
        for (int start = 0; start < N; start++) {
            for (int end = start; end < N; end++) {
                min = Integer.MAX_VALUE;
                
                for (int i = start; i <= end; i++)
                    min = Math.min(min, A[i]);

                result = Math.max(result, min * (end - start + 1));
            }
        }

        return result;
    }
}
```

**`Time Complexity:`** O(N^3) <br>
**`Space Complexity:`** O(1)

---
### Approach 2 - Using 2 for loops

```java
class Solution {
    public int largestRectangleArea(int[] A) {
        int N = A.length, result = 0;
        int min;
        
        for (int start = 0; start < N; start++) {
            min = Integer.MAX_VALUE;
            for (int end = start; end < N; end++) {
                min = Math.min(min, A[end]);
                result = Math.max(result, min * (end - start + 1));
            }
        }

        return result;
    }
}
```

**`Time Complexity:`** O(N^2) <br>
**`Space Complexity:`** O(1)

---
### Approach 3 - Divide and Conquer

With this approach, we would be finding the index where we have the minimum value. We would then be breaking the array into two parts and calculating the `MAX Area` separately.

```java
class Solution {
    public int largestRectangleArea(int[] A) {
        int N = A.length;
        return calculate(A, 0, N - 1);
    }

    int calculate(int[] A, int start, int end) {
        if (start > end) return 0;

        int min = start;
        for (int i = start; i <= end; i++)
            if (A[i] < A[min])
                min = i;

        return Math.max(
            A[min] * (end - start + 1),
            Math.max(
                calculate(A, start, min - 1),
                calculate(A, min + 1, end)
            )
        );
    }
}
```

**`Time Complexity:`** O(NlogN) <br>
**`Space Complexity:`** O(N), recursion stack space

---
### Approach 4 - Using Stacks

```java
class Solution {
    public int largestRectangleArea(int[] A) {
        int N = A.length;
        int height, width, result = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);

        for (int i = 0; i < N; i++) {
            while (stack.peek() != -1 && A[i] <= A[stack.peek()]) {
                height = A[stack.pop()];
                width = i - stack.peek() - 1;

                result = Math.max(result, height * width);
            }
            stack.push(i);
        }

        while (stack.peek() != -1) {
            height = A[stack.pop()];
            width = N - stack.peek() - 1;

            result = Math.max(result, height * width);
        }

        return result;
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(N)

---
### Approach 5 - Using Nearest Smallest

Similar to the above approach, this is a more descriptive version of the same. However, a little bad at the Overall Time Complexity, since this approach requires two Nearest Smaller arrays to be created.

```java
class Solution {
    public int largestRectangleArea(int[] A) {
        int N = A.length, result = 0;
        int height, width;
        int[] nsl = new int[N], nsr = new int[N];

        nearestSmallestLeft(A, nsl);
        nearestSmallestRight(A, nsr);

        for (int i = 0; i < N; i++) {
            height = A[i];
            width = nsr[i] - nsl[i] - 1;

            result = Math.max(result, height * width);
        }

        return result;
    }

    void nearestSmallestLeft(int[] A, int[] nsl) {
        int N = A.length;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            if (stack.isEmpty()) nsl[i] = -1;
            else {
                while (!stack.isEmpty() && A[stack.peek()] >= A[i]) stack.pop();

                if (stack.isEmpty()) nsl[i] = -1;
                else nsl[i] = stack.peek();
            }

            stack.push(i);
        }
    }

    void nearestSmallestRight(int[] A, int[] nsr) {
        int N = A.length;
        Stack<Integer> stack = new Stack<>();

        for (int i = (N - 1); i >= 0; i--) {
            if (stack.isEmpty()) nsr[i] = N;
            else {
                while (!stack.isEmpty() && A[stack.peek()] >= A[i]) stack.pop();

                if (stack.isEmpty()) nsr[i] = N;
                else nsr[i] = stack.peek();
            }

            stack.push(i);
        }
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(N)

---