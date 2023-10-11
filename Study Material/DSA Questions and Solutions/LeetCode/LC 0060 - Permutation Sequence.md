## Problem Statement
The set `[1, 2, 3, ..., N]` contains a total of N! unique permutations. <br>
By listing and labeling all of the permutations in order, we get the following sequence for `N = 3`:

```
    1 - "123"
    2 - "132"
    3 - "213"
    4 - "231"
    5 - "312"
    6 - "321"
```
Given `N` and `K`, return the `Kth` permutation sequence.

## Solutions
### Approach 1 - Recursion - Generating all permutations

```java
class Solution {
    boolean[] used;
    char[] temp;
    List<String> permutations;

    public String getPermutation(int N, int K) {
        used = new boolean[N + 1];
        permutations = new ArrayList<>();

        temp = new char[N];
        generateAll(0, N);

        return permutations.get(K - 1);
    }

    void generateAll(int index, int N) {
        if (index == N) {
            permutations.add(new String(temp));
            return;
        }

        for (int i = 0; i < N; i++) {
            if (used[i + 1]) continue;

            used[i + 1] = true;
            temp[index] = (char) ((i + 1) + '0');
            generateAll(index + 1, N);
            used[i + 1] = false;
        }
    }
}
```

**`Time Complexity:`** O(N!) <br>
**`Space Complexity:`** O(N!)

---

### Approach 2

This is a mathematical trick to solve the permutations problem.

Lets take the below example.

```
When N = 4, below is the list of all the possible permutations that can be there.

1234    2134    3124    4123
1243    2143    3142    4132
1324    2314    3214    4213
1342    2341    3241    4231
1423    2413    3412    4312
1432    2431    3421    4321
```

The total number of permutations is given by the below formula. <br>
`Total Permutations = N!`

Thus, for the given example, N = 4 <br>
`Total Permutations = 4! = 24`

If we see the pattern above,

- All the permutaions are divided into 4 groups with 6 permutations in each. Given by the formula <br>
    `(Total Permutations / Total Characters) = (24 / 4) = 6 permutations.`

- Select any one group, and you'll notice that they are divided into 3 groups, each containing 2 permutations each, based on the second character. <br>
    `(Total Permutations / Total Characters) = (6 / 3) = 2 permutations.`

- Select any one group, and you'll notice that they are divided into 2 groups, each containing 1 permutations each, based on the third character. <br>
    `(Total Permutations / Total Characters) = (2 / 2) = 1 permutations.`

- The last character is what we are left with in the end.

For the given problem at hand, for any given K, this can be calculated using MOD operators.

```java
class Solution {
    public String getPermutation(int N, int K) {
        StringBuilder sb = new StringBuilder();
        List<Integer> values = new ArrayList<>();
        int fact = 1;

        for (int i = 1; i < N; i++) {
            fact *= i;
            values.add(i);
        }
        K--;
        values.add(N);

        while (true) {
            sb.append(values.get(K / fact));
            values.remove(K / fact);

            if (sb.length() == N) break;

            K = K % fact;
            fact /= values.size();
        }

        return sb.toString();
    }
}
```

### Example : Dry Run

```
N = 4, K = 14
K becomes 13 (0 based indexing)

N = 4, K = 13
Values = [1, 2, 3, 4]

Result : _ _ _ _

The first character would be
    (K / (N - 1)!) = (13 / 3!) = (13 / 6) = 2nd index element

Result : 3 _ _ _

Now,
    K becomes, the remainder left in the selected group (K % (N - 1)!)
        K = (13 % 3!) = (13 % 6) = 1
    N becomes (N - 1), since we are now left with 3 characters to fill in.
        N = N - 1 = 4 - 1 = 3
_____________

N = 3, K = 1
Values = [1, 2, 4]

The second character would be
    (K / (N - 1)!) = (1 / 2!) = (1 / 2) = 0th index element

Result : 3 1 _ _

Now,
    K becomes, the remainder left in the selected group (K % (N - 1)!)
        K = (0 % 2!) = (0 % 2) = 0
    N becomes (N - 1), since we are now left with 3 characters to fill in.
        N = N - 1 = 3 - 1 = 2
_____________

N = 2, K = 0
Values = [2, 4]

The third character would be
    (K / (N - 1)!) = (0 / 1!) = (0 / 1) = 0th index element

Result : 3 1 2 _
_____________

N = 1, K = 0
Values = [4]

The last character would be
    (K / (N - 1)!) = (0 / 0!) = (0 / 1) = 0th index element

Result : 3 1 2 4

```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(N)

---