package vincent.adventofcode.aoc2019.day9;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String... args) {
        List<String> inputStrings = InputDataUtil.getInputStrings(Day.AOC_2019_DAY_9);
        List<Long> collect = Arrays.stream(inputStrings.get(0).split(","))
                .map(Long::valueOf)
                .collect(Collectors.toList());
        System.out.println("FIRST PART:");
        System.out.println(new IntcodeProgram(collect).execute(1)); //3601950151
        System.out.println("SECOND PART:");
        System.out.println(new IntcodeProgram(collect).execute(2)); //64236
    }
}
