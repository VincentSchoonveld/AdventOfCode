package vincent.adventofcode.aoc2019.day25;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
class Combination {

    static List<List<Integer>> getCombinations(int total, int numberOfElements) {
        List<Integer> totalList = IntStream.range(0, total).boxed().collect(Collectors.toList());
        int[] data = new int[numberOfElements];
        List<List<Integer>> options = new ArrayList<>();
        combinationUtil(totalList, data, 0, totalList.size()-1, 0, numberOfElements, options);
        return options;
    }

    // Used a combination of: https://www.geeksforgeeks.org/print-all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/
    private static void combinationUtil(List<Integer> totalList,
                                        int[] data,
                                        int start,
                                        int end,
                                        int index,
                                        int r,
                                        List<List<Integer>> options) {
        if (index == r) {
            options.add(arrayToList(data));
        } else {
            for (int i=start; i<=end && end-i+1 >= r-index; i++) {
                data[index] = totalList.get(i);
                combinationUtil(totalList, data, i+1, end, index+1, r, options);
            }
        }
    }

    private static List<Integer> arrayToList(int[] array) {
        return Arrays.stream(array).boxed().collect(Collectors.toList());
    }
}
