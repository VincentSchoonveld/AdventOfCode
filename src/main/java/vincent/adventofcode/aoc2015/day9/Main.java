package vincent.adventofcode.aoc2015.day9;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.List;

public class Main {
    public static void main(String... args) {
        List<String> inputStrings = InputDataUtil.getInputStrings(Day.AOC_2015_DAY_9);
        DistanceMap distanceMap = DistanceMap.forInput(inputStrings);
        int min = Permutations.createAllPermutationsOf(distanceMap.names())
                .stream()
                .mapToInt(distanceMap::sumDistance)
                .min()
                .getAsInt();
        System.out.println("min="+min);

        int max = Permutations.createAllPermutationsOf(distanceMap.names())
                .stream()
                .mapToInt(distanceMap::sumDistance)
                .max()
                .getAsInt();
        System.out.println("max="+max);
    }
}
