## Problem Statement
Given two binary strings `A` and `B`, return their sum as a binary string.

> ### Example 2:
> **`Input:`** A = "1010", B = "1011" <br>
> **`Output:`** "10101" <br><br>

**`Constraints:`**
- 1 <= A.length, B.length <= 104
- A and B consist only of '0' or '1' characters.
- Each string does not contain leading zeros except for the zero itself.

## Solutions
### Approach 1 - Linear Addition

```java
class Solution {
    public String addBinary(String A, String B) {
        StringBuilder sb = new StringBuilder();
        int N = A.length(), M = B.length();
        int carryOver = 0, max = Math.max(N, M);

        for (int i = 0; i < max; i++) {
            int X = (i < N) ? A.charAt(N - i - 1) - '0' : 0;
            int Y = (i < M) ? B.charAt(M - i - 1) - '0' : 0;

            carryOver += (X + Y);
            sb.append(carryOver % 2);
            carryOver /= 2;
        }

        if (carryOver != 0) sb.append(carryOver);

        return sb.reverse().toString();
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(1)

---