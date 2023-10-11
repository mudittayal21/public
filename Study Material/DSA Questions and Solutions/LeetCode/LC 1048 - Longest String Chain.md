## Problem Statement
You are given an array of `words` where each word consists of lowercase English letters.

`wordA` is a `predecessor` of `wordB` if and only if we can insert `exactly one` letter anywhere in `wordA` `without changing the order of the other characters` to make it equal to wordB.

For example, `"abc"` is a `predecessor` of `"abac"`, while `"cba"` is not a `predecessor` of `"bcad"`. <br>
A `word chain` is a sequence of words `[word1, word2, ..., wordk]` with `k >= 1`, where `word1` is a `predecessor` of `word2`, `word2` is a predecessor of `word3`, and so on. A single word is trivially a word chain with k == 1.

`Return the length of the longest possible word chain with words chosen from the given list of words.`

## Solutions
### Using a DP Array

```java
class Solution {
    public int longestStrChain(String[] words) {
        int N = words.length, result = 1;
        
        Arrays.sort(words, (A, B) -> (A.length() - B.length()));

        int[] dp = new int[N];
        Arrays.fill(dp, 1);

        for (int i = 0; i < N; i++) {
            for (int j = (i + 1); j < N; j++) {
                if (compare(words[i], words[j])) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);

                    result = Math.max(result, dp[j]);
                }
            }
        }

        return result;
    }

    boolean compare(String A, String B) {
        int N = A.length(), M = B.length();
        boolean isSkipped = false;

        if ((M - N) != 1) return false;

        while (N > 0 && M > 0) {
            if (A.charAt(N - 1) != B.charAt(M - 1)) {
                if (isSkipped) return false;

                isSkipped = true;
                M--;
                continue;
            }

            N--;
            M--;
        }

        return N == 0 && M <= 1;
    }
}
```

**`Time Complexity:`** O(NlogN + N ^ 2) -> O(N ^ 2) <br>
**`Space Complexity:`** O(N)

---

### Using DP and a HashMap

```java
public class Solution {
    public int longestStrChain(String[] words) {
        int N = words.length, result = 1;
        HashMap<String, Integer> map = new HashMap<>();
        
        Arrays.sort(words, (A, B) -> (A.length() - B.length()));

        for (String word : words) {
            map.put(word, 1);
            int wordLength = word.length();

            for (int i = 0; i < wordLength; i++) {
                String prev =
                    word.substring(0, i) + word.substring(i + 1, wordLength);

                if (map.containsKey(prev)) {
                    int temp = Math.max(map.get(word), map.get(prev) + 1);

                    map.put(word, temp);
                }
            }

            result = Math.max(result, map.get(word));
        }

        return result;
    }
}
```

**`Time Complexity:`** O(NlogN + N * M), where M is the longest length of the word. <br>
**`Space Complexity:`** O(N)

---