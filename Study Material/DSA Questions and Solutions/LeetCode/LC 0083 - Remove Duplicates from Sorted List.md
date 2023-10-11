## Problem Statement
Given the `head` of a sorted linked list, delete `all duplicates such that each element appears only once`. Return the `linked list sorted` as well.

> ### Example 1:
> **`Input:`** head = [1,1,2] <br>
> **`Output:`** [1,2] <br><br>

**`Constraints:`**
- The number of nodes in the list is in the range [0, 300].
- -100 <= Node.val <= 100
- The list is guaranteed to be sorted in ascending order.

## Solutions
### Approach 1

```java
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode prev = head, current = head;
        
        while (current != null) {
            if (current.val != prev.val) {
                prev.next = current;
                prev = current;
            }

            current = current.next;
        }

        prev.next = null;
        return head;
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(1)

---