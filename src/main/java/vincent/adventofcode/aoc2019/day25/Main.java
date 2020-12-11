package vincent.adventofcode.aoc2019.day25;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String... args) {
        List<String> inputStrings = InputDataUtil.getInputStrings(Day.AOC_2019_DAY_25);
        List<Long> collect = Arrays.stream(inputStrings.get(0).split(",")).map(Long::parseLong).collect(Collectors.toList());
        IntcodeProgram intcodeProgram = new IntcodeProgram(collect);
        AsciiProgram asciiProgram = new AsciiProgram(intcodeProgram);
        CommandProgram commandProgram = new CommandProgram(asciiProgram);

        System.out.println("FIRST PART:");
        commandProgram.run();
        System.out.println("SECOND PART FOR FREE!");
    }
}
