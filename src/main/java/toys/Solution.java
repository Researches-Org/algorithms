package toys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {

//        System.out.println(
//                new Solution().popularNToys(6, 2, Arrays.asList(new String[]{"elmo", "elsa", "legos", "drone", "tablet", "warcraft"}), 6,
//                        Arrays.asList(new String[]{
//                                "Elmo is the hottest of the season! Elmo will be on every kid's wishlist!",
//                                "The new Elmo dolls are super high quality",
//                                "Expect the Elsa dolls to be very popular this year, Elsa!",
//                                "Elsa and Elmo are the toys I'll be buying for my kids, Elsa is good",
//                                "For parents of older kids, look into buying them a drone",
//                                "Warcraft is slowly rising in popularity ahead of the holiday season"
//                        })));


        System.out.println(
                new Solution().popularNToys(5, 2, Arrays.asList(new String[]{"anacell", "betacellular", "cetracular", "deltacellular", "eurocell"}), 5,
                        Arrays.asList(new String[]{
                                "I love anacell best anacell town",
                                "deltacellular has services",
                                ""
                        })));
    }

    public ArrayList<String> popularNToys(int numToys, int topToys, List<String> toys, int numQuotes,
                                         List<String> quotes) {

        Map<String, int[]> frequencies = new HashMap<>();
        for (String toy : toys) {
            frequencies.put(toy, new int[]{0, 0});
        }

        for (String quote : quotes) {
            Set<String> alreadyUsed = new HashSet<>();

            String[] words = quote.toLowerCase().split("\\W+");
            for (String w : words) {
                if (!frequencies.containsKey(w)) {
                    continue;
                }

                int[] numbers = frequencies.get(w);

                numbers[0]++;
                if (!alreadyUsed.contains(w)) {
                    numbers[1]++;
                }

                alreadyUsed.add(w);
            }
        }

        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            if (frequencies.get(a)[0] != frequencies.get(b)[0]) {
                return frequencies.get(a)[0] - frequencies.get(b)[0];
            }

            if (frequencies.get(a)[1] != frequencies.get(b)[1]) {
                return frequencies.get(a)[1] - frequencies.get(b)[1];
            }

            return b.compareTo(a);
        });

        if (topToys > numToys) {
            for (String toy : frequencies.keySet()) {
                if (frequencies.get(toy)[0] > 0) {
                    pq.add(toy);
                }
            }
        } else {
            for (String toy : toys) {
                pq.add(toy);

                if (pq.size() > topToys) {
                    pq.poll();
                }
            }
        }

        ArrayList<String> output = new ArrayList<>();
        while (!pq.isEmpty()) {
            output.add(pq.poll());
        }

        Collections.reverse(output);

        return output;
    }
}
