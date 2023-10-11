## Problem Statement
Given an `array of integers A` and an `integer B`, `return indices of the two numbers such that they add up to B`.<br>
You may assume that each input would have exactly one solution, and you may not use the same element twice. You can return the answer in any order.


> ### Example 1
> **`Input:`**
> <br>
> A = [2,7,11,15]
> <br>
> B = 9
> 
> **`Output:`** [0,1]
> 
> **`Explanation:`** Because nums[0] + nums[1] == 9, we return [0, 1].

**`Constraints:`**
- 2 <= nums.length <= 10^4
- -10^9 <= nums[i] <= 10^9
- -10^9 <= target <= 10^9
- Only one valid answer exists.

## Solutions

### Approach 1 - Brute Force
**`Using TWO FOR LOOPS.`**

``` java
class Solution {
    public int[] twoSum(int[] A, int B) {
        int N = A.length;
        
        for (int i = 0; i < N; i++) {

            for (int j = (i + 1); j < N; j++) {
                if (A[i] + A[j] == B) {
                    return new int[] {i, j};
                }
            }

        }

        return new int[] {};
    }
}
```

**`Time Complexity:`** O(N^2) <br>
**`Space Complexity:`** O(1)

While solving a DSA problem, we assume that the application will be able to perform `10^9 iterations per second (FOR JAVA)`. <br>
As per the problem statement given to us `N can range from [2, 10^4]`, thus for the N^2 approach here the maximum iterations can be `10^8`.

**`THEREFORE ACCEPTABLE`**

---

### Approach 2 - Optimisation 1
**`Using a HASHMAP`**

The HashMap here would be used to keep a track of the difference between the ith element and the current target. If moving forward an ith value is found in the HashMap it is safe to assume that we got the answer.


``` java
class Solution {
    public int[] twoSum(int[] A, int B) {
        int N = A.length, diff = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            diff = B - A[i];
            if (map.containsKey(A[i]))
                return new int[] { map.get(A[i]), i };

            map.put(diff, i);
        }

        return new int[] {};
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(N)

---