package vincent.adventofcode.aoc2019.day7;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String... args) {
        List<String> inputStrings = InputDataUtil.getInputStrings(Day.AOC_2019_DAY_7);
        List<Integer> initialState = Arrays.stream(inputStrings.get(0).split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        System.out.println("FIRST PART:");
        System.out.println(max(initialState, 0, 4)); //43812
        System.out.println("SECOND PART:");
        System.out.println(max(initialState, 5, 9)); //59597414
    }

    private static int max(List<Integer> initialState, int minValue, int maxValue) {
        return Permutations.createAllPermutationsBetween(minValue, maxValue)
                .stream()
                .mapToInt(permutation -> Amplifiers.apply(initialState, permutation, 0))
                .max()
                .orElseThrow(() -> new IllegalArgumentException("No permutations for minValue="+minValue+", and maxValue="+maxValue));
    }
}
