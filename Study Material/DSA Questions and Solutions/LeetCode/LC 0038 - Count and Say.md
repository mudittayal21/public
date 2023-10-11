## Problem Statement
The count-and-say sequence is a sequence of digit strings defined by the recursive formula:

- countAndSay(1) = "1"
- countAndSay(n) is the way you would "say" the digit string from countAndSay(n-1), which is then converted into a different digit string.

To determine how you "say" a digit string, split it into the minimal number of substrings such that each substring contains exactly one unique digit. Then for each substring, say the number of digits, then say the digit. Finally, concatenate every said digit. <br>
For example, the saying and conversion for digit string "3322251":

Given a positive integer n, return the nth term of the count-and-say sequence.

**`Constraints:`**
- 1 <= n <= 30

## Solutions
### Approach 1 - Recursion

```java
class Solution {
    public String countAndSay(int N) {
        if (N == 1) return "1";

        StringBuilder sb = new StringBuilder();
        String result = countAndSay(N - 1);
        int M = result.length(), freq = 1;
        char prev = result.charAt(0);

        for (int i = 1; i < M; i++) {
            if (result.charAt(i) == prev) freq++;
            else {
                sb.append(freq);
                sb.append(prev);

                prev = result.charAt(i);
                freq = 1;
            }
        }

        sb.append(freq);
        sb.append(prev);

        return sb.toString();
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(N)

---