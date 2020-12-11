package vincent.adventofcode.aoc2017.day5;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String... args) {
        List<String> inputStrings = InputDataUtil.getInputStrings(Day.AOC_2017_DAY_5);
        List<Integer> input = inputStrings.stream().map(Integer::valueOf).collect(Collectors.toList());

        System.out.println("FIRST PART");
        System.out.println(new Jumps(input).escapeMazeFirst());
        System.out.println("SECOND PART");
        System.out.println(new Jumps(input).escapeMazeSecond());
    }
}
