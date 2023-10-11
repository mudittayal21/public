## Problem Statement
Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).

![Emaple 1](./images/LC_0101.jpg)

**`Constraints:`**
- The number of nodes in the tree is in the range [1, 1000].
- -100 <= Node.val <= 100

## Solutions
### Approach 1 - Using Recursion

```java
class Solution {
    public boolean isSymmetric(TreeNode root) {
        return check(root.left, root.right);
    }

    boolean check(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;

        if (left.val != right.val) return false;
        return check(left.left, right.right) && check(left.right, right.left);
    }
}
```

**`Time Complexity:`** O(H) <br>
**`Space Complexity:`** O(H)

--- 