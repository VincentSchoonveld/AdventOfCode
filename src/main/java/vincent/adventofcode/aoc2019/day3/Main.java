package vincent.adventofcode.aoc2019.day3;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String... args) {
        List<String> inputStrings = InputDataUtil.getInputStrings(Day.AOC_2019_DAY_3);
        List<Points> points = inputStrings.stream()
                .map(a -> Points.forInput(Arrays.asList(a.split(","))))
                .collect(Collectors.toList());
        Points intersection = points.get(0).intersectionWith(points.get(1));
        System.out.println("FIRST PART");
        System.out.println(intersection.getMinimalDistanceToCentre()); //225
        System.out.println("SECOND PART");
        System.out.println(intersection.getMinimalPathSum()); //35194
    }
}
