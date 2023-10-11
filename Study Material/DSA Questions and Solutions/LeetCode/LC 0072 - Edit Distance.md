## Problem Statement
Given two strings `A` and `B`, return the minimum number of operations required to convert `A` to `B`.

You have the following three operations permitted on a word:
- Insert a character
- Delete a character
- Replace a character

> ### Example 1:
> **`Input:`** <br> A = "horse", B = "ros" <br>
> **`Output:`** 3 <br>
> **`Explanation:`** <br>
> horse -> rorse (replace 'h' with 'r') <br>
> rorse -> rose (remove 'r') <br>
> rose -> ros (remove 'e') <br><br>

**`Constraints:`**
0 <= A.length, B.length <= 500
A and B consist of lowercase English letters.

## Solutions
### Approach 1 - Recursion

```java
class Solution {
    public int minDistance(String A, String B) {
        int N = A.length(), M = B.length();

        // Comparing the two strings from the right to left
        return editDistance(A, B, N, M);    
    }

    int editDistance(String A, String B, int N, int M) {
        // When the pointer to String A reaches the left end,
        // insert all the remaining characters from String B.
        if (N == 0) return M;

        // When the pointer to String B reaches the left end,
        // delete all the remaining characters from String A.
        if (M == 0) return N;

        // If the (N - 1)th character in String A matches the
        // (M - 1)th character in String B, move forward.
        if (A.charAt(N - 1) == B.charAt(M - 1))
            return editDistance(A, B, N - 1, M - 1);

        // Case 1 : Insert a Character to String A
        int insert = editDistance(A, B, N, M - 1);

        // Case 2 : Delete a Character in String A
        int delete = editDistance(A, B, N - 1, M);

        // Case 3 : Replace a Character in String A
        int replace = editDistance(A, B, N - 1, M - 1);

        return 1 + Math.min(
            Math.min(insert, delete),
            replace
        );
    }
}
```

**`Time Complexity:`** O(3 ^ N) <br>
**`Space Complexity:`** O(N + M), recursive call stack

---

### Approach 2 - DP - Recursion using memoisation

```java
class Solution {
    int[][] memo;

    public int minDistance(String A, String B) {
        int N = A.length(), M = B.length();
        
        memo = new int[N][M];
        for (int i = 0; i < N; i++) Arrays.fill(memo[i], -1);

        // Comparing the two strings from the right to left
        return editDistance(A, B, N, M);    
    }

    int editDistance(String A, String B, int N, int M) {
        // When the pointer to String A reaches the left end,
        // insert all the remaining characters from String B.
        if (N == 0) return M;

        // When the pointer to String B reaches the left end,
        // delete all the remaining characters from String A.
        if (M == 0) return N;

        // Memoisation Call
        if (memo[N - 1][M - 1] != -1) return memo[N - 1][M - 1];

        // If the (N - 1)th character in String A matches the
        // (M - 1)th character in String B, move forward.
        if (A.charAt(N - 1) == B.charAt(M - 1))
            return memo[N - 1][M - 1] = editDistance(A, B, N - 1, M - 1);

        // Case 1 : Insert a Character to String A
        int insert = editDistance(A, B, N, M - 1);

        // Case 2 : Delete a Character in String A
        int delete = editDistance(A, B, N - 1, M);

        // Case 3 : Replace a Character in String A
        int replace = editDistance(A, B, N - 1, M - 1);

        return memo[N - 1][M - 1] = 1 + Math.min(
            Math.min(insert, delete),
            replace
        );
    }
}
```

**`Time Complexity:`** O(N * M) <br>
**`Space Complexity:`** O(N * M + (N + M)) = O(N * M)

---

### Approach 3 - DP - Tabulation or Bottom Up

```java
class Solution {
    public int minDistance(String A, String B) {
        int N = A.length(), M = B.length();
        int[][] dp = new int[N + 1][M + 1];

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                // Insert all the elements into String A
                if (i == 0)
                    dp[i][j] = j;

                // Delete all the elements from String A
                else if (j == 0)
                    dp[i][j] = i;

                // If the characters match, move forward.
                else if (A.charAt(i - 1) == B.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];

                else
                    dp[i][j] = 1 + Math.min(
                        Math.min(
                            dp[i][j - 1],   // Insert
                            dp[i - 1][j]    // Delete
                        ),
                        dp[i - 1][j - 1]    // Replace
                    );
            }
        }

        return dp[N][M];
    }
}
```

**`Time Complexity:`** O(N * M) <br>
**`Space Complexity:`** O(N * M)

---

### Approach 4 - DP - Tabulation or Bottom Up - Current and Previous Arrays

```java
class Solution {
    public int minDistance(String A, String B) {
        int N = A.length(), M = B.length();
        int[] prev = new int[M + 1], current = new int[M + 1];
        
        if (N == 0) return M;
        if (M == 0) return N;

        // When String A is Empty,
        // Insert all the characters from String B to A.
        for (int i = 0; i <= M; i++) prev[i] = i;

        for (int i = 1; i <= N; i++) {
            // Initiating i = 0
            // Remove all the elements of String B
            current[0] = i;

            for (int j = 1; j <= M; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1))
                    current[j] = prev[j - 1];

                else
                    current[j] = 1 + Math.min(
                        Math.min(
                            current[j - 1], // Insert
                            prev[j]         // Delete
                        ),
                        prev[j - 1]         // Replace
                    );
            }

            prev = current.clone();
        }

        return current[M];
    }
}
```

**`Time Complexity:`** O(N * M) <br>
**`Space Complexity:`** O(2 * N) = O(N)

---

