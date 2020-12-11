package vincent.adventofcode.aoc2019.day25;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
class AsciiProgram {
    private IntcodeProgram intcodeProgram;

    String execute(String input) {
        List<Long> inputLongs = inputStringToLongs(input);
        List<Long> outputList = intcodeProgram.execute(inputLongs);
        return outputToString(outputList);
    }

    private List<Long> inputStringToLongs(String input) {
        return input.chars().mapToObj(Long::valueOf).collect(Collectors.toList());
    }

    private static String outputToString(List<Long> outputList) {
        return outputList.stream()
                .map(AsciiProgram::longToString)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    private static String longToString(long aLong) {
        return isAscii(aLong) ? String.valueOf((char) aLong) : String.valueOf(aLong);
    }

    private static boolean isAscii(long l) {
        return l>=0 && l<128;
    }
}
