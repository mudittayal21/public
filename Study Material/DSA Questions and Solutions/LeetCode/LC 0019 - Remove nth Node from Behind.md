## Problem Statement
Given the head of a linked list, remove the nth node from the end of the list and return its head.

> ### Example 1
> **`Input:`** <br> head = [1,2,3,4,5], N = 2 <br>
> **`Output:`** <br> [1,2,3,5] <br><br>

**`Constraints:`**
- The number of nodes in the list is sz.
- 1 <= sz <= 30
- 0 <= Node.val <= 100
- 1 <= n <= sz

## Solutions

### Approach 1 - Brute Force

1. Caculate the size of the Linked List.
1. Find the nth node to be deleted from the head of the Linked List

```java
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int N) {
        int size = getSize(head);
        int M = size - N;

        if (size == N) return head.next;

        ListNode current = head, prev = null;
        while (M-- > 0) {
            prev = current;
            current = current.next;
        }

        prev.next = current.next;
        return head;
    }

    int getSize(ListNode head) {
        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }
        return size;
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(1)

---

### Approach 2 - Using Fast and Slow Pointers

1. In the first iteration, the idea here is to move the fast pointer forward by N steps.
1. In the second iteration, we move the slow pointer and the fast pointer forward together, till the time the fast pointer becomes null, at this point the slow pointer will be behind the node to be removed.

```java
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int N) {
        ListNode fast = head, slow = head;

        for (int i = 0; i < N; i++) fast = fast.next;
        if (fast == null) return head.next;
        
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;

        return head;
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(1)

---