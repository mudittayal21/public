## Problem Statement
Given two strings `s` and `t`, return `true` if `s` is a `subsequence` of `t`, or `false` otherwise.

A `subsequence` of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., `"ace"` is a subsequence of `"abcde"` while `"aec"` is not). 

> ### Example 1:
> **`Input:`** s = "abc", t = "ahbgdc" <br>
> **`Output:`** true <br><br>

**`Constraints:`**
- 0 <= s.length <= 100
- 0 <= t.length <= 104
- s and t consist only of lowercase English letters.

## Solutions

### Approach 1 - Using Recursion

```java
class Solution {
    public boolean isSubsequence(String S, String T) {
        int N = S.length(), M = T.length();
        return check(S, T, N, M);
    }

    private boolean check(String S, String T, int i, int j) {
        if (i == 0) return true;
        if (j == 0) return false;

        if (S.charAt(i - 1) == T.charAt(j - 1))
            return check(S, T, i - 1, j - 1);

        return check(S, T, i, j - 1);
    }
}
```

**`Time Complexity:`** O(M) <br>
**`Space Complexity:`** O(1)

---

### Approach 2 - Using two pointers

```java
class Solution {
    public boolean isSubsequence(String S, String T) {
        int N = S.length(), M = T.length();
        int i = 0, j = 0;

        while (i < N && j < M) {
            if (S.charAt(i) == T.charAt(j)) i++;
            j++;
        }

        return i == N;
    }
}
```

**`Time Complexity:`** O( `MAX`( N, M ) ) <br>
**`Space Complexity:`** O(1)

---