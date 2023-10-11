## Problem Statement
Given two integers `N` and `K`, return all possible combinations of `K` numbers chosen from the `range [1, N]`.

You may return the answer in any order.

> ### Example 1:
> **`Input:`** <br> N = 4, K = 2 <br>
> **`Output:`** <br> [ [1, 2], [1, 3], [1, 4], [2, 3], [2, 4], [3, 4]] <br>
> **`Explanation:`** <br>
> There are 4 choose 2 = 6 total combinations. <br>
> Note that combinations are unordered, i.e., [1, 2] and [2, 1] are considered to be the same combination. <br><br>

**`Constraints:`**
- 1 <= n <= 20
- 1 <= k <= n

## Solutions
### Approach 1 - Recursion

```java
class Solution {
    List<List<Integer>> result;
    public List<List<Integer>> combine(int N, int K) {
        result = new ArrayList<>();
        generateAll(1, N, K, new ArrayList<>());
        return result;
    }

    void generateAll(int start, int end, int K, ArrayList<Integer> temp) {
        if (temp.size() == K) {
            result.add(new ArrayList(temp));
            return;
        }

        for (int i = start; i <= end; i++) {
            temp.add(i);
            generateAll(i + 1, end, K, temp);
            temp.remove(temp.size() - 1);
        }
    }
}
```

**`Time Complexity:`** O( `K`C`N` ) <br>
**`Space Complexity:`** O(K), Recursion Space

---