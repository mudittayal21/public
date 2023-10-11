## Problem Statement
The string **`"PAYPALISHIRING"`** is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

```
P   A   H   N
A P L S I I G 
Y   I   R     
```
And then read line by line: **`"PAHNAPLSIIGYIR"`**

Write the code that will take a string and make this conversion given a number of rows:

**`string convert(string s, int numRows);`**

**`Constraints:`**
- 1 <= A.length <= 1000
- A consists of English letters (lower-case and upper-case), ',' and '.'.
1 <= numRows <= 1000

## Solutions
## Approach 1 - Identifying the Pattern

``` java
// Input
String A = PAYPALISHIRING;
int rows = 4

// Output
P    I    N
A  L S  I G
Y A  H R
P    I
```

The same can be represented in indexes as follows

```java
// Output (Index Version)
A[0]           A[6]             A[12]
A[1]      A[5] A[7]       A[11] A[13]
A[2] A[4]      A[8] A[10]
A[3]           A[9]
```

**`There are two types of steps here:`**

**1. Straight Steps**

    ``` java
    0 -> A[0], A[6], A[12]
    1 -> A[1], A[7], A[13]
    2 -> A[2], A[8]
    3 -> A[3], A[9]
    ```
    
If you try deriving a pattern based on the `rows` given as the input. <br>
**`Step` = [ ( 2 * rows ) - 2 ]**


**2. Slant Steps**

    ```java
       0    -> No Elements to Display
       1    -> A[5] (before A[7]), A[11] (before A[13])
       2    -> A[4] (before A[8]), A[10] (before A[14], if there)
    (N - 1) -> No Elements to Display
    ```

**`Step` = [ j + step - ( 2 * i ) ]**

```java
class Solution {
    public String convert(String A, int rows) {
        int N = A.length();
        if (N == 1 || rows <= 1) return A;

        int step = (2 * rows) - 2;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < rows; i++) {
            for (int j = i; j < N; j += step) { 
                sb.append(A.charAt(j));

                int slantIndex = j + step - (2 * i);
                if (i != 0 && i != (rows - 1) && slantIndex < N) {
                    sb.append(A.charAt(slantIndex));
                }
            }
        }

        return sb.toString();
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(1)

---