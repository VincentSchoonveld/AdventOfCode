package vincent.adventofcode.aoc2019.day17;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String... args) {
        List<String> inputStrings = InputDataUtil.getInputStrings(Day.AOC_2019_DAY_17);
        List<Long> collect = Arrays.stream(inputStrings.get(0).split(",")).map(Long::parseLong).collect(Collectors.toList());
        Surface surface = Surface.forIntcodeprogram(new IntcodeProgram(collect));
        System.out.println("FIRST PART");
        System.out.println(surface.sumAlignmentParameters());
        System.out.println("SECOND PART");
        StringDecomposition stringDecomposition = StringDecomposition.forString(surface.naive());

        collect.set(0, 2L);
        List<Long> collect1 = Arrays.stream(stringDecomposition.toInputString().split("")).map(a -> (long) a.charAt(0)).collect(Collectors.toList());

        IntcodeProgram intcodeProgram = new IntcodeProgram(collect);
        long execute = intcodeProgram.execute(collect1);
        long lastNonEnd = execute;
        while (execute != -1) {
            lastNonEnd = execute;
            execute = intcodeProgram.execute(collect1);
        }
        System.out.println(lastNonEnd);
    }


}
