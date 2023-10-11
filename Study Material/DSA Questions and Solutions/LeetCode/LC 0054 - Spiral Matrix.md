## Problem Statement
Given an `N x M matrix`, return all elements of the matrix in spiral order.

> ### Example 1
> **`Input:`** matrix = [[1,2,3],[4,5,6],[7,8,9]] <br>
> **`Output:`** [1,2,3,6,9,8,7,4,5] <br><br>

**`Constraints:`**
- m == matrix.length
- n == matrix[i].length
- 1 <= m, n <= 10
- -100 <= matrix[i][j] <= 100

## Solutions

### Approach 1

```java
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int N = matrix.length, M = matrix[0].length;
        List<Integer> result = new ArrayList<>();
        
        int rowStart = 0, rowEnd = N - 1;
        int colStart = 0, colEnd = M - 1;

        while (rowStart <= rowEnd && colStart <= colEnd) {
            // Left to Right
            for (int i = colStart; i <= colEnd; i++) {
                result.add(matrix[rowStart][i]);
            }
            rowStart++;

            // Up to Down
            for (int i = rowStart; i <= rowEnd; i++) {
                result.add(matrix[i][colEnd]);
            }
            colEnd--;

            if (rowStart <= rowEnd) {
                // Right to Left
                for (int i = colEnd; i >= colStart; i--) {
                    result.add(matrix[rowEnd][i]);
                }
                rowEnd--;
            }

            if (colStart <= colEnd) {
                // Down to Up
                for (int i = rowEnd; i >= rowStart; i--) {
                    result.add(matrix[i][colStart]);
                }
                colStart++;
            }
        }

        return result;
    }
}
```

**`Time Complexity:`** O(N * M) <br>
**`Space Complexity:`** O(1)

---