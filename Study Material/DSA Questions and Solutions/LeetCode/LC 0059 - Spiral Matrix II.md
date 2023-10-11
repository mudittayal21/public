## Problem Statement
Given a positive integer `N`, generate an `N` x `N` matrix filled with elements from `1 to (N * N) in spiral order`.

> ### Example 1:
> **`Input:`** n = 3 <br>
> **`Output:`** [[1,2,3],[8,9,4],[7,6,5]] <br><br>

**`Constraints:`**
- 1 <= n <= 20

## Solutions
### Approach 1

```java
class Solution {
    public int[][] generateMatrix(int N) {
        int[][] result = new int[N][N];
        int current = 1;

        int rowStart = 0, rowEnd = (N - 1);
        int colStart = 0, colEnd = (N - 1);

        while (rowStart <= rowEnd && colStart <= colEnd) {
            // Left to Right
            for (int i = colStart; i <= colEnd; i++) {
                result[rowStart][i] = current++;
            }
            rowStart++;

            // Up to Down
            for (int i = rowStart; i <= rowEnd; i++) {
                result[i][colEnd] = current++;
            }
            colEnd--;

            // Right to Left
            for (int i = colEnd; i >= colStart; i--) {
                result[rowEnd][i] = current++;
            }
            rowEnd--;

            // Down to Up
            for (int i = rowEnd; i >= rowStart; i--) {
                result[i][colStart] = current++;
            }
            colStart++;
        }

        return result;
    }
}
```

**`Time Complexity:`** O(N * N) <br>
**`Space Complexity:`** O(1)

---