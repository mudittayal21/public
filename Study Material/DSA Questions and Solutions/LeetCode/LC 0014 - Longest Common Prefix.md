## Problem Statement
Write a function to find the longest common prefix string amongst an array of strings.
If there is no common prefix, return an empty string "".

> ### Example 1:
> **`Input:`** strs = ["flower","flow","flight"] <br>
> **`Output:`** "fl" <br><br>

**`Constraints:`**
- 1 <= strs.length <= 200
- 0 <= strs[i].length <= 200
- strs[i] consists of only lowercase English letters.

## Solutions

### Approach 1 - Horizontal Scanning

Taking the first string as the LCP (Longest Common Prefix) and then comparing the same with next available string. Everytime the comparison happens, the LCP would either stay the same or become even smaller.

At any point, if the LCP becomes an empty string we will break the loop and return the result.

```java
class Solution {
    public String longestCommonPrefix(String[] A) {
        int N = A.length, j, min;
        String result = A[0], temp;

        for (int i = 1; i < N; i++) {
            temp = A[i];
            j = 0;
            min = Math.min(temp.length(), result.length());

            while (j < min && result.charAt(j) == temp.charAt(j)) j++;

            result = result.substring(0, j);
            if (result.length() == 0) return result;
        }

        return result;
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(1)

---

### Approach 2 - Vertical Scanning

Given that the max length of individual strings is 200. Thus, we would be going over every string character by character.

```java
class Solution {
    public String longestCommonPrefix(String[] A) {
        int N = A.length;
        StringBuilder sb = new StringBuilder();
        boolean isFinished = false;
        char temp;

        for (int i = 0; i < 200; i++) {
            if (A[0].length() <= i) break;
            temp = A[0].charAt(i);

            for (int j = 1; j < N; j++) {
                if (A[j].length() <= i || A[j].charAt(i) != temp) {
                    isFinished = true;
                    break;
                }
            }

            if (isFinished) break;
            sb.append(temp);
        }

        return sb.toString();
    }
}
```

**`Time Complexity:`** O(K), where K is the Minimum Length of the String in the Array. <br>
**`Space Complexity:`** O(1)

---

### Approach 3 - Divide and Conquer - Recurrsion

Using recursion, we would be dividing the String Array Input into smaller and smaller subarrays, then comapring the strings using the bottom up approach.

```java
class Solution {
    public String longestCommonPrefix(String[] A) {
        int N = A.length;

        return evaluateLcp(A, 0, N - 1);
    }

    String evaluateLcp(String[] A, int start, int end) {
        if (start == end) return A[start];

        int mid = start + (end - start) / 2;
        String left = evaluateLcp(A, start, mid);
        String right = evaluateLcp(A, mid + 1, end);

        StringBuilder sb = new StringBuilder();
        int min = Math.min(left.length(), right.length());

        for (int i = 0; i < min; i++) {
            if (left.charAt(i) == right.charAt(i))
                sb.append(left.charAt(i));
            else
                break;
        }

        return sb.toString();
    }
}
```

**`Time Complexity:`** O(K), where K is the sum of all the characters. <br>
**`Space Complexity:`** O(MlogN), where M is the length of the longest string and N is the length of the array.

---

### Approach 4 - Binary Search

Getting the smallest size of the string and setting the boundary as <br> `[0, <LENGTH_OF_THE_SMALLEST_STRING>]`

```java
class Solution {
    public String longestCommonPrefix(String[] A) {
        int N = A.length;
        int left = 0, right = getMinLength(A, N) - 1;
        String result = "";

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (check(A, left, mid)) {
                left = mid + 1;
                result = A[0].substring(0, mid + 1);
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    boolean check(String[] A, int start, int end) {
        int N = A.length;
        char temp;

        for (int i = 1; i < N; i++) {
            for (int j = start; j <= end; j++) {
                if (A[0].charAt(j) != A[i].charAt(j))
                    return false;
            }
        }

        return true;
    }

    int getMinLength(String[] A, int N) {
        int result = A[0].length();
        for (int i = 0; i < N; i++) result = Math.min(result, A[i].length());
        return result;
    }
}
```

**`Time Complexity:`** O(KlogM), where K is the total length of the all the strings and M is the length of the smallest string. <br>
**`Space Complexity:`** O(1)

---