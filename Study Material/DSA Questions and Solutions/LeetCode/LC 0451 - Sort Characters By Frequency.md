## Problem Statement
Given a string `A`, sort it in `decreasing order` based on the `frequency of the characters`. The `frequency of a character` is the number of times it appears in the string.

Return the `sorted string`. If there are multiple answers, return any of them.

> ### Example 1:
> **`Input:`** A = "tree" <br>
> **`Output:`** "eert" <br>
> **`Explanation:`** 'e' appears twice while 'r' and 't' both appear once. <br>
> So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer. <br><br>

**`Constraints:`**
- 1 <= A.length <= 5 * 105
- A consists of uppercase and lowercase English letters and digits.

## Solutions
### Approach 1 - Using a Min Heap

```java
class Solution {
    class Pair implements Comparable<Pair> {
        int current, freq;

        Pair (int current, int freq) {
            this.current = current;
            this.freq = freq;
        }

        public int compareTo(Pair next) {
            return next.freq - this.freq;
        }
    }

    public String frequencySort(String A) {
        int N = A.length();
        StringBuilder sb = new StringBuilder();
        HashMap<Character, Integer> map = new HashMap<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        for (char c : A.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);

        for (char c : map.keySet())
            pq.offer(new Pair(c, map.get(c)));

        while (!pq.isEmpty()) {
            Pair top = pq.poll();

            for (int i = 0; i < top.freq; i++) {
                sb.append((char) top.current);
            }
        }

        return sb.toString();
    }
}
```

**`Time Complexity:`** O(N) <br>
**`Space Complexity:`** O(N)

---