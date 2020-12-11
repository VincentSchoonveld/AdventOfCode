package vincent.adventofcode.aoc2015.day9;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class Permutations {
    static <T> List<List<T>> createAllPermutationsOf(List<T> list) {
        if (list.size() == 1) {
            return Collections.singletonList(list);
        } else {
            T element = list.remove(0);
            List<List<T>> toReturn = new ArrayList<>();

            List<List<T>> currentPermutations = createAllPermutationsOf(list);

            for(List<T> perm : currentPermutations) {
                for (int i  = 0; i <= perm.size(); i++) {
                    List<T> tempArray = new ArrayList<>(perm);
                    tempArray.add(i, element);
                    toReturn.add(tempArray);
                }
            }
            return toReturn;
        }
    }
}
