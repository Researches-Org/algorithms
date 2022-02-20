package string;

import java.util.Set;
import java.util.HashSet;

public class MinimumNumberOfSubstrings {

    // you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");




        // It is a greedy algorithm:
        // For every char present in input string,
        // it can be added to previous substring or
        // start a new substring. When both options are
        // possible, we need to keep the char in previous
        // substring instead of creating a new one because
        // we want to minimize the number of substrings.
        //
        // Time complexity is O(n)
        // Memory complexity is O(n)
        public int solution(String S) {
            int minNumSubstr = S.isEmpty() ? 0 : 1;

            // O(n) memory complexity
            Set<Character> chars = new HashSet<>();

            // O(n) time complexity
            for (char c: S.toCharArray()) {
                if (chars.contains(c)) {
                    chars.clear();
                    minNumSubstr++;
                }
                chars.add(c);
            }

            return minNumSubstr;
        }



}
