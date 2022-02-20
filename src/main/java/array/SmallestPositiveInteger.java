package array;

import java.util.PriorityQueue;

public class SmallestPositiveInteger {

    // you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

    public int solution(int[] A) {
            // write your code in Java SE 8
            PriorityQueue<Integer> q = new PriorityQueue<>();

            for (int i: A) {
                if (i > 0 && !q.contains(i)) {
                    q.add(i);
                }
            }
            if (q.isEmpty()) {
                return 1;
            }

            int result = 1;
            while (!q.isEmpty()) {
                int i = q.poll();
                if (i > result) {
                    return result;
                }
                result++;
            }

            return result;
        }



}
