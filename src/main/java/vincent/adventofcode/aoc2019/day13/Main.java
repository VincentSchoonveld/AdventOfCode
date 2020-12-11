package vincent.adventofcode.aoc2019.day13;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String... args) {
        List<String> inputStrings = InputDataUtil.getInputStrings(Day.AOC_2019_DAY_13);

        List<Long> collect2 = Arrays.stream(inputStrings.get(0).split(","))
                .map(Long::valueOf)
                .collect(Collectors.toList());
        collect2.set(0, 2L);
        IntcodeProgram intcodeProgram = new IntcodeProgram(collect2);

        Long score = null;
        int numberOfblocks = 0;

        int pos = 22;
        long input = 0L;
        Output output = intcodeProgram.execute(input);
        while(output != null) {
            if(output.getFirst() == -1L) {
                score = output.getThird();
            } else if(output.getThird() == 2) {
                numberOfblocks++;
            } else if(output.getThird() == 4) {
                int compare = Long.compare(output.getFirst(), pos);
                input = (long) compare;
                pos += compare;
            }
            output = intcodeProgram.execute(input);
        }

        System.out.println("FIRST PART:");
        System.out.println(numberOfblocks); //414
        System.out.println("SECOND PART:");
        System.out.println(score); //20183
    }
}
