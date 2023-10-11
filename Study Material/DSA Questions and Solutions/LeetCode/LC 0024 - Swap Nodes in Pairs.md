## Problem Statement
Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)

> ### Example 1
> **`Input:`** <br> head = [1,2,3,4] <br>
> **`Output:`** <br> [2,1,4,3] <br><br>

**`Constraints:`**
- The number of nodes in the list is in the range [0, 100].
- 0 <= Node.val <= 100

## Solutions

### Approach 1
Swapping the values of adjacent nodes

```java
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode temp = head;
        while (temp != null && temp.next != null) {
            int data = temp.val;
            temp.val = temp.next.val;
            temp.next.val = data;

            temp = temp.next.next;
        }

        return head;
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(1)

---

`However, during an interview it might be asked to swap the nodes instead of swapping the values, since in the practical cases no nodes would just be holding Integer values, but pretty complex data, which might be expensive to copy.`

---

Approach 2 - Swapping Nodes

Using recursion.

```java
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode prev = head, current = head.next, next = current.next;

        current.next = prev;
        prev.next = swapPairs(next);

        return current;
    } 
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(N), Recursion Space.

---