## Problem Statement

Given an array of integers `nums` containing `n + 1` integers where each integer is in the range `[1, n]` inclusive. <br>
There is only **`one repeated number`** in `nums`, return this repeated number.

You must solve the problem **`without modifying the array nums`** and uses only **`constant extra space`**.

> ### Example 1:
> **`Input:`** A = [1,3,4,2,2] <br>
> **`Output:`** 2 <br><br>

> ### Example 2:
> **`Input:`** nums = [3,1,3,4,2] <br>
> **`Output:`** 3 <br><br>

**`Constraints:`**
- 1 <= N <= 10^5
- A.length == N + 1
- 1 <= A[i] <= N
- All the integers in `A` appear only `once` except for `precisely one integer` which appears `two or more times`.

## Solutions

### Approach 1 - Using Two For Loops

```java
public int findDuplicate(int[] A) {
    int N = A.length;

    for (int i = 0; i < N; i++) {
        boolean isUnique = true;

        for (int j = 0; j < N; j++) {
            if (i == j) continue;

            if (A[i] == A[j]) {
                isUnique = false;
                break;
            }
        }

        if (!isUnique) return A[i];
    }

    return -1;
}
```
With this appraoch, based in the given constraints, at runtime the code would `throw a TLE` since the `size of the input is 10^5. Therefore, N^2 means 10^10 which is not accepteable`.

**`Time Complexity:`** O(N^2) <br>
**`Space Complexity:`** O(1)

---

### Approach 2 - Sorting the Array

```java
public int findDuplicate(int[] A) {
    int N = A.length;
    Arrays.sort(A);

    for (int i = 1; i < N; i++)
        if (A[i] == A[i - 1]) return A[i];

    return -1;
}
```

`Modifying the input array is not allowed plus extra space is not allowed.`

**`Time Complexity:`** O(NlogN) <br>
**`Space Complexity:`** O(N)

---

### Approach 3 - Using a HashSet / HashMap / HashTable

```java
public int findDuplicate(int[] A) {
    int N = A.length;
    HashSet<Integer> set = new HashSet<>();

    for (int i = 0; i < N; i++) {
        if (set.contains(A[i])) return A[i];

        set.add(A[i]);
    }

    return -1;
}
```

`Similary the code can be solved using the below data structures as well.`

1. **`Using a HashMap`**
    ```java
    // Calculate the frequency of every element.
    // Iterate the map to find the duplicate element.
    HashMap<Integer, Integer> map = new HashMap<>();
    ```

1. **`Using a HashTable`**
    ```java
    // Calculate the frequency of every element.
    // Iterate the hashtable to find the duplicate element.
    int[] hashTable = new int[N];
    ```

`However, using extra is not allowed.`

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(N)

---

### Approach 4 - Swapping to the Correct Index

```java
public int findDuplicate(int[] A) {
    int N = A.length;

    for (int i = 0; i < N; i++) {
        int index = A[i] - 1;

        if (i == index) continue;
        if (A[i] == A[index]) return A[i];

        int temp = A[i];
        A[i] = A[index];
        A[index] = temp;
        i--;
    }

    return -1;
}
```

`Modifying the input is not allowed.`

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(1)

---

### Approach 5 - Binary Search

```java
public int findDuplicate(int[] A) {
    int N = A.length;
    int left = 1, right = N - 1, mid = -1;
    int result = left;

    while (left <= right) {
        mid = left + (right - left) / 2;

        int count = 0;
        for (int i = 0; i < N; i++) {
            if (A[i] <= mid) count++;
        }

        if (count <= mid) left = mid + 1;
        else {
            result = mid;
            right = mid  - 1;
        } 
    }

    return result;
}
```

**`Time Complexity:`** O(NlogN) <br>
**`Space Complexity:`** O(1)

---

### Approach 6 - Floyd's Slow and Fast Pointer

If you take a look at the below example, every integer in the array also acts as a index that points to the next integer in the array. Thus, the same can be represented as a `linked list`. <br>

```java
A = [1, 3, 4, 2, 2]

1  -  3  -  2  -  4
            |     |
            -------
```

Therefore, the problem could be solved using the Floyd's Slow and Fast pointer approach where the next node is given by

> **`slow`** = A [ **`slow`** ] <br>
> **`fast`** = A [ A [ **`fast`** ] ]

```java
public int findDuplicate(int[] A) {
    int N = A.length;
    int slow = A[0], fast = A[0];

    do {
        slow = A[slow];
        fast = A[A[fast]];
    } while (slow != fast);

    slow = A[0];
    while (slow != fast) {
        slow = A[slow];
        fast = A[fast];
    }

    return slow;
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(1)

---