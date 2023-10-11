## Problem Statement

## Solutions
### Approach 1 - Generating all the subsets

```java
class Solution {
    List<List<Integer>> result;
    public List<List<Integer>> subsets(int[] A) {
        int N = A.length;
        result = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();

        generateAll(A, 0, N - 1, temp);

        return result;
    }

    void generateAll(int[] A, int start, int end, ArrayList<Integer> temp) {
        result.add(new ArrayList(temp));

        for (int i = start; i <= end; i++) {
            temp.add(A[i]);
            generateAll(A, i + 1, end, temp);
            temp.remove(temp.size() - 1);
        }
    }
}
```

**`Time Complexity:`** O(2^N) <br>
**`Space Complexity:`** O(N)

---