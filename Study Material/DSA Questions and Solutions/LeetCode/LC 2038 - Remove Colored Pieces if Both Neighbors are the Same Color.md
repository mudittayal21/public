## Problem Statement

There are `n` pieces arranged in a line, and each piece is colored either by `'A'` or by `'B'`. You are given a string colors of length n where `colors[i]` is the color of the `ith` piece.

Alice and Bob are playing a game where they take *`alternating turns`* removing pieces from the line. In this game, Alice moves *`first`*.

- Alice is only allowed to remove a piece colored `'A'` if `both its neighbors` are also colored 'A'. She is `not allowed` to remove pieces that are colored `'B'`.
- Bob is only allowed to remove a piece colored `'B'` if `both its neighbors` are also colored 'B'. He is `not allowed` to remove pieces that are colored `'A'`.
- Alice and Bob `cannot` remove pieces from the edge of the line.
- If a player cannot make a move on their turn, that player `loses` and the other player `wins`.

Assuming Alice and Bob play optimally, return `true` if Alice wins, or return `false` if Bob wins.

## Solution

### Approach 1 - Using Linear Algorithm

```java
class Solution {
    public boolean winnerOfGame(String A) {
        int N = A.length();
        int countA = 0, countB = 0;

        for (int i = 1; i < (N - 1); i++) {
            if (
                A.charAt(i) == A.charAt(i - 1) && 
                A.charAt(i) == A.charAt(i + 1)
            ) {
                countA += (A.charAt(i) == 'A' ? 1 : 0);
                countB += (A.charAt(i) == 'B' ? 1 : 0);
            }
        }

        return countA > countB;
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(1)

---