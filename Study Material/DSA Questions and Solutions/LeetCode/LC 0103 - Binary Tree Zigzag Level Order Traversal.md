## Problem Statement
Given the `root` of a binary tree, `return the zigzag level order traversal of its nodes' values`. (i.e., from left to right, then right to left for the next level and alternate between).

> ### Example 1:
> ![Example 1](./images/LC_0103.jpg) <br>
> **`Input:`** root = [3, 9, 20, null, null, 15, 7] <br>
> **`Output:`** [[3], [20, 9], [15, 7]] <br><br>

## Solutions
### Approach 1 - Using Queues

```java
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        int size, level = 0;
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

            if ((level & 1) == 1) Collections.reverse(temp);

            result.add(new ArrayList<>(temp));
            level++;
        }

        return result;
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(N)

---
### Approach 2 - Using Deque

```java
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        int size, level = 0;
        TreeNode current;
        List<Integer> temp;
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) return result;

        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            size = queue.size();
            temp = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                if ((level & 1) == 0)
                    current = queue.poll();
                else
                    current = queue.pollLast();

                temp.add(current.val);

                if ((level & 1) == 0) {
                    
                    if (current.left != null)
                        queue.add(current.left);
                    if (current.right != null)
                        queue.add(current.right);

                } else {
                    
                    if (current.right != null)
                        queue.addFirst(current.right);
                    if (current.left != null)
                        queue.addFirst(current.left);

                }
            }

            result.add(new ArrayList<>(temp));
            level++;
        }

        return result;
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(N)

---