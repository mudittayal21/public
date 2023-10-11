## Problem Statement
Given a non-negative integer x, return the square root of x rounded down to the nearest integer. The returned integer should be non-negative as well.

You must not use any built-in exponent function or operator.

For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.

> ### Example 1:
> **`Input:`** x = 4 <br>
> **`Output:`** 2 <br>
> **`Explanation:`** The square root of 4 is 2, so we return 2. <br><br>

**`Constraints:`**
- 0 <= x <= 2^31 - 1

## Solutions
### Approach 1 - Linear

```java
class Solution {
    public int mySqrt(int X) {
        int result = 1;

        for (int i = 1; i <= X; i++) {
            if ((i * i) <= X) result = i;
            else break;
        }

        return result;
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(1)

---

### Approach 2 - Binary Search

```java
class Solution {
    public int mySqrt(int X) {
        long left = 1, right = X, mid;
        long result = 0;

        while (left <= right) {
            mid = left + (right - left) / 2;

            if ((mid * mid) <= X) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return (int) result;
    }
}
```

**`Time Complexity:`** O(logN) <br>
**`Space Complexity:`** O(1)

---