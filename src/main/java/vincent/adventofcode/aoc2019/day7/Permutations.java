package vincent.adventofcode.aoc2019.day7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Permutations {
    static List<List<Integer>> createAllPermutationsBetween(int from, int to) {
        List<Integer> initialList = IntStream.rangeClosed(from, to).boxed().collect(Collectors.toList());
        return createAllPermutationsOf(initialList);
    }

    private static List<List<Integer>> createAllPermutationsOf(List<Integer> list) {
        if (list.size() == 1) {
            return Collections.singletonList(list);
        } else {
            Integer element = list.remove(0);
            List<List<Integer>> toReturn = new ArrayList<>();

            List<List<Integer>> currentPermutations = createAllPermutationsOf(list);

            for(List<Integer> perm : currentPermutations) {
                for (int i  = 0; i <= perm.size(); i++) {
                    List<Integer> tempArray = new ArrayList<>(perm);
                    tempArray.add(i, element);
                    toReturn.add(tempArray);
                }
            }
            return toReturn;
        }
    }
}
