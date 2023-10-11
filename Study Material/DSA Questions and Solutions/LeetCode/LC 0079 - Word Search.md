## Problem Statement
Given an `N x M` grid of characters `board` and a string `word`, return `true if word exists` in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.

> ### Example 1:
> **`Input:`** <br>
> board = `[["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]` <br>
> word = "ABCCED" <br>
> **`Output:`** true <br><br>

**`Constraints:`**
- N == board.length
- M = board[i].length
- 1 <= N, M <= 6
- 1 <= word.length <= 15
- board and word consists of only lowercase and uppercase English letters.

## Solutions
### Approach 1 - Using Recursion

```java
class Solution {
    boolean[][] visited;

    public boolean exist(char[][] board, String word) {
        int N = board.length, M = board[0].length;
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (check(board, word, 0, i, j)) return true;
            }
        }

        return false;
    }

    boolean check(char[][] board, String word, int index, int i, int j) {
        if (index >= word.length()) return true;

        if (i < 0 || i >= board.length) return false;
        if (j < 0 || j >= board[0].length) return false;

        if (visited[i][j]) return false;
        if (board[i][j] != word.charAt(index)) return false;

        visited[i][j] = true;
        
        boolean right = check(board, word, index + 1, i, j + 1);
        boolean left = check(board, word, index + 1, i, j - 1);
        boolean up = check(board, word, index + 1, i - 1, j);
        boolean down = check(board, word, index + 1, i + 1, j);

        visited[i][j] = false;

        return right || left || up || down;
    }
}
```

**`Time Complexity:`** O(N * M * (4 ^ wordLength)) <br>
**`Space Complexity:`** O(4 ^ wordLength)

---