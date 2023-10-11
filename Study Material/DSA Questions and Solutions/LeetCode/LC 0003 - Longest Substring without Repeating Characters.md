## Problem Statement
Given a string s, find the length of the longest substring without repeating characters.


> ### Example 1
> **`Input:`** A = "abcabcbb"
> 
> **`Output:`** 3
> 
> **`Explanation:`** The answer is "abc", with the length of 3.

**`Constraints:`**
- 0 <= s.length <= 5 * 10^4
- s consists of English letters, digits, symbols and spaces

## Solutions

### Approach 1 - Brute Force
**`Using TWO FOR LOOPS and a Check Function.`**

In this solution we would be using `two for loops to mark the starting and the ending indexes for a substring`, then using the `check function` we would be running another for loop to `validate the substring, if or not it contains duplicates`.

``` java
class Solution {
    public int lengthOfLongestSubstring(String A) {
        int N = A.length(), result = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                if (check(A, i, j)) {
                    result = Math.max(result, j - i + 1);
                }
            }
        }

        return result;
    }

    boolean check(String A, int start, int end) {
        char[] temp = new char[128];
        int current = 0;

        for (int i = start; i <= end; i++) {
            current = A.charAt(i);

            if (temp[current] > 0) return false;
            temp[current]++;
        }

        return true;
    }
}
```

**`Time Complexity:`** O(N^3) <br>
**`Space Complexity:`** O(1)

---

### Approach 2 - Brute Force Optimisation
**`Using a Single FOR LOOP and a Check Function.`**

In this solution we would be using a `single for loop to mark the starting index for the substring` and `then iterate over the string in the range [i, N] to check for repetitive characters`. As soon as one is encountered, we break and move to the next index.

``` java
class Solution {
    int result = 0;

    public int lengthOfLongestSubstring(String A) {
        int N = A.length();
        for (int i = 0; i < N; i++) check(A, i);
        return result;
    }

    void check(String A, int start) {
        char[] temp = new char[128];
        int N = A.length(), current = 0;

        for (int i = start; i < N; i++) {
            current = A.charAt(i);

            if (temp[current] > 0) return;
            temp[current]++;

            result = Math.max(result, i - start + 1);
        }
    }
}
```

**`Time Complexity:`** O(N^2) <br>
**`Space Complexity:`** O(1)

---

### Approach 3 - Two Pointers and HashSet
**`Using two pointers and a HashSet`**

Using the two pointers, we would be moving the jth pointer forward till the time we have non repeating characters in the `HashSet`. When a duplicate character is found in the `HashSet` we would be moving the ith pointer forward till the repeating character is not removed.

``` java
class Solution {
    public int lengthOfLongestSubstring(String A) {
        int N = A.length();
        int i = 0, j = 0, result = 0;
        int current = 0, temp = 0;
        HashSet<Integer> set = new HashSet();

        while (j < N) {
            current = A.charAt(j);

            if (set.contains(current)) {
                while (i <= j && A.charAt(i) != current) {
                    temp = A.charAt(i);

                    set.remove(temp);
                    i++;
                }
                i++;
            } else {
                result = Math.max(result, j - i + 1);
                set.add(current);
            }
            j++;
        }

        return result;
    }
}
```

**`Time Complexity:`** O(2N) ~ O(N) <br>
**`Space Complexity:`** O(N)

---
### Approach 4 - Two Pointers and Character Array
**`Using two pointers and a Character Array`**

In the above mentioned approach we would be `replacing the HashSet with a char[128] character array`. Doing so, gives us a O(1) Space Complexity.

``` java
class Solution {
    public int lengthOfLongestSubstring(String A) {
        int N = A.length();
        int i = 0, j = 0, result = 0;
        int current = 0, temp = 0;
        char[] set = new char[128];

        while (j < N) {
            current = A.charAt(j);

            if (set[current] == 1) {
                while (i <= j && A.charAt(i) != current) {
                    temp = A.charAt(i);

                    set[temp]--;
                    i++;
                }
                i++;
            } else {
                result = Math.max(result, j - i + 1);
                set[current]++;
            }
            j++;
        }

        return result;
    }
}
```

**`Time Complexity:`** O(2N) ~ O(N) <br>
**`Space Complexity:`** O(1)

---