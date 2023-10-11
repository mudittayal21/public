## Problem Statement
Given the root of a binary tree, return the `inorder traversal` of its nodes' values.

> ### Example 1:
> **`Input:`** root = [1,null,2,3] <br>
> **`Output:`** [1,3,2] <br><br>

**`Constraints:`**
- The number of nodes in the tree is in the range [0, 100].
- -100 <= Node.val <= 100

## Solutions
### Approach 1 - Using Recursion

```java
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    void inorder(TreeNode root, ArrayList<Integer> result) {
        if (root == null) return;

        inorder(root.left, result);
        result.add(root.val);
        inorder(root.right, result);
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(H), where H is the height of the tree.

---
### Approach 2 - Using Stacks

```java
class Solution {
    class Pair {
        TreeNode node;
        int task;

        public Pair (TreeNode node) {
            this.node = node;
            this.task = 0;
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(root));

        while (!stack.isEmpty()) {
            Pair current = stack.peek();

            switch (current.task) {
                case 0:
                    if (current.node.left != null)
                        stack.push(new Pair(current.node.left));
                    break;

                case 1:
                    result.add(current.node.val);
                    break;

                case 2:
                    if (current.node.right != null)
                        stack.push(new Pair(current.node.right));
                    break;

                default:
                    stack.pop();
            }

            current.task++;
        }

        return result;
    }
}
```

**`Time Complexity:`** O(4 * N) = O(N), `every node would be accessed 4 times at max.` <br>
**`Space Complexity:`** O(H)

---