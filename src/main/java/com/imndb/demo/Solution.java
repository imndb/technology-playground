package com.imndb.demo;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;



class Result {

    /*
     * Complete the 'minDeletions' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static int minDeletions(List<Integer> arr) {
        int n = arr.size(); // Size of the input array

        // Edge case: If the array is empty or null, no deletions are needed
        if (arr == null || arr.isEmpty()) {
            return 0;
        }

        List<Integer> list = new ArrayList<>(); // List to maintain the LIS (Longest Increasing Subsequence)

        // Traverse each number in the input array
        for (int number : arr) {
            int position = Collections.binarySearch(list, number); // Find the position where 'number' should be inserted

            // If 'number' is not found, Collections.binarySearch returns -(insertion point) - 1
            // Convert the negative position to its actual insertion point
            if (position < 0) {
                position = -(position + 1);
            }

            // If 'position' is within the current size of 'list', update the element at 'position'
            // Otherwise, add 'number' to the end of 'list'
            if (position < list.size()) {
                list.set(position, number);
            } else {
                list.add(number);
            }
        }

        // The number of deletions required to make the array almost sorted
        // is the difference between the original size 'n' and the size of the LIS 'list'
        return n - list.size();
    }



    public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = IntStream.range(0, arrCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int result = Result.minDeletions(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}}
