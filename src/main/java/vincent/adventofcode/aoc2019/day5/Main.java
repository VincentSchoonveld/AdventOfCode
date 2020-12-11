package vincent.adventofcode.aoc2019.day5;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String... args) {
        List<String> inputStrings = InputDataUtil.getInputStrings(Day.AOC_2019_DAY_5);
        List<Integer> collect = Arrays.stream(inputStrings.get(0).split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        System.out.println("PART 1:");
        new IntcodeProgram(collect).execute(1); // 5074395

        System.out.println("PART 2:");
        new IntcodeProgram(collect).execute(5); //8346937
    }
}
