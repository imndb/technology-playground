package com.imndb.demo;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class Result2 {

    /*
     * Complete the 'getMaxOccurrences' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING components
     *  2. INTEGER minLength
     *  3. INTEGER maxLength
     *  4. INTEGER maxUnique
     */

    public static int getMaxOccurrences(String components, int minLength, int maxLength, int maxUnique) {
        int n = components.length();
        int maxOccurrences = 0;

        for (int len = minLength; len <= maxLength; len++) {
            Map<String, Integer> substringCounts = new HashMap<>();

            for (int i = 0; i <= n - len; i++) {
                String substring = components.substring(i, i + len);
                if (isValidSubstring(substring, maxUnique)) {
                    // Count occurrences of valid substrings
                    substringCounts.put(substring, substringCounts.getOrDefault(substring, 0) + 1);
                    // Update maxOccurrences
                    maxOccurrences = Math.max(maxOccurrences, substringCounts.get(substring));
                }
            }
        }

        return maxOccurrences;
    }

    private static boolean isValidSubstring(String substring, int maxUnique) {
        Set<Character> uniqueChars = new HashSet<>();
        for (char ch : substring.toCharArray()) {
            uniqueChars.add(ch);
            if (uniqueChars.size() > maxUnique) {
                return false;
            }
        }
        return true;
    }
}

public class Solution2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String components = bufferedReader.readLine();

        int minLength = Integer.parseInt(bufferedReader.readLine().trim());

        int maxLength = Integer.parseInt(bufferedReader.readLine().trim());

        int maxUnique = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result2.getMaxOccurrences(components, minLength, maxLength, maxUnique);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}


/*
To solve the problem of finding the maximum number of occurrences of any substring that satisfies given conditions, we need to consider substrings of varying lengths and ensure they meet the criteria of having a certain minimum length (`minLength`), a maximum length (`maxLength`), and containing a maximum number of unique characters (`maxUnique`).

### Approach

1. **Sliding Window Technique**: Use a sliding window approach to iterate through substrings of different lengths from `minLength` to `maxLength`.

2. **Character Frequency Count**: For each substring, maintain a count of its characters using an array or a map.

3. **Condition Check**: Ensure that the substring meets the conditions:
   - Length between `minLength` and `maxLength`.
   - Contains at most `maxUnique` unique characters.

4. **Count Occurrences**: Use a hashmap to count occurrences of each valid substring.

5. **Find Maximum Occurrences**: Track the maximum occurrence count among all valid substrings.

### Implementation

Here's the implementation of the `getMaxOccurrences` method:

```java
import java.util.*;

class Result {

    public static int getMaxOccurrences(String s, int minLength, int maxLength, int maxUnique) {
        int n = s.length();
        int maxOccurrences = 0;

        // Iterate over all possible lengths of substrings from minLength to maxLength
        for (int len = minLength; len <= maxLength; len++) {
            // HashMap to count occurrences of substrings of length len
            Map<String, Integer> substringCounts = new HashMap<>();

            // Sliding window approach to iterate through the string
            for (int i = 0; i <= n - len; i++) {
                String substring = s.substring(i, i + len);
                if (isValidSubstring(substring, maxUnique)) {
                    // Count occurrences of valid substrings
                    substringCounts.put(substring, substringCounts.getOrDefault(substring, 0) + 1);
                    // Update maxOccurrences
                    maxOccurrences = Math.max(maxOccurrences, substringCounts.get(substring));
                }
            }
        }

        return maxOccurrences;
    }

    // Helper function to check if a substring has at most maxUnique unique characters
    private static boolean isValidSubstring(String substring, int maxUnique) {
        Set<Character> uniqueChars = new HashSet<>();
        for (char ch : substring.toCharArray()) {
            uniqueChars.add(ch);
            if (uniqueChars.size() > maxUnique) {
                return false;
            }
        }
        return true;
    }
}
```

### Explanation of the Code:
- **Nested Loops**: The outer loop iterates over all possible lengths of substrings from `minLength` to `maxLength`.
- **Sliding Window**: The inner loop (`for (int i = 0; i <= n - len; i++)`) slides over the string `s` and extracts substrings of length `len`.
- **Validation**: Within each iteration, the `isValidSubstring` function checks if the current substring has at most `maxUnique` unique characters.
- **Counting**: Valid substrings are counted using a hashmap (`substringCounts`), and `maxOccurrences` is updated whenever a new substring is found with a higher count.
- **Return**: Finally, `maxOccurrences` holds the maximum count of occurrences of any substring that meets the conditions.

### Complexity
- The time complexity of this approach is approximately O(n * maxLength), where `n` is the length of the string `s`. This is because for each length `len` from `minLength` to `maxLength`, we potentially iterate over the string once.
- The space complexity is dominated by the hashmap used to store substring counts, which could grow up to O(n) in the worst case.

### Edge Cases
- The function handles cases where `minLength` equals `maxLength`, ensuring that substrings of fixed length are properly evaluated.
- It efficiently counts occurrences even when substrings overlap or when the string contains duplicate characters.

This implementation should correctly compute the maximum number of occurrences of any valid substring meeting the specified criteria.
 */