package array;

import java.util.Map;
import java.util.HashMap;

public class PairOfEqualsNumbers {

    // you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");



        // time complexity is O(N)
        // memory complexity is O(N)
        public boolean solution(int[] A) {
            // write your code in Java SE 8
            final int N = A.length;

            // if size of array is odd, it is not possible to pair all the values
            // so returns false.
            if (N % 2 == 1) {
                return false;
            }

            // count occurrences
            Map<Integer, Integer> count = new HashMap<>();

            for (int i: A) {
                Integer c = count.get(i);

                if (c == null) {
                    count.put(i, 1);
                } else {
                    count.put(i, c + 1);
                }
            }

            // In case some value occurs odd times, returns false;
            for (Integer c: count.values()) {
                if (c % 2 == 1) {
                    return false;
                }
            }

            return true;
        }


}
