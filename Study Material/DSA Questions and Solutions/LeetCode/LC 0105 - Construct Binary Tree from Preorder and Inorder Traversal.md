## Problem Statement
Given two integer arrays `preorder` and `inorder` where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and `return the binary tree`.

> ### Example 1:
> ![Example 1](./images/LC_0105.jpg) <br>
> **`Input:`** preorder = [3, 9, 20, 15, 7], inorder = [9, 3, 15, 20, 7] <br>
> **`Output:`** [3, 9, 20, null, null, 15, 7] <br><br>

**`Constraints:`**
- 1 <= preorder.length <= 3000
- inorder.length == preorder.length
- -3000 <= preorder[i], inorder[i] <= 3000
- preorder and inorder consist of unique values.
- Each value of inorder also appears in preorder.
- preorder is guaranteed to be the preorder traversal of the tree.
- inorder is guaranteed to be the inorder traversal of the tree.

## Solutions

### Approach 1

```java
class Solution {
    HashMap<Integer, Integer> map;

    public TreeNode buildTree(int[] P, int[] I) {
        int N = I.length;

        map = new HashMap<>();
        for (int i = 0; i < N; i++)
            map.put(I[i], i);

        return build(P, I, 0, N - 1, 0, N - 1);
    }

    TreeNode build(int[] P, int[] I, int ps, int pe, int is, int ie) {
        if (ps > pe || is > ie) return null;

        int rootValue = P[ps], index = map.get(rootValue);
        int leftElements = index - is;
        TreeNode root = new TreeNode(rootValue);

        root.left =
            build(P, I, ps + 1, ps + leftElements, is, index - 1);

        root.right =
            build(P, I, ps + leftElements + 1, pe, index + 1, ie);

        return root;
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(1)

---