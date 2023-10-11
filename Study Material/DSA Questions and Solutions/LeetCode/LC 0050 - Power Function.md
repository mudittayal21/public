## Problem Statement
Implement pow(x, n), which calculates x raised to the power n (i.e., x^n).

> ### Example 1:
> **`Input:`** x = 2.00000, n = 10 <br>
> **`Output:`** 1024.00000 <br><br>

**`Constraints:`**
- -100.0 < x < 100.0
- -2^31 <= n <= 2^31^^-1
- n is an integer.
- Either x is not zero or n > 0.
- -10^4 <= xn <= 10^4

## Solutions
### Approach 1 - Using the Math Library

```java
class Solution {
    public double myPow(double X, int N) {
        return Math.pow(X, N);
    }
}
```

**`Time Complexity:`** O(logN) <br>
**`Space Complexity:`** O(1)

---

### Approach 2 - Iterative Approach

```java
class Solution {
    public double myPow(double X, long N) {
        if (X == 1 || N == 0) return 1;

        int sign = (N < 0) ? -1 : 1;
        N *= sign;

        double result = 1;
        while (N > 0) {
            result *= X;
            N--;
        }

        if (sign == -1) return (1 / result);
        return result;
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(1)

---

### Approach 3 - Fast Power

**`Iterative Approach`**
```java
class Solution {
    public double myPow(double X, long N) {
        if (X == 1 || N == 0) return 1;

        int sign = (N < 0) ? -1 : 1;
        N *= sign;

        double result = 1;

        while (N != 0) {
            if ((N & 1) == 1) {
                result *= X;
                N--;
            } else {
                X *= X;
                N >>= 1;
            }
        }

        if (sign == -1) return (1 / result);
        return result;
    }
}
```

**`Time Complexity:`** O(logN) <br>
**`Space Complexity:`** O(1)

**`Recursive Approach`**
```java
class Solution {
    public double myPow(double X, int N) {
        long sign = N < 0 ? -1 : 1;
        double power = power(X, N * sign);

        if (sign == 1) return power;
        else return 1 / power;
    }

    double power(double X, long N) {
        if (X == 1 || N == 0) return 1;

        double power = power(X, N >> 1);
        power *= power;

        if ((N & 1) == 1) return X * power;
        else return power;
    }
}
```

**`Time Complexity:`** O(logN) <br>
**`Space Complexity:`** O(1)

---