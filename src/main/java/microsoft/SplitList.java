package microsoft;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SplitList {

    static boolean useMap = false;

    static int calls = 0;

    static List<List<Integer>> splitBottomUp(List<Integer> list) {
        State state = new State();
        Set<State> states = new HashSet<>();
        states.add(state);

        for (int i = 0; i < list.size(); i++) {

            Set<State> tmp = new HashSet<>();
            for (State s: states) {
                State s1 = s.addToListOne(list.get(i));

                if (i == list.size() - 1 && s1.sum1 == s1.sum2) {
                    return Lists.newArrayList(s1.list1, s1.list2);
                }

                State s2 = s.addToListTwo(list.get(i));

                if (i == list.size() - 1 && s2.sum1 == s2.sum2) {
                    return Lists.newArrayList(s2.list1, s2.list2);
                }

                tmp.add(s1);
                tmp.add(s2);
            }

            states = tmp;

        }

        return null;

    }

    static List<List<Integer>> split(List<Integer> list, boolean useMap) {
        SplitList.useMap = useMap;
        calls = 0;

        Map<State, State> map = new HashMap<>();
        State state = new State();
        State result = doSplit(list, state, map);
        if (result != null) {
            List<List<Integer>> r = new ArrayList<>();
            r.add(result.list1);
            r.add(result.list2);
            return r;
        }

        return null;
    }

    static State doSplit(List<Integer> list, State state, Map<State, State> map) {
        calls++;
        if (useMap && map.containsKey(state)) {
            return map.get(state);
        }
        if (state.index < list.size()) {
            State s1 = state.addToListOne(list.get(state.index));

            State r = doSplit(list, s1, map);
            map.put(state, r);

            if (r != null) {
                return r;
            }

            State s2 = state.addToListTwo(list.get(state.index));

            r = doSplit(list, s2, map);
            map.put(state, r);

            return r;

        } else if (state.isValid()) {
            map.put(state, state);
            return state;
        }

        map.put(state, null);

        return null;

    }


    static class State {
        int index = 0;
        int sum1 = 0;
        int sum2 = 0;
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        boolean isValid() {
            return sum1 == sum2;
        }

        State(State s) {
            index = s.index + 1;
            list1 = new ArrayList<>(s.list1);
            list2 = new ArrayList<>(s.list2);
            sum1 = s.sum1;
            sum2 = s.sum2;
        }

        State() {}

        State addToListOne(int value) {
            State state = new State(this);
            state.list1.add(value);
            state.sum1 += value;

            return state;
        }

        State addToListTwo(int value) {
            State state = new State(this);
            state.list2.add(value);
            state.sum2 += value;

            return state;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof State)) {
                return false;
            }
            State s = (State) o;
            return index == s.index && ((sum1 == s.sum1 && sum2 == s.sum2) || (sum1 == s.sum2 && sum2 == s.sum1) );
        }

        @Override
        public int hashCode() {
            int result = 17;

            result = 31 * result + index;
            result = 31 * result + Math.min(sum1, sum2);
            result = 31 * result + Math.max(sum1, sum2);

            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(">>> Not using memoization");
        System.out.println(split(Lists.newArrayList(1, 3, 3, 0), false));
        System.out.println("Calls:" + calls);
        System.out.println(split(Lists.newArrayList(1, 3, 6, 2, 8), false));
        System.out.println("Calls:" + calls);
        System.out.println(split(Lists.newArrayList(1, 8, 6, 2, 3), false));
        System.out.println("Calls:" + calls);
        System.out.println(split(Lists.newArrayList(1, 5, 1, 8, 6, 2, 4, 1, 3), false));

        System.out.println(">>> Using memoization");
        System.out.println(split(Lists.newArrayList(1, 3, 3, 0), true));
        System.out.println("Calls:" + calls);
        System.out.println(split(Lists.newArrayList(1, 3, 6, 2, 8), true));
        System.out.println("Calls:" + calls);
        System.out.println(split(Lists.newArrayList(1, 8, 6, 2, 3), true));
        System.out.println("Calls:" + calls);
        System.out.println(split(Lists.newArrayList(1, 5, 1, 8, 6, 2, 4, 1, 3), true));

        System.out.println(">>> Using bottom up");
        System.out.println(splitBottomUp(Lists.newArrayList(1, 3, 3, 0)));
        System.out.println(splitBottomUp(Lists.newArrayList(1, 3, 6, 2, 8)));
        System.out.println(splitBottomUp(Lists.newArrayList(1, 8, 6, 2, 3)));
        System.out.println(splitBottomUp(Lists.newArrayList(1, 5, 1, 8, 6, 2, 4, 1, 3)));

    }


}
