## Problem Statement
Implement the `myAtoi(string A)` function, which converts a string to a 32-bit signed integer (similar to C/C++'s atoi function). <br>
The algorithm for `myAtoi(string A)` is as follows:

1. Read in and ignore any leading whitespace.
1. Check if the next character (if not already at the end of the string) is '-' or '+'. Read this character in if it is either. This determines if the final result is negative or positive respectively. Assume the result is positive if neither is present.
1. Read in next the characters until the next non-digit character or the end of the input is reached. The rest of the string is ignored.
1. Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32). If no digits were read, then the integer is 0. Change the sign as necessary (from step 2).
1. If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then clamp the integer so that it remains in the range. Specifically, integers less than -231 should be clamped to -231, and integers greater than 231 - 1 should be clamped to 231 - 1.
1. Return the integer as the final result.

**`Note:`**
- Only the space character ' ' is considered a whitespace character.
- Do not ignore any characters other than the leading whitespace or the rest of the string after the digits.

**`Constraints:`**
- 0 <= s.length <= 200
- A consists of English letters (lower-case and upper-case), digits (0-9), ' ', '+', '-', and '.'.

## Solutions

### Approach 1 - Brute Force
`Linear Approach`

```java
class Solution {
    public int myAtoi(String A) {
        A = A.trim();

        int N = A.length(), sign = 1;
        boolean isSignFound = false, isDigitFound = false;
        long result = 0;

        for (int i = 0; i < N; i++) {
            if (A.charAt(i) == '-' || A.charAt(i) == '+') {
                if (isSignFound) break;
                if (isDigitFound) break;

                isSignFound = true;
                sign = A.charAt(i) == '-' ? -1 : 1;
            }
            else if (A.charAt(i) >= '0' && A.charAt(i) <= '9') {
                isDigitFound = true;
                result *= 10;
                result += A.charAt(i) - '0';

                if ((sign * result) >= Integer.MAX_VALUE) return Integer.MAX_VALUE;
                else if ((sign * result) <= Integer.MIN_VALUE) return Integer.MIN_VALUE;
            } else break;
        }        

        return (int) (sign * result);
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(1)

---