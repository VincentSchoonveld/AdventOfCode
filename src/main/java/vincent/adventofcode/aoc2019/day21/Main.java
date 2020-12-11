package vincent.adventofcode.aoc2019.day21;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String... args) {
        List<String> inputStrings = InputDataUtil.getInputStrings(Day.AOC_2019_DAY_21);
        List<Long> collect = Arrays.stream(inputStrings.get(0).split(",")).map(Long::parseLong).collect(Collectors.toList());
        AsciiProgram asciiProgram = new AsciiProgram(collect);
        System.out.println("FIRST PART");
        String walk = new BooleanProgram(asciiProgram)
                .not("A", "T")
                .not("B", "J")
                .or("T", "J")
                .not("C", "T")
                .or("T", "J")
                .and("D", "J")
                .walk();
        System.out.println(walk);

        System.out.println("\nSECOND PART");
        String run = new BooleanProgram(asciiProgram)
                .not("A", "T")
                .not("B", "J")
                .or("T", "J")
                .not("C", "T")
                .or("T", "J")
                .and("D", "J")
                .not("H", "T")
                .not("T", "T")
                .or("E", "T")
                .and("T", "J")
                .run();

        System.out.println(run);
    }
}
