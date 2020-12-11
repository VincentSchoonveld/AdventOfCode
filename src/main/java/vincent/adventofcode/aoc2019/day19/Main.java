package vincent.adventofcode.aoc2019.day19;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String... args) {
        List<String> inputStrings = InputDataUtil.getInputStrings(Day.AOC_2019_DAY_19);
        List<Long> collect = Arrays.stream(inputStrings.get(0).split(",")).map(Long::parseLong).collect(Collectors.toList());
        IntcodeProgram intcodeProgram = new IntcodeProgram(collect);

        System.out.println("FIRST PART:");
        TractorBeam tractorBeam = TractorBeam.forIntcodeProgramAndSizes(intcodeProgram, 50);
        System.out.println(tractorBeam.numberOfPointsAffected(50));

        System.out.println("SECOND PART:");
        TractorBeam tractorBeam2 = TractorBeam.forIntcodeProgramAndSizes(intcodeProgram, 1300);
        Index x2 = tractorBeam2.containsSquare(100L).get();
        System.out.println(x2.output());
    }
}
