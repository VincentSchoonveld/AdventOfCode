package vincent.adventofcode.aoc2015.day13;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Permutations {
    static List<List<Integer>> permutations(int size) {
        List<Integer> initialList = IntStream.range(0, size).boxed().collect(Collectors.toList());
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
