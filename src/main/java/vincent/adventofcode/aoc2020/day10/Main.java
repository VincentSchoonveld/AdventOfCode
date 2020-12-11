package vincent.adventofcode.aoc2020.day10;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        List<Integer> input = InputDataUtil.getInputIntegers(Day.DAY_10);
        Collections.sort(input);
        input.add(0,0);
        input.add(input.get(input.size()-1)+3);
        System.out.println(numberOfDifferencesEqualTo(input, 1) * numberOfDifferencesEqualTo(input, 3));
        System.out.println(numberOfPossibilities(input));
    }

    private static long numberOfPossibilities(List<Integer> input) {
        Map<Integer, Long> numberOfPossibilities = new HashMap<>();
        for(int i : input) {
            long sum = IntStream.range(i - 3, i)
                    .mapToLong(j -> numberOfPossibilities.getOrDefault(j, 0L))
                    .sum();
            numberOfPossibilities.put(i, Math.max(sum, 1L));
        }
        return numberOfPossibilities.get(numberOfPossibilities.size()-1);
    }

    private static long numberOfDifferencesEqualTo(List<Integer> ls, int difference) {
        return IntStream.range(0, ls.size()-1)
                .map(i -> ls.get(i+1)-ls.get(i))
                .filter(i -> i == difference)
                .count();
    }
}
