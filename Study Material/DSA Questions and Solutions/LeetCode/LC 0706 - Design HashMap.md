## Problem Statement
Design a `HashMap` without using any `built-in hash table libraries`.

Implement the `MyHashMap` class:

- `MyHashMap()` initializes the object with an empty map.
- `void put(int key, int value)` inserts a `(key, value)` pair into the HashMap. If the `key` already exists in the map, update the corresponding `value`.
- `int get(int key)` returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
- `void remove(key)` removes the `key` and its corresponding `value` if the map contains the mapping for the key.

**`Constraints:`**
0 <= key, value <= 106
At most 104 calls will be made to put, get, and remove.

## Solutions

### Using a Constant Size Array
Since the constaints states that the input could be at max 10^6, we will be creating an array of the same size and directly replacing the data at the key-indexes.

```java
class MyHashMap {
    int[] table;

    public MyHashMap() {
        this.table = new int[1000001];
        Arrays.fill(this.table, -1);
    }
    
    public void put(int key, int value) {
        this.table[key] = value;
    }
    
    public int get(int key) {
        return this.table[key];
    }
    
    public void remove(int key) {
        this.table[key] = -1;
    }
}
```

This approach blocks the a lot of unnecessary space in the memory.

**`Time Complexity:`**    
- `put()` - O(1)
- `get()` - O(1)
- `remove()` - O(1)

**`Space Complexity:`** O(N)

---

### Using a LinkedList Array

```java
class MyHashMap {
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity, size;
    private float loadFactor;
    private Node[] table;

    // Node Class Definition
    class Node {
        int key, value;
        Node next;

        Node (int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // Constructor
    public MyHashMap() {
        this.capacity = this.DEFAULT_CAPACITY;
        this.size = 0;
        this.loadFactor = this.LOAD_FACTOR;
        this.table = new Node[this.capacity];
    }
    
    public void put(int key, int value) {
        int index = hash(key);
        Node current = table[index];

        while (current != null) {
            if (current.key == key) {
                current.value = value;
                return;
            }

            current = current.next;
        }

        Node newNode = new Node(key, value);
        
        newNode.next = table[index];
        table[index] = newNode;

        this.size++;

        if (this.size > (this.capacity * this.loadFactor))
            resize();
    }
    
    public int get(int key) {
        int index = hash(key);
        Node current = table[index];

        while (current != null) {
            if (current.key == key)
                return current.value;

            current = current.next;
        }

        return -1;
    }
    
    public void remove(int key) {
        int index = hash(key);
        Node prev = null, current = table[index];

        while (current != null) {
            if (current.key == key) break;

            prev = current;
            current = current.next;
        }

        if (current == null)
            return;

        if (prev == null)
            table[index] = current.next;
        else
            prev.next = current.next;

        this.size--;
    }

    // Helper Functions
    private int hash(int key) {
        return key % this.capacity;
    }

    private void resize() {
        this.capacity *= 2;
        Node[] newTable = new Node[this.capacity];

        for (Node node : this.table) {
            while (node != null) {
                int index = hash(node.key);
                Node current = newTable[index], next = node.next;

                node.next = current;
                newTable[index] = node;
                node = next;
            }
        }

        this.table = newTable;
    }
}
```

**`Time Complexity:`**
- In most of the cases the TC would remain O(1).
- However, whenever the HashTable need to be resized, the TC would change to O(N).

**`Space Complexity:`** O(N)

---
