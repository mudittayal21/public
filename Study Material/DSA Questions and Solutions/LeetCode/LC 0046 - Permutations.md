## Problem Statement
Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.

> ### Example 1:
> **`Input:`** nums = [1,2,3] <br>
> **`Output:`** [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]] <br><br>

**`Constraints:`**
- 1 <= nums.length <= 6
- -10 <= nums[i] <= 10
- All the integers of nums are unique.

## Solutions
### Approach 1 - Recursion

```java
class Solution {
    List<List<Integer>> result;
    boolean[] visited;
    ArrayList<Integer> temp;

    public List<List<Integer>> permute(int[] A) {
        int N = A.length;
        temp = new ArrayList<>();
        visited = new boolean[N];
        result = new ArrayList<>();

        permute(A, 0);
        return result;
    }

    void permute(int[] A, int index) {
        int N = A.length;

        if (temp.size() == N) {
            result.add((ArrayList<Integer>) temp.clone());
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;

            temp.add(A[i]);
            permute(A, i + 1);
            temp.remove(temp.size() - 1);

            visited[i] = false;
        }
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O()

---