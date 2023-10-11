## Problem Statement
The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.

**`Constraints:`**
1 <= n <= 9

## Solutions
### Approach 1 - Recursion (Backtracking)

We would be using 3 visited arrays to keep a track of the Queen placement.
1. Rows Visited Array
1. Left Diagonal Array
1. Right Diagonal Array

Considering a 6 x 6 matrix, the above mentioned matrices would be initialised as

```java
rows = new boolean[N];
lDiagonal = new boolean[(2 * N) - 1];
rDiagonal = new boolean[(2 * N) - 1];
```

`1. Rows Visited Array` <br>
The position of the Queen is given as below for the columns [0 to 5]
    
```java
rows[0], rows[1], rows[2], rows[3], rows[4], rows[5]
```

`2. Left Visited Array` <br>
The left diagonals are the ones displayed in the image below. Thus the same could be directly referenced to the lDiagonals array as the below formula.

```java
lDiagonal[row + col] = true;
```

![NQueens - Left Diagonal](./images/NQueens%20-%20Left%20Diagonal.webp)

`3. Right Visited Array` <br>
The right diagonals are the ones displayed in the image below. Thus the same could be directly referenced to the lDiagonals array as the below formula.

(N - 1) is added to the formula to get the result in the range [0 to 10]

```java
rDiagonal[row - col + N - 1] = true;
```

![NQueens - Right Diagonal](./images/NQueens%20-%20Right%20Diagonal.webp)

`Code:`

```java
class Solution {
    List<List<String>> result;
    boolean[] rows, lDiagonal, rDiagonal;
    char[][] board;

    public List<List<String>> solveNQueens(int N) {
        result = new ArrayList<>();
        rows = new boolean[N];
        lDiagonal = new boolean[(2 * N) - 1];
        rDiagonal = new boolean[(2 * N) - 1];

        board = new char[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(board[i], '.');

        solve(0, N);
        return result;
    }

    void solve(int row, int N) {
        if (row == N) {
            addResult(N);
            return;
        }

        for (int col = 0; col < N; col++) {
            if (check(row, col, N)) {
                rows[col] = true;
                lDiagonal[row + col] = true;
                rDiagonal[col - row + N - 1] = true;

                board[row][col] = 'Q';
                solve(row + 1, N);
                board[row][col] = '.';

                rows[col] = false;
                lDiagonal[row + col] = false;
                rDiagonal[col - row + N - 1] = false;
            }
        }
    }

    boolean check(int row, int column, int N) {
        if (rows[column]) return false;
        if (lDiagonal[row + column]) return false;
        if (rDiagonal[column - row + N - 1]) return false;

        return true;
    }

    void addResult(int N) {
        ArrayList<String> temp = new ArrayList();
        for (int i = 0; i < N; i++) temp.add(new String(board[i]));
        result.add(temp);
    }
}
```

**`Time Complexity:`** O(N^2) <br>
**`Space Complexity:`** O(N^2)

---