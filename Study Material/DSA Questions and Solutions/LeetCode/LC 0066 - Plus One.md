## Problem Statement
You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit of the integer. The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading 0's.

Increment the large integer by one and return the resulting array of digits.

> ### Example 1:
> **`Input:`** digits = [1, 2, 3] <br>
> **`Output:`** [1, 2, 4] <br>
> **`Explanation:`** <br>
> The array represents the integer 123. <br>
> Incrementing by one gives 123 + 1 = 124. <br>
> Thus, the result should be [1, 2, 4]. <br><br>

**`Constraints:`**
- 1 <= digits.length <= 100
- 0 <= digits[i] <= 9
- digits does not contain any leading 0's.

## Solutions
### Approach 1 - CarryOver

```java
class Solution {
    public int[] plusOne(int[] A) {
        int N = A.length;
        int[] result = new int[N + 1];
        int carryOver = 1;

        for (int i = (N - 1); i >= 0; i--) {
            carryOver += A[i];
            result[i + 1] = carryOver % 10;
            carryOver /= 10;
        }

        if (carryOver != 0) {
            result[0] = carryOver;
            return result;
        }

        return Arrays.copyOfRange(result, 1, N + 1);
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(1)

---