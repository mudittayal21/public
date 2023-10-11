## Problem Statement
You are given the heads of two sorted linked lists `A` and `B`. <br>
`Merge the two lists into one sorted list`. The list should be made by splicing together the nodes of the first two lists. <br>
`Return the head of the merged linked list`.

> ### Example 1
> **`Input:`** <br> A = [1,2,4], B = [1,3,4] <br>
> **`Output:`** <br> [1,1,2,3,4,4] <br><br>

**`Constraints:`**
- The number of nodes in both lists is in the range [0, 50].
- -100 <= Node.val <= 100
- Both list1 and list2 are sorted in non-decreasing order.

## Solutions

### Approach 1

```java
class Solution {
    public ListNode mergeTwoLists(ListNode A, ListNode B) {
        ListNode head = null, current = null, temp;

        if (A == null && B == null) return head;
        if (A == null) return B;
        if (B == null) return A;

        if (A.val <= B.val) {
            head = current = A;
            A = A.next;
        } else {
            head = current = B;
            B = B.next;
        }

        while (A != null && B != null) {
            if (A.val <= B.val) {
                temp = A;
                A = A.next;
            } else {
                temp = B;
                B = B.next;
            }

            current.next = temp;
            current = current.next;
        }

        while (A != null) {
            current.next = A;
            current = current.next;
            A = A.next;
        }

        while (B != null) {
            current.next = B;
            current = current.next;
            B = B.next;
        }

        return head;
    }
}
```

**`Time Complexity:`** O(N + M) <br>
**`Space Complexity:`** O(1)

---