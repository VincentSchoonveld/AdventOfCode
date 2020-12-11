package vincent.adventofcode.aoc2019.day15;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String... args) {
        List<String> inputStrings = InputDataUtil.getInputStrings(Day.AOC_2019_DAY_15);
        List<Long> collect = Arrays.stream(inputStrings.get(0).split(",")).map(Long::parseLong).collect(Collectors.toList());
        IntcodeProgram intcodeProgram = new IntcodeProgram(collect);
        MapMover mapMover = new MapMover(intcodeProgram, new Index(0,0));
        System.out.println("FIRST PART:");
        System.out.println(mapMover.getStepsToOxygen());
        System.out.println("SECOND PART:");
        System.out.println(mapMover.getStepsToOxygenEverywhere());
    }
}
