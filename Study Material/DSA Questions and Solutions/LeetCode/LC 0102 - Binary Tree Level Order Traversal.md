## Problem Statement
Given the `root` of a binary tree, return `the level order traversal of its nodes'` values. (i.e., from left to right, level by level).

> ### Example 1:
> ![Example 1](./images/LC_0103.jpg) <br>
> **`Input:`** root = [3, 9, 20, null, null, 15, 7] <br>
> **`Output:`** [[3], [9, 20], [15, 7]] <br><br>

**`Constraints:`**
- The number of nodes in the tree is in the range [0, 2000].
- -1000 <= Node.val <= 1000

## Solutions
### Approach 1 - Using Queues

```java
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        int size;
        TreeNode current;
        List<Integer> temp;
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            size = queue.size();
            temp = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                current = queue.poll();
                temp.add(current.val);

                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }

            result.add(new ArrayList<>(temp));
        }

        return result;
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(N)

---
### Approach 2 - Using Queues and Pair Class

```java
class Solution {
    class Pair {
        TreeNode current;
        int level;

        public Pair (TreeNode current, int level) {
            this.current = current;
            this.level = level;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<Pair> queue = new LinkedList<>();
        Pair pair;

        queue.add(new Pair(root, 0));

        while (!queue.isEmpty()) {
            pair = queue.poll();
            if (pair.current == null) continue;

            if (result.size() == pair.level)
                result.add(new ArrayList<>());

            queue.add(new Pair(pair.current.left, pair.level + 1));
            queue.add(new Pair(pair.current.right, pair.level + 1));

            result.get(pair.level).add(pair.current.val);
        }

        return result;
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(N)

---