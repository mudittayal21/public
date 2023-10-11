## Problem Statement
`Given a string A, return the Longest Palindromic Substring in String A.`

> ### Example 1
> **`Input:`** s = "babad" <br>
> **`Output:`** "bab" <br>
> **`Explanation:`** "aba" is also a valid answer. <br><br>

**`Constraints:`**
- 1 <= A.length <= 1000
- A consist of only Digits and English Letters.

## Solutions

### Approach 1 - Brute Force
`Using 2 for loops, creating every possible substring and then checking if the substring is a palindrome or not.`

```java
class Solution {
    public String longestPalindrome(String A) {
        int N = A.length();
        int start = 0, end = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                if ((end - start) < (j - i) && check(A, i, j)) {
                    start = i;
                    end = j;
                }
            }
        }

        return A.substring(start, end + 1);
    }

    boolean check(String A, int start, int end) {
        while (start < end) {
            if (A.charAt(start) != A.charAt(end)) return false;
            
            start++;
            end--;
        }

        return true;
    }
}
```

**`Time Complexity:`** O(N^3) <br>
**`Space Complexity:`** O(1)

---

### Approach 2 - Dynamic Programming
`We would be using a 2D matrix dp, where dp[i][j] represents:` <br>
&emsp;&emsp; dp[ i ][ j ] - If the substring starting from the ith index to the jth index is a palindrome or not.

```java
class Solution {
    public String longestPalindrome(String A) {
        int N = A.length();
        int max = 1, index = 0;
        boolean[][] dp = new boolean[N][N];

        // For String Length 1
        for (int i = 0; i < N; i++) dp[i][i] = true;

        // For String Length = 2
        for (int i = 0; i < N - 1; i++) {
            if (A.charAt(i) == A.charAt(i + 1)) {
                dp[i][i + 1] = true;

                index = i;
                max = 2;
            }
        }
            
        // For String Length > 3
        for (int size = 3; size <= N; size++) {

            for (int start = 0; start < (N - size + 1); start++) {
                int end = start + size - 1;

                if (
                    A.charAt(start) == A.charAt(end) &&
                    dp[start + 1][end - 1]
                ) {
                    dp[start][end] = true;

                    if ((end - start + 1) > max) {
                        index = start;
                        max = size;
                    }
                }
            }

        }

        return A.substring(index, index + max);
    }
}
```

**`Time Complexity:`** O(N^2) <br>
**`Space Complexity:`** O(N^2)

---

### Approach 3 - Expanding along the Center

`In this approach we would be going through every character in the string and expanding along the center, till the palindromic pattern breaks.` <br>
We can have two categories of starting points:
1. For Odd Length Substrings
1. For Even Length Substrings

```java
class Solution {
    public String longestPalindrome(String A) {
        int N = A.length();
        int start = 0, end = 0, current = 0, max = 0;

        for (int i = 0; i < N; i++) {
            // For Odd Length Substrings
            current = expand(A, i, i);
            if (current > max) {
                start = i - (current >> 1);
                end = i + (current >> 1);
                max = current;
            }

            // For Even Length Substrings
            if (i < (N - 1)) {
                current = expand(A, i, i + 1);
                if (current > max) {
                    start = i + 1 - (current >> 1);
                    end = i + (current >> 1);
                    max = current;
                }
            }
        }

        return A.substring(start, end + 1);
    }

    int expand(String A, int start, int end) {
        int size = (start == end) ? -1 : 0;

        while (
            start >= 0 && end < A.length() &&
            A.charAt(start) == A.charAt(end)
        ) {
            size += 2;
            start--;
            end++;
        }

        return size;
    }
}
```

**`Time Complexity:`** O(N^2) <br>
**`Space Complexity:`** O(1)

---